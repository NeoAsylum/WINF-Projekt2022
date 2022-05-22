package Export;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import Datentypen.Produkt;

public class Liste {
    static StringBuilder sb = new StringBuilder();

    public static void writeTheShit(Object[][] theShit, Produkt p) {

        sb.append(
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<!DOCTYPE Listen SYSTEM \"liste.dtd\">\n<Listen>\n");
        for (int k = 1; k < theShit.length; k++) {
            sb.append("<" + p.produktTyp() + " ");
            for (int i = 0; i < theShit[0].length; i++) {
                sb.append(theShit[0][i] + "=\"" + theShit[k][i] + "\" ");
            }
            sb.append("/>" + "\n");
        }
        sb.append("</Listen>");
        System.out.println(sb.toString());
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("xml.xml"));
            writer.append(sb);
            writer.close();
        } catch (IOException e) {
        }
    }
}
