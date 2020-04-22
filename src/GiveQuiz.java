import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class GiveQuiz implements ActionListener {
    JFrame frame;
    JPanel panel;
    JLabel lquestion;
    JRadioButton ra,rb,rc,rd;
    ButtonGroup bg;
    JButton bnext;

    GiveQuiz(String que,String A,String B,String C,String D)
    {
        frame=new JFrame("Start Quiz");
        panel=new JPanel();
        lquestion=new JLabel(que);
        ra=new JRadioButton(A);
        rb=new JRadioButton(B);
        rc=new JRadioButton(C);
        rd=new JRadioButton(D);
        bg=new ButtonGroup();
        bnext=new JButton("Next");
        bnext.addActionListener(this);
        bg.add(ra);
        bg.add(rb);
        bg.add(rc);
        bg.add(rd);
        panel.add(lquestion);
        panel.add(ra);
        panel.add(rb);
        panel.add(rc);
        panel.add(rd);
        panel.add(bnext);
        frame.add(panel);
        frame.setSize(450,250);
        frame.setVisible(true);
        bnext.setText("next");

    }

    GiveQuiz(String que,String A,String B,String C,String D,int a)
    {
        this(que,A,B,C,D);
        System.out.println(a);
        bnext.setText("Finish");
    }
    /*GiveQuiz() throws SQLException, ClassNotFoundException {
        String a[]=new String[1];
        main(a);
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
       
    }*/

    @Override
    public void actionPerformed(ActionEvent e) {
    	 
        String ans=" ";
        if(ra.isSelected())
            ans="A";
        if(rb.isSelected())
            ans="B";
        if(rc.isSelected())
            ans="C";
        if(rd.isSelected())
            ans="D";
        System.out.println("Your Answer : "+ans);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "vinithande4", "Swarali123");
            System.out.println("Connection Succuess ....");
            PreparedStatement pst=con.prepareStatement("insert into answers values (?)");
            pst.setString(1,ans);
            pst.execute();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        if(e.getSource()==bnext)
        {
            frame.dispose();
        }
    }
}
