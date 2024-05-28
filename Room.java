package FirstAssignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Room {
    private int id;
    private boolean visited;
    private String name;
    private int[] adjacentRooms;
    private String description;
    private List<Item> items;
    private HashMap<String, Puzzle> puzzles;
    private HashMap<String, Monster> monsters;


    public Room(int roomId, boolean visited, String name, int[] adjacentRooms, String description) {
        this.id = roomId;
        this.visited = visited;
        this.name = name;
        this.adjacentRooms = adjacentRooms;
        this.description = description;
        this.items = new ArrayList<>();
        this.puzzles = new HashMap<>();
        this.monsters = new HashMap<>();
    }

    public boolean isVisited() { return visited; }
    public void setVisited(boolean visited) { this.visited = visited; }
    public String getName() { return name; }
    public int[] getAdjacentRooms() { return adjacentRooms; }
    public String getDescription() { return description; }
    public List<Item> getItems() { return items; }
    public void addItem(Item item) { items.add(item); }
    public void addPuzzle(String puzzleName, Puzzle puzzle) { puzzles.put(puzzleName, puzzle); }
    public HashMap<String, Puzzle> getPuzzles() { return puzzles; }
    public boolean hasPuzzle() {
        return !puzzles.isEmpty();
    }
    public void addMonster(String monsterName, Monster monster) { monsters.put(monsterName, monster); }
    public HashMap<String, Monster> getMonsters() { return monsters; }
    public boolean hasMonster() { return !monsters.isEmpty(); }
    public void removeMonster(Monster monster) { monsters.remove(monster.getMonsterName()); }
}
