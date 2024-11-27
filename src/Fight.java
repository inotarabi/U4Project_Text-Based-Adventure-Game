
public class Fight {

    public static String startFight(Character player, String playerName, int playerHealth, int playerAttack, int playerDefense, String enemyName, int enemyHealth, int enemyAttack, int enemyDefense) {

        boolean isWin = false;

        // Print initial stats
        printStats(player, playerName, playerHealth, playerAttack, playerDefense, enemyName, enemyHealth, enemyAttack, enemyDefense);
        // Combat loop
        while (playerHealth > 0 && enemyHealth > 0) {
            // Player attacks enemy
            int playerDamage = calculateDamage(playerAttack, enemyDefense);
            enemyHealth = Math.max(0, enemyHealth - playerDamage); // takes the larger number of the two
            System.out.println("\n" + playerName + " attacks " + enemyName + " for " + playerDamage + " damage!");
            delay();


            // Check if the enemy is defeated
            if (enemyHealth <= 0) {
                System.out.println(enemyName + " is defeated! You win!");
                isWin = true;
                break;
            }

            // Enemy attacks player
            int enemyDamage = calculateDamage(enemyAttack, playerDefense);
            playerHealth = Math.max(0, playerHealth - enemyDamage); // takes the larger number of the two
            player.newHealth(playerHealth);
            System.out.println(enemyName + " retaliates and hits " + playerName + " for " + enemyDamage + " damage!");
            delay();

            // Check if the player is defeated
            if (playerHealth <= 0) {
                System.out.println(playerName + " has been defeated.");
                break;
            }

            // Print updated stats
            printStats(player, playerName, playerHealth, playerAttack, playerDefense, enemyName, enemyHealth, enemyAttack, enemyDefense);
            delay();
        }

        if (isWin){
            return "win";
        } else {
            return "lose";
        }
        
    }

    // Calculate damage based on attack and defense
    public static int calculateDamage(int attack, int defense) {
        
        int baseDamage = attack - defense; // base calculation
        int randomVariance = (int) (Math.random() * 5) - 2; // Damage varies upon chance
        int minimumDamage = (int) (attack * 0.2); // Minimum damage based is 20% of attack stat

        // Final damage calculation
        return Math.max(minimumDamage, baseDamage + randomVariance); // takes the larger number of the two
    }

    // Print current stats for player and enemy
    public static void printStats(Character player, String playerName, int playerHealth, int playerAttack, int playerDefense,
                                  String enemyName, int enemyHealth, int enemyAttack, int enemyDefense) {
        System.out.println("\n--- Stats ---");
        System.out.println(playerName + " | Health: " + playerHealth + " | Attack: " + playerAttack + " | Defense: " + playerDefense);
        System.out.println(enemyName + " | Health: " + enemyHealth + " | Attack: " + enemyAttack + " | Defense: " + enemyDefense);
    }

    // delay function i got from online that pauses program for said time
    public static void delay(){
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
