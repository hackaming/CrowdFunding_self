package com.sjtu.Tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankClient extends Frame {
	private TankUnit tu = new TankUnit();
	public static void main(String argv[]) {
		TankClient tc = null;
		tc = new TankClient();
		tc.init();
		//this.paint();
	}

	public void init() {
		this.setTitle("Game Tank");
		this.setLocation(550, 200);
		this.setSize(500, 500);
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
