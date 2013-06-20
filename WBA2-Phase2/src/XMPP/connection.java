package XMPP;

import java.util.Map;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.PubSubManager;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;

public class connection {
	public static XMPPConnection connection;
	public static  PubSubManager mgr;
	public static  String userName;
	public static  String password;
	public static AccountManager am;
	
	
	
	public static void connect() throws Exception{
		ConnectionConfiguration config = new ConnectionConfiguration(Config.server,
				Config.port);
		connection = new XMPPConnection(config);

		connection.connect();
		
	}
	
	public static void registerUser(String username, String password, Map<String, String> attribute) throws XMPPException{
		
		am.createAccount(username, password, attribute);
		
	}

	public static void login(String userName, String password) throws XMPPException {
		try
		{
		connection.login(userName, password);
		}
		catch(XMPPException ex)
		{
			throw ex;
		}
		mgr = new PubSubManager(connection, "pubsub." + connection.getHost());
	}

	public void disconnect() {
		connection.disconnect();
	}
	
	
	public String getUsername() {
		return userName;
	}

	public static void setUsername(String username) {
		userName = username;
	}

	public static String getServer() {
		return Config.server;
	}


	public static int getPort() {
		return Config.port;
	}


	public String getPassword() {
		return password;
	}
	


	public static void setPassword(String password) {
		password = password;
	}
}
