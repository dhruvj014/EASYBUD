package account.management.system;
import com.toedter.calendar.JDateChooser;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SignupTwo extends JFrame implements ActionListener {

    JButton submit,cancel;
    JLabel secqn,secans;
    JTextField secansid;
    JFrame frame = new JFrame();
    JComboBox secqns;
    JCheckBox checkBox;
    String emailid;
    SignupTwo(String email){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

        this.emailid = email;

//        JLabel formno = new JLabel("Account No. #"+fno);
//        formno.setFont(new Font("Osward",Font.PLAIN,20));
//        formno.setForeground(Color.WHITE);
//        formno.setBounds(1000,50,900,100);
//        frame.add(formno,BorderLayout.NORTH);

        JLabel personalDetails = new JLabel("Page 2 : Security Details");
        personalDetails.setFont(new Font("Osward",Font.BOLD,50));
        personalDetails.setForeground(Color.BLACK);
        personalDetails.setHorizontalAlignment(SwingConstants.CENTER);
        personalDetails.setVerticalAlignment(SwingConstants.CENTER);
        personalDetails.setBounds(200,15,900,100);
        frame.add(personalDetails,BorderLayout.NORTH);

        secqn = new JLabel("Security Question : ");
        secqn.setFont(new Font("Osward",Font.PLAIN,25));
        secqn.setForeground(Color.BLACK);
        secqn.setBounds(350,150,900,25);
        frame.add(secqn,BorderLayout.NORTH);

        String[] allqns = {"What is your Favourite Color?"
                            ,"Which was your First Car?"
                            ,"What was your first pets name?"
                            ,"In which city were you born>"};
        secqns = new JComboBox<>(allqns);
        secqns.setBackground(Color.WHITE);
        secqns.setBounds(575,150,250,35);
        frame.add(secqns);

        secans = new JLabel("Answer : ");
        secans.setFont(new Font("Osward",Font.PLAIN,25));
        secans.setForeground(Color.BLACK);
        secans.setBounds(350,200,900,25);
        frame.add(secans,BorderLayout.NORTH);

        secansid = new JTextField();
        secansid.setBounds(575,200,250,35);
        frame.add(secansid);

        checkBox = new JCheckBox("I affirm that I have entered all the details correctly and to the best of my ability.");
        checkBox.setFont(new Font("Osward",Font.PLAIN,25));
        checkBox.setOpaque(false);
        checkBox.setForeground(Color.BLACK);
        checkBox.setBounds(200,450,900,25);
        frame.add(checkBox);

        submit = new JButton("Submit");
        submit.addActionListener(this);
        submit.setFocusPainted(false);
        submit.setForeground(Color.BLACK);
        submit.setBackground(Color.WHITE);
        submit.setBounds(400,500,150,50);
        frame.add(submit);

        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        cancel.setFocusPainted(false);
        cancel.setForeground(Color.BLACK);
        cancel.setBackground(Color.WHITE);
        cancel.setBounds(600,500,150,50);
        frame.add(cancel);

        JLabel footer = new JLabel("Effortless Finance Control");
        footer.setFont(new Font("MS Trebuchet",Font.PLAIN,45));
        footer.setForeground(Color.BLACK);
        footer.setHorizontalAlignment(SwingConstants.CENTER);
        footer.setVerticalAlignment(SwingConstants.CENTER);
        footer.setOpaque(true);
        footer.setBackground(new Color(255,255,255,150));
        footer.setBounds(0,585,1280,100);
        frame.add(footer);


        JLabel label1 = Login.getLogo();
        frame.add(label1,BorderLayout.NORTH);

        JLabel label = Login.getLabel();
        frame.add(label);

        frame.setLocation(150,75);
        frame.setSize(1280,720);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SignupTwo("");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        String formno = ""+fno;
        String secQuestion = (String) secqns.getSelectedItem();
        String secAnswer = secansid.getText();

        try{
             if (e.getSource()==cancel) {
                 ConnectorSQL c = new ConnectorSQL();
                 String query = "delete from signup where email = '"+emailid+"'";
                 c.s.executeUpdate(query);
                 String query1 = "delete from signup where email = '"+emailid+"'";
                 c.s.executeUpdate(query1);
                 frame.setVisible(false);
                 new Login().setVisible(true);
            } else if(secAnswer.isEmpty()){
                JOptionPane.showMessageDialog(null,"Security Answer is Required.");
            } else if(!checkBox.isSelected()){
                JOptionPane.showMessageDialog(null,"Please check the agreement.");
            } else if(e.getSource()==submit){
                ConnectorSQL c = new ConnectorSQL();
                String query = "insert into signuptwo values('"+emailid+"', '"+secQuestion+"', '"+secAnswer+"')";
                c.s.executeUpdate(query);
                frame.setVisible(false);
                new Login().setVisible(true);
            }
        }
        catch(Exception except){
            System.out.print(except);
        }

    }
}
