package FirstAssignment;

import java.util.*;

public class GameController {
    private GameModel model;
    private GameView view;
    private Scanner scanner;
    private boolean gameRunning;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.scanner = new Scanner(System.in);
        this.gameRunning = true;
    }

    public void start() {
        Player player = model.getPlayer();
        view.displayWelcomeMessage();
        while (gameRunning) {
            if (player.isDead()) {
                String dead = "You died.";
                System.out.println(ANSI_RED + "\u001B[1m" + dead + ANSI_RESET);
                boolean validChoice = false;
                while (!validChoice) {
                    System.out.println("Do you want to exit or restart the game?");
                    Scanner keyboard = new Scanner(System.in);
                    String playerInput = keyboard.nextLine().toLowerCase();
                    if (playerInput.equalsIgnoreCase("exit")) {
                        gameRunning = false;
                        System.out.println("Exiting game...");
                        validChoice = true;
                        break;
                    } else if (playerInput.equalsIgnoreCase("restart")) {
                        restart();
                        System.out.println("Restarting game...");
                        validChoice = true;
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter 'exit' or 'restart'.");
                        break;
                    }
                }
            } else {
                Room currentRoom = model.getCurrentRoom();
                view.displayRoomInfo(currentRoom);
                view.displayItemsInRoom(currentRoom.getItems());
                String input = getUserInput("What do you want to do? (Type 'help' for available commands): ");
                parseInput(input);
            }
        }
    }

    private void restart() {
        GameModel model = new GameModel();
        GameView view = new GameView();
        GameController controller = new GameController(model, view);
        controller.start();
    }

    private String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().toLowerCase();
    }

    private void parseInput(String input) {
        String[] inputParts = input.split(" ");
        String command = inputParts[0];
        String itemName = inputParts.length > 1 ? inputParts[1] : "";
        String puzzleName = inputParts.length > 1 ? inputParts[1] : "";
        String monsterName = inputParts.length > 1 ? inputParts[1] : "";

        switch (command) {
            case "north":
            case "east":
            case "south":
            case "west":
                movePlayer(command);
                break;
            case "pickup":
                pickupItem(itemName);
                break;
            case "inspect":
                inspectItem(itemName);
                break;
            case "drop":
                dropItem(itemName);
                break;
            case "inventory":
                displayInventory();
                break;
            case "help":
                displayHelp();
                break;
            case "solve":
                solvePuzzle(puzzleName);
                break;
            case "explore":
                exploreRoom();
                break;
            case "equip":
                equipItem(itemName);
                break;
            case "unequip":
                unequipItem();
                break;
            case "player":
                playerStatPrint();
                break;
            case "heal":
                consumeItem(itemName);
                break;
            case "examine":
                examineMonster(monsterName);
                break;
            default:
                view.displayMessage("Invalid command. Type 'help' for available commands.");
        }
    }

    private void exploreRoom() {
        Room currentRoom = model.getCurrentRoom();
        List<Item> roomItems = currentRoom.getItems();
        List<Puzzle> roomPuzzles = new ArrayList<>(currentRoom.getPuzzles().values());
        if (!roomItems.isEmpty()) {
            System.out.println();
            System.out.println("Items in the room:");
            for (Item item : roomItems) {
                System.out.println("- " + item.getItemName());
                System.out.println();
            }
        } else {
            System.out.println();
            System.out.println("There is nothing in this room.");
            System.out.println();
        }
    }

    private void examineMonster(String monsterName) {
        Room currentRoom = model.getCurrentRoom();
        HashMap<String, Monster> monsters = currentRoom.getMonsters();
        model.getPlayer().examine(monsterName, currentRoom);
    }

    private void movePlayer(String direction) {
        Room currentRoom = model.getCurrentRoom();
        currentRoom.setVisited(true);
        model.getPlayer().moveTo(direction, currentRoom.getAdjacentRooms());
    }

    private void pickupItem(String itemName) {
        Room currentRoom = model.getCurrentRoom();
        model.getPlayer().pickup(itemName, currentRoom);
    }

    private void inspectItem(String itemName) {
        model.getPlayer().inspect(itemName);
    }

    private void dropItem(String itemName) {
        Room currentRoom = model.getCurrentRoom();
        model.getPlayer().drop(itemName, currentRoom);
    }

    private void displayInventory() {
        model.getPlayer().inventory();
    }

    private void displayHelp() {
        view.displayMessage("Available commands:");
        view.displayMessage("- Player: view player stats");
        view.displayMessage("- North, East, South, West: move to different rooms");
        view.displayMessage("- Pickup item-name: pick up items");
        view.displayMessage("- Inspect item-name: inspect items");
        view.displayMessage("- Drop item-name: drop items");
        view.displayMessage("- Inventory: view inventory");
        view.displayMessage("- Explore: view the items in the current room");
        view.displayMessage("- Equip item-name: equip items");
        view.displayMessage("- Unequip: unequip items");
        view.displayMessage("- Heal item-name: replenish health");
        view.displayMessage("- Solve puzzle-name: solve for puzzles");
        view.displayMessage("- Examine monster-name: interact with monsters");
        view.displayMessage("");
    }

    private void solvePuzzle(String puzzleName) {
        Room currentRoom = model.getCurrentRoom();
        HashMap<String, Puzzle> puzzles = currentRoom.getPuzzles();
        puzzleName = puzzleName.toLowerCase();
        //System.out.println("Puzzle names: " + puzzles.keySet());
        if (puzzles.containsKey(puzzleName)) {
            Puzzle puzzle = puzzles.get(puzzleName);
            //System.out.println("Attempting to solve: " + puzzleName);
            String answer = getUserInput("Enter your answer: ");
            if (puzzle.solvePuzzle(answer, puzzles)) {
                view.displayMessage("You solved the puzzle!");
            } else {
                view.displayMessage("The answer is wrong, try again. You have " + puzzle.getAttempts() + " remaining.");
                if (puzzle.getAttempts() == 0) {
                    puzzles.remove(puzzleName);
                    view.displayMessage("You failed to solve the puzzle.");
                }
            }
        } else {
            view.displayMessage("No puzzle with that name found in this room.");
            //System.out.println("Puzzle name entered by user: " + puzzleName);
        }
    }

    private void equipItem(String itemName) { model.getPlayer().equip(itemName); }
    private void unequipItem() { model.getPlayer().unequip(); }
    private void playerStatPrint() { model.getPlayer().playerStats(); }
    private void consumeItem(String itemName) { model.getPlayer().consume(itemName); }
}
