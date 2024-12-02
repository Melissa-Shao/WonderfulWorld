package io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.view;

import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.controller.InventoryController;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.core.Config;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.GameModel;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.component.Inventory;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.AItem;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.recovery.HealthBottle;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.equipment.Armor;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.items.equipment.Weapon;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.model.entity.Player;
import io.github.wonderfulworld.comp2522202430termprojectwonderfulworld.view.component.inventory.ItemView;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Objects;

/**
 * Represents the inventory view in the application.
 * This class extends AView and defines the behavior and layout
 * of the inventory screen, including item display, equipped items,
 * and hints for user interaction.
 *
 * @author Melissa Shao, Candice Wei
 * @version 2024
 */
public class InventoryView extends AView {
    // Constants for layout dimensions and text sizes
    private static final int GRID_PANE_H_GAP = 32;
    private static final int GRID_PANE_V_GAP = 48;
    private static final int ITEM_INFO_VIEW_SPACING = 24;
    private static final int ITEM_INFO_TITLE_SIZE = 20;
    private static final int ITEMS_SPACING = 24;
    private static final int INVENTORY_TITLE_SIZE = 20;
    private static final int CAPACITY_FONT_SIZE = 16;
    private static final int HINTS_SPACING = 32;
    private static final int HINT_TEXT_SIZE = 20;
    private static final int ITEM_INFO_SPACING = 8;
    private static final String BACKGROUND_STYLE = "-fx-background-color: "
            + "linear-gradient(to bottom, "
            + "#FFE4E1, #FFC0CB);"
            + " -fx-padding: 48px 96px;";
    private static final String ITEM_BOX_STYLE = "-fx-background-color: #FFB6C1;"
            + " -fx-padding: 32px;"
            + " -fx-border-radius: 20px; -fx-background-radius: 20px;"
            + " -fx-border-width: 3px; -fx-border-style: solid;"
            + " -fx-border-color: #FFD700;";


    private Pane itemInfoContainer;
    private FlowPane items;
    private FlowPane equippedItems;
    private Inventory inventory;
    private Player player;

    /**
     * Constructs a new InventoryView with the specified controller.
     *
     * @param controller the InventoryController responsible for handling
     *                   inventory interactions
     */
    public InventoryView(final InventoryController controller) {
        this.controller = controller;
    }

    /**
     * Creates a default FlowPane with predefined spacing and dimensions.
     *
     * @return the configured FlowPane
     */
    private FlowPane createDefaultFlowPane() {
        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(ITEMS_SPACING);
        flowPane.setVgap(ITEMS_SPACING);
        return flowPane;
    }

    /**
     * Creates a GridPane layout with predefined spacing and style.
     * This GridPane serves as the main layout for the inventory view.
     *
     * @return a GridPane configured with default properties
     */
    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(GRID_PANE_H_GAP);
        gridPane.setVgap(GRID_PANE_V_GAP);
        gridPane.setStyle(BACKGROUND_STYLE);
        return gridPane;
    }

    /**
     * Creates a VBox for displaying item details.
     * This box includes a title and a container for showing specific item information.
     *
     * @return a VBox configured for item information display
     */
    private VBox createItemInfoBox() {
        VBox itemInfoView = new VBox();
        itemInfoView.setStyle(ITEM_BOX_STYLE);
        itemInfoView.setSpacing(ITEM_INFO_VIEW_SPACING);

        Text itemInfoTitle = new Text("Item Characteristics");
        itemInfoTitle.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, ITEM_INFO_TITLE_SIZE));
        itemInfoTitle.setFill(Color.WHITE);

        itemInfoContainer = new Pane();

        itemInfoView.getChildren().addAll(itemInfoTitle, itemInfoContainer);
        return itemInfoView;
    }

    /**
     * Creates a VBox for displaying inventory and equipped items.
     * This box includes inventory details, capacity information, and the equipped items section.
     *
     * @return a VBox configured for inventory and equipped items
     */
    private VBox createItemsBox() {
        VBox itemsView = new VBox();
        itemsView.setStyle(ITEM_BOX_STYLE);
        itemsView.setSpacing(ITEMS_SPACING);

        Text inventoryTitle = new Text("Inventory");
        inventoryTitle.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, INVENTORY_TITLE_SIZE));
        inventoryTitle.setFill(Color.WHITE);

        Text capacity = new Text();
        capacity.textProperty().bind(
                inventory.quantityProperty().asString("Capacity %d / ")
                        .concat(inventory.capacityProperty().asString("%d"))
        );
        capacity.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, CAPACITY_FONT_SIZE));
        capacity.setFill(Color.WHITE);

        items = createDefaultFlowPane();

        Text equippedItemsTitle = new Text("Equipped Items");
        capacity.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, CAPACITY_FONT_SIZE));
        equippedItemsTitle.setFill(Color.WHITE);

        equippedItems = createDefaultFlowPane();

        renderItems();
        renderEquippedItems();

        itemsView.getChildren().addAll(inventoryTitle, capacity, items,
                equippedItemsTitle, equippedItems);
        return itemsView;
    }

    /**
     * Creates an HBox for displaying user interaction hints.
     * This box includes instructions for equipping, using, or removing items.
     *
     * @return an HBox configured with user interaction hints
     */
    private HBox createHintsBox() {
        HBox hintsView = new HBox();
        hintsView.setStyle("""
            -fx-background-color: #FFB6C1;
            -fx-padding: 32px;
            -fx-border-radius: 20px;
            -fx-background-radius: 20px;
            -fx-border-width: 3px;
            -fx-border-style: solid;
            -fx-border-color: #FFD700;
            """);
        hintsView.setSpacing(HINTS_SPACING);

        Text hintText = new Text("J: Equip/UnEquip");
        hintText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, HINT_TEXT_SIZE));
        hintText.setFill(Color.WHITE);

        Text hintTextUse = new Text("K: Use");
        hintTextUse.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, HINT_TEXT_SIZE));
        hintTextUse.setFill(Color.WHITE);

        Text hintTextRemove = new Text("L: Remove");
        hintTextRemove.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, HINT_TEXT_SIZE));
        hintTextRemove.setFill(Color.WHITE);

        hintsView.getChildren().addAll(hintText, hintTextUse, hintTextRemove);
        return hintsView;
    }

    /**
     * Attaches property change listeners to update the view dynamically.
     * Listeners handle updates to inventory quantity and equipped items.
     */
    private void attachListeners() {
        inventory.quantityProperty().addListener((_, _, _) -> renderItems());
        player.getEquipment().weaponProperty().addListener((_, _, _)
                -> renderEquippedItems());
        player.getEquipment().armorProperty().addListener((_, _, _)
                -> renderEquippedItems());
    }

    /**
     * Initializes the inventory view by setting up the layout, styles,
     * and attaching event listeners for user interactions.
     */
    @Override
    public void init() {
        GameModel gameModel = GameModel.getInstance();
        player = gameModel.getPlayer();
        inventory = player.getInventory();

        // Main Pane
        GridPane gridPane = createGridPane();

        // Item Info Box
        VBox itemInfoView = createItemInfoBox();
        gridPane.add(itemInfoView, 0, 0);

        // Items Box
        VBox itemsView = createItemsBox();
        gridPane.add(itemsView, 1, 0);
        GridPane.setHgrow(itemsView, Priority.ALWAYS);
        GridPane.setVgrow(itemsView, Priority.ALWAYS);

        // Hints Box
        HBox hintsView = createHintsBox();
        gridPane.add(hintsView, 0, 1, 2, 2);

        // Setting Scene
        scene = new Scene(gridPane, Config.getWindowWidth(), Config.getWindowHeight(), Color.BLACK);

        // Attaching Event Listeners
        attachListeners();

        scene.setOnKeyPressed(((InventoryController) controller)::keyPress);
    }

    /**
     * Renders the inventory view. Currently, this method does not contain
     * any specific rendering logic.
     */
    @Override
    public void render() {
    }

    /**
     * Renders the equipped items in the inventory view.
     * Clears the equipped items container and updates the UI to display
     * the currently equipped weapon and armor.
     */
    private void renderEquippedItems() {
        equippedItems.getChildren().clear();

        // Create new ItemView instances
        ItemView weaponView = new ItemView(this);
        ItemView armorView = new ItemView(this);

        // Set items if they exist
        AItem currentWeapon = player.getCurrentWeapon();
        if (currentWeapon != null) {
            weaponView.setItem(currentWeapon);
        }

        AItem currentArmor = player.getCurrentArmor();
        if (currentArmor != null) {
            armorView.setItem(currentArmor);
        }

        // Add views individually
        equippedItems.getChildren().add(weaponView);
        equippedItems.getChildren().add(armorView);
    }

    /**
     * Renders the inventory items in the inventory view.
     * Clears the inventory container and populates it with all available items.
     */
    private void renderItems() {
        // Clear existing items first
        items.getChildren().clear();

        // Create a new list of item views
        for (int i = 0; i < inventory.getCapacity(); i++) {
            // Create a new ItemView instance for each slot
            ItemView newItemView = new ItemView(this);

            // If there's an item for this slot, set it
            if (i < inventory.getItems().size()) {
                AItem item = inventory.getItems().get(i);
                newItemView.setItem(item);
            }

            // Only add if it's not already in the FlowPane
            if (!items.getChildren().contains(newItemView)) {
                items.getChildren().add(newItemView);
            }
        }
    }

    /**
     * Adds the name of the specified item to the given VBox.
     *
     * @param itemInfo the VBox to which the item name is added
     * @param item     the item whose name is to be displayed
     */
    private void addItemName(final VBox itemInfo, final AItem item) {
        Text nameTitle = new Text("Name:");
        nameTitle.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, HINT_TEXT_SIZE));
        nameTitle.setFill(Color.WHITE);

        Text name = new Text(item.getName());
        name.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, HINT_TEXT_SIZE));
        name.setFill(Color.WHITE);

        itemInfo.getChildren().add(nameTitle);
        itemInfo.getChildren().add(name);
    }

    /**
     * Adds weapon-specific information to the given VBox.
     * Displays the weapon's damage and damage radius.
     *
     * @param itemInfo the VBox to which the weapon information is added
     * @param weapon   the weapon whose details are to be displayed
     */
    private void addWeaponInfo(final VBox itemInfo, final Weapon weapon) {
        Text damageTitle = new Text("Damage:");
        damageTitle.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, HINT_TEXT_SIZE));
        damageTitle.setFill(Color.WHITE);

        Text damage = new Text(String.valueOf(weapon.getDamage()));
        damage.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, HINT_TEXT_SIZE));
        damage.setFill(Color.WHITE);

        Text damageRadiusTitle = new Text("Damage Radius:");
        damageRadiusTitle.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, HINT_TEXT_SIZE));
        damageRadiusTitle.setFill(Color.WHITE);

        Text damageRadius = new Text(String.valueOf(weapon.getRadius()));
        damageRadius.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, HINT_TEXT_SIZE));
        damageRadius.setFill(Color.WHITE);

        itemInfo.getChildren().add(damageTitle);
        itemInfo.getChildren().add(damage);
        itemInfo.getChildren().add(damageRadiusTitle);
        itemInfo.getChildren().add(damageRadius);
    }

    /**
     * Adds armor-specific information to the given VBox.
     * Displays the armor's defense value.
     *
     * @param itemInfo the VBox to which the armor information is added
     * @param armor    the armor whose details are to be displayed
     */
    private void addArmorInfo(final VBox itemInfo, final Armor armor) {
        Text armorTitle = new Text("Armor:");
        armorTitle.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, HINT_TEXT_SIZE));
        armorTitle.setFill(Color.WHITE);

        Text armorProperty = new Text(String.valueOf(armor.getArmor()));
        armorProperty.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, HINT_TEXT_SIZE));
        armorProperty.setFill(Color.WHITE);

        itemInfo.getChildren().add(armorTitle);
        itemInfo.getChildren().add(armorProperty);
    }

    /**
     * Adds health bottle-specific information to the given VBox.
     * Displays the health restoration value of the health bottle.
     *
     * @param itemInfo the VBox to which the health bottle information is added
     * @param bottle   the health bottle whose details are to be displayed
     */
    private void addHealthBottleInfo(final VBox itemInfo, final HealthBottle bottle) {
        Text bottleTitle = new Text("Health:");
        bottleTitle.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, HINT_TEXT_SIZE));
        bottleTitle.setFill(Color.WHITE);

        Text health = new Text("+" + bottle.getHealth());
        health.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, HINT_TEXT_SIZE));
        health.setFill(Color.WHITE);

        itemInfo.getChildren().add(bottleTitle);
        itemInfo.getChildren().add(health);
    }

    /**
     * Sets the item information display to show the details of the specified item.
     *
     * @param item the item whose information is to be displayed
     */
    public void setItemInfo(final AItem item) {
        itemInfoContainer.getChildren().clear();
        if (item == null) {
            return;
        }

        VBox itemInfo = new VBox();
        itemInfo.setSpacing(ITEM_INFO_SPACING);

        addItemName(itemInfo, item);

        switch (item) {
            case Weapon weapon -> addWeaponInfo(itemInfo, weapon);
            case Armor armor -> addArmorInfo(itemInfo, armor);
            case HealthBottle bottle -> addHealthBottleInfo(itemInfo, bottle);
            default -> {
            }
        }

        itemInfoContainer.getChildren().add(itemInfo);
    }

    /**
     * Returns a string representation of the InventoryView.
     *
     * @return a string representation of this InventoryView
     */
    @Override
    public String toString() {
        return "InventoryView{" + "itemInfoContainer=" + itemInfoContainer + ", items=" + items
                + ", equippedItems=" + equippedItems + ", inventory=" + inventory + ", player="
                + player + '}';
    }

    /**
     * Compares this InventoryView with another object for equality.
     *
     * @param object the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        InventoryView that = (InventoryView) object;
        return Objects.equals(itemInfoContainer, that.itemInfoContainer)
                && Objects.equals(items, that.items)
                && Objects.equals(equippedItems, that.equippedItems)
                && Objects.equals(inventory, that.inventory)
                && Objects.equals(player, that.player);
    }

    /**
     * Computes the hash code for this InventoryView.
     *
     * @return the hash code of this InventoryView
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), itemInfoContainer, items,
                equippedItems, inventory, player);
    }
}
