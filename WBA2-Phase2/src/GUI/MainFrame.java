package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
	JButton btn_publish;
	JButton btn_publish_event;
	JButton btn_publish_ort;
	
	JButton btn_subscribe;
	JButton btn_subscribe_to;
	JButton btn_browse;
	JButton btn_browse_nodes;
	JButton btn_browse_subscribtions;
	
	JButton btn_e;
	JButton btn_f;
	JButton btn_g;
	JButton btn_login;
	JButton btn_register;
	JTextField t_username;
	JTextField t_password;

	String username, password;

	public MainFrame() {
		this.getContentPane().setLayout(null);

		this.initWindow();

	}

	protected void initWindow() {
		btn_login = new JButton("Login");
		btn_login.setBounds(300, 110, 100, 30);
		btn_register = new JButton("Register");
		btn_register.setBounds(100, 110, 100, 30);
		t_username = new JTextField("username");
		t_username.setBounds(5, 10, 400, 25);
		t_password = new JTextField("password");
		t_password.setBounds(5, 80, 400, 25);

		this.getContentPane().add(btn_login);
		this.getContentPane().add(t_username);
		this.getContentPane().add(t_password);
		this.getContentPane().add(btn_register);

		ButtonHandler handler = new ButtonHandler();
		btn_login.addActionListener(handler);

	}

	public static void main(String[] args) {

		// Make frame
		MainFrame f = new MainFrame();

		MainFrame theAppWindow = new MainFrame();
		theAppWindow.setBounds(10, 10, 420, 180);
		theAppWindow.show();

	} // end of main

	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btn_browse) {
				new BrowseFrame();
			}
			if (e.getSource() == btn_login) {
				new LoggedFrame();
				
			}
			if (e.getSource() == btn_publish) {
				new PublishFrame();
			}
			if (e.getSource() == btn_subscribe) {
				new SubscribeFrame();
			}
		} // end of inner class

		public class BrowseFrame extends JFrame implements ActionListener

		{
			// initialises the frame and opens it
			public BrowseFrame() {
				this.getContentPane().setLayout(null);
				
				btn_browse_nodes = new JButton("Browse Nodes");
				btn_browse_subscribtions = new JButton("Browse Subscribtions");
				btn_browse_subscribtions = new JButton("Browse Subscribtions");
				
				btn_browse_subscribtions.setBounds(50, 50, 150, 25);
				btn_browse_nodes.setBounds(210, 50, 150, 25);
				
				this.getContentPane().add(btn_browse_subscribtions);
				this.getContentPane().add(btn_browse_nodes);

				this.setVisible(true);
				this.setBounds(10, 10, 420, 600);

			}


			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		}
		public class PublishFrame extends JFrame implements ActionListener

		{
			// initialises the frame and opens it
			public PublishFrame() {
				this.getContentPane().setLayout(null);
				
				btn_publish_event = new JButton("Publish Event");
				btn_publish_ort = new JButton("Publish Ort");
				
				btn_publish_event.setBounds(50, 50, 150, 25);
				btn_publish_ort.setBounds(210, 50, 150, 25);

				this.getContentPane().add(btn_publish_event);
				this.getContentPane().add(btn_publish_ort);
				
				this.setVisible(true);
				this.setBounds(10, 10, 420, 600);

			}


			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		}
		
		public class SubscribeFrame extends JFrame implements ActionListener

		{
			// initialises the frame and opens it
			public SubscribeFrame() {
				this.getContentPane().setLayout(null);
				
				btn_subscribe_to = new JButton("Subscribe to:");
				btn_subscribe_to.setBounds(20, 50, 360, 25);

				this.getContentPane().add(btn_subscribe_to);
				
				this.setVisible(true);
				this.setBounds(10, 10, 420, 600);
				


			}


			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		}

		public class LoggedFrame extends JFrame implements ActionListener {

			public LoggedFrame() {
				this.getContentPane().setLayout(null);
				// this.initWindow();
				btn_publish = new JButton("Publish");
				btn_subscribe = new JButton("Subscribe");
				btn_browse = new JButton("Browse");

				btn_browse.setBounds(20, 250, 360, 25);
				btn_subscribe.setBounds(20, 300, 360, 25);
				btn_publish.setBounds(20, 200, 360, 25);

				this.getContentPane().add(btn_browse);
				this.getContentPane().add(btn_subscribe);
				this.getContentPane().add(btn_publish);

				ButtonHandler handler = new ButtonHandler();
				btn_browse.addActionListener(handler);
				btn_subscribe.addActionListener(handler);
				btn_publish.addActionListener(handler);
				

				this.setVisible(true);
				this.setBounds(10, 10, 420, 600);

				this.addWindowListener(new WindowListener() {

					@Override
					public void windowActivated(WindowEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowClosed(WindowEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowClosing(WindowEvent arg0) {
						System.exit(0);

					}

					@Override
					public void windowDeactivated(WindowEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowDeiconified(WindowEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowIconified(WindowEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowOpened(WindowEvent arg0) {
						// TODO Auto-generated method stub

					}
				});
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}

		}
	}
}
