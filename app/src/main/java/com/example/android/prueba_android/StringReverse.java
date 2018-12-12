package com.example.android.prueba_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StringReverse extends AppCompatActivity {

    EditText editTextReverseString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_reverse);
        editTextReverseString = (EditText) findViewById(R.id.string_reverse_et);
    }

    public void checkStringReverse(View view){
        String inputWord = editTextReverseString.getText().toString();

        if (inputValidation(inputWord)){
            String reversedWord = new StringBuilder(inputWord).reverse().toString();
            if (inputWord.equals(reversedWord)){
                Toast.makeText(this, R.string.yes, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.No, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Boolean inputValidation(String inputWord){
        if(inputWord.length()>50){
            Toast.makeText(this, R.string.toast_string_reverse_length, Toast.LENGTH_SHORT).show();
            return false;
        }

        if(inputWord.matches(".*\\d+.*")){
            Toast.makeText(this, R.string.only_lower_case, Toast.LENGTH_SHORT).show();
            return false;
        }

        if(inputWord.length()==0){
            Toast.makeText(this, R.string.no_string_message, Toast.LENGTH_SHORT).show();
            return false;
        }

        for (int i=0; i<inputWord.length();i++){
            Character ch = inputWord.charAt(i);
            if (!Character.isLowerCase(ch)) {
                Toast.makeText(this, R.string.only_lower_case, Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}
