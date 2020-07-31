package com.mdy.dzs.game.fight.ui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class FightWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1582607625371207822L;
	public FightWindow() {
		super();
		setup();
	}
	//
	private void setup(){
		setTitle("fight");
		JPanel panel = (JPanel)getContentPane();
		panel.setPreferredSize(new Dimension(700,500));
		panel.setLayout(null);
		// setup our canvas size and put it into the content of the frame
		//设置画布尺寸并将其添加到frame(窗口)的内容面板
		setBounds(0,0,700,500);
		//
		// Tell AWT not to bother repainting our canvas since we're
		// going to do that our self in accelerated mode
		setIgnoreRepaint(true);//设置是否应该忽略从操作系统接受的绘制消息。
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		createBufferStrategy(2);
	}
	//
	public RenderContext getRenderContext(){
		RenderContextImpl rc=new RenderContextImpl(getBufferStrategy());
		rc.setWidth(getWidth());
		rc.setHeigth(getHeight());
		return rc;
	}
	public static void main(String[] args) {
		FightWindow gw=new FightWindow();
		gw.setVisible(true);
	}
}
