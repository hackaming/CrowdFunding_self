package com.sjtu.Tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankClient extends Frame {
	private TankUnit tu = new TankUnit();
	private Image offScreenImg = null;
	public static void main(String argv[]) {
		TankClient tc = null;
		tc = new TankClient();
		tc.init();
		//this.paint();
	}

	public void init() {
		this.setTitle(TankConstants.GAME_TITLE);
		this.setLocation(550, 200);
		this.setSize(TankConstants.GAME_HEIGHT, TankConstants.GAME_WIDTH);
		this.setBackground(Color.PINK);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.
			 * WindowEvent)
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				System.exit(1);
			}

		});
		this.setVisible(true);
		
		new Thread(new PaintThread()).start();
	}
	public void update(Graphics g){
		if (null == offScreenImg){
			offScreenImg=this.createImage(TankConstants.GAME_WIDTH,TankConstants.GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImg.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.GREEN);
		gOffScreen.fillOval(tu.getX(), tu.getY(), TankConstants.TANK_WIDTH,TankConstants.TANK_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImg, 0, 0, null);
	}

	/* (non-Javadoc)
	 * @see java.awt.Component#repaint()
	 */
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		tu.paint(g);
	}
	private class PaintThread implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true){
				repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
