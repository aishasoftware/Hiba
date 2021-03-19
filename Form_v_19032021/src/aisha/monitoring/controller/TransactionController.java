package aisha.monitoring.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;

import aisha.bean.Application;
import aisha.monitoring.bean.GmppTransaction;


@Controller
@RequestMapping(value="/TransactionController")
public class TransactionController {

	
	 @RequestMapping(value= "/getTransactions", method = RequestMethod.GET)
	 public String viewSubmittedApp(HttpServletRequest request,Model model) throws ParserConfigurationException, SAXException, IOException{
		 ArrayList<String> searchFields = GmppTransaction.getSearchFields();
		 HashMap<String, Object> criteria = new HashMap<>();
		 HashMap<String, Object> dateFilter = new HashMap<>();
		 GmppTransaction searchGmpp = new GmppTransaction();
		 GmppTransaction searchResultGmpp = new GmppTransaction();
	     System.out.println("#### searchFields GmppTransaction : "+searchFields);
	    // dateFilter.put("fromDate", new Date());
	     //dateFilter.put("toDate", new Date());
	     for(int i=0;i<searchFields.size();i++)
	     {
if(searchFields.get(i).equals("fromDate") || searchFields.get(i).equals("toDate"))
	{
	if(request.getParameter(searchFields.get(i)) != null)
	
{
		dateFilter.put(searchFields.get(i), request.getParameter(searchFields.get(i)));

}
	}
if(!searchFields.get(i).equals("fromDate") && !searchFields.get(i).equals("toDate") && request.getParameter(searchFields.get(i)) != null )
criteria.put(searchFields.get(i), request.getParameter(searchFields.get(i)));
	    	if(!dateFilter.isEmpty())
	    		criteria.put("creationTime",dateFilter );
	     }
	     
	     searchGmpp.setSearchCriteria(criteria);
	     searchResultGmpp = queryGmppDB(searchGmpp);
	     model.addAttribute("controllerName","TransactionController");
	    
	
		
		//model.addAttribute("tranList",  searchResultGmpp.get);
		model.addAttribute("tableFields", GmppTransaction.getTableFields());
	 	
	 	model.addAttribute("bean",null  );
	 	model.addAttribute("beanName", "ApplicationTemplate" );
	 	
	 	return "GMPP_getTransactions";
	 	}
	 
	 
	 
	 public GmppTransaction queryGmppDB(GmppTransaction tran)
	 {
		   // JDBC driver name and database URL
		   final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";  
		   final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";

		   //  Database credentials
		   final String USER = "system";
		   final String PASS = "Aisha123";
		   

		   Connection conn = null;
		   Statement stmt = null;
		   
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("oracle.jdbc.OracleDriver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();

		      String sql1 = "SELECT count(id) as count_id,full_name from talents group by full_name";
		    /*  String sql2 = "SELECT count(id),full_name from talents group by full_name";
		      String sql3 = "SELECT count(id),full_name from talents group by full_name";
		      String sql4 = "SELECT count(id),full_name from talents group by full_name";*/
		      ResultSet rs1 = stmt.executeQuery(sql1);
	/*	      ResultSet rs2 = stmt.executeQuery(sql2);
		      ResultSet rs3 = stmt.executeQuery(sql3);
		      ResultSet rs4 = stmt.executeQuery(sql4);*/
		      //STEP 5: Extract data from result set
		      while(rs1.next()){
		         //Retrieve by column name
		         int id  = rs1.getInt("count_id");
		        // int age = rs1.getInt("age");
		         String full_name = rs1.getString("full_name");
		         //String last = rs.getString("last");
		         System.out.print("count_id: " + id);
		         System.out.print(", full_name: " + full_name);
		         //Display values
	/*	         System.out.print("ID: " + id);
		         System.out.print(", Age: " + age);
		         System.out.print(", First: " + first);
		         System.out.println(", Last: " + last);*/
		      }
		      rs1.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
		return tran;
		}//end main



}