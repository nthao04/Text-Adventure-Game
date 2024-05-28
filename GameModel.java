package FirstAssignment;

public class GameModel {
    private Map gameMap;
    private Player player;

    public GameModel() {
        this.gameMap = new Map();
        this.player = new Player(1, 10, 100, false);
    }

    public Room getCurrentRoom() {
        return gameMap.getRoom(player.getCurrentRoomId());
    }
    public Player getPlayer() {
        return player;
    }

}
