package daovudat.finalproject.routine;

/**
 * Created by Dao Vu Dat on 9/8/2016.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vstechlab.easyfonts.EasyFonts;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import daovudat.finalproject.AboutApp;
import daovudat.finalproject.R;

/**
 * Created by Dao Vu Dat on 9/8/2016.
 */
public class DetailAvenger extends AppCompatActivity{
    private TextView name,titleCurExe, titleNexExe,cycle,CurExe,NexExe,reps,startToNext;
    private ImageButton btnNext,btnView,btnSound;
    private MediaPlayer mp;
    private int mark=1, count=1,count1 = 1;
    private Boolean unlock = true;
    private static final String FORMAT = "%02d:%02d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);

        // ...but notify us that it happened.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);
        setContentView(R.layout.activity_detail_routine);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);

        init();
        setup();

        mp = MediaPlayer.create(this,R.raw.tremo);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mark != 4) {
                    if (count == 1 && unlock == true) {
                        startToNext.setText("Next");
                        mp.start();
                        count++;
                    }
                    else if (count == 13 && unlock == true)
                    {
                        count = 1;
                        cycle.setText("Cycle "+mark+"/3");
                        CurExe.setText("Wide Pull Ups");
                        NexExe.setText("Rest");
                        reps.setText("3 reps");
                        count++;
                    }

                    else if (count == 2 & unlock == true)
                    {
                        count1++;
                        CurExe.setText("Rest");
                        NexExe.setText("Dips");
                        new CountDownTimer(3000, 500) {
                            public void onTick(long millisUntilFinished) {
                                unlock = false;
                                reps.setText("" + String.format(FORMAT,
                                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                            }

                            public void onFinish() {unlock = true;
                                reps.setText("00:00");
                                count++;

                            }
                        }.start();
                    }


                    else if (count == 3 && unlock == true)
                    {
                        CurExe.setText("Dips");
                        NexExe.setText("Rest");
                        reps.setText("4 reps");
                        count++;
                    }

                    else if (count == 4 & unlock == true)
                    {
                        count1++;
                        CurExe.setText("Rest");
                        NexExe.setText("Pistol Squats");
                        new CountDownTimer(3000, 500) {
                            public void onTick(long millisUntilFinished) {
                                unlock = false;
                                reps.setText("" + String.format(FORMAT,
                                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                            }

                            public void onFinish() {unlock = true;
                                reps.setText("00:00");
                                count++;

                            }
                        }.start();
                    }

                    else if (count == 5&& unlock == true)
                    {
                        CurExe.setText("Pistol Squats");
                        NexExe.setText("Rest");
                        reps.setText("1 reps");
                        count++;
                    }

                    else if (count == 6 && unlock == true)
                    {
                        count1++;
                        CurExe.setText("Rest");
                        NexExe.setText("Clap Push Ups");
                        new CountDownTimer(3000, 500) {
                            public void onTick(long millisUntilFinished) {
                                unlock = false;
                                reps.setText("" + String.format(FORMAT,
                                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                            }

                            public void onFinish() {  unlock = true;
                                reps.setText("00:00");
                                count++;

                            }
                        }.start();
                    }

                    else if (count == 7&& unlock == true)
                    {
                        CurExe.setText("Clap Push Ups");
                        NexExe.setText("Rest");
                        reps.setText("1 reps");
                        count++;
                    }
                    else if (count == 8 && unlock == true)
                    {
                        count1++;
                        CurExe.setText("Rest");
                        NexExe.setText("L-sit Chip Ups");
                        new CountDownTimer(3000, 500) {
                            public void onTick(long millisUntilFinished) {
                                unlock = false;
                                reps.setText("" + String.format(FORMAT,
                                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                            }

                            public void onFinish() {unlock = true;
                                reps.setText("00:00");
                                count++;

                            }
                        }.start();
                    }
                    else if (count == 9&& unlock == true)
                    {
                        CurExe.setText("L-sit Chip Ups");
                        NexExe.setText("Rest");
                        reps.setText("1 reps");
                        count++;

                    }
                    else if (count == 10 && unlock == true)
                    {
                        count1++;
                        CurExe.setText("Rest");
                        NexExe.setText("Calf Raises");
                        new CountDownTimer(3000, 500) {
                            public void onTick(long millisUntilFinished) {
                                unlock = false;
                                reps.setText("" + String.format(FORMAT,
                                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                            }

                            public void onFinish() {unlock = true;
                                reps.setText("00:00");
                                count++;

                            }
                        }.start();
                    }
                    else if (count == 11 && unlock == true)
                    {
                        CurExe.setText("Calf Raises");
                        NexExe.setText("Rest");
                        reps.setText("10 reps");
                        count++;
                    }

                    else if (count == 12 && mark!= 3 && unlock == true)
                    {
                        count1 = 1;
                        CurExe.setText("Rest");
                        NexExe.setText("Wide Pull Ups");
                        new CountDownTimer(9000, 500) {
                            public void onTick(long millisUntilFinished) {
                                unlock = false;
                                reps.setText("" + String.format(FORMAT,
                                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                            }

                            public void onFinish() {
                                unlock = true;
                                reps.setText("00:00");
                                count++;
                                mark++;

                            }
                        }.start();
                    }
                    else if (count == 12 && mark== 3 && unlock == true)
                    {
                        CurExe.setText("Rest");
                        NexExe.setText("Finish");
                        startToNext.setText("Finish");
                        new CountDownTimer(9000, 500) {
                            public void onTick(long millisUntilFinished) {
                                unlock = false;
                                reps.setText("" + String.format(FORMAT,
                                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                            }

                            public void onFinish() {
                                unlock = true;
                                reps.setText("00:00");
                                count++;
                                mark++;
                            }
                        }.start();
                    }

                }
                // If cycle = 4 ===> get a prize
                else if (mark == 4)
                {
                    sth();
                }

            }
        });

        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying())
                {
                    btnSound.setImageResource(R.drawable.off);
                    mp.pause();
                }
                else
                {
                    btnSound.setImageResource(R.drawable.on);
                    mp.start();
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count1 == 1)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=ona7yPgrjvc")));
                }
                else if (count1 == 2)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=hYHSZA9TS5s")));
                }
                else if (count1 == 3)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=39nBwk5tdU8")));
                }
                else if (count1 == 4)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=R_4TcbkfG7g")));
                }
                else if (count1 == 5)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=7Wd45lzZhQk")));
                }
                else if (count1 == 6)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=GVWK8LBsEZU")));
                }
            }
        });

    }

    private void setup() {
        name.setTypeface(EasyFonts.captureIt(this));
        titleNexExe.setTypeface(EasyFonts.tangerineBold(this));
        titleCurExe.setTypeface(EasyFonts.tangerineBold(this));

        name.setText("Avenger");
        CurExe.setText("Wide Pull Ups");
        NexExe.setText("Rest");
        cycle.setText("Cycle 1/3");
        reps.setText("3 reps");
    }

    private void init() {
        name = (TextView)findViewById(R.id.nameRoutineSlicer);
        titleCurExe = (TextView)findViewById(R.id.currentExeTitle);
        titleNexExe = (TextView)findViewById(R.id.nextExeTitle);
        cycle = (TextView)findViewById(R.id.cycleDetail);
        NexExe = (TextView)findViewById(R.id.nextExe);
        CurExe = (TextView)findViewById(R.id.currentExe);
        reps = (TextView)findViewById(R.id.reps);
        startToNext = (TextView)findViewById(R.id.startToNext);

        btnNext = (ImageButton)findViewById(R.id.btnNextRoutine);
        btnView = (ImageButton)findViewById(R.id.btnViewRoutineInDetail);
        btnSound = (ImageButton)findViewById(R.id.volume);
    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this,R.style.MyDialogTheme)
                .setIcon(R.drawable.exit)
                .setTitle("Back")
                .setCancelable(false)
                .setMessage("Are you sure you want to back? If yes, you will lose your current result.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
                        mp.stop();
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
        // super.onBackPressed();
    }

    public void sth()
    {
        new AlertDialog.Builder(this,R.style.MyDialogTheme)
                .setIcon(R.drawable.exit)
                .setTitle("Congratulations")
                .setCancelable(false)
                .setMessage("You have finished the AVENGER. You got 20 point and 10 exp.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Add point
                        int point = loadPoint();
                        point += 20;
                        String temp = String.valueOf(point);
                        FileOutputStream wfile;
                        try {
                            wfile = openFileOutput("stored_point", Context.MODE_PRIVATE);
                            wfile.write(temp.getBytes());
                            wfile.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        // Add exp
                        int exp = loadExp();
                        exp += 10;
                        String temp1 = String.valueOf(exp);
                        FileOutputStream wfile1;
                        try {
                            wfile1 = openFileOutput("stored_exp", Context.MODE_PRIVATE);
                            wfile1.write(temp1.getBytes());
                            wfile1.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent (getApplicationContext(),AboutApp.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }

                })
                .show();
    }

    private int loadExp() {
        int exp;
        String temp = null;
        try {
            FileInputStream rfile = openFileInput("stored_exp");
            InputStreamReader inputReader = new InputStreamReader(rfile);
            BufferedReader buffer = new BufferedReader(inputReader);
            StringBuffer stringBuffer  = new StringBuffer();
            temp = buffer.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exp = Integer.parseInt(temp);
    }

    public int loadPoint() {
        int point;
        String temp = null;
        try {
            FileInputStream rfile = openFileInput("stored_point");
            InputStreamReader inputReader = new InputStreamReader(rfile);
            BufferedReader buffer = new BufferedReader(inputReader);
            StringBuffer stringBuffer  = new StringBuffer();
            temp = buffer.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return point = Integer.parseInt(temp);
    }
}
