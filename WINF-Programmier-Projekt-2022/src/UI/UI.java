package UI;

import java.awt.BorderLayout;
import Backend.QueryOutputHandling;
import Backend.SQL;
import Datentypen.CPU;
import Datentypen.Fertigprodukt;
import Datentypen.Festplatte;
import Datentypen.Grafikkarte;
import Datentypen.Hauptspeicher;
import Datentypen.Produkt;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UI extends JFrame {
    enum tabs {
        Suche, Einlagerung
    };

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
    private JPanel einlagern;
    private JPanel panel;
    private JPanel panel_3;
    private JComboBox<String> dropdownSuche1_1;
    JScrollPane scrollPane_2;
    private JButton btnNewButton_1;
    private JTable einlagerungsTable;
    private JPanel einlagern_1;
    private JPanel panel_4;
    private JPanel panel_5;
    private JButton exportieren;
    private JScrollPane scrollPane_1;
    private JPanel einlagern_2;
    private JPanel panel_6;
    private JButton exportieren_1;
    private JScrollPane scrollPane_3;
    private JTable table_1;
    private JButton aktualisieren_1;
    private JButton okSuche;

    public UI() {
        Object[][] input = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" } };
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
        deleteSuche.addActionListener(e -> deletion());
        deleteSuche.setToolTipText("put x in 'delete' column");
        panel_1.add(deleteSuche);
        btnNewButton = new JButton("Export");
        panel_1.add(btnNewButton);
        panel_2 = new JPanel();
        suche.add(panel_2, BorderLayout.NORTH);
        String[] arr = new String[] { "Grafikkarte", "Festplatte", "Hauptspeicher",
                "Fertigprodukt", "CPU" };
        dropdownSuche1 = new JComboBox<String>();
        dropdownSuche1.addItem("");
        for (String a : arr) {
            dropdownSuche1.addItem(a);
        }
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

        okSuche = new JButton("OK");

        panel_2.add(okSuche);
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

        panel_3 = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
        flowLayout_1.setAlignment(FlowLayout.RIGHT);
        einlagern.add(panel_3, BorderLayout.SOUTH);

        btnNewButton_1 = new JButton("Lagerplaetze ausgeben");
        btnNewButton_1.addActionListener(e -> queryAdd());
        panel_3.add(btnNewButton_1);

        JScrollPane scrollPane_2 = new JScrollPane();
        einlagern.add(scrollPane_2, BorderLayout.CENTER);

        einlagerungsTable = new JTable();
        scrollPane_2.setViewportView(einlagerungsTable);

        einlagern_1 = new JPanel();
        tabbedPane.addTab("Inventar", null, einlagern_1, null);
        einlagern_1.setLayout(new BorderLayout(0, 0));

        panel_4 = new JPanel();
        einlagern_1.add(panel_4, BorderLayout.NORTH);

        panel_5 = new JPanel();
        einlagern_1.add(panel_5, BorderLayout.SOUTH);

        exportieren = new JButton("Exportieren");
        panel_5.add(exportieren);

        scrollPane_1 = new JScrollPane();
        einlagern_1.add(scrollPane_1, BorderLayout.WEST);

        einlagern_2 = new JPanel();
        tabbedPane.addTab("Bestellliste", null, einlagern_2, null);
        einlagern_2.setLayout(new BorderLayout(0, 0));

        panel_6 = new JPanel();
        einlagern_2.add(panel_6, BorderLayout.SOUTH);

        exportieren_1 = new JButton("Exportieren");
        panel_6.add(exportieren_1);

        aktualisieren_1 = new JButton("Aktualisieren");
        panel_6.add(aktualisieren_1);

        aktualisieren_1.addActionListener(e ->

        produktFuerSuche("Grafikkarte"));

        scrollPane_3 = new JScrollPane();
        einlagern_2.add(scrollPane_3, BorderLayout.CENTER);

        table_1 = new JTable();
        scrollPane_3.setViewportView(table_1);
        dropdownSuche1_1 = new JComboBox<String>();
        for (String a : arr) {
            dropdownSuche1_1.addItem(a);
        }
        panel.add(dropdownSuche1_1);

    }

    public void addActionListeners() {
        dropdownSuche1_1.addActionListener(e -> updateEinlagerungsTable());
        dropdownSuche1.addActionListener(e -> querySuche());
        dropdownSuche1.addActionListener(e -> updateDropdown2());
        okSuche.addActionListener(e -> querySuche());
    }

    /**
     * Create the frame.
     * 
     * @wbp.parser.constructor
     */
    public void setup(Object[][] input) {
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
        deleteSuche.addActionListener(e -> deletion());
        deleteSuche.setToolTipText("put x in 'delete' column");
        panel_1.add(deleteSuche);
        btnNewButton = new JButton("Export");
        panel_1.add(btnNewButton);
        panel_2 = new JPanel();
        suche.add(panel_2, BorderLayout.NORTH);
        String[] arr = new String[] { "Grafikkarte", "Festplatte", "Hauptspeicher",
                "Fertigprodukt", "CPU" };
        dropdownSuche1 = new JComboBox<String>();
        dropdownSuche1.addItem("");
        for (String a : arr) {
            dropdownSuche1.addItem(a);
        }
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

        panel_3 = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
        flowLayout_1.setAlignment(FlowLayout.RIGHT);
        einlagern.add(panel_3, BorderLayout.SOUTH);

        btnNewButton_1 = new JButton("Lagerplaetze ausgeben");
        btnNewButton_1.addActionListener(e -> queryAdd());
        panel_3.add(btnNewButton_1);

        JScrollPane scrollPane_2 = new JScrollPane();
        einlagern.add(scrollPane_2, BorderLayout.CENTER);

        einlagerungsTable = new JTable();
        scrollPane_2.setViewportView(einlagerungsTable);

        einlagern_1 = new JPanel();
        tabbedPane.addTab("Inventar", null, einlagern_1, null);
        einlagern_1.setLayout(new BorderLayout(0, 0));

        panel_4 = new JPanel();
        einlagern_1.add(panel_4, BorderLayout.NORTH);

        panel_5 = new JPanel();
        einlagern_1.add(panel_5, BorderLayout.SOUTH);

        exportieren = new JButton("Exportieren");
        panel_5.add(exportieren);

        scrollPane_1 = new JScrollPane();
        einlagern_1.add(scrollPane_1, BorderLayout.WEST);

        einlagern_2 = new JPanel();
        tabbedPane.addTab("Bestellliste", null, einlagern_2, null);
        einlagern_2.setLayout(new BorderLayout(0, 0));

        panel_6 = new JPanel();
        einlagern_2.add(panel_6, BorderLayout.SOUTH);

        exportieren_1 = new JButton("Exportieren");
        panel_6.add(exportieren_1);

        aktualisieren_1 = new JButton("Aktualisieren");
        panel_6.add(aktualisieren_1);
        
        aktualisieren_1.addActionListener(e->{
        	
        	//table_1
        	//queryToUI(String query, String oberflaeche, String[] tabelleneintraege)
        });
        
        
       
        




        scrollPane_3 = new JScrollPane();
        einlagern_2.add(scrollPane_3, BorderLayout.CENTER);

        table_1 = new JTable();
        scrollPane_3.setViewportView(table_1);
        dropdownSuche1_1 = new JComboBox<String>();
        dropdownSuche1_1.addActionListener(e -> updateEinlagerungsTable());
        for (String a : arr) {
            dropdownSuche1_1.addItem(a);
        }
        panel.add(dropdownSuche1_1);
        dropdownSuche1.addActionListener(e -> querySuche());
        dropdownSuche1.addActionListener(e -> updateDropdown2());
        dropdownSuche4.addActionListener(e -> updateDropdown2());
        dropdownSuche2.addActionListener(e -> updateDropdown2());
        dropdownSuche3.addActionListener(e -> updateDropdown2());
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
    
    public void setBestellTable(Object[][] input) {
    	
   	 Object[][] data = Arrays.copyOfRange(input, 1, input.length);
        DefaultTableModel model = (DefaultTableModel) table_1.getModel();
        model.setRowCount(0);
        model = new DefaultTableModel(data, input[0]);
        table_1.setModel(model);
   	
   }

    /*
     * Methode welche von einem Produktnamen ausgehend ein Produkt erstellt.
     */
    public Produkt produktFuerSuche(String dropdownname) {
        Produkt p;
        System.out.println(dropdownname);
        switch (dropdownname) {
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
        case "Fertigprodukt":
            p = new Fertigprodukt();
            break;
        default:
            p = new Grafikkarte();
            break;
        }
        return p;
    }

    /*
     * Methode welche Query zum Loeschen der in der Suchtabelle angezeigten
     * Komponenten erstellt.
     */
    public void deletion() {
        Produkt p = produktFuerSuche(dropdownSuche1.getSelectedItem().toString());
        if (p != null) {
            String sqlQuery = "DELETE ";

            sqlQuery += " FROM " + p.produktTyp() + " ";
            int added = 0;
            if (!(dropdownSuche2.getSelectedItem().toString().length() <= 1)) {
                sqlQuery += dropdownSuche2.getSelectedItem().equals("ID")
                        ? dropdownSuche2.getSelectedItem() + "=" + textField.getText() + " AND "
                        : dropdownSuche2.getSelectedItem() + "='" + textField.getText() + "' AND ";
                added = 1;
            }
            if (!(dropdownSuche3.getSelectedItem().toString().length() <= 1)) {
                if (added == 0) {
                    sqlQuery += "WHERE ";
                }
                sqlQuery += dropdownSuche3.getSelectedItem().equals("ID")
                        ? dropdownSuche3.getSelectedItem() + "=" + textField_1.getText() + " AND "
                        : dropdownSuche3.getSelectedItem() + "='" + textField_1.getText()
                                + "' AND ";
                added = 1;
            }
            if (!(dropdownSuche4.getSelectedItem().toString().length() <= 1)) {
                if (added == 0) {
                    sqlQuery += "WHERE ";
                }
                sqlQuery += dropdownSuche4.getSelectedItem().equals("ID")
                        ? dropdownSuche4.getSelectedItem() + "=" + textField_2.getText() + " "
                        : dropdownSuche4.getSelectedItem() + "='" + textField_2.getText() + "' ";
                added = 0;
            }
            sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1 - added * 4) + ";";
            sqlQuery = p == null ? null : sqlQuery;
            Object[] options = { "Yes", "No" };
            int n = JOptionPane.showOptionDialog(this, "Delete current Selection?", "Delete?",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
                    options[1]);
            if (n == 1) {
                SQL.update(sqlQuery);
                QueryOutputHandling.nonsenseQuery();
            }
        }
    }

    /*
     * Methode welche eine Such-Query basierend auf dem Zustand des UI erstellt.
     */
    public void querySuche() {
        Produkt p = produktFuerSuche(dropdownSuche1.getSelectedItem().toString());
        if (p != null) {
            String sqlQuery = "SELECT ";
            for (int i = 0; i < p.getTabelleneintraege().length; i++) {
                sqlQuery += p.getTabelleneintraege()[i] + ", ";
            }
            sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 2);
            sqlQuery += " FROM " + p.produktTyp() + " ";
            int added = 0;
            if (!(dropdownSuche2.getSelectedItem().toString().length() <= 1)) {
                System.out.println(sqlQuery);

                sqlQuery += dropdownSuche2.getSelectedItem().equals("ID")
                        ? dropdownSuche2.getSelectedItem() + "=" + textField.getText() + " AND "
                        : dropdownSuche2.getSelectedItem() + "='" + textField.getText() + "' AND ";
                added = 1;
            }
            if (!(dropdownSuche3.getSelectedItem().toString().length() <= 1)) {
                if (added == 0) {
                    sqlQuery += "WHERE ";
                }
                System.out.println(sqlQuery);

                sqlQuery += dropdownSuche3.getSelectedItem().equals("ID")
                        ? dropdownSuche3.getSelectedItem() + "=" + textField_1.getText() + " AND "
                        : dropdownSuche3.getSelectedItem() + "='" + textField_1.getText()
                                + "' AND ";
                added = 1;
            }
            if (!(dropdownSuche4.getSelectedItem().toString().length() <= 1)) {
                if (added == 0) {
                    sqlQuery += "WHERE ";
                }
                System.out.println(sqlQuery);

                sqlQuery += dropdownSuche4.getSelectedItem().equals("ID")
                        ? dropdownSuche4.getSelectedItem() + "=" + textField_2.getText() + " "
                        : dropdownSuche4.getSelectedItem() + "='" + textField_2.getText() + "' ";
                added = 0;
                System.out.println(sqlQuery);

            }
            sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1 - added * 4) + ";";
            sqlQuery = p == null ? null : sqlQuery;
            QueryOutputHandling.queryToUI(sqlQuery, "Suche", p.getTabelleneintraege());
        }
    }

    /*
     * Methode welche die in die Einlagern-Tabelle eingefuegten Daten bei
     * Vollständigkeit Reihenweise in die SQL-Datenbank einfuegt.
     */
    public void queryAdd() {
        for (int k = 0; k < einlagerungsTable.getRowCount(); k++) {
            Produkt p = produktFuerSuche(dropdownSuche1_1.getSelectedItem().toString());
            boolean allRowsFull = true;
            for (int i = 0; i < einlagerungsTable.getColumnCount(); i++) {
                allRowsFull = !einlagerungsTable.getModel().getValueAt(k, i).equals("");
            }
            if (allRowsFull && p != null) {
                String sqlQuery = "INSERT INTO " + p.produktTyp() + " (";
                for (int i = 0; i < p.getTabelleneintraege().length - 2; i++) {
                    sqlQuery += p.getTabelleneintraege()[i] + ",";
                }
                sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1) + ",LAGERPLATZ)"
                        + System.lineSeparator() + "VALUES (";

                for (int i = 0; i < p.getTabelleneintraege().length - 2; i++) {
                    System.out.println(einlagerungsTable.getModel().getValueAt(k, i));
                    sqlQuery += "'" + einlagerungsTable.getModel().getValueAt(k, i) + "', ";
                }
                sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 2) + ", "
                        + SQL.getLagerplatzID(p) + " );";
                System.out.println(sqlQuery);
                SQL.update(sqlQuery);
                if(!p.produktTyp().equals("Fertigprodukt")) {
                    SQL.anzahlImLagerHochzählen(SQL.getLagerplatzID(p), p.produktTyp());
                }
                QueryOutputHandling.nonsenseQuery();

            } else {
            }
        }
    }

    /*
     * Methode welche eine Query basierend auf dem Zustand des UI erstellt.
     */
    public void updateEinlagerungsTable() {
        Produkt p = produktFuerSuche(dropdownSuche1_1.getSelectedItem().toString());
        QueryOutputHandling.queryToUI(
                "SELECT " + "Name, VRAM, Hersteller "
                        + "FROM GRAFIKKARTE WHERE HERSTELLER='ABCDEFG';",
                "Einlagerung", Arrays.copyOfRange(p.getTabelleneintraege(), 0,
                        p.getTabelleneintraege().length - 2));
    }

    /*
     * Tabelle in Tab Einlagerung wird gefuellt.
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
     * Methode welche die JComboBoxen refresht.
     */
    public void updateDropdown2() {
        Produkt p = produktFuerSuche(dropdownSuche1.getSelectedItem().toString());
        dropdownSuche2.removeAllItems();
        dropdownSuche3.removeAllItems();
        dropdownSuche4.removeAllItems();
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
