import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CheckAnswer implements ActionListener {
    JFrame frame;
    JPanel panel;
    JLabel lscore;
    JButton bdone;
    CheckAnswer()
    {
        frame=new JFrame("Result");
        panel=new JPanel();
        lscore=new JLabel("Your Score : "+check());
        bdone=new JButton("Done");
        bdone.addActionListener(this);
        panel.add(lscore);
        panel.add(bdone);
        frame.add(panel);
        frame.setSize(250,250);
        frame.setVisible(true);
    }
    int check()
    {
        int count=0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "vinithande4", "Swarali123");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from quizn");
            Statement stmt1=con.createStatement();
            ResultSet rs1=stmt1.executeQuery("select * from answers");
            String option,answer;
            while(rs.next()&&rs1.next())
            {
                option=rs.getString(6);
                answer=rs1.getString(1);
                System.out.println(option+" "+answer);
                //Compare Quiz Answers and selected option
                if(option.equals(answer))
                    count++;
                System.out.println(count);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connection Succuess ....");
        return count;
    }

    public static void main(String[] args) {
        new CheckAnswer();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bdone)
            frame.dispose();
    }
}
