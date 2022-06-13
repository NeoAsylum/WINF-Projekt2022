package ui;

import java.awt.BorderLayout; 

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.Hauptklasse;
import sql.sqlSetup;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class PasswortVergessen extends JFrame {
    private JPanel contentPane;
    private JTextField bn;
    private JPasswordField pw;
    private JPasswordField admin;
    private JButton btn;
    private String key = "admin";
    /**
     * Methode zum Handlen, wenn das Passwort vergessen wurde.
     */
    public PasswortVergessen() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{211, 211, 0};
        gbl_contentPane.rowHeights = new int[]{70, 70, 70, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);
        
        JLabel lblNewLabel = new JLabel("Admin-Schl\u00FCssel:");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        contentPane.add(lblNewLabel, gbc_lblNewLabel);
        
        admin = new JPasswordField();
        admin.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_admin = new GridBagConstraints();
        gbc_admin.fill = GridBagConstraints.HORIZONTAL;
        gbc_admin.insets = new Insets(0, 0, 5, 0);
        gbc_admin.gridx = 1;
        gbc_admin.gridy = 0;
        contentPane.add(admin, gbc_admin);
        
        JLabel lblNewLabel_1 = new JLabel("Benutzername:");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 0;
        gbc_lblNewLabel_1.gridy = 1;
        contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
        
        bn = new JTextField();
        bn.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_bn = new GridBagConstraints();
        gbc_bn.fill = GridBagConstraints.HORIZONTAL;
        gbc_bn.insets = new Insets(0, 0, 5, 0);
        gbc_bn.gridx = 1;
        gbc_bn.gridy = 1;
        contentPane.add(bn, gbc_bn);
        bn.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("neues Passwort:");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 0;
        gbc_lblNewLabel_2.gridy = 2;
        contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
        
        pw = new JPasswordField();
        pw.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_pw = new GridBagConstraints();
        gbc_pw.insets = new Insets(0, 0, 5, 0);
        gbc_pw.fill = GridBagConstraints.HORIZONTAL;
        gbc_pw.gridx = 1;
        gbc_pw.gridy = 2;
        contentPane.add(pw, gbc_pw);
        
        btn = new JButton("Zur\u00FCcksetzen");
        GridBagConstraints gbc_btn = new GridBagConstraints();
        gbc_btn.gridx = 1;
        gbc_btn.gridy = 3;
        contentPane.add(btn, gbc_btn);
        
        btn.addActionListener(e->{
        	JFrame frame = new JFrame();
        	if(admin.getText().equals(key)) {
        		Statement stmt;
				try {
					stmt = sqlSetup.getConn().createStatement();
				ResultSet res = stmt.executeQuery("SELECT * FROM passwoerter");
        		for(int i = 1; res.next(); i++) {;
        				if(res.getString(1).equals(bn.getText())) {
        					if(!pw.getText().equals("")) {
        						Statement stmt1 = sql.sqlSetup.getConn().createStatement();
        						stmt1.execute("UPDATE passwoerter SET passwort = '"+ pw.getText() + "' WHERE benutzername = '" + bn.getText() + "'");
        						JOptionPane.showMessageDialog(frame, "Ihr Passwort konnte erfolgreich zurückgesetzt werden");
        						dispose();
        						return;
        					}
        				}
        		}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

        	}
        	JOptionPane.showMessageDialog(frame, "Es ist ein Fehler beim Zurücksetzen aufgetreten");
    		dispose();
        	
        });
    }
}
