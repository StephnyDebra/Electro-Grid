package service;

import com.model;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/User")
public class service {
	
	model userObj = new model(); 
	
	@POST
	@Path("/insert") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem(@FormParam("AccountNo") String AccountNo, 
	 @FormParam("Name") String Name, 
	 @FormParam("Address") String Address, 
	 @FormParam("NIC") String NIC,
	 @FormParam("TelNo") String TelNo) 
	{ 
	 String output = userObj.insertUser(AccountNo, Name, Address, NIC, TelNo); 
	return output; 
	}

	@GET
	@Path("/read") 
	@Produces(MediaType.TEXT_HTML) 
	public String readUser() 
	 { 
	 return userObj.readUser(); 
	}
	
	@PUT
	@Path("/update") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateUser(String userData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String CID = userObject.get("CID").getAsString(); 
	 String AccountNo = userObject.get("AccountNo").getAsString(); 
	 String Name = userObject.get("Name").getAsString(); 
	 String Address = userObject.get("Address").getAsString(); 
	 String NIC = userObject.get("NIC").getAsString();
	 String TelNo = userObject.get("TelNo").getAsString();
	 String output =userObj.updateUser(CID, AccountNo, Name, Address, NIC, TelNo); 
	return output; 
	}
	
	@DELETE
	@Path("/delete") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteUser(String userData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(userData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String CID = doc.select("CID").text(); 
	 String output = userObj.deleteUser(CID); 
	return output; 
	}

}
