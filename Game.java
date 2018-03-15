import java.util.ArrayList;

public class Game {


    public static void main(String[] args) {
        Card attack = new Card(10, 0,0,0, 0);
        Card block = new Card(0, 10,0,0, 0);
        Card cleanse = new Card(0,0,2, 0, 0);
        Card heal = new Card(0, 0, 0, 15, 0);
        Card bossAttack = new Card(20, 0, 0, 0, 2);
        Card bossBlock = new Card(0, 30, 0, 0, 0);
        Card bossMadness = new Card(0,0,0,0,4);
        ArrayList<Card> kaardid = new ArrayList<>();
        ArrayList<Card> bossKaardid = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            kaardid.add(attack);
            kaardid.add(block);
            if (i < 3){
                kaardid.add(cleanse);
                kaardid.add(heal);
            }
            if (i < 2){
                bossKaardid.add(bossAttack);
            }
            if (i < 1){
                bossKaardid.add(bossBlock);
                bossKaardid.add(bossMadness);
            }
        }
        Deck pakk = new Deck(kaardid);
        Deck bosspakk = new Deck(bossKaardid);

        Character player = new Character(100, 0,0, pakk, true);

        Character boss = new Character(200, 0,0, bosspakk, false);

        player.getDrawPile().shuffle();

        int firstTurn = 0;
        if (firstTurn == 0) {
            player.draw(5);
            firstTurn = 1;
            boss.draw(1);
        }
        else {
            if (player.seeDrawPile().size() < 3){
                player.getDiscardPile().shuffle();
                for (Card kaart: player.seeDiscardPile()){
                    player.seeDrawPile().add(kaart);
                    player.seeDiscardPile().remove(kaart);
                }
            }
            player.draw(3);
        }


        player.play(1, player, boss);
        boss.play(1, boss, player);

        System.out.println(player.getHand());
        System.out.println(player.seeDrawPile());
        System.out.println(player.seeDiscardPile());
        System.out.println(player);
        System.out.println(boss);

        //väljundis peaks olema ka bossi intent, e. mida ta oma käigul teeb, 
        //kui seal on attack, siis näitab numbriliselt kui palju see on, 
        //kui block või madness, siis tekstiliselt aga ei täpsusta hulka.
    }
}
