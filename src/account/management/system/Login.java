package account.management.system;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;


public class Login extends JFrame implements ActionListener {

    JButton signup = new JButton("Sign Up NOW!!");
    JTextField useridInput = new JTextField();
    JPasswordField passwordInput = new JPasswordField(20);
    JButton forgotpass = new JButton("Forgot Password?");
    JButton login = new JButton("Log In");
    JButton eye;
    JFrame frame = new JFrame();
    boolean passvis=false;


    Login(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

        frame.setTitle("EASYBUD");

        JLabel label1 = getLogo();
        frame.add(label1,BorderLayout.NORTH);

        JLabel text1 = new JLabel("Don't Have an Account?");
        text1.setFont(new Font("Osward",Font.PLAIN,20));
        text1.setForeground(Color.BLACK);
        text1.setBounds(1000,40,900,100);
        frame.add(text1,BorderLayout.NORTH);

        signup.addActionListener(this);
        signup.setBorderPainted(false);
        signup.setFocusPainted(false);
        signup.setContentAreaFilled(false);
        signup.setFont(new Font("Trebuchet MS",Font.BOLD,15));
        signup.setBorder(new EmptyBorder(0, 0, 0, 0));
        signup.setForeground(Color.BLACK);
        signup.setBounds(953,90,200,50);
        signup.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                signup.setFont(new Font("Trebuchet MS",Font.PLAIN,17));
                signup.setForeground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                signup.setFont(new Font("Trebuchet MS",Font.PLAIN,15));
                signup.setForeground(Color.BLACK);
            }
        });
        frame.add(signup);

        ImageIcon user1 = new ImageIcon(ClassLoader.getSystemResource("images/user.png"));
        Image iuser1 = user1.getImage().getScaledInstance(25,25 , Image.SCALE_SMOOTH);
        user1 = new ImageIcon(iuser1);
        JLabel userlabel = new JLabel(user1);
        userlabel.setBounds(410,385,25,25);
        frame.add(userlabel,BorderLayout.NORTH);


        JLabel userid = new JLabel("Email ID :");
        userid.setFont(new Font("Trebuchet MS",Font.PLAIN,20));
        userid.setForeground(Color.BLACK);
        userid.setBounds(448,350,900,100);
        frame.add(userid,BorderLayout.NORTH);

        useridInput.setBounds(560,385,250,35);
        useridInput.setFont(new Font("Trebuchet MS",Font.PLAIN,15));
        frame.add(useridInput);

        ImageIcon pass1 = new ImageIcon(ClassLoader.getSystemResource("images/lock.png"));
        Image ipass1 = pass1.getImage().getScaledInstance(25,25 , Image.SCALE_SMOOTH);
        pass1 = new ImageIcon(ipass1);
        JLabel passlabel = new JLabel(pass1);
        passlabel.setBounds(410,435,25,25);
        frame.add(passlabel,BorderLayout.NORTH);

        JLabel password = new JLabel("Password :");
        password.setFont(new Font("Trebuchet MS",Font.PLAIN,20));
        password.setForeground(Color.BLACK);
        password.setBounds(448,400,900,100);
        frame.add(password,BorderLayout.NORTH);

        passwordInput.setBounds(560,435,250,35);
        passwordInput.setFont(new Font("Trebuchet MS",Font.PLAIN,15));
        frame.add(passwordInput);

        ImageIcon eye1 = new ImageIcon(ClassLoader.getSystemResource("images/eye.png"));
        Image ieye1 = eye1.getImage().getScaledInstance(20,20 , Image.SCALE_SMOOTH);
        eye1 = new ImageIcon(ieye1);
        eye = new JButton(eye1);
        eye.setBorderPainted(false);
        eye.setContentAreaFilled(false);
        eye.setFocusPainted(false);
        eye.addActionListener(this);
        eye.setBounds(810,440,25,25);
        frame.add(eye);

        forgotpass.addActionListener(this);
        forgotpass.setBorderPainted(false);
        forgotpass.setFont(new Font("Trebuchet MS",Font.PLAIN,15));
        forgotpass.setContentAreaFilled(false);
        forgotpass.setForeground(Color.BLACK);
        forgotpass.setFocusPainted(false);
        forgotpass.setBounds(520,455,200,50);
        forgotpass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                forgotpass.setFont(new Font("Trebuchet MS",Font.PLAIN,17));
                forgotpass.setForeground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                forgotpass.setFont(new Font("Trebuchet MS",Font.PLAIN,15));
                forgotpass.setForeground(Color.BLACK);
            }
        });
        frame.add(forgotpass);

        JLabel footer = new JLabel("Effortless Finance Control");
        footer.setFont(new Font("MS Trebuchet",Font.PLAIN,45));
        footer.setForeground(Color.BLACK);
        footer.setHorizontalAlignment(SwingConstants.CENTER);
        footer.setVerticalAlignment(SwingConstants.CENTER);
        footer.setOpaque(true);
        footer.setBackground(new Color(255,255,255,150));
        footer.setBounds(0,585,1280,100);
        frame.add(footer);

        login.addActionListener(this);
        login.setFocusPainted(false);
        login.setForeground(Color.BLACK);
        login.setBackground(Color.WHITE);
        login.setBounds(525,495,200,50);
        frame.add(login);

        JLabel backgroundLabel = getLabel();
        frame.add(backgroundLabel);

        frame.setLocation(150,75);
        frame.setSize(1280,720);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==login){
            ConnectorSQL c = new ConnectorSQL();
            String emailInput = useridInput.getText();
            char[] passInput = passwordInput.getPassword();
            String passString = new String(passInput);
            String query1 = "select * from login where email = '"+emailInput+"' and pass = '"+passString+"'";
            try {
                ResultSet rs = c.s.executeQuery(query1);
                if (rs.next()) {
                        JOptionPane.showMessageDialog(null,"Login Successful!");
                        frame.setVisible(false);
                        new Dashboard(emailInput);
                    }
                else{
                        JOptionPane.showMessageDialog(null,"Invalid Login Credentials.");
                    }
                } catch(Exception err){
                        System.out.println(err);
            }
        }
        else if(e.getSource()==forgotpass){
            frame.setVisible(false);
            new ForgotPass().setVisible(true);
        }
        else if (e.getSource()==signup) {
            frame.setVisible(false);
            frame.dispose();
            new SignupOne().setVisible(true);
        }
        else if(e.getSource()==eye){
            passvis = !passvis;
            if(passvis){
                passwordInput.setEchoChar((char) 0);
            }
            else{
                passwordInput.setEchoChar('•');
            }
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
