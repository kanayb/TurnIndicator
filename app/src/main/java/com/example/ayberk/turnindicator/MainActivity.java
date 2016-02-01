package com.example.ayberk.turnindicator;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    RelativeLayout layout;
    TextView text;
    Button resetButton;
    int xAdjust;
    int yAdjust;
    int counter;
    boolean setUp;
    private ArrayList<CircleView> dots;
    int turnNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (RelativeLayout) findViewById(R.id.rlayout);
        text = (TextView) findViewById(R.id.text);
        xAdjust = 13;
        yAdjust = 95;
        counter = 0;
        setUp = false;
        turnNumber = 0;
        dots = new ArrayList<>();
        resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int q = 0; q < dots.size(); q++){
                    layout.removeView(dots.get(q));
                }
                setUp = false;
                dots.clear();
                counter = 0;
                turnNumber = 0;
                layout.addView(text);
            }
        });
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        if(setUp == false) {
            int xAdjust2 = (int) pxFromDp(this, (float) xAdjust);
            int yAdjust2 = (int) pxFromDp(this, (float) yAdjust);

            if ((event.getActionMasked() == MotionEvent.ACTION_DOWN)) {
                CircleView circle;
                int x = (int) event.getX();
                int y = (int) event.getY();
                int r = 100;
                counter++;
                circle = new CircleView(this, x - xAdjust2, y - yAdjust2, 100, "" + counter);
                dots.add(circle);
                layout.addView(circle);
                layout.removeView(text);
            } else if (event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN) {
                CircleView circle;
                int x = (int) event.getX(counter);
                int y = (int) event.getY(counter);
                int r = 100;
                counter++;
                circle = new CircleView(this, x - xAdjust2, y - yAdjust2, 100, "" + counter);
                dots.add(circle);
                layout.addView(circle);
            } else if (event.getActionMasked() == MotionEvent.ACTION_POINTER_UP) {
                counter--;
            } else if (event.getActionMasked() == MotionEvent.ACTION_UP) {
                counter = 0;
                setUp = true;
                for(int q = 1; q < dots.size(); q++){
                    layout.removeView(dots.get(q));
                }
            }
        }
//        if(event.getPointerCount() == 0){
//            setUp = true;
//            for(int x = 1; x < dots.size(); x++){
//                layout.removeView(dots.get(x));
//            }
//        }

        if(setUp == true && event.getAction() == MotionEvent.ACTION_DOWN){
            layout.removeView(dots.get(turnNumber % dots.size()));
            layout.addView(dots.get(++turnNumber % dots.size()));
        }
        return true;
    }


    public static float pxFromDp(final Context context, final float dp) {
        float pixelsPerOneDp = context.getResources().getDisplayMetrics().density;
        return dp * pixelsPerOneDp;
    }
}
