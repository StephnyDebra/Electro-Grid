package service;


import com.model;

import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;


@Path("/Service")
public class service {
	
	

	model serviceObj = new model();
	
	@POST
	@Path("/insert") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertService(@FormParam("AccountNo") String AccountNo, 
	 @FormParam("Address") String Address, 
	 @FormParam("Inquiry") String Inquiry, 
	 @FormParam("Status") String Status,
	 @FormParam("TelNo") String TelNo) 
	{ 
	 String output = serviceObj.insertService(AccountNo, Address, Inquiry, Status, TelNo); 
	return output; 
	}
	
	@GET
	@Path("/read") 
	@Produces(MediaType.TEXT_HTML) 
	public String readService() 
	 { 
	 return serviceObj.readService(); 
	}
	
	@PUT
	@Path("/update") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateService(String serviceData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject serviceObject = new JsonParser().parse(serviceData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String ServiceId = serviceObject.get("ServiceId").getAsString(); 
	 String AccountNo = serviceObject.get("AccountNo").getAsString(); 
	 String Address = serviceObject.get("Address").getAsString(); 
	 String Inquiry = serviceObject.get("Inquiry").getAsString(); 
	 String Status = serviceObject.get("Status").getAsString();
	 String TelNo = serviceObject.get("TelNo").getAsString();
	 String output = serviceObj.updateService(ServiceId, AccountNo, Address, Inquiry, Status, TelNo); 
	return output; 
	}

	@DELETE
	@Path("/delete") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteService(String serviceData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(serviceData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String ServiceId = doc.select("ServiceId").text(); 
	 String output = serviceObj.deleteService(ServiceId); 
	return output; 
	}

}
