package resources;

import helper.marsh;

import java.io.FileNotFoundException;

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

import jaxb.Orteliste;

import org.xml.sax.SAXException;





@Path("orte")
public class orteressource {

	
public marsh xml;
	
	public orteressource() throws JAXBException {
		this.xml = new marsh();
}
	

	@GET
	@Produces("application/xml")
	public Orteliste getOrte() throws JAXBException, SAXException{
		return this.xml.unmarshalOrt();
	}

	@GET
	@Path("{id}")
	@Produces("application/xml")
	public Orteliste getOrt(@PathParam("id") String platz)
			throws JAXBException {
		Orteliste.Ort ort = new Orteliste.Ort();

		Orteliste returnOrt = new Orteliste();
		Orteliste orte = this.xml.unmarshalOrt();

		int paramId = Integer.parseInt(platz);
		for (Orteliste.Ort each : orte.getOrt()) {
			if (Integer.parseInt(each.getPlatz()) == paramId) { 
															// == paramId)
				ort = each;

				returnOrt.getOrt().add(ort);

				return returnOrt;
			}
		}
		return orte;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public void setOrt(Orteliste temp) throws JAXBException,
			FileNotFoundException, SAXException, DatatypeConfigurationException {
		Orteliste orte = this.xml.unmarshalOrt();
		int index = 0;
		boolean achive = false;
		int queryId = Integer.parseInt(temp.getOrt().get(0).getPlatz());
		for (Orteliste.Ort each : orte.getOrt()) {
			if (Integer.parseInt(each.getPlatz()) == queryId) {
				index = orte.getOrt().indexOf(each);
				achive = true;
			}
		}

		if (achive) {
			orte.getOrt().set(index, temp.getOrt().get(0));
		} else {
			System.out.printf("Fehler, Ort nicht gefunden!");
		}
		this.xml.marshalOrt(orte);
	}

	@POST
	// @Path("post")
	@Consumes(MediaType.APPLICATION_XML)
	public void createOrt(Orteliste o) throws JAXBException,
			FileNotFoundException, SAXException, DatatypeConfigurationException {
		Orteliste orte = this.xml.unmarshalOrt();
		Orteliste ortList = new Orteliste();

		o.getOrt().get(0).setPlatz(this.xml.unmarshalOrt().getOrt().get(0).getPlatz()); //id=Platz=String... in xml?richtig?

		ortList.getOrt().add(o.getOrt().get(0));
		for (Orteliste.Ort each : orte.getOrt()) {
			ortList.getOrt().add(each);
		}
		this.xml.marshalOrt(ortList);
	}

	@DELETE
	@Path("{id}")
	public void deleteOrt(@PathParam("id") String platz) throws JAXBException,
			FileNotFoundException, SAXException {
		int paramId = Integer.parseInt(platz);
		Orteliste orte = this.xml.unmarshalOrt();
		for (Orteliste.Ort each : orte.getOrt()) {
			if (Integer.parseInt(each.getPlatz()) == paramId) {
				orte.getOrt().remove(each);
				break;
			}
		}
		this.xml.marshalOrt(orte);
	}

}
