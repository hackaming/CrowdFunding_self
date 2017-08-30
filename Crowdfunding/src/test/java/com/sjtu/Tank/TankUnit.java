package com.sjtu.Tank;

import java.awt.Color;
import java.awt.Graphics;

public class TankUnit extends Movable{
	public TankUnit(){
		this.setX(50);
		this.setY(50);
		this.setHeight(TankConstants.GAME_HEIGHT);
		this.setWidth(TankConstants.GAME_WIDTH);
	}
	
	public TankUnit(int x,int y,int z,int b){
		this.setX(x);
		this.setY(y);
		this.setHeight(z);
		this.setWidth(b);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		g.fillOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		g.setColor(c);
		this.move(); //making it movable
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
		//need to add code to control the border, to adjust x,y
		this.setX(this.getX()+5);
		
	}
}
