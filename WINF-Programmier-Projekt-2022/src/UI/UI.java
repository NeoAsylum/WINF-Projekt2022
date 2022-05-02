package UI;

import java.awt.BorderLayout;
import Backend.Tabelleneintraege;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.DropMode;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

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
    private JComboBox dropdownSuche1;
    private JComboBox dropdownSuche2;
    private JComboBox dropdownSuche3;
    private JComboBox dropdownSuche4;
    private JButton deleteSuche;

    public UI(Tabelleneintraege e) {

    }

    /**
     * Create the frame.
     * 
     * @wbp.parser.constructor
     */
    public UI(Object[][] input) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
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
        
        btnNewButton = new JButton("Export");
        panel_1.add(btnNewButton);
        
        panel_2 = new JPanel();
        suche.add(panel_2, BorderLayout.NORTH);
        
        dropdownSuche1 = new JComboBox();
        panel_2.add(dropdownSuche1);
        
        dropdownSuche2 = new JComboBox();
        panel_2.add(dropdownSuche2);
        
        dropdownSuche3 = new JComboBox();
        panel_2.add(dropdownSuche3);
        
        dropdownSuche4 = new JComboBox();
        panel_2.add(dropdownSuche4);
        
        deleteSuche = new JButton("delete");
        panel_2.add(deleteSuche);
        
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

    public void setGrakaTable(Object[][] input) {
        Object[] names = { "Name", "VRAM", "Hersteller","Delete" };
        Object[][] data = Arrays.copyOfRange(input, 1, input.length);
        setTable(input[0], data);
    }

    public void setTable(Object[] names, Object[][] data) {
        DefaultTableModel model = new DefaultTableModel(data, names);

        //table = new JTable(data, names);
        table = new JTable(model) {

            private static final long serialVersionUID = 1L;

            /*@Override
            public Class getColumnClass(int column) {
            return getValueAt(0, column).getClass();
            }*/
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };

    }

}
