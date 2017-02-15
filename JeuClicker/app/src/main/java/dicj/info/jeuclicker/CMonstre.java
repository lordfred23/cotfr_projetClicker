package dicj.info.jeuclicker;

/**
 * Created by utilisateur on 2017-02-14.
 */

public class CMonstre {
    int life;
    boolean dead;
    public CMonstre(int life,boolean dead){
        this.dead=dead;
        this.life=life;
    }
    public boolean getDead(){
        return dead;
    }
    public void setDead(boolean dead){
        this.dead=dead;
    }
    public int getLife()
    {
        return life;
    }
    public void setLife(int life)
    {
        this.life=life;
    }
}
