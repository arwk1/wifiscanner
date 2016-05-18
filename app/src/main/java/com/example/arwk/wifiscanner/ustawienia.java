package com.example.arwk.wifiscanner;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class ustawienia extends AppCompatActivity {
    CheckBox checkbox1;
    CheckBox checkbox2;
    TextView textview;
    TextView textview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ustawienia);
        checkbox1 = (CheckBox)findViewById(R.id.checkBox1);
        checkbox2 = (CheckBox)findViewById(R.id.checkBox2);
        textview = new TextView(this);
        textview2 = new TextView(this);
        textview=(TextView)findViewById(R.id.textView4);
        textview2=(TextView)findViewById(R.id.textView5);

        loadSavedPreferences();
        checkButtonClick();
        checkButtonClick2();
        }

    private void savePreferences(String key, Integer value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.getContextOfApplication());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();

    }
    private void loadSavedPreferences(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.getContextOfApplication());
        int checkBox1Val = sharedPreferences.getInt("CheckBox_Val1", -1);
        int checkBox2Val = sharedPreferences.getInt("CheckBox_Val2", -1);
        if (checkBox1Val == 1) {
            checkbox1.setChecked(true);
        }
        else {
            checkbox1.setChecked(false);
        }
        if (checkBox2Val == 1){
            checkbox2.setChecked(true);
        }
        else{
            checkbox2.setChecked(false);
        }
    }
    private void checkButtonClick(){
        final Button myButton = (Button)findViewById(R.id.button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(checkbox1.isChecked()){
                savePreferences("CheckBox_Val1", 1);}
                else{
                savePreferences("CheckBox_Val1", 0);
            }
                if(checkbox2.isChecked()){
                    savePreferences("CheckBox_Val2", 1);
                }
                else{
                    savePreferences("CheckBox_Val2", 0);
                }
            }
        });
    }
    private void checkButtonClick2(){
        final Button myButton = (Button)findViewById(R.id.button2);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.contextOfApplication);
                Integer check1val = sharedPreferences.getInt("CheckBox_Val1", -1);
                Integer check2Val = sharedPreferences.getInt("CheckBox_Val2", -1);
                if(check1val == 1){
                    textview.setText("true");}
                else{
                    textview.setText("false");
                }
                if(check2Val == 1){
                    textview2.setText("true");}
                else{
                    textview2.setText("false");}
                }
            });
        }
}







