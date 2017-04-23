package daovudat.finalproject;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ActionViewTarget;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainContain extends Fragment {

    private ImageButton exercises, nutrition;
    private View view;
    private ImageView imageView;
    private ShowcaseView showcaseView;
    private TextView level, point;
    private ProgressBar exp;
    int index = 0;
    private int TotalPoint_Int,Exp_Int;
    private String TotalPoint_Str,Exp_Str;
    public MainContain() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Exercises");
         view = inflater.inflate(R.layout.fragment_main_contain,
                container, false);
        view.setBackgroundColor(0xFFFFFF);

        init();
        firstTimeRun();

        Picasso.with(getContext())
                .load(R.drawable.lg1)
                .resize(590,810)
                .into(imageView);


        nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Nutrition.class);
                startActivity(intent);
            }
        });

        exercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(getContext(), Exercises.class);
                startActivity(intent);
                getActivity().finish();
            }
      });

        updatePoint();
        updateExp();

        // Inflate the layout for this fragment
        return view;
    }

    private void updateExp() {

        if (Exp_Int>2 && Exp_Int <=100)
        {
            level.setText("Level 2");

            float progress;
            progress = (((float)Exp_Int-2)/98)*100;
            exp.setProgress((int)progress);



        }
        else if (Exp_Int > 100)
        {
                level.setText("Level 3");
                    float progress;
                    progress = ((float)Exp_Int-100);
                    exp.setProgress((int)progress);
        }
        else if (Exp_Int <= 2)
        {
            level.setText("Level 1");
            float progress;
            progress = (((float)Exp_Int)/2)*100;
            exp.setProgress((int)Math.round(progress));
        }

    }

    private void firstTimeRun() {
        SharedPreferences preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        Boolean isFirstRun = preferences.getBoolean("isFirstRun",true);
        if(isFirstRun)
        {
               showcaseView = new ShowcaseView.Builder(getActivity())
                       .setTarget(Target.NONE)
                       .setStyle(R.style.CustomShowcaseTheme2)
                       .setContentTitle("Tutorial!")
                       .setContentText("This is the first time you use the application. Click 'Next' to next the tutorial.")
                       .hideOnTouchOutside()
                       .blockAllTouches()
                       .setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               switch (index){
                                   case 0:
                                       showcaseView.setShowcase(new ViewTarget(exercises),true);
                                       showcaseView.setContentTitle("Exercises");
                                       showcaseView.setContentText("This will show all the exercises.");
                                       showcaseView.setButtonText("Next");
                                       break;
                                   case 1:
                                       showcaseView.setShowcase(new ViewTarget(nutrition),true);
                                       showcaseView.setContentTitle("Nutrition");
                                       showcaseView.setContentText("This will show all the nutrition, plan and meal.");
                                       showcaseView.setButtonText("Next");
                                       break;
                                   case 2:
                                       showcaseView.setShowcase(new ViewTarget(level),true);
                                       showcaseView.setContentTitle("Level");
                                       showcaseView.setContentText("This is your current level. You need to get more level to unlock the exercises.");
                                       showcaseView.setButtonText("Next");
                                       break;
                                   case 3:
                                       showcaseView.setShowcase(new ViewTarget(exp),true);
                                       showcaseView.setContentTitle("Experience");
                                       showcaseView.setContentText("This is your experience bar.");
                                       showcaseView.setButtonText("Next");
                                       break;
                                   case 4:
                                       showcaseView.setShowcase(new ViewTarget(point),true);
                                       showcaseView.setContentTitle("Point");
                                       showcaseView.setContentText("This is your current point. The point will unlock the exercises.");
                                       showcaseView.setButtonText("Got it");
                                       break;
                                   case 5:
                                       showcaseView.hide();
                                       break;
                               }
                               index++;
                           }
                       })
                       .withHoloShowcase()
                       .build();
               showcaseView.setButtonText("Next");

            FileOutputStream wfile,wfile1;
            try {
                wfile = getActivity().openFileOutput("stored_point", Context.MODE_APPEND);
                wfile1 = getActivity().openFileOutput("stored_exp",Context.MODE_APPEND);
                wfile1.write("0".getBytes());
                wfile.write("0".getBytes());
                wfile1.close();
                wfile.close();
                level.setText("Level 1");
                TotalPoint_Int = 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
            preferences.edit().putBoolean("isFirstRun",false).commit();

        }
        else if (isFirstRun == false) {
            String temp = null;
            try {
                FileInputStream rfile = getActivity().openFileInput("stored_point");
                InputStreamReader inputReader = new InputStreamReader(rfile);
                BufferedReader buffer = new BufferedReader(inputReader);
                StringBuffer stringBuffer  = new StringBuffer();
                temp = buffer.readLine();
                TotalPoint_Int = Integer.parseInt(temp);
            } catch (IOException e) {
                e.printStackTrace();

            }


            String temp1 = null;
            try {
                FileInputStream rfile1 = getActivity().openFileInput("stored_exp");
                InputStreamReader inputReader1 = new InputStreamReader(rfile1);
                BufferedReader buffer1 = new BufferedReader(inputReader1);
                StringBuffer stringBuffer1  = new StringBuffer();
                temp1 = buffer1.readLine();
                Exp_Int = Integer.parseInt(temp1);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }


    private void init() {
        exercises = (ImageButton) view.findViewById(R.id.btnExercises);
        nutrition = (ImageButton) view.findViewById(R.id.btnNutrition);
        imageView = (ImageView)view.findViewById(R.id.ImageLoadscreen);
        level = (TextView)view.findViewById(R.id.level);
        exp = (ProgressBar)view.findViewById(R.id.exp);
        point = (TextView)view.findViewById(R.id.point);
    }


    private void updatePoint() {
        TotalPoint_Str = Integer.toString(TotalPoint_Int);
        point.setText("Points: "+ TotalPoint_Str);
    }



}
