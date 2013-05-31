package resources;

import helper.marsh;
import jaxb.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.*;
import java.lang.Number;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;

import com.sun.jersey.api.NotFoundException;

import jaxb.EType;
import jaxb.EventsType;
import jaxb.SlType;
import jaxb.SpielerType;

@Path("recources/events")
public class eventsresource {

	public marsh xml;

	public eventsresource() throws JAXBException {
		this.xml = new marsh();
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public EventsType getEvents() throws JAXBException, SAXException {
		return this.xml.unmarshalEvent();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public EventsType getUser(@PathParam("id") BigInteger eventID)
			throws JAXBException {
		EType veranstaltung = new EType();

		EventsType returnEvent = new EventsType();
		EventsType events = this.xml.unmarshalEvent();

		int paramId = BigInteger.intValue(eventID);
		for (EType each : events.getEvent()) {
			if (BigInteger.intValue(each.getEventID()) == paramId) {
				veranstaltung = each;

				returnEvent.getEvent().add(veranstaltung);

				return returnEvent;
			}
		}
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public void setEvent(EventsType temp) throws JAXBException,
			FileNotFoundException, SAXException, DatatypeConfigurationException {
		EventsType veranstaltungen = this.xml.unmarshalEvent();
		int index = 0;
		boolean achive = false;
		int queryId = BigInteger.intValue(temp.getEvent().get(0)
				.getEventID());
		for (EType use : veranstaltungen.getEvent()) {
			if (BigInteger.intValue(use.getEventID()) == queryId) {
				index = veranstaltungen.getEvent().indexOf(use);
				achive = true;
			}
		}

		if (achive) {
			veranstaltungen.getEvent().set(index, temp.getEvent().get(0));
		} else {
			System.out.printf("Fehler, Spieler nicht gefunden!");
		}
		this.xml.marshalEvent(veranstaltungen );
	}
}
