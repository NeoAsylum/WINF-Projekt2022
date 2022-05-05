package UI;

import java.awt.BorderLayout;
import Backend.QueryOutputHandling;
import Datentypen.CPU;
import Datentypen.Festplatte;
import Datentypen.Grafikkarte;
import Datentypen.Hauptspeicher;
import Datentypen.Produkt;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.util.Arrays;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.JTextField;

public class UI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JTable table;
	private JPanel suche;
	private JPanel panel_1;
	private JPanel panel_2;
	private JScrollPane scrollPane;
	private JButton btnNewButton;
	private JComboBox<String> dropdownSuche1;
	private JComboBox<String> dropdownSuche2;
	private JComboBox<String> dropdownSuche3;
	private JComboBox<String> dropdownSuche4;
	private JButton deleteSuche;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton okButton;
	private JPanel einlagern;
	private JPanel panel;
	private JPanel panel_3;
	private JComboBox<String> dropdownSuche1_1;
	private JButton okButton_1;
	private JScrollPane scrollPane_1;
	private JButton btnNewButton_1;
	private JTable einlagerungsTable;

	public UI() {

	}

	/**
	 * Create the frame.
	 * 
	 * @wbp.parser.constructor
	 */
	public UI(Object[][] input) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 987, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		suche = new JPanel();
		tabbedPane.addTab("Suche", null, suche, null);                

		suche.setLayout(new BorderLayout(0, 0));
		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		suche.add(panel_1, BorderLayout.SOUTH);
		deleteSuche = new JButton("delete");
		deleteSuche.setToolTipText("put x in 'delete' column");
		panel_1.add(deleteSuche);
		btnNewButton = new JButton("Export");
		panel_1.add(btnNewButton);
		panel_2 = new JPanel();
		suche.add(panel_2, BorderLayout.NORTH);
		String[] arr = new String[] { "Grafikkarte", "Festplatte", "Hauptspeicher", "Fertigprodukt", "CPU" };
		dropdownSuche1 = new JComboBox<String>();
		dropdownSuche1.addItem("");
		for (String a : arr) {
			dropdownSuche1.addItem(a);
		}
		dropdownSuche1.addActionListener(e -> updateDropdown2());
		panel_2.add(dropdownSuche1);
		dropdownSuche2 = new JComboBox<String>();
		dropdownSuche2.addItem("");
		dropdownSuche2.setSelectedIndex(0);
		panel_2.add(dropdownSuche2);
		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(10);
		dropdownSuche3 = new JComboBox<String>();
		dropdownSuche3.addItem("");
		dropdownSuche3.setSelectedIndex(0);
		panel_2.add(dropdownSuche3);
		textField_1 = new JTextField();
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		dropdownSuche4 = new JComboBox<String>();
		dropdownSuche4.addItem("");
		dropdownSuche4.setSelectedIndex(0);
		panel_2.add(dropdownSuche4);
		textField_2 = new JTextField();
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		okButton = new JButton("OK");
		okButton.addActionListener(e -> querySuche());
		panel_2.add(okButton);
		scrollPane = new JScrollPane();
		suche.add(scrollPane, BorderLayout.CENTER);
		table = new JTable();
		setSuchTable(input);
		scrollPane.setViewportView(table);

		einlagern = new JPanel();
		tabbedPane.addTab("Einlagern", null, einlagern, null);
		einlagern.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		einlagern.add(panel, BorderLayout.NORTH);

		dropdownSuche1_1 = new JComboBox<String>();
		for (String a : arr) {
			dropdownSuche1_1.addItem(a);
		}
		panel.add(dropdownSuche1_1);

		okButton_1 = new JButton("OK");
		panel.add(okButton_1);
		okButton_1.addActionListener(e -> queryEinlagern());

		panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		einlagern.add(panel_3, BorderLayout.SOUTH);

		btnNewButton_1 = new JButton("Lagerplaetze ausgeben");
		btnNewButton_1.addActionListener(e -> queryEinlagern());
		panel_3.add(btnNewButton_1);

		scrollPane_1 = new JScrollPane();
		einlagern.add(scrollPane_1, BorderLayout.CENTER);

		einlagerungsTable = new JTable();
		scrollPane_1.setViewportView(einlagerungsTable);
	}

	/*
	 * Methode erzeugt Table neu.
	 */
	public void setSuchTable(Object[][] input) {
		Object[][] data = Arrays.copyOfRange(input, 1, input.length);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		model = new DefaultTableModel(data, input[0]);
		table.setModel(model);
	}

	/*
	 * Methode welche eine Query basierend auf dem Zustand des UI erstellt.
	 */
	public void querySuche() {
		Produkt p;
		switch (dropdownSuche1.getSelectedItem().toString()) {
		case "Grafikkarte":
			p = new Grafikkarte();
			break;
		case "Festplatte":
			p = new Festplatte();
			break;
		case "Hauptspeicher":
			p = new Hauptspeicher();
			break;
		case "CPU":
			p = new CPU();
			break;
		default:
			p = null;
			break;
		}
		if (p != null) {
			String sqlQuery = "SELECT ";
			for (int i = 0; i < p.getTabelleneintraege().length; i++) {
				sqlQuery += p.getTabelleneintraege()[i] + ", ";
			}
			sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 2);
			sqlQuery += " FROM " + p.produktTyp() + " ";
			int added = 0;
			if (!(dropdownSuche2.getSelectedItem().toString().length() <= 1)) {
				sqlQuery += "WHERE " + dropdownSuche2.getSelectedItem() + "='" + textField.getText() + "' AND ";
				added = 1;
			}
			if (!(dropdownSuche3.getSelectedItem().toString().length() <= 1)) {
				if (added == 0) {
					sqlQuery += "WHERE ";
				}
				sqlQuery += dropdownSuche3.getSelectedItem() + "='" + textField_1.getText() + "' AND ";
				added = 1;

			}
			if (!(dropdownSuche4.getSelectedItem().toString().length() <= 1)) {
				if (added == 0) {
					sqlQuery += "WHERE ";
				}
				sqlQuery += dropdownSuche4.getSelectedItem() + "='" + textField_2.getText() + "'";
				added = 0;
			}
			sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1 - added * 4) + ";";
			sqlQuery = p == null ? null : sqlQuery;
			QueryOutputHandling.queryToUI(sqlQuery, "Suche", p.getTabelleneintraege());
		}
	}

	/*
	 * Methode welche eine Query basierend auf dem Zustand des UI erstellt.
	 */
	public void queryEinlagern() {
		Produkt p;
		switch (dropdownSuche1_1.getSelectedItem().toString()) {
		case "Grafikkarte":
			p = new Grafikkarte();
			break;
		case "Festplatte":
			p = new Festplatte();
			break;
		case "Hauptspeicher":
			p = new Hauptspeicher();
			break;
		case "CPU":
			p = new CPU();
			break;
		default:
			p = null;
			break;
		}
		QueryOutputHandling.queryToUI("SELECT " + "Name, VRAM, Hersteller " + "FROM GRAFIKKARTEN WHERE HERSTELLER='ABCDEFG';",
				"Einlagerung", p.getTabelleneintraege());
	}

	/*
	 * 
	 */
	public void setEinlagerungTable(Object[][] input) {
		Object[][] data = new Object[8][input[0].length];
		for (int i = 0; i < input[0].length; i++) {
			for (int a = 0; a < 8; a++) {
				data[a][i] = "";
			}
		}

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		model = new DefaultTableModel(data, input[0]);
		einlagerungsTable.setModel(model);
	}

	/*
	 * Methode welche die JComboBoxen refresht
	 */
	public void updateDropdown2() {
		Produkt p;
		dropdownSuche2.removeAllItems();
		dropdownSuche3.removeAllItems();
		dropdownSuche4.removeAllItems();

		switch (dropdownSuche1.getSelectedItem().toString()) {
		case "Grafikkarte":
			p = new Grafikkarte();
			break;
		case "Festplatte":
			p = new Festplatte();
			break;
		case "Hauptspeicher":
			p = new Hauptspeicher();
			break;
		case "CPU":
			p = new CPU();
			break;
		default:
			p = null;
			break;
		}
		dropdownSuche2.addItem("");
		dropdownSuche3.addItem("");
		dropdownSuche4.addItem("");
		if (p != null) {
			for (String a : p.getTabelleneintraege()) {
				dropdownSuche2.addItem(a);
				dropdownSuche3.addItem(a);
				dropdownSuche4.addItem(a);
			}
		}
	}
}
