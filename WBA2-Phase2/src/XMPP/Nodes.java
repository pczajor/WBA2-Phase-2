package XMPP;

import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.Chat;
import java.util.*;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.math.BigInteger;

import jaxb.Accountliste;
import jaxb.Events;
import jaxb.Orteliste;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.pubsub.PubSubManager;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class Nodes {

	private XMPPConnection connection;
	private PubSubManager mgr;
	private String userName;
	private String password;
	private String server;
	private int port;
	private Client restClient;
	private WebResource webResource;
	private String URL;

	public void login() throws XMPPException {
		ConnectionConfiguration config = new ConnectionConfiguration(server,
				port);
		connection = new XMPPConnection(config);

		connection.connect();
		this.setUsername("user1");
		this.setPassword("user");
		connection.login(userName, password);
		mgr = new PubSubManager(connection, "pubsub." + connection.getHost());
	}

	public void disconnect() {
		connection.disconnect();
	}

	private WebResource rest() {
		restClient = Client.create();
		webResource = restClient.resource(this.URL);
		webResource.type("application/xml");
		return webResource;
	}

	public String getAccount(String id) {
		String temp = "";

		restClient = Client.create();
		webResource = restClient.resource("http://" + getServer() + ":"
				+ getPort() + "/accounts");
		webResource.path(id);

		Accountliste account = webResource.type(MediaType.APPLICATION_XML).get(
				Accountliste.class);

		temp = "Telefonnummer: "
				+ account.getSpieler().get(0).getTelefonnummer() + "\n"
				+ "Anzeigename: "
				+ account.getSpieler().get(0).getAnzeigename() + "\n";
		return temp;

	}

	public String getOrt(BigInteger id) {
		String temp = "";

		restClient = Client.create();
		webResource = restClient.resource("http://" + getServer() + ":"
				+ getPort() + "/orte");
		webResource.path(String.valueOf(id));

		Orteliste ort = webResource.type(MediaType.APPLICATION_XML).get(
				Orteliste.class);

		temp = "ID: " + ort.getOrt().get(0).getOrtID() + "\n" 
				+ "Ort: " + ort.getOrt().get(0).getOrt() + "\n" 
				+ "Platz: " + ort.getOrt().get(0).getPlatz() + "\n"
				+ "Min. Spieler: " + ort.getOrt().get(0).getMinSpieleranzahl() + "\n"
				+ "Max. Spieler:" + ort.getOrt().get(0).getMaxSpieleranzahl() + "\n"
				+ "Geöffnet von: " + ort.getOrt().get(0).getOeffnungszeiten().getGeoeffnetVon() + "\n"
				+ "Geöffnet bis: " + ort.getOrt().get(0).getOeffnungszeiten().getGeoeffnetBis() + "\n"
				+ "Geschlossen an: " + ort.getOrt().get(0).getOeffnungszeiten().getGeschlossenAn() + "\n"
				+ "Preis: " + ort.getOrt().get(0).getPreis() + "\n";
		return temp;
	}

	public String getEvent(BigInteger id) {
		String temp = "";

		restClient = Client.create();
		webResource = restClient.resource("http://" + getServer() + ":"
				+ getPort() + "/events");
		webResource.path(String.valueOf(id));

		Events event = webResource.type(MediaType.APPLICATION_XML).get(
				Events.class);
		
		//event.getEvent().get(0).getSpielerliste() 
		
		//or(each:  ){
			
	

		temp = "ID: " + event.getEvent().get(0).getEventID() + "\n" +
				"ID: " + event.getEvent().get(0).getSpielzeitraum().getVon() + "\n" +
				"ID: " + event.getEvent().get(0).getSpielzeitraum().getBis() + "\n" +
				"ID: " + event.getEvent().get(0).getSportart() + "\n" +
				"Örtlichkeit"+ getOrt(event.getEvent().get(0).getOertlichkeit().getOrtID()) + "\n" +
				"Spielerlist" + "\n" +
				"ID: " + event.getEvent().get(0).getAdmin() + "\n" +
				"Blacklist";
		
				
		return temp;
	}
	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	/*
	 * public static void main(String args[]) throws XMPPException, IOException
	 * { // declare variables Nodes c = new Nodes(); BufferedReader br = new
	 * BufferedReader(new InputStreamReader(System.in)); //String msg;
	 * 
	 * // turn on the enhanced debugger XMPPConnection.DEBUG_ENABLED = true;
	 * 
	 * // Enter your login information here
	 * 
	 * c.login();
	 * 
	 * // c.displayBuddyList();
	 * 
	 * System.out.println("-----");
	 * 
	 * System.out
	 * .println("Who do you want to talk to? - Type contacts full email address:"
	 * ); String talkTo = br.readLine();
	 * 
	 * System.out.println("-----");
	 * System.out.println("All messages will be sent to " + talkTo);
	 * System.out.println("Enter your message in the console:");
	 * System.out.println("-----\n");
	 * 
	 * 
	 * while (!(msg = br.readLine()).equals("bye")) { c.sendMessage(msg,
	 * talkTo); }
	 * 
	 * 
	 * c.disconnect(); System.exit(0); }
	 */
	/*
	 * public void sendMessage(String message, String to) throws XMPPException {
	 * Chat chat = connection.getChatManager().createChat(to, this);
	 * chat.sendMessage(message); }
	 * 
	 * public void processMessage(Chat chat, Message message) { if
	 * (message.getType() == Message.Type.chat)
	 * System.out.println(chat.getParticipant() + " says: " +
	 * message.getBody()); }
	 * 
	 * public void displayBuddyList() { Roster roster = connection.getRoster();
	 * Collection<RosterEntry> entries = roster.getEntries();
	 * 
	 * System.out.println("\n\n" + entries.size() + " buddy(ies):");
	 * for(RosterEntry r:entries) { System.out.println(r.getUser()); } }
	 */

}