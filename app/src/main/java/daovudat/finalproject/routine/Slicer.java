package daovudat.finalproject.routine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.vstechlab.easyfonts.EasyFonts;

import daovudat.finalproject.Exercises;
import daovudat.finalproject.R;

/**
 * Created by Dao Vu Dat on 9/4/2016.
 */
public class Slicer extends AppCompatActivity {

    private ShowcaseView showcaseView;
    int index = 0;
    private RadioGroup radioGroup;
    private RadioButton btn1, btn2, btn3 , btn4, btn5, btn6;
    private TextView name,exe1, exe2, exe3,exe4, exe5, exe6,cycle;
    private ImageButton start,view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);

        init();
        RunFirstTime();
        setDetail();

        // Do function
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if(btn1.getId() == selectedId)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=akgixqoy4Rw")));
                }
                else if (btn2.getId() == selectedId)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=4Azm8-c_k4Y")));
                }
                else if (btn3.getId() == selectedId)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=qHBTBRYmoV4")));
                }
                else if (btn4.getId() == selectedId)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Y1R3-cJxy4s")));
                }
                else if (btn5.getId() == selectedId)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=ta1UQKbqlQw")));
                }
                else if (btn6.getId() == selectedId)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dNaNk02kH4g")));
                }
            }
        });


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DetailSlicer.class);
                startActivity(intent);
            }
        });

    }

    private void setDetail() {
        name.setText("SLICER");
        name.setTypeface(EasyFonts.captureIt(this));

        exe1.setText("Plank");
        exe2.setText("Side Plank Right");
        exe3.setText("Side Plank Left");
        exe4.setText("Lying Leg Raises");
        exe5.setText("Half Burpees");
        exe6.setText("Crunches");

        cycle.setText("3 cycles");
    }

    private void RunFirstTime() {
        Boolean isFirstRun = getSharedPreferences("PREFERENCE",MODE_PRIVATE).getBoolean("FirstRun",true);
        if(isFirstRun)
        {
            showcaseView = new ShowcaseView.Builder(this)
                    .setTarget(Target.NONE)
                    .setStyle(R.style.CustomShowcaseTheme2)
                    .setContentTitle("This is rountine")
                    .setContentText("Click 'Next' to next the tutorial.")
                    .hideOnTouchOutside()
                    .blockAllTouches()
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (index){
                                case 0:
                                    showcaseView.setShowcase(new ViewTarget(name),true);
                                    showcaseView.setContentTitle("Name");
                                    showcaseView.setContentText("Name of the routine.");
                                    showcaseView.setButtonText("Next");
                                    break;
                                case 1:
                                    showcaseView.setShowcase(new ViewTarget(view),true);
                                    showcaseView.setContentTitle("View");
                                    showcaseView.setContentText("Choose one item and click here to know how to practise that exercise.");
                                    showcaseView.setButtonText("Next");
                                    break;
                                case 2:
                                    showcaseView.setShowcase(new ViewTarget(exe1),true);
                                    showcaseView.setContentTitle("Exercise");
                                    showcaseView.setContentText("Name of the exercise.");
                                    showcaseView.setButtonText("Next");
                                    break;
                                case 3:
                                    showcaseView.setShowcase(new ViewTarget(btn1),true);
                                    showcaseView.setContentTitle("Choose button");
                                    showcaseView.setContentText("Choose here and click view to watch exercise.");
                                    showcaseView.setButtonText("Next");
                                    break;
                                case 4:
                                    showcaseView.setShowcase(new ViewTarget(cycle),true);
                                    showcaseView.setContentTitle("Cycle");
                                    showcaseView.setContentText("Number of cycles you need to do in routine.");
                                    showcaseView.setButtonText("Next");
                                    break;
                                case 5:
                                    showcaseView.setShowcase(new ViewTarget(start),true);
                                    showcaseView.setContentTitle("Start");
                                    showcaseView.setContentText("Click here to start the routine.");
                                    showcaseView.setButtonText("Got it!");
                                    break;
                                case 6:
                                    showcaseView.hide();
                                    break;
                            }
                            index++;
                        }
                    })
                    .withHoloShowcase()
                    .build();
            showcaseView.setButtonText("Next");
            getSharedPreferences("PREFERENCE",MODE_PRIVATE).edit().putBoolean("FirstRun",false).commit();
        }
    }

    private void init() {
        radioGroup = (RadioGroup)findViewById(R.id.radioBtnGroup);
        btn1 = (RadioButton)findViewById(R.id.radioBtn1);
        btn2 = (RadioButton)findViewById(R.id.radioBtn2);
        btn3 = (RadioButton)findViewById(R.id.radioBtn3);
        btn4 = (RadioButton)findViewById(R.id.radioBtn4);
        btn5 = (RadioButton)findViewById(R.id.radioBtn5);
        btn6 = (RadioButton)findViewById(R.id.radioBtn6);
        name = (TextView)findViewById(R.id.nameRoutine);
        exe1 = (TextView)findViewById(R.id.exe1);
        exe2 = (TextView)findViewById(R.id.exe2);
        exe3 = (TextView)findViewById(R.id.exe3);
        exe4 = (TextView)findViewById(R.id.exe4);
        exe5 = (TextView)findViewById(R.id.exe5);
        exe6 = (TextView)findViewById(R.id.exe6);
        start = (ImageButton)findViewById(R.id.btnStartRoutine);
        view = (ImageButton)findViewById(R.id.btnViewRoutine);
        cycle = (TextView)findViewById(R.id.cycle);
    }
}
