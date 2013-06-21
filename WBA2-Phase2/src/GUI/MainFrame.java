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
	JTextField t_nodeName;
	JTextField t_Ort;
	JTextField t_Platz;
	JTextField t_von;
	JTextField t_bis;
	JTextField t_minS;
	JTextField t_maxS;
	JTextField t_ga;
	JTextField t_Preis;
	
	
	JButton btn_subscribe;
	JButton btn_subscribe_to;
	JButton btn_browse;
	JButton btn_browse_events;
	JButton btn_browse_orte;
	JButton btn_browse_subscribtions;
	
	JButton btn_e;
	JButton btn_f;
	JButton btn_g;
	JButton btn_login;
	JButton btn_register;
	JTextField t_username;
	JTextField t_password;


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
				dispose();
			}
			if (e.getSource() == btn_login) {
				new LoggedFrame();
				dispose();
				
			}
			if (e.getSource() == btn_publish) {
				new PublishFrame();
				dispose();
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
				
				btn_browse_events = new JButton("Browse Events");
				btn_browse_orte = new JButton("Browse Orte");
				btn_browse_subscribtions = new JButton("Browse Subscribtions");
				
				btn_browse_events.setBounds(50, 50, 150, 25);
				btn_browse_orte.setBounds(210, 50, 150, 25);
				btn_browse_subscribtions.setBounds(50, 20, 310, 25);
				
				this.getContentPane().add(btn_browse_events);
				this.getContentPane().add(btn_browse_orte);
				this.getContentPane().add(btn_browse_subscribtions);
				
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
				
				
				
				//Publishort:
				t_nodeName =new JTextField("Name");
				t_Ort =new JTextField("Ort");
				t_Platz =new JTextField("Platz");
				t_von =new JTextField("Geöffnet von");
				t_bis =new JTextField("Geöffnet bis");
				t_minS =new JTextField("Min. Spielerzahl");
				t_maxS =new JTextField("Max. Spielerzahl");
				t_ga =new JTextField("Geschlossen am:");
				t_Preis =new JTextField("Preis");
				
				t_nodeName.setBounds(50,100,50,25);
				t_Ort.setBounds(50,130,50,25);
				t_Platz.setBounds(50,160,50,25);
				t_von.setBounds(50,190,50,25);
				t_bis.setBounds(50,220,50,25);
				t_minS.setBounds(50,250,50,25);
				t_maxS.setBounds(50,280,50,25);
				t_ga.setBounds(50,310,50,25);
				t_Preis.setBounds(50,340,50,25);
				
				this.getContentPane().add(t_nodeName);
				this.getContentPane().add(t_Ort);
				this.getContentPane().add(t_Platz);
				this.getContentPane().add(t_von);
				this.getContentPane().add(t_bis);
				this.getContentPane().add(t_minS);
				this.getContentPane().add(t_maxS);
				this.getContentPane().add(t_ga);
				this.getContentPane().add(t_Preis);
				
				
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
