package com.example.android.prueba_android;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.android.prueba_android.databinding.ActivityJavaArrayBinding;

import java.util.ArrayList;

public class JavaArray extends AppCompatActivity {

    private ActivityJavaArrayBinding mBinding;
    private ArrayList<ArrayList<Integer>> matrix;
    private ArrayList<Integer> inputArray;
    private ArrayAdapter<String> mAdapter;
    private int totalLines;
    private int remainingQueries = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_java_array);
        matrix = new ArrayList<ArrayList<Integer>>();
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        mBinding.arraysLv.setAdapter(mAdapter);
    }

    public void nextArray(View view) {
        inputArray = new ArrayList<Integer>();
        String linesAmount = mBinding.numberArraysTv.getText().toString();

        if (nLinesValidation(linesAmount)) {
            if (!mBinding.arrayEnteredEt.getText().toString().isEmpty()) {
                String tempString = mBinding.arrayEnteredEt.getText().toString();
                String[] individualNums = tempString.split("\\s+");
                if (arrayValidation(individualNums)) {
                    for (String num : individualNums) {
                        inputArray.add(Integer.parseInt(num));
                    }

                    matrix.add(inputArray);
                    mBinding.arrayEnteredEt.setText("");
                    mAdapter.add(tempString);
                    mAdapter.notifyDataSetChanged();

                    if (matrix.size() == totalLines) {
                        mBinding.nextArrayBtn.setEnabled(false);
                        mBinding.queryBtn.setEnabled(true);
                    }
                }
            } else {
                Toast.makeText(this, "No text has been entered", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Select a number of lines between 1 and 20.000", Toast.LENGTH_SHORT).show();
        }
    }

    public void executeQuery(View view) {
        String numberQueries = mBinding.numberQueriesTv.getText().toString();
        String inputQuery = mBinding.queryEt.getText().toString();

        if(remainingQueries == 0){
            if(!numberQueriesValidation(numberQueries)){
                Toast.makeText(this, "Enter a number of lines between 1 and 1000", Toast.LENGTH_SHORT).show();
            }
        }

        if (!inputQuery.isEmpty()) {
            String[] individualNums = inputQuery.split("\\s+");
            ArrayList<Integer> selectedArray;
            if (individualNums.length == 2) {
                remainingQueries -= 1;
                if (remainingQueries == 0) {
                    mBinding.queryBtn.setEnabled(false);
                }

                int firstDigit = Integer.parseInt(individualNums[0]);
                int secondDigit = Integer.parseInt(individualNums[1]);

                if (firstDigit > matrix.size() || firstDigit < 1 || firstDigit > totalLines) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    selectedArray = matrix.get(firstDigit - 1);
                }

                if (secondDigit >= selectedArray.size()) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                } else {
                    Integer result = selectedArray.get(secondDigit);
                    String resultString = String.valueOf(result);
                    Toast.makeText(this, resultString, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Enter two numbers per query", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No query has been entered", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean numberQueriesValidation(String amount) {
        if (amount != null && !amount.isEmpty()) {
            remainingQueries = Integer.parseInt(amount);
            return totalLines >= 1 && totalLines <= 1000;
        } else {
            return false;
        }
    }


    private Boolean nLinesValidation(String amount) {
        if (amount != null && !amount.isEmpty()) {
            totalLines = Integer.parseInt(amount);
            return totalLines >= 1 && totalLines <= 20000;
        } else {
            return false;
        }
    }

    private Boolean arrayValidation(String[] inputString) {
        int firstDigit = Integer.parseInt(inputString[0]);

        if (0 <= firstDigit && firstDigit <= 50000) {
            if (firstDigit != inputString.length - 1) {
                int missingNumbers = (firstDigit + 1) - inputString.length;
                Toast.makeText(this, "Please enter " + missingNumbers + " additional numbers", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(this, "First digit must be between 0 and 50.000", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
