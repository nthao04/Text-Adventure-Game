package FirstAssignment;

import java.util.HashMap;

public class Puzzle {
    private String name;
    private String question;
    private String answer;
    private int attempts;
    private int location;
    private boolean solved;


    public Puzzle(String name, String question, String answer, int attempts, int location) {
        this.name = name;
        this.question = question;
        this.answer = answer;
        this.attempts = attempts;
        this.location = location;
        this.solved = solved;
    }

    public String getName() { return name; }
    public String getQuestion() {
        return question;
    }
    public int getAttempts() {
        return attempts;
    }

    public boolean solvePuzzle(String answer, HashMap<String, Puzzle> puzzles) {
        if (this.attempts > 0) {
            if (this.answer.equalsIgnoreCase(answer)) {
                puzzles.remove(this.name);
                return true;
            } else {
                this.attempts--;
            }
        }
        return false;
    }
}
