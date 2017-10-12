package NetworkRelated;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;


public class ChatClient extends Frame{
	private TextField tfTxt = new TextField();
	private TextArea taContent = new TextArea();
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	private Socket socket = null;
	private boolean bConnected = false;
	private Logger logger = Logger.getLogger(this.getClass());
	
	public static void main(String argv[]){
		ChatClient ct = new ChatClient();
		ct.init();
		ct.connect();
	}
	
	
	public void init(){ //initialize the window
		this.setTitle("Chat Client");
		this.setSize(800, 600);
		this.setLocation(600, 250);
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				disconnect();
				System.exit(0);
			}		
		});
		
		this.add(tfTxt, BorderLayout.SOUTH);
		this.add(taContent, BorderLayout.NORTH);
		tfTxt.addActionListener(new TFListener());
		this.pack();
		this.setVisible(true);
	}
	
	public void connect(){ //connect to the server
		try {
			socket = new Socket("127.0.0.1",9999);
			logger.info("connected to the server!");
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			bConnected = true;
			new Thread(new MessageReceive(dis)).start();
			// new the message receive thread.
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void disconnect(){ //disconnect from the server
		bConnected = false;
		if (null != dis){
			try {
				dis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (null !=dos ){
			try {
				dos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (null != socket){
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void sendMessage(String str){
		try {
			dos.writeUTF(str);
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	private class TFListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String str = tfTxt.getText();
			tfTxt.setText("");
			//add code to send out messages
			//need to connect to server, and send out
			sendMessage(str);
		}
		
	}
	private class MessageReceive implements Runnable{
		private DataInputStream dis;
		public MessageReceive(DataInputStream dis){
			this.dis = dis;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			logger.info("a new Thread's started in client to receive the message");
			while (bConnected && null != dis){
				try {
					String str = dis.readUTF();
					taContent.setText(taContent.getText()+str + '\n');
					logger.info("message received from server:"+str);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
