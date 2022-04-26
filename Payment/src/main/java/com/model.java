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
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/world", "root", "123Aruna"); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 
	public String insertPayment(String CardType, String CardNum, String ExpiryDate, String CVC) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting payment"; } 
	 // create a prepared statement
	 String query = " insert into payment (`PaymentId`,`CardType`,`CardNum`,`ExpiryDate`,`CVC`)"
	 + " values (?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, CardType); 
	 preparedStmt.setString(3, CardNum); 
	 preparedStmt.setString(4, ExpiryDate); 
	 preparedStmt.setString(5, CVC); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while inserting the Payment Details"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	public String readPayment() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Payment ID</th><th>Card Type</th>" +
	 "<th>Card Number</th>" + 
	 "<th>Expiry Date</th>" +
	 "<th>CVC</th>"+
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from payment"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String PaymentId = Integer.toString(rs.getInt("PaymentId")); 
	 String CardType = rs.getString("CardType"); 
	 String CardNum = rs.getString("CardNum"); 
	 String ExpiryDate = rs.getString("ExpiryDate"); 
	 String CVC = rs.getString("CVC"); 
	 // Add into the html table
	 output += "<tr><td>" + PaymentId + "</td>"; 
	 output += "<td>" + CardType + "</td>"; 
	 output += "<td>" + CardNum + "</td>"; 
	 output += "<td>" + ExpiryDate + "</td>";
	 output += "<td>" + CVC + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='items.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='itemID' type='hidden' value='" + PaymentId 
	 + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the Payment Details"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	public String updatePayment(String PaymentId, String CardType, String CardNum, String ExpiryDate, String CVC)
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE payment SET CardType=?,CardNum=?,ExpiryDate=?,CVC=? WHERE PaymentId=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, CardType); 
		 preparedStmt.setString(2, CardNum); 
		 preparedStmt.setString(3, ExpiryDate); 
		 preparedStmt.setString(4, CVC); 
		 preparedStmt.setInt(5, Integer.parseInt(PaymentId)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while updating the Payment Details"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
	public String deletePayment(String PaymentId) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from payment where PaymentId=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(PaymentId)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the Payment Details"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }

}
