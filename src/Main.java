import java.util.Scanner;

public class Main {
    static String playerName;
    static Character player;
    
    static boolean gameOn = true;
    static boolean currentlyChoosingBuild;

    public static void main(String[] args) {

        // Beginning Description
        System.out.println("The kingdom of Edlas has been thriving for 200 years. \r\n" +  "However, recently after the murder of the King, the demon lord Azgoth has taken over and now the kingdom is in peril. \r\n" + "The kingdom has been destroyed and demons are everywhere. \r\n" + //
                        "You, a young mercenary, are drawn into the chaos and must navigate through this world with countless challenges and defeat the accursed demon lord Azgoth. \r\n" +
                        "");

        // asks for player name
        System.out.print("What is your name Player: ");
        Scanner nameInput = new Scanner(System.in);
        playerName = nameInput.nextLine();

        clearScreenOnly(); // clears screen
        startGame(); // starts game
        gameLoop();

    }

    public static void startGame(){

        String tempSelection = "";
        currentlyChoosingBuild = true;

        System.out.println("Welcome " + playerName);

        // build choose logic
        while (currentlyChoosingBuild){
            // User input for specific build
            System.out.println("Do you want to choose Assassin, Knight, or Healer: ");
            Scanner buildInfo = new Scanner(System.in);
            tempSelection = buildInfo.nextLine().toLowerCase();

            // prints build info
            printBuildInfo(tempSelection);

            // Scammer to confirm or go back
            System.out.println("Press Y to comfirm selection. Press N to go back and choose another");
            Scanner input = new Scanner(System.in);
            String playerInput = input.nextLine().toLowerCase();

            
            if (playerInput.equals("y")){ // if yes breaks out of loop
                currentlyChoosingBuild = false; 
                clearScreenOnly(); 
                break;
            } else if (playerInput.equals("n")){ // if no restarts loop
                clearScreenOnly(); 
            }
        }

        // creates the permananent player object and stores it inside player
        if (tempSelection.equals("assassin")){
            player = new Assassin();
        } else if (tempSelection.equals("knight")){
            player = new Knight();
        } else if (tempSelection.equals("healer")){
            player = new Healer();
        }

        System.out.println(player.toString());
   
    }

    public static void gameLoop(){

        // game loop
        while (gameOn){
            
            // User input for quiting or continue
            System.out.println("Do you want to explore (1) (chance of finding enemy) or do you want to quit game (2)");
            Scanner input = new Scanner(System.in);
            int choice = input.nextInt();

            if (choice == 1){
                // encounter enemy code goes here
                encounterEnemy();
            } else if (choice == 2){
                // end game code goes here
                gameOn = false;
                endGame("quit");
            }
        }
    }

    // handles logic of encountering an enemy
    public static void encounterEnemy(){

        System.out.println("\nYou decide to explore the area...");

        if (Math.random() > 0.3){ // 70% chance of finding an enemy
            String[] enemy = generateRandomEnemy(); // is the returned enemy

            // generates enemy stats
            int enemyHealth = generateEnemyStat(enemy[1], enemy[2]);
            int enemyAttack = generateEnemyStat(enemy[3], enemy[4]);
            int enemyDefense = generateEnemyStat(enemy[5], enemy[6]);

            // prints out enemy details
            System.out.println("\nA wild " + enemy[0] + " appears!");
            System.out.println("Enemy Health: " + enemyHealth);
            System.out.println("Enemy Attack: " + enemyAttack);
            System.out.println("Enemy Defense: " + enemyDefense);

            String fightResult = Fight.startFight(player, playerName, player.getHealth(), player.getAttack(), player.getDefense(), 
            enemy[0], enemyHealth, enemyAttack, enemyDefense);
            gameOn = false;
            endGame(fightResult);

        } else {
            System.out.println("You got lucky. You found no enemies.");
            gameOn = false;
            endGame("win");
        }
    }

    // generates random enemy and returns the array with all the enemy info
    public static String[] generateRandomEnemy(){

        // 2D array of enemies
        // {name, minHealth, maxHealth, minAttack, maxAttack, minDefense, maxDefense, spawnChance}
        String[][] enemies = {
            {"goblin", "10", "15", "5", "10", "2", "5", "0.6"},
            {"bandit", "10", "20", "5", "10", "5", "10", "0.25"},
            {"goblin king", "30", "60", "30", "50", "30", "40", "0.1"},
            {"dragon", "100", "150", "50", "75", "75", "100", "0.05"} 
        };

        double randomChance = Math.random();
        double cumulativeChance = 0.0; // is used to track the running total chance as the it loops through enemies
        
        for (int i = 0; i < enemies.length; i++){
            String[] enemy = enemies[i];
            cumulativeChance += Double.parseDouble(enemy[7]);
            if (randomChance <= cumulativeChance) {
                return enemy;
            }
        }

        return enemies[0];
    }

    public static int generateEnemyStat(String min, String max){

        // converts string to int
        int minVal = Integer.parseInt(min);
        int maxVal = Integer.parseInt(max);
        return (int) (Math.random() * (maxVal - minVal + 1)) + minVal; // random number between min and max inclusive

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

    // game over 
    public static void endGame(String ending){
        if (ending.equals("lose")){
            System.out.println("Game Over!! You Died");
        } else if (ending.equals("win")){
            System.out.println("You survived for today. You Won!!");
        } else {
            System.out.println("I expected better. LLL Your a quitter");
        }
        
    }

    // only clears screen
    public static void clearScreenOnly() {
       
        System.out.print("\033[H\033[2J");
        System.out.flush();        

    }
}
