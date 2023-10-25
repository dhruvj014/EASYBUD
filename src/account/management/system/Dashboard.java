package account.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.*;

import com.toedter.calendar.JDateChooser;

import static account.management.system.Login.getLabel;

public class Dashboard extends JFrame implements ActionListener {

    static ArrayList<Integer> transactions = new ArrayList<>();
    static ArrayList<Date> dates = new ArrayList<>();
    static JTable table;
    static JScrollPane scrollPane;
    static double displaybal=0;
    static double displayshop=0;
    static double displayfood=0;
    static double displaybill=0;
    static double displayother=0;
    JButton addExpenses;
    JButton addFunds;
    static JButton submit;
    JLabel addExpenditure;
    static JLabel typeExp;
    JTextField purpose,sum;
    JDateChooser dateChooser;
    static JFrame frame = new JFrame();
    static JRadioButton shopping;
    static JRadioButton fad;
    static JRadioButton bills;
    static JRadioButton other;
    JPanel sideBarPanel;
    static JLabel otherico1;
    static JLabel homeico1;
    static JLabel cutico1;
    static JLabel shopico1;
    static JPanel dynExpensePanel;
    static JPanel expensePanelHead;
    static JPanel tableData;
    static JLabel balancedisplayer,shopdisplayer,fooddisplayer,billdisplayer,otherdisplayer;
    static JPanel balancePanel;
    static JPanel fixexpensePanel;
    static JPanel shoppingPanel;
    static JPanel billsPanel;
    static JPanel foodPanel;
    static JPanel otherPanel;
    static JPanel submitPanel;
    String useremail;
    static Container contentPane;
    static boolean isexpense = true;
    public Dashboard(String emailid){
        this.useremail = emailid;

        GridBagConstraints c = new GridBagConstraints();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

        contentPane = frame.getContentPane();
        contentPane.setBackground(new Color(236,193,205));
//        222,213,241
        sideBarPanel = new JPanel(new GridBagLayout());
        sideBarPanel.setBackground(new Color(0, 0, 40));
        sideBarPanel.setBounds(0,0,200,720);

        fixexpensePanel = new JPanel(new GridBagLayout());
        fixexpensePanel.setBackground(new Color(255, 255, 255));
        fixexpensePanel.setBounds(250,35,995,85);

        expensePanelHead = new JPanel(new BorderLayout());
        expensePanelHead.setBackground(new Color(236, 19, 24));
        expensePanelHead.setBounds(250,35,995,35);

        addExpenditure = new JLabel("    Add Expense : ");
        addExpenditure.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        addExpenditure.setForeground(Color.WHITE);
//        addExpenditure.setBounds(270,35,950,35);
        expensePanelHead.add(addExpenditure,BorderLayout.WEST);

        JLabel purposetext = new JLabel("Purpose :");
        purposetext.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        purposetext.setForeground(Color.BLACK);
//        purposetext.setBounds(270,50,900,100);
        c.ipadx = 100;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(50,20,0,600);
        c.weightx=1;
        fixexpensePanel.add(purposetext,c);

        purpose = new JTextField();
        purpose.setBounds(390,85,125,30);
        purpose.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        c.ipadx = 125;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(55,-100,0,300);
        fixexpensePanel.add(purpose,c);

        JLabel sumtext = new JLabel("Sum :");
        sumtext.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        sumtext.setForeground(Color.BLACK);
//        sumtext.setBounds(560,50,900,100);
        c.ipadx = 25;
        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(50,-500,0,500);
//        c.weightx=1;
        fixexpensePanel.add(sumtext,c);

        sum = new JTextField();
//        sum.setBounds(630,85,125,30);
        sum.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        c.ipadx = 125;
        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(50,-500,0,300);
        c.weightx=1;
        fixexpensePanel.add(sum,c);

        JLabel datetext = new JLabel("Date :");
        datetext.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        datetext.setForeground(Color.BLACK);
//        datetext.setBounds(780,50,900,100);
        c.ipadx = 25;
        c.gridx = 3;
        c.gridy = 1;
        c.insets = new Insets(50,-300,0,300);
        fixexpensePanel.add(datetext,c);

        dateChooser = new JDateChooser();
        dateChooser.setEnabled(false);
        dateChooser.getCalendarButton().setEnabled(true);
//        dateChooser.setBounds(850,85,250,35);
        c.ipadx = 200;
        c.ipady = 15;
        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(50,-100,0,0);
        c.weightx=1;
        fixexpensePanel.add(dateChooser,c);

        dynExpensePanel = new JPanel(new GridBagLayout());
        dynExpensePanel.setBackground(new Color(255, 255, 255));
        dynExpensePanel.setBounds(250,115,995,90);
        contentPane.add(dynExpensePanel);

        c.weightx=0;
        c.ipady = 0;
        c.insets = new Insets(20,0,0,0);

        typeExp = new JLabel("Category : ");
        typeExp.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        typeExp.setForeground(Color.BLACK);
//        typeExp.setBounds(270,150,400,25);
        c.ipadx = 50;
        c.gridx = 1;
        c.gridy = 1;
        dynExpensePanel.add(typeExp,c);

        ImageIcon cart1 = new ImageIcon(ClassLoader.getSystemResource("images/shopping-cart.png"));
        Image icart1 = cart1.getImage().getScaledInstance(20,20 , Image.SCALE_SMOOTH);
        cart1 = new ImageIcon(icart1);
        shopico1 = new JLabel(cart1);
//        shopico1.setBounds(420,150,100,35);
        c.ipadx = 25;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(20,150,0,0);
        c.weightx=1;
        dynExpensePanel.add(shopico1,c);

        shopping = new JRadioButton("");
//        shopping.setBounds(430,150,100,35);
        shopping.setForeground(Color.WHITE);
        shopping.setOpaque(false);
        c.ipadx = 25;
        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(20,-120,0,0);
        c.weightx=1;
        dynExpensePanel.add(shopping,c);

        ImageIcon cutlery1 = new ImageIcon(ClassLoader.getSystemResource("images/cutlery.png"));
        Image icutlery1 = cutlery1.getImage().getScaledInstance(20,20 , Image.SCALE_SMOOTH);
        cutlery1 = new ImageIcon(icutlery1);
        cutico1 = new JLabel(cutlery1);
//        cutico1.setBounds(520,150,100,35);
        c.insets = new Insets(20,-180,0,0);
        c.ipadx = 25;
        c.gridx = 3;
        c.gridy = 1;
        c.weightx=1;
        dynExpensePanel.add(cutico1,c);

        fad = new JRadioButton("");
//        fad.setBounds(530,150,50,35);
        fad.setForeground(Color.WHITE);
        fad.setOpaque(false);
        c.insets = new Insets(20,-260,0,0);
        c.ipadx = 25;
        c.gridx = 4;
        c.gridy = 1;
        c.weightx=1;
        dynExpensePanel.add(fad,c);

        ImageIcon home1 = new ImageIcon(ClassLoader.getSystemResource("images/home.png"));
        Image ihome1 = home1.getImage().getScaledInstance(20,20 , Image.SCALE_SMOOTH);
        home1 = new ImageIcon(ihome1);
        homeico1 = new JLabel(home1);
//        homeico1.setBounds(620,150,100,35);
        c.insets = new Insets(20,-300,0,0);
        c.ipadx = 25;
        c.gridx = 5;
        c.gridy = 1;
        c.weightx=1;
        dynExpensePanel.add(homeico1,c);

        bills = new JRadioButton("");
        bills.setBounds(630,150,100,35);
        bills.setForeground(Color.WHITE);
        bills.setOpaque(false);
        c.insets = new Insets(20,-385,0,0);
        c.ipadx = 25;
        c.gridx = 6;
        c.gridy = 1;
        c.weightx=1;
        dynExpensePanel.add(bills,c);

        ImageIcon other1 = new ImageIcon(ClassLoader.getSystemResource("images/leaf.png"));
        Image iother1 = other1.getImage().getScaledInstance(20,20 , Image.SCALE_SMOOTH);
        other1 = new ImageIcon(iother1);
        otherico1 = new JLabel(other1);
//        otherico1.setBounds(720,150,100,35);
        c.insets = new Insets(20,-435,0,0);
        c.ipadx = 25;
        c.gridx = 7;
        c.gridy = 1;
        c.weightx=1;
        dynExpensePanel.add(otherico1,c);

        other = new JRadioButton("");
//        other.setBounds(730,150,100,35);
        other.setForeground(Color.WHITE);
        other.setOpaque(false);
        c.insets = new Insets(20,-510,0,0);
        c.ipadx = 25;
        c.gridx = 8;
        c.gridy = 1;
        c.weightx=1;
        dynExpensePanel.add(other,c);

        c.insets = new Insets(0,0,0,0);

        ButtonGroup categories = new ButtonGroup();
        categories.add(shopping);
        categories.add(fad);
        categories.add(bills);
        categories.add(other);

        submit = new JButton("Submit");
        submit.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        submit.addActionListener(this);
        submit.setFocusPainted(false);
        submit.setForeground(Color.WHITE);
        submit.setBackground(new Color(236, 19, 24));
        c.gridx=9;
        c.gridy=1;
        c.ipadx = 30;
        c.insets = new Insets(20,-200,0,0);
        dynExpensePanel.add(submit,c);

        balancePanel = new JPanel(new GridBagLayout());
        balancePanel.setBackground(new Color(51, 204, 51));
        balancePanel.setBounds(775,230,470,150);

        c.gridx=0;
        c.gridy=0;
        c.ipady=0;
        c.ipadx=0;
        c.weightx=0;
        c.insets = new Insets(0,0,0,0);

        JLabel baltext = new JLabel("Balance");
        baltext.setFont(new Font("Bookman Old Style",Font.BOLD,15));
        baltext.setForeground(Color.WHITE);
//        baltext.setBounds(825,170,300,200);
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(20,20,0,0);
        balancePanel.add(baltext,c);
        ImageIcon bal1 = new ImageIcon(ClassLoader.getSystemResource("images/cash.png"));
        Image ibal1 = bal1.getImage().getScaledInstance(48,48 , Image.SCALE_SMOOTH);
        bal1 = new ImageIcon(ibal1);
        JLabel balico1 = new JLabel(bal1);
//        balico1.setBounds(985,175,300,200);
        c.gridx = 3;
        c.gridy = 1;
        c.insets = new Insets(30,100,0,0);
        balancePanel.add(balico1,c);
        JLabel currency = new JLabel("INR");
        currency.setFont(new Font("Bookman Old Style",Font.BOLD,20));
        currency.setForeground(Color.WHITE);
//        currency.setBounds(825,310,350,35);
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(10,0,20,0);
        balancePanel.add(currency,c);
        balancedisplayer = new JLabel(String.valueOf(displaybal));
        balancedisplayer.setFont(new Font("Bookman Old Style",Font.BOLD,40));
        balancedisplayer.setForeground(Color.WHITE);
//        balancedisplayer.setBounds(975,310,350,50);
        c.gridx = 3;
        c.gridy = 3;
        c.insets = new Insets(10,80,0,0);
//        c.insets = new Insets(20,20,0,0);
        balancePanel.add(balancedisplayer,c);

        shoppingPanel = new JPanel(new GridBagLayout());
        shoppingPanel.setBackground(new Color(10,210,210));
        shoppingPanel.setBounds(775,390,150,150);
        JLabel shoptext = new JLabel("Shopping");
        shoptext.setFont(new Font("Bookman Old Style",Font.BOLD,15));
        shoptext.setForeground(Color.WHITE);
//        shoptext.setBounds(810,435,350,35);
        c.gridy=0;
        c.gridx=0;
        c.insets = new Insets(0,0,15,0);
        shoppingPanel.add(shoptext,c);
        ImageIcon shop1 = new ImageIcon(ClassLoader.getSystemResource("images/wshopping-cart.png"));
        Image ishop1 = shop1.getImage().getScaledInstance(32,32 , Image.SCALE_SMOOTH);
        shop1 = new ImageIcon(ishop1);
        JLabel shopico2 = new JLabel(shop1);
//        shopico2.setBounds(800,400,100,50);
        c.gridx=0;
        c.gridy=0;
        c.insets = new Insets(0,0,70,0);
        shoppingPanel.add(shopico2,c);
        shopdisplayer = new JLabel(String.valueOf(displayshop));
        shopdisplayer.setFont(new Font("Bookman Old Style",Font.BOLD,30));
        shopdisplayer.setForeground(Color.WHITE);
        c.ipady = 10;
        c.insets = new Insets(50,0,0,0);
        shoppingPanel.add(shopdisplayer,c);

        foodPanel = new JPanel(new GridBagLayout());
        foodPanel.setBackground(new Color(252,198,2));
        foodPanel.setBounds(935,390,150,150);
        JLabel foodtext = new JLabel("Food & Drinks");
        foodtext.setFont(new Font("Bookman Old Style",Font.BOLD,15));
        foodtext.setForeground(Color.WHITE);
//        foodtext.setBounds(960,435,350,35);
        c.gridy=0;
        c.gridx=0;
        c.insets = new Insets(0,0,15,0);
        foodPanel.add(foodtext,c);
        ImageIcon food1 = new ImageIcon(ClassLoader.getSystemResource("images/wcutlery.png"));
        Image ifood1 = food1.getImage().getScaledInstance(32,32 , Image.SCALE_SMOOTH);
        food1 = new ImageIcon(ifood1);
        JLabel foodico2 = new JLabel(food1);
//        foodico2.setBounds(960,400,100,50);
        c.gridx=0;
        c.gridy=0;
        c.insets = new Insets(0,0,70,0);
        foodPanel.add(foodico2,c);
        fooddisplayer = new JLabel("-");
        fooddisplayer.setFont(new Font("Bookman Old Style",Font.BOLD,30));
        fooddisplayer.setForeground(Color.WHITE);
        c.ipady = 10;
        c.insets = new Insets(50,0,0,0);
        foodPanel.add(fooddisplayer,c);

        billsPanel = new JPanel(new GridBagLayout());
        billsPanel.setBackground(new Color(236, 19, 24));
        billsPanel.setBounds(1095,390,150,150);
        JLabel billstext = new JLabel("Bills");
        billstext.setFont(new Font("Bookman Old Style",Font.BOLD,15));
        billstext.setForeground(Color.WHITE);
//        billstext.setBounds(1155,435,350,35);
        c.gridy=0;
        c.gridx=0;
        c.insets = new Insets(0,0,15,0);
        billsPanel.add(billstext,c);
        ImageIcon bill1 = new ImageIcon(ClassLoader.getSystemResource("images/whome.png"));
        Image ibill1 = bill1.getImage().getScaledInstance(32,32 , Image.SCALE_SMOOTH);
        bill1 = new ImageIcon(ibill1);
        JLabel billico2 = new JLabel(bill1);
//        billico2.setBounds(1120,400,100,50);
        c.gridx=0;
        c.gridy=0;
        c.insets = new Insets(0,0,70,0);
        billsPanel.add(billico2,c);
        billdisplayer = new JLabel("-");
        billdisplayer.setFont(new Font("Bookman Old Style",Font.BOLD,30));
        billdisplayer.setForeground(Color.WHITE);
        c.ipady = 10;
        c.insets = new Insets(50,0,0,0);
        billsPanel.add(billdisplayer,c);

        otherPanel = new JPanel(new GridBagLayout());
        otherPanel.setBackground(Color.BLACK);
        otherPanel.setBounds(775,550,470,80);
        JLabel othertext = new JLabel("Other");
        othertext.setFont(new Font("Bookman Old Style",Font.BOLD,15));
        othertext.setForeground(Color.WHITE);
//        othertext.setBounds(975,550,350,35);
        c.gridx=1;
        c.gridy=0;
        c.insets = new Insets(0,0,30,0);
        otherPanel.add(othertext,c);
        otherdisplayer = new JLabel("----");
        otherdisplayer.setFont(new Font("Bookman Old Style",Font.BOLD,30));
        otherdisplayer.setForeground(Color.WHITE);
        c.ipady = 10;
        c.insets = new Insets(35,0,0,0);
        otherPanel.add(otherdisplayer,c);

        tableData = new JPanel();
        tableData.setBounds(250,230,500,475);
        tableData.setBackground(Color.WHITE);
        table = new JTable();
        scrollPane = new JScrollPane(table);
        JLabel recentTr = new JLabel("Recent Transactions",SwingConstants.LEFT);
        recentTr.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        recentTr.setForeground(Color.BLACK);
        recentTr.setBounds(270,35,950,35);
        tableData.add(recentTr);
        scrollPane.setBounds(250,300,500,450);
        tableData.add(scrollPane);
        getData(useremail,isexpense);
        contentPane.add(tableData);

        addFunds = new JButton("Add Funds");
        addFunds.addActionListener(this);
        addFunds.setForeground(Color.BLACK);
        addFunds.setBackground(new Color(230,230,230));
        addFunds.setFocusPainted(false);
        addFunds.setBorderPainted(false);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30;
        c.insets = new Insets(0, 0, 150, 0);
        sideBarPanel.add(addFunds,c);

        addExpenses = new JButton("Add Expenses");
        addExpenses.addActionListener(this);
        addExpenses.setForeground(Color.BLACK);
        addExpenses.setBackground(new Color(230,230,230));
        addExpenses.setFocusPainted(false);
        addExpenses.setBorderPainted(false);
        c.gridy=1;
        c.insets = new Insets(-145, 0, 250, 0);
        sideBarPanel.add(addExpenses,c);

        JLabel backgroundLabel = getLabel();


        contentPane.add(dynExpensePanel);
        contentPane.add(expensePanelHead);
        contentPane.add(fixexpensePanel);
        contentPane.add(sideBarPanel);
        frame.setBackground(new Color(10,210,210));
        frame.setLocation(150,75);
        frame.setSize(1280,720);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==submit && isexpense){
            boolean truth;
            double sumDB = 0;
            String purposeDB = purpose.getText();
            String stringsumDB = sum.getText();
            try {
                sumDB = Double.parseDouble(stringsumDB);
                truth = false;
            } catch(NumberFormatException ne){
                truth = true;
            }
            String dateOfExp = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
            String categ = "";
            if(shopping.isSelected()){
                categ = "Shopping";
            } else if (fad.isSelected()) {
                categ="Food";
            }else if(bills.isSelected()){
                categ="Bills";
            }else if(other.isSelected()){
                categ="Other";
            }
            try{
                if(purposeDB.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Purpose is Required.");
                }
                else if(stringsumDB.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Amount is Required.");
                }
                else if(truth){
                    JOptionPane.showMessageDialog(null,"Enter Valid Number.");
                }
                else if(dateOfExp.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Date of expense is required.");
                }
                else if(categ.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Choose A Category!");
                }else if(sumDB>displaybal){
                    JOptionPane.showMessageDialog(null,"Expense Cannot Exceed Account Balance!");
                }
                else{
                    ConnectorSQL c = new ConnectorSQL();
                    String query = "insert into newdatabase values('"+useremail+"', 'Withdraw' , '"+purposeDB+"', '"+sumDB+"', '"+categ+"', '"+dateOfExp+"')";
                    c.s.executeUpdate(query);
                    getData(useremail,isexpense);
                }
            }
            catch(Exception except){
                System.out.print(except);
            }
        } if(e.getSource()==submit && !isexpense){
            boolean truth;
            double sumDB = 0;
            String purposeDB = purpose.getText();
            String stringsumDB = sum.getText();
            try {
                sumDB = Double.parseDouble(stringsumDB);
                truth = false;
            } catch(NumberFormatException ne){
                truth = true;
            }
            String dateOfExp = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
            String categ = "Deposit";
            try{
                if(purposeDB.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Purpose is Required.");
                }
                else if(stringsumDB.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Amount is Required.");
                }
                else if(truth){
                    JOptionPane.showMessageDialog(null,"Enter Valid Number.");
                }
                else if(dateOfExp.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Date of expense is required.");
                }
                else{
                    ConnectorSQL c = new ConnectorSQL();
                    String query = "insert into newdatabase values('"+useremail+"', 'Deposit' , '"+purposeDB+"', '"+sumDB+"', '"+categ+"', '"+dateOfExp+"')";
                    c.s.executeUpdate(query);
                    getData(useremail,isexpense);
                }
            }
            catch(Exception except){
                System.out.print(except);
            }
        }
        else if (e.getSource()==addFunds) {
//            frame.setVisible(false);
//            frame.dispose();
//            new Deposit(useremail);
            isexpense = false;
            getData(useremail,isexpense);
            frame.repaint();
            frame.revalidate();
        }
        else if (e.getSource()==addExpenses) {
//            frame.setVisible(false);
//            frame.dispose();
//            new Deposit(useremail);
            isexpense = true;
            getData(useremail,isexpense);
            frame.repaint();
            frame.revalidate();
        }
    }

    public static void categoryeditor(boolean isexpense){
        if(isexpense){
            typeExp.setVisible(true);
            shopping.setVisible(true);
            fad.setVisible(true);
            bills.setVisible(true);
            other.setVisible(true);
            otherico1.setVisible(true);
            homeico1.setVisible(true);
            cutico1.setVisible(true);
            shopico1.setVisible(true);
        }
        else{
            typeExp.setVisible(false);
            shopping.setVisible(false);
            fad.setVisible(false);
            bills.setVisible(false);
            other.setVisible(false);
            otherico1.setVisible(false);
            homeico1.setVisible(false);
            cutico1.setVisible(false);
            shopico1.setVisible(false);
        }
    }
    public static void getData(String useremail, boolean isexpense) {
        try {
            if (isexpense) {
                System.out.println(isexpense);
                expensePanelHead.setBackground(new Color(236, 19, 24));
                submit.setBackground(new Color(236, 19, 24));
                contentPane.add(dynExpensePanel);
                categoryeditor(isexpense);
                contentPane.add(billsPanel);
                contentPane.add(otherPanel);
                contentPane.add(foodPanel);
                contentPane.add(shoppingPanel);
                contentPane.add(balancePanel);
                contentPane.add(tableData);

                tableData.remove(scrollPane);
                String query = "SELECT purpose, category, amount, date, transactype FROM newdatabase WHERE email = '" + useremail + "'";
                ConnectorSQL c = new ConnectorSQL();
                ResultSet rs = c.s.executeQuery(query);

                Vector<String> columnNames = new Vector<>(Arrays.asList("Purpose", "Category", "Amount", "Date"));
                Vector<Vector<Object>> data = new Vector<>();

                displayshop = 0;
                displaybal = 0;
                displayfood = 0;
                displaybill = 0;
                displayother = 0;
                // Load the data into a temporary vector in reverse order
                while (rs.next()) {
                    Vector<Object> row = new Vector<>();
                    row.add(rs.getString("purpose"));
                    String categcheck = rs.getString("category");
                    row.add(categcheck);

                    double displayupdater = rs.getDouble("amount");

                    switch (categcheck) {
                        case "Shopping" -> displayshop += displayupdater;
                        case "Bills" -> displaybill += displayupdater;
                        case "Food" -> displayfood += displayupdater;
                        case "Other" -> displayother += displayupdater;
                    }
                    //                System.out.println("Shopping update - "+displayshop);
                    System.out.println("Food update - " + displayfood);
                    //                System.out.println("Shopping update - "+displayshop);


                    String transactType = rs.getString("transactype");
                    displaybal += transactType.equals("Withdraw") ? (-displayupdater) : displayupdater;
                    String amount = "<html><font color='" + (transactType.equals("Withdraw") ? "red" : "green") + "'>" + rs.getString("amount") + "</font></html>";

                    row.add(amount);
                    row.add(rs.getString("date"));

                    // Add the row to the beginning of the data vector
                    data.add(0, row);
                }

                DefaultTableModel model = new DefaultTableModel(data, columnNames) {
                    @Override
                    public Class<?> getColumnClass(int column) {
                        return String.class;
                    }
                };

                table.setModel(model);

                // Set row height
                table.setRowHeight(30);

                // Beautify the table layout
                JTableHeader tableHeader = table.getTableHeader();
                tableHeader.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
                tableHeader.setBackground(Color.WHITE);
                tableHeader.setReorderingAllowed(false);

                table.setSelectionBackground(new Color(232, 57, 95));
                table.setSelectionForeground(Color.WHITE);

                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

                // Custom cell renderer for the "Category" column to change the background color
                table.getColumnModel().getColumn(1).setCellRenderer(new CategoryCellRenderer());
                tableData.add(scrollPane);
                balancedisplayer.setText(String.valueOf(displaybal));
                shopdisplayer.setText(String.valueOf(displayshop));
                fooddisplayer.setText(String.valueOf(displayfood));
                billdisplayer.setText(String.valueOf(displaybill));
                otherdisplayer.setText(String.valueOf(displayother));
                frame.repaint();
                frame.revalidate();
            }
            else{
                contentPane.remove(billsPanel);
                contentPane.remove(otherPanel);
                contentPane.remove(foodPanel);
                contentPane.remove(shoppingPanel);
                contentPane.remove(balancePanel);
                contentPane.remove(tableData);
                categoryeditor(isexpense);
                submit.setBackground(new Color(51, 204, 51));
                expensePanelHead.setBackground(new Color(51, 204, 51));
            }
        }
        catch (Exception e) {
                e.printStackTrace();
        }

    }

    private static Color getCategoryColor(String category) {
        Map<String, Color> colorMap = new HashMap<>();
        colorMap.put("Shopping", new Color(0,204,204));
        colorMap.put("Food", new Color(252,198,2));
        colorMap.put("Bills", new Color(236, 19, 24));
        colorMap.put("Other", Color.black);
        colorMap.put("Deposit",new Color(51, 204, 51));

        return colorMap.getOrDefault(category, Color.black);
    }

    private static class CategoryCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setOpaque(true);

            // Get the category text
            String categoryText = (value != null) ? value.toString() : "";
            Color categoryColor = getCategoryColor(categoryText);

            label.setBackground(categoryColor);
            label.setForeground(Color.WHITE);

            return label;
        }
    }


//    public static void getData(String useremail){
//        try {
//            String query = "select * from newdatabase where email = '"+useremail+"'";
//            ConnectorSQL c = new ConnectorSQL();
//            ResultSet rs = c.s.executeQuery(query);
//
//            Vector<String> columnNames = new Vector<>();
//            Vector<Vector<Object>> data = new Vector<>();
//
//            ResultSetMetaData metaData = rs.getMetaData();
//            int columnCount = metaData.getColumnCount();
//
//            for (int i = 1; i <= columnCount; i++) {
//                columnNames.add(metaData.getColumnName(i));
//            }
//
//            while (rs.next()) {
//                Vector<Object> row = new Vector<>();
//                for (int i = 1; i <= columnCount; i++) {
//                    row.add(rs.getObject(i));
//                }
//                data.add(row);
//            }
//
//            table.setModel(new DefaultTableModel(data, columnNames));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        new Dashboard("tan@gmail.com");
    }
    //
}
