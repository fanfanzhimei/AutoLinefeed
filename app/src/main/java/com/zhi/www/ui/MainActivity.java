package com.zhi.www.ui;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zhi.www.autolinefeed.R;

import java.util.Random;


//自动换行。在ListView中的每一行TextView中加入字符串并判断长度实现自动换行。考察窗口管理器，适配器，TextView和TextPaint
public class MainActivity extends Activity {
    private String string[] = { "美丽新世界", "这就是爱", "super star", "anyway", "开始懂了" };
    float text_size = 25.0f;
    private int windowWidth;
    private ListView listview;
    private MyAdapter adapter;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listview);
        init();
        button = (Button) findViewById(R.id.btn_refresh);

        button.setOnClickListener(new View.OnClickListener() {//刷新按钮
            @Override
            public void onClick(View arg0) {
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void init() {
        //No.1
        //开始写代码。要求实现获取屏幕宽度。
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        windowWidth = dm.widthPixels;
        //end_code
        adapter = new MyAdapter();
        listview.setAdapter(adapter);
    }
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {//设置显示10行数据
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout linearlayout = (LinearLayout) View.inflate(getApplicationContext(), R.layout.listview_item, null);
            Random random = new Random();
            int length = 0;
            for (int i = 0; i < 10; i++) {
                TextView textview = new TextView(MainActivity.this);
                TextPaint textpaint;

                //No.2
                //开始写代码。要求实现获取任意的string添加到textview中，每个字符串以“~”分隔，并获取textview的长度。

                textpaint = new TextPaint();
                textpaint.setTextSize(text_size);
                textpaint.setTextAlign(Paint.Align.LEFT);

                int index = random.nextInt(4);
                String tv = textview.getText().toString() + string[index] + "~";
                textview.setText(tv);
                length = (int)textpaint.measureText(tv);
                //end_code
                if(length > (windowWidth-100)){
                    break;
                }
                linearlayout.addView(textview);

            }
            return linearlayout;
        }
    }
}