package resources;

import helper.marsh;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;

import jaxb.SlType;
import jaxb.SpielerType;

@Path("recources/accounts")
public class accountsressource {

	public marsh xml;

	public accountsressource() throws JAXBException {
		this.xml = new marsh();
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public SlType getAccounts() throws JAXBException, SAXException {
		return this.xml.unmarshalSpieler();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public SlType getUser(@PathParam("id") String telefonnummer)
			throws JAXBException {
		SpielerType spieler = new SpielerType();

		SlType returnSpieler = new SlType();
		SlType accounts = this.xml.unmarshalSpieler();

		int paramId = Integer.parseInt(telefonnummer);
		for (SpielerType use : accounts.getSpieler()) {
			if (Integer.parseInt(use.getTelefonnummer()) == paramId) {
				spieler = use;

				returnSpieler.getSpieler().add(spieler);

				return returnSpieler;
			}
		}

		return accounts;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public void setSpieler(SlType temp) throws JAXBException,
			FileNotFoundException, SAXException, DatatypeConfigurationException {
		SlType accounts = this.xml.unmarshalSpieler();
		int index = 0;
		boolean achive = false;
		int queryId = Integer.parseInt(temp.getSpieler().get(0)
				.getTelefonnummer());
		for (SpielerType use : accounts.getSpieler()) {
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
}
