package com.sjtu.NewTank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//完全跟马士兵老师的一样，先这样写一个吧，把图片，网络版的都整理出来
//再自己修改
public class TankClient extends Frame{
	private int x = 50,y = 50;
	private Image offScreenImage = null;
	public static void main(String argv[]){
		new TankClient().launchFrame();
		
	}
	public void launchFrame(){
		this.setSize(Tank_Constants.GAME_WIDTH, Tank_Constants.GAME_HEIGHT);
		this.setTitle("Tank Game");
		this.setLocation(Tank_Constants.GAME_START_X,Tank_Constants.GAME_START_Y);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter(){

			/* (non-Javadoc)
			 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(1);
			}
		});		
		this.setVisible(true);
		new Thread(new PaintThread()).start();
	}
	
	public void paint(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, 30, 30);
		y+=5;
	}
	public void update(Graphics g){
		if (null == offScreenImage){
			offScreenImage = this.createImage(Tank_Constants.GAME_WIDTH, Tank_Constants.GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.GREEN);
		gOffScreen.fillRect(0, 0, Tank_Constants.GAME_WIDTH, Tank_Constants.GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	private class PaintThread implements Runnable{

		@Override
		public void run() {
			while (true){
				repaint();
				try{
					Thread.sleep(50);
				} catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	private class KeyMonitor extends KeyAdapter{

		/* (non-Javadoc)
		 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()){
			case KeyEvent.VK_UP:
				y-=5;
				if (y<0){
					y=500;
				}
				break;
			case KeyEvent.VK_DOWN:
				y+=5;
				if (y>500){
					y=0;
				}
				break;
			case KeyEvent.VK_LEFT:
				x-=5;
				if (x<0){
					x=500;
				};
				break;
			case KeyEvent.VK_RIGHT:
				x+=5;
				if (x>500){
					x=0;
				}
				break;
			}
			
		}
		
	}
}
