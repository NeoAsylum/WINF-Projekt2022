package Datentypen;

public interface EinObjekt {
    String id="";
    String name="";
    default String getID() {
        return id;
    }
    default String getName() {
        return name;
    }
}
