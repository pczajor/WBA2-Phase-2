package resources;

import java.io.File;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import jaxb.OType;



@Path("recources/orte/")
public class ortressource {
	
	
	public OType ort = new OType();
	
	public ortressource(OType ort) {
			super();
			this.ort = ort;
	}
		
		
		
		@GET
		@Path("{id}")
		@Produces(MediaType.APPLICATION_XML)
		public OType getSpielerInfo(@PathParam("id") String platz) {
			return this.ort;
		
	}

}
