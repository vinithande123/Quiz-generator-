import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Ui implements ActionListener{

	JFrame frame;
	JPanel panel;
	JTextField tusername;
	JPasswordField Jpassword;
	JLabel luser,lpass;
	JButton button,createuserbutton;
	
	Ui(){
		frame=new JFrame("Login Window");
		panel=new JPanel();
		tusername=new JTextField(10);
		Jpassword=new JPasswordField(10);
 		luser=new JLabel("Username: ");
		lpass=new JLabel("Password: ");
		button=new JButton("Login");
		button.addActionListener(this);
		createuserbutton=new JButton("Sign up");
		createuserbutton.addActionListener(this);
		panel.add(luser);
		panel.add(tusername);
		panel.add(lpass);
		panel.add(Jpassword);
		panel.add(button);
		panel.add(createuserbutton);
		frame.add(panel);
		frame.setSize(250,250);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Ui();
	}
	@Override
	public void actionPerformed(ActionEvent obj) {
		// TODO Auto-generated method stub
		String username=null,password=null;
		if(obj.getSource()==button) {
			username=tusername.getText();
			password= String.valueOf(Jpassword.getPassword());
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int flag=0;
			try {
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "vinithande4", "Swarali123");
				System.out.println("Connection Success ...");
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select * from form");
				
				while(rs.next()){
					if(username.equals(rs.getString(1))) {
						if(password.equals(rs.getString(2))) {
							JOptionPane.showMessageDialog(frame, "Login successful!!");
							frame.dispose();
							new QuizIndex();
							flag=0;
							break;
						}
						else
							JOptionPane.showMessageDialog(frame, "Password invalid Try again!!");
					}
					flag=1;
				}
				if(flag==1)
					JOptionPane.showMessageDialog(frame, "Username invalid Try again!!");
				flag=0;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(obj.getSource()==createuserbutton) {
			JOptionPane.showMessageDialog(frame, "Welcome to sign up page!!");
			frame.dispose();
			new Ui1();
		}
	}
	
}
/*if(obj.getSource()==button){
if(tusername.getText().equals("xyz")){
if(Jpassword.getText().equals("xyz")){
	System.out.println("User authorised");
	frame.dispose();
	new Ui1();
}
else{
	System.out.println("password is wrongs");
}
}
else
System.out.println("Username is wrong");
}*/