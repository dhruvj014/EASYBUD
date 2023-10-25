package account.management.system;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class ForgotPass extends JFrame implements ActionListener {

    boolean qns=false;
    String emailid;
    JTextField useridInput = new JTextField();
    JLabel password1,password2,secqn;
    JTextField newpass1 = new JTextField();
    JTextField newpass2 = new JTextField();
    JTextField secans = new JTextField();
    JButton check = new JButton("Check");
    JButton confirm = new JButton("Confirm");
    JButton reset = new JButton("Reset");
    JFrame frame = new JFrame();

    ForgotPass(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

        frame.setTitle("EASYBUD");

        JLabel label1 = getLogo();
        frame.add(label1,BorderLayout.NORTH);

        JLabel userid = new JLabel("Email ID :");
        userid.setFont(new Font("Trebuchet MS",Font.PLAIN,20));
        userid.setForeground(Color.BLACK);
        userid.setBounds(460,250,900,100);
        frame.add(userid,BorderLayout.NORTH);

        useridInput.setBounds(560,285,250,35);
        frame.add(useridInput);

        secqn = new JLabel("hello!");
        secqn.setFont(new Font("Trebuchet MS",Font.PLAIN,20));
        secqn.setForeground(Color.BLACK);
        secqn.setBounds(500,315,400,100);
        secqn.setVisible(false);
        frame.add(secqn);

        confirm.addActionListener(this);
        confirm.setFocusPainted(false);
        confirm.setForeground(Color.BLACK);
        confirm.setBackground(Color.WHITE);
        confirm.setBounds(525,495,150,50);
        confirm.setVisible(false);
        frame.add(confirm);

        password1 = new JLabel("New Password :");
        password1.setFont(new Font("Trebuchet MS",Font.PLAIN,20));
        password1.setForeground(Color.BLACK);
        password1.setBounds(403,300,200,100);
        password1.setVisible(false);
        frame.add(password1);

        newpass1.setBounds(560,335,250,35);

        password2 = new JLabel("Re-Enter New Password :");
        password2.setFont(new Font("Trebuchet MS",Font.PLAIN,20));
        password2.setForeground(Color.BLACK);
        password2.setBounds(320,350,400,100);
        password2.setVisible(false);
        frame.add(password2);

        newpass2.setBounds(560,385,250,35);

        check.addActionListener(this);
        check.setFocusPainted(false);
        check.setForeground(Color.BLACK);
        check.setBackground(Color.WHITE);
        check.setBounds(525,495,150,50);
        frame.add(check);

        secans.setBounds(460,395,350,35);

        reset.addActionListener(this);
        reset.setFocusPainted(false);
        reset.setForeground(Color.BLACK);
        reset.setBackground(Color.WHITE);
        reset.setBounds(525,495,150,50);
        reset.setVisible(false);
        frame.add(reset);

        JLabel backgroundLabel = getLabel();
        frame.add(backgroundLabel);

        frame.setLocation(150,75);
        frame.setSize(1280,720);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ForgotPass();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==check){
            ConnectorSQL c = new ConnectorSQL();
            String emailInput = useridInput.getText();
            String query1 = "select * from login where email = '"+emailInput+"'";
            try{
                ResultSet rs = c.s.executeQuery(query1);
                if(rs.next()){
//                    fno = rs.getLong("formno");
                    this.emailid = emailInput;
                    String query2 = "select * from signuptwo where email = '"+emailid+"'";
                    try{
                        ResultSet rs2 = c.s.executeQuery(query2);
                        if(rs2.next()){
                            String secqntext = rs2.getString("secQuestion");
                            secqn.setText(secqntext);
                            System.out.println(secqntext);
                            secqn.setVisible(true);
                            frame.revalidate();
                            frame.repaint();
                        }
                    }catch(Exception e2){
                        System.out.println(e2);
                    }
                    frame.remove(check);
                    confirm.setVisible(true);
                    frame.add(secans);
                    frame.revalidate();
                    frame.repaint();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Email ID not registered.");
                }
            } catch (Exception ex) {
                System.out.print(emailInput+ex);
            }
        }
        else if(e.getSource()==confirm){
            ConnectorSQL c = new ConnectorSQL();
            String secpass = secans.getText();
            String query1 = "select * from signuptwo where secAnswer = '"+secpass+"'";
            try{
                ResultSet rs = c.s.executeQuery(query1);
                if(rs.next()){
                    String query2 = "select * from signuptwo where email = '"+emailid+"'";
                    try{
                        ResultSet rs2 = c.s.executeQuery(query2);
                        if(rs2.next()){
                            confirm.setVisible(false);
                            secqn.setVisible(false);
                            frame.remove(secans);
                            reset.setVisible(true);
                            password1.setVisible(true);
                            frame.add(newpass1);
                            password2.setVisible(true);
                            frame.add(newpass2);
                            frame.revalidate();
                            frame.repaint();
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Invalid Answer.");
                        }
                    }catch(Exception e2){
                        System.out.println(e2);
                    }
                    frame.revalidate();
                    frame.repaint();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Invalid Answer.");
                }
            } catch (Exception ex) {
                System.out.print(ex);
            }
            } else if (e.getSource()==reset) {
                ConnectorSQL c = new ConnectorSQL();
                String emailInput = useridInput.getText();
                String newpass = newpass1.getText();
                String confpass = newpass2.getText();
                if(newpass.equals(confpass)){
                    String query1 = "UPDATE login SET pass = '"+newpass+"' WHERE email = '"+emailInput+"'";
                    try {
                        c.s.executeUpdate(query1);
                        String query2 = "UPDATE signup SET pass = '"+newpass+"' WHERE email = '"+emailInput+"'";
                        try {
                            c.s.executeUpdate(query2);
                            JOptionPane.showMessageDialog(null,"Update Successful.\n Redirecting to Login Page...");
                        } catch (Exception e2) {
                            System.out.println(e2);
                        }
                        frame.setVisible(false);
                        frame.dispose();
                        new Login().setVisible(true);
                    } catch(Exception re){
                        System.out.println("error - "+re);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Passwords Dont Match");
                }

            }else {
            JOptionPane.showMessageDialog(null, "Email ID not registered.");
        }

        }
    public static JLabel getLabel() {
        ImageIcon backgroundImage = new ImageIcon(ClassLoader.getSystemResource("images/gradientbg.png"));
        Image bg1 = backgroundImage.getImage().getScaledInstance(backgroundImage.getIconWidth(),backgroundImage.getIconHeight() , Image.SCALE_DEFAULT);
        backgroundImage = new ImageIcon(bg1);
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        return backgroundLabel;
    }
    public static JLabel getLogo(){
        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("images/logo_final1.png"));
        Image i1 = img1.getImage().getScaledInstance(img1.getIconWidth(),img1.getIconHeight() , Image.SCALE_SMOOTH);
        img1 = new ImageIcon(i1);
        JLabel label1 = new JLabel(img1);
        label1.setBounds(0,0,1280,720);
        return label1;
    }
}
