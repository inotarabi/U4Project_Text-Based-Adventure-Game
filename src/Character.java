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
    }

    public void newHealth(int health){
        this.health = health;
    }

    public void newAttack(int attack){
        this.attack = attack;
    }

    public void newDefense(int defense){
        this.defense = defense;
    }

}
