package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentmanagement.database.Database;
import com.example.studentmanagement.model.Subject;

public class ActivityAddSubject extends AppCompatActivity {

    Button btnAddSubject;
    EditText edtSubjectTitle, edtSubjectCredit, edtSubjectTime, edtSubjectPlace;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        btnAddSubject = findViewById(R.id.buttonAddSubject);
        edtSubjectTitle = findViewById(R.id.EditTextSubjectTitle);
        edtSubjectCredit = findViewById(R.id.EditTextSubjectCredit);
        edtSubjectTime = findViewById(R.id.EditTextSubjectTime);
        edtSubjectPlace = findViewById(R.id.EditTextSubjectPlace);

        database = new Database(this);

        btnAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd();
            }
        });
    }

    private void DialogAdd(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogadd);
        //tat click ngoai la thoat
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYes);
        Button btnNo = dialog.findViewById(R.id.buttonNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjectTitle = edtSubjectTitle.getText().toString().trim();
                String credit = edtSubjectCredit.getText().toString().trim();
                String time = edtSubjectTime.getText().toString().trim();
                String place = edtSubjectPlace.getText().toString().trim();

                //neu du lieu troong
                if(subjectTitle.equals("") || credit.equals("") || time.equals("") || place.equals("")){
                    Toast.makeText(ActivityAddSubject.this, "Please enter enough information", Toast.LENGTH_SHORT).show();
                } else {
                    Subject subject = createSubject();
                    database.addSubject(subject);
                    Intent intent = new Intent(ActivityAddSubject.this, ActivitySubject.class);
                    startActivity(intent);
                    Toast.makeText(ActivityAddSubject.this, "more success", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();

    }

    //create subject
    private Subject createSubject(){
        String subjectTitle = edtSubjectTitle.getText().toString().trim();
        int credit = Integer.parseInt(edtSubjectCredit.getText().toString().trim());
        String time = edtSubjectTime.getText().toString().trim();
        String place = edtSubjectPlace.getText().toString().trim();

        Subject subject = new Subject(subjectTitle, credit, time, place);
        return subject;
    }
}