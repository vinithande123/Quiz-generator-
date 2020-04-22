import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class GiveQuiz1 implements ActionListener {
    JFrame frame;
    JPanel panel;
    JLabel lquestion;
    JRadioButton ra,rb,rc,rd;
    ButtonGroup bg;
    JButton bnext;
    Connection con=null;
    Statement stmt=null;
    ResultSet rs=null;
    int count=0;
    GiveQuiz1() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "vinithande4", "Swarali123");
            System.out.println("Connection Succuess ....");
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("select * from quizn");
                frame = new JFrame("Give Quiz");
                panel = new JPanel();
                lquestion = new JLabel("");
                ra = new JRadioButton("");
                rb = new JRadioButton("");
                rc = new JRadioButton("");
                rd = new JRadioButton("");
                bg = new ButtonGroup();
               /* lquestion.setText(rs.getString(1));
                ra.setText(rs.getString(2));
                rb.setText(rs.getString(3));
                rc.setText(rs.getString(4));
                rc.setText(rs.getString(5));*/
                bnext = new JButton("Next");
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
                frame.setSize(450, 250);
                frame.setVisible(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*GiveQuiz() throws SQLException, ClassNotFoundException {
        String a[]=new String[1];
        main(a);
    }*/
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new GiveQuiz1();
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==bnext)
        {
            if(count>0) {
                String ans = " ";
                if (ra.isSelected())
                    ans = "A";
                if (rb.isSelected())
                    ans = "B";
                if (rc.isSelected())
                    ans = "C";
                if (rd.isSelected())
                    ans = "D";
                PreparedStatement pst = null;
                try {
                    pst = con.prepareStatement("insert into answers values (?)");
                    pst.setString(1, ans);
                    pst.execute();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            try {
                if(rs.next())
                {
                    count=2;
                    lquestion.setText(rs.getString(1));
                    ra.setText(rs.getString(2));
                    rb.setText(rs.getString(3));
                    rc.setText(rs.getString(4));
                    rd.setText(rs.getString(5));
                    System.out.println("Next Question");
                }
                else
                {
                    System.out.println("Frame Diposed");
                    frame.dispose();
                    new CheckAnswer();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
/*    public void actionPerformed(ActionEvent e) {

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
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "rushabh1", "password");
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
    }*/
}