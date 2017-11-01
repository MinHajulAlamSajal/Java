

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.sql.*;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.sql.*;
//import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
 class AddContact extends JFrame {
             JLabel l,contact,name,email,id;
             JTextField contact1,name1,email1,id1;
             JButton b,b1,b2;
             JButton back;
             JButton chooseButton;
             JLabel choosenImageLabel;
             BufferedImage choosenImage;

             JButton saveButton;
            
              
             JLabel dbImageLabel;
             JButton showButton;
             Connection con;
    		 Statement stmt;
        AddContact(){
        	
        	l=new JLabel("Add New Contact");
        	l.setForeground(Color.green);
        	l.setFont(new Font("Serif", Font.PLAIN, 24));
        	
        	id=new JLabel("Id   ");
        	contact =new JLabel("Contact Number  ");
        	name =new JLabel("Name  ");
        	email =new JLabel("E-mail  ");
        	id1=new JTextField();
        	contact1=new JTextField();
        	name1=new JTextField();
        	email1=new JTextField();
        	
        	b=new JButton("Save");
        	b1=new JButton("Clear");
        	b2=new JButton("View");
        	
        	choosenImageLabel = new JLabel();        
			chooseButton = new JButton("Choose");
			chooseButton.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
				//Choosing the image
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File file = chooser.getSelectedFile();                

				if (file != null) {
				    try {
					//Reading the choosen file as buffered image
					choosenImage = ImageIO.read(file);
					
				       //Scaling the original image (ignore if you don't want to scale) -----
					double scalingRatio = (double)choosenImage.getHeight()/choosenImage.getWidth();
					int scaledWidth = 150; //Change according to your need
					int scaledHeight = (int)(scaledWidth*scalingRatio);
					BufferedImage scaledImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
					Graphics g = scaledImage.createGraphics();
					g.drawImage(choosenImage, 0, 0, scaledWidth, scaledHeight, null);
					g.dispose();             
					choosenImage = scaledImage;              
					//----------------------------------------------------------------------                        
				    
					//Displaying the choosen image as a label icon
					ImageIcon icon = new ImageIcon(choosenImage);
					choosenImageLabel.setIcon(icon);        
//choosenImageLabel.setText("Showing the Selected");
				} catch (Exception ex) {
				ex.printStackTrace();
			   }
				 }
			    }
			});
            
        	
        	b.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                	try {
                		 
                		
                         Class.forName("oracle.jdbc.driver.OracleDriver");
                         con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1520:XE","JAVA1","oracle");
                      //   stmt = con.createStatement();
                         PreparedStatement ps = con.prepareStatement("INSERT INTO phonebook1 (id,Contact_Number, name, email,image)  VALUES(?,?,?,?,?)");  
                         ByteArrayOutputStream os = new ByteArrayOutputStream();
                         ImageIO.write(choosenImage, "jpg", os);
                         byte[] imageInByte = os.toByteArray();  
                         String id=id1.getText();
                         String contact=contact1.getText();
                         String name = name1.getText();
                         String email = email1.getText();  
                         
                         ps.setString(1,id);
                         ps.setString(2,contact);
                         ps.setString(3,name);
                         ps.setString(4,email);
                         ps.setBytes(5,imageInByte);
                         //Executing the statement
                         ps.executeUpdate();
                         con.close();                     
                      //  System.out.println(query);
                      //   System.out.println(stmt.executeUpdate(query));
                
                     } catch (Exception ex) {
                          ex.printStackTrace();
                     }
        	     }
        	 });
        	
        	 b1.addActionListener(new ActionListener(){
       		      public void actionPerformed(ActionEvent e) {
       			        contact1.setText("");
       				    name1.setText("");
       				    email1.setText("");        			
       		         }
         	});

        	b2.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                	View v=new View();
	                	v.setVisible(true);
	                //	this.setVisble(false);
	                }

					
        	});  
        	
        	dbImageLabel = new JLabel();        
            showButton = new JButton("SHOW");
            showButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                       Class.forName("oracle.jdbc.driver.OracleDriver");

                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1520:XE","JAVA1","oracle");
                        Statement stmt = con.createStatement();
                        
                        String query = "SELECT image FROM phonebook1 where id ='"+id1.getText()+"'";
                        ResultSet rs = stmt.executeQuery(query);
                        
                        if(rs.next()){
                            byte[] imageInByte = rs.getBytes("image");
                            InputStream in = new ByteArrayInputStream(imageInByte);
                            BufferedImage retrievedImage = ImageIO.read(in);
                            
                            ImageIcon icon = new ImageIcon(retrievedImage);
                           
                            dbImageLabel.setIcon(icon);  
                            dbImageLabel.setText("Showing from Database");
                            dbImageLabel.setForeground(Color.cyan);
                            dbImageLabel.setFont(new Font("Serif", Font.PLAIN, 20));
                        }
                        
                        
                        con.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }                
                }
            });
        	
        	int x=10,y=20,h=100,w=20;
        	
        	l.setBounds(x, y, h+110, w);
        	id.setBounds(x+20,y+30, h, w);
        	contact.setBounds(x+20,y+60, h, w);
        	name.setBounds(x+20,y+90, h, w);
        	email.setBounds(x+20,y+120, h, w);
        	id1.setBounds(x+120, y+30, h+60, w);
         	contact1.setBounds(x+120,y+60, h+60, w);
        	name1.setBounds(x+120,y+90, h+60, w);
        	email1.setBounds(x+120,y+120, h+60, w);
        	
        	choosenImageLabel.setBounds(x+20,y+160, h+40, w+140);
        	chooseButton.setBounds(x+20,y+350, h, w);
        	b.setBounds(x+20,y+390, h, w);        b1.setBounds(x+140,y+390, h, w);   b2.setBounds(x+260,y+390, h, w);  showButton.setBounds(x+380,y+390, h, w);
        //	showButton.setBounds(x+20,y+430, h, w);
        	dbImageLabel.setBounds(x+20,y+420, h, w+100);
        	
        	
        //	b.setBounds(x+20,y+170, h, w);
        //	b1.setBounds(x+140,y+170, h, w);
        //	b2.setBounds(x+260,y+170, h, w);
        	
        	
        	this.add(l);
        	this.add(id);
        	this.add(contact);
        	this.add(name);
        	this.add(email);
        	this.add(contact1);
        	this.add(name1);
        	this.add(email1);
        	this.add(id1);
        	this.add(choosenImageLabel);
        	this.add(chooseButton);
        	this.add(showButton);
        	this.add(dbImageLabel);
        	
        	this.add(b);
        	this.add(b1);
        	this.add(b2);
        	
        	this.getContentPane().setBackground(Color.gray);
        	this.setLayout(null);
        	this.setSize(740,700);
        	this.setVisible(true);
        	
        	
         }

     
	
 }	
