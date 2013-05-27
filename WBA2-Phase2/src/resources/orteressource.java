package resources;

import helper.marsh;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;

import jaxb.EventsType;
import jaxb.OType;
import jaxb.OrteType;





@Path("recources/orte")
public class orteressource {

	
public marsh xml;
	
	public orteressource() throws JAXBException {
		this.xml = new marsh();
}
	

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public OrteType getOrte() throws JAXBException, SAXException{
		return this.xml.unmarshalOrt();
	}
}
