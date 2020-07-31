package com.mdy.dzs.net;

import java.util.HashMap;

import com.google.gson.Gson;
import com.mdy.dzs.net.controller.BaseController;
import com.mdy.sharp.container.biz.BizException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;

public class WebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static Logger logger = LogManager.getLogger(WebSocketFrameHandler.class);
    private static BaseController baseController = new BaseController();

    /** 空闲次数 */
    private int idle_count = 1;
    /** 发送次数 */
    private int count = 1;
    /**
     * 心跳
     */
    public final static int Heartbeat = 10;

    private final AttributeKey<String> counter = AttributeKey.valueOf("counter");

    public WebSocketFrameHandler() {
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object obj) throws Exception {
        if (obj instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) obj;
            if (IdleState.READER_IDLE.equals(event.state())) { // 如果读通道处于空闲状态，说明没有接收到心跳命令
                if (idle_count > 3) {
                    logger.warn("超过两次无客户端请求，关闭该channel");
                    ctx.channel().close();
                }

                logger.info("已等待" + Heartbeat + "秒还没收到客户端发来的消息");
                idle_count++;
            }
        } else {
            super.userEventTriggered(ctx, obj);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame frame) {
        logger.info(frame.text());
        Base b = new Gson().fromJson(frame.text(), Base.class);
        HashMap<String, Object> flushData = new HashMap<String, Object>();
        flushData.put("_rid", b._rid);
        baseController.runAction(b.body, flushData);
        if (flushData.get("body") == null) {
            Body bd = new Gson().fromJson(b.body, Body.class);
            logger.warn(String.format("m: %s a:%s", bd.m, bd.a));
        }
        String res = new Gson().toJson(flushData);
        TextWebSocketFrame tws = new TextWebSocketFrame(res);
        ctx.writeAndFlush(tws);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        Channel inChannel = ctx.channel();
        // 在线就把该通道（客户端）添加到集合中
        // channels.add(inChannel);
        logger.info("[客户端]：" + inChannel.remoteAddress().toString().substring(1) + "上线");
    }

    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        Channel inChannel = ctx.channel();
        // 离线就把该客户端踢出集合
        // channels.remove(inChannel);
        logger.info("[客户端]：" + inChannel.remoteAddress().toString().substring(1) + "离线");
        ctx.close();
    }

    class Base {
        public int _rid = 0;
        public String body = "";
    }

    class Body {
        public String m = "";
        public String a = "";
    }
}
