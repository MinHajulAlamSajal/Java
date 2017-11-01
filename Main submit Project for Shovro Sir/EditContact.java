
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

public class EditContact extends JFrame implements ActionListener{

	JTable phoneBookTable;
	 JTextField idTextField;
	 JTextField contactTextField;
	    JTextField nameTextField;    
	    JTextField emailTextField;
	    Connection con=null;
		Statement stmt = null;
		JButton b1;
		JLabel l;
		public EditContact(){
			 Vector data = new Vector();
	            idTextField = new JTextField();
	            contactTextField = new JTextField();
	            nameTextField = new JTextField(); 
	            emailTextField = new JTextField();
	            l=new JLabel("Edit");
	            b1=new JButton("SAVE");
	          
					try {
							
							Class.forName("oracle.jdbc.driver.OracleDriver");
							 con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1520:XE","JAVA1","oracle");
							 stmt = con.createStatement();
							 String query = "SELECT id,Contact_Number, name, email,image FROM phonebook1";           
				                ResultSet rs = stmt.executeQuery(query);             
				                                       
				                while(rs.next()){
				                    
				                    Vector row = new Vector();
				                    row.add(rs.getString("id"));
				                    row.add(rs.getString("Contact_Number"));
				                    row.add(rs.getString("name"));
				                    row.add(rs.getString("email"));
				                     row.add(rs.getBytes("image"));
				                    data.add(row);
				                }
				                
				            } catch (Exception ex) {
				                JOptionPane.showMessageDialog(this, ex);
				            }
				            
				           
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
				                        String contact = phoneBookTable.getValueAt(selectedRow, 1).toString();
				                        String name = phoneBookTable.getValueAt(selectedRow, 2).toString();            
				                        String email = phoneBookTable.getValueAt(selectedRow, 3).toString();
				                        
				                        idTextField.setText(id);
				                        contactTextField.setText(contact);
				                        nameTextField.setText(name); 
				                        emailTextField.setText(email);
				                    }
				                }        	
				            });   
				           
				            b1.addActionListener(this);
				            this.getContentPane().add(l);
				            JScrollPane pnl = new JScrollPane(phoneBookTable);
				            this.getContentPane().add(idTextField);		          
				            this.getContentPane().add(contactTextField);
				            this.getContentPane().add(nameTextField);        
				            this.getContentPane().add(emailTextField);         
				            this.getContentPane().add(pnl);
				            this.getContentPane().add(b1);    
				            
				           
						  this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
						  
						  this.setSize(750, 300);
						  this.setVisible(true);  
				  }
		
		public void actionPerformed(ActionEvent e) {
			String query="UPDATE phonebook SET Contact_Number='"+contactTextField.getText() +"',name='"+nameTextField.getText() +"',email='"+emailTextField.getText() +"' WHERE id='"+idTextField.getText() +"'";			         
	        System.out.println(query);     
	        try{
	        	System.out.println(stmt.executeQuery(query));
	        }catch  (Exception ex) {
	        	ex.printStackTrace();
	        }
		}
	}

