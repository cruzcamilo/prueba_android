package com.example.android.prueba_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;

public class JavaAnagrams extends AppCompatActivity {

    EditText anagramWord1;
    EditText anagramWord2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_anagrams);

        anagramWord1 = (EditText) findViewById(R.id.anagram_first_word_et);
        anagramWord2 = (EditText) findViewById(R.id.anagram_second_word_et);
    }

    public void verifyAnagrams(View view){
        String firstWord = anagramWord1.getText().toString();
        String secondWord = anagramWord2.getText().toString();

        if(charactersValidation(firstWord) && charactersValidation(secondWord)){
            if(inputLengthValidation(firstWord, secondWord)){
                char[] first = firstWord.toLowerCase().toCharArray();
                char[] second = secondWord.toLowerCase().toCharArray();

                Arrays.sort(first);
                Arrays.sort(second);
                
                if (Arrays.equals(first, second)){
                    Toast.makeText(this, R.string.anagrams, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, R.string.not_anagrams, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private Boolean inputLengthValidation(String a, String b){

        if(a.length() ==0 || b.length()==0){
            Toast.makeText(this, R.string.no_string_message, Toast.LENGTH_SHORT).show();
            return false;
        }

        if(a.length()>50 || b.length()>50){
            Toast.makeText(this, R.string.length_anagram, Toast.LENGTH_SHORT).show();
            return false;
        }

        if(a.length() != b.length()){
            Toast.makeText(this, R.string.not_anagrams, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private Boolean charactersValidation(String inputWord){
        for (int i=0; i<inputWord.length();i++){
            Character ch = inputWord.charAt(i);
            if (!Character.isLetter(ch)) {
                Toast.makeText(this, R.string.only_alphabetic_characters, Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}
