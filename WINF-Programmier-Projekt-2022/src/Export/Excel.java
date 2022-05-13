package Export;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class Excel {
	
	public static void exportieren(JTable table, String name) {
		try {
			
			TableModel table_model = table.getModel();
			FileWriter file_writer = new FileWriter("WINF-Programmier-Projekt-2022\\Folder\\" + name + ".xls");
			for(int i = 0; i<table_model.getColumnCount(); i++) {
				file_writer.write(table_model.getColumnName(i) + "\t");
			}
			
		      file_writer.write("\n");
		      
		      for(int i=0; i < table_model.getRowCount(); i++) {
		    	  
		        for(int j=0; j < table_model.getColumnCount(); j++) {
		        	
		          file_writer.write(table_model.getValueAt(i,j).toString()+"\t");
		        }
		        file_writer.write("\n");
		      }
		      file_writer.close();
		      
		      JFrame jf = new JFrame();
		      JOptionPane.showMessageDialog(jf, "Die Datei wurde exportiert.");
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
