package dicj.info.jeuclicker;

/**
 * Created by utilisateur on 2017-03-06.
 */

public class CAttacker {
    String nom;
    int cost,dmg,level;

    public CAttacker(String nom,int cost,int dmg,int level)
    {
        this.nom=nom;
        this.cost=cost;
        this.dmg=dmg;
        this.level=level;
    }

    public int getCost() {
        return cost;
    }

    public int getDmg() {
        return dmg;
    }

    public String getNom() {
        return nom;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
