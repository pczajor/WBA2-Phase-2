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


import jaxb.SlType;
import jaxb.SpielerType;



@Path("recources/accounts")
public class accountsressource {


	
	public accountsressource() throws Exception {
		String xmlaccounts = "../accounts.xml";

		JAXBContext context = JAXBContext.newInstance(SlType.class);
		Unmarshaller u = context.createUnmarshaller();

		SlType accountliste = (SlType) u.unmarshal(
				new StreamSource(new File(xmlaccounts)), SlType.class)
				.getValue();
		ArrayList<SpielerType> aliste = (ArrayList<SpielerType>) accountliste.getSpieler();
	}
	
	
	
	public static SlType account = new SlType();
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<SpielerType> getAccounts() {
		return account.getSpieler();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
