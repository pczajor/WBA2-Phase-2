package XMPP;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import jaxb.Accountliste;
import jaxb.Events;
import jaxb.Orteliste;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.smackx.pubsub.AccessModel;
import org.jivesoftware.smackx.pubsub.ConfigureForm;
import org.jivesoftware.smackx.pubsub.FormType;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.NodeType;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.PubSubManager;
import org.jivesoftware.smackx.pubsub.PublishModel;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.jivesoftware.smackx.pubsub.Subscription;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Nodes {
	public XMPPConnection connection;
	public PubSubManager mgr;
	private String username;
	public String password;
	public AccountManager am;

	private Client restClient;
	private WebResource webResource;
	private String URL;
	public static ItemEventListener<Item> listener;

	// Verbinden mit dem Server
	// Hostname und Port in Config.java
	public void connect() throws Exception {
		ConnectionConfiguration config = new ConnectionConfiguration(
				Config.server, Config.port);
		connection = new XMPPConnection(config);
		new PubSubManager(connection, "pubsub." + connection.getHost());
		connection.connect();

	}

	// Event veröffentlichen
	// Parameter werden in der GUI eingegeben
	// Spieler werden einzeln eingetragen und in einem Array gespeichert
	public boolean publishEvent(String nodeName, String Ort, String oID,
			String Platz, String von, String bis, String sportart, String minS,
			String maxS, String gV, String gB, String ga, String preis,
			String[] Spieler, String[] tNr, String aSpieler, String atNr,
			String[] bSpieler, String[] btNr) throws Exception {
		// ID generieren
		BigInteger id = BigInteger
				.valueOf(resources.eventsresource.getNextId());

		// Array für die Spielerliste anlegen
		String spielerliste = "<Spielerliste>";

		// Spieler in das Array eintragen
		for (int i = 0; i < Spieler.length; i++) {
			spielerliste += "<Spieler>" + "<Anzeigename>" + Spieler[i]
					+ "</Anzeigename>" + "<Telefonnummer>" + tNr[i]
					+ "</Telefonnummer>" + "</Spieler>";
		}
		spielerliste += "</Spielerliste>";

		// Siehe Spielerliste
		String blacklist = "<Backlist>";

		for (int i = 0; i < Spieler.length; i++) {
			blacklist += "<Spieler>" + "<Anzeigename>" + bSpieler[i]
					+ "</Anzeigename>" + "<Telefonnummer>" + btNr[i]
					+ "</Telefonnummer>" + "</Spieler>";
		}
		blacklist += "</Backlist>";

		// StringBuilder um einen String zubauen
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<events>");
		stringBuilder.append("<event>");
		stringBuilder.append("<Event-ID>");
		stringBuilder.append(id);
		stringBuilder.append("</Event-ID>");
		stringBuilder.append("<Spielzeitraum>");
		stringBuilder.append("<von>");
		stringBuilder.append(von);
		stringBuilder.append("</von>");
		stringBuilder.append("<bis>");
		stringBuilder.append(bis);
		stringBuilder.append("</bis>");
		stringBuilder.append("</Spielzeitraum>");
		stringBuilder.append("<Sportart>");
		stringBuilder.append(sportart);
		stringBuilder.append("</Sportart>");
		stringBuilder.append("<Oertlichkeit>");
		stringBuilder.append("<Ort-ID>");
		stringBuilder.append(oID);
		stringBuilder.append("</Ort-ID>");
		stringBuilder.append("<Ort>");
		stringBuilder.append(Ort);
		stringBuilder.append("</Ort>");
		stringBuilder.append("<Platz>");
		stringBuilder.append(Platz);
		stringBuilder.append("</Platz>");
		stringBuilder.append("<min.Spieleranzahl>");
		stringBuilder.append(minS);
		stringBuilder.append("</min.Spieleranzahl>");
		stringBuilder.append("<max.Spieleranzahl>");
		stringBuilder.append(maxS);
		stringBuilder.append("</max.Spieleranzahl>");
		stringBuilder.append("<Oeffnungszeiten>");
		stringBuilder.append("<geoeffnet_von>");
		stringBuilder.append(gV);
		stringBuilder.append("</geoeffnet_von>");
		stringBuilder.append("<geoeffnet_bis>");
		stringBuilder.append(gB);
		stringBuilder.append("</geoeffnet_bis>");
		stringBuilder.append("<geschlossen_an>");
		stringBuilder.append(ga);
		stringBuilder.append("</geschlossen_an>");
		stringBuilder.append("</Oeffnungszeiten>");
		stringBuilder.append("<Preis>");
		stringBuilder.append(preis);
		stringBuilder.append("</Preis>");
		stringBuilder.append("</Oertlichkeit>");
		stringBuilder.append(spielerliste);
		stringBuilder.append(blacklist);
		stringBuilder.append("<Admin>");
		stringBuilder.append("<Anzeigename>");
		stringBuilder.append(aSpieler);
		stringBuilder.append("</Anzeigename>");
		stringBuilder.append("<Telefonnummer>");
		stringBuilder.append(atNr);
		stringBuilder.append("</Telefonnummer>");
		stringBuilder.append("</Admin>");
		stringBuilder.append("</event>");
		stringBuilder.append("</events>");

		// stringBuilder in String
		// rEvent = String für REST
		String rEvent = stringBuilder.toString();

		// xEvent = String für XMPP
		String xEvent = "<events>" + "<event>" + "<Event-ID>" + id
				+ "</Event-ID>" + "<Spielzeitraum>" + "<von>" + von + "</von>"
				+ "<bis>" + bis + "</bis>" + "</Spielzeitraum>" + "<Sportart>"
				+ sportart + "</Sportart>" + "<Oertlichkeit>" + "<Ort>" + Ort
				+ "</Ort>" + "<Platz>" + Platz + "</Platz>" + "<Preis>" + preis
				+ "</Preis>" + "</Oertlichkeit>" + spielerliste + "<Admin>"
				+ "<Anzeigename>" + aSpieler + "</Anzeigename>"
				+ "<Telefonnummer>" + atNr + "</Telefonnummer>" + "</Admin>"
				+ "</event>" + "</events>";

		// REST Client und POST
		try {
			restClient = Client.create();
			webResource = restClient.resource("http://" + getServer() + ":"
					+ getPort() + "/events");
			ClientResponse response = webResource.type(
					MediaType.APPLICATION_XML).post(ClientResponse.class,
					rEvent);

			// 204 NO CONTENT
			if (response.getStatus() == 204) {

				LeafNode node = null;

				try {

					// Payload
					node = mgr.getNode(nodeName);
					SimplePayload sp = new SimplePayload(nodeName, "", xEvent);
					PayloadItem<SimplePayload> pi = new PayloadItem<SimplePayload>(
							id.toString(), sp);

					node.publish(pi);
					return true;

				} catch (XMPPException e) {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// Event bearbeiten
	public boolean putEvent(String nodeName, String Ort, String oID,
			String Platz, String von, String bis, String sportart, String minS,
			String maxS, String gV, String gB, String ga, String preis,
			String[] Spieler, String[] tNr, String aSpieler, String atNr,
			String[] bSpieler, String[] btNr, String id) throws Exception {
		// ID generieren

		// Array für die Spielerliste anlegen
		String spielerliste = "<Spielerliste>";

		// Spieler in das Array eintragen
		for (int i = 0; i < Spieler.length; i++) {
			spielerliste += "<Spieler>" + "<Anzeigename>" + Spieler[i]
					+ "</Anzeigename>" + "<Telefonnummer>" + tNr[i]
					+ "</Telefonnummer>" + "</Spieler>";
		}
		spielerliste += "</Spielerliste>";

		// Siehe Spielerliste
		String blacklist = "<Backlist>";

		for (int i = 0; i < Spieler.length; i++) {
			blacklist += "<Spieler>" + "<Anzeigename>" + bSpieler[i]
					+ "</Anzeigename>" + "<Telefonnummer>" + btNr[i]
					+ "</Telefonnummer>" + "</Spieler>";
		}
		blacklist += "</Backlist>";

		// StringBuilder um einen String zubauen
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<events>");
		stringBuilder.append("<event>");
		stringBuilder.append("<Event-ID>");
		stringBuilder.append(id);
		stringBuilder.append("</Event-ID>");
		stringBuilder.append("<Spielzeitraum>");
		stringBuilder.append("<von>");
		stringBuilder.append(von);
		stringBuilder.append("</von>");
		stringBuilder.append("<bis>");
		stringBuilder.append(bis);
		stringBuilder.append("</bis>");
		stringBuilder.append("</Spielzeitraum>");
		stringBuilder.append("<Sportart>");
		stringBuilder.append(sportart);
		stringBuilder.append("</Sportart>");
		stringBuilder.append("<Oertlichkeit>");
		stringBuilder.append("<Ort-ID>");
		stringBuilder.append(oID);
		stringBuilder.append("</Ort-ID>");
		stringBuilder.append("<Ort>");
		stringBuilder.append(Ort);
		stringBuilder.append("</Ort>");
		stringBuilder.append("<Platz>");
		stringBuilder.append(Platz);
		stringBuilder.append("</Platz>");
		stringBuilder.append("<min.Spieleranzahl>");
		stringBuilder.append(minS);
		stringBuilder.append("</min.Spieleranzahl>");
		stringBuilder.append("<max.Spieleranzahl>");
		stringBuilder.append(maxS);
		stringBuilder.append("</max.Spieleranzahl>");
		stringBuilder.append("<Oeffnungszeiten>");
		stringBuilder.append("<geoeffnet_von>");
		stringBuilder.append(gV);
		stringBuilder.append("</geoeffnet_von>");
		stringBuilder.append("<geoeffnet_bis>");
		stringBuilder.append(gB);
		stringBuilder.append("</geoeffnet_bis>");
		stringBuilder.append("<geschlossen_an>");
		stringBuilder.append(ga);
		stringBuilder.append("</geschlossen_an>");
		stringBuilder.append("</Oeffnungszeiten>");
		stringBuilder.append("<Preis>");
		stringBuilder.append(preis);
		stringBuilder.append("</Preis>");
		stringBuilder.append("</Oertlichkeit>");
		stringBuilder.append(spielerliste);
		stringBuilder.append(blacklist);
		stringBuilder.append("<Admin>");
		stringBuilder.append("<Anzeigename>");
		stringBuilder.append(aSpieler);
		stringBuilder.append("</Anzeigename>");
		stringBuilder.append("<Telefonnummer>");
		stringBuilder.append(atNr);
		stringBuilder.append("</Telefonnummer>");
		stringBuilder.append("</Admin>");
		stringBuilder.append("</event>");
		stringBuilder.append("</events>");

		// stringBuilder in String
		// rEvent = String für REST
		String rEvent = stringBuilder.toString();

		// xEvent = String für XMPP
		String xEvent = "<events>" + "<event>" + "<Event-ID>" + id
				+ "</Event-ID>" + "<Spielzeitraum>" + "<von>" + von + "</von>"
				+ "<bis>" + bis + "</bis>" + "</Spielzeitraum>" + "<Sportart>"
				+ sportart + "</Sportart>" + "<Oertlichkeit>" + "<Ort>" + Ort
				+ "</Ort>" + "<Platz>" + Platz + "</Platz>" + "<Preis>" + preis
				+ "</Preis>" + "</Oertlichkeit>" + spielerliste + "<Admin>"
				+ "<Anzeigename>" + aSpieler + "</Anzeigename>"
				+ "<Telefonnummer>" + atNr + "</Telefonnummer>" + "</Admin>"
				+ "</event>" + "</events>";

		// REST Client und POST
		try {
			restClient = Client.create();
			webResource = restClient.resource("http://" + getServer() + ":"
					+ getPort() + "/events");
			ClientResponse response = webResource.type(
					MediaType.APPLICATION_XML)
					.put(ClientResponse.class, rEvent);

			// 204 NO CONTENT
			if (response.getStatus() == 204) {

				LeafNode node = null;

				try {

					// Payload
					node = mgr.getNode(nodeName);
					SimplePayload sp = new SimplePayload(nodeName, "", xEvent);
					PayloadItem<SimplePayload> pi = new PayloadItem<SimplePayload>(
							id.toString(), sp);

					node.publish(pi);
					return true;

				} catch (XMPPException e) {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// Ort veröffentlichen
	public boolean publishOrt(String nodeName, String Ort, String Platz,
			String von, String bis, String minS, String maxS, String ga,
			String Preis) throws Exception {

		// ID generieren
		BigInteger id = BigInteger.valueOf(resources.orteressource.getNextId());
		// createLeafNode(nodeName);

		// rOrte = String für REST
		String rOrte = "<Orte>" + "<Ort>" + "<Ort-ID>" + id + "</Ort-ID>"
				+ "<Ort>" + Ort + "</Ort>" + "<Platz>" + Platz + "</Platz>"
				+ "<min.Spieleranzahl>" + minS + "</min.Spieleranzahl>"
				+ "<max.Spieleranzahl>" + maxS + "</max.Spieleranzahl>"
				+ "<Oeffnungszeiten>" + "<geoeffnet_von>" + von
				+ "</geoeffnet_von>" + "<geoeffnet_bis>" + bis
				+ "</geoeffnet_bis>" + "<geschlossen_an>" + ga
				+ "</geschlossen_an>" + "</Oeffnungszeiten>" + "<Preis>"
				+ Preis + "</Preis>" + "</Ort>" + "</Orte>";

		// xOrte = String für XMPP
		String xOrte = "<Orte>" + "<Ort>" + "<Ort>" + Ort + "</Ort>"
				+ "<Platz>" + Platz + "</Platz>" + "<min.Spieleranzahl>" + minS
				+ "</min.Spieleranzahl>" + "<max.Spieleranzahl>" + maxS
				+ "</max.Spieleranzahl>" + "<Oeffnungszeiten>"
				+ "<geoeffnet_von>" + von + "</geoeffnet_von>"
				+ "<geoeffnet_bis>" + bis + "</geoeffnet_bis>"
				+ "<geschlossen_an>" + ga + "</geschlossen_an>"
				+ "</Oeffnungszeiten>" + "<Preis>" + Preis + "</Preis>"
				+ "</Ort>" + "</Orte>";

		// REST Client und POST
		try {
			restClient = Client.create();
			webResource = restClient.resource("http://" + getServer() + ":"
					+ getPort() + "/orte");
			ClientResponse response = webResource.type(
					MediaType.APPLICATION_XML)
					.post(ClientResponse.class, rOrte);

			// 204 NO CONTENT
			if (response.getStatus() == 204) {

				LeafNode node = null;

				try {
					node = mgr.getNode(nodeName);
					SimplePayload sp = new SimplePayload(nodeName, "", xOrte);
					// Payload
					PayloadItem<SimplePayload> pi = new PayloadItem<SimplePayload>(
							id.toString(), sp);

					node.publish(pi);
					return true;

				} catch (XMPPException e) {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// Ort bearbeiten

	public boolean putOrt(String nodeName, String Ort, String Platz,
			String von, String bis, String minS, String maxS, String ga,
			String Preis, String id) throws Exception {

		// ID generieren

		// createLeafNode(nodeName);

		// rOrte = String für REST
		String rOrte = "<Orte>" + "<Ort>" + "<Ort-ID>" + id + "</Ort-ID>"
				+ "<Ort>" + Ort + "</Ort>" + "<Platz>" + Platz + "</Platz>"
				+ "<min.Spieleranzahl>" + minS + "</min.Spieleranzahl>"
				+ "<max.Spieleranzahl>" + maxS + "</max.Spieleranzahl>"
				+ "<Oeffnungszeiten>" + "<geoeffnet_von>" + von
				+ "</geoeffnet_von>" + "<geoeffnet_bis>" + bis
				+ "</geoeffnet_bis>" + "<geschlossen_an>" + ga
				+ "</geschlossen_an>" + "</Oeffnungszeiten>" + "<Preis>"
				+ Preis + "</Preis>" + "</Ort>" + "</Orte>";

		// xOrte = String für XMPP
		String xOrte = "<Orte>" + "<Ort>" + "<Ort>" + Ort + "</Ort>"
				+ "<Platz>" + Platz + "</Platz>" + "<min.Spieleranzahl>" + minS
				+ "</min.Spieleranzahl>" + "<max.Spieleranzahl>" + maxS
				+ "</max.Spieleranzahl>" + "<Oeffnungszeiten>"
				+ "<geoeffnet_von>" + von + "</geoeffnet_von>"
				+ "<geoeffnet_bis>" + bis + "</geoeffnet_bis>"
				+ "<geschlossen_an>" + ga + "</geschlossen_an>"
				+ "</Oeffnungszeiten>" + "<Preis>" + Preis + "</Preis>"
				+ "</Ort>" + "</Orte>";

		// REST Client und POST
		try {
			restClient = Client.create();
			webResource = restClient.resource("http://" + getServer() + ":"
					+ getPort() + "/orte");
			ClientResponse response = webResource.type(
					MediaType.APPLICATION_XML).put(ClientResponse.class, rOrte);

			// 204 NO CONTENT
			if (response.getStatus() == 204) {

				LeafNode node = null;

				try {
					node = mgr.getNode(nodeName);
					SimplePayload sp = new SimplePayload(nodeName, "", xOrte);
					// Payload
					PayloadItem<SimplePayload> pi = new PayloadItem<SimplePayload>(
							id.toString(), sp);

					node.publish(pi);
					return true;

				} catch (XMPPException e) {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// Node abonnieren
	public boolean subscribeNode(String nodeName) {
		LeafNode node = null;
		try {
			node = mgr.getNode(nodeName);
			node.subscribe(username + "@" + getServer());
			node.addItemEventListener(listener);
			return true;
		} catch (XMPPException e) {
			return false;
		}

	}

	// Node abbestellen
	public boolean unsubscribeNode(String nodeName, ItemEventListener<Item> item) {
		LeafNode node = null;
		try {
			node = mgr.getNode(nodeName);
			if (item != null) {
				node.removeItemEventListener(item);
				node.unsubscribe(username + "@" + getServer());
			}
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	// Liste alles abonnierten Nodes
	public List<String> getSubscribedNodes() {
		List<String> entries = new ArrayList<String>();

		try {
			Iterator<Subscription> subscription = mgr.getSubscriptions()
					.iterator();

			while (subscription.hasNext()) {
				entries.add(subscription.next().getNode());
			}
		} catch (Exception e) {

		}
		return entries;
	}

	// Liste aller Nodes
	public List<String> getAllNodes() {

		List<String> entries = new ArrayList<String>();

		try {
			DiscoverItems items = mgr.discoverNodes(null);
			Iterator<DiscoverItems.Item> it = items.getItems();

			while (it.hasNext()) {
				entries.add(it.next().getNode());
			}

		} catch (XMPPException e) {
			e.printStackTrace();
		}

		return entries;
	}

	// Leafnode erstellen
	public boolean createLeafNode(String leafnodeName) {

		LeafNode leafNode = null;
		// Config
		ConfigureForm nodeConfig = new ConfigureForm(FormType.submit);
		nodeConfig.setAccessModel(AccessModel.open);
		nodeConfig.setDeliverPayloads(true);
		nodeConfig.setNotifyRetract(true);
		nodeConfig.setNotifyConfig(true);
		nodeConfig.setSubscribe(true);
		nodeConfig.setPersistentItems(true);
		nodeConfig.setPublishModel(PublishModel.open);
		nodeConfig.setNodeType(NodeType.leaf);

		try {
			// Wenn Node nicht gefunden dann neuen erstellen
			leafNode = (LeafNode) mgr.getNode(leafnodeName);
		} catch (XMPPException ex) {
			System.err.println("Node nicht gefunden!");

			if (ex.getXMPPError().getCode() == 404) {
				try {
					leafNode = mgr.createNode(leafnodeName);
					if (nodeConfig != null)
						leafNode.sendConfigurationForm(nodeConfig);
				} catch (XMPPException ex2) {
					System.err.println("Node konnte nicht erstellt werden!");
					return false;
				}
			}
		}

		return true;
	}

	// Node löschen
	public void deleteNode(String nodeName) throws XMPPException {
		mgr.deleteNode(nodeName);
	}

	/*
	 * private WebResource rest() { restClient = Client.create(); webResource =
	 * restClient.resource(this.URL); webResource.type("application/xml");
	 * return webResource; }
	 */

	// Account ausgeben
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

	// Ort ausgeben
	public String getOrt(BigInteger id) {
		String temp = "";

		restClient = Client.create();
		webResource = restClient.resource("http://" + getServer() + ":"
				+ getPort() + "/orte");
		webResource.path(String.valueOf(id));

		Orteliste ort = webResource.type(MediaType.APPLICATION_XML).get(
				Orteliste.class);

		temp = "ID: " + ort.getOrt().get(0).getOrtID() + "\n" + "Ort: "
				+ ort.getOrt().get(0).getOrt() + "\n" + "Platz: "
				+ ort.getOrt().get(0).getPlatz() + "\n" + "Min. Spieler: "
				+ ort.getOrt().get(0).getMinSpieleranzahl() + "\n"
				+ "Max. Spieler:" + ort.getOrt().get(0).getMaxSpieleranzahl()
				+ "\n" + "Geöffnet von: "
				+ ort.getOrt().get(0).getOeffnungszeiten().getGeoeffnetVon()
				+ "\n" + "Geöffnet bis: "
				+ ort.getOrt().get(0).getOeffnungszeiten().getGeoeffnetBis()
				+ "\n" + "Geschlossen an: "
				+ ort.getOrt().get(0).getOeffnungszeiten().getGeschlossenAn()
				+ "\n" + "Preis: " + ort.getOrt().get(0).getPreis() + "\n";
		return temp;
	}

	// Event ausgeben
	public String getEvent(BigInteger id) {
		String temp = "";

		restClient = Client.create();
		webResource = restClient.resource("http://" + getServer() + ":"
				+ getPort() + "/events");
		webResource.path(String.valueOf(id));

		Events event = webResource.type(MediaType.APPLICATION_XML).get(
				Events.class);

		temp = "ID: " + event.getEvent().get(0).getEventID() + "\n"
				+ "Sportart: " + event.getEvent().get(0).getSportart() + "\n"
				+ "Start: "
				+ event.getEvent().get(0).getSpielzeitraum().getVon() + "\n"
				+ "Min. Teilnehmer: " + event.getEvent().get(0).getMinSpieler()
				+ "\n" + "Max. Teilnehmer: "
				+ event.getEvent().get(0).getMaxSpieler() + "\n" + "Ende: "
				+ event.getEvent().get(0).getSpielzeitraum().getBis() + "\n"
				+ "Örtlichkeit"
				+ getOrt(event.getEvent().get(0).getOertlichkeit().getOrtID())
				+ "\n" + "Spielerlist" + "\n" + "ID: "
				+ event.getEvent().get(0).getAdmin() + "\n" + "Blacklist"
				+ event.getEvent().get(0).getBacklist();

		return temp;

	}

	// User registrieren über AccountManager am
	public void registerUser(String username, String password,
			Map<String, String> attribute) throws XMPPException {

		am.createAccount(username, password, attribute);

	}

	// login
	public void login() throws XMPPException {
		try {
			connection.login(username, password);
		} catch (XMPPException ex) {
			throw ex;
		}

	}

	// Verbindung zum Server trennen
	public void disconnect() {
		connection.disconnect();
	}

	// Username wiedergeben
	public String getUsername() {
		return username;
	}

	// Username übergeben
	public void setUsername(String username) {
		this.username = username;
	}

	// Server wiedergeben
	public String getServer() {
		return Config.server;
	}

	// Port wiedergeben
	public int getPort() {
		return Config.port;
	}

	// Password übergeben
	public String getPassword() {
		return password;
	}

	// Passwort übergeben
	public void setPassword(String password) {
		this.password = password;
	}
	/*
	 * Java Swing Beispiel
	 * 
	 * 
	 * 
	 * 
	 * 
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