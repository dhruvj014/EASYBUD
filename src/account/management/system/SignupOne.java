package account.management.system;
import com.toedter.calendar.JDateChooser;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SignupOne extends JFrame implements ActionListener {

    JButton right;
    JRadioButton male,female,other;
    JDateChooser dateChooser;
    JLabel fname,lname,dob,gender,email,passw;
    JTextField fnameid,lnameid,dobid,genderid,emailid,passwid;
    JFrame frame = new JFrame();
    static long fno;
    SignupOne(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

//        Random r1 = new Random();
//        fno = Math.abs((r1.nextLong()%9000L)+1000L);

//        JLabel formno = new JLabel("Account No. #"+fno);
//        formno.setFont(new Font("Osward",Font.PLAIN,20));
//        formno.setForeground(Color.WHITE);
//        formno.setBounds(1000,50,900,100);
//        frame.add(formno,BorderLayout.NORTH);

        JLabel personalDetails = new JLabel("Page 1 : Personal Details");
        personalDetails.setFont(new Font("Osward",Font.BOLD,50));
        personalDetails.setForeground(Color.BLACK);
        personalDetails.setHorizontalAlignment(SwingConstants.CENTER);
        personalDetails.setVerticalAlignment(SwingConstants.CENTER);
        personalDetails.setBounds(200,15,900,100);
        frame.add(personalDetails,BorderLayout.NORTH);

        fname = new JLabel("First Name : ");
        fname.setFont(new Font("Osward",Font.PLAIN,25));
        fname.setForeground(Color.BLACK);
        fname.setBounds(400,200,900,25);
        frame.add(fname,BorderLayout.NORTH);

        fnameid = new JTextField();
        fnameid.setBounds(575,200,250,35);
        frame.add(fnameid);

        lname = new JLabel("Last Name : ");
        lname.setFont(new Font("Osward",Font.PLAIN,25));
        lname.setForeground(Color.BLACK);
        lname.setBounds(400,250,900,25);
        frame.add(lname,BorderLayout.NORTH);

        lnameid = new JTextField();
        lnameid.setBounds(575,250,250,35);
        frame.add(lnameid);

        dob = new JLabel("Date Of Birth : ");
        dob.setFont(new Font("Osward",Font.PLAIN,25));
        dob.setForeground(Color.BLACK);
        dob.setBounds(400,300,900,25);
        frame.add(dob,BorderLayout.NORTH);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(575,300,250,35);
        frame.add(dateChooser);

        gender = new JLabel("Gender : ");
        gender.setFont(new Font("Osward",Font.PLAIN,25));
        gender.setForeground(Color.BLACK);
        gender.setBounds(400,350,900,25);
        frame.add(gender,BorderLayout.NORTH);

        male = new JRadioButton("Male");
        male.setBounds(575,350,100,35);
        male.setForeground(Color.BLACK);
        male.setOpaque(false);
        frame.add(male);
        female = new JRadioButton("Female");
        female.setBounds(675,350,100,35);
        female.setForeground(Color.BLACK);
        female.setOpaque(false);
        frame.add(female);
        other = new JRadioButton("Other");
        other.setBounds(775,350,100,35);
        other.setForeground(Color.BLACK);
        other.setOpaque(false);
        frame.add(other);
        ButtonGroup genders = new ButtonGroup();
        genders.add(male);
        genders.add(female);
        genders.add(other);

        email = new JLabel("Email : ");
        email.setFont(new Font("Osward",Font.PLAIN,25));
        email.setForeground(Color.BLACK);
        email.setBounds(400,400,900,25);
        frame.add(email,BorderLayout.NORTH);
        
        emailid = new JTextField();
        emailid.setBounds(575,400,250,35);
        frame.add(emailid);

        passw = new JLabel("Password : ");
        passw.setFont(new Font("Osward",Font.PLAIN,25));
        passw.setForeground(Color.BLACK);
        passw.setBounds(400,450,900,25);
        frame.add(passw,BorderLayout.NORTH);

        passwid = new JTextField();
        passwid.setBounds(575,450,250,35);
        frame.add(passwid);


        ImageIcon right1 = new ImageIcon(ClassLoader.getSystemResource("images/right1.png"));
        Image iright1 = right1.getImage().getScaledInstance(64,64 , Image.SCALE_AREA_AVERAGING);
        right1 = new ImageIcon(iright1);
        right = new JButton(right1);
        right.setBorderPainted(false);
        right.setContentAreaFilled(false);
        right.setFocusPainted(false);
        right.addActionListener(this);
        right.setBounds(950,350,64 ,64);
        frame.add(right);

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
        new SignupOne();

    }

    public static long getfno(){
        return fno;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String formno = ""+fno;
        String firstName = fnameid.getText();
        String lastName = lnameid.getText();
        String birthdate = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String sex = "";
        if(male.isSelected()){
            sex = "Male";
        } else if (female.isSelected()) {
            sex="Female";
        }else if(other.isSelected()){
            sex="Other";
        }
        String email = emailid.getText();
        String passw = passwid.getText();

        try{
            if(firstName.isEmpty()){
                JOptionPane.showMessageDialog(null,"First Name is Required.");
            }
            else if(lastName.isEmpty()){
                JOptionPane.showMessageDialog(null,"Last Name is Required.");
            }
            else if(birthdate.isEmpty()){
                JOptionPane.showMessageDialog(null,"Birthdate is Required.");
            }
            else if(sex.isEmpty()){
                JOptionPane.showMessageDialog(null,"Gender is Required.");
            }
            else if(email.isEmpty()){
                JOptionPane.showMessageDialog(null,"Email is Required.");
            }
            else if(passw.isEmpty()){
                JOptionPane.showMessageDialog(null,"Password is Required.");
            }
            else{
                ConnectorSQL c = new ConnectorSQL();
                String query = "insert into signup values('"+email+"', '"+firstName+"', '"+lastName+"', '"+birthdate+"','"+sex+"', '"+passw+"')";
                String query1 = "insert into login values('"+email+"', '"+passw+"')";
                c.s.executeUpdate(query);
                c.s.executeUpdate(query1);
                frame.setVisible(false);
                frame.dispose();
                new SignupTwo(email).setVisible(true);
            }
        }
        catch(Exception except){
            System.out.print(except);
        }

    }
}
