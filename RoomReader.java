package FirstAssignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class RoomReader {
    private static HashMap<Integer, Room> rooms = new HashMap<>();

    public static void readTextFile(String roomFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("Rooms.txt"))) {
            //implement filePath = "Rooms.txt"
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                int id = Integer.parseInt(parts[0]);
                boolean visited = Boolean.parseBoolean(parts[1]);
                String name = parts[2];
                int[] adjacentRooms = Arrays.stream(parts[3].split(",")).mapToInt(Integer::parseInt).toArray();
                String description = parts[4];
                Room room = new Room(id, visited, name, adjacentRooms, description);
                rooms.put(id, room);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public HashMap<Integer, Room> getRooms() {
        return rooms;
    }
}
