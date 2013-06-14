package helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import jaxb.Accountliste;
import jaxb.Events;
import jaxb.Orteliste;

import org.xml.sax.SAXException;

public class marsh {

	JAXBContext spieler;
	JAXBContext event;
	JAXBContext ort;

	public marsh() throws JAXBException {
		this.spieler = JAXBContext.newInstance(Accountliste.class);
		this.event = JAXBContext.newInstance(Events.class);
		this.ort = JAXBContext.newInstance(Orteliste.class);
	}

	public Accountliste unmarshalSpieler() throws JAXBException {

		Unmarshaller un = spieler.createUnmarshaller();
		Accountliste spielerliste = (Accountliste) un.unmarshal(new File(
				"src/xsd/accounts.xml"));

		return spielerliste;
	}

	public void marshalSpieler(Accountliste account) throws JAXBException,
			SAXException, FileNotFoundException {
		Marshaller mar = spieler.createMarshaller();
		mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		FileOutputStream file = new FileOutputStream("src/xsd/accounts.xml");
		mar.marshal(account, file);
	}

	public Events unmarshalEvent() throws JAXBException {
		Unmarshaller un = event.createUnmarshaller();
		Events eventliste = (Events) un.unmarshal(new File(
				"src/xsd/event.xml"));

		return eventliste;
	}

	public void marshalEvent(Events e) throws JAXBException, SAXException,
			FileNotFoundException {
		Marshaller mar = event.createMarshaller();
		mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		FileOutputStream file = new FileOutputStream("src/xsd/event.xml");
		mar.marshal(e, file);
	}

	public Orteliste unmarshalOrt() throws JAXBException {
		Unmarshaller un = ort.createUnmarshaller();
		Orteliste orteliste = (Orteliste) un.unmarshal(new File(
				"src/xsd/orte.xml"));

		return orteliste;
	}

	public void marshalOrt(Orteliste o) throws JAXBException, SAXException,
			FileNotFoundException {
		Marshaller mar = ort.createMarshaller();
		mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		FileOutputStream file = new FileOutputStream("src/xsd/orte.xml");
		mar.marshal(o, file);
	}

}
