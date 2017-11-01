

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
public class Delete  extends JFrame implements ActionListener{
	 JTable phoneBookTable;
	 JTextField idTextField;
	 JTextField contactTextField;
	    JTextField nameTextField;    
	    JTextField emailTextField;
	    Connection con=null;
		Statement stmt = null;
		JButton b,b1;
		JLabel l;
		JTextField t;
		public Delete(){
			 Vector data = new Vector();
	            idTextField = new JTextField();
	            contactTextField = new JTextField();
	            nameTextField = new JTextField(); 
	            emailTextField = new JTextField();
	            b=new JButton("delete");
	  	     l=new JLabel("Selected id to be deleted: ");
	  	     t=new JTextField();
					try {
							
							Class.forName("oracle.jdbc.driver.OracleDriver");
							 con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1520:XE","JAVA1","oracle");
							 stmt = con.createStatement();
							 String query = "SELECT id,Contact_Number, name, email,image FROM phonebook1";           
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
				                        
				                    }
				                }        	
				            });  
	 					b.addActionListener(this);
	 					JScrollPane pnl = new  JScrollPane(phoneBookTable);

	 				    	this.getContentPane().add(pnl);
				            this.getContentPane().add(l);
				            this.getContentPane().add(t);
				            this.getContentPane().add(b);
				           
				            this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
						  
				            this.setSize(750,300);
				            this.setVisible(true);  
		}

		public void actionPerformed(ActionEvent e) {
			String query = "delete from phonebook1 where id = '"+t.getText()+"'";
			        System.out.println(query);
			        try {
						ResultSet rs = stmt.executeQuery(query);
						System.out.println(stmt.executeQuery(query));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
		}
	}


