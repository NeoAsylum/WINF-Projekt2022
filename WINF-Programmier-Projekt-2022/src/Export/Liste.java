package Export;

import Datentypen.Produkt;

public class Liste {
    static StringBuilder sb = new StringBuilder("xml.xml");

    public static void main(String[] args) {
    }

    public void writeTheShit(String[][] theShit, Produkt p) {
        sb.append(
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<!DOCTYPE Listen SYSTEM \"liste.dtd\">\n<Listen>\n<"
                        + p.produktTyp());
        for (int k = 0; k < theShit.length; k++) {
            sb.append("<" + p.produktTyp() + " ");
            for (int i = 0; i < theShit[0].length; i++) {
                sb.append(theShit[0][i] + " \"" + theShit[1][i] + "\"");
            }
            sb.append("/>");
        }
        sb.append("/Listen");
        System.out.println(sb.toString());
    }
}
