package dicj.info.jeuclicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Handler;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    int test, level, damage, kill,damageSeconde;
    double life, lifeAfficher;
    boolean start;
    private Handler progressBarHandler = new Handler();
    CMonstre monster;


    ProgressBar pg;
    TextView txtGold, txtLife, txtLevel, txtKill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UiChangeListener();
        hideSystemUI();
        test = 0;
        life = 100;
        level = 1;
        pg = (ProgressBar) findViewById(R.id.myProgress);
        txtGold = (TextView) findViewById(R.id.idGold);
        txtLife = (TextView) findViewById(R.id.txtLife);
        txtLevel = (TextView) findViewById(R.id.txtLevel);
        txtKill = (TextView) findViewById(R.id.kill);
        start = true;
        damageSeconde=3;
        createMonster(1);
        damage = 1;
        kill = 0;
        lifeAfficher = 100;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                {

                    txtGold.setText("" + test);
                    txtLife.setText("" + monster.getLife());
                    pg.setProgress((int) lifeAfficher);
                    txtLevel.setText("Level :" + level);
                    txtKill.setText(" nb de Kill :" + kill);
                    progressBarHandler.postDelayed(this, 300);

                }
            }
        };
        Runnable runable = new Runnable() {
            @Override
            public void run() {
                {
                    killedOrNot();
                    monster.setLife(monster.getLife() - damageSeconde);
                    lifeAfficher = (double) monster.getLife() / life;
                    killedOrNot();
                        progressBarHandler.postDelayed(this, 1000);

                }
            }
        };
        progressBarHandler.post(runnable);
        progressBarHandler.post(runable);


    }

    public void onClicky(View v) {


        switch (v.getId()) {
            case R.id.layClick:
                jouer();
                break;
            case R.id.attack1:
                if (enoughGold(25))
                    damage++;

                break;
        }

    }

    private void jouer() {
        killedOrNot();
        Attack();
        killedOrNot();



    }

    private void createMonster(int level) {


        monster = new CMonstre(level * 10, false);
        post();
    }

    private void Attack() {
        monster.setLife(monster.getLife() - damage);
        lifeAfficher = (double) monster.getLife() / life;
    }

    private void post() {
        this.life = (double) monster.getLife() / 100;

    }

    private void randomGold() {
        Random rn = new Random();
        test += rn.nextInt(10) * level;
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
        if (cost <= test) {
            test = test - cost;
            return true;
        }
        else {
            return false;
        }

    }
    private void killedOrNot()
    {
        if (monster.getLife() <= 0) {
            lifeAfficher = 100;

            kill++;
            if (kill % 5 == 0) {
                randomGold();
                createMonster(level++);
            } else {
                createMonster(level);
                randomGold();
            }

        }
    }
}




