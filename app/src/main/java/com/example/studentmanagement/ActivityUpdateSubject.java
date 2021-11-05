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

public class ActivityUpdateSubject extends AppCompatActivity {

    EditText edtUpdateTitle, edtUpdateCredit, edtUpdateTime, edtUpdatePlace;
    Button btnUpdatesubject;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_update_subject);

        edtUpdateCredit = findViewById (R.id.EditTextUpdateSubjectCredit);
        edtUpdatePlace = findViewById (R.id.EditTextUpdateSubjectPlace);
        edtUpdateTime = findViewById (R.id.EditTextUpdateSubjectTime);
        edtUpdateTitle = findViewById (R.id.EditTextUpdateSubjectTitle);
        btnUpdatesubject = findViewById (R.id.buttonUpdateSubject);

        //lấy dữ liệu intent
        Intent intent = getIntent ();

        int id = intent.getIntExtra ("id", 0);
        String title = intent.getStringExtra ("title");
        int credit = intent.getIntExtra ("credit", 0);
        String time = intent.getStringExtra ("time");
        String place = intent.getStringExtra ("place");

        edtUpdateTitle.setText (title);
        edtUpdateTime.setText (time);
        edtUpdatePlace.setText (place);
        edtUpdateCredit.setText (credit + "");

        database = new Database (this);

        btnUpdatesubject.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                DialogUpdate (id);
            }
        });
    }

    private void DialogUpdate(int id) {
        Dialog dialog = new Dialog (this);
        dialog.setContentView (R.layout.dialogupdatesubject);

        dialog.setCanceledOnTouchOutside (false);

        Button btnYes = dialog.findViewById (R.id.buttonYesupdate);
        Button btnNo = dialog.findViewById (R.id.buttonNoupdate);

        btnYes.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                String subjecttile = edtUpdateTitle.getText ().toString ().trim ();
                String credit = edtUpdateCredit.getText ().toString ().trim ();
                String time = edtUpdateTime.getText ().toString ().trim ();
                String place = edtUpdatePlace.getText ().toString ().trim ();

                if (subjecttile.equals ("") || credit.equals ("") || time.equals ("") || place.equals ("")) {
                    Toast.makeText (ActivityUpdateSubject.this, "Did you enter enough information", Toast.LENGTH_SHORT).show ();
                } else {
                    Subject subject = updatesubject ();
                    database.updateSubject (subject, id);
//update thanh cong thi qua activity subject
                    Intent intent = new Intent (ActivityUpdateSubject.this, ActivitySubject.class);
                    startActivity (intent);
                    Toast.makeText (ActivityUpdateSubject.this, "more success", Toast.LENGTH_SHORT).show ();
                }
            }
        });

        btnNo.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                dialog.cancel ();
            }
        });
        dialog.show ();
    }

    private Subject updatesubject() {
        String subjecttile = edtUpdateTitle.getText ().toString ().trim ();
        int credit = Integer.parseInt (edtUpdateCredit.getText ().toString ().trim ());
        String time = edtUpdateTime.getText ().toString ().trim ();
        String place = edtUpdatePlace.getText ().toString ().trim ();

        Subject subject = new Subject (subjecttile, credit, time, place);
        return subject;
    }
}