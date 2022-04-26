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
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/world", "root", "20001212@abc");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	public String insertBill(String AccountNo, String Month, String UnitConsumed, String UnitPrice, String TotalAmount)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into billing(`BillId`,`AccountNo`,`Month`,`UnitConsumed`,`UnitPrice`, `TotalAmount`)"
	 + " values (?, ?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, AccountNo);
	 preparedStmt.setString(3, Month);
	 preparedStmt.setString(4, UnitConsumed);
	 preparedStmt.setString(5, UnitPrice);
	 preparedStmt.setString(6, TotalAmount);	 

	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the Bill";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String readBill()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Bill ID</th><th>Account Number</th>" +
	 "<th>Month</th>" +
	 "<th>Unit Consumed</th>" +
	 "<th>Unit Price</>"+
	 "<th>Total Amount</th>"+
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from billing";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String BillId = Integer.toString(rs.getInt("BillId"));
	 String AccountNo = rs.getString("AccountNo");
	 String Month = rs.getString("Month");
	 String UnitConsumed = rs.getString("UnitConsumed");
	 String UnitPrice = rs.getString("UnitPrice");
	 String TotalAmount = rs.getString("TotalAmount");
	 // Add into the html table
	 output += "<tr><td>" + BillId + "</td>";
	 output += "<td>" + AccountNo + "</td>";
	 output += "<td>" + Month + "</td>";
	 output += "<td>" + UnitConsumed + "</td>";
	 output += "<td>" + UnitPrice + "</td>";
	 output += "<td>" + TotalAmount + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='items.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='itemID' type='hidden' value='" + BillId
	 + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the Bill";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String updateBill(String BillId, String AccountNo, String Month, String UnitConsumed, String UnitPrice, String TotalAmount)
	{
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE billing SET AccountNo=?,Month=?,UnitConsumed=?,UnitPrice=?, TotalAmount=? WHERE BillId=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, AccountNo);
		 preparedStmt.setString(2, Month);
		 preparedStmt.setString(3, UnitConsumed);
		 preparedStmt.setString(4, UnitPrice);
		 preparedStmt.setString(5, TotalAmount);
		 preparedStmt.setInt(6, Integer.parseInt(BillId));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the billing.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String deleteBill(String BillId)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from billing where BillId=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(BillId));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
}
