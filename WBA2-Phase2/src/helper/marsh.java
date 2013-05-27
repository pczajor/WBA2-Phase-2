package helper;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

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

	
	public EventsType unmarshalEvent() throws JAXBException{
		Unmarshaller un = event.createUnmarshaller();
		EventsType eventliste = (EventsType) un.unmarshal(new File("../events.xml"));

		return eventliste;
	}
	
	
	public OrteType unmarshalOrt() throws JAXBException{
		Unmarshaller un = ort.createUnmarshaller();
		OrteType orteliste = (OrteType) un.unmarshal(new File("../orte.xml"));

		return orteliste;
	}
	
	
	
	
}
