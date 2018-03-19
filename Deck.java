import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> deck;
    private int size;
// KONSTRUKTOR
    public Deck(ArrayList<Card> deck) {
        this.deck = deck;
        this.size = deck.size();
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
        this.size = deck.size();
    }
// PAKI SEGAMINE
    public void shuffle(){
        for (int i = 0; i < size; i++){ // iga kaardikoha x puhul...
            int j = (int)(Math.random() * size); // valime mingi teise suvalise kaardikoha n...
            Card temp = deck.get(i); // salvestame korraks koha x kaardi
            deck.set(i, deck.get(j)); // asetame kohale x kohal n asuva kaardi (koha n kaart on nüüd mõlemal kohal)
            deck.set(j, temp); // ja asetame kohale n kohal x asunud kaardi
        }
    }

}
