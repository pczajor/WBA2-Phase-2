package resources;

import helper.marsh;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
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
	public SlType getAccounts() throws JAXBException, SAXException{
		return this.xml.unmarshalSpieler();	
	}
	
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public SlType getUser(@PathParam("id") String telefonnummer) throws JAXBException{
		SpielerType spieler = new SpielerType();

		SlType returnSpieler = new SlType();
		SlType accounts = this.xml.unmarshalSpieler();

		int paramId = Integer.parseInt(telefonnummer);
		for (SpielerType use: accounts.getSpieler()){
			if(Integer.parseInt(use.getTelefonnummer()) == paramId){
				spieler=use;

				 returnSpieler.getSpieler().add(spieler);
				 
				 return returnSpieler;
			}
		}

		return accounts;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
