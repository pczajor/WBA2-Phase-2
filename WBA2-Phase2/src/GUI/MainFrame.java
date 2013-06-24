package GUI;

import grizzlyserver.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.BigInteger;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import XMPP.Config;
import XMPP.Nodes;

public class MainFrame extends JFrame {

	// erstellen aller wichtiger Instanzen und Variablen
	Nodes con = new Nodes();
	server rest;
	Config serverCon = new Config();
	boolean error = false;
	boolean ortp = false;

	public MainFrame() {
		// Das Loginfenster
		final JButton btn_login = new JButton("Login");
		JButton btn_register = new JButton("Register");
		final JTextField t_username;
		final JPasswordField t_password;
		final JLabel l_username;
		JLabel l_password;

		this.getContentPane().setLayout(null);
		btn_login.setBounds(300, 110, 100, 30);
		btn_register.setBounds(100, 110, 100, 30);
		l_username = new JLabel("Username");
		l_username.setBounds(5, 10, 400, 25);
		t_username = new JTextField("user1");
		t_username.setBounds(5, 35, 400, 25);
		l_password = new JLabel("Passwort");
		l_password.setBounds(5, 60, 400, 25);
		t_password = new JPasswordField("user");
		t_password.setBounds(5, 80, 400, 25);

		this.getContentPane().add(btn_login);
		this.getContentPane().add(t_username);
		this.getContentPane().add(t_password);
		this.getContentPane().add(btn_register);
		this.getContentPane().add(l_username);
		this.getContentPane().add(l_password);

		ActionListener al = new ActionListener() {
			// wenn die eingegebenen Daten korekt sind, wird der User verbunden
			// und eingelogt
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btn_login) {
					rest = new server();
					con.setUsername(t_username.getText());
					con.setPassword(t_password.getText());
					try {
						con.connect();
						con.login();

					} catch (Exception e1) {
						e1.printStackTrace();
						error = true;
						System.out.println("Login failed");
					}

					if (error == false) {
						new LoggedFrame();
						dispose();
					}

				}
			}
		};

		btn_login.addActionListener(al);
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

	public static void main(String[] args) {

		// erstellen des Loginfensters
		MainFrame f = new MainFrame();

		MainFrame theAppWindow = new MainFrame();
		theAppWindow.setBounds(10, 10, 420, 180);
		theAppWindow.show();

	}

	public class BrowseFrame extends JFrame implements ActionListener

	{
		// initialisiert das Browsefenster und öffnet es
		public BrowseFrame() {
			this.getContentPane().setLayout(null);

			final JButton btn_zurueck = new JButton("zurück");
			btn_zurueck.setBounds(300, 633, 100, 25);
			this.getContentPane().add(btn_zurueck);

			// die Elemente auf dem Fenster werden initalisiert.
			final JButton btn_browse_events = new JButton("EvenIDt");
			final JButton btn_browse_orte = new JButton("OrtID");
			final JButton btn_browse_subscribtions = new JButton(
					"Browse Subscribtions");
			final JButton btn_browse_nodes = new JButton("Show all Nodes");
			final JTextField t_EventID = new JTextField();
			final JTextField t_OrteID = new JTextField();
			final JTextArea a_Inhalt = new JTextArea();

			// Den Elementen werden ihr Positionen und Größen gegeben
			a_Inhalt.setBounds(20, 100, 360, 530);
			btn_browse_events.setBounds(50, 50, 75, 25);
			t_EventID.setBounds(125, 50, 75, 25);
			btn_browse_orte.setBounds(210, 50, 75, 25);
			t_OrteID.setBounds(285, 50, 75, 25);
			btn_browse_subscribtions.setBounds(50, 20, 150, 25);
			btn_browse_nodes.setBounds(210, 20, 150, 25);

			// Die Elemente werden dem Fenster hinzugefügt
			this.getContentPane().add(a_Inhalt);
			this.getContentPane().add(btn_browse_events);
			this.getContentPane().add(t_EventID);
			this.getContentPane().add(btn_browse_orte);
			this.getContentPane().add(btn_browse_subscribtions);
			this.getContentPane().add(btn_browse_nodes);
			this.getContentPane().add(t_OrteID);
			this.setVisible(true);
			this.setBounds(10, 10, 420, 700);

			// Im ActionListener steht, welcher Button welche Funktionen
			// bekommt
			ActionListener al = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (e.getSource() == btn_zurueck) {
						new LoggedFrame();
						setVisible(false);
					}
					if (e.getSource() == btn_browse_subscribtions) {
						a_Inhalt.setText(con.getSubscribedNodes().toString());
					}
					if (e.getSource() == btn_browse_nodes) {
						a_Inhalt.setText(con.getAllNodes().toString());
					}
					if (e.getSource() == btn_browse_events) {
						BigInteger id = new BigInteger(t_EventID.getText());
						a_Inhalt.setText(con.getEvent(id));
					}
					if (e.getSource() == btn_browse_orte) {
						BigInteger id = new BigInteger(t_EventID.getText());
						a_Inhalt.setText(con.getOrt(id));
					}
				}
			};

			// den Buttons wird ein ActionListener zugeteilt
			btn_zurueck.addActionListener(al);
			btn_browse_subscribtions.addActionListener(al);
			btn_browse_nodes.addActionListener(al);
			btn_browse_events.addActionListener(al);
			btn_browse_orte.addActionListener(al);

			// der Windows Listener ist dafür verantwortlich, dass wenn man ein
			// Fenster schließt, sich auch das Programm beendet
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

	public class PublishFrame extends JFrame

	{

		public PublishFrame() {
			this.getContentPane().setLayout(null);

			// Die Elemente werden initailisiert
			final JTextField t_nodeName = new JTextField("");
			final JTextField t_von = new JTextField("");
			final JTextField t_bis = new JTextField("");
			final JTextField t_sportart = new JTextField("");
			final JTextField t_Ort = new JTextField("");
			final JTextField t_o_Platz = new JTextField("");
			final JTextField t_o_von = new JTextField("");
			final JTextField t_o_bis = new JTextField("");
			final JTextField t_o_minS = new JTextField("");
			final JTextField t_o_maxS = new JTextField("");
			final JTextField t_o_ga = new JTextField("");
			final JTextField t_o_Preis = new JTextField("");
			final JTextField t_spielerName1 = new JTextField("");
			final JTextField t_spielerNummer1 = new JTextField("");
			final JTextField t_blacklistName = new JTextField("");
			final JTextField t_blacklsitNummer = new JTextField("");
			final JTextField t_adminName = new JTextField("");
			final JTextField t_adminNummer = new JTextField("");

			final JLabel l_nodeName = new JLabel("Nodename:");
			final JLabel l_von = new JLabel("Von:");
			final JLabel l_bis = new JLabel("Bis:");
			final JLabel l_sportart = new JLabel("Sportart:");
			final JLabel l_Ort = new JLabel("Ort:");
			final JLabel l_o_Platz = new JLabel("Platz:");
			final JLabel l_o_von = new JLabel("Geöffnet von:");
			final JLabel l_o_bis = new JLabel("Geöffnet bis:");
			final JLabel l_o_minS = new JLabel("Min. Spielerzahl:");
			final JLabel l_o_maxS = new JLabel("Max. Spielerzahl:");
			final JLabel l_o_ga = new JLabel("Geschlossen am:");
			final JLabel l_o_Preis = new JLabel("Preis:");
			final JLabel l_spielerName = new JLabel("Spielernamen:");
			final JLabel l_spielerNummer = new JLabel("Spielernummern:");
			final JLabel l_blacklistName = new JLabel("Blacklistnamen:");
			final JLabel l_blacklsitNummer = new JLabel("Blacklistnummern:");
			final JLabel l_adminName = new JLabel("Adminname:");
			final JLabel l_adminNummer = new JLabel("Adminnummer:");

			// die Elemente werden positioniert
			int x = 210, y = 60;
			t_nodeName.setBounds(210, y, 110, 25);
			t_von.setBounds(210, y + 30, 110, 25);
			t_bis.setBounds(210, y + 60, 110, 25);
			t_sportart.setBounds(210, y + 90, 110, 25);
			t_Ort.setBounds(210 + 30, y + 120, 110, 25);
			t_o_Platz.setBounds(210 + 30, y + 150, 110, 25);
			t_o_von.setBounds(210 + 30, y + 180, 110, 25);
			t_o_bis.setBounds(210 + 30, y + 210, 110, 25);
			t_o_minS.setBounds(210 + 30, y + 240, 110, 25);
			t_o_maxS.setBounds(210 + 30, y + 270, 110, 25);
			t_o_ga.setBounds(210 + 30, y + 300, 110, 25);
			t_o_Preis.setBounds(210 + 30, y + 330, 110, 25);
			t_spielerName1.setBounds(210, y + 360, 110, 25);
			t_spielerNummer1.setBounds(210, y + 390, 110, 25);
			t_blacklistName.setBounds(210, y + 420, 110, 25);
			t_blacklsitNummer.setBounds(210, y + 450, 110, 25);
			t_adminName.setBounds(210, y + 480, 110, 25);
			t_adminNummer.setBounds(210, y + 510, 110, 25);

			l_nodeName.setBounds(50, y, 110, 25);
			l_von.setBounds(50, y + 30, 110, 25);
			l_bis.setBounds(50, y + 60, 110, 25);
			l_sportart.setBounds(50, y + 90, 110, 25);
			l_Ort.setBounds(50 + 30, y + 120, 110, 25);
			l_o_Platz.setBounds(50 + 30, y + 150, 110, 25);
			l_o_von.setBounds(50 + 30, y + 180, 110, 25);
			l_o_bis.setBounds(50 + 30, y + 210, 110, 25);
			l_o_minS.setBounds(50 + 30, y + 240, 110, 25);
			l_o_maxS.setBounds(50 + 30, y + 270, 110, 25);
			l_o_ga.setBounds(50 + 30, y + 300, 110, 25);
			l_o_Preis.setBounds(50 + 30, y + 330, 110, 25);
			l_spielerName.setBounds(50, y + 360, 110, 25);
			l_spielerNummer.setBounds(50, y + 390, 110, 25);
			l_blacklistName.setBounds(50, y + 420, 110, 25);
			l_blacklsitNummer.setBounds(50, y + 450, 110, 25);
			l_adminName.setBounds(50, y + 480, 110, 25);
			l_adminNummer.setBounds(50, y + 510, 110, 25);

			// die Elemente werden dem Frame hinzugefügt
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
			this.getContentPane().add(t_spielerName1);
			this.getContentPane().add(t_spielerNummer1);
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

			JLabel Name = new JLabel();

			final JButton btn_zurueck = new JButton("zurück");
			btn_zurueck.setBounds(300, 633, 100, 25);
			this.getContentPane().add(btn_zurueck);

			final JButton btn_publish_event = new JButton("Publish Event");
			final JButton btn_publish_ort = new JButton("Publish Ort");
			final JButton btn_published = new JButton("Publish!");

			btn_published.setBounds(50, 600, 310, 25);
			btn_publish_event.setBounds(50, 20, 150, 25);
			btn_publish_ort.setBounds(210, 20, 150, 25);

			this.getContentPane().add(btn_publish_event);
			this.getContentPane().add(btn_publish_ort);
			this.getContentPane().add(btn_published);

			// gewisse Elemente werden unsichtbar gemacht, da sie noch nicht von
			// Bedeutung sind für den User

			t_nodeName.setVisible(false);
			t_von.setVisible(false);
			t_bis.setVisible(false);
			t_sportart.setVisible(false);
			t_Ort.setVisible(false);
			t_o_Platz.setVisible(false);
			t_o_von.setVisible(false);
			t_o_bis.setVisible(false);
			t_o_minS.setVisible(false);
			t_o_maxS.setVisible(false);
			t_o_ga.setVisible(false);
			t_o_Preis.setVisible(false);
			t_spielerName1.setVisible(false);
			t_spielerNummer1.setVisible(false);
			t_blacklistName.setVisible(false);
			t_blacklsitNummer.setVisible(false);
			t_adminName.setVisible(false);
			t_adminNummer.setVisible(false);

			l_nodeName.setVisible(false);
			l_von.setVisible(false);
			l_bis.setVisible(false);
			l_sportart.setVisible(false);
			l_Ort.setVisible(false);
			l_o_Platz.setVisible(false);
			l_o_von.setVisible(false);
			l_o_bis.setVisible(false);
			l_o_minS.setVisible(false);
			l_o_maxS.setVisible(false);
			l_o_ga.setVisible(false);
			l_o_Preis.setVisible(false);
			l_spielerName.setVisible(false);
			l_spielerNummer.setVisible(false);
			l_blacklistName.setVisible(false);
			l_blacklsitNummer.setVisible(false);
			l_adminName.setVisible(false);
			l_adminNummer.setVisible(false);
			btn_published.setVisible(false);

			ActionListener al = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (e.getSource() == btn_zurueck) {
						new LoggedFrame();
						setVisible(false);
					}

					if (e.getSource() == btn_publish_event) {
						// sämtliche Elemente die benötigt werden um ein Event
						// zu erstellen werden wieder eingeblendet
						t_nodeName.setVisible(true);
						t_von.setVisible(true);
						t_bis.setVisible(true);
						t_sportart.setVisible(true);
						t_Ort.setVisible(true);
						t_o_Platz.setVisible(true);
						t_o_von.setVisible(true);
						t_o_bis.setVisible(true);
						t_o_minS.setVisible(true);
						t_o_maxS.setVisible(true);
						t_o_ga.setVisible(true);
						t_o_Preis.setVisible(true);
						t_spielerName1.setVisible(true);
						t_spielerNummer1.setVisible(true);
						t_blacklistName.setVisible(true);
						t_blacklsitNummer.setVisible(true);
						t_adminName.setVisible(true);
						t_adminNummer.setVisible(true);

						l_nodeName.setVisible(true);
						l_von.setVisible(true);
						l_bis.setVisible(true);
						l_sportart.setVisible(true);
						l_Ort.setVisible(true);
						l_o_Platz.setVisible(true);
						l_o_von.setVisible(true);
						l_o_bis.setVisible(true);
						l_o_minS.setVisible(true);
						l_o_maxS.setVisible(true);
						l_o_ga.setVisible(true);
						l_o_Preis.setVisible(true);
						l_spielerName.setVisible(true);
						l_spielerNummer.setVisible(true);
						l_blacklistName.setVisible(true);
						l_blacklsitNummer.setVisible(true);
						l_adminName.setVisible(true);
						l_adminNummer.setVisible(true);

						btn_published.setVisible(true);

					}
					if (e.getSource() == btn_publish_ort) {
						// sämtliche Elemente die benötigt werden um einen Ort
						// zu erstellen werden wieder eingeblendet
						// und überflüssige Element ausgeblendet

						t_nodeName.setVisible(true);
						t_von.setVisible(false);
						t_bis.setVisible(false);
						t_sportart.setVisible(false);
						t_Ort.setVisible(true);
						t_o_Platz.setVisible(true);
						t_o_von.setVisible(true);
						t_o_bis.setVisible(true);
						t_o_minS.setVisible(true);
						t_o_maxS.setVisible(true);
						t_o_ga.setVisible(true);
						t_o_Preis.setVisible(true);
						t_spielerName1.setVisible(false);
						t_spielerNummer1.setVisible(false);
						t_blacklistName.setVisible(false);
						t_blacklsitNummer.setVisible(false);
						t_adminName.setVisible(false);
						t_adminNummer.setVisible(false);

						l_nodeName.setVisible(true);
						l_von.setVisible(false);
						l_bis.setVisible(false);
						l_sportart.setVisible(false);
						l_Ort.setVisible(true);
						l_o_Platz.setVisible(true);
						l_o_von.setVisible(true);
						l_o_bis.setVisible(true);
						l_o_minS.setVisible(true);
						l_o_maxS.setVisible(true);
						l_o_ga.setVisible(true);
						l_o_Preis.setVisible(true);
						l_spielerName.setVisible(false);
						l_spielerNummer.setVisible(false);
						l_blacklistName.setVisible(false);
						l_blacklsitNummer.setVisible(false);
						l_adminName.setVisible(false);
						l_adminNummer.setVisible(false);

						btn_published.setVisible(true);

						ortp = true;
					}
					if (e.getSource() == btn_published) {
						// testweiße ist die oID "3", da die NextID Funktion der
						// Orteressource noch einen Fehler aufweißt
						String oID = "3";
						String[] Spieler = new String[2];
						String[] tSpieler = new String[2];
						Spieler[0] = t_spielerName1.getText();
						tSpieler[0] = t_spielerNummer1.getText();

						if (ortp == true) {
							// die notwendigen Informationen werden aus dem
							// zugehörigen Textfeld entnommen
							try {
								con.publishOrt(t_nodeName.getText(),
										t_Ort.getText(), t_o_Platz.getText(),
										t_o_von.getText(), t_o_bis.getText(),
										t_o_minS.getText(), t_o_maxS.getText(),
										t_o_ga.getText(), t_o_Preis.getText());
							} catch (Exception e1) {

								e1.printStackTrace();
							}
						} else {
							// die notwendigen Informationen werden aus dem
							// zugehörigen Textfeld entnommen
							try {
								con.publishEvent(t_nodeName.getText(),
										t_Ort.getText(), oID,
										t_o_Platz.getText(), t_von.getText(),
										t_bis.getText(), t_sportart.getText(),
										t_o_minS.getText(), t_o_maxS.getText(),
										t_o_von.getText(), t_o_bis.getText(),
										t_o_ga.getText(), t_o_Preis.getText(),
										Spieler, tSpieler,
										t_adminName.getText(),
										t_adminNummer.getText(), Spieler,
										tSpieler);
							} catch (Exception e1) {

								e1.printStackTrace();
							}
						}

						System.out.println("Publish accomplished");

					}
				}
			};
			// den Knöpfen wird ein ActionListener zugeteilt
			btn_publish_event.addActionListener(al);
			btn_publish_ort.addActionListener(al);
			btn_zurueck.addActionListener(al);
			btn_published.addActionListener(al);

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

	}

	public class SubscribeFrame extends JFrame implements ActionListener

	{
		public SubscribeFrame() {
			this.getContentPane().setLayout(null);

			JTextArea t_Inhalt = new JTextArea("Inhalt");
			t_Inhalt.setBounds(20, 20, 360, 530);
			this.getContentPane().add(t_Inhalt);

			final JButton btn_subscribe_to;
			btn_subscribe_to = new JButton("Subscribe!");
			btn_subscribe_to.setBounds(20, 600, 360, 25);

			JLabel l_nodeName = new JLabel("Nodename:");
			l_nodeName.setBounds(50, 570, 150, 25);
			this.getContentPane().add(l_nodeName);

			final JTextField t_nodeName = new JTextField("");
			t_nodeName.setBounds(210, 570, 150, 25);
			this.getContentPane().add(t_nodeName);

			this.getContentPane().add(btn_subscribe_to);

			final JButton btn_zurueck = new JButton("zurück");
			btn_zurueck.setBounds(300, 640, 100, 25);
			this.getContentPane().add(btn_zurueck);

			this.setVisible(true);
			this.setBounds(10, 10, 420, 720);

			// sobald das Fenster erzeugt wird, werden alle Nodes angezeigt
			List<String> liste = con.getSubscribedNodes();
			String listString = "";

			for (String s : liste) {
				listString += s + "\t";
			}
			t_Inhalt.setText(listString);

			ActionListener al = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (e.getSource() == btn_zurueck) {
						new LoggedFrame();
						setVisible(false);
					}
					if (e.getSource() == btn_subscribe_to) {
						// nach Eingabe des Nodenamen wird dieser subscribed
						con.subscribeNode(t_nodeName.getText());
					}

				}

			};

			btn_zurueck.addActionListener(al);
			btn_subscribe_to.addActionListener(al);

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

	public class UnsubscribeFrame extends JFrame implements ActionListener

	{
		public UnsubscribeFrame() {
			this.getContentPane().setLayout(null);

			final JTextArea t_Inhalt = new JTextArea("Inhalt");
			t_Inhalt.setBounds(20, 20, 360, 530);
			this.getContentPane().add(t_Inhalt);

			final JButton btn_unsubscribe_to = new JButton("Unsubscribe!");
			btn_unsubscribe_to.setBounds(20, 600, 360, 25);

			JLabel l_nodeName = new JLabel("Nodename:");
			l_nodeName.setBounds(50, 570, 150, 25);
			this.getContentPane().add(l_nodeName);

			final JTextField t_nodeName = new JTextField("");
			t_nodeName.setBounds(210, 570, 150, 25);
			this.getContentPane().add(t_nodeName);

			this.getContentPane().add(btn_unsubscribe_to);

			final JButton btn_zurueck = new JButton("zurück");
			btn_zurueck.setBounds(300, 640, 100, 25);
			this.getContentPane().add(btn_zurueck);

			this.setVisible(true);
			this.setBounds(10, 10, 420, 720);

			// sobald das Fenster geladen wird, werden alle subscribed Nodes
			// angezeigt
			List<String> liste = con.getSubscribedNodes();
			String listString = "";

			for (String s : liste) {
				listString += s + "\t";
			}
			t_Inhalt.setText(listString);

			ActionListener al = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (e.getSource() == btn_zurueck) {
						new LoggedFrame();
						setVisible(false);
					}
					if (e.getSource() == btn_unsubscribe_to) {
						// Aus den angezeigten Nodes, gibt man den gewünschten
						// Nodenamen in das Textfeld ein, damit man ihn
						// ansubscribed
						con.unsubscribeNode(t_nodeName.getText(), null);
					}
				}

			};

			btn_zurueck.addActionListener(al);
			btn_unsubscribe_to.addActionListener(al);

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

	public class VeraendernFrame extends JFrame

	{
		public VeraendernFrame() {
			this.getContentPane().setLayout(null);

			final JTextField t_nodeName = new JTextField("");
			final JTextField t_von = new JTextField("");
			final JTextField t_bis = new JTextField("");
			final JTextField t_sportart = new JTextField("");
			final JTextField t_Ort = new JTextField("");
			final JTextField t_o_Platz = new JTextField("");
			final JTextField t_o_von = new JTextField("");
			final JTextField t_o_bis = new JTextField("");
			final JTextField t_o_minS = new JTextField("");
			final JTextField t_o_maxS = new JTextField("");
			final JTextField t_o_ga = new JTextField("");
			final JTextField t_o_Preis = new JTextField("");
			final JTextField t_spielerName1 = new JTextField("");
			final JTextField t_spielerNummer1 = new JTextField("");
			final JTextField t_blacklistName = new JTextField("");
			final JTextField t_blacklsitNummer = new JTextField("");
			final JTextField t_adminName = new JTextField("");
			final JTextField t_adminNummer = new JTextField("");

			final JLabel l_nodeName = new JLabel("Nodename:");
			final JLabel l_von = new JLabel("Von:");
			final JLabel l_bis = new JLabel("Bis:");
			final JLabel l_sportart = new JLabel("Sportart:");
			final JLabel l_Ort = new JLabel("Ort:");
			final JLabel l_o_Platz = new JLabel("Platz:");
			final JLabel l_o_von = new JLabel("Geöffnet von:");
			final JLabel l_o_bis = new JLabel("Geöffnet bis:");
			final JLabel l_o_minS = new JLabel("Min. Spielerzahl:");
			final JLabel l_o_maxS = new JLabel("Max. Spielerzahl:");
			final JLabel l_o_ga = new JLabel("Geschlossen am:");
			final JLabel l_o_Preis = new JLabel("Preis:");
			final JLabel l_spielerName = new JLabel("Spielernamen:");
			final JLabel l_spielerNummer = new JLabel("Spielernummern:");
			final JLabel l_blacklistName = new JLabel("Blacklistnamen:");
			final JLabel l_blacklsitNummer = new JLabel("Blacklistnummern:");
			final JLabel l_adminName = new JLabel("Adminname:");
			final JLabel l_adminNummer = new JLabel("Adminnummer:");
			final JTextField t_id = new JTextField("ID");

			int x = 210, y = 60;
			t_nodeName.setBounds(210, y, 110, 25);
			t_von.setBounds(210, y + 30, 110, 25);
			t_bis.setBounds(210, y + 60, 110, 25);
			t_sportart.setBounds(210, y + 90, 110, 25);
			t_Ort.setBounds(210 + 30, y + 120, 110, 25);
			t_o_Platz.setBounds(210 + 30, y + 150, 110, 25);
			t_o_von.setBounds(210 + 30, y + 180, 110, 25);
			t_o_bis.setBounds(210 + 30, y + 210, 110, 25);
			t_o_minS.setBounds(210 + 30, y + 240, 110, 25);
			t_o_maxS.setBounds(210 + 30, y + 270, 110, 25);
			t_o_ga.setBounds(210 + 30, y + 300, 110, 25);
			t_o_Preis.setBounds(210 + 30, y + 330, 110, 25);
			t_spielerName1.setBounds(210, y + 360, 110, 25);
			t_spielerNummer1.setBounds(210, y + 390, 110, 25);
			t_blacklistName.setBounds(210, y + 420, 110, 25);
			t_blacklsitNummer.setBounds(210, y + 450, 110, 25);
			t_adminName.setBounds(210, y + 480, 110, 25);
			t_adminNummer.setBounds(210, y + 510, 110, 25);

			l_nodeName.setBounds(50, y, 110, 25);
			l_von.setBounds(50, y + 30, 110, 25);
			l_bis.setBounds(50, y + 60, 110, 25);
			l_sportart.setBounds(50, y + 90, 110, 25);
			l_Ort.setBounds(50 + 30, y + 120, 110, 25);
			l_o_Platz.setBounds(50 + 30, y + 150, 110, 25);
			l_o_von.setBounds(50 + 30, y + 180, 110, 25);
			l_o_bis.setBounds(50 + 30, y + 210, 110, 25);
			l_o_minS.setBounds(50 + 30, y + 240, 110, 25);
			l_o_maxS.setBounds(50 + 30, y + 270, 110, 25);
			l_o_ga.setBounds(50 + 30, y + 300, 110, 25);
			l_o_Preis.setBounds(50 + 30, y + 330, 110, 25);
			l_spielerName.setBounds(50, y + 360, 110, 25);
			l_spielerNummer.setBounds(50, y + 390, 110, 25);
			l_blacklistName.setBounds(50, y + 420, 110, 25);
			l_blacklsitNummer.setBounds(50, y + 450, 110, 25);
			l_adminName.setBounds(50, y + 480, 110, 25);
			l_adminNummer.setBounds(50, y + 510, 110, 25);

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
			this.getContentPane().add(t_spielerName1);
			this.getContentPane().add(t_spielerNummer1);
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

			JLabel Name = new JLabel();

			final JButton btn_zurueck = new JButton("zurück");
			btn_zurueck.setBounds(300, 633, 100, 25);
			this.getContentPane().add(btn_zurueck);

			final JButton btn_publish_event = new JButton("Event bearbeiten");
			final JButton btn_publish_ort = new JButton("Ort bearbeiten");
			final JButton btn_veraendern = new JButton("Verändern!");

			btn_veraendern.setBounds(50, 600, 310, 25);
			btn_publish_event.setBounds(50, 20, 75, 25);
			btn_publish_ort.setBounds(300, 20, 75, 25);
			t_id.setBounds(175, 20, 80, 27);

			this.getContentPane().add(btn_publish_event);
			this.getContentPane().add(btn_publish_ort);
			this.getContentPane().add(btn_veraendern);
			this.getContentPane().add(t_id);

			// erstmal unwichtige Informationen werden ausgeblendet
			t_nodeName.setVisible(false);
			t_von.setVisible(false);
			t_bis.setVisible(false);
			t_sportart.setVisible(false);
			t_Ort.setVisible(false);
			t_o_Platz.setVisible(false);
			t_o_von.setVisible(false);
			t_o_bis.setVisible(false);
			t_o_minS.setVisible(false);
			t_o_maxS.setVisible(false);
			t_o_ga.setVisible(false);
			t_o_Preis.setVisible(false);
			t_spielerName1.setVisible(false);
			t_spielerNummer1.setVisible(false);
			t_blacklistName.setVisible(false);
			t_blacklsitNummer.setVisible(false);
			t_adminName.setVisible(false);
			t_adminNummer.setVisible(false);

			l_nodeName.setVisible(false);
			l_von.setVisible(false);
			l_bis.setVisible(false);
			l_sportart.setVisible(false);
			l_Ort.setVisible(false);
			l_o_Platz.setVisible(false);
			l_o_von.setVisible(false);
			l_o_bis.setVisible(false);
			l_o_minS.setVisible(false);
			l_o_maxS.setVisible(false);
			l_o_ga.setVisible(false);
			l_o_Preis.setVisible(false);
			l_spielerName.setVisible(false);
			l_spielerNummer.setVisible(false);
			l_blacklistName.setVisible(false);
			l_blacklsitNummer.setVisible(false);
			l_adminName.setVisible(false);
			l_adminNummer.setVisible(false);
			btn_veraendern.setVisible(false);

			ActionListener al = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (e.getSource() == btn_zurueck) {
						new LoggedFrame();
						setVisible(false);
					}

					if (e.getSource() == btn_publish_event) {
						// notwendige Elemente werden eingeblendet
						t_nodeName.setVisible(true);
						t_von.setVisible(true);
						t_bis.setVisible(true);
						t_sportart.setVisible(true);
						t_Ort.setVisible(true);
						t_o_Platz.setVisible(true);
						t_o_von.setVisible(true);
						t_o_bis.setVisible(true);
						t_o_minS.setVisible(true);
						t_o_maxS.setVisible(true);
						t_o_ga.setVisible(true);
						t_o_Preis.setVisible(true);
						t_spielerName1.setVisible(true);
						t_spielerNummer1.setVisible(true);
						t_blacklistName.setVisible(true);
						t_blacklsitNummer.setVisible(true);
						t_adminName.setVisible(true);
						t_adminNummer.setVisible(true);

						l_nodeName.setVisible(true);
						l_von.setVisible(true);
						l_bis.setVisible(true);
						l_sportart.setVisible(true);
						l_Ort.setVisible(true);
						l_o_Platz.setVisible(true);
						l_o_von.setVisible(true);
						l_o_bis.setVisible(true);
						l_o_minS.setVisible(true);
						l_o_maxS.setVisible(true);
						l_o_ga.setVisible(true);
						l_o_Preis.setVisible(true);
						l_spielerName.setVisible(true);
						l_spielerNummer.setVisible(true);
						l_blacklistName.setVisible(true);
						l_blacklsitNummer.setVisible(true);
						l_adminName.setVisible(true);
						l_adminNummer.setVisible(true);

						btn_veraendern.setVisible(true);

					}
					if (e.getSource() == btn_publish_ort) {
						// notwendige Elemente werden eingeblendet
						// nicht notwendige ausgeblendet
						t_nodeName.setVisible(true);
						t_von.setVisible(false);
						t_bis.setVisible(false);
						t_sportart.setVisible(false);
						t_Ort.setVisible(true);
						t_o_Platz.setVisible(true);
						t_o_von.setVisible(true);
						t_o_bis.setVisible(true);
						t_o_minS.setVisible(true);
						t_o_maxS.setVisible(true);
						t_o_ga.setVisible(true);
						t_o_Preis.setVisible(true);
						t_spielerName1.setVisible(false);
						t_spielerNummer1.setVisible(false);
						t_blacklistName.setVisible(false);
						t_blacklsitNummer.setVisible(false);
						t_adminName.setVisible(false);
						t_adminNummer.setVisible(false);

						l_nodeName.setVisible(true);
						l_von.setVisible(false);
						l_bis.setVisible(false);
						l_sportart.setVisible(false);
						l_Ort.setVisible(true);
						l_o_Platz.setVisible(true);
						l_o_von.setVisible(true);
						l_o_bis.setVisible(true);
						l_o_minS.setVisible(true);
						l_o_maxS.setVisible(true);
						l_o_ga.setVisible(true);
						l_o_Preis.setVisible(true);
						l_spielerName.setVisible(false);
						l_spielerNummer.setVisible(false);
						l_blacklistName.setVisible(false);
						l_blacklsitNummer.setVisible(false);
						l_adminName.setVisible(false);
						l_adminNummer.setVisible(false);

						btn_veraendern.setVisible(true);

						ortp = true;
					}
					if (e.getSource() == btn_veraendern) {
						// testweiße ist die oID "3", da die NextID Funktion der
						// Orteressource noch einen Fehler aufweißt
						String oID = "3";
						String[] Spieler = new String[2];
						String[] tSpieler = new String[2];
						Spieler[0] = t_spielerName1.getText();
						tSpieler[0] = t_spielerNummer1.getText();

						if (ortp == true) {
							try {
								// die notwendigen Informationen werden aus den
								// Textfeldern gelesen
								con.putOrt(t_nodeName.getText(),
										t_Ort.getText(), t_o_Platz.getText(),
										t_o_von.getText(), t_o_bis.getText(),
										t_o_minS.getText(), t_o_maxS.getText(),
										t_o_ga.getText(), t_o_Preis.getText(),
										t_id.getText());
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							try {
								// die notwendigen Informationen werden aus den
								// Textfeldern gelesen
								con.putEvent(t_nodeName.getText(),
										t_Ort.getText(), oID,
										t_o_Platz.getText(), t_von.getText(),
										t_bis.getText(), t_sportart.getText(),
										t_o_minS.getText(), t_o_maxS.getText(),
										t_o_von.getText(), t_o_bis.getText(),
										t_o_ga.getText(), t_o_Preis.getText(),
										Spieler, tSpieler,
										t_adminName.getText(),
										t_adminNummer.getText(), Spieler,
										tSpieler, t_id.getText());
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

						System.out.println("Verändern accomplished");

					}
				}
			};

			btn_publish_event.addActionListener(al);
			btn_publish_ort.addActionListener(al);
			btn_zurueck.addActionListener(al);
			btn_veraendern.addActionListener(al);

			this.setVisible(true);
			this.setBounds(10, 10, 420, 700);
			// der WindowListener macht es möglich, dass wenn man das Fenster
			// schließt sich auch das Programm schließt
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

	}

	public class LoggedFrame extends JFrame {

		public LoggedFrame() {
			this.getContentPane().setLayout(null);

			final JButton btn_publish = new JButton("Publish");
			final JButton btn_subscribe = new JButton("Subscribe");
			final JButton btn_unsubscribe = new JButton("Unsubscribe");
			final JButton btn_browse = new JButton("Browse");
			final JButton btn_veraendern = new JButton("Verändern");

			btn_publish.setBounds(20, 200, 360, 25);
			btn_browse.setBounds(20, 250, 360, 25);
			btn_subscribe.setBounds(20, 300, 360, 25);
			btn_unsubscribe.setBounds(20, 350, 360, 25);
			btn_veraendern.setBounds(20, 400, 360, 25);

			this.getContentPane().add(btn_publish);
			this.getContentPane().add(btn_browse);
			this.getContentPane().add(btn_subscribe);
			this.getContentPane().add(btn_unsubscribe);
			this.getContentPane().add(btn_veraendern);
			ActionListener al = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (e.getSource() == btn_browse) {
						new BrowseFrame();
						setVisible(false);
					}

					if (e.getSource() == btn_publish) {
						new PublishFrame();
						setVisible(false);
					}
					if (e.getSource() == btn_subscribe) {
						new SubscribeFrame();
						setVisible(false);
					}
					if (e.getSource() == btn_unsubscribe) {
						new UnsubscribeFrame();
						setVisible(false);
					}
					if (e.getSource() == btn_veraendern) {
						new VeraendernFrame();
						setVisible(false);
					}

				}
			};

			btn_browse.addActionListener(al);
			btn_subscribe.addActionListener(al);
			btn_publish.addActionListener(al);
			btn_veraendern.addActionListener(al);
			btn_unsubscribe.addActionListener(al);

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

	}
}
