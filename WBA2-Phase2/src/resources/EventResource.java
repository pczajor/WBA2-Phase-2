package resources;

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
	public EType getEventInfos(int ID) {
		return this.event;
	}


}
