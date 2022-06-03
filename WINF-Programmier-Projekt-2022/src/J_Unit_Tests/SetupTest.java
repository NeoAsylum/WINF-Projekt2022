package J_Unit_Tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Backend.Hauptklasse;
import SQL.NurSQL;

/**
 * In dieser Klasse wird die Applikation auf Reaktionen getestet durch JUnit.
 * @author yann
 *
 */
public class SetupTest {

    @BeforeEach
    public void setUp() {
        Hauptklasse.main(new String[] { "no login" });
        assertTrue(!(Hauptklasse.getUI() == null));
        Hauptklasse.log.info("UI erfolgreich initialisiert.");
        assertTrue(!(Hauptklasse.getUebersetzer() == null));
        Hauptklasse.log.info("Uebersetzer erfolgreich initialisiert.");
    }

    @Test
    public void testNonsenseQuery() {
        assertTrue("[[Name, VRAM, Hersteller]]"
                .equals(Arrays.deepToString(NurSQL.nonsenseQuery())));
        Hauptklasse.log.info("Nonsense Query erfolgreich");
    }
    
   
}
