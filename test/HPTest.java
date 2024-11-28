import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.component.HP;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HPTest {
    private HP hp;
    private HP anotherHP;

    @BeforeEach
    void setUp() {
        hp = new HP(100.0);
        anotherHP = new HP(100.0);
    }

    @Test
    void testInitialHPState() {
        assertEquals(100.0, hp.getHealth(), "Initial health should be 100.");
        assertEquals(100.0, hp.getInitialHealth(), "Initial health value should be set correctly.");
    }

    @Test
    void testAddHealth() {
        hp.reduceHealth(50.0);
        hp.addHealth(30.0);
        assertEquals(80.0, hp.getHealth(), "Health should increase correctly but not exceed initial health.");

        hp.addHealth(50.0);
        assertEquals(100.0, hp.getHealth(), "Health should not exceed initial health.");
    }

    @Test
    void testReduceHealth() {
        hp.reduceHealth(20.0);
        assertEquals(80.0, hp.getHealth(), "Health should decrease correctly.");

        hp.reduceHealth(100.0);
        assertEquals(0.0, hp.getHealth(), "Health should not go below zero.");
    }

    @Test
    void testSetHealth() {
        hp.setHealth(80.0);
        assertEquals(80.0, hp.getHealth(), "Health should be set correctly.");

        hp.setHealth(150.0);
        assertEquals(100.0, hp.getHealth(), "Health should not exceed initial health.");
    }

    @Test
    void testHealthProperty() {
        hp.healthProperty().set(70.0);
        assertEquals(70.0, hp.getHealth(), "Health property should update health value.");
    }

    @Test
    void testToStringRepresentation() {
        hp.reduceHealth(20.0);
        String expected = "HP{initialHealth=100.0, currentHealth=80.0}";
        assertEquals(expected, hp.toString(), "toString should return the correct format.");
    }

    @Test
    void testEqualsWhenHPIsSame() {
        anotherHP.setHealth(100.0);
        assertEquals(hp, anotherHP, "HP instances with the same health values should be considered equal.");
    }

    @Test
    void testEqualsWhenHPIsDifferent() {
        anotherHP.setHealth(50.0);
        assertNotEquals(hp, anotherHP, "HP instances with different health values should not be considered equal.");
    }

    @Test
    void testHashCodeWhenHPIsSame() {
        anotherHP.setHealth(100.0);
        assertEquals(hp.hashCode(), anotherHP.hashCode(), "Hash codes should be equal for HP instances with the same values.");
    }

    @Test
    void testHashCodeWhenHPIsDifferent() {
        anotherHP.setHealth(50.0);
        assertNotEquals(hp.hashCode(), anotherHP.hashCode(), "Hash codes should be different for HP instances with different values.");
    }
}
