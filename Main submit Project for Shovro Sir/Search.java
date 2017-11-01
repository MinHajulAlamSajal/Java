
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
public class Search  extends JFrame {
	  
	Connection con;
	Statement stmt;
	Vector data = new Vector();
	Vector data1 = new Vector();
	 JTable phoneBookTable,phoneBookTable1;
	 JTextField t;
	 JTextArea ta;
	 JButton b,b1;
				public Search(){
					 t=new JTextField("");
			            b1=new JButton("SEARCH");
			            this.getContentPane().add(t);
			            this.getContentPane().add(b1);
			            b1.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent arg0) {
								JFrame f=new JFrame("Showing Search Component");
								try{
									   Class.forName("oracle.jdbc.driver.OracleDriver");
						                 con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1520:XE","JAVA1","oracle");
						                 stmt = con.createStatement();
								//	String query = "SELECT * FROM phonebook WHERE  name like  '%"+t.getText()+"%'";
							//		SELECT * FROM Customers  WHERE Country NOT LIKE '%land%';
						                 String query1 = "SELECT id,Contact_Number,name,email,image FROM phonebook1 WHERE  name like  '"+t.getText()+"%'";
									System.out.println(query1);     
							       
							        	System.out.println(stmt.executeQuery(query1));
							        	System.out.println(stmt.executeUpdate(query1));
							        ResultSet rs = stmt.executeQuery(query1);             
			                        
					                while(rs.next()){
					                    //Preparing the row
					                    Vector row = new Vector();
					                    row.add(rs.getString("id"));
					                    row.add(rs.getString("Contact_Number"));
					                    row.add(rs.getString("name"));
					                    row.add(rs.getString("email"));
					                    row.add(rs.getBytes("image"));
					                    //adding row to data
					                    data1.add(row);
					                }
					                
					            } catch (Exception ex) {
					                JOptionPane.showMessageDialog(f, ex);
					            }
					            
					            //Preparing the columns
					            Vector columns = new Vector();
					            columns.add("ID");
					            columns.add("Contact_Number");
					            columns.add("NAME");
					            columns.add("EMAIL");
					            columns.add("IMAGE");
					            phoneBookTable = new JTable(data1, columns);
					          
					           
					            JScrollPane pnl1 = new JScrollPane(phoneBookTable);		
					           
					            f.add(pnl1);

					            f.setLayout(new BoxLayout(f.getContentPane(),BoxLayout.Y_AXIS));
								
								f.setSize(500,350);
								f.setVisible(true);
							}
	            	
			            });
		           		try{
						   Class.forName("oracle.jdbc.driver.OracleDriver");
			                 con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1520:XE","JAVA1","oracle");
			                 stmt = con.createStatement();
				
			                 String query = "SELECT id,Contact_Number,name,email,image FROM phonebook1 ";
						System.out.println(query);     
				       
				        	System.out.println(stmt.executeQuery(query));
				        	System.out.println(stmt.executeUpdate(query));
				        ResultSet rs1 = stmt.executeQuery(query);             
                        
		                while(rs1.next()){
		                    //Preparing the row
		                    Vector row1 = new Vector();
		                    row1.add(rs1.getString("id"));
		                    row1.add(rs1.getString("Contact_Number"));
		                    row1.add(rs1.getString("name"));
		                    row1.add(rs1.getString("email"));
		                    row1.add(rs1.getBytes("image"));
		                    //adding row to data
		                    data.add(row1);
		                }
		                
		            } catch (Exception ex) {
		                JOptionPane.showMessageDialog(this, ex);
		            }
		            
		            //Preparing the columns
		            Vector columns1 = new Vector();
		            columns1.add("ID");
		            columns1.add("Contact_Number");
		            columns1.add("NAME");
		            columns1.add("EMAIL");
		            columns1.add("IMAGE");
		            phoneBookTable1 = new JTable(data, columns1);
		          
		           
		            JScrollPane pnl = new JScrollPane(phoneBookTable1);			           
		            this.getContentPane().add(pnl);

		            this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		            setSize(500,350);
		            setVisible(true);
		            
				  }
		
}
