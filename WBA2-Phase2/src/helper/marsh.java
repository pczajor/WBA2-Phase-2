package helper;

import java.io.File;
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
		SlType spielerliste = (SlType) un.unmarshal(new File("../accounts.xml"));

		return spielerliste;
	}

	public void marshalSpieler(SlType account) throws JAXBException, SAXException, FileNotFoundException{
		Marshaller mar = spieler.createMarshaller();
		mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		FileOutputStream file = new FileOutputStream("../accounts.xml");
		mar.marshal(account, file);
	}
	
	public EventsType unmarshalEvent() throws JAXBException{
		Unmarshaller un = event.createUnmarshaller();
		EventsType eventliste = (EventsType) un.unmarshal(new File("../events.xml"));

		return eventliste;
	}
	
	public void marshalEvent(EventsType e) throws JAXBException, SAXException, FileNotFoundException{
		Marshaller mar = event.createMarshaller();
		mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		FileOutputStream file = new FileOutputStream("../events.xml");
		mar.marshal(e, file);
	}
	
	
	public OrteType unmarshalOrt() throws JAXBException{
		Unmarshaller un = ort.createUnmarshaller();
		OrteType orteliste = (OrteType) un.unmarshal(new File("../orte.xml"));

		return orteliste;
	}
	
	public void marshalOrt(OrteType o) throws JAXBException, SAXException, FileNotFoundException{
		Marshaller mar = ort.createMarshaller();
		mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		FileOutputStream file = new FileOutputStream("../orte.xml");
		mar.marshal(o, file);
	}
	
	
	
}
