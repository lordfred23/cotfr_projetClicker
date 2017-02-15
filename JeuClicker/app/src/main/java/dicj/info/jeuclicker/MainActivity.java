package dicj.info.jeuclicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {


    int test,level,damage;
    double life,lifeAfficher;
    boolean monsterDead,start;
    private Handler progressBarHandler = new Handler();
    CMonstre monster;


     ProgressBar pg;
     TextView txtGold;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UiChangeListener();
        hideSystemUI();
        test=0;
        life=100;
        level=0;
        pg =(ProgressBar)findViewById(R.id.myProgress);
        txtGold = (TextView)findViewById(R.id.idGold);
        start=true;
        monsterDead=true;
        damage=1;
        lifeAfficher=0;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                {

                        txtGold.setText("" + test);
                        pg.setProgress( (int)lifeAfficher);
                        progressBarHandler.postDelayed(this, 300);

                }
            }
        };
        progressBarHandler.post(runnable);






    }
    public void onClicky(View v) {


        switch (v.getId())
        {
            case R.id.layClick:jouer();


                break;
        }

    }
    private void jouer()
    {
        createMonster(level);
        Attack();

    }
    private void createMonster(int level){
        if(monsterDead) {
            level += 1;
            monster = new CMonstre(level * 10, false);
            post(monster.getLife());
            monsterDead=false;




        }

    }
    private void Attack() {
        monster.setLife(monster.getLife()-damage);
        test+=1;
        lifeAfficher=(double)monster.getLife()/life;
    }
    private void post(int life) {
       this.life = (double)monster.getLife()/100;

    }



    private void hideSystemUI() {
        View mDecorView= getWindow().getDecorView();
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

    public void UiChangeListener()
    {
        final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener (new View.OnSystemUiVisibilityChangeListener() {
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

}


