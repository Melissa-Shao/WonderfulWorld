import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.equipment.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponTest {

    @Test
    void testConstructorAndGetters() {
        Weapon weapon = new Weapon(1, "Sword of Power", 100.0, 1.5);

        assertEquals(1, weapon.getId());
        assertEquals("Sword of Power", weapon.getName());
        assertEquals(100.0, weapon.getDamage());
        assertEquals(1.5, weapon.getRadius());
    }

    @Test
    void testToString() {
        Weapon weapon = new Weapon(2, "Axe of Fury", 75.0, 2.0);
        String expected = "Weapon{damage=75.0, radius=2.0}";

        assertEquals(expected, weapon.toString());
    }

    @Test
    void testEquals_DifferentObjectsEqual() {
        Weapon weapon1 = new Weapon(4, "Staff of Wisdom", 30.0, 5.0);
        Weapon weapon2 = new Weapon(4, "Staff of Wisdom", 30.0, 5.0);

        assertEquals(weapon1, weapon2); // Different instances with the same data should be equal
    }

    @Test
    void testEquals_DifferentObjectsNotEqual() {
        Weapon weapon1 = new Weapon(5, "Hammer of Justice", 60.0, 3.0);
        Weapon weapon2 = new Weapon(6, "Dagger of Shadows", 40.0, 1.0);

        assertNotEquals(weapon1, weapon2); // Different instances with different data should not be equal
    }

    @Test
    void testHashCode_EqualObjects() {
        Weapon weapon1 = new Weapon(7, "Flail of Doom", 80.0, 2.5);
        Weapon weapon2 = new Weapon(7, "Flail of Doom", 80.0, 2.5);

        assertEquals(weapon1.hashCode(), weapon2.hashCode()); // Equal objects should have the same hash code
    }

    @Test
    void testHashCode_NotEqualObjects() {
        Weapon weapon1 = new Weapon(8, "Whip of Agony", 90.0, 3.0);
        Weapon weapon2 = new Weapon(9, "Shield of Honor", 50.0, 0.0);

        assertNotEquals(weapon1.hashCode(), weapon2.hashCode()); // Non-equal objects should ideally have different hash codes
    }
}
