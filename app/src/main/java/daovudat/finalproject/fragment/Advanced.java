package daovudat.finalproject.fragment;


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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import daovudat.finalproject.CustomAdapter;
import daovudat.finalproject.R;
import daovudat.finalproject.routine.Slicer;

/**
 * A simple {@link Fragment} subclass.
 */
public class Advanced extends Fragment {
    private View view;
    private GridView gridview;
    private TextView level,point;
    private int TotalPoint_Int,Exp_Int;
    private Boolean checkTexas,
            checkCantWalk,
            checkTurtleBack;

    private SharedPreferences pre;

    public Advanced() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_advanced, container, false);

        init();
        List<ItemObject> allItems = getAllItemObject();
        CustomAdapter customAdapter = new CustomAdapter(getContext(), allItems);
        gridview.setAdapter(customAdapter);

        getPoint();
        updatePoint();


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    pre = getActivity().getSharedPreferences("checkTexas", Context.MODE_PRIVATE);
                    checkTexas = pre.getBoolean("checkTexasBool", true);
                    if (checkTexas) {
                        if (Exp_Int >= 100 && TotalPoint_Int >= 300) {
                            new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme)
                                    .setIcon(R.drawable.exit)
                                    .setTitle("Unlock Routine")
                                    .setCancelable(false)
                                    .setMessage("Texas costs 300 points. Do you want to unlock this routine?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            TotalPoint_Int -= 300;
                                            String temp = String.valueOf(TotalPoint_Int);
                                            FileOutputStream wfile;
                                            try {
                                                wfile = getActivity().openFileOutput("stored_point", Context.MODE_PRIVATE);
                                                wfile.write(temp.getBytes());
                                                wfile.close();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            pre.edit().putBoolean("checkTexasBool", false).commit();
                                            Intent intent = new Intent(getContext(), Slicer.class);
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
                                    .setMessage("You need to work harder to get some points and level to unlock this routine? - 300 points and Level 3")
                                    .setNegativeButton("OK", null)
                                    .show();
                        }
                    } else {
                        Intent intent = new Intent(getContext(), Slicer.class);
                        startActivity(intent);
                    }
                } else if (position == 1) {
                    pre = getActivity().getSharedPreferences("checkCantWalk", Context.MODE_PRIVATE);
                    checkCantWalk = pre.getBoolean("checkCantWalkBool", true);
                    if (checkCantWalk) {
                        if (Exp_Int >= 100 && TotalPoint_Int >= 700) {
                            new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme)
                                    .setIcon(R.drawable.exit)
                                    .setTitle("Unlock Routine")
                                    .setCancelable(false)
                                    .setMessage("Can't Walk costs 700 points. Do you want to unlock this routine?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            TotalPoint_Int -= 700;
                                            String temp = String.valueOf(TotalPoint_Int);
                                            FileOutputStream wfile;
                                            try {
                                                wfile = getActivity().openFileOutput("stored_point", Context.MODE_PRIVATE);
                                                wfile.write(temp.getBytes());
                                                wfile.close();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            pre.edit().putBoolean("checkCantWalkBool", false).commit();
                                            Intent intent = new Intent(getContext(), Slicer.class);
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
                                    .setMessage("You need to work harder to get some points and level to unlock this routine? - 700 points and Level 3")
                                    .setNegativeButton("OK", null)
                                    .show();
                        }
                    } else {
                        Intent intent = new Intent(getContext(), Slicer.class);
                        startActivity(intent);
                    }
                }
                else if (position == 2) {
                    pre = getActivity().getSharedPreferences("checkTurtleBack", Context.MODE_PRIVATE);
                    checkTurtleBack = pre.getBoolean("checkTurtleBackBool", true);
                    if (checkTurtleBack) {
                        if (Exp_Int >= 100 && TotalPoint_Int >= 2000) {
                            new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme)
                                    .setIcon(R.drawable.exit)
                                    .setTitle("Unlock Routine")
                                    .setCancelable(false)
                                    .setMessage("Can't Walk costs 2000 points. Do you want to unlock this routine?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            TotalPoint_Int -= 300;
                                            String temp = String.valueOf(TotalPoint_Int);
                                            FileOutputStream wfile;
                                            try {
                                                wfile = getActivity().openFileOutput("stored_point", Context.MODE_PRIVATE);
                                                wfile.write(temp.getBytes());
                                                wfile.close();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            pre.edit().putBoolean("checkTurtleBackBool", false).commit();
                                            Intent intent = new Intent(getContext(), Slicer.class);
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
                                    .setMessage("You need to work harder to get some points and level to unlock this routine? - 2000 points and Level 3")
                                    .setNegativeButton("OK", null)
                                    .show();
                        }
                    } else {
                        Intent intent = new Intent(getContext(), Slicer.class);
                        startActivity(intent);
                    }
                }

            }
        });
        return view;
    }

    private List<ItemObject> getAllItemObject(){
        List<ItemObject> items = new ArrayList<>();
        items.add(new ItemObject(R.drawable.texas,"Texas", "5 cycles - 100ps"));
        items.add(new ItemObject(R.drawable.cantwalk,"Can't Walk", "4 cycles - 100ps"));
        items.add(new ItemObject(R.drawable.turtleback,"Turtle Back", "2/more cycles"));
        return items;
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
    private void init() {
        gridview = (GridView)view.findViewById(R.id.gridview3);
        level = (TextView)view.findViewById(R.id.advancedLevel);
        point = (TextView)view.findViewById(R.id.advancedPoint);
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
