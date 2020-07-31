package com.mdy.dzs.game.fight.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;

/**
 * 
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月25日  上午10:36:15
 */
public class RenderContextImpl implements  RenderContext {
	private int width;
	private int heigth;
	private BufferStrategy bufferStrategy;
	private Graphics2D g2d;
	public RenderContextImpl(BufferStrategy bufferStrategy) {
		this.bufferStrategy=bufferStrategy;
	}
	
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the heigth
	 */
	public int getHeigth() {
		return heigth;
	}

	/**
	 * @param heigth the heigth to set
	 */
	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}

	//
	@Override
	public void startRender(){
		g2d=(Graphics2D) bufferStrategy.getDrawGraphics();	
		g2d.setColor(Color.WHITE);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.fillRect(0,0,getWidth(),getHeigth());
	}
	//
	@Override
	public void endRender(){
		g2d.dispose();
		bufferStrategy.show();
	}
	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @see java.awt.Graphics#clearRect(int, int, int, int)
	 */
	@Override
	public void clearRect(int arg0, int arg1, int arg2, int arg3) {
		g2d.clearRect(arg0, arg1, arg2, arg3);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 * @param arg5
	 * @see java.awt.Graphics#drawArc(int, int, int, int, int, int)
	 */
	@Override
	public void drawArc(int arg0, int arg1, int arg2, int arg3, int arg4,
			int arg5) {
		g2d.drawArc(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 * @see java.awt.Graphics#drawChars(char[], int, int, int, int)
	 */
	@Override
	public void drawChars(char[] arg0, int arg1, int arg2, int arg3, int arg4) {
		g2d.drawChars(arg0, arg1, arg2, arg3, arg4);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @see java.awt.Graphics#drawLine(int, int, int, int)
	 */
	@Override
	public void drawLine(int arg0, int arg1, int arg2, int arg3) {
		g2d.drawLine(arg0, arg1, arg2, arg3);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @see java.awt.Graphics#drawOval(int, int, int, int)
	 */
	@Override
	public void drawOval(int arg0, int arg1, int arg2, int arg3) {
		g2d.drawOval(arg0, arg1, arg2, arg3);
	}
	//
	@Override
	public void draw(Shape shape){
		g2d.draw(shape);
	}
	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @see java.awt.Graphics#drawPolygon(int[], int[], int)
	 */
	@Override
	public void drawPolygon(int[] arg0, int[] arg1, int arg2) {
		g2d.drawPolygon(arg0, arg1, arg2);
	}

	/**
	 * @param arg0
	 * @see java.awt.Graphics#drawPolygon(java.awt.Polygon)
	 */
	@Override
	public void drawPolygon(Polygon arg0) {
		g2d.drawPolygon(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @see java.awt.Graphics#drawPolyline(int[], int[], int)
	 */
	@Override
	public void drawPolyline(int[] arg0, int[] arg1, int arg2) {
		g2d.drawPolyline(arg0, arg1, arg2);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @see java.awt.Graphics#drawRect(int, int, int, int)
	 */
	@Override
	public void drawRect(int arg0, int arg1, int arg2, int arg3) {
		g2d.drawRect(arg0, arg1, arg2, arg3);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 * @param arg5
	 * @see java.awt.Graphics#drawRoundRect(int, int, int, int, int, int)
	 */
	@Override
	public void drawRoundRect(int arg0, int arg1, int arg2, int arg3, int arg4,
			int arg5) {
		g2d.drawRoundRect(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	/**
	 * @param str
	 * @param x
	 * @param y
	 * @see java.awt.Graphics2D#drawString(java.lang.String, float, float)
	 */
	@Override
	public void drawString(String str, float x, float y) {
		g2d.drawString(str, x, y);
	}

	/**
	 * @param str
	 * @param x
	 * @param y
	 * @see java.awt.Graphics2D#drawString(java.lang.String, int, int)
	 */
	@Override
	public void drawString(String str, int x, int y) {
		g2d.drawString(str, x, y);
	}

	/**
	 * @param s
	 * @see java.awt.Graphics2D#fill(java.awt.Shape)
	 */
	@Override
	public void fill(Shape s) {
		g2d.fill(s);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 * @param arg5
	 * @see java.awt.Graphics#fillArc(int, int, int, int, int, int)
	 */
	@Override
	public void fillArc(int arg0, int arg1, int arg2, int arg3, int arg4,
			int arg5) {
		g2d.fillArc(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @see java.awt.Graphics#fillOval(int, int, int, int)
	 */
	@Override
	public void fillOval(int arg0, int arg1, int arg2, int arg3) {
		g2d.fillOval(arg0, arg1, arg2, arg3);
	}

	/**
	 * @param arg0
	 * @see java.awt.Graphics#fillPolygon(java.awt.Polygon)
	 */
	@Override
	public void fillPolygon(Polygon arg0) {
		g2d.fillPolygon(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @see java.awt.Graphics#fillRect(int, int, int, int)
	 */
	@Override
	public void fillRect(int arg0, int arg1, int arg2, int arg3) {
		g2d.fillRect(arg0, arg1, arg2, arg3);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 * @param arg5
	 * @see java.awt.Graphics#fillRoundRect(int, int, int, int, int, int)
	 */
	@Override
	public void fillRoundRect(int arg0, int arg1, int arg2, int arg3, int arg4,
			int arg5) {
		g2d.fillRoundRect(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	/**
	 * @return
	 * @see java.awt.Graphics#getColor()
	 */
	@Override
	public Color getColor() {
		return g2d.getColor();
	}

	/**
	 * @param theta
	 * @param x
	 * @param y
	 * @see java.awt.Graphics2D#rotate(double, double, double)
	 */
	@Override
	public void rotate(double theta, double x, double y) {
		g2d.rotate(theta, x, y);
	}

	/**
	 * @param theta
	 * @see java.awt.Graphics2D#rotate(double)
	 */
	@Override
	public void rotate(double theta) {
		g2d.rotate(theta);
	}

	/**
	 * @param sx
	 * @param sy
	 * @see java.awt.Graphics2D#scale(double, double)
	 */
	@Override
	public void scale(double sx, double sy) {
		g2d.scale(sx, sy);
	}

	/**
	 * @param color
	 * @see java.awt.Graphics2D#setBackground(java.awt.Color)
	 */
	@Override
	public void setBackground(Color color) {
		g2d.setBackground(color);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @see java.awt.Graphics#setClip(int, int, int, int)
	 */
	@Override
	public void setClip(int arg0, int arg1, int arg2, int arg3) {
		g2d.setClip(arg0, arg1, arg2, arg3);
	}

	/**
	 * @param arg0
	 * @see java.awt.Graphics#setClip(java.awt.Shape)
	 */
	@Override
	public void setClip(Shape arg0) {
		g2d.setClip(arg0);
	}

	/**
	 * @param arg0
	 * @see java.awt.Graphics#setColor(java.awt.Color)
	 */
	@Override
	public void setColor(Color arg0) {
		g2d.setColor(arg0);
	}

	/**
	 * @param arg0
	 * @see java.awt.Graphics#setFont(java.awt.Font)
	 */
	@Override
	public void setFont(Font arg0) {
		g2d.setFont(arg0);
	}

	/**
	 * @param paint
	 * @see java.awt.Graphics2D#setPaint(java.awt.Paint)
	 */
	@Override
	public void setPaint(Paint paint) {
		g2d.setPaint(paint);
	}

	/**
	 * @param s
	 * @see java.awt.Graphics2D#setStroke(java.awt.Stroke)
	 */
	@Override
	public void setStroke(Stroke s) {
		g2d.setStroke(s);
	}

	/**
	 * @param Tx
	 * @see java.awt.Graphics2D#setTransform(java.awt.geom.AffineTransform)
	 */
	@Override
	public void setTransform(AffineTransform Tx) {
		g2d.setTransform(Tx);
	}

	/**
	 * @param arg0
	 * @see java.awt.Graphics#setXORMode(java.awt.Color)
	 */
	@Override
	public void setXORMode(Color arg0) {
		g2d.setXORMode(arg0);
	}
	
}
