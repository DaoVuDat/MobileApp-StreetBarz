package daovudat.finalproject.routine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.vstechlab.easyfonts.EasyFonts;

import daovudat.finalproject.R;

/**
 * Created by Dao Vu Dat on 9/8/2016.
 */
public class MissionFit extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton btn1, btn2, btn3 , btn4, btn5, btn6;
    private TextView name,exe1, exe2, exe3,exe4, exe5, exe6,cycle;
    private ImageButton start,view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);

        init();
        setDetail();

        // Do function
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if(btn1.getId() == selectedId)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Qi3watD4vSI")));
                }
                else if (btn2.getId() == selectedId)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dNaNk02kH4g")));
                }
                else if (btn3.getId() == selectedId)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=ZHoBuAg5z5w")));
                }
                else if (btn4.getId() == selectedId)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=4Azm8-c_k4Y")));
                }
                else if (btn5.getId() == selectedId)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=qHBTBRYmoV4")));
                }
                else if (btn6.getId() == selectedId)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=MRhppTuCAYo")));
                }
            }
        });


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DetailMissionFit.class);
                startActivity(intent);
            }
        });

    }

    private void setDetail() {
        name.setText("Mission Fit");
        name.setTypeface(EasyFonts.captureIt(this));

        exe1.setText("Push Ups");
        exe2.setText("Crunches");
        exe3.setText("Squats");
        exe4.setText("Side Plank Right");
        exe5.setText("Side Plank Left");
        exe6.setText("Squat Jumps");

        cycle.setText("3 cycles");
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
