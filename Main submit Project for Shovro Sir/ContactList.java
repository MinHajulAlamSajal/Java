
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;
import java.sql.ResultSet;
import java.util.Vector;

public class ContactList extends JFrame{

    JTable phoneBookTable;
    JTextField t;
    JLabel dbImageLabel;
			       public ContactList(){
			    	   t=new JTextField();
			    	   dbImageLabel=new JLabel();
			    			            Vector data = new Vector();
			            try {
			            	Connection con;
			            	Statement stmt;
			                Class.forName("oracle.jdbc.driver.OracleDriver");
			                 con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1520:XE","JAVA1","oracle");
			                 stmt = con.createStatement();
			                                        
			                String query = "SELECT * FROM phonebook1";           
			                ResultSet rs = stmt.executeQuery(query);             
			                                       
			                while(rs.next()){
			                    //Preparing the row
			                    Vector row = new Vector();
			                    row.add(rs.getString("id"));
			                    row.add(rs.getString("Contact_Number"));
			                    row.add(rs.getString("name"));
			                    row.add(rs.getString("email"));
			                    row.add(rs.getBytes("image"));
			                    
			                    //adding row to data
			                    data.add(row);
			                }
			                
			            } catch (Exception ex) {
			                JOptionPane.showMessageDialog(this, ex);
			            }
			            
			            //Preparing the columns
			            Vector columns = new Vector();
			            columns.add("ID");
			            columns.add("Contact_Number");
			            columns.add("NAME");
			            columns.add("EMAIL");
			            columns.add("IMAGE");
			            
			            phoneBookTable = new JTable(data, columns);
			            phoneBookTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			                public void valueChanged(ListSelectionEvent e) {
			                    if(phoneBookTable.getSelectedRowCount()==1){ //Only for single selection
			                        int selectedRow = phoneBookTable.getSelectedRow();
			    		
			                        String id = phoneBookTable.getValueAt(selectedRow, 0).toString();
			                        
			                        t.setText(id);
			                        try {
			                            Class.forName("oracle.jdbc.driver.OracleDriver");

			                             Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1520:XE","JAVA1","oracle");
			                             Statement stmt = con.createStatement();
			                             
			                             String query = "SELECT image FROM phonebook1 where id ='"+id+"'";
			                             ResultSet rs = stmt.executeQuery(query);
			                             
			                             if(rs.next()){
			                                 byte[] imageInByte = rs.getBytes("image");
			                                 InputStream in = new ByteArrayInputStream(imageInByte);
			                                 BufferedImage retrievedImage = ImageIO.read(in);
			                                 
			                                 ImageIcon icon = new ImageIcon(retrievedImage);
			                                
			                                 dbImageLabel.setIcon(icon);  
			                                 dbImageLabel.setText("Showing from Database");
			                                 dbImageLabel.setForeground(Color.magenta);
			                                 dbImageLabel.setFont(new Font("Serif", Font.PLAIN, 20));
			                             }
			                             
			                             
			                             con.close();
			                         } catch (Exception ex) {
			                             ex.printStackTrace();
			                         }    
			                    }
			                }
		            }); 
			            JScrollPane pnl = new JScrollPane(phoneBookTable);
			           
			            this.getContentPane().add(pnl);
			            this.getContentPane().add(t);
			            this.getContentPane().add(dbImageLabel);
			            this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
			            
			            this.setSize(750, 500);
			            this.setVisible(true);
			           
		    }
}
