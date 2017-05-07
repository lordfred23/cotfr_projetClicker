package dicj.info.jeuclicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by utilisateur on 2017-03-27.
 */

public class MarketActivities extends AppCompatActivity {

    valuesMarket vm;
    CJoueur joueur;
    TextView txtTrophy;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market);
        UiChangeListener();
        hideSystemUI();
        vm = new valuesMarket();
        joueur = new CJoueur();
        txtTrophy=(TextView)findViewById(R.id.txtTrophyMarket);
        txtTrophy.setText("Trophy : "+joueur.getTrophy());

    }
    @Override
    public void onResume(){
        super.onResume();
        txtTrophy.setText("Trophy : "+joueur.getTrophy());
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

    public void onClick(View v){
        switch (v.getId()){
            case R.id.back:restartView();
                break;
        }
    }
    private void restartView(){
        Intent intent =new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);

    }
}


