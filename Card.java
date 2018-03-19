public class Card {
    private String nimi;
    // igal kaardil on need 5 omadust (ehk stat'i):
    private int attack;
    private int block;
    private int cleanse;
    private int heal;
    private int madness;
// KONSTRUKTOR
    public Card(String nimi, int attack, int block, int cleanse, int heal, int madness) {
        this.nimi = nimi;
        this.attack = attack;
        this.block = block;
        this.cleanse = cleanse;
        this.heal = heal;
        this.madness = madness;
    }
// GETTERID & SETTERID
    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getCleanse() {
        return cleanse;
    }

    public void setCleanse(int cleanse) {
        this.cleanse = cleanse;
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public int getMadness() {
        return madness;
    }

    public void setMadness(int madness) {
        this.madness = madness;
    }

    public String toString(){
        return getNimi();
    }

}