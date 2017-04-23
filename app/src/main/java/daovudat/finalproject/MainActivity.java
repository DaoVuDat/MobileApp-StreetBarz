package daovudat.finalproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vstechlab.easyfonts.EasyFonts;

public class MainActivity extends AppCompatActivity {
    ProgressBar pro;
    int progress = 0;
    Handler h = new Handler();
    TextView title;
    ImageView loadscreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        loadscreen = (ImageView)findViewById(R.id.ImageLoadscreen);
        Picasso.with(getApplicationContext())
                .load(R.drawable.lg1)
                .resize(620,820)
                .into(loadscreen);

        pro=(ProgressBar)findViewById(R.id.progressBar);
        title = (TextView)findViewById(R.id.TextTitle);
        title.setTypeface(EasyFonts.captureIt(this));
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10;i++)
                {
                    progress += 20;
                    h.post(new Runnable() {
                        @Override
                        public void run() {
                            pro.setProgress(progress);
                            if(progress  == pro.getMax())
                            {
                                 Intent intent = new Intent (getApplicationContext(),AboutApp.class);
                                 startActivity(intent);
                                 finish();
                            }
                        }
                    });
                    try{
                        Thread.sleep(550);
                    }catch (InterruptedException e)
                    {
                    }
                }
            }
        }).start();
    }
}