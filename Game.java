import java.util.ArrayList;
import java.util.Scanner;

public class Game {


    public static void main(String[] args) {
        // loome kaardid tegelaste jaoks, tugevamad kaardid bosside jaoks
        Card attack = new Card("Attack", 10, 0, 0, 0, 0);
        Card block = new Card("Block", 0, 10, 0, 0, 0);
        Card cleanse = new Card("Cleanse", 0, 0, 2, 0, 0);
        Card heal = new Card("Heal", 0, 0, 0, 15, 0);
        Card bossAttack = new Card("Attack", 20, 0, 0, 0, 2);
        Card bossBlock = new Card("Block", 0, 30, 0, 0, 0);
        Card bossMadness = new Card("Madness", 0, 0, 0, 0, 4);
        ArrayList<Card> kaardid = new ArrayList<>(); // teeme tühjad listid pakkide jaoks
        ArrayList<Card> bossKaardid = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            kaardid.add(attack); // mängija pakki lisatakse 7 attack'i
            kaardid.add(block); // mängija pakki lisatakse 7 block'i
            if (i < 6) {
                kaardid.add(cleanse); // mängija pakki lisatakse 3 cleanse'i
            }
            if (i < 2) {
                bossKaardid.add(bossAttack); // bossi pakki lisatakse 2 bossAttack'i
            }
            if (i < 1) { // bossi pakki lisatakse 1 bossBlock ja 1 bossMadness
                bossKaardid.add(bossBlock);
                bossKaardid.add(bossMadness);
            }
        }
        Deck pakk = new Deck(kaardid); // listist kaardid tehakse deck
        Deck bosspakk = new Deck(bossKaardid); // listist bossKaardid tehakse deck

        Character player = new Character(100, 0, 0, pakk, true); // deck kaardid saab mängijale
        Character boss = new Character(200, 0, 0, bosspakk, false); // deck bossKaardid saab bossile

        player.getDrawPile().shuffle(); // segame mängija kaardid

// MAIN PLAY LOOP
        Scanner reader = new Scanner(System.in);
        boolean gameOn = true;
        boolean firstTurn = true;
        while (gameOn) {
            if (firstTurn == true) {
                System.out.println("You are battling the great scary mountain troll Gorganon! Enter \"9\" to end your turn. Enter \"0\" to exit.");
                player.draw(5); // esimesel käigul tõmbab mängija erandlikult 5 kaarti
                firstTurn = false;
                boss.draw(1); // bossil on alati 1 kaart, ta mängib selle alati
            } else {
                if (boss.seeDrawPile().size() < 1) { // kui bossi drawPile's on alla 1 kaardi
                    for (int i = 0; i < boss.seeDiscardPile().size(); i++) { // ja lisame discardPile'st kaardid drawPile'sse
                        boss.seeDrawPile().add(boss.seeDiscardPile().get(0));
                        boss.seeDiscardPile().remove(0); // teeme discardPile'i tühjaks
                    }
                }
                if (player.seeDrawPile().size() < 3) { // kui mängija drawPile's on alla 3 kaardi
                    player.getDiscardPile().shuffle(); // segame discardPile'i
                    for (int i = 0; i < player.seeDiscardPile().size(); i++) { // ja lisame discardPile'st kaardid drawPile'sse
                        player.seeDrawPile().add(player.seeDiscardPile().get(0));
                        player.seeDiscardPile().remove(0); // teeme discardPile'i tühjaks
                    }
                }
            }
            System.out.println("Your opponent will use: " + boss.getHand());
            System.out.println("Select your course of action - enter three different card numbers."); // teade iga käigu alguses
            for (int i = 0; i < 3; i++) { // võtab mängijalt 3 sisendit (tavaliselt kaardinumbrid)
                System.out.println(player.getHand()); // trükib käe välja
                int kaart = reader.nextInt(); // kuulame, mis number meile antakse
                if (kaart == 0) { // kui saame "0", siis lõpetame mängu
                    gameOn = false;
                    i = 3;
                    System.out.println("Thanks for playing!");
                } else {
                    if (kaart == 9) {
                        i = 3;
                    } // kui saame "9", siis anname käigu bossile edasi
                    else if (0 < kaart && kaart < 21) { // saame kaardinumbri
                        player.play(kaart, player, boss);
                        System.out.println("----YOU----\n" + player);
                        System.out.println("--GORGANON--\n" + boss);
                    }
                }
            }
            if (gameOn == true) {
                boss.play(1, boss, player);
                System.out.println("----YOU----\n" + player);
                System.out.println("--GORGANON--\n" + boss);
                if (player.getHealth() <= 0) {
                    gameOn = false;
                    System.out.println("You died!\nGame over.");
                }
                if (player.getMadness() >= 10) {
                    gameOn = false;
                    System.out.println("You went mad!\nGame over.");
                }
                if (boss.getHealth() <= 0) {
                    gameOn = false;
                    System.out.println("You defeated Gorganon! Congratulations!");
                }
                player.draw(3);
                boss.draw(1);
            }
        }
        reader.close();
    }
}
// mängu lõppu pole veel sisse programmeeritud
