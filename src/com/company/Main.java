package com.company;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Example");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
    }
    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel hostlable=new JLabel("Host:");
        hostlable.setBounds(10,20,80,25);
        panel.add(hostlable);

        JTextField hostname=new JTextField(20);
        hostname.setBounds(100,20,165,25);
        panel.add(hostname);

        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(10,50,80,25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100,50,165,25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10,80,80,25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,80,165,25);
        panel.add(passwordText);

        JLabel authtype = new JLabel("Auth Type");
        authtype.setBounds(10,110,80,25);
        panel.add(authtype);

        JComboBox comboBox=new JComboBox();
        comboBox.setBounds(100,110,165,25);
        comboBox.addItem("Windows Authentication");
        comboBox.addItem("SQL Server Authentication");
        panel.add(comboBox);

        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 140, 80, 25);
        panel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("提交，密码为: " + new String(passwordText.getPassword()));
                System.out.println(new String(userText.getText()));
                JOptionPane jop=new JOptionPane();
                panel.add(jop);

                if(comboBox.getSelectedItem()=="Windows Authentication")
                {
                    String connectionUrl2="jdbc:sqlserver://"+new String(hostname.getText())+":1433;databaseName=Person;";
                    System.out.print("Connecting to SQL Server ... ");
                    try (Connection connection = DriverManager.getConnection(connectionUrl2)) {
                        System.out.println("Done.");
                        jop.showMessageDialog(null,"Connection successfully!");
                    }
                catch (SQLException g) {
                    System.out.println("Connection failed!");
                    jop.showMessageDialog(null,"Connection failed!");
                }
                }
                else if (comboBox.getSelectedItem()=="SQL Server Authentication")
                {
                    try {
                        // Load SQL Server JDBC driver and establish connection.
                        String connectionUrl="jdbc:sqlserver://"+new String(hostname.getText())+":1433;databaseName=Person;user="+new String(userText.getText())+";password="+new String(passwordText.getPassword());
                        System.out.print("Connecting to SQL Server ... ");
                        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
                            System.out.println("Done.");
                            jop.showMessageDialog(null,"Connection successfully!");
                        }
                    } catch (SQLException d) {
                        System.out.println("Connection failed!");
                        jop.showMessageDialog(null,"Connection failed!");
                    }
                }
            }
        });
    }
}
