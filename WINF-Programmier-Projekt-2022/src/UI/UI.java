package UI;

import java.awt.BorderLayout;

import Backend.AuslagernMethoden;
import Backend.EinlagernMethoden;
import Backend.Hauptklasse;
import Backend.InventarUndBestelllisteMethoden;
import Datentypen.CPU;
import Datentypen.Fertigprodukt;
import Datentypen.Festplatte;
import Datentypen.Grafikkarte;
import Datentypen.Hauptspeicher;
import Datentypen.Produkt;
import Export.Excel;
import SQL.NurSQL;
import SQL.SQLOutputHandling;

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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Dimension;

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
    private JButton sucheExportButton;
    private JComboBox<String> dropdownSucheProdukttyp;
    private JComboBox<String> dropdownSucheAttribut1;
    private JComboBox<String> dropdownSucheAttribut2;
    private JComboBox<String> dropdownSucheAttribut3;
    private JButton deleteSuche;
    private JTextField textFieldSucheAttribut1;
    private JTextField textFieldSucheAttribut2;
    private JTextField textFieldSucheAttribut3;
    private JPanel einlagerTab;
    private JPanel panelEinlagerungButtonsOben;
    private JPanel panelEinlagerungButtonsUnten;
    private JComboBox<String> dropdownEinlagerungProdukttyp;
    JScrollPane scrollPane_2;
    private JButton einlagernButton;
    private JTable einlagerungsTabelle;
    private JPanel inventarTab;
    private JPanel panel_4;
    private JPanel panel_5;
    private JButton inventarExportButton;
    private JPanel bestelllistenTab;
    private JPanel bestellenButtonsUnten;
    private JButton bestelllisteExportButton;
    private JScrollPane scrollPaneBestellliste;
    private JTable table_1;
    private JButton aktualisieren_1;
    private JButton okSuche;
    private JPanel bestellenButtonsOben;
    private JComboBox<String> dropdownSuche1_2;
    private JComboBox<String> sprachwahl;
    private JScrollPane scrollPaneInventar;
    private JTable inventarTabelle;
    private JButton okButtonInventar;
    private JScrollPane lagerplatzScrollPane;
    private JTable lagerplaetzeEinlagerung;

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
        tabbedPane.addTab(Hauptklasse.getUebersetzer().getUebersetzung("Suche"), null, suche,
                null);
        suche.setLayout(new BorderLayout(0, 0));
        suchePanelButtonsUnten = new JPanel();
        FlowLayout fl_suchePanelButtonsUnten = (FlowLayout) suchePanelButtonsUnten.getLayout();
        fl_suchePanelButtonsUnten.setAlignment(FlowLayout.RIGHT);
        suche.add(suchePanelButtonsUnten, BorderLayout.SOUTH);
        deleteSuche = new JButton(Hauptklasse.getUebersetzer().getUebersetzung("Delete"));
        deleteSuche.addActionListener(e -> deletionSuchTabelle());

        sprachwahl = new JComboBox<String>();
        sprachwahl.addItem("");
        sprachwahl.addItem("English");
        sprachwahl.addItem("Deutsch");
        suchePanelButtonsUnten.add(sprachwahl);

        deleteSuche.setToolTipText("put x in 'delete' column");
        suchePanelButtonsUnten.add(deleteSuche);
        sucheExportButton = new JButton(
                Hauptklasse.getUebersetzer().getUebersetzung("Exportieren"));
        suchePanelButtonsUnten.add(sucheExportButton);
        /*
         * sucheExportButton.addActionListener(e -> { Excel.exportieren(tabelleSuche);
         * });
         */
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
        setDropdownSucheAttribut1(new JComboBox<String>());
        getDropdownSucheAttribut1().addItem("");
        getDropdownSucheAttribut1().setSelectedIndex(0);
        suchePanelButtonsOben.add(getDropdownSucheAttribut1());
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
        einlagerTab = new JPanel();
        tabbedPane.addTab(Hauptklasse.getUebersetzer().getUebersetzung("Einlagern"), null,
                einlagerTab, null);
        einlagerTab.setLayout(new BorderLayout(0, 0));

        // panel Buttons oben
        panelEinlagerungButtonsOben = new JPanel();
        FlowLayout fl_panelEinlagerungButtonsOben = (FlowLayout) panelEinlagerungButtonsOben
                .getLayout();
        fl_panelEinlagerungButtonsOben.setAlignment(FlowLayout.LEFT);
        einlagerTab.add(panelEinlagerungButtonsOben, BorderLayout.NORTH);
        // panel Buttons unten
        panelEinlagerungButtonsUnten = new JPanel();
        FlowLayout fl_panelEinlagerungButtonsUnten = (FlowLayout) panelEinlagerungButtonsUnten
                .getLayout();
        fl_panelEinlagerungButtonsUnten.setAlignment(FlowLayout.RIGHT);
        einlagerTab.add(panelEinlagerungButtonsUnten, BorderLayout.SOUTH);

        // Button einlagerung
        einlagernButton = new JButton("Einlagern");
        einlagernButton.addActionListener(e -> queryAdd());
        panelEinlagerungButtonsUnten.add(einlagernButton);

        // Scrollpane Einlagerung
        JScrollPane scrollpaneEinlagerungTabelle = new JScrollPane();
        einlagerTab.add(scrollpaneEinlagerungTabelle, BorderLayout.CENTER);

        // tabelle Einlagerung
        einlagerungsTabelle = new JTable();
        einlagerungsTabelle.setModel(new DefaultTableModel(
                new Object[][] { { null, null, null, null }, { null, null, null, null }, },
                new String[] { "", "", "", "" }));
        scrollpaneEinlagerungTabelle.setViewportView(einlagerungsTabelle);

        //
        inventarTab = new JPanel();
        tabbedPane.addTab(Hauptklasse.getUebersetzer().getUebersetzung("Inventar"), null,
                inventarTab, null);
        inventarTab.setLayout(new BorderLayout(0, 0));

        panel_4 = new JPanel();
        inventarTab.add(panel_4, BorderLayout.NORTH);

        panel_5 = new JPanel();
        inventarTab.add(panel_5, BorderLayout.SOUTH);

        inventarExportButton = new JButton("Exportieren");
        inventarExportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        panel_5.add(inventarExportButton);
        
                okButtonInventar = new JButton("Aktualisieren");
                panel_5.add(okButtonInventar);

        scrollPaneInventar = new JScrollPane();
        inventarTab.add(scrollPaneInventar, BorderLayout.CENTER);

        inventarTabelle = new JTable();
        inventarTabelle.setModel(new DefaultTableModel(
                new Object[][] { { null, null, null, null }, { null, null, null, null }, },
                new String[] { "", "", "", "" }));
        scrollPaneInventar.setViewportView(inventarTabelle);

        bestelllistenTab = new JPanel();
        tabbedPane.addTab("Bestellliste", null, bestelllistenTab, null);
        bestelllistenTab.setLayout(new BorderLayout(0, 0));

        bestellenButtonsUnten = new JPanel();
        bestelllistenTab.add(bestellenButtonsUnten, BorderLayout.SOUTH);

        bestelllisteExportButton = new JButton("Exportieren");
        bestellenButtonsUnten.add(bestelllisteExportButton);

     
        aktualisieren_1 = new JButton("Aktualisieren");
        bestellenButtonsUnten.add(aktualisieren_1);

        aktualisieren_1.addActionListener(e -> {
            Produkt p = produktTypausString(dropdownSuche1_2.getSelectedItem().toString());
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
            SQLOutputHandling.queryToUI("SELECT * FROM " + tabelle, "Bestellliste",
                    p.getTabelleneintraege());
        });
        scrollPaneBestellliste = new JScrollPane();
        bestelllistenTab.add(scrollPaneBestellliste, BorderLayout.CENTER);
        table_1 = new JTable();
        table_1.setModel(new DefaultTableModel(
                new Object[][] { { null, null, null, null }, { null, null, null, null }, },
                new String[] { "", "", "", "" }));
        scrollPaneBestellliste.setViewportView(table_1);
        bestellenButtonsOben = new JPanel();
        bestelllistenTab.add(bestellenButtonsOben, BorderLayout.NORTH);
        dropdownSuche1_2 = new JComboBox<String>();
        dropdownSuche1_2.addItem("");
        for (String a : arr) {
            dropdownSuche1_2.addItem(a);
        }
        bestellenButtonsOben.add(dropdownSuche1_2);
        dropdownEinlagerungProdukttyp = new JComboBox<String>();
        for (String a : arr) {
            dropdownEinlagerungProdukttyp.addItem(a);
        }
        panelEinlagerungButtonsOben.add(dropdownEinlagerungProdukttyp);

        lagerplatzScrollPane = new JScrollPane();
        lagerplatzScrollPane.setPreferredSize(new Dimension(150, 2));
        lagerplatzScrollPane.setMaximumSize(new Dimension(200, 32767));
        lagerplatzScrollPane.setBounds(new Rectangle(50, 0, 50, 0));
        lagerplatzScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        lagerplatzScrollPane
                .setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        einlagerTab.add(lagerplatzScrollPane, BorderLayout.WEST);

        lagerplaetzeEinlagerung = new JTable();
        lagerplaetzeEinlagerung.setMaximumSize(new Dimension(150, 0));
        lagerplaetzeEinlagerung
                .setModel(new DefaultTableModel(new Object[][] { { null }, { null }, { null }, },
                        new String[] { "Lagerplaetze" }));
        lagerplatzScrollPane.setViewportView(lagerplaetzeEinlagerung);
    }
    
    /**
     * Methode, um die Spracheinstellung zu akltualisieren.
     */
    public void updateSprache() {
        System.out.println(sprachwahl.getSelectedItem().toString());
        Hauptklasse.getUebersetzer().setSprache(sprachwahl.getSelectedItem().toString());
        System.out.println(Hauptklasse.getUebersetzer().getUebersetzung("Suche"));
        this.setVisible(false);
        dispose();
        Hauptklasse.setupUI();
    }

    /**
     * Methode, welche den UI-Elementen Action-Listener hinzufuegt.
     */
    public void addActionListenersToUi() {

        inventarExportButton.addActionListener(e ->{ 
        	Export.XMLExport
                .writeInvetoryToXML(InventarUndBestelllisteMethoden.inventarisierung());
            Export.Excel.exportieren(inventarTabelle, "Inventar");
        });
       
        sucheExportButton.addActionListener(e -> {Export.XMLExport.writeQueryToXML(getTableData(tabelleSuche),
                produktTypausString(dropdownSucheProdukttyp.toString()));
        Export.Excel.exportieren(tabelleSuche, "Suchergebnisse");});
        
        okButtonInventar
                .addActionListener(e -> setInventarTable(InventarUndBestelllisteMethoden.inventarisierung()));
        
        bestelllisteExportButton.addActionListener(e -> {
            Excel.exportieren(table_1, "Bestellliste");
        });
        sprachwahl.addActionListener(e -> updateSprache());
        dropdownEinlagerungProdukttyp.addActionListener(e -> updateEinlagerungsTable());
        dropdownSucheProdukttyp.addActionListener(e -> querySuche());
        dropdownSucheProdukttyp.addActionListener(e -> updateDropdown2());
        okSuche.addActionListener(e -> querySuche());
    }

    /**
     * Methode erstellt die Suchtabelle.
     * @param input
     */
    public void setSuchTable(Object[][] input) {
        Object[][] data = Arrays.copyOfRange(input, 1, input.length);
        DefaultTableModel model = (DefaultTableModel) tabelleSuche.getModel();
        model.setRowCount(0);
        model = new DefaultTableModel(data, input[0]);
        tabelleSuche.setModel(model);
    }
    /**
     * Methode erstellt die Einlagerungstabelle.
     * @param input
     */
    public void setLagerplaetzeEinlagerungTable(Object[][] input) {
        System.out.println(Arrays.deepToString(input));
        Object[][] data = Arrays.copyOfRange(input, 1, input.length);
        DefaultTableModel model = (DefaultTableModel) lagerplaetzeEinlagerung.getModel();
        model.setRowCount(0);
        model = new DefaultTableModel(input, new Object[] { "Lagerplaetze" });
        lagerplaetzeEinlagerung.setModel(model);
    }

  /**
   * Methode erstellt die Inventartabelle.
   * @param input
   */
    public void setInventarTable(Object[][] input) {
        Object[][] data = Arrays.copyOfRange(input, 1, input.length);
        DefaultTableModel model = (DefaultTableModel) inventarTabelle.getModel();
        model.setRowCount(0);
        model = new DefaultTableModel(data, input[0]);
        inventarTabelle.setModel(model);
    }

    /**
     * Methode welche von einem Produktnamen ausgehend ein Produkt erstellt.
     * 
     * @param Name des Produktes.
     */
    public Produkt produktTypausString(String produktname) {
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
        Produkt p = produktTypausString(dropdownSucheProdukttyp.getSelectedItem().toString());
        if (p != null) {
            String sqlQuery = "DELETE ";
            String sqlQuery2 = "SELECT NAME, LAGERPLATZ ";
            sqlQuery2 += "FROM " + p.produktTyp() + " ";

            sqlQuery += "FROM " + p.produktTyp() + " ";
            int added = 0;
            if (!(getDropdownSucheAttribut1().getSelectedItem().toString().length() <= 1)) {
                sqlQuery += "WHERE " + (getDropdownSucheAttribut1().getSelectedItem().equals("ID")
                        ? getDropdownSucheAttribut1().getSelectedItem() + "="
                                + textFieldSucheAttribut1.getText() + " AND "
                        : getDropdownSucheAttribut1().getSelectedItem() + "='"
                                + textFieldSucheAttribut1.getText() + "' AND ");
                sqlQuery2 += "WHERE " + (getDropdownSucheAttribut1().getSelectedItem().equals("ID")
                        ? getDropdownSucheAttribut1().getSelectedItem() + "="
                                + textFieldSucheAttribut1.getText() + " AND "
                        : getDropdownSucheAttribut1().getSelectedItem() + "='"
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
            sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1 - added * 4) + ";";
            System.out.println(sqlQuery);
            sqlQuery = p == null ? null : sqlQuery;
            Object[] options = { "Yes", "No" };
            int n = JOptionPane.showOptionDialog(this, "Delete current Selection?", "Delete?",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
                    options[1]);
            if (n == 0) {
                AuslagernMethoden.anhandEinesArraysAlleRunterzaehlen(SQLOutputHandling
                        .queryToStringArray(sqlQuery2, new String[] { "Name", "Lagerplatz" }),
                        p.produktTyp());
                NurSQL.update(sqlQuery);
                NurSQL.nonsenseQuery();
            }
        }
    }

    /**
     * Methode welche die Daten aus einem JTable als Array zurueckgibt.
     * 
     * @param table
     * @return
     */
    public Object[][] getTableData(JTable table) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
        Object[][] tableData = new Object[nRow][nCol];
        for (int i = 0; i < nRow; i++)
            for (int j = 0; j < nCol; j++)
                tableData[i][j] = dtm.getValueAt(i, j);
        return tableData;
    }

    /**
     * Methode welche eine Such-Query basierend auf dem Zustand des UI erstellt und
     * dann den Such-Table direkt nach den Ergebnissen setzt.
     */
    public void querySuche() {
        Produkt p = produktTypausString(dropdownSucheProdukttyp.getSelectedItem().toString());
        if (p != null) {
            String sqlQuery = "SELECT ";
            for (int i = 0; i < p.getTabelleneintraege().length; i++) {
                sqlQuery += p.getTabelleneintraege()[i] + ", ";
            }
            sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 2);
            sqlQuery += " FROM " + p.produktTyp() + " ";
            int added = 0;
            if (!(getDropdownSucheAttribut1().getSelectedItem().toString().length() <= 1)) {
                if (added == 0) {
                    sqlQuery += "WHERE ";
                }
                sqlQuery += getDropdownSucheAttribut1().getSelectedItem().equals("ID")
                        ? getDropdownSucheAttribut1().getSelectedItem() + "="
                                + textFieldSucheAttribut1.getText() + " AND "
                        : getDropdownSucheAttribut1().getSelectedItem() + "='"
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
            SQLOutputHandling.queryToUI(sqlQuery, "Suche", p.getTabelleneintraege());
        }
    }

    /**
     * Methode welche die in die Einlagern-Tabelle eingefuegten Daten bei
     * Vollständigkeit Reihenweise in die SQL-Datenbank einfuegt.
     */
    public void queryAdd() {
        LinkedList<String> lagerplaetze = new LinkedList<String>();
        for (int k = 0; k < einlagerungsTabelle.getRowCount(); k++) {
            Object name = "";
            Produkt p = produktTypausString(
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
                int lagerplatzID = EinlagernMethoden.einlagern(p, (String) name);
                lagerplaetze.add(lagerplatzID + "");
                sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 2) + ", " + lagerplatzID
                        + " );";
                NurSQL.update(sqlQuery);
            } else {
            }
        }
        if (!lagerplaetze.isEmpty()) {
            String[][] lagerplaetzeArray = new String[lagerplaetze.size()][1];
            for (int i = 0; i < lagerplaetze.size(); i++) {
                lagerplaetzeArray[i][0] = lagerplaetze.get(i);
            }
            setLagerplaetzeEinlagerungTable(lagerplaetzeArray);
        }
    }

    /**
     * Methode welche den Einlagerungstable fuellt.
     */
    public void updateEinlagerungsTable() {
        Produkt p = produktTypausString(
                dropdownEinlagerungProdukttyp.getSelectedItem().toString());
        SQLOutputHandling.queryToUI(
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
        Produkt p = produktTypausString(dropdownSucheProdukttyp.getSelectedItem().toString());
        getDropdownSucheAttribut1().removeAllItems();
        dropdownSucheAttribut2.removeAllItems();
        dropdownSucheAttribut3.removeAllItems();
        getDropdownSucheAttribut1().addItem("");
        dropdownSucheAttribut2.addItem("");
        dropdownSucheAttribut3.addItem("");
        if (p != null) {
            for (String a : p.getTabelleneintraege()) {
                getDropdownSucheAttribut1().addItem(a);
                dropdownSucheAttribut2.addItem(a);
                dropdownSucheAttribut3.addItem(a);
            }
        }
    }

    /**
     * Methode erstellt die Bestelllistentabelle.
     * @param input
     */
    public void setBestellTable(Object[][] input) {
        Object[][] data = Arrays.copyOfRange(input, 1, input.length);
        DefaultTableModel model = (DefaultTableModel) table_1.getModel();
        model.setRowCount(0);
        model = new DefaultTableModel(data, input[0]);
        table_1.setModel(model);
    }

    /**
     * Formatiert den gegebenen 2d-Object-Array zu einem 2*x Object-Array und
     * filtert nach Produkten, die auch wirklich unter der Mindestmenge sind für die
     * Bestellliste.
     * 
     * @param input
     */
    public void formatierenBestellliste(Object[][] input) {
        try {

            Produkt p = produktTypausString(dropdownSuche1_2.getSelectedItem().toString());
            // System.out.println(p.getMindestmenge());

            Object[][] strings = Arrays.copyOfRange(input, 1, input.length);

            List<Object[]> strings2 = Arrays.asList(strings);

            Map<Object, Object> map = new HashMap<>();

            String name = null;

            for (Object[] o : strings2) {

                name = (String) o[0];
                int count = map.containsKey(name) ? (int) map.get(name) : p.getMindestmenge();
                map.put(name, count - 1);

            }

            List<Object> namen = new ArrayList<>();
            namen.addAll(Arrays.asList(map.keySet().toArray()));
            namen.stream().filter(e -> (Integer) map.get(e) <= 0).forEach(e -> map.remove(e));

            Object[] k = map.keySet().toArray();
            Object[] v = map.values().toArray();
            Object[][] arr = new Object[k.length + 1][2];
            arr[0][0] = "Produkt";
            arr[0][1] = "Bestellmenge";

            for (int i = 1; i < k.length + 1; i++) {

                arr[i][0] = k[i - 1];
                arr[i][1] = v[i - 1];

            }

            setBestellTable(arr);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public JComboBox<String> getDropdownSucheAttribut1() {
        return dropdownSucheAttribut1;
    }

    public void setDropdownSucheAttribut1(JComboBox<String> dropdownSucheAttribut1) {
        this.dropdownSucheAttribut1 = dropdownSucheAttribut1;
    }

}
