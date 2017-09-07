package com.sjtu.NewTank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class TankUnit extends Movable{
	private boolean bKeyU=false,bKeyD=false,bKeyL=false,bKeyR=false;
	private ArrayList<Missle> missles = new ArrayList<Missle>();
	
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
		for (int i=0;i<missles.size();i++){
			missles.get(i).draw(g);
		}
		g.setColor(c);
		move();
	}

	@Override
	public void paint(Graphics g) {
		
	}

	@Override
	public void move() {
		switch (dir){
		case D: 
			this.setY(this.getY()+5);
			break;
		case L:
			this.setX(this.getX()-5);
			break;
		case R:
			this.setX(this.getX()+5);
			break;
		case U:
			this.setY(this.getY()-5);
			break;
		case Ul:
			this.setX(this.getX()-5);
			this.setY(this.getY()-5);
			break;
		case Ur:
			this.setX(this.getX()-5);
			this.setY(this.getY()+5);
			break;
		case Dl:
			this.setX(this.getX()+5);
			this.setY(this.getY()-5);
			break;
		case Dr:
			this.setX(this.getX()+5);
			this.setY(this.getY()+5);
			break;
		case S: 
			break;
		}

		for (int i=0;i<missles.size();i++){
			if (missles.get(i).getX()<10){ //出界了，
				missles.remove(i);
			}
			if (missles.get(i).getX()>765){
				missles.remove(i);
			}
			if (missles.get(i).getY()<30){
				missles.remove(i);
			}
			if (missles.get(i).getY()>570){
				missles.remove(i);
			}
		}
		
		//加入判断是否出界
		if (this.getX()<10){
			this.setX(765);
		}
		if (this.getX()>765){
			this.setX(10);
		}
		if (this.getY()<30){
			this.setY(570);
		}
		if (this.getY()>570){
			this.setY(30);
		}
	}
	
	public void locate(){
		//change the dir based on the keys that pressed.
		if (bKeyU && !bKeyD && !bKeyL && !bKeyR){
			predir=dir;
			dir = Direction.U;
		} else if((!bKeyU && bKeyD && !bKeyL && !bKeyR)){
			predir=dir;
			dir = Direction.D;
		} else if((!bKeyU && !bKeyD && bKeyL && !bKeyR)){
			predir=dir;
			dir = Direction.L;
		} else if((!bKeyU && !bKeyD && !bKeyL && bKeyR)){
			predir=dir;
			dir = Direction.R;
		}  else if((bKeyU && !bKeyD && bKeyL && !bKeyR)){
			predir=dir;
			dir = Direction.Ul;
		}else if((bKeyU && !bKeyD && !bKeyL && bKeyR)){
			predir=dir;
			dir = Direction.Ur;
		}else if((!bKeyU && bKeyD && bKeyL && !bKeyR)){
			predir=dir;
			dir = Direction.Dl;
		}else if((!bKeyU && bKeyD && !bKeyL && bKeyR)){
			predir=dir;
			dir = Direction.Dr;
		}else if((!bKeyU && !bKeyD && !bKeyL && !bKeyR)){
			dir = Direction.S;
		}
		
	}
	public void shoot(){
		Missle m = new Missle(this.getX(),this.getY(),predir);
		missles.add(m);
	}
	public void KeyPressed(KeyEvent e){
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			bKeyU = true;
			break;
		case KeyEvent.VK_DOWN:
			bKeyD = true;
			break;
		case KeyEvent.VK_LEFT:
			bKeyL = true;
			break;
		case KeyEvent.VK_RIGHT:
			bKeyR = true;
			break;
		case KeyEvent.VK_S:
			shoot();
			break;
		}
		System.out.println("x:"+this.getX()+",y:"+this.getY());
		locate();
	}
	public void keyReleased(KeyEvent e){
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			bKeyU = false;
			break;
		case KeyEvent.VK_DOWN:
			bKeyD = false;
			break;
		case KeyEvent.VK_LEFT:
			bKeyL = false;
			break;
		case KeyEvent.VK_RIGHT:
			bKeyR = false;
			break;
		}
		locate();
	}
}
