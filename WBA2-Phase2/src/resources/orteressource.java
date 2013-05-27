package resources;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import jaxb.OType;
import jaxb.OrteType;





@Path("recources/orte")
public class orteressource {

	
	
	public orteressource() throws Exception {
		String xmlorte = "../orte.xml";

		JAXBContext context = JAXBContext.newInstance(OrteType.class);
		Unmarshaller u = context.createUnmarshaller();

		OrteType orteliste = (OrteType) u.unmarshal(
				new StreamSource(new File(xmlorte)), OrteType.class)
				.getValue();
		ArrayList<OType> oliste = (ArrayList<OType>) orteliste.getOrt();
	}
	
	
	
	public static OrteType orte = new OrteType();
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<OType> getAccounts() {
		return orte.getOrt();
	}
}
