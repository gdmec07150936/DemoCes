package com.example.tjh.democes;

import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private GridLayout gridLayout;
    private LinearLayout linearLayout;
    private int mEtWidth, mEtHeight;
    private int row = 5;
    private int columen = 6;
    private int width = 0;
    private ArrayList<TextView> mTextViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = this.findViewById(R.id.gridLayout);
        linearLayout = this.findViewById(R.id.linearLayout);

        gridLayout.setRowCount(row);
        gridLayout.setColumnCount(columen);
        mTextViews = new ArrayList<>();
        linearLayout.post(new Runnable() {
            @Override
            public void run() {
                getGriderViewSize();
                addEts();
            }
        });
    }


    public void getGriderViewSize(){
        mEtWidth = linearLayout.getWidth() / columen;
        mEtHeight = linearLayout.getHeight()/row;
        if (mEtHeight < mEtWidth && mEtHeight != 0){
            width = mEtHeight;
        }else{
            width = mEtWidth;
        }
    }

    private void addEts() {
        String data[][] = new String[row][columen];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < columen; j++) {
                data[i][j] = (i*columen)+j+"";
                TextView editText = new TextView(this);
                editText.setText(data[i][j]+"");
                editText.setGravity(Gravity.CENTER);
                gridLayout.addView(editText, mEtWidth, mEtWidth);
                mTextViews.add(editText);
            }
        }


        String[][] data2= reverse(data);
        gridLayout.setRowCount(data2.length);
        gridLayout.setColumnCount(data2[0].length);
        getGriderViewSize();
        for (int i = 0; i < data2.length; i++) {
            for (int j = 0; j < data2[0].length; j++) {
                TextView textView = mTextViews.get((i*data2[0].length)+j);
                textView.setText(data2[i][j]);
                textView.setBackgroundColor(Color.WHITE);
                ViewGroup.LayoutParams lp = textView.getLayoutParams();
                lp.width = width;
                lp.height = width;
                textView.setLayoutParams(lp);

            }
        }
    }

    public static String[][] reverse(String[][] tem){
        String[][] data1 = new String[tem[0].length][tem.length];
        for (int i = 0; i < tem.length; i++) {
            for (int j = 0; j < tem[0].length; j++) {
                data1[j][i] = tem[i][j] ;
            }
        }
        return data1;
    }
}
