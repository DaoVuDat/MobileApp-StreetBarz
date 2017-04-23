package daovudat.finalproject.fragment;

/**
 * Created by Dao Vu Dat on 8/30/2016.
 */
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import daovudat.finalproject.CustomAdapter;
import daovudat.finalproject.Nutrition;
import daovudat.finalproject.R;
import daovudat.finalproject.routine.MissionFit;
import daovudat.finalproject.routine.PushUp;
import daovudat.finalproject.routine.SimpleKill;
import daovudat.finalproject.routine.Slicer;

public class Basic extends Fragment {
    private View view;
    private ShowcaseView showcaseView;
    private GridView gridview;
    int index = 0;
    private TextView level,point;
    private int TotalPoint_Int,Exp_Int;
    private Boolean checkMissionFit,
                    checkSimpleKill,
                    checkPushup;

    private SharedPreferences pre;
    public Basic() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_basic, container, false);

        init();
        List<ItemObject> allItems = getAllItemObject();
        CustomAdapter customAdapter = new CustomAdapter(getContext(), allItems);
        gridview.setAdapter(customAdapter);


        firstTimeRun();
        getPoint();
        updatePoint();


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                {
                    Intent intent = new Intent(getContext(), Slicer.class);
                    startActivity(intent);
                }
                else if (position == 1)
                {
                    pre = getActivity().getSharedPreferences("checkMissionFit", Context.MODE_PRIVATE);
                    checkMissionFit = pre.getBoolean("checkMissionFitBool",true);
                    if(checkMissionFit) {
                        if (Exp_Int >= 2 && TotalPoint_Int >= 2) {
                            new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme)
                                    .setIcon(R.drawable.exit)
                                    .setTitle("Unlock Routine")
                                    .setCancelable(false)
                                    .setMessage("Mission Fit costs 2 points. Do you want to unlock this routine?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            TotalPoint_Int -= 2;
                                            String temp = String.valueOf(TotalPoint_Int);
                                            FileOutputStream wfile;
                                            try {
                                                wfile = getActivity().openFileOutput("stored_point", Context.MODE_PRIVATE);
                                                wfile.write(temp.getBytes());
                                                wfile.close();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            pre.edit().putBoolean("checkMissionFitBool",false).commit();
                                            Intent intent = new Intent(getContext(), MissionFit.class);
                                            startActivity(intent);
                                            updatePoint();
                                        }

                                    })
                                    .setNegativeButton("No", null)
                                    .show();
                        } else {
                            new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme)
                                    .setIcon(R.drawable.exit)
                                    .setTitle("Work harder")
                                    .setCancelable(false)
                                    .setMessage("You need to work harder to get some points and level to unlock this routine? - 2 points and Level 1")
                                    .setNegativeButton("OK", null)
                                    .show();
                        }
                    }
                    else
                    {
                        Intent intent = new Intent(getContext(), MissionFit.class);
                        startActivity(intent);
                    }
                }
                else if (position == 2)
                {
                    pre = getActivity().getSharedPreferences("checkSimpleKill", Context.MODE_PRIVATE);
                    checkSimpleKill = pre.getBoolean("checkSimpleKillBool",true);
                    if(checkSimpleKill) {
                        if (Exp_Int >= 2 && TotalPoint_Int >= 2) {
                            new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme)
                                    .setIcon(R.drawable.exit)
                                    .setTitle("Unlock Routine")
                                    .setCancelable(false)
                                    .setMessage("Simple Kill costs 2 points. Do you want to unlock this routine?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            TotalPoint_Int -= 2;
                                            String temp = String.valueOf(TotalPoint_Int);
                                            FileOutputStream wfile;
                                            try {
                                                wfile = getActivity().openFileOutput("stored_point", Context.MODE_PRIVATE);
                                                wfile.write(temp.getBytes());
                                                wfile.close();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            pre.edit().putBoolean("checkSimpleKillBool",false).commit();
                                            Intent intent = new Intent(getContext(), SimpleKill.class);
                                            startActivity(intent);
                                            updatePoint();
                                        }

                                    })
                                    .setNegativeButton("No", null)
                                    .show();
                        } else {
                            new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme)
                                    .setIcon(R.drawable.exit)
                                    .setTitle("Work harder")
                                    .setCancelable(false)
                                    .setMessage("You need to work harder to get some points and level to unlock this routine? - 2 points and Level 1")
                                    .setNegativeButton("OK", null)
                                    .show();
                        }
                    }
                    else
                    {
                        Intent intent = new Intent(getContext(), SimpleKill.class);
                        startActivity(intent);
                    }
                }
                else if (position == 3)
                {
                    pre = getActivity().getSharedPreferences("checkPushUp", Context.MODE_PRIVATE);
                    checkPushup = pre.getBoolean("checkPushUpBool",true);
                    if(checkPushup) {
                        if (Exp_Int >= 3 && TotalPoint_Int >= 3) {
                            new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme)
                                    .setIcon(R.drawable.exit)
                                    .setTitle("Unlock Routine")
                                    .setCancelable(false)
                                    .setMessage("Push Up costs 3 points. Do you want to unlock this routine?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            TotalPoint_Int -= 3;
                                            String temp = String.valueOf(TotalPoint_Int);
                                            FileOutputStream wfile;
                                            try {
                                                wfile = getActivity().openFileOutput("stored_point", Context.MODE_PRIVATE);
                                                wfile.write(temp.getBytes());
                                                wfile.close();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            pre.edit().putBoolean("checkPushUpBool",false).commit();
                                            Intent intent = new Intent(getContext(), PushUp.class);
                                            startActivity(intent);
                                            updatePoint();
                                        }

                                    })
                                    .setNegativeButton("No", null)
                                    .show();
                        } else {
                            new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme)
                                    .setIcon(R.drawable.exit)
                                    .setTitle("Work harder")
                                    .setCancelable(false)
                                    .setMessage("You need to work harder to get some points and level to unlock this routine? - 3 points and Level 2")
                                    .setNegativeButton("OK", null)
                                    .show();
                        }
                    }
                    else
                    {
                        Intent intent = new Intent(getContext(), PushUp.class);
                        startActivity(intent);
                    }
                }
                else if (position == 4)
                {
                    TotalPoint_Int += 10;
                    String temp = String.valueOf(TotalPoint_Int);
                    FileOutputStream wfile;
                    try {
                        wfile = getActivity().openFileOutput("stored_point", Context.MODE_PRIVATE);
                        wfile.write(temp.getBytes());
                        wfile.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Exp_Int += 10;
                    String temp1 = String.valueOf(Exp_Int);
                    FileOutputStream wfile1;
                    try {
                        wfile1 = getActivity().openFileOutput("stored_exp", Context.MODE_PRIVATE);
                        wfile1.write(temp1.getBytes());
                        wfile1.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getContext(),"Added",Toast.LENGTH_SHORT).show();
                    updatePoint();
                }
            }
        });
        return view;


    }
    private void updatePoint() {
        point.setText("Points: "+TotalPoint_Int);
        if (Exp_Int>2 && Exp_Int <=100)
        {
            level.setText("Level 2");
        }
        else if (Exp_Int > 100)
        {
            level.setText("Level 3");
        }
        else if (Exp_Int <= 2)
        {
            level.setText("Level 1");
        }
    }

    private void firstTimeRun() {
        SharedPreferences preferences = this.getActivity().getSharedPreferences("preference", Context.MODE_PRIVATE);
        Boolean isFirstRun = preferences.getBoolean("isFirstRunBasic",true);
        if(isFirstRun)
        {
            showcaseView = new ShowcaseView.Builder(getActivity())
                    .setTarget(Target.NONE)
                    .setStyle(R.style.CustomShowcaseTheme2)
                    .setContentTitle("Exercises")
                    .setContentText("This is the grid view exercises. Click 'Next' to next the tutorial.")
                    .hideOnTouchOutside()
                    .blockAllTouches()
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (index){
                                case 0:
                                    showcaseView.setShowcase(new ViewTarget(level),true);
                                    showcaseView.setContentTitle("Level");
                                    showcaseView.setContentText("This is your current level. You need to get more level to unlock the exercises.");
                                    showcaseView.setButtonText("Next");
                                    break;
                                case 1:
                                    showcaseView.setShowcase(new ViewTarget(point),true);
                                    showcaseView.setContentTitle("Point");
                                    showcaseView.setContentText("This is your current point. The point will unlock the exercises.");
                                    showcaseView.setButtonText("Next");
                                    break;
                                case 2:
                                    showcaseView.setShowcase(new ViewTarget(gridview),true);
                                    showcaseView.setContentTitle("Grid view exercises");
                                    showcaseView.setContentText("Chooses one routine and do it.");
                                    showcaseView.setButtonText("Got it");
                                    break;
                                case 3:
                                    showcaseView.hide();
                                    break;
                            }
                            index++;
                        }
                    })
                    .withHoloShowcase()
                    .build();
            showcaseView.setButtonText("Next");
            preferences.edit().putBoolean("isFirstRunBasic",false).commit();
        }
    }

    private void init() {
        gridview = (GridView)view.findViewById(R.id.gridview);
        level = (TextView)view.findViewById(R.id.basicLevel);
        point = (TextView)view.findViewById(R.id.basicPoint);
    }

    private List<ItemObject> getAllItemObject(){
        List<ItemObject> items = new ArrayList<>();
        items.add(new ItemObject(R.drawable.slicer,"Slicer", "3 cycles - 1p"));
        items.add(new ItemObject(R.drawable.missionfit,"Mission Fit", "3 cycles - 1p"));
        items.add(new ItemObject(R.drawable.simplekill,"Simple Kill", "4 cycles - 2ps"));
        items.add(new ItemObject(R.drawable.pushupsroutine,"Push Ups", "3 cycles - 2ps"));
        items.add(new ItemObject(R.drawable.users,"Demo Function","Add 10 ps/exp"));
        return items;
    }

    public void getPoint() {
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