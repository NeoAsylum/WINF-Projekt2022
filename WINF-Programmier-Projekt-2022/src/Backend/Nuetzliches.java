package Backend;

/**
 * Klasse mit nuetzlichen Methoden.
 * @author All-Stars
 *
 */
public class Nuetzliches {

    /**
     * Methode welche eine Spalte von einem zweidimensionalen Array zurueckgibt.
     * 
     * @param array Der betroffene Array.
     * @param index Der Index der Spalte.
     * @return Die Spalte als Array.
     */
    public static Object[] getColumnInArray(Object[][] array, int index) {
        Object[] column = new Object[array.length];
        for (int i = 0; i < column.length; i++) {
            column[i] = array[i][index];
        }
        return column;
    }

}
