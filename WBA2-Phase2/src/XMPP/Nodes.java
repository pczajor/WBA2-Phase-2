package XMPP;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.pubsub.AccessModel;
import org.jivesoftware.smackx.pubsub.ConfigureForm;
import org.jivesoftware.smackx.pubsub.FormType;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.NodeType;
import org.jivesoftware.smackx.pubsub.PubSubManager;
import org.jivesoftware.smackx.pubsub.PublishModel;

public class Nodes {

	
	PubSubManager mgr;
	
	
	 public boolean createLeafNode(String leafNodeName) {
	        LeafNode leafNode = null;
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
	            leafNode = (LeafNode) mgr.getNode(leafNodeName);
	            //System.out.println("Leafnode bereits vorhanden");
	        } catch (XMPPException e) {
	            System.out.println("Node konnte nicht ermittelt werden.");
	            //e.printStackTrace();
	        }

	        try {
	            if (null == leafNode) {
	                leafNode = (LeafNode) mgr.createNode(leafNodeName,
	                        nodeConfig);
	                //System.out.println("Fehlenden Leafnode erstellt");
	            } else {
	                leafNode.sendConfigurationForm(nodeConfig);
	            }
	        } catch (XMPPException e) {
	            System.out.println("Exception occured while trying to create node: "
	                    + e.getLocalizedMessage());
	            e.printStackTrace();
	        }
	        
	        return true;
	 }
	 
	
	
}
