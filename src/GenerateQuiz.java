import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;

public class GenerateQuiz implements ActionListener{
	int i=1;
	JFrame frame;
	JPanel panel;
	JLabel lquestion,lo1,lo2,lo3,lo4,lanswer;
	JTextField tquestion,to1,to2,to3,to4;
	JComboBox<String> canswer;
	JButton bnext;
	GenerateQuiz()
	{
		frame=new JFrame("Quiz Developing Window");
		panel=new JPanel();
		tquestion=new JTextField(40);
		to1=new JTextField(10);
		to2=new JTextField(10);
		to3=new JTextField(10);
		to4=new JTextField(10);
		canswer=new JComboBox<>();
		lquestion=new JLabel("Write Question: ");
		lo1=new JLabel("Enter 1st option: ");
		lo2=new JLabel("Enter 2st option: ");
		lo3=new JLabel("Enter 3st option: ");
		lo4=new JLabel("Enter 4st option: ");
		lanswer=new JLabel("Answer : ");
		bnext=new JButton("next");
		bnext.addActionListener(this);
		canswer.addItem("A");
		canswer.addItem("B");
		canswer.addItem("C");
		canswer.addItem("D");
		panel.add(lquestion);
		panel.add(tquestion);
		panel.add(lo1);
		panel.add(to1);
		panel.add(lo2);
		panel.add(to2);
		panel.add(lo3);
		panel.add(to3);
		panel.add(lo4);
		panel.add(to4);
		panel.add(lanswer);
		panel.add(canswer);
		panel.add(bnext);
		frame.add(panel);
		frame.setSize(500,500);
		frame.setVisible(true);
		bnext.setText("next");
	}

	GenerateQuiz(int a)
	{
		frame=new JFrame("Quiz Developing Window");
		panel=new JPanel();
		tquestion=new JTextField(40);
		to1=new JTextField(10);
		to2=new JTextField(10);
		to3=new JTextField(10);
		to4=new JTextField(10);
		canswer=new JComboBox<>();
		lquestion=new JLabel("Write Question: ");
		lo1=new JLabel("Enter 1st option: ");
		lo2=new JLabel("Enter 2st option: ");
		lo3=new JLabel("Enter 3st option: ");
		lo4=new JLabel("Enter 4st option: ");
		lanswer=new JLabel("Answer : ");
		bnext=new JButton("Finish");
		bnext.addActionListener(this);
		canswer.addItem("A");
		canswer.addItem("B");
		canswer.addItem("C");
		canswer.addItem("D");
		panel.add(lquestion);
		panel.add(tquestion);
		panel.add(lo1);
		panel.add(to1);
		panel.add(lo2);
		panel.add(to2);
		panel.add(lo3);
		panel.add(to3);
		panel.add(lo4);
		panel.add(to4);
		panel.add(lanswer);
		panel.add(canswer);
		panel.add(bnext);
		frame.add(panel);
		frame.setSize(500,500);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new GenerateQuiz();
	}

	@Override
	public void actionPerformed(ActionEvent obj) {
		// TODO Auto-generated method stub
		String dquestion="",do1="",do2="",do3="",do4="";
		String ans= (String) canswer.getSelectedItem();
			dquestion=tquestion.getText();
			do1=to1.getText();
			do2=to2.getText();
			do3=to3.getText();
			do4=to4.getText();
			i++;
			if(obj.getSource()==bnext)
			{
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "vinithande4", "Swarali123");
					PreparedStatement pr=con.prepareStatement("insert into quizn values(?,?,?,?,?,?)");
					System.out.println("Connection Succuess ....");
					pr.setString(1, dquestion);
					pr.setString(2, do1);
					pr.setString(3, do2);
					pr.setString(4, do3);
					pr.setString(5, do4);
					pr.setString(6,ans);
					pr.execute();
					JOptionPane.showMessageDialog(frame,"Question Added");
					frame.dispose();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}	
		}
}
