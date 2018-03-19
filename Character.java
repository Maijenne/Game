import java.util.ArrayList;

public class Character {
    // tegelase kolm põhistati:
    private int health;
    private int shield;
    private int madness; // madness'i rakendatakse vaid mängija puhul
    private ArrayList<Card> hand; // käsi
    private Deck drawPile;
    private Deck discardPile;
    private boolean isPlayer;
// KONSTRUKTOR
    public Character(int health, int shield, int madness, Deck drawPile, boolean isPlayer) {
        this.health = health;
        this.shield = shield;
        this.madness = madness;
        this.drawPile = drawPile;
        this.hand = new ArrayList<>(); // käeks saab tühi list, sinna hakkame deckist kaarte lisama
        this.isPlayer = isPlayer;
        ArrayList discardPile = new ArrayList<Card>(); // kasutatud kaartide pakiks saab tühi deck (tühjast listist tehtud)
        this.discardPile = new Deck(discardPile);
    }
// GETTERID & SETTERID
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public int getMadness() {
        return madness;
    }

    public void setMadness(int madness) {
        this.madness = madness;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setDrawPile(Deck drawPile) {
        this.drawPile = drawPile;
    }

    public ArrayList<Card> seeDrawPile() {
        return drawPile.getDeck();
    } // tagastab drawPile'i deck'i

    public Deck getDrawPile(){
        return drawPile;
    } // tagastab drawPile'i (objektikirje)

    public ArrayList<Card> seeDiscardPile() {
        return discardPile.getDeck();
    } // tagastab discardPile'i deck'i

    public Deck getDiscardPile() {
        return discardPile;
    } // tagastab discardPile'i (objektikirje)

    public void setDiscardPile(Deck discardPile) {
        this.discardPile = discardPile;
    }

    public boolean isPlayer() {
        return isPlayer;
    }
// KAARTIDE KÄTTE LISAMINE
    public void draw(int drawSize){
        if (this.seeDrawPile().size() == 0) { }
        else if (this.seeDrawPile().size() < drawSize) {
            for (int i = 0; i < seeDrawPile().size(); i++) {
                this.hand.add((drawPile.getDeck()).get(0)); // võetakse esimene element drawPile'st ja lisatakse kätte
                seeDrawPile().remove(0); // seejärel kustutatakse kaart drawPile'st
            }
        }
        else for (int i = 0; i < drawSize; i++) { // võetakse just nii palju kui drawSize lubab
            this.hand.add((drawPile.getDeck()).get(0)); // võetakse esimene element drawPile'st ja lisatakse kätte
            seeDrawPile().remove(0); // seejärel kustutatakse kaart drawPile'st
        }
    }

// TEGELASE STATIDE NÄITAMINE
    public String toString(){
        StringBuilder characterUI = new StringBuilder(); // teeme tegelasele tühja statikuva
        String healthBar = "";
        String shieldBar = "";
        String madnessBar = "";
        if (health >= 0){ // tekitame kasti stati "health" numbriga
            if (0 <= health && health < 10){ // tühikute arv sõltub sellest, mitmekohalise arvuga on tegu
                healthBar = "HEALTH  [       " + health + "     ]";
            }
            else if (10 <= health && health < 100){
                healthBar = "HEALTH  [      " + health + "     ]";
            }
            else if (100 <= health){
                healthBar = "HEALTH  [     " + health + "     ]";
            }
            characterUI.append(healthBar); // lisame health-kasti tegelase statikuvale
            characterUI.append("\n"); // võtame statikuval järgmise rea
        }
        if (shield >= 0) { // tekitame kasti stati "shield" numbriga
            if (0 <= shield && shield < 10) { // tühikute arv sõltub sellest, mitmekohalise arvuga on tegu
                shieldBar = "SHIELD  [       " + shield + "     ]";
            } else if (10 <= shield && shield < 100) {
                shieldBar = "SHIELD  [      " + shield + "     ]";
            } else if (100 <= shield) {
                shieldBar = "SHIELD  [     " + shield + "     ]";
            }
            characterUI.append(shieldBar); // lisame shield-kasti tegelase statikuvale
            characterUI.append("\n"); // võtame statikuval järgmise rea
        }
        if (isPlayer){ // kui tegelane on mängija, siis...
            if (0 <= madness && madness < 10) { // tekitame kasti stati "madness" numbriga; tühikute arv sõltub sellest, mitmekohalise arvuga on tegu
                madnessBar = "MADNESS [       " + madness + "     ]";
            }
            else if (madness == 10) {
                madnessBar = "MADNESS [      " + madness + "     ]";
            }
            characterUI.append(madnessBar); // lisame health-kasti tegelase statikuvale
            characterUI.append("\n"); // võtame statikuval järgmise rea
        }
        String characterBars = characterUI.toString(); // teeme StringBuilderi kujul olnud statikuva String'iks
        return characterBars;
    }
// KAARDI MÄNGIMINE
    public void play(int kaardiNr, Character self, Character target){ // kaardi järjekorranumber (hand'is), tegelane ise, tegelase vastane
        int handSize = hand.size();
        for (int i = 0; i < handSize; i++){
            if (!isPlayer) { System.out.println("Your opponent used: " + hand.get(i)); } // kirjutab, mida su vastane kasutas
            if (kaardiNr == i+1) { // inimene valib kaardi numbrite 1-5 seast, aga listi indeksid on 0-4
                Card kaart = hand.get(i);
                if (isPlayer) { System.out.println("You used: " + hand.get(i)); } // kirjutab, mida sa kasutasid
                if (kaart.getAttack() > 0) {
                    int attack = kaart.getAttack();
                    if (target.getShield() > 0){
                        if (target.getShield() < kaart.getAttack()) { // kui attack ületab shield'i
                            attack = kaart.getAttack() - target.getShield();
                            target.setShield(0);
                        } else { // kui shield on võrdne või ületab attack'i
                            target.setShield(target.getShield() - kaart.getAttack());
                            attack = 0;
                        }
                    }
                    target.setHealth(target.getHealth() - attack);
                } if (kaart.getBlock() > 0) {
                    self.setShield(self.getShield() + kaart.getBlock());
                } if (kaart.getHeal() > 0) {
                    if (self.getHealth() < 100){
                        self.setHealth(self.getHealth() + kaart.getHeal());
                        if (self.getHealth() > 100){ // kui heal'iga läksid elud üle maksimumi (100)...
                            self.setHealth(100); // ...siis pannakse elud 100 peale
                        }
                    }
                } if (kaart.getMadness() > 0) {
                    target.setMadness(target.getMadness() + kaart.getMadness());
                } if (kaart.getCleanse() > 0) {
                    self.setMadness(self.getMadness()-kaart.getCleanse());
                    if (self.getMadness() < 0) { // kui cleanse'iga läks madness alla 0...
                        self.setMadness(0); // ...siis pannakse madness 0 peale tagasi
                    }
                }
                this.seeDiscardPile().add(hand.get(i)); // mängitud kaart lisatakse discardPile'i
                getHand().remove(i); // seejärel kustutatakse kaart käest
            }
        }
    }

}