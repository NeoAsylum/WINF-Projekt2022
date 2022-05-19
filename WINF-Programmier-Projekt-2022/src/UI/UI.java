package UI;

import java.awt.BorderLayout;

import Backend.Hauptklasse;
import Backend.QueryOutputHandling;
import Backend.SQL;
import Datentypen.CPU;
import Datentypen.Fertigprodukt;
import Datentypen.Festplatte;
import Datentypen.Grafikkarte;
import Datentypen.Hauptspeicher;
import Datentypen.Produkt;
import Export.Excel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.JTextField;

/**
 * Klasse fuer das grafische Nutzerinterface.
 * 
 * @author All-Stars
 *
 */
@SuppressWarnings("serial")
public class UI extends JFrame {
    enum tabs {
        Suche, Einlagerung
    };

    private JPanel contentPane;
    private JTabbedPane tabbedPane;
    private JTable tabelleSuche;
    private JPanel suche;
    private JPanel suchePanelButtonsUnten;
    private JPanel suchePanelButtonsOben;
    private JScrollPane scrollPaneSucheTabelle;
    private JButton btnNewButton;
    private JComboBox<String> dropdownSucheProdukttyp;
    private JComboBox<String> dropdownSucheAttribut1;
    private JComboBox<String> dropdownSucheAttribut2;
    private JComboBox<String> dropdownSucheAttribut3;
    private JButton deleteSuche;
    private JTextField textFieldSucheAttribut1;
    private JTextField textFieldSucheAttribut2;
    private JTextField textFieldSucheAttribut3;
    private JPanel einlagern;
    private JPanel panelEinlagerungButtonsOben;
    private JPanel panelEinlagerungButtonsUnten;
    private JComboBox<String> dropdownEinlagerungProdukttyp;
    JScrollPane scrollPane_2;
    private JButton btnNewButton_1;
    private JTable einlagerungsTabelle;
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
    private JPanel panel_7;
    private JComboBox<String> dropdownSuche1_2;
    private JComboBox<String> sprachwahl;

    /**
     * Fuegt alle UI-Elemente hinzu.
     */
    public UI() {

        // Frame setup
        Object[][] input = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" } };
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 987, 437);
        // content pane
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(0, 1, 0, 0));
        // tabs
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(tabbedPane);
        // suche
        suche = new JPanel();
        tabbedPane.addTab(Hauptklasse.uebersetzer.getUebersetzung("Suche"), null, suche, null);
        suche.setLayout(new BorderLayout(0, 0));
        suchePanelButtonsUnten = new JPanel();
        FlowLayout fl_suchePanelButtonsUnten = (FlowLayout) suchePanelButtonsUnten.getLayout();
        fl_suchePanelButtonsUnten.setAlignment(FlowLayout.RIGHT);
        suche.add(suchePanelButtonsUnten, BorderLayout.SOUTH);
        deleteSuche = new JButton(Hauptklasse.uebersetzer.getUebersetzung("Delete"));
        deleteSuche.addActionListener(e -> deletionSuchTabelle());

        sprachwahl = new JComboBox<String>();
        sprachwahl.addItem("");
        sprachwahl.addItem("English");
        sprachwahl.addItem("Deutsch");
        suchePanelButtonsUnten.add(sprachwahl);

        deleteSuche.setToolTipText("put x in 'delete' column");
        suchePanelButtonsUnten.add(deleteSuche);
        btnNewButton = new JButton(Hauptklasse.uebersetzer.getUebersetzung("Exportieren"));
        suchePanelButtonsUnten.add(btnNewButton);
        btnNewButton.addActionListener(e -> {
            Excel.exportieren(tabelleSuche);
        });
        suchePanelButtonsOben = new JPanel();
        suche.add(suchePanelButtonsOben, BorderLayout.NORTH);
        String[] arr = new String[] { "Grafikkarte", "Festplatte", "Hauptspeicher",
                "Fertigprodukt", "CPU" };
        // Dropdown 1 Suche
        dropdownSucheProdukttyp = new JComboBox<String>();
        dropdownSucheProdukttyp.addItem("");
        for (String a : arr) {
            dropdownSucheProdukttyp.addItem(a);
        }
        suchePanelButtonsOben.add(dropdownSucheProdukttyp);
        // Dropdown 2 Suche
        dropdownSucheAttribut1 = new JComboBox<String>();
        dropdownSucheAttribut1.addItem("");
        dropdownSucheAttribut1.setSelectedIndex(0);
        suchePanelButtonsOben.add(dropdownSucheAttribut1);
        // Textfield Suche
        textFieldSucheAttribut1 = new JTextField();
        textFieldSucheAttribut1.setColumns(11);
        suchePanelButtonsOben.add(textFieldSucheAttribut1);
        // dropdownSuche3 Suche
        dropdownSucheAttribut2 = new JComboBox<String>();
        dropdownSucheAttribut2.addItem("");
        dropdownSucheAttribut2.setSelectedIndex(0);
        suchePanelButtonsOben.add(dropdownSucheAttribut2);
        // textField Suche
        textFieldSucheAttribut2 = new JTextField();
        textFieldSucheAttribut2.setColumns(11);
        suchePanelButtonsOben.add(textFieldSucheAttribut2);
        // dropdownSuche4
        dropdownSucheAttribut3 = new JComboBox<String>();
        dropdownSucheAttribut3.addItem("");
        dropdownSucheAttribut3.setSelectedIndex(0);
        suchePanelButtonsOben.add(dropdownSucheAttribut3);
        // textField Suche
        textFieldSucheAttribut3 = new JTextField();
        textFieldSucheAttribut3.setColumns(11);
        suchePanelButtonsOben.add(textFieldSucheAttribut3);
        // ok Button Suche
        okSuche = new JButton("OK");
        suchePanelButtonsOben.add(okSuche);
        // scrollpane Suche
        scrollPaneSucheTabelle = new JScrollPane();
        suche.add(scrollPaneSucheTabelle, BorderLayout.CENTER);
        // suchtabelle
        tabelleSuche = new JTable();
        setSuchTable(input);
        scrollPaneSucheTabelle.setViewportView(tabelleSuche);
        // Einlagerung
        einlagern = new JPanel();
        tabbedPane.addTab(Hauptklasse.uebersetzer.getUebersetzung("Einlagern"), null, einlagern,
                null);
        einlagern.setLayout(new BorderLayout(0, 0));

        // panel Buttons oben
        panelEinlagerungButtonsOben = new JPanel();
        FlowLayout fl_panelEinlagerungButtonsOben = (FlowLayout) panelEinlagerungButtonsOben
                .getLayout();
        fl_panelEinlagerungButtonsOben.setAlignment(FlowLayout.LEFT);
        einlagern.add(panelEinlagerungButtonsOben, BorderLayout.NORTH);
        // panel Buttons unten
        panelEinlagerungButtonsUnten = new JPanel();
        FlowLayout fl_panelEinlagerungButtonsUnten = (FlowLayout) panelEinlagerungButtonsUnten
                .getLayout();
        fl_panelEinlagerungButtonsUnten.setAlignment(FlowLayout.RIGHT);
        einlagern.add(panelEinlagerungButtonsUnten, BorderLayout.SOUTH);

        // Button einlagerung
        btnNewButton_1 = new JButton("Lagerpl\u00E4tze ausgeben");
        btnNewButton_1.addActionListener(e -> queryAdd());
        panelEinlagerungButtonsUnten.add(btnNewButton_1);

        // Scrollpane Einlagerung
        JScrollPane scrollpaneEinlagerungTabelle = new JScrollPane();
        einlagern.add(scrollpaneEinlagerungTabelle, BorderLayout.CENTER);

        // tabelle Einlagerung
        einlagerungsTabelle = new JTable();
        scrollpaneEinlagerungTabelle.setViewportView(einlagerungsTabelle);

        //
        einlagern_1 = new JPanel();
        tabbedPane.addTab(Hauptklasse.uebersetzer.getUebersetzung("Inventar"), null, einlagern_1,
                null);
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
        tabbedPane.addTab(Hauptklasse.uebersetzer.getUebersetzung("Inventar"), null, einlagern_2,
                null);
        einlagern_2.setLayout(new BorderLayout(0, 0));

        panel_6 = new JPanel();
        einlagern_2.add(panel_6, BorderLayout.SOUTH);

        exportieren_1 = new JButton("Exportieren");
        panel_6.add(exportieren_1);

        aktualisieren_1 = new JButton("Aktualisieren");
        panel_6.add(aktualisieren_1);

        aktualisieren_1.addActionListener(e -> {
            Produkt p = produktFuerSuche(dropdownSuche1_2.getSelectedItem().toString());
            String tabelle;
            switch (dropdownSuche1_2.getSelectedItem().toString()) {
            case "Grafikkarte":
                tabelle = "grafikkarte";
                break;
            case "CPU":
                tabelle = "cpu";
                break;
            case "Fertigprodukt":
                tabelle = "fertigprodukt";
                break;
            case "Hauptspeicher":
                tabelle = "hauptspeicher";
                break;
            case "Festplatte":
                tabelle = "festplatte";
                break;
            default:
                System.out.println(dropdownSuche1_2.getSelectedItem().toString());
                tabelle = "cpu";
                return;
            }
            QueryOutputHandling.queryToUI("SELECT * FROM " + tabelle, "Bestellliste",
                    p.getTabelleneintraege());
        });
        scrollPane_3 = new JScrollPane();
        einlagern_2.add(scrollPane_3, BorderLayout.CENTER);
        table_1 = new JTable();
        scrollPane_3.setViewportView(table_1);
        panel_7 = new JPanel();
        einlagern_2.add(panel_7, BorderLayout.NORTH);
        dropdownSuche1_2 = new JComboBox<String>();
        dropdownSuche1_2.addItem("");
        for (String a : arr) {
            dropdownSuche1_2.addItem(a);
        }
        panel_7.add(dropdownSuche1_2);
        dropdownEinlagerungProdukttyp = new JComboBox<String>();
        for (String a : arr) {
            dropdownEinlagerungProdukttyp.addItem(a);
        }
        panelEinlagerungButtonsOben.add(dropdownEinlagerungProdukttyp);
    }

    public void updateSprache() {
        System.out.println(sprachwahl.getSelectedItem().toString());
        Hauptklasse.uebersetzer.setSprache(sprachwahl.getSelectedItem().toString());
        System.out.println(Hauptklasse.uebersetzer.getUebersetzung("Suche"));
        this.setVisible(false);
        dispose();
        Hauptklasse.frame = new UI();
        Hauptklasse.frame.setVisible(true);
        Hauptklasse.frame.addActionListenersToUi();
    }

    /**
     * Methode welche den UI-Elementen Action-Listener hinzufuegt.
     */
    public void addActionListenersToUi() {
        sprachwahl.addActionListener(e -> updateSprache());
        dropdownEinlagerungProdukttyp.addActionListener(e -> updateEinlagerungsTable());
        dropdownSucheProdukttyp.addActionListener(e -> querySuche());
        dropdownSucheProdukttyp.addActionListener(e -> updateDropdown2());
        okSuche.addActionListener(e -> querySuche());
    }

    /**
     * Methode erzeugt Table neu.
     */
    public void setSuchTable(Object[][] input) {
        Object[][] data = Arrays.copyOfRange(input, 1, input.length);
        DefaultTableModel model = (DefaultTableModel) tabelleSuche.getModel();
        model.setRowCount(0);
        model = new DefaultTableModel(data, input[0]);
        tabelleSuche.setModel(model);
    }

    /**
     * Methode welche von einem Produktnamen ausgehend ein Produkt erstellt.
     * 
     * @param Name des Produktes.
     */
    public Produkt produktFuerSuche(String produktname) {
        Produkt p;
        switch (produktname) {
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

    /**
     * Methode welche Query zum Loeschen der in der Suchtabelle angezeigten
     * Komponenten erstellt.
     */
    public void deletionSuchTabelle() {
        Produkt p = produktFuerSuche(dropdownSucheProdukttyp.getSelectedItem().toString());
        if (p != null) {
            String sqlQuery = "DELETE ";
            String sqlQuery2 = "SELECT NAME, LAGERPLATZ ";
            sqlQuery2 += "FROM " + p.produktTyp() + " ";

            sqlQuery += "FROM " + p.produktTyp() + " ";
            int added = 0;
            if (!(dropdownSucheAttribut1.getSelectedItem().toString().length() <= 1)) {
                sqlQuery += "WHERE " + (dropdownSucheAttribut1.getSelectedItem().equals("ID")
                        ? dropdownSucheAttribut1.getSelectedItem() + "="
                                + textFieldSucheAttribut1.getText() + " AND "
                        : dropdownSucheAttribut1.getSelectedItem() + "='"
                                + textFieldSucheAttribut1.getText() + "' AND ");
                sqlQuery2 += "WHERE " + (dropdownSucheAttribut1.getSelectedItem().equals("ID")
                        ? dropdownSucheAttribut1.getSelectedItem() + "="
                                + textFieldSucheAttribut1.getText() + " AND "
                        : dropdownSucheAttribut1.getSelectedItem() + "='"
                                + textFieldSucheAttribut1.getText() + "' AND ");
                added = 1;
            }
            if (!(dropdownSucheAttribut2.getSelectedItem().toString().length() <= 1)) {
                if (added == 0) {
                    sqlQuery += "WHERE ";
                    sqlQuery2 += "WHERE";
                }
                sqlQuery += dropdownSucheAttribut2.getSelectedItem().equals("ID")
                        ? dropdownSucheAttribut2.getSelectedItem() + "="
                                + textFieldSucheAttribut2.getText() + " AND "
                        : dropdownSucheAttribut2.getSelectedItem() + "='"
                                + textFieldSucheAttribut2.getText() + "' AND ";
                sqlQuery2 += dropdownSucheAttribut2.getSelectedItem().equals("ID")
                        ? dropdownSucheAttribut2.getSelectedItem() + "="
                                + textFieldSucheAttribut2.getText() + " AND "
                        : dropdownSucheAttribut2.getSelectedItem() + "='"
                                + textFieldSucheAttribut2.getText() + "' AND ";
                added = 1;

            }
            if (!(dropdownSucheAttribut3.getSelectedItem().toString().length() <= 1)) {
                if (added == 0) {
                    sqlQuery += "WHERE ";
                    sqlQuery2 += "WHERE";
                }
                sqlQuery += dropdownSucheAttribut3.getSelectedItem().equals("ID")
                        ? dropdownSucheAttribut3.getSelectedItem() + "="
                                + textFieldSucheAttribut3.getText() + " "
                        : dropdownSucheAttribut3.getSelectedItem() + "='"
                                + textFieldSucheAttribut3.getText() + "' ";
                sqlQuery2 += dropdownSucheAttribut3.getSelectedItem().equals("ID")
                        ? dropdownSucheAttribut3.getSelectedItem() + "="
                                + textFieldSucheAttribut3.getText() + " "
                        : dropdownSucheAttribut3.getSelectedItem() + "='"
                                + textFieldSucheAttribut3.getText() + "' ";
                added = 0;
            }
            sqlQuery2 = sqlQuery2.substring(0, sqlQuery2.length() - 1 - added * 4) + ";";
            System.out.println(sqlQuery2);
            SQL.anhandEinesArraysAlleRunterzaehlen(
                    SQL.queryToStringArray(sqlQuery2, new String[] { "Name", "Lagerplatz" }),
                    p.produktTyp());
            sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1 - added * 4) + ";";
            System.out.println(sqlQuery);

            sqlQuery = p == null ? null : sqlQuery;
            Object[] options = { "Yes", "No" };
            int n = JOptionPane.showOptionDialog(this, "Delete current Selection?", "Delete?",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
                    options[1]);
            if (n == 0) {
                SQL.update(sqlQuery);
                QueryOutputHandling.nonsenseQuery();
            }
        }
    }

    /**
     * Methode welche eine Such-Query basierend auf dem Zustand des UI erstellt und
     * dann den Such-Table direkt nach den Ergebnissen setzt.
     */
    public void querySuche() {
        Produkt p = produktFuerSuche(dropdownSucheProdukttyp.getSelectedItem().toString());
        if (p != null) {
            String sqlQuery = "SELECT ";
            for (int i = 0; i < p.getTabelleneintraege().length; i++) {
                sqlQuery += p.getTabelleneintraege()[i] + ", ";
            }
            sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 2);
            sqlQuery += " FROM " + p.produktTyp() + " ";
            int added = 0;
            if (!(dropdownSucheAttribut1.getSelectedItem().toString().length() <= 1)) {
                if (added == 0) {
                    sqlQuery += "WHERE ";
                }
                sqlQuery += dropdownSucheAttribut1.getSelectedItem().equals("ID")
                        ? dropdownSucheAttribut1.getSelectedItem() + "="
                                + textFieldSucheAttribut1.getText() + " AND "
                        : dropdownSucheAttribut1.getSelectedItem() + "='"
                                + textFieldSucheAttribut1.getText() + "' AND ";
                added = 1;
            }
            if (!(dropdownSucheAttribut2.getSelectedItem().toString().length() <= 1)) {
                if (added == 0) {
                    sqlQuery += "WHERE ";
                }
                sqlQuery += dropdownSucheAttribut2.getSelectedItem().equals("ID")
                        ? dropdownSucheAttribut2.getSelectedItem() + "="
                                + textFieldSucheAttribut2.getText() + " AND "
                        : dropdownSucheAttribut2.getSelectedItem() + "='"
                                + textFieldSucheAttribut2.getText() + "' AND ";
                added = 1;
            }
            if (!(dropdownSucheAttribut3.getSelectedItem().toString().length() <= 1)) {
                if (added == 0) {
                    sqlQuery += "WHERE ";
                }
                sqlQuery += dropdownSucheAttribut3.getSelectedItem().equals("ID")
                        ? dropdownSucheAttribut3.getSelectedItem() + "="
                                + textFieldSucheAttribut3.getText() + " "
                        : dropdownSucheAttribut3.getSelectedItem() + "='"
                                + textFieldSucheAttribut3.getText() + "' ";
                added = 0;

            }
            sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1 - added * 4) + ";";
            sqlQuery = p == null ? null : sqlQuery;
            QueryOutputHandling.queryToUI(sqlQuery, "Suche", p.getTabelleneintraege());
        }
    }

    /**
     * Methode welche die in die Einlagern-Tabelle eingefuegten Daten bei
     * Vollständigkeit Reihenweise in die SQL-Datenbank einfuegt.
     */
    public void queryAdd() {
        for (int k = 0; k < einlagerungsTabelle.getRowCount(); k++) {
            Object name = "";
            Produkt p = produktFuerSuche(
                    dropdownEinlagerungProdukttyp.getSelectedItem().toString());
            boolean allRowsFull = true;
            for (int i = 0; i < einlagerungsTabelle.getColumnCount(); i++) {
                allRowsFull = !einlagerungsTabelle.getModel().getValueAt(k, i).equals("");
            }
            if (allRowsFull && p != null) {
                String sqlQuery = "INSERT INTO " + p.produktTyp() + " (";
                // Alle Spaltennamen werden eingegeben.
                for (int i = 0; i < p.getTabelleneintraege().length - 2; i++) {
                    sqlQuery += p.getTabelleneintraege()[i] + ",";
                }
                sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1) + ",LAGERPLATZ)"
                        + System.lineSeparator() + "VALUES (";
                // Alle Tabelleneintraege werden ausgelesen.
                for (int i = 0; i < p.getTabelleneintraege().length - 2; i++) {
                    if (i == 0) {
                        name = einlagerungsTabelle.getModel().getValueAt(k, i);
                    }
                    sqlQuery += "'" + einlagerungsTabelle.getModel().getValueAt(k, i) + "', ";
                }
                int lagerplatzID = SQL.einlagern(p, (String) name);
                sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 2) + ", " + lagerplatzID
                        + " );";
                SQL.update(sqlQuery);
            } else {
            }
        }
    }

    /**
     * Methode welche den Einlagerungstable fuellt.
     */
    public void updateEinlagerungsTable() {
        Produkt p = produktFuerSuche(dropdownEinlagerungProdukttyp.getSelectedItem().toString());
        QueryOutputHandling.queryToUI(
                "SELECT " + "Name, VRAM, Hersteller "
                        + "FROM GRAFIKKARTE WHERE HERSTELLER='ABCDEFG';",
                "Einlagerung", Arrays.copyOfRange(p.getTabelleneintraege(), 0,
                        p.getTabelleneintraege().length - 2));
    }

    /**
     * Tabelle in Tab Einlagerung wird gefuellt.
     */
    public void setEinlagerungTable(Object[][] input) {
        Object[][] data = new Object[8][input[0].length];
        for (int i = 0; i < input[0].length; i++) {
            for (int a = 0; a < 8; a++) {
                data[a][i] = "";
            }
        }
        DefaultTableModel model = (DefaultTableModel) tabelleSuche.getModel();
        model.setRowCount(0);
        model = new DefaultTableModel(data, input[0]);
        einlagerungsTabelle.setModel(model);
    }

    /**
     * Methode welche die JComboBoxen refresht.
     */
    public void updateDropdown2() {
        Produkt p = produktFuerSuche(dropdownSucheProdukttyp.getSelectedItem().toString());
        dropdownSucheAttribut1.removeAllItems();
        dropdownSucheAttribut2.removeAllItems();
        dropdownSucheAttribut3.removeAllItems();
        dropdownSucheAttribut1.addItem("");
        dropdownSucheAttribut2.addItem("");
        dropdownSucheAttribut3.addItem("");
        if (p != null) {
            for (String a : p.getTabelleneintraege()) {
                dropdownSucheAttribut1.addItem(a);
                dropdownSucheAttribut2.addItem(a);
                dropdownSucheAttribut3.addItem(a);
            }
        }
    }

    /**
     * 
     * @param input
     */
    public void setBestellTable(Object[][] input) {
        Object[][] data = Arrays.copyOfRange(input, 1, input.length);
        DefaultTableModel model = (DefaultTableModel) table_1.getModel();
        model.setRowCount(0);
        model = new DefaultTableModel(data, input[0]);
        table_1.setModel(model);
    }

    public void formatieren(Object[][] input) {
        try {
        	
        	Produkt p = produktFuerSuche(dropdownSuche1_2.getSelectedItem().toString());
        	//System.out.println(p.getMindestmenge());
        	

            Object[][] strings = Arrays.copyOfRange(input, 1, input.length);

            List<Object[]> strings2 = Arrays.asList(strings);

            Map<Object, Object> map = new HashMap<>();

            String name = null;

            for (Object[] o : strings2) {

                name = (String) o[0];
                int count = map.containsKey(name) ? (int) map.get(name) : 0;
                map.put(name, count + 1);

            }
            
            List<Object> namen = new ArrayList<>();
            namen.addAll(Arrays.asList(map.keySet().toArray()));
            namen.stream().filter(e->(Integer) map.get(e)>=p.getMindestmenge()).forEach(e->map.remove(e));
            
            map.forEach((k,v)->System.out.println(k + " " + v));
            
            
            
            Object[] k = map.keySet().toArray();
            Object[] v = map.values().toArray();
            Object[][] arr = new Object[k.length + 1][2];
            arr[0][0] = "Name";
            arr[0][1] = "Menge";

            for (int i = 1; i < k.length + 1; i++) {
            	
                arr[i][0] = k[i - 1];
                arr[i][1] = v[i - 1];
  
            }

            setBestellTable(arr);
        } catch (Exception e) {
        	e.printStackTrace();

        }

    }

}
