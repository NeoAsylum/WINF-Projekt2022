package UI;

import java.awt.BorderLayout;

import Backend.Hauptklasse;
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
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.JTextField;

public class UI extends JFrame {

    private JPanel contentPane;
    private JTabbedPane tabbedPane;
    private JScrollPane einlagern;
    private JScrollPane inventar;
    private JScrollPane bestellliste;
    private JTable table;
    private JPanel suche;
    private JPanel panel_1;
    private JPanel panel_2;
    private JScrollPane scrollPane;
    private JTable table_1;
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
        panel_1.add(deleteSuche);

        btnNewButton = new JButton("Export");
        panel_1.add(btnNewButton);

        panel_2 = new JPanel();
        suche.add(panel_2, BorderLayout.NORTH);
        String[] arr = new String[] { "Grafikkarte", "Festplatte", "Hauptspeicher",
                "Fertigprodukt", "CPU" };
        dropdownSuche1 = new JComboBox<String>();
        for (String a : arr) {
            dropdownSuche1.addItem(a);
        }
        dropdownSuche1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateDropdown2();
            }
        });
        panel_2.add(dropdownSuche1);

        dropdownSuche2 = new JComboBox<String>();
        dropdownSuche2.addItem("");
        dropdownSuche2.setSelectedIndex(0);;
        panel_2.add(dropdownSuche2);

        textField = new JTextField();
        panel_2.add(textField);
        textField.setColumns(10);

        dropdownSuche3 = new JComboBox<String>();
        dropdownSuche3.addItem("");
        dropdownSuche3.setSelectedIndex(0);;

        panel_2.add(dropdownSuche3);

        textField_1 = new JTextField();
        panel_2.add(textField_1);
        textField_1.setColumns(10);

        dropdownSuche4 = new JComboBox<String>();
        dropdownSuche4.addItem("");
        dropdownSuche4.setSelectedIndex(0);;

        panel_2.add(dropdownSuche4);

        textField_2 = new JTextField();
        panel_2.add(textField_2);
        textField_2.setColumns(10);

        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                query();
            }
        });
        panel_2.add(okButton);

        scrollPane = new JScrollPane();
        suche.add(scrollPane, BorderLayout.CENTER);

        table_1 = new JTable();
        scrollPane.setViewportView(table_1);

        einlagern = new JScrollPane();
        tabbedPane.addTab("Einlagern", null, einlagern, null);

        inventar = new JScrollPane();
        tabbedPane.addTab("Inventar", null, inventar, null);

        bestellliste = new JScrollPane();
        tabbedPane.addTab("Bestelliste", null, bestellliste, null);

        table = new JTable();
        setGrakaTable(input);
        scrollPane.setViewportView(table);

    }

    /*
     * Methode erzeugt Table neu.
     */
    public void setGrakaTable(Object[][] input) {
        Object[][] data = Arrays.copyOfRange(input, 1, input.length);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        model=new DefaultTableModel(data, input[0]);
        table.setModel(model);
    }
    
    
    /*
     * Methode welche eine Query basierend auf dem Zustand des UI erstellt.
     */
    public void query() {
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
        String sqlQuery = "SELECT ";
        for (int i = 0; i < p.getTabelleneintraege().length; i++) {
            sqlQuery += p.getTabelleneintraege()[i] + ", ";
        }
        sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 2);
        sqlQuery += " FROM " + p.produktTyp() + " ";
        int added=0;
        if (!(dropdownSuche2.getSelectedItem().toString().length() <= 1)) {
            sqlQuery += "WHERE " + dropdownSuche2.getSelectedItem() + "='" + textField.getText()
                    + "', ";
            added=1;
        }
        if (!(dropdownSuche3.getSelectedItem().toString().length() <= 1)) {
            sqlQuery += "WHERE " + dropdownSuche3.getSelectedItem() + "='" + textField_1.getText()
                    + "', ";
            added=1;

        }
        if (!(dropdownSuche4.getSelectedItem().toString().length() <= 1)) {
            System.out.println(dropdownSuche2.getSelectedItem().toString());
            sqlQuery += "WHERE " + dropdownSuche4.getSelectedItem() + "='" + textField_2.getText()
                    + "', ";
            added=1;

        }
        sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1 - added) + ";";
        sqlQuery = p == null ? null : sqlQuery;
        System.out.println(sqlQuery);
        try {
            Hauptklasse.aQuery(p, sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        for (String a : p.getTabelleneintraege()) {
            dropdownSuche2.addItem(a);
            dropdownSuche3.addItem(a);
            dropdownSuche4.addItem(a);
            System.out.println(a);
        }
    }
}
