package com;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class model {
	
	
	
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.cj.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/world", "root", "kavinda@12345"); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 
	public String insertService(String AccountNo, String Address, String Inquiry, String Status, String TelNo) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into cusservice (`ServiceId`,`AccountNo`,`Address`,`Inquiry`,`Status`, `TelNo`)"
	 + " values (?, ?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, AccountNo); 
	 preparedStmt.setString(3, Address); 
	 preparedStmt.setString(4, Inquiry); 
	 preparedStmt.setString(5, Status);
	 preparedStmt.setString(6, TelNo);
	 // execute the statement
	 
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while inserting the Details"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	
	public String readService() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Service Id</th><th>AccountNo</th>"+
    "<th>Address</th>" +
	 "<th>Inquiry</th>" + 
	 "<th>Status</th>" +
	 "<th>TelNo</th>"+
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from cusservice"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String ServiceId = Integer.toString(rs.getInt("ServiceId")); 
	 String AccountNo = rs.getString("AccountNo"); 
	 String Address = rs.getString("Address"); 
	 String Inquiry = rs.getString("Inquiry");
	 String Status = rs.getString("Status");
	 String TelNo = rs.getString("TelNo"); 
	 // Add into the html table
	 output += "<tr><td>" + ServiceId + "</td>"; 
	 output += "<td>" + AccountNo + "</td>"; 
	 output += "<td>" + Address + "</td>"; 
	 output += "<td>" + Inquiry + "</td>";
	 output += "<td>" + Status + "</td>";
	 output += "<td>" + TelNo + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='items.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='itemID' type='hidden' value='" + ServiceId 
	 + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the Details"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }

	public String updateService(String ServiceId, String AccountNo, String Address, String Inquiry, String Status, String TelNo) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for updating."; } 
	 // create a prepared statement
	 String query = "UPDATE cusservice SET AccountNo=?, Address=?, Inquiry=?, Status=?, TelNo=? WHERE ServiceId=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setString(1, AccountNo); 
	 preparedStmt.setString(2, Address); 
	 preparedStmt.setString(3, Inquiry); 
	 preparedStmt.setString(4, Status); 
	 preparedStmt.setString(5, TelNo);
	 preparedStmt.setInt(6, Integer.parseInt(ServiceId));
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Updated successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while updating the details"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	public String deleteService(String ServiceId) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from cusservice where ServiceId=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(ServiceId)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the details"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }

}
