package FirstAssignment;

import java.util.*;

public class Player {
    private int currentRoomId;
    private int attack;
    private int health;
    private boolean dead;
    private List<Item> inventory;
    private List<Item> equip;
    public Player(int startingRoomId, int attack, int health, boolean dead) {
        this.currentRoomId = startingRoomId;
        this.inventory = new ArrayList<>();
        this.equip = new ArrayList<>();
        this.attack = attack;
        this.health = health;
        this.dead = dead;

    }
    public int getCurrentRoomId() {
            return currentRoomId;
        }
    public void setCurrentRoomId(int currentRoomId) { this.currentRoomId = currentRoomId; }
    public int getAttack() { return attack; }
    public int getHealth() { return health; }
    public boolean isDead() { return dead; }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\033[0;35m";

    public void playerStats() {
        System.out.println("Player Stats:");
        System.out.println("Health = " + health);
        System.out.println("Attack = " + attack);
        if (equip.size() > 0) {
            System.out.println("Equipped: " + equip.get(0).getItemName());
        } else {
            System.out.println("Equipped: Nothing");
        }
    }

    public void moveTo(String direction, int[] adjacentRooms) {
        int newRoomId = findNewRoom(direction, adjacentRooms);
         if (newRoomId != 0) {
             setCurrentRoomId(newRoomId);
             System.out.println();
             System.out.println("Moved " + direction);
             System.out.println();
         } else if (newRoomId == 0) {
             System.out.println();
             System.out.println("Cannot move " + direction);
             System.out.println();
         }
    }

    private int findNewRoom(String direction, int[] adjacentRooms) {
        if ("north".equals(direction.toLowerCase())) {
            if (adjacentRooms != null) {
                return adjacentRooms[0];
            } else {
                return getCurrentRoomId();
            }
        } else if ("east".equals(direction.toLowerCase())) {
            if (adjacentRooms != null) {
                return adjacentRooms[1];
            } else {
                return getCurrentRoomId();
            }
        } else if ("south".equals(direction.toLowerCase())) {
            if (adjacentRooms != null) {
                return adjacentRooms[2];
            } else {
                return getCurrentRoomId();
            }
        } else if ("west".equals(direction.toLowerCase())) {
            if (adjacentRooms != null) {
                return adjacentRooms[3];
            } else {
                return getCurrentRoomId();
            }
        } else {
            return getCurrentRoomId();
        }
    }

    public void pickup(String itemName, Room currentRoom) {
        List<Item> roomItems = currentRoom.getItems();
        List<Item> itemsToRemove = new ArrayList<>();
        for (Item item : roomItems) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                inventory.add(item);
                itemsToRemove.add(item);
                System.out.println("Picked up " + itemName);
                System.out.println();
                break;
            }
        }
        roomItems.removeAll(itemsToRemove);
        if (itemsToRemove.isEmpty()) {
            System.out.println("Item not found");
            System.out.println();
        }
    }

    public void unequip() {
        if (!equip.isEmpty()) {
            Item unequippedItem = equip.remove(0);
            attack -= unequippedItem.getItemStat();
            System.out.println("Unequipped " + unequippedItem.getItemName());
        } else {
            System.out.println("Nothing to unequip.");
        }
    }

    public void equip(String itemName) {
        boolean itemFound = false;
        if (equip.size() > 0) {
            System.out.println("You have already equipped an item.");
        } else {
            for (Item item : inventory) {
                if (item.getItemName().equalsIgnoreCase(itemName) && item.getItemUse().equalsIgnoreCase("equip")) {
                    equip.add(item);
                    attack += item.getItemStat();
                    System.out.println("Equipped " + itemName);
                    itemFound = true;
                    break;
                }
            }
            if (!itemFound) {
                System.out.println("Item cannot be equipped or found");
                System.out.println();
            }
        }
    }

    public void consume(String itemName) {
        boolean itemFound = false;
        for (Item item : inventory) {
            if (item.getItemName().equalsIgnoreCase(itemName) && item.getItemUse().equalsIgnoreCase("consume")) {
                inventory.remove(item);
                health += item.getItemStat();
                System.out.println("Consumed " + itemName);
                itemFound = true;
                break;
                }
            }
            if (!itemFound) {
                System.out.println("Item cannot be consumed or found");
                System.out.println();
            }
        }

    public void inspect(String itemName) {
        boolean itemFound = false;
        for (Item item : inventory) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                System.out.println(itemName + ": " + item.getItemDescription());
                System.out.println();
                itemFound = true;
                break;
            }
        }
        if (!itemFound) {
            System.out.println("Item not found");
            System.out.println();
        }
    }

    public void examine(String monsterName, Room currentRoom) {
        HashMap<String, Monster> monsters = currentRoom.getMonsters();
        if (monsters.containsKey(monsterName)) {
            Monster monster = monsters.get(monsterName);
            System.out.println("Monster: " + monster.getMonsterName());
            System.out.println("Description: " + monster.getDescription());
            System.out.println("Health: " + monster.getHealth());
            System.out.println("Attack: " + monster.getAttack());

            Scanner keyboard = new Scanner(System.in);

            while (true) {
                System.out.print("Would you like to attack or ignore? ");
                String playerInput = keyboard.nextLine().toLowerCase();
                System.out.println();

                if (playerInput.equalsIgnoreCase("attack") || playerInput.equalsIgnoreCase("ignore")) {
                    if (playerInput.equalsIgnoreCase("attack")) {
                        System.out.println();
                        initiateCombat(monster, currentRoom);
                        break;
                    } else if (playerInput.equalsIgnoreCase("ignore")) {
                        System.out.println();
                        currentRoom.removeMonster(monster);
                        break;
                    }
                } else {
                    System.out.println("Invalid Command");
                    System.out.println();
                }
            }
        } else {
            System.out.println("Monster not found in this room.");
            System.out.println();
        }
    }

    private void initiateCombat(Monster monster, Room currentRoom) {
        System.out.println("A battle begins with " + monster.getMonsterName() + "!");
        int playerHP = this.getHealth();
        int playerATK = this.getAttack();
        int monsterHP = monster.getHealth();
        int monsterATK = monster.getAttack();
        int skillpoint = 0;


        while (!this.isDead() && !monster.isDead()) {
            // Player's Turn
            System.out.println(ANSI_PURPLE + "Your turn:" + ANSI_RESET);
            System.out.println("Player HP: " + playerHP);
            System.out.println(monster.getMonsterName() + " HP: " + monsterHP);
            System.out.print("Choose an action (attack1, attack2, equip, heal, unequip): ");
            Scanner keyboard = new Scanner(System.in);
            String playerInput = keyboard.nextLine().toLowerCase();
            System.out.println();

            if (playerInput.equalsIgnoreCase("attack1")) {
                System.out.println("Attack1");
                monsterHP -= playerATK;
                skillpoint += 1;
                System.out.println("You did " + playerATK + " damage!");
                System.out.println("Player HP: " + playerHP);
                System.out.println(monster.getMonsterName() + " HP: " + monsterHP);
                System.out.println("Skill Points: " + skillpoint);
                System.out.println();
            } else if ((playerInput.equalsIgnoreCase("attack2")) && (skillpoint <= 0)) {
                System.out.println("Not enough skill points");
                System.out.println();
                continue;
            } else if ((playerInput.equalsIgnoreCase("attack2")) && (skillpoint > 0)) {
                System.out.println("Attack2");
                monsterHP -= (playerATK * 2);
                skillpoint -= 1;
                System.out.println("You did " + (playerATK * 2) + " damage!");
                System.out.println("Player HP: " + playerHP);
                System.out.println(monster.getMonsterName() + " HP: " + monsterHP);
                System.out.println("Skill Points: " + skillpoint);
                System.out.println();
            } else if (playerInput.equalsIgnoreCase("equip")) {
                System.out.println("What item would you like to equip?");
                String itemName = keyboard.nextLine();
                equip(itemName);
                continue;
            } else if (playerInput.equalsIgnoreCase("heal")) {
                System.out.println("What item would you like to consume?");
                String itemName = keyboard.nextLine();
                consume(itemName);
                continue;
            } else if (playerInput.equalsIgnoreCase("unequip")) {
                unequip();
                continue;
            } else {
                System.out.println("Invalid action");
                System.out.println();
                continue;
            }
            if (monsterHP <= 0) {
                System.out.println("You defeated " + monster.getMonsterName() + "!");
                System.out.println();
                currentRoom.removeMonster(monster);
                break;
            }

            // Monster's Turn
            System.out.println(ANSI_PURPLE + monster.getMonsterName() + "'s turn:" + ANSI_RESET);
            Random random = new Random();
            int randomNumber = random.nextInt(10);
            if (randomNumber > 4) {
                System.out.println("Attack Multiplied");
                playerHP -= (monsterATK * 2);
                System.out.println("Monster did " + (monsterATK * 2) + " damage!");
                System.out.println("Player HP: " + playerHP);
                System.out.println(monster.getMonsterName() + " HP: " + monsterHP);
                System.out.println();
            } else {
                System.out.println("Normal Attack");
                playerHP -= monsterATK;
                System.out.println("Monster did " + monsterATK + " damage!");
                System.out.println("Player HP: " + playerHP);
                System.out.println(monster.getMonsterName() + " HP: " + monsterHP);
                System.out.println();
            }

            if (playerHP <= 0) {
                System.out.println();
                dead = true;
                return;
            }
        }
    }

    public void drop(String itemName, Room currentRoom) {
        List<Item> itemsToRemove = new ArrayList<>();
        for (Item item : inventory) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                currentRoom.addItem(item);
                itemsToRemove.add(item);
                System.out.println("Dropped " + itemName);
                System.out.println();
                break;
            }
        }
        inventory.removeAll(itemsToRemove);
        if (itemsToRemove.isEmpty()) {
            System.out.println("Item not found");
            System.out.println();
        }
    }

    public void inventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty");
            System.out.println();
        } else {
            System.out.println("Inventory:");
            for (Item item : inventory) {
                System.out.println(item.getItemName());
                System.out.println();
            }
        }
    }

}
