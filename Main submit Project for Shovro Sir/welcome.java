
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class welcome extends JFrame {
             
             JButton b;
             public welcome(){
b=new  JButton("Welcome To PhoneBoook");
             	 b.setForeground(Color.magenta);
             	 b.setFont(new Font("Serif", Font.PLAIN, 24));
             	 
             	b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	PhoneBook pb=new PhoneBook();
                    	pb.setVisible(true);
                    	this.setVisible(false);
                    }
		    private void setVisible(boolean b) {
					}  
             	});
            	 this.setLayout(new BorderLayout()); 
            	 int x=100,y=120,h=300,w=35;
            	 b.setBounds(x,y,h,w);
            	 
            	
            	 
            	 this.setContentPane(new JLabel(new ImageIcon("island.JPG")));
            	 this.add(b);
     	    	this.setSize(540,320);
     	    	this.setVisible(true);
     	    	this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);  
             }
         
}
