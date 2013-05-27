package resources;

import jaxb.*;

import java.io.File;
import java.math.BigInteger;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import com.sun.jersey.api.NotFoundException;

@Path("recources/events")
public class eventsresource {

	public eventsresource() throws Exception {
		String xmlevents = "../Events.xml";

		JAXBContext context = JAXBContext.newInstance(EventsType.class);
		Unmarshaller u = context.createUnmarshaller();

		EventsType eventliste = (EventsType) u.unmarshal(
				new StreamSource(new File(xmlevents)), EventsType.class)
				.getValue();
		ArrayList<EType> eliste = (ArrayList<EType>) eventliste.getEvent();
	}

	public static EventsType events = new EventsType();

	@Context
	UriInfo uriInfo;

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<EType> getEvents() {
		return events.getEvent();
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response postevent(EType event) {
		URI location = addevent(event);

		return Response.created(location).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response postevent(
			@FormParam("Spielzeitaum") DType spielzeitraum,
			@FormParam("Event-ID") BigInteger eventID, // die ID ist ein
														// Attribut, ist es
														// trotzdem richtig so?
			@FormParam("Sportart") SType sportart,
			@FormParam("Oertlichkeit") OType oertlichkeit,
			@FormParam("Spielerliste") SlType spielerliste,
			@FormParam("Blacklist") BType blacklist,
			@FormParam("Admin") SpielerType admin) {
		if (spielzeitraum == null || sportart == null || oertlichkeit == null
				|| admin == null) {
			throw new WebApplicationException(
					Response.status(400)
							.entity("Event konnte nicht angelegt werden, da wichtige Informationen fehlen.")
							.build());
		}
		EType veranstaltung = new EType();

		veranstaltung.setSpielzeitraum(spielzeitraum);
		veranstaltung.setSportart(sportart);
		veranstaltung.setOertlichkeit(oertlichkeit);
		veranstaltung.setSpielerliste(spielerliste);
		veranstaltung.setBacklist(blacklist);
		veranstaltung.setAdmin(admin);

		URI location = addevent(veranstaltung);

		return Response.created(location).build();

	}

	@OPTIONS
	public Response optionsOptions() {
		return Response.ok()
				.header("Allow-Control-Allow-Methods", "POST,GET,OPTIONS")
				.header("Access-Control-Allow-Origin", "*").build();
	}

	private URI addevent(EType event) {
		System.out.println("DEBUG: adding new event ("
				+ event.getSpielzeitraum() + ", " + event.getSportart() + ", "
				+ event.getOertlichkeit() + ", " + event.getSpielerliste()
				+ ", " + event.getBacklist() + ", " + event.getAdmin() + ")");
		int index = events.getEvent().size();

		event.setEventID(BigInteger.valueOf(index));

		URI location = uriInfo.getAbsolutePathBuilder().path("" + index)
				.build();

		events.getEvent().add(event);

		return location;
	}

}
