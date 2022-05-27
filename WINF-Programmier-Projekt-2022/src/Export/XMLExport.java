package Export;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;

import Backend.Hauptklasse;
import Datentypen.Produkt;

public class XMLExport {
    static StringBuilder sb = new StringBuilder();

    public static void writeQueryToXML(Object[][] theShit, Produkt p) {
        sb.append(
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<!DOCTYPE Listen SYSTEM \"liste.dtd\">\n<Listen>\n");
        for (int k = 1; k < theShit.length; k++) {
            System.out.println("aaaah");
            sb.append("<" + p.produktTyp().charAt(0)+p.produktTyp().substring(1).toLowerCase() +  " ");
            for (int i = 0; i < theShit[0].length; i++) {
                sb.append(p.getTabelleneintraege()[i] + "=\"" + theShit[k][i] + "\" ");
            }
            sb.append("></" + p.produktTyp().charAt(0)+p.produktTyp().substring(1).toLowerCase() + "> "+ "\n");
        }
        sb.append("</Listen>");
        System.out.println(sb.toString());
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("WINF-Programmier-Projekt-2022\\Folder\\xml.xml"));
            writer.append(sb);
            writer.close();
            sb=new StringBuilder();
        } catch (IOException e) { 
            Hauptklasse.log.log(Level.SEVERE,"Not written to XML",e);
        }
    }

    public static void writeInvetoryToXML(Object[][] theShit) {
        Arrays.deepToString(theShit);
        sb.append(
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<!DOCTYPE Listen SYSTEM \"liste.dtd\">\n<Listen>\n");
        for (int k = 1; k < theShit.length; k++) {
            sb.append("<" + theShit[k][0] +  " ");
            for (int i = 1; i < theShit[0].length; i++) {
                sb.append(theShit[0][i] + "=\"" + theShit[k][i] + "\" ");
            }
            sb.append("></" + theShit[k][0] + "> "+ "\n");
        }
        sb.append("</Listen>");
        System.out.println(sb.toString());
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("WINF-Programmier-Projekt-2022\\Folder\\xml.xml"));
            writer.append(sb);
            writer.close();
            sb=new StringBuilder();
        } catch (IOException e) { 
            Hauptklasse.log.log(Level.SEVERE,"Not written to XML",e);
        }
    }
}
