import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.equipment.Armor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArmorTest {

    @Test
    void testConstructorAndGetters() {
        Armor armor = new Armor(1, "Steel Armor", 50.0);

        assertEquals(1, armor.getId());
        assertEquals("Steel Armor", armor.getName());
        assertEquals(50.0, armor.getArmor());
    }

    @Test
    void testToString() {
        Armor armor = new Armor(2, "Leather Armor", 20.0);
        String expected = "Armor{armor=20.0}";

        assertEquals(expected, armor.toString());
    }

    @Test
    void testEquals_DifferentObjectsEqual() {
        Armor armor1 = new Armor(4, "Golden Armor", 70.0);
        Armor armor2 = new Armor(4, "Golden Armor", 70.0);

        assertEquals(armor1, armor2); // Different instances with same data should be equal
    }

    @Test
    void testEquals_DifferentObjectsNotEqual() {
        Armor armor1 = new Armor(5, "Golden Armor", 70.0);
        Armor armor2 = new Armor(6, "Iron Armor", 60.0);

        assertNotEquals(armor1, armor2); // Different instances with different data should not be equal
    }

    @Test
    void testHashCode_EqualObjects() {
        Armor armor1 = new Armor(7, "Golden Armor", 70.0);
        Armor armor2 = new Armor(7, "Golden Armor", 70.0);

        assertEquals(armor1.hashCode(), armor2.hashCode()); // Equal objects should have the same hash code
    }

    @Test
    void testHashCode_NotEqualObjects() {
        Armor armor1 = new Armor(8, "Golden Armor", 70.0);
        Armor armor2 = new Armor(9, "Iron Armor", 60.0);

        assertNotEquals(armor1.hashCode(), armor2.hashCode()); // Non-equal objects should ideally have different hash codes
    }
}

