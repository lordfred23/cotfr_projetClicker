package dicj.info.jeuclicker;

/**
 * Created by utilisateur on 2017-02-23.
 */

public class CJoueur {
    int gold,kill,damage,damageSeconde,level;
    public CJoueur()
    {
        gold =0;
        kill=0;
        damage=1;
        damageSeconde=0;
        level=1;
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
}
