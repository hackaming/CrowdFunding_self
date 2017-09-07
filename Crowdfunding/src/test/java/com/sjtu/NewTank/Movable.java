package com.sjtu.NewTank;

import java.awt.Graphics;

public abstract class Movable {
	private int x; //position
	private int y; //position
	private int width;
	private int height;
	protected enum Direction {U,D,L,R,Ul,Ur,Dl,Dr,S};
	protected Direction dir = Direction.S;
	
	public abstract void draw(Graphics g);
	public abstract void paint(Graphics g);
	public abstract void move();
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * @return the dir
	 */
	public Direction getDir() {
		return dir;
	}
	/**
	 * @param dir the dir to set
	 */
	public void setDir(Direction dir) {
		this.dir = dir;
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
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
}
