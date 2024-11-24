
import java.util.Scanner;

import javax.swing.text.html.StyleSheet;

public class Main {

    static String playerName;
    static Character player;
    static boolean gameOn = true;
    static boolean hasFled = false;
    public static void main(String[] args) {

        System.out.print("What is your name Player: ");
        Scanner nameInput = new Scanner(System.in);
        playerName = nameInput.nextLine();

        clearScreen();
        startGame();
        gameLoop();
        

    }
   
    public static void startGame(){
        System.out.println("The kingdom of Edlas has been thriving for 200 years. However, recently after the murder of the King, the demon lord Azgoth has taken over and now the kingdom is in peril. The kingdom has been destroyed and demons are everywhere. \r\n" + //
                        "You, a young mercenary, are drawn into the chaos and must navigate through this world with countless challenges and defeat the accursed demon lord Azgoth. \r\n" + 
                        "");

        String altSelection = "";
        boolean currentlyChoosingBuild = true;

        System.out.println("Welcome " + playerName);

        while (currentlyChoosingBuild){

            System.out.println("Do you want to choose Assassin, Knight, or Healer: ");
            Scanner buildInfoWant = new Scanner(System.in);
            altSelection = buildInfoWant.nextLine().toLowerCase();
           
            printBuildInfo(altSelection);

            System.out.println("Press Y to comfirm selection. Press N to go back and choose another");
            Scanner input = new Scanner(System.in);
            String playerInput = input.nextLine().toLowerCase();

            if (playerInput.equals("y")){
                currentlyChoosingBuild = false;
                clearScreen();
                break;
            } else if (playerInput.equals("n")){
                clearScreen();
            }
        }

        // creates the permananent player object
        if (altSelection.equals("assassin")){
            player = new Assassin();
        } else if (altSelection.equals("knight")){
            player = new Knight();
        } else if (altSelection.equals("healer")){
            player = new Healer();
        }
   
    }

    // creates temp objects just to print eacb builds info
    public static void printBuildInfo(String build){
        if (build.equals("assassin")){
            Assassin altMyCharacter = new Assassin();
            altMyCharacter.displayStats();
        } else if (build.equals("healer")){
            Healer altMyCharacter = new Healer();
            altMyCharacter.displayStats();
        } else if (build.equals("knight")){
            Knight altMyCharacter = new Knight();
            altMyCharacter.displayStats();
        }
    }

    public static void gameLoop(){
        

        while (gameOn){
            clearScreen();
            player.displayStats();

            System.out.println("Do you want to explore (1) (chance of finding enemy) or do you want to quit game (2)");
            Scanner choiceInput = new Scanner(System.in);
            int choice = choiceInput.nextInt();
            
            if (choice == 1){
                encounterEnemy();
            } else if (choice == 2){
                endGame();
            }
        }

        // fight("");
    }

    // the fight method that handles all attacks and damages dealt
    public static void fight(String enemy){

        int enemyDamage;
        int actualEnemyInflictedDamage;
        int playerDamage;
        int actualPlayerInflictedDamage;

        
        System.out.println("You engage in a fierce battle with a " + enemy + "!");

        enemyDamage = (int) ((Math.random() * (player.getDefense() + 15)) + 1); // range of damage is 15 more than player defense
        if (enemyDamage > player.getDefense()){
            actualEnemyInflictedDamage = (int) ((Math.random() * 5) + 1); // actual damage to be subtracted
            if (actualEnemyInflictedDamage == 5){
                actualEnemyInflictedDamage *= 1.5; // if random chooses 5, it makes a critical hit
            }
            player.newHealth(player.getHealth() - actualEnemyInflictedDamage); // new health
            System.out.println("The enemy took " + actualEnemyInflictedDamage + " hearts");
        } else if (enemyDamage <= player.getDefense()){
            player.newDefense(player.getDefense() - 2); // regardless if attack doesn't work chips away at defense
            System.out.println("No hearts taken but defense damaged");
        }


    }

    public static void encounterEnemy(){

        boolean inCombat = false;

        System.out.println("\nYou decide to explore the area...");


        if (Math.random() > 0.7){ // 70% chance of finding an enemy while exploring
            getRandomEnemy();
            inCombat = true;

            // System.out.println("\nA wild " + enemyType + " appears!");
            // System.out.println("Enemy Health: " + enemyHealth);
            // System.out.println("Enemy Attack: " + enemyAttack);
        }

        while (inCombat){
            System.out.println("\nSurprise!! An enemy has appeared");

        }

    }

    

    public static void getRandomEnemy(){

        double randomEnemyChance = Math.random();
        String randomEnemy = "";

        int enemyHealth;
        int enemyAttack;
        int enemyDefense;

        if (randomEnemyChance <= 0.6){ // 60% chance of goblin spawn

            randomEnemy = "goblin";
            enemyHealth = generateEnemyStat(10, 15);
            enemyAttack = generateEnemyStat(5, 10);
            enemyDefense = generateEnemyStat(2, 5);

        } else if (randomEnemyChance < 0.85){ // 25% chance of bandit

            randomEnemy = "bandit";
            enemyHealth = generateEnemyStat(10, 20);
            enemyAttack = generateEnemyStat(5, 10);
            enemyDefense = generateEnemyStat(5, 10);

        } else if (randomEnemyChance < 0.95){ // 10% chance of goblin king

            randomEnemy = "goblin king";
            enemyHealth = generateEnemyStat(30, 60);
            enemyAttack = generateEnemyStat(30, 50);
            enemyDefense = generateEnemyStat(30, 40);

        } else {

            randomEnemy = "dragon"; // 5% chance of dragon
            enemyHealth = generateEnemyStat(100, 150);
            enemyAttack = generateEnemyStat(50, 75);
            enemyDefense = generateEnemyStat(75, 100);
        }

    }

    public static int generateEnemyStat(int firstNumInRange, int secondNumInRange){
        int stat = (int) (Math.random() * (secondNumInRange - firstNumInRange + 1) + firstNumInRange);
        return stat;
    }

    public static void endGame(){

    }

    // clears the terminal
    public static void clearScreen() { 
        
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}




