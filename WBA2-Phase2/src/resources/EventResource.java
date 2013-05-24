package resources;

import java.math.BigInteger;
import java.net.URI;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jaxb.*;

@Path("resources")
public class EventResource {

	private EType event = new EType();

	public EventResource(EType event) {
		super();
		this.event = event;
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public EType getEventInfos() {
		return this.event;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response postSpielerliste(
			@FormParam("Spielerliste") SlType spielerliste) {
		SlType sliste = new SlType();
		EType veranstaltung =new EType();
		
		veranstaltung.setSpielerliste(sliste);
		
		

		URI location = addevent(veranstaltung);

		return Response.created(location).build();

	}

}
