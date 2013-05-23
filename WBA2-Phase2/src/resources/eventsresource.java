package resources;

import jaxb.*;

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

import com.sun.jersey.api.NotFoundException;


@Path("recources")
public class eventsresource {

	public static EventsType events = new EventsType();

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<EType> getEvents() {
		return events.getEvent();
	}

	/*
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response postevent(EType event){
		URI location = addevent(event);

		return Response.created(location).build();
	}*/
	
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response postevent(@FormParam("Spielzeitaum") DType spielzeitraum,
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

		return Response.created(location).build();

	}

}
