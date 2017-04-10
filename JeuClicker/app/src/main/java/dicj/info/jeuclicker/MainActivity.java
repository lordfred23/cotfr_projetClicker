package dicj.info.jeuclicker;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Handler;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    double life, lifeAfficher;
    boolean start;
    private Handler progressBarHandler = new Handler();
    int lifeGold,lvlLife;
    CJoueur joueur;
    CMonstre monster;
    CAttacker[] tabAttack;
    int cpt;
    MediaPlayer mp;



    ProgressBar pg;
    TextView txtGold, txtLife, txtLevel, txtKill,txtAttack1,txtAttack2,txtAttack3,txtAttack4,txtAttack5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UiChangeListener();
        hideSystemUI();
        Initialisation_Variable();
        Initialisation_Txt_Attack();


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                {

                    txtGold.setText("" + joueur.getGold());
                    txtLife.setText("" + monster.getLife());
                    pg.setProgress((int) lifeAfficher);
                    txtLevel.setText("Level :" + joueur.getLevel() +"dmg:"+tabAttack[0].getDmg());
                    txtKill.setText(" nb de Kill :" + joueur.getKill());

                    progressBarHandler.postDelayed(this, 300);

                }
            }
        };
        Runnable runable = new Runnable() {
            @Override
            public void run() {
                {
                    killedOrNot();
                    monster.setLife(monster.getLife() - joueur.getDamageSeconde());
                    lifeAfficher = (double) monster.getLife() / life;
                    killedOrNot();
                    progressBarHandler.postDelayed(this, 1000);

                }
            }
        };
        progressBarHandler.post(runnable);
        progressBarHandler.post(runable);


    }
    private void Initialisation_Variable(){
        CAttacker att;
        joueur = new CJoueur();
        life = 100;
        lvlLife=15;
        pg = (ProgressBar) findViewById(R.id.myProgress);
        txtGold = (TextView) findViewById(R.id.idGold);
        txtLife = (TextView) findViewById(R.id.txtLife);
        txtLevel = (TextView) findViewById(R.id.txtLevel);
        txtKill = (TextView) findViewById(R.id.kill);
        txtAttack1=(TextView) findViewById(R.id.txtAttack1);
        txtAttack2=(TextView) findViewById(R.id.txtAttack2);
        txtAttack3=(TextView)findViewById(R.id.txtAttack3);
        txtAttack4=(TextView)findViewById(R.id.txtAttack4);
        txtAttack5=(TextView) findViewById(R.id.txtAttack5);


        start = true;
        cpt=0;
        createMonster(1);
        tabAttack = new CAttacker[5];
        tabAttack[0]= new CAttacker("attack1",25,1,1);
        tabAttack[1]= new CAttacker("attack2",100,2,1);
        tabAttack[2]= new CAttacker("attack3",1000,6,1);
        tabAttack[3]= new CAttacker("attack4",8000,10,1);
        tabAttack[4]= new CAttacker("attack5",15000,15,1);






        lifeAfficher = 100;
    }

    public void onClicky(View v) {


        switch (v.getId()) {
            case R.id.layClick:
                mediaReader();
                jouer();

                break;
            case R.id.attack1:
                if (enoughGold(tabAttack[0].getCost()))
                {
                    joueur.setDamage(joueur.getDamage() + tabAttack[0].getDmg());
                    Augmentation_Attack1();
                }

                break;
            case R.id.attack2:
                if(enoughGold(tabAttack[1].getCost()))
                {
                    joueur.setDamageSeconde(joueur.getDamageSeconde()+tabAttack[1].getDmg());
                    Augmentation_Attack2();
                }
                 break;
            case R.id.attack3:
                if(enoughGold(tabAttack[2].getCost()))
                {
                    joueur.setDamageSeconde(joueur.getDamageSeconde()+tabAttack[2].getDmg());
                    Augmentation_Attack3();
                }
                break;
            case R.id.attack4:
                if(enoughGold(tabAttack[3].getCost()))
                {
                    joueur.setDamageSeconde(joueur.getDamageSeconde()+tabAttack[3].getDmg());
                    Augmentation_Attack4();
                }
                break;
            case R.id.attack5:
                if(enoughGold(tabAttack[4].getCost()))
                {
                    joueur.setDamageSeconde(joueur.getDamageSeconde()+tabAttack[4].getDmg());
                    Augmentation_Attack5();
                }
                break;
            case R.id.marketButton:callMarket();
                break;

        }
        Initialisation_Txt_Attack();

    }

    private void jouer() {
        killedOrNot();
        Attack();
        killedOrNot();


    }
    private void callMarket(){
        Intent intent = new Intent(this,MarketActivities.class);
        startActivity(intent);
    }

    private void createMonster(int level) {
        cpt++;
        if((level % 5 ==0)&&(cpt>=6))
        {
            lvlLife=lvlLife+10;
            cpt=0;
        }
        else

        monster = new CMonstre(level * lvlLife, false);
        lifeGold=monster.getLife();
        post();
    }

    private void Attack() {
        if (Crit_calc()) {
            monster.setLife(monster.getLife() - (joueur.getDamage() * 2));
            lifeAfficher = (double) monster.getLife() / life;
        } else {
            monster.setLife(monster.getLife() - joueur.getDamage());
            lifeAfficher = (double) monster.getLife() / life;
        }

    }

    private void post() {
        this.life = (double) monster.getLife() / 100;

    }

    private void randomGold() {
        Random rn = new Random();
        joueur.setGold(joueur.getGold() + rn.nextInt(lifeGold/2));
    }


    private void hideSystemUI() {
        View mDecorView = getWindow().getDecorView();
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        mDecorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    public void UiChangeListener() {
        final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    decorView.setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                }
            }
        });
    }

    private boolean enoughGold(int cost) {
        if (cost <= joueur.getGold()) {
            joueur.setGold(joueur.getGold() - cost);
            return true;
        } else {
            return false;
        }

    }

    private void killedOrNot() {
        if (monster.getLife() <= 0) {
            mp = MediaPlayer.create(getApplicationContext(), R.raw.explosion);
            mp.start();
            lifeAfficher = 100;
            joueur.setKill(joueur.getKill() + 1);

            if (joueur.getKill() % 5 == 0) {
                randomGold();
                joueur.setLevel(joueur.getLevel() + 1);
                createMonster(joueur.getLevel());

            } else {
                randomGold();
                createMonster(joueur.getLevel());


            }

        }
    }

    private boolean Crit_calc() {
        double min;
        min = 100.00 - joueur.getCritique();
        double x = Math.random() * (100.00);
        x = Math.round(x);

        int retval = Double.compare(x, min);
        if ((retval == 1)||(retval == 0)) {
            return true;
        } else {
            return false;
        }
    }
    private void Initialisation_Txt_Attack(){
        txtAttack1.setText("Cost:"+tabAttack[0].getCost()+" \n dmg:"+tabAttack[0].getDmg());
        txtAttack2.setText("Cost:"+tabAttack[1].getCost()+" \n dommage:"+tabAttack[1].getDmg());
        txtAttack3.setText("Cost:"+tabAttack[2].getCost()+" \n dommage:"+tabAttack[2].getDmg());
        txtAttack4.setText("Cost:"+tabAttack[3].getCost()+" \n dommage:"+tabAttack[3].getDmg());

        if(tabAttack[4].getCost()>=100000){
            int atk4=tabAttack[4].getCost()/1000;
            txtAttack5.setText("Cost:"+atk4+"K"+" \n dommage:"+tabAttack[4].getDmg());
        }else{
            txtAttack5.setText("Cost:"+tabAttack[4].getCost()+" \n dommage:"+tabAttack[4].getDmg());
        }


    }

    private void Augmentation_Attack1() {
            tabAttack[0].setCost((int)Math.round(tabAttack[0].getCost()*1.25));

    }
    private void Augmentation_Attack2(){
        tabAttack[1].setCost((int)Math.round(tabAttack[1].getCost()*1.25));

    }
    private void Augmentation_Attack3(){
        tabAttack[2].setCost((int)Math.round(tabAttack[2].getCost()*1.25));

    }
    private void Augmentation_Attack4(){
        tabAttack[3].setCost((int)Math.round(tabAttack[3].getCost()*1.25));

    }
    private void Augmentation_Attack5(){
        tabAttack[4].setCost((int)Math.round(tabAttack[4].getCost()*1.25));

    }

    private void mediaReader(){
        if (mp != null)
        {
            mp.reset();
        }
        mp = MediaPlayer.create(getApplicationContext(), R.raw.laserblaster);
        mp.start();
    }




}




