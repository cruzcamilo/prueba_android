package com.example.android.prueba_android;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.android.prueba_android.databinding.ActivityJavaSortBinding;

import java.util.ArrayList;
import java.util.Collections;

public class JavaSort extends AppCompatActivity {

    private static final String TAG = JavaSort.class.getName();

    private int totalStudents;
    private ArrayList<Student> students;
    private int id;
    private String firstName;
    private double cgpa;
    private ActivityJavaSortBinding mBinding;
    private int inputId;
    private ArrayAdapter<Student> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_java_sort);
        students = new ArrayList<Student>();
        mBinding.studentsTotalEt.setText("2");

        mAdapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, students);
        mBinding.studentsLv.setAdapter(mAdapter);
    }


    public void saveStudent(View view) {
        String studentAmount = mBinding.studentsTotalEt.getText().toString();
        if (studentAmountValidation(studentAmount)) {
            if (mBinding.studentInfoEt.getText().toString().isEmpty()) {
                Toast.makeText(this, "No text has been entered", Toast.LENGTH_SHORT).show();
            } else {
                String tempString = mBinding.studentInfoEt.getText().toString();
                String[] words = tempString.split("\\s+");
                if (inputValidation(words) && students.size() < totalStudents) {
                    students.add(new Student(id, firstName, cgpa));
                    mBinding.studentInfoEt.setText("");
                    mAdapter.notifyDataSetChanged();

                    if(students.size()==totalStudents) {
                        mBinding.sortStudents.setEnabled(true);
                        mBinding.saveStudents.setEnabled(false);
                    }
                }
            }
        } else {
            Toast.makeText(this, R.string.student_number_range, Toast.LENGTH_SHORT).show();
        }
        //Test code
            /*
            students.add(new Student(33, "Rumpa", 3.68));
            students.add(new Student(85, "Ashis", 3.85));
            students.add(new Student(56, "Samiha", 3.75));
            students.add(new Student(19, "Samara", 3.75));
            students.add(new Student(22, "Fahim", 3.76));
            sortStudents(students);
            */
    }

    public void sortStudents(View view) {
        Collections.sort(students, Student.studentComparator);

        ArrayList<String> sortedNames = new ArrayList<>();
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sortedNames);
        for (int i = 0; i < students.size();i++) {
            adapter.add(students.get(i).getFirstName());
        }
        mAdapter.clear();
        mBinding.studentsLv.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    private Boolean studentAmountValidation(String amount) {
        if (amount!=null && !amount.isEmpty()) {
            totalStudents = Integer.parseInt(amount);
            return totalStudents >= 2 && totalStudents <= 1000;
        } else {
            return false;
        }
    }


    private Boolean inputValidation(String[] inputString) {

        String inputName = "";
        Double inputCgpa;

        if (inputString.length != 3) {
            Toast.makeText(this, "Enter ID, name and cgpa", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (idValidation(inputString[0])) {
            id = inputId;
        } else {
            return false;
        }

        inputName = inputString[1];
        if (inputName.length() < 5 || inputName.length() > 30) {
            Toast.makeText(this, "Name length must be between 5 and 30 characters", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            firstName = inputName;
        }

        try{
            inputCgpa = Double.parseDouble(inputString[2]);
        } catch (NumberFormatException e){
            Log.e(TAG, e.toString());
            Toast.makeText(this, "CGPA must be a double", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (inputCgpa < 0 || inputCgpa > 4.00) {
            Toast.makeText(this, "CGPA must be between 0 and 4.00", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            cgpa = inputCgpa;
        }
        return true;
    }

    private Boolean idValidation(String inputId) {
        for (int i = 0; i < inputId.length(); i++) {
            Character ch = inputId.charAt(i);
            if (!Character.isDigit(ch)) {
                Toast.makeText(this, "Id must be an integer", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        this.inputId = Integer.parseInt(inputId);

        if (this.inputId < 0 || this.inputId > 100000) {
            Toast.makeText(this, "Id must be a integer between 1 and 100000", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}