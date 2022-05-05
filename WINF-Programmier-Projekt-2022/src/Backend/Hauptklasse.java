package Backend;

import UI.Login;
import UI.UI;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Hauptklasse {

	static UI frame;

	public static final Logger log = Logger.getLogger(Hauptklasse.class.getName());

	public static void main(String[] args) {
		
		Login login = new Login();	
		login.setVisible(true);
		
		
		
	}
	
	
	

}
