package FirstAssignment;

public class Monster {
    private String monsterName;
    private String description;
    private int health;
    private int attack;
    private boolean dead;

    public Monster(String monsterName, String description, int health, int attack, boolean dead) {
        this.monsterName = monsterName;
        this.description = description;
        this.health = health;
        this.attack = attack;
        this.dead = dead;
    }

    public String getMonsterName() { return monsterName; }
    public String getDescription() { return description; }
    public int getHealth() { return health; }
    public int getAttack() { return attack; }
    public boolean isDead() { return dead; }
}
