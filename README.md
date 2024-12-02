# Wonderful World

## Table of Contents

- [Description](#description)
- [Technical Requirements](#technical-requirements)
- [Installation and Setup](#installation-and-setup)
- [Features](#features)
- [References](#references)
- [Project member](#project-member)
- [Student number](#student-number)

## Description
Wonderful World is a 2D magical room adventure game where you explore an open world filled with monsters, treasures, and portals. Battle enemies, collect powerful items, and uncover mysterious locations. Manage your inventory, teleport through portals, and fight to stay alive in this magical yet dangerous world.

Will you overcome the dangers and unravel the mysteries of Wonderland? Embark on this journey and find out!


## Technical Requirements

- IDE: IntelliJ IDEA
- Java SDK: 22
- Build Tool: Maven 3.9.9
- Test Framework: JUnit 5

## Installation and Setup
Follow these steps to set up and run the project on your local machine:

1. Clone the repository to your computer.
2. Navigate to the project root directory.
3. Run the application using the JavaFX plugin:
    ```sh
    mvn javafx:run
    ```

## Features

### Main Menu

When the user launches the application, the main menu is displayed.

![Main Menu](https://github.com/Melissa-Shao/COMP-2522-202430-Term-Project-WonderfulWorld/blob/master/image/main_menu.png)

### Game World

1. Press the "Start Game" button to enter the game.
2. Explore the open world by navigating with the **W**, **A**, **S**, **D** keys.

![Game World](https://github.com/Melissa-Shao/COMP-2522-202430-Term-Project-WonderfulWorld/blob/master/image/game_world.png)

### Combat System

1. Attack enemies using the **J** key.
2. Monsters have unique attributes: Speed, Visibility, Attack radius.
3. If a monster notices you, it will chase you until: You kill it. Or you escape through a portal.

![Combat System](https://github.com/Melissa-Shao/COMP-2522-202430-Term-Project-WonderfulWorld/blob/master/image/combat_system.png)

### Game Menu

1. Press **Escape** during gameplay to open the game menu.
2. Options available in the game menu: pause the game, save progress, load a saved game, exit to the main menu, quit the game.

![Game Menu](https://github.com/Melissa-Shao/COMP-2522-202430-Term-Project-WonderfulWorld/blob/master/image/game_menu.png)

### Inventory

1. Collect items (weapons, armor, healing potions) on the map.
2. Open the inventory by pressing the **I** key.
3. Manage inventory with these controls:
- **W**, **A**, **S**, **D**: Navigate through items.
- **J**: Equip or unequip items.
- **K**: Use the selected item.
- **L**: Delete the selected item.
4. Equipped items and selected items are visually highlighted.
5. Press **Escape** to return to the game.

![Inventory](https://github.com/Melissa-Shao/COMP-2522-202430-Term-Project-WonderfulWorld/blob/master/image/inventory.png)

### Portals

Portals allow movement between different locations in the game world. Walk into a portal to teleport to the connected location.

![Portals](https://github.com/Melissa-Shao/COMP-2522-202430-Term-Project-WonderfulWorld/blob/master/image/portal.png)

### End Game

If the player dies, a 'Game Over' screen will be displayed.

![End Game](https://github.com/Melissa-Shao/COMP-2522-202430-Term-Project-WonderfulWorld/blob/master/image/end_game.png)


## Controls

Here is a list of the keybindings for Wonderful World and their functionalities:

### General Controls
- **W, A, S, D**: Move the player up, left, down, or right.
- **Escape**:
    - During gameplay: Opens the game menu (pauses the game).
    - In the game menu: Return to the main menu or exit the game.

### Combat
- **J**: Attack nearby enemies.

### Inventory Management
- **I**: Open the inventory menu.
- **J**: Equip or unequip an item.
- **K**: Use the currently selected item.
- **L**: Delete the currently selected item.
- **Escape**: Close the inventory and return to the game.

### Portals
- **Walk into a portal location**: Automatically teleport to the connected location.


## References
[JavaFX Graphics](https://docs.oracle.com/javafx/2/api/javafx/scene/canvas/GraphicsContext.html#:~:text=This%20class%20is%20used%20to,image%20of%20the%20Canvas%20node.) | [JavaFX Docs](https://openjfx.io/openjfx-docs/) | [MVC Documentation](https://maven.apache.org/guides/index.html) | [MVC Explained](https://www.youtube.com/watch?v=DUg2SWWK18I) | [Maven Tutorial](https://www.youtube.com/watch?v=Xatr8AZLOsE) | [OpenAI](https://openai.com/)

## Project member
- [@Melissa-Shao](https://github.com/Melissa-Shao)
- [@candiceweily](https://github.com/candiceweily)

## Student number
- A01386861
- A01386061