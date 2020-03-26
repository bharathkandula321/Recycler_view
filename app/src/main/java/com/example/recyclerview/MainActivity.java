package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements MyAdapter.AdapterInterface {

    ArrayList<QuestionAndAnswers> arrayList=new ArrayList<>();
    HashMap<String, String> answersHashmap=new HashMap<>();

    /*ArrayList<String> questionsList = new ArrayList<>();
    ArrayList<String []> optionsList = new ArrayList<>();*/

    /*HashMap<Integer, HashMap<String,String[]>> questionHashMapSetByID = new HashMap<>();
    HashMap<String,String[]> questionsAndAnswersHashMap = new HashMap<>()*/


    /*void addQuestions() {

        questionsList.add("What is your Qualification?");

        questionsList.add("What is your branch?");

    }*/

    void addElementts() {

        arrayList.add(new QuestionAndAnswers("What is your qualification ?",new String[]{"B.tech","M.Tech","Mba","Diploma"},1));
        arrayList.add(new QuestionAndAnswers("What is your stream ?",new String[]{"ece","eee","mech","cse"},2));
        arrayList.add(new QuestionAndAnswers("What is the capital of the god's own country Kerala?",new String[]{"Thiruvanantapuram","Kolkata","Mumbai","Bhopal"},3));
        arrayList.add(new QuestionAndAnswers("What is the capital of India's heart, Madhya Pradesh?",new String[]{"Jammu","Bhopal","Kota","Srinagar"},4));



    }
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addElementts();
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);

        adapter = new MyAdapter(arrayList,MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        submitButton = (Button) this.findViewById(R.id.submit_button);

    }
    @Override
    public void onResume() {

        super.onResume();
        setListners();

    }

    void setListners() {

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<String> set=answersHashmap.keySet();
                int l=set.size();
                System.out.println(l);
                if (set.size() == arrayList.size()) {

                    Intent intent = new Intent(MainActivity.this,DynamicActivity.class);
                    intent.putExtra("answers",answersHashmap);
                    startActivity(intent);
                    finish();
                }


            }
        });
    }


    @Override
    public void sendAnswersToActivity(HashMap<String, String> hashMap) {
        answersHashmap = hashMap;
    }




    class QuestionAndAnswers {

        public String question;
        String[] options;
        int questionID;
        QuestionAndAnswers( String question,String[] options ,int questionID )  {


            this.question = question;
            this.options = options;
            this.questionID = questionID;
        }
        public  String getQuestion() {

            return question;
        }
        public String[] getOptions() {

            return options;
        }
        public int getQuestionID() {

            return questionID;
        }

    }

}
