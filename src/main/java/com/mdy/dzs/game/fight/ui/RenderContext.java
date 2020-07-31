package com.mdy.dzs.game.fight.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;

/**
 * 
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月25日  上午10:36:10
 */
public interface RenderContext {

	//
	public abstract void startRender();

	//
	public abstract void endRender();

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @see java.awt.Graphics#clearRect(int, int, int, int)
	 */
	public abstract void clearRect(int arg0, int arg1, int arg2, int arg3);

	/**绘制弧线
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 * @param arg5
	 * @see java.awt.Graphics#drawArc(int, int, int, int, int, int)
	 */
	public abstract void drawArc(int arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5);

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 * @see java.awt.Graphics#drawChars(char[], int, int, int, int)
	 */
	public abstract void drawChars(char[] arg0, int arg1, int arg2, int arg3,
			int arg4);

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @see java.awt.Graphics#drawLine(int, int, int, int)
	 */
	public abstract void drawLine(int arg0, int arg1, int arg2, int arg3);

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @see java.awt.Graphics#drawOval(int, int, int, int)
	 */
	public abstract void drawOval(int arg0, int arg1, int arg2, int arg3);

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @see java.awt.Graphics#drawPolygon(int[], int[], int)
	 */
	public abstract void drawPolygon(int[] arg0, int[] arg1, int arg2);

	/**
	 * @param arg0
	 * @see java.awt.Graphics#drawPolygon(java.awt.Polygon)
	 */
	public abstract void drawPolygon(Polygon arg0);

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @see java.awt.Graphics#drawPolyline(int[], int[], int)
	 */
	public abstract void drawPolyline(int[] arg0, int[] arg1, int arg2);

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @see java.awt.Graphics#drawRect(int, int, int, int)
	 */
	public abstract void drawRect(int arg0, int arg1, int arg2, int arg3);

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 * @param arg5
	 * @see java.awt.Graphics#drawRoundRect(int, int, int, int, int, int)
	 */
	public abstract void drawRoundRect(int arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5);

	/**
	 * @param str
	 * @param x
	 * @param y
	 * @see java.awt.Graphics2D#drawString(java.lang.String, float, float)
	 */
	public abstract void drawString(String str, float x, float y);

	/**
	 * @param str
	 * @param x
	 * @param y
	 * @see java.awt.Graphics2D#drawString(java.lang.String, int, int)
	 */
	public abstract void drawString(String str, int x, int y);

	/**
	 * @param s
	 * @see java.awt.Graphics2D#fill(java.awt.Shape)
	 */
	public abstract void fill(Shape s);

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 * @param arg5
	 * @see java.awt.Graphics#fillArc(int, int, int, int, int, int)
	 */
	public abstract void fillArc(int arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5);

	/**填充椭圆
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @see java.awt.Graphics#fillOval(int, int, int, int)
	 */
	public abstract void fillOval(int arg0, int arg1, int arg2, int arg3);

	/**
	 * @param arg0
	 * @see java.awt.Graphics#fillPolygon(java.awt.Polygon)
	 */
	public abstract void fillPolygon(Polygon arg0);

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @see java.awt.Graphics#fillRect(int, int, int, int)
	 */
	public abstract void fillRect(int arg0, int arg1, int arg2, int arg3);

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 * @param arg5
	 * @see java.awt.Graphics#fillRoundRect(int, int, int, int, int, int)
	 */
	public abstract void fillRoundRect(int arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5);

	/**
	 * @return
	 * @see java.awt.Graphics#getColor()
	 */
	public abstract Color getColor();

	/**
	 * @param theta
	 * @param x
	 * @param y
	 * @see java.awt.Graphics2D#rotate(double, double, double)
	 */
	public abstract void rotate(double theta, double x, double y);

	/**
	 * @param theta
	 * @see java.awt.Graphics2D#rotate(double)
	 */
	public abstract void rotate(double theta);

	/**
	 * @param sx
	 * @param sy
	 * @see java.awt.Graphics2D#scale(double, double)
	 */
	public abstract void scale(double sx, double sy);

	/**
	 * @param color
	 * @see java.awt.Graphics2D#setBackground(java.awt.Color)
	 */
	public abstract void setBackground(Color color);

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @see java.awt.Graphics#setClip(int, int, int, int)
	 */
	public abstract void setClip(int arg0, int arg1, int arg2, int arg3);

	/**
	 * @param arg0
	 * @see java.awt.Graphics#setClip(java.awt.Shape)
	 */
	public abstract void setClip(Shape arg0);

	/**
	 * @param arg0
	 * @see java.awt.Graphics#setColor(java.awt.Color)
	 */
	public abstract void setColor(Color arg0);

	/**
	 * @param arg0
	 * @see java.awt.Graphics#setFont(java.awt.Font)
	 */
	public abstract void setFont(Font arg0);

	/**
	 * @param paint
	 * @see java.awt.Graphics2D#setPaint(java.awt.Paint)
	 */
	public abstract void setPaint(Paint paint);

	/**
	 * @param s
	 * @see java.awt.Graphics2D#setStroke(java.awt.Stroke)
	 */
	public abstract void setStroke(Stroke s);

	/**
	 * @param Tx
	 * @see java.awt.Graphics2D#setTransform(java.awt.geom.AffineTransform)
	 */
	public abstract void setTransform(AffineTransform Tx);

	/**
	 * @param arg0
	 * @see java.awt.Graphics#setXORMode(java.awt.Color)
	 */
	public abstract void setXORMode(Color arg0);

	void draw(Shape shape);

}