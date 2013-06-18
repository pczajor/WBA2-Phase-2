package resources;

import helper.marsh;

import java.io.FileNotFoundException;
import java.math.BigInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import jaxb.Events;

import org.xml.sax.SAXException;

@Path("events")
public class eventsresource {

	public marsh xml;

	public eventsresource() throws JAXBException {
		this.xml = new marsh();
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Events getEvents() throws JAXBException, SAXException {
		return this.xml.unmarshalEvent();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Events getEvent(@PathParam("id") BigInteger eventID)
			throws JAXBException {
		Events.Event veranstaltung = new Events.Event();

		Events returnEvent = new Events();
		Events events = this.xml.unmarshalEvent();

		int paramId = eventID.intValue();
		for (Events.Event each : events.getEvent()) {
			if (each.getEventID().intValue() == paramId) { // (each.getEventID().intValue()
															// == paramId)
				veranstaltung = each;

				returnEvent.getEvent().add(veranstaltung);

				return returnEvent;
			}
		}
		return events;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public void setEvent(Events temp) throws JAXBException,
			FileNotFoundException, SAXException, DatatypeConfigurationException {
		Events veranstaltungen = this.xml.unmarshalEvent();
		int index = 0;
		boolean achive = false;
		int queryId = temp.getEvent().get(0).getEventID().intValue();
		for (Events.Event each : veranstaltungen.getEvent()) {
			if (each.getEventID().intValue() == queryId) {
				index = veranstaltungen.getEvent().indexOf(each);
				achive = true;
			}
		}

		if (achive) {
			veranstaltungen.getEvent().set(index, temp.getEvent().get(0));
		} else {
			System.out.printf("Fehler, Event nicht gefunden!");
		}
		this.xml.marshalEvent(veranstaltungen);
	}

	@POST
	// @Path("post")
	@Consumes(MediaType.APPLICATION_XML)
	public void createEvent(Events e) throws JAXBException,
			FileNotFoundException, SAXException, DatatypeConfigurationException {
		Events events = this.xml.unmarshalEvent();
		Events eventList = new Events();

		e.getEvent().get(0).setEventID(BigInteger.valueOf(getNextId()));

		eventList.getEvent().add(e.getEvent().get(0));
		for (Events.Event each : events.getEvent()) {
			eventList.getEvent().add(each);
		}
		this.xml.marshalEvent(eventList);
	}

	@DELETE
	@Path("{id}")
	public void deleteEvent(@PathParam("id") BigInteger eventID) throws JAXBException,
			FileNotFoundException, SAXException {
		int paramId = eventID.intValue();
		Events events = this.xml.unmarshalEvent();
		for (Events.Event each : events.getEvent()) {
			if (each.getEventID().intValue() == paramId) {
				events.getEvent().remove(each);
				break;
			}
		}
		this.xml.marshalEvent(events);
	}

	public int getNextId() throws JAXBException {
		int count = this.xml.unmarshalEvent().getEvent().get(0).getEventID().intValue();
		count++;

		return count;

	}
}
