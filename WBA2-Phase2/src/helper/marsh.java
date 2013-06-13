package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.xml.sax.SAXException;

import jaxb.*;

public class marsh {

	JAXBContext spieler;
	JAXBContext event;
	JAXBContext ort;
	
	public marsh() throws JAXBException{
		this.spieler= JAXBContext.newInstance(SlType.class);
		this.event= JAXBContext.newInstance(EventsType.class);
		this.ort= JAXBContext.newInstance(OrteType.class);
	}
	
	
	
	
	public SlType unmarshalSpieler() throws JAXBException{
		
		Unmarshaller un = spieler.createUnmarshaller();
		try{
		SlType spielerliste = (SlType) un.unmarshal(new File("src/xsd/accounts.xml"));
		
		return spielerliste;
		}
		catch(Exception e){System.out.println(System.getProperty("user.dir"));}
		return null;
	}

	public void marshalSpieler(SlType account) throws JAXBException, SAXException, FileNotFoundException{
		Marshaller mar = spieler.createMarshaller();
		mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		FileOutputStream file = new FileOutputStream("src/xsd/accounts.xml");
		mar.marshal(account, file);
	}
	
	public EventsType unmarshalEvent() throws JAXBException{
		Unmarshaller un = event.createUnmarshaller();
		EventsType eventliste = (EventsType) un.unmarshal(new File("src/xml/events.xml"));

		return eventliste;
	}
	
	public void marshalEvent(EventsType e) throws JAXBException, SAXException, FileNotFoundException{
		Marshaller mar = event.createMarshaller();
		mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		FileOutputStream file = new FileOutputStream("src/xml/events.xml");
		mar.marshal(e, file);
	}
	
	
	public OrteType unmarshalOrt() throws JAXBException{
		Unmarshaller un = ort.createUnmarshaller();
		OrteType orteliste = (OrteType) un.unmarshal(new File("src/xml/orte.xml"));

		return orteliste;
	}
	
	public void marshalOrt(OrteType o) throws JAXBException, SAXException, FileNotFoundException{
		Marshaller mar = ort.createMarshaller();
		mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		FileOutputStream file = new FileOutputStream("src/xml/orte.xml");
		mar.marshal(o, file);
	}
	
	
	
}
