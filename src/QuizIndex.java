import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QuizIndex implements ActionListener {
    JFrame frame;
    JPanel panel;
    JLabel label;
    JButton bgivequiz,bcreatequiz;

    QuizIndex()
    {
        frame=new JFrame("Quiz Index");
        panel=new JPanel();
        label=new JLabel("Welcome To Quizzzz !!! :)");
        bgivequiz=new JButton("Give Quiz");
        bcreatequiz=new JButton("Create Quiz");
        bcreatequiz.addActionListener(this);
        bgivequiz.addActionListener(this);
        panel.add(label);
        panel.add(bcreatequiz);
        panel.add(bgivequiz);
        frame.add(panel);
        frame.setSize(250,100);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new QuizIndex();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bcreatequiz)
        {
            frame.dispose();
            new Count();
        }
        if(e.getSource()==bgivequiz)
        {
            int flag=0;
            frame.dispose();
            try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            int a=JOptionPane.showConfirmDialog(frame,"Are You Sure ? ");
            if(a==JOptionPane.YES_OPTION)
            {
                try {
                    new GiveQuiz1();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }
}
