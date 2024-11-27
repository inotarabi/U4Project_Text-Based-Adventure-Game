public class Character {
    private String build;
    private int health;
    private int attack;
    private int defense;

    public Character(String build, int health, int attack, int defense){
        this.build = build;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    public void displayStats(){
        System.out.println("Your Build: " + build);
        System.out.println("Health: " + health);
        System.out.println("Attack: " + attack);
        System.out.println("Defense: " + defense);
        System.out.println();
    }

    public String toString() {
        return "Character [Build: " + build + ", Health: " + health + "]";
    }

    // setter methods to set new stats
    public void newHealth(int health){
        this.health = health;    // sets new health stat
    }

    public void newAttack(int attack){
        this.attack = attack;    // sets new health stat
    }

    public void newDefense(int defense){
        this.defense = defense;    // sets new defense stat
    }

    // getter methods for my build stats
    public int getHealth(){    // gets health stat
        return health;
    }

    public int getAttack(){    // gets attack stat
        return attack;
    }

    public int getDefense(){    // gets defense stat
        return defense;
    }
}
