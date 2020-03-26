package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public  class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    ArrayList<MainActivity.QuestionAndAnswers> arrayList;
    HashMap<String, String> updatedHashMap = new HashMap<String, String>();
    AdapterInterface adapterInterface;

    MyAdapter(ArrayList<MainActivity.QuestionAndAnswers> arrayList1, AdapterInterface ans) {

        arrayList = arrayList1;
        adapterInterface=ans ;

    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public RadioGroup radioGroup;
        public LinearLayout linearLayout;
        public RadioButton radioButton1,radioButton2,radioButton3,radioButton4;
        public TextView questionTextView,questionid;
        public Button clearButton;


        View view;
        public ViewHolder(@NonNull View questionAndAnswers) {

            super(questionAndAnswers);
            radioGroup = questionAndAnswers.findViewById(R.id.options);
            radioButton1 = questionAndAnswers.findViewById(R.id.option1);

            radioButton2 = questionAndAnswers.findViewById(R.id.option2);

            radioButton3 = questionAndAnswers.findViewById(R.id.option3);
            linearLayout = questionAndAnswers.findViewById(R.id.itemview_linearlayout1);
            radioButton4 = questionAndAnswers.findViewById(R.id.option4);
            questionTextView = questionAndAnswers.findViewById(R.id.question);
            questionid = questionAndAnswers.findViewById(R.id.questionid);
            clearButton = questionAndAnswers.findViewById(R.id.clear_button);


            view=questionAndAnswers;
            view.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(), questionTextView.getText(),Toast.LENGTH_SHORT).show();
                }
            });
            clearButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

                 radioGroup.clearCheck();

                }
            });
            /*String a = (String) questionid.getText();

            int id1 = radioGroup.getCheckedRadioButtonId();
            RadioButton b = (RadioButton) questionAndAnswers.findViewById(id1);
            CharSequence s=b.getText();



           updatedHashMap.put((String) questionid.getText(),s.toString() );
*/
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    String a = (String) questionid.getText();

                    int id1 = radioGroup.getCheckedRadioButtonId();
                    if(id1!=-1){
                    RadioButton b = (RadioButton) radioGroup.findViewById(id1);
                    CharSequence s=b.getText();
                    updatedHashMap.put((String) questionid.getText(),s.toString() );
                    adapterInterface.sendAnswersToActivity(updatedHashMap);}
                }
            });
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_itemview,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MainActivity.QuestionAndAnswers questionAndAnswers = arrayList.get(position);

        holder.questionTextView.setText(questionAndAnswers.getQuestion());
        String options[] = questionAndAnswers.getOptions();
        holder.radioButton1.setText(options[0]);
        holder.radioButton2.setText(options[1]);
        holder.radioButton3.setText(options[2]);
        holder.radioButton4.setText(options[3]);
        holder.questionid.setText((questionAndAnswers.getQuestionID()+""));

    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public interface AdapterInterface {

        public void sendAnswersToActivity(HashMap<String, String> hashMap);

    }

}
