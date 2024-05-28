package FirstAssignment;

import java.util.List;

public class GameView {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public void displayWelcomeMessage() {
        System.out.println("Welcome to the Space Station!");
        System.out.println();
    }

    public void displayRoomInfo(Room room) {
        System.out.println(ANSI_GREEN + room.getName() + ANSI_RESET);
//        System.out.println(room.getDescription());
        if (room.isVisited()) {
            System.out.println("You have visited this room.");
        }
            System.out.println();
        if (room.hasPuzzle()) {
            Puzzle puzzle = room.getPuzzles().values().iterator().next();
            System.out.println(puzzle.getName() + " - " + puzzle.getQuestion());
            System.out.println();
        }
        if (room.hasMonster()) {
            Monster monster = room.getMonsters().values().iterator().next();
            System.out.println("There is a " + monster.getMonsterName() + "!");
            System.out.println();
        }

    }

    private boolean exploring;

        public void displayItemsInRoom (List < Item > items) {
            if (exploring && !items.isEmpty()) {
                System.out.println();
                System.out.println("Items in the room:");
                for (Item item : items) {
                    System.out.println("- " + item.getItemName());
                    System.out.println();
                    System.out.println();
                }
                System.out.println();
            }
        }

        public void displayMessage (String message){
            System.out.println(message);
            System.out.println();
        }
}