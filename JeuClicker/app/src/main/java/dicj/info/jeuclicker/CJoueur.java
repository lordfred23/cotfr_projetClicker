package dicj.info.jeuclicker;

/**
 * Created by utilisateur on 2017-02-23.
 */

public class CJoueur {
     int gold,kill,damage,damageSeconde,level,trophy;
     double critique,trophyChance;
    public CJoueur()
    {
        gold =1000000;
        kill=0;
        damage=1;
        damageSeconde=0;
        level=1;
        critique=0.50;
        trophy=0;
        trophyChance=0.5;
    }

    public int getDamage() {
        return damage;
    }

    public int getDamageSeconde() {
        return damageSeconde;
    }

    public int getGold() {
        return gold;
    }

    public int getKill() {
        return kill;
    }

    public int getLevel() {
        return level;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDamageSeconde(int damageSeconde) {
        this.damageSeconde = damageSeconde;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setKill(int kill) {
        this.kill = kill;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getCritique() {
        return critique;
    }

    public void setCritique(double critique) {
        this.critique = critique;
    }

    public int getTrophy() {
        return trophy;
    }

    public void setTrophy(int trophy) {
        this.trophy = trophy;
    }

    public double getTrophyChance() {
        return trophyChance;
    }

    public void setTrophyChance(double trophyChance) {
        this.trophyChance = trophyChance;
    }
}
