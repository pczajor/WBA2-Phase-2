package resources;

//REST Ressource f�r orte.xml
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

import jaxb.Orteliste;

import org.xml.sax.SAXException;

@Path("orte")
public class orteressource {

	public static marsh xml;

	public orteressource() throws JAXBException {
		xml = new marsh();
	}
	
	//Get Liste aller Orte
	@GET
	@Produces("application/xml")
	public Orteliste getOrte() throws JAXBException, SAXException {
		return xml.unmarshalOrt();
	}

	//Get einzelnen Ort
	//PathParam = ID
	@GET
	@Path("{id}")
	@Produces("application/xml")
	public Orteliste getOrt(@PathParam("id") BigInteger ortID)
			throws JAXBException {
		Orteliste.Ort ort = new Orteliste.Ort();

		Orteliste returnOrt = new Orteliste();
		Orteliste orte = xml.unmarshalOrt();

		int paramId = ortID.intValue();
		for (Orteliste.Ort each : orte.getOrt()) {
			if (each.getOrtID().intValue() == paramId) {
				// == paramId)
				ort = each;

				returnOrt.getOrt().add(ort);

				return returnOrt;
			}
		}
		return orte;
	}

	//Bearbeiten von  einzelnen Orten
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public void setOrt(Orteliste temp) throws JAXBException,
			FileNotFoundException, SAXException, DatatypeConfigurationException {
		Orteliste orte = xml.unmarshalOrt();
		int index = 0;
		boolean achive = false;
		int queryId = Integer.parseInt(temp.getOrt().get(0).getPlatz());
		

		//Orteliste durchlaufen bis passende ID gefunden
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
		xml.marshalOrt(orte);
	}
	
	//Neuen Ort hinzuf�gen
	@POST
	// @Path("post")
	@Consumes(MediaType.APPLICATION_XML)
	public void createOrt(Orteliste o) throws JAXBException,
			FileNotFoundException, SAXException, DatatypeConfigurationException {
		Orteliste orte = xml.unmarshalOrt();
		Orteliste ortList = new Orteliste();

		o.getOrt().get(0).setOrtID(BigInteger.valueOf(getNextId()));

		ortList.getOrt().add(o.getOrt().get(0));
		for (Orteliste.Ort each : orte.getOrt()) {
			ortList.getOrt().add(each);
		}
		xml.marshalOrt(ortList);
	}
	//Ort l�schen
	@DELETE
	@Path("{id}")
	public void deleteOrt(@PathParam("id") BigInteger ortID)
			throws JAXBException, FileNotFoundException, SAXException {
		int paramId = ortID.intValue();
		Orteliste orte = xml.unmarshalOrt();
		
		//Orte durchlaufen bis ID gefunden
		for (Orteliste.Ort each : orte.getOrt()) {
			if (each.getOrtID().intValue() == paramId) {
				orte.getOrt().remove(each);
				break;
			}
		}
		xml.marshalOrt(orte);
	}
	
	//Methode zum berechnen der n�cshten ID
	public static int getNextId() throws JAXBException {
		// int count = xml.unmarshalOrt().getOrt().get(0).getOrtID().intValue();
		// count++;

		// return count=;
		return 5;

	}

}
