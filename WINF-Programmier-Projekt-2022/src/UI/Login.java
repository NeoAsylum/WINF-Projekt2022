package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.x.protobuf.MysqlxConnection.Close;

import Backend.SQL;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	/**
	 *
	 * Create the frame.
	 */
	//
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("Login:");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{212, 212, 0};
		gbl_panel_1.rowHeights = new int[]{51, 51, 51, 51, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel label_1 = new JLabel("");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.weighty = 1.0;
		gbc_label_1.weightx = 1.0;
		gbc_label_1.fill = GridBagConstraints.BOTH;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 0;
		panel_1.add(label_1, gbc_label_1);
		
		JLabel label_2 = new JLabel("");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.weighty = 1.0;
		gbc_label_2.weightx = 1.0;
		gbc_label_2.fill = GridBagConstraints.BOTH;
		gbc_label_2.insets = new Insets(0, 0, 5, 0);
		gbc_label_2.gridx = 1;
		gbc_label_2.gridy = 0;
		panel_1.add(label_2, gbc_label_2);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.weighty = 1.0;
		gbc_lblNewLabel_1.weightx = 1.0;
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		username = new JTextField();
		GridBagConstraints gbc_username = new GridBagConstraints();
		gbc_username.weighty = 1.0;
		gbc_username.weightx = 1.0;
		gbc_username.fill = GridBagConstraints.HORIZONTAL;
		gbc_username.insets = new Insets(0, 0, 5, 0);
		gbc_username.gridx = 1;
		gbc_username.gridy = 1;
		panel_1.add(username, gbc_username);
		username.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Passwort:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.weighty = 1.0;
		gbc_lblNewLabel_2.weightx = 1.0;
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		password = new JPasswordField();
		GridBagConstraints gbc_password = new GridBagConstraints();
		gbc_password.weighty = 1.0;
		gbc_password.weightx = 1.0;
		gbc_password.fill = GridBagConstraints.HORIZONTAL;
		gbc_password.insets = new Insets(0, 0, 5, 0);
		gbc_password.gridx = 1;
		gbc_password.gridy = 2;
		panel_1.add(password, gbc_password);
		
		JLabel label_3 = new JLabel("");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.weighty = 1.0;
		gbc_label_3.weightx = 1.0;
		gbc_label_3.fill = GridBagConstraints.BOTH;
		gbc_label_3.insets = new Insets(0, 0, 0, 5);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 3;
		panel_1.add(label_3, gbc_label_3);
		
		JLabel label_4 = new JLabel("");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.weighty = 1.0;
		gbc_label_4.weightx = 1.0;
		gbc_label_4.fill = GridBagConstraints.BOTH;
		gbc_label_4.gridx = 1;
		gbc_label_4.gridy = 3;
		panel_1.add(label_4, gbc_label_4);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{167, 89, 0};
		gbl_panel_2.rowHeights = new int[]{23, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel fehler_login = new JLabel("");
		GridBagConstraints gbc_fehler_login = new GridBagConstraints();
		gbc_fehler_login.weighty = 1.0;
		gbc_fehler_login.weightx = 1.0;
		gbc_fehler_login.insets = new Insets(0, 0, 0, 5);
		gbc_fehler_login.gridx = 0;
		gbc_fehler_login.gridy = 0;
		panel_2.add(fehler_login, gbc_fehler_login);
		
		JButton enter = new JButton("Enter");
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String benutzername = "Benutzer123";
                String passwort = "Passwort123";

               String name = String.valueOf(username.getText());
               String passwort1 = String.valueOf(password.getPassword());

               if(benutzername.equals(name) && passwort1.equals(passwort) ) {
            	   
            	   SQL.setup();
            	   
            	   dispose();
            	   

               }else{

                   String nachricht = "Benutzername oder Passwort ist flasch"; 

                   JOptionPane.showMessageDialog(null, nachricht);
                   
                   loeschen();

                   
               }
			}
		});
		GridBagConstraints gbc_enter = new GridBagConstraints();
		gbc_enter.weighty = 1.0;
		gbc_enter.weightx = 1.0;
		gbc_enter.anchor = GridBagConstraints.NORTHEAST;
		gbc_enter.gridx = 1;
		gbc_enter.gridy = 0;
		panel_2.add(enter, gbc_enter);
	}
	
	public void loeschen() {
		
	username.setText("");
	password.setText("");
			
	}
	
	
	

}


