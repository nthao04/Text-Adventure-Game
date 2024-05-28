package FirstAssignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class PuzzleReader {

    private HashMap<Integer, Room> rooms;
    public PuzzleReader(HashMap<Integer, Room> rooms) {
        this.rooms = rooms;
    }
    public void readTextFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("Puzzles.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    String puzzleName = parts[0].trim();
                    String question = parts[1];
                    String answer = parts[2].trim();
                    int attempts = Integer.parseInt(parts[3]);
                    int roomNumber = Integer.parseInt(parts[4]);
                    Room room = rooms.get(roomNumber);
                    if (room != null) {
                        Puzzle puzzle = new Puzzle(puzzleName, question, answer, attempts, roomNumber);
                        room.addPuzzle(puzzleName, puzzle);
                    }
                }
            }
        }
    }
}

