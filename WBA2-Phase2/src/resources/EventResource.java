package resources;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jaxb.*;


@Path("resources")
public class EventResource {
	
	private static EType event = new EType();

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public EType getEventInfos(int ID) {
		return eventsresource.events.getEvent()
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public int putevent(){
		return 1;
	}
	

	
	
}
