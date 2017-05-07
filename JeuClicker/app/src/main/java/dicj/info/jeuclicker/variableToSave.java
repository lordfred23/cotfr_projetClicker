package dicj.info.jeuclicker;

/**
 * Created by lordfred23 on 2017-05-01.
 */

public class variableToSave {
    int lifeGold,lvlLife,cpt;
     double life, lifeAfficher;


    public variableToSave(){

    }

    public double getLife() {
        return life;
    }

    public double getLifeAfficher() {
        return lifeAfficher;
    }

    public int getCpt() {
        return cpt;
    }

    public int getLifeGold() {
        return lifeGold;
    }

    public int getLvlLife() {
        return lvlLife;
    }

    public void setCpt(int cpt) {
        this.cpt = cpt;
    }

    public void setLife(double life) {
        this.life = life;
    }

    public void setLifeAfficher(double lifeAfficher) {
        this.lifeAfficher = lifeAfficher;
    }

    public void setLifeGold(int lifeGold) {
        this.lifeGold = lifeGold;
    }

    public void setLvlLife(int lvlLife) {
        this.lvlLife = lvlLife;
    }
}
