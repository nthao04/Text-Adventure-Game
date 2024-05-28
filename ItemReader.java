package FirstAssignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ItemReader {
    private HashMap<Integer, Room> rooms;
    public ItemReader(HashMap<Integer, Room> rooms) {
        this.rooms = rooms;
    }

    public void readTextFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("Items.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    String itemName = parts[0];
                    String description = parts[1];
                    int roomNumber = Integer.parseInt(parts[2]);
                    Room room = rooms.get(roomNumber);
                    String use = parts[3];
                    int itemStat = Integer.parseInt(parts[4]);
                    if (room != null) {
                        Item item = new Item(itemName, description, use, itemStat);
                        room.addItem(item);
                    }
                }
            }
        }
    }
}
