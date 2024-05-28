package FirstAssignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class MonsterReader {
    private HashMap<Integer, Room> rooms;
    public MonsterReader(HashMap<Integer, Room> rooms) {
        this.rooms = rooms;
    }
    public void readTextFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("Monsters.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 6) {
                    String monsterName = parts[0];
                    String description = parts[1];
                    int roomNumber = Integer.parseInt(parts[2]);
                    Room room = rooms.get(roomNumber);
                    int health = Integer.parseInt(parts[3]);
                    int attack = Integer.parseInt(parts[4]);
                    boolean dead = Boolean.parseBoolean(parts[5]);
                    if (room != null) {
                        Monster monster = new Monster(monsterName, description, health, attack, dead);
                        room.addMonster(monsterName, monster);
                    }
                }
            }
        }
    }
}
