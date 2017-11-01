

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PhoneBook extends JFrame implements ActionListener{
	    JFrame f;
		JPanel pnl;
		JLabel l,l1;
	    JButton b,b1,b2,b3,b4;
	 int x=10,y=30,h=150,w=30;
	
	  public  PhoneBook(){
		  
	    	l=new JLabel("PhoneBook");
	    	l.setForeground(Color.magenta);
	    	l.setFont(new Font("Serif", Font.PLAIN, 24));
	    	
	    	b=new  JButton("     Contact List ",new ImageIcon("l.jpg"));
	    	b1=new  JButton("     Add Contact ",new ImageIcon("insert.png"));	
	    	b2=new  JButton("     Edit Contact ",new ImageIcon("edit.jpg"));
	    	b3=new  JButton("     Delete Contact ",new ImageIcon("delete.jpg"));
	    	b4=new  JButton("     Search Contact ",new ImageIcon("l.jpg"));
	    	  this.setLayout(new BorderLayout());
	    	  this.setContentPane(new JLabel(new ImageIcon("ph.JPG")));
	    	
	    	
	    	b.addActionListener(this);
	    	b1.addActionListener(this);
	        b2.addActionListener(this);
	        b3.addActionListener(this);
	        b4.addActionListener(this);
	    	l.setBounds(x,y,h,w);
	         b.setBounds(x+40,y+50,h+50,w+2);
	        b1.setBounds(x+40,y+100,h+50,w+2);
	        b2.setBounds(x+40,y+150,h+50,w+2);
	        b3.setBounds(x+40,y+200,h+50,w+2);
	        b4.setBounds(x+40,y+250,h+50,w+2);
	    	this.add(b);
	    	this.add(b1);
	    	this.add(b2);
	    	this.add(b3);
	    	this.add(b4);
	    	this.add(l);
	    	
	    	this.getContentPane().setBackground(Color.gray);
	    //	this.setLayout(null);
	    	this.setSize(640,420);
	    	this.setVisible(true);
	 
	  }   
		public void actionPerformed(ActionEvent e) {
					
					if(e.getSource()==b){
						ContactList cl =new ContactList();
			             cl.setVisible(true);
					 //  	this.setVisible(false);
						
					}
					else if(e.getSource()==b1){
						AddContact ac=new AddContact();
						ac.setVisible(true);
					//	this.setVisible(false);
					}
					
					else if(e.getSource()==b2){
						EditContact ec=new EditContact();
						ec.setVisible(true);
					//	this.setVisible(false);
					}
					else if(e.getSource()==b3){
						Delete d=new Delete();
						d.setVisible(true);
					//	this.setVisible(false);
					}
					else if(e.getSource()==b4){
						Search s=new Search();
						s.setVisible(true);
					//	this.setVisible(false);
					}
				}
		
}
