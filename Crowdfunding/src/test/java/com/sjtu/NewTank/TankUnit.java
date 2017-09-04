package com.sjtu.NewTank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class TankUnit extends Movable{
	public TankUnit(){
		this.setX(50);
		this.setY(50);
		this.setHeight(Tank_Constants.TANK_HEIGHT);
		this.setWidth(Tank_Constants.TANK_WIDTH);
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
		// TODO Auto-generated method stub
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		g.fillOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		g.setColor(c);
	}

	@Override
	public void paint(Graphics g) {

	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		//need to add code to control the border, to adjust x,y		
	}
	
	public void KeyPressed(KeyEvent e){
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			this.setY(this.getY()-5);
			if (this.getY()<0){
				this.setY(500);
			}
			break;
		case KeyEvent.VK_DOWN:
			this.setY(this.getY()+5);
			if (this.getY()>500){
				this.setY(0);
			}
			break;
		case KeyEvent.VK_LEFT:
			this.setX(this.getX()-5);
			if (this.getX()<0){
				this.setX(500);
			};
			break;
		case KeyEvent.VK_RIGHT:
			this.setX(this.getX()+5);
			if (this.getX()>500){
				this.setX(0);
			}
			break;
		}
		
	}
}
