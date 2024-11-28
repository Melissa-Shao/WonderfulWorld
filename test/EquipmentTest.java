import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.component.Equipment;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.component.Inventory;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.equipment.Armor;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.equipment.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EquipmentTest {
    private Equipment equipment;
    private Equipment anotherEquipment;
    private Inventory inventory;
    private Weapon peachBlossom;
    private Armor purpleDress;

    @BeforeEach
    void setUp() {
        equipment = new Equipment();
        anotherEquipment = new Equipment();
        inventory = new Inventory(10);
        peachBlossom = new Weapon(1, "Peach Blossom", 15.0, 2.0);
        purpleDress = new Armor(2, "Purple Dress", 20.0);
    }

    @Test
    void testInitialEquipmentState() {
        assertNull(equipment.getWeapon(), "Initially, no weapon should be equipped.");
        assertNull(equipment.getArmor(), "Initially, no armor should be equipped.");
    }

    @Test
    void testEquipWeapon() {
        inventory.addItem(peachBlossom);
        equipment.setEquipment(peachBlossom, inventory);

        assertEquals(peachBlossom, equipment.getWeapon(), "Weapon should be equipped correctly.");
        assertFalse(inventory.isInInventory(peachBlossom), "Equipped weapon should be removed from inventory.");
    }

    @Test
    void testEquipArmor() {
        inventory.addItem(purpleDress);
        equipment.setEquipment(purpleDress, inventory);

        assertEquals(purpleDress, equipment.getArmor(), "Armor should be equipped correctly.");
        assertFalse(inventory.isInInventory(purpleDress), "Equipped armor should be removed from inventory.");
    }

    @Test
    void testReplaceEquippedWeapon() {
        Weapon jadeSword = new Weapon(3, "Jade Sword", 20.0, 3.0);
        inventory.addItem(peachBlossom);
        inventory.addItem(jadeSword);

        equipment.setEquipment(peachBlossom, inventory);
        equipment.setEquipment(jadeSword, inventory);

        assertEquals(jadeSword, equipment.getWeapon(), "New weapon should replace the old weapon.");
        assertTrue(inventory.isInInventory(peachBlossom), "Old weapon should be returned to inventory.");
    }

    @Test
    void testReplaceEquippedArmor() {
        Armor goldenArmor = new Armor(4, "Golden Armor", 25.0);
        inventory.addItem(purpleDress);
        inventory.addItem(goldenArmor);

        equipment.setEquipment(purpleDress, inventory);
        equipment.setEquipment(goldenArmor, inventory);

        assertEquals(goldenArmor, equipment.getArmor(), "New armor should replace the old armor.");
        assertTrue(inventory.isInInventory(purpleDress), "Old armor should be returned to inventory.");
    }

    @Test
    void testUnequipWeapon() {
        inventory.addItem(peachBlossom);
        equipment.setEquipment(peachBlossom, inventory);
        equipment.unsetEquipment(peachBlossom, inventory);

        assertNull(equipment.getWeapon(), "Weapon should be unequipped.");
        assertTrue(inventory.isInInventory(peachBlossom), "Unequipped weapon should return to inventory.");
    }

    @Test
    void testUnequipArmor() {
        inventory.addItem(purpleDress);
        equipment.setEquipment(purpleDress, inventory);
        equipment.unsetEquipment(purpleDress, inventory);

        assertNull(equipment.getArmor(), "Armor should be unequipped.");
        assertTrue(inventory.isInInventory(purpleDress), "Unequipped armor should return to inventory.");
    }

    @Test
    void testToStringRepresentation() {
        inventory.addItem(peachBlossom);
        inventory.addItem(purpleDress);
        equipment.setEquipment(peachBlossom, inventory);
        equipment.setEquipment(purpleDress, inventory);

        String expected = "Equipment{weapon=Weapon{damage=15.0, radius=2.0}, armor=Armor{armor=20.0}}";
        assertEquals(expected, equipment.toString(), "toString should return the correct format.");
    }

    @Test
    void testEqualsWhenEquipmentIsSame() {
        equipment.setEquipment(peachBlossom, inventory);
        equipment.setEquipment(purpleDress, inventory);

        anotherEquipment.setEquipment(peachBlossom, inventory);
        anotherEquipment.setEquipment(purpleDress, inventory);

        assertEquals(equipment, anotherEquipment, "Equipments with the same items should be considered equal.");
    }

    @Test
    void testEqualsWhenEquipmentIsDifferent() {
        Weapon jadeSword = new Weapon(3, "Jade Sword", 20.0, 3.0);
        Armor goldenArmor = new Armor(4, "Golden Armor", 25.0);

        equipment.setEquipment(peachBlossom, inventory);
        equipment.setEquipment(purpleDress, inventory);

        anotherEquipment.setEquipment(jadeSword, inventory);
        anotherEquipment.setEquipment(goldenArmor, inventory);

        assertNotEquals(equipment, anotherEquipment, "Equipments with different items should not be considered equal.");
    }

    @Test
    void testHashCodeWhenEquipmentIsSame() {
        equipment.setEquipment(peachBlossom, inventory);
        equipment.setEquipment(purpleDress, inventory);

        anotherEquipment.setEquipment(peachBlossom, inventory);
        anotherEquipment.setEquipment(purpleDress, inventory);

        assertEquals(equipment.hashCode(), anotherEquipment.hashCode(), "Hash codes should be equal for equipments with the same items.");
    }

    @Test
    void testHashCodeWhenEquipmentIsDifferent() {
        Weapon jadeSword = new Weapon(3, "Jade Sword", 20.0, 3.0);
        Armor goldenArmor = new Armor(4, "Golden Armor", 25.0);

        equipment.setEquipment(peachBlossom, inventory);
        equipment.setEquipment(purpleDress, inventory);

        anotherEquipment.setEquipment(jadeSword, inventory);
        anotherEquipment.setEquipment(goldenArmor, inventory);

        assertNotEquals(equipment.hashCode(), anotherEquipment.hashCode(), "Hash codes should be different for equipments with different items.");
    }
}
