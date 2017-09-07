package com.sjtu.NewTank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.sjtu.NewTank.Movable.Direction;

//完全跟马士兵老师的一样，先这样写一个吧，把图片，网络版的都整理出来
//再自己修改
public class TankClient extends Frame{
	private TankUnit tk = new TankUnit();
	private Missle m = new Missle(200,200,Direction.D);
	private Image offScreenImage = null;
	public static void main(String argv[]){
		TankClient tc = new TankClient();
		tc.launchFrame();
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
		this.addKeyListener(new KeyMonitor() );
		this.setVisible(true);
		new Thread(new PaintThread()).start();
	}
	
	public void paint(Graphics g){
		tk.draw(g);
		m.draw(g);
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
			System.out.println("Key pressed!");
			tk.KeyPressed(e);
		}
		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("Key pressed!");
			tk.keyReleased(e);
		}
	
	}
}
