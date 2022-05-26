package Export;

import java.util.ArrayList;
import java.util.LinkedList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import Datentypen.Grafikkarte;
import Datentypen.Produkt;

public class XMLParser extends DefaultHandler {
    ArrayList<String[]> alleElemente = new ArrayList<String[]>();

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        if (qName.equals("Grafikkarte")) {
            LinkedList<String> liste = new LinkedList<String>();
            liste.add(attributes.getValue("Name"));
            liste.add(attributes.getValue("ID"));
            if(attributes.getValue("VRAM")!=null) {
                liste.add(attributes.getValue("VRAM"));
            }
            liste.add(attributes.getValue("Lagerplatz"));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

    }

    @Override
    public void endDocument() throws SAXException {

    }
}
