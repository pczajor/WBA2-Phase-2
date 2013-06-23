package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
	JButton btn_publish;
	JButton btn_publish_event;
	
	JButton btn_publish_ort;
	
	JButton btn_zurueck;
	
	
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
			if (e.getSource() == btn_publish_event) {
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
				

				JTextField t_Inhalt = new JTextField("Inhalt");
				t_Inhalt.setBounds(20, 100, 360, 530);
				this.getContentPane().add(t_Inhalt);
				
				btn_browse_events.setBounds(50, 50, 150, 25);
				btn_browse_orte.setBounds(210, 50, 150, 25);
				btn_browse_subscribtions.setBounds(50, 20, 310, 25);
				
				this.getContentPane().add(btn_browse_events);
				this.getContentPane().add(btn_browse_orte);
				this.getContentPane().add(btn_browse_subscribtions);
				
				this.setVisible(true);
				this.setBounds(10, 10, 420, 700);

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
				
				JTextField t_nodeName= new JTextField("Name");
				JTextField t_von=new JTextField("von");
				JTextField t_bis=new JTextField("bis");
				JTextField t_sportart=new JTextField("Sportart");
					JTextField t_Ort= new JTextField("Ort");
					JTextField t_o_Platz= new JTextField("Platz");
					JTextField t_o_von= new JTextField("Geöffnet von");
					JTextField t_o_bis= new JTextField("Geöffnet bis");
					JTextField t_o_minS= new JTextField("Min. Spielerzahl");
					JTextField t_o_maxS= new JTextField("Max. Spielerzahl");
					JTextField t_o_ga= new JTextField("Geschlossen am:");
					JTextField t_o_Preis= new JTextField("Preis");
				JTextField t_spielerName=new JTextField("Spielernamen");
				JTextField t_spielerNummer=new JTextField("Spielernummern");
				JTextField t_blacklistName=new JTextField("Blacklistnamen");
				JTextField t_blacklsitNummer=new JTextField("Blacklistnummern");
				JTextField t_adminName=new JTextField("Adminname");
				JTextField t_adminNummer=new JTextField("Adminnummer");
				
				JLabel l_nodeName= new JLabel("Name");
				JLabel l_von=new JLabel("von");
				JLabel l_bis=new JLabel("bis");
				JLabel l_sportart=new JLabel("Sportart");
					JLabel l_Ort= new JLabel("Ort");
					JLabel l_o_Platz= new JLabel("Platz");
					JLabel l_o_von= new JLabel("Geöffnet von");
					JLabel l_o_bis= new JLabel("Geöffnet bis");
					JLabel l_o_minS= new JLabel("Min. Spielerzahl");
					JLabel l_o_maxS= new JLabel("Max. Spielerzahl");
					JLabel l_o_ga= new JLabel("Geschlossen am:");
					JLabel l_o_Preis= new JLabel("Preis");
				JLabel l_spielerName=new JLabel("Spielernamen");
				JLabel l_spielerNummer=new JLabel("Spielernummern");
				JLabel l_blacklistName=new JLabel("Blacklistnamen");
				JLabel l_blacklsitNummer=new JLabel("Blacklistnummern");
				JLabel l_adminName=new JLabel("Adminname");
				JLabel l_adminNummer=new JLabel("Adminnummer");
				

				int x= 210, y=20;
				t_nodeName.setBounds(210,y,110,25);
				t_von.setBounds(210,y+30,110,25);
				t_bis.setBounds(210,y+60,110,25);
				t_sportart.setBounds(210,y,110,25);
					t_Ort.setBounds(210+30,y,110,25);
					t_o_Platz.setBounds(210+30,y,110,25);
					t_o_von.setBounds(210+30,y,110,25);
					t_o_bis.setBounds(210+30,y,110,25);
					t_o_minS.setBounds(210+30,y,110,25);
					t_o_maxS.setBounds(210+30,y,110,25);
					t_o_ga.setBounds(210+30,y,110,25);
					t_o_Preis.setBounds(210+30,y,110,25);
				t_spielerName.setBounds(210,y,110,25);
				t_spielerNummer.setBounds(210,y,110,25);
				t_blacklistName.setBounds(210,y,110,25);
				t_blacklsitNummer.setBounds(210,y,110,25);
				t_adminName.setBounds(210,y,110,25);
				t_adminNummer.setBounds(210,y,110,25);
				
				l_nodeName.setBounds(50,100,110,25);
				l_von.setBounds(50,130,110,25);
				l_bis.setBounds(50,160,110,25);
				l_sportart.setBounds(50,190,110,25);
					l_Ort.setBounds(50+30,220,110,25);
					l_o_Platz.setBounds(50+30,250,110,25);
					l_o_von.setBounds(50+30,280,110,25);
					l_o_bis.setBounds(50+30,310,110,25);
					l_o_minS.setBounds(50+30,340,110,25);
					l_o_maxS.setBounds(50+30,370,110,25);
					l_o_ga.setBounds(50+30,400,110,25);
					l_o_Preis.setBounds(50+30,430,110,25);
				l_spielerName.setBounds(50,460,110,25);
				l_spielerNummer.setBounds(50,490,110,25);
				l_blacklistName.setBounds(50,520,110,25);
				l_blacklsitNummer.setBounds(50,550,110,25);
				l_adminName.setBounds(50,580,110,25);
				l_adminNummer.setBounds(50,610,110,25);

				
				this.getContentPane().add(t_nodeName);
				this.getContentPane().add(t_von);
				this.getContentPane().add(t_bis);
				this.getContentPane().add(t_sportart);
					this.getContentPane().add(t_Ort);
					this.getContentPane().add(t_o_Platz);
					this.getContentPane().add(t_o_von);
					this.getContentPane().add(t_o_bis);
					this.getContentPane().add(t_o_minS);
					this.getContentPane().add(t_o_maxS);
					this.getContentPane().add(t_o_ga);
					this.getContentPane().add(t_o_Preis);
				this.getContentPane().add(t_spielerName);
				this.getContentPane().add(t_spielerNummer);
				this.getContentPane().add(t_blacklistName);
				this.getContentPane().add(t_blacklsitNummer);
				this.getContentPane().add(t_adminName);
				this.getContentPane().add(t_adminNummer);
				
				this.getContentPane().add(l_nodeName);
				this.getContentPane().add(l_von);
				this.getContentPane().add(l_bis);
				this.getContentPane().add(l_sportart);
					this.getContentPane().add(l_Ort);
					this.getContentPane().add(l_o_Platz);
					this.getContentPane().add(l_o_von);
					this.getContentPane().add(l_o_bis);
					this.getContentPane().add(l_o_minS);
					this.getContentPane().add(l_o_maxS);
					this.getContentPane().add(l_o_ga);
					this.getContentPane().add(l_o_Preis);
				this.getContentPane().add(l_spielerName);
				this.getContentPane().add(l_spielerNummer);
				this.getContentPane().add(l_blacklistName);
				this.getContentPane().add(l_blacklsitNummer);
				this.getContentPane().add(l_adminName);
				this.getContentPane().add(l_adminNummer);
				
				JLabel Name= new JLabel();
				
				btn_publish_event = new JButton("Publish Event");
				btn_publish_ort = new JButton("Publish Ort");
				
				
				btn_publish_event.setBounds(50, 50, 150, 25);
				btn_publish_ort.setBounds(210, 50, 150, 25);

				this.getContentPane().add(btn_publish_event);
				this.getContentPane().add(btn_publish_ort);
				

				
				
				
				
				this.setVisible(true);
				this.setBounds(10, 10, 420, 700);
				
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
				btn_subscribe_to.setBounds(20, 80, 360, 25);


				JLabel l_nodeName = new JLabel("Nodename:");
				l_nodeName.setBounds(50, 50, 150, 25);
				this.getContentPane().add(l_nodeName);
				
				JTextField t_nodeName = new JTextField("");
				t_nodeName.setBounds(210, 50, 150, 25);
				this.getContentPane().add(t_nodeName);
				
				this.getContentPane().add(btn_subscribe_to);
				
				this.setVisible(true);
				this.setBounds(10, 10, 420, 700);
				


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
				this.setBounds(10, 10, 420, 700);
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
