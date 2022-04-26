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
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/world", "root", "!123Aloka"); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 
	public String insertUser(String AccountNo, String Name, String Address, String NIC, String TelNo) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into user (`CID`,`AccountNo`,`Name`,`Address`,`NIC`, `TelNo`)"
	 + " values (?, ?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, AccountNo); 
	 preparedStmt.setString(3, Name); 
	 preparedStmt.setString(4, Address); 
	 preparedStmt.setString(5, NIC);
	 preparedStmt.setString(6, TelNo);
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while inserting the User"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	
	public String readUser() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Customer ID</th><th>Account No</th>" +
	 "<th>Name</th>" + 
	 "<th>Address</th>" +
	 "<th>NIC</th>" +
	 "<th>TelNo</th>" +
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from user"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String CID = Integer.toString(rs.getInt("CID")); 
	 String AccountNo = rs.getString("AccountNo"); 
	 String Name = rs.getString("Name"); 
	 String Address = rs.getString("Address"); 
	 String NIC = rs.getString("NIC");
	 String TelNo = rs.getString("TelNo");
	 // Add into the html table
	 output += "<tr><td>" + CID + "</td>"; 
	 output += "<td>" + AccountNo + "</td>"; 
	 output += "<td>" + Name + "</td>"; 
	 output += "<td>" + Address + "</td>";
	 output += "<td>" + NIC + "</td>";
	 output += "<td>" + TelNo + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='items.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='itemID' type='hidden' value='" + CID
	 + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 

	public String updateUser(String CID, String AccountNo, String Name, String Address, String NIC, String TelNo)
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE user SET AccountNo=?,Name=?,Address=?,NIC=?, TelNo=? WHERE CID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, AccountNo); 
		 preparedStmt.setString(2, Name); 
		 preparedStmt.setString(3, Address); 
		 preparedStmt.setString(4, NIC);
		 preparedStmt.setString(5, TelNo);
		 preparedStmt.setInt(6, Integer.parseInt(CID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while updating the item."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 }
	
	public String deleteUser(String CID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from user where CID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(CID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the User"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
}
