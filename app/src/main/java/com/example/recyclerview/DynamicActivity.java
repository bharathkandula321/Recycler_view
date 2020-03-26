package com.example.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DynamicActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_layout);
        Intent intent = getIntent();
        HashMap<String,String> hashMap = (HashMap<String, String>) intent.getSerializableExtra("answers");
        Set<String> keys = hashMap.keySet();
        System.out.println(hashMap);
/*
        LinearLayout linearLayout = findViewById(R.id.responses_layout) ;
*/
        LinearLayout linearLayout1 = findViewById(R.id.response_inner_layout) ;
        /*extView textView1 = findViewById(R.id.responses_question_number);
        TextView textView2 = findViewById(R.id.responses_answer);*/
        Iterator<String> iterator=keys.iterator();
       for (int i = 0; i < hashMap.size(); i++) {
            String temp=iterator.next();
            TextView textView1 = new TextView(this);
           TextView textView2 = new TextView(this);
           LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
           params1.weight = 1;
           textView1.setLayoutParams(params1);
           LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
           params2.weight = 1;
           textView2.setLayoutParams(params2);
            textView1.setText(temp);
            textView2.setText(hashMap.get(temp));
           LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
           LinearLayout childLayout = new LinearLayout(this);
           childLayout.setOrientation(LinearLayout.HORIZONTAL);
           childLayout.setLayoutParams(layoutParams);
           childLayout.addView(textView1);
           childLayout.addView(textView2);
           linearLayout1.addView(childLayout);


            /*linearLayout1.addView(textView1);
            linearLayout1.
            linearLayout1.addView(textView2);*/

            /*TextView textView = new TextView(this);
            textView.setText("        "+temp+ "                                " +hashMap.get(temp));
            linearLayout.addView(textView);*/
            iterator.hasNext();
       }
    }

    @Override
    public void onResume() {

        super.onResume();

    }
}
