package com.example.arwk.wifiscanner;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class ustawienia extends AppCompatActivity {
    CheckBox checkbox1;
    CheckBox checkbox2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ustawienia);
        checkbox1 = (CheckBox)findViewById(R.id.checkBox1);
        checkbox2 = (CheckBox)findViewById(R.id.checkBox2);
        loadSavedPreferences();
        checkButtonClick();
        }
    private void savePreferences(String key, boolean value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
    private void loadSavedPreferences(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean checkBox1Val = sharedPreferences.getBoolean("CheckBox_Val1", false);
        boolean checkBox2Val = sharedPreferences.getBoolean("CheckBox_Val2", false);
        if (checkBox1Val) {
            checkbox1.setChecked(true);
        }
        else {
            {checkbox1.setChecked(false);
            }
        }
        if (checkBox2Val){
            checkbox2.setChecked(true);
        }
        else{
            {
                checkbox2.setChecked(false);
            }
        }
    }
    private void checkButtonClick(){
        final Button myButton = (Button)findViewById(R.id.button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(checkbox1.isChecked()){
                savePreferences("CheckBox_Val1",checkbox1.isChecked());
            }
                if(checkbox2.isChecked()){
                    savePreferences("CheckBox_Val2",checkbox2.isChecked());

                }
            }



        });
    }
}







