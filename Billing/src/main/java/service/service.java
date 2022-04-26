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

@Path("/Bill")
public class service {

	model billObj = new model();
	
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertBill(@FormParam("AccountNo") String AccountNo,
	 @FormParam("Month") String Month,
	 @FormParam("UnitConsumed") String UnitConsumed,
	 @FormParam("UnitPrice") String UnitPrice,
	 @FormParam("TotalAmount") String TotalAmount)
	{
	 String output = billObj.insertBill(AccountNo, Month, UnitConsumed, UnitPrice, TotalAmount);
	return output;
	}
	
	@GET
	@Path("/read")
	@Produces(MediaType.TEXT_HTML)
	public String readBill()
	 {
	 return billObj.readBill();
	}

	
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBill(String billData)
	{
	//Convert the input string to a JSON object
	 JsonObject billObject = new JsonParser().parse(billData).getAsJsonObject();
	//Read the values from the JSON object
	 String BillId = billObject.get("BillId").getAsString();
	 String AccountNo = billObject.get("AccountNo").getAsString();
	 String Month = billObject.get("Month").getAsString();
	 String UnitConsumed = billObject.get("UnitConsumed").getAsString();
	 String UnitPrice = billObject.get("UnitPrice").getAsString();
	 String TotalAmount=billObject.get("TotalAmount").getAsString();
	 String output = billObj.updateBill(BillId, AccountNo, Month, UnitConsumed, UnitPrice,TotalAmount);
	return output;
	}
	
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBill(String billData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(billData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String BillId = doc.select("BillId").text();
	 String output = billObj.deleteBill(BillId);
	return output;
	}

	
	
}
