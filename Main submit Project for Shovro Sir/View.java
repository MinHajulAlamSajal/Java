
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JLabel;

   public class View extends JFrame{
						JLabel lname,lnumber,lemail,lphoto;
					
					    JLabel dName,dNumber,dEmail;

						public View(){
						 lname=new JLabel("Name : ");
						 lnumber=new JLabel("Number : ");
						 lemail=new JLabel("E-mail : ");
						 try {
	                		 Connection con;
	                		 Statement stmt;
	                         Class.forName("oracle.jdbc.driver.OracleDriver");
	                         con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1520:XE","JAVA1","oracle");
	                         stmt = con.createStatement();
	                         
	                         dName=new JLabel();
	                         
	                        
							 String query = "SELECT name FROM phonebook1 ";
							  System.out.println(query);
		                         System.out.println(stmt.executeUpdate(query));
		                   
							  ResultSet rs = stmt.executeQuery(query);
							
							  while(rs.next()){
					                System.out.println(rs.getString(1) );
								 dName.setText(rs.getString(1));
								 
							}
							  dNumber=new JLabel();
								 String query1 = "SELECT Contact_Number FROM phonebook1 ";
								 ResultSet rs1 = stmt.executeQuery(query1);
								  while(rs1.next()){
						               System.out.println(rs1.getString(1) );
						                dNumber.setText(rs1.getString(1));
									 
								}
						   dEmail=new JLabel();
						   String query2 = "SELECT email FROM phonebook1 ";
						   ResultSet rs2 = stmt.executeQuery(query2);
						   while(rs2.next()){
				              System.out.println(rs2.getString(1) );
				                dEmail.setText(rs2.getString(1));
							 
						}
								  
	                       
	                         
	                     } catch (Exception ex) {
	                          ex.printStackTrace();
	                     }
						 
						 
					 lphoto=new JLabel("Photo : ");
						 
						 lname.setForeground(Color.green);
						 lname.setFont(new Font("Serif", Font.PLAIN, 20));
						 lnumber.setForeground(Color.cyan);
						 lnumber.setFont(new Font("Serif", Font.PLAIN, 20));
						 lemail.setForeground(Color.pink);
						 lemail.setFont(new Font("Serif", Font.PLAIN, 20));
						
						 
						 int x=40,y=20,h=200,w=20;
						 lname.setBounds(x, y, h, w);
						 dName.setBounds(x+20,y+30, h, w);
						 lnumber.setBounds(x,y+60, h, w);
						 dNumber.setBounds(x+20,y+90, h, w);
						 lemail.setBounds(x,y+120, h, w);
						 dEmail.setBounds(x+20,y+150, h, w);
						
						 
						 this.add(lname);
						 this.add(lnumber);
						 this.add(lemail);
						
						 this.add(dName);
						 this.add(dNumber);
						 this.add(dEmail);
						
						 this.getContentPane().setBackground(Color.gray);
						
							this.setSize(700,300);
							this.setLayout(null);
							this.setVisible(true);
							 
						}


			   
}

