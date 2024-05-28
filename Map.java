package FirstAssignment;

import java.io.IOException;
import java.util.HashMap;

public class Map {
    private HashMap<Integer, Room> rooms;

    public Map() {
        rooms = new HashMap<>();
        initializeRooms();
    }

    private void initializeRooms() {
        RoomReader roomReader = new RoomReader();
        try {
            roomReader.readTextFile("Rooms.txt");
            rooms = roomReader.getRooms();

            ItemReader itemReader = new ItemReader(rooms);
            itemReader.readTextFile("Items.txt");

            PuzzleReader puzzleReader = new PuzzleReader(rooms);
            puzzleReader.readTextFile("Puzzles.txt");

            MonsterReader monsterReader = new MonsterReader(rooms);
            monsterReader.readTextFile("Monsters.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Room getRoom(int roomId) { return rooms.get(roomId); }
}

