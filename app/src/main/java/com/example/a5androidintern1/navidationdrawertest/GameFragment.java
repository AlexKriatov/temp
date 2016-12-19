package com.example.a5androidintern1.navidationdrawertest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.a5androidintern1.navidationdrawertest.MainActivity.packageName;

/**
 * Created by A5 Android Intern 1 on 19.12.2016.
 */

public class GameFragment extends Fragment implements View.OnClickListener{

    ImageButton btnPause;
    CustomImageButton btn1;
    CustomImageButton btn2;
    CustomImageButton btn3;
    CustomImageButton btn4;
    CustomImageButton btn5;
    CustomImageButton btn6;
    CustomImageButton btn7;
    CustomImageButton btn8;
    CustomImageButton btn9;
    ImageView ivMain;
    TextView tvTime;
    TextView tvCount;
    int randNumber;
    int time = 0;
    String imgPath;
    private Timer GameTimer;
    private GameTimerTask GameTimerTask;
    private Timer ImgTimer;
    private ImageTimerTask ImgTimerTask;
    public int pos;
    public int rPos;
    final Random random = new Random();
    public int count = 0;
    public int totalCount = 0;
    String result;
    Boolean isStopped = true;
    AlertDialog.Builder adb;
    private ArrayList<CustomImageButton> btnsList = new ArrayList<>();
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.game_fragment, null);
         btnPause = (ImageButton) v.findViewById(R.id.pauseBtn);
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).openDrawer();
                timersStop();
            }
        });
         btn1 = (CustomImageButton) v.findViewById(R.id.btn1);
         btn2 = (CustomImageButton) v.findViewById(R.id.btn2);
         btn3 = (CustomImageButton) v.findViewById(R.id.btn3);
         btn4 = (CustomImageButton) v.findViewById(R.id.btn4);
         btn5 = (CustomImageButton) v.findViewById(R.id.btn5);
         btn6 = (CustomImageButton) v.findViewById(R.id.btn6);
         btn7 = (CustomImageButton) v.findViewById(R.id.btn7);
         btn8 = (CustomImageButton) v.findViewById(R.id.btn8);
         btn9 = (CustomImageButton) v.findViewById(R.id.btn9);
        btnPause.setImageDrawable(getResources().getDrawable(R.drawable.pause));
        ivMain = (ImageView)v.findViewById(R.id.ivMain);
        tvTime = (TextView) v.findViewById(R.id.tvTime);
        tvCount = (TextView) v.findViewById(R.id.tvCount);
        ivMain = (ImageView) v.findViewById(R.id.ivMain);
        ivMain.setImageDrawable(getResources().getDrawable(R.drawable.img_3));
        fillBtnsList();
        setOnClickListener(btnsList);
        newGame();
        return v;
    }

    @Override
    public void onResume() {

        super.onResume();
        timersStart();

    }

    @Override
    public void onPause() {
        super.onPause();
        timersStop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                onBtnClick(btn1);
                break;
            case R.id.btn2:
                onBtnClick(btn2);
                break;
            case R.id.btn3:
                onBtnClick(btn3);
                break;
            case R.id.btn4:
                onBtnClick(btn4);
                break;
            case R.id.btn5:
                onBtnClick(btn5);
                break;
            case R.id.btn6:
                onBtnClick(btn6);
                break;
            case R.id.btn7:
                onBtnClick(btn7);
                break;
            case R.id.btn8:
                onBtnClick(btn8);
                break;
            case R.id.btn9:
                onBtnClick(btn9);
                break;

        }
        tvCount.setText("Count: " + count);
        if (btn1.isClicked() && btn2.isClicked() && btn3.isClicked() &&
                btn4.isClicked() && btn5.isClicked() && btn6.isClicked() &&
                btn7.isClicked() && btn8.isClicked() && btn9.isClicked()) {
            if (count >= 3) {
                resetButtons(btnsList);
                gameStart();
            } else {

                timersStop();
                result = "You scored " + totalCount + " hits in " + time + " seconds. Try again?";



            }
        }
    }
    public void onBtnClick(CustomImageButton btn) {
        if (!btn.isClicked()) {
            btn.setClicked(true);
            if (btn.getDrawable().getConstantState().equals(ivMain.getDrawable().getConstantState())) {
                btn.setBackground(getResources().getDrawable(R.drawable.background_button_true));
                count++;
                totalCount++;
            } else
                btn.setBackground(getResources().getDrawable(R.drawable.background_button_false));
        }

    }
    public void setOnClickListener(ArrayList<CustomImageButton> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setOnClickListener(this);
        }

    }
    public void fillBtnsList() {
        btnsList.add(btn1);
        btnsList.add(btn2);
        btnsList.add(btn3);
        btnsList.add(btn4);
        btnsList.add(btn5);
        btnsList.add(btn6);
        btnsList.add(btn7);
        btnsList.add(btn8);
        btnsList.add(btn9);

    }
    public void resetButtons(ArrayList<CustomImageButton> list) {
        for (int i = 0; i < list.size(); i++) {
            btnsList.get(i).setClicked(false);
            btnsList.get(i).setBackground(getResources().getDrawable(R.drawable.background_button));
        }
        count = 0;
    }
    class ImageTimerTask extends TimerTask {

        @Override
        public void run() {
            rPos = 0;
            for (int i = 0; i < btnsList.size(); i++) {
                if (!btnsList.get(i).isClicked()) {
                    rPos = i;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setRandomImage(btnsList.get(rPos));
                        }
                    });



                    try {
                        java.util.concurrent.TimeUnit.MICROSECONDS.sleep(60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }


        }
    }
    public void setRandomImage(ImageView v) {
        randNumber = random.nextInt(8) + 1;
        imgPath = "drawable/img_" + randNumber;
        int imgResource = getResources().getIdentifier(imgPath, null, packageName);
        v.setImageDrawable(getResources().getDrawable(imgResource));
    }
    public void gameStart() {
        setRandomImage(ivMain);
        for (pos = 0; pos < btnsList.size(); pos++) {
            setRandomImage(btnsList.get(pos));

        }

        count = 0;
        tvCount.setText("Count: " + count);

    }
    class GameTimerTask extends TimerTask {

        @Override
        public void run() {

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvTime.setText("Time: " + time);
                }
            });


            time++;

        }

    }
    public void newGame() {
        time = 0;
        totalCount = 0;
        resetButtons(btnsList);
        timersStart();
        gameStart();


    }
public void timersStart(){
    if(isStopped) {
        GameTimer = new Timer();
        ImgTimer = new Timer();
        GameTimerTask = new GameTimerTask();
        ImgTimerTask = new ImageTimerTask();
        GameTimer.schedule(GameTimerTask, 1000, 1000);
        ImgTimer.schedule(ImgTimerTask, 0, 500);
        isStopped=!isStopped;
    }
}
    public void timersStop() {
        if (!isStopped){
            GameTimer.cancel();
            ImgTimer.cancel();
            isStopped=!isStopped;
    }
    }

}
