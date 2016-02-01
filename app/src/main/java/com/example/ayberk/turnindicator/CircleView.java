package com.example.ayberk.turnindicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by ayberk on 10/10/15.
 */
public class CircleView extends View {

    private float x;
    private float y;
    private float r;
    private String s;
    private Paint paintGraph;
    private Paint paintBackground;
    Context context;


    public CircleView(Context context, float a, float b, float c,String s){
        super(context);
        init(context);
        this.context = context;
        x = a;
        y = b;
        r = c;
        this.s = s;
        /*Canvas canvas = new Canvas();
        this.onDraw(canvas);*/


    }

    private void init(Context c){
        int red = (int) (Math.random()*256);
        int green = (int) (Math.random()*256);
        int blue = (int) (Math.random()*256);

        paintBackground = new Paint();
        paintBackground.setStyle(Paint.Style.FILL);
        paintBackground.setColor(Color.BLACK);
        paintBackground.setTextSize(48);

        paintGraph = new Paint();
        paintGraph.setStyle(Paint.Style.STROKE);

        paintGraph.setColor(Color.rgb(red, green, blue));
        paintGraph.setStyle(Paint.Style.FILL);
        paintGraph.setStrokeWidth(3);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawCircle(x, y, r, paintGraph);

        canvas.drawText(s,x,y,paintBackground);
    }


}
