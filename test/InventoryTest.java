import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.component.Inventory;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.equipment.Armor;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.equipment.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    private Inventory inventory;
    private Inventory anotherInventory;
    private Armor armorItem;
    private Weapon weaponItem;

    @BeforeEach
    void setUp() {
        inventory = new Inventory(5);
        anotherInventory = new Inventory(5);
        armorItem = new Armor(1, "Purple Dress", 50.0);
        weaponItem = new Weapon(2, "Peach Blossom", 30.0, 5.0);
    }

    @Test
    void testAddItem() {
        assertTrue(inventory.addItem(armorItem), "Should successfully add armor to inventory.");
        assertEquals(1, inventory.getQuantity(), "Inventory should contain one item.");
        assertTrue(inventory.isInInventory(armorItem), "Armor should be in inventory.");
    }

    @Test
    void testRemoveItem() {
        inventory.addItem(weaponItem);
        assertTrue(inventory.removeItem(weaponItem), "Should successfully remove weapon from inventory.");
        assertEquals(0, inventory.getQuantity(), "Inventory should be empty.");
    }

    @Test
    void testIsInInventory() {
        inventory.addItem(armorItem);
        assertTrue(inventory.isInInventory(armorItem), "Armor should be in inventory.");
        assertFalse(inventory.isInInventory(weaponItem), "Weapon should not be in inventory.");
    }

    @Test
    void testIsFull() {
        inventory.addItem(armorItem);
        inventory.addItem(weaponItem);
        assertFalse(inventory.isFull(), "Inventory should not be full.");
    }

    @Test
    void testToString() {
        inventory.addItem(armorItem);
        inventory.addItem(weaponItem);
        String expected = "Inventory{capacity=5, items=[" + armorItem + ", " + weaponItem + "]}";
        assertEquals(expected, inventory.toString(), "toString should match expected format.");
    }

    @Test
    void testEqualsDifferentObjectsSameContent() {
        inventory.addItem(armorItem);
        inventory.addItem(weaponItem);

        anotherInventory.addItem(armorItem);
        anotherInventory.addItem(weaponItem);

        assertEquals(inventory, anotherInventory, "Inventories with the same items and capacity should be equal.");
    }

    @Test
    void testNotEqualsDifferentContent() {
        inventory.addItem(armorItem);

        anotherInventory.addItem(weaponItem);

        assertNotEquals(inventory, anotherInventory, "Inventories with different items should not be equal.");
    }

    @Test
    void testNotEqualsDifferentCapacity() {
        Inventory largerInventory = new Inventory(10);
        assertNotEquals(inventory, largerInventory, "Inventories with different capacities should not be equal.");
    }

    @Test
    void testHashCodeSameObjects() {
        inventory.addItem(armorItem);
        inventory.addItem(weaponItem);

        anotherInventory.addItem(armorItem);
        anotherInventory.addItem(weaponItem);

        assertEquals(inventory.hashCode(), anotherInventory.hashCode(), "Hash codes of equal inventories should match.");
    }

    @Test
    void testHashCodeDifferentObjects() {
        inventory.addItem(armorItem);

        anotherInventory.addItem(weaponItem);

        assertNotEquals(inventory.hashCode(), anotherInventory.hashCode(), "Hash codes of different inventories should not match.");
    }
}
