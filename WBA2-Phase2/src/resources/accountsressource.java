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

import jaxb.Accountliste;

import org.xml.sax.SAXException;

@Path("accounts/")
public class accountsressource {

	public marsh xml;

	public accountsressource() throws JAXBException {
		this.xml = new marsh();
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Accountliste getAccounts() throws JAXBException, SAXException {
			return this.xml.unmarshalSpieler();
		
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Accountliste getSpieler(@PathParam("id") String telefonnummer)
			throws JAXBException {
		Accountliste.Spieler spieler = new Accountliste.Spieler();

		Accountliste returnSpieler = new Accountliste();
		Accountliste accounts = this.xml.unmarshalSpieler();

		int paramId = Integer.parseInt(telefonnummer);
		for (Accountliste.Spieler each : accounts.getSpieler()) {
			if (Integer.parseInt(each.getTelefonnummer()) == paramId) {
				spieler = each;

				returnSpieler.getSpieler().add(spieler);

				return returnSpieler;
			}
		}

		return accounts;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public void setSpieler(Accountliste temp) throws JAXBException,
			FileNotFoundException, SAXException, DatatypeConfigurationException {
		Accountliste accounts = this.xml.unmarshalSpieler();
		int index = 0;
		boolean achive = false;
		int queryId = Integer.parseInt(temp.getSpieler().get(0)
				.getTelefonnummer());
		for (Accountliste.Spieler use : accounts.getSpieler()) {
			if (Integer.parseInt(use.getTelefonnummer()) == queryId) {
				index = accounts.getSpieler().indexOf(use);
				achive = true;
			}
		}

		if (achive) {
			accounts.getSpieler().set(index, temp.getSpieler().get(0));
		} else {
			System.out.printf("Fehler, Spieler nicht gefunden!");
		}
		this.xml.marshalSpieler(accounts);
	}
	@POST
	// @Path("post")
	@Consumes(MediaType.APPLICATION_XML)
	public void createSpieler(Accountliste s) throws JAXBException,
			FileNotFoundException, SAXException, DatatypeConfigurationException {
		Accountliste accounts = this.xml.unmarshalSpieler();
		Accountliste accountList = new Accountliste();

		s.getSpieler().get(0).setTelefonnummer(this.xml.unmarshalSpieler().getSpieler().get(0).getTelefonnummer()); //id=telefonnummer=String... in xml?richtig?

		accountList.getSpieler().add(s.getSpieler().get(0));
		for (Accountliste.Spieler each : accounts.getSpieler()) {
			accountList.getSpieler().add(each);
		}
		this.xml.marshalSpieler(accountList);
	}

	@DELETE
	@Path("{id}")
	public void deleteSpieler(@PathParam("id") String telefonnummer) throws JAXBException,
			FileNotFoundException, SAXException {
		int paramId = Integer.parseInt(telefonnummer);
		Accountliste spieler = this.xml.unmarshalSpieler();
		for (Accountliste.Spieler each : spieler.getSpieler()) {
			if (Integer.parseInt(each.getTelefonnummer()) == paramId) {
				spieler.getSpieler().remove(each);
				break;
			}
		}
		this.xml.marshalSpieler(spieler);
	}

}

