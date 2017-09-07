package com.sjtu.NewTank;

import java.awt.Color;
import java.awt.Graphics;

public class Missle extends Movable{
	
	public Missle(int x,int y,Direction dir){
		this.setX(x);
		this.setY(y);
		this.dir = dir;
	}

	@Override
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(this.getX(), this.getY(), 10, 10);
		g.setColor(c);
		move();
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		switch (dir){
		case D: 
			this.setY(this.getY()+10);
			break;
		case L:
			this.setX(this.getX()-10);
			break;
		case R:
			this.setX(this.getX()+10);
			break;
		case U:
			this.setY(this.getY()-10);
			break;
		case Ul:
			this.setX(this.getX()-10);
			this.setY(this.getY()-10);
			break;
		case Ur:
			this.setX(this.getX()-10);
			this.setY(this.getY()+10);
			break;
		case Dl:
			this.setX(this.getX()+10);
			this.setY(this.getY()-10);
			break;
		case Dr:
			this.setX(this.getX()+10);
			this.setY(this.getY()+10);
			break;
		}
		
	}

}
