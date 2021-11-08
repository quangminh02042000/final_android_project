package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.studentmanagement.database.Database;
import com.example.studentmanagement.model.Student;

public class ActivityAddStudent extends AppCompatActivity {

    Button btnAddStudent;
    EditText editStudentName, editStudentCode, editStudentDateofBirth;
    RadioButton radMale, radFemale;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        btnAddStudent = findViewById(R.id.buttonAddStudent);
        editStudentName = findViewById(R.id.EditTextStudentName);
        editStudentCode = findViewById(R.id.EditTextStudentCode);
        editStudentDateofBirth = findViewById(R.id.EditTextStudentBirthday);

        radMale = findViewById(R.id.radMale);
        radFemale = findViewById(R.id.radFemale);

        //Get id of subject
        Intent intent = getIntent();
        int id_subject = intent.getIntExtra("id_subject", 0);

        database = new Database(this);
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAdd(id_subject);
            }
        });
    }

    //Dialog Add
    private void DialogAdd(int id_subject) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogaddstudent);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYesAddStudent);
        Button btnNo = dialog.findViewById(R.id.buttonNoAddStudent);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editStudentName.getText().toString().trim();
                String code = editStudentCode.getText().toString().trim();
                String birthday = editStudentDateofBirth.getText().toString().trim();
                String sex = "";
                if(radMale.isChecked()) {
                    sex = "Male";
                }else if(radFemale.isChecked()) {
                    sex = "Female";
                }

                if(name.equals("") || code.equals("") || birthday.equals("") || sex.equals("")) {
                    Toast.makeText(ActivityAddStudent.this, "You must fill all infomation", Toast.LENGTH_SHORT).show();
                } else {
                    Student student = createStudent(id_subject);

                    database.addStudent(student);

                    Intent intent = new Intent(ActivityAddStudent.this, ActivityStudent.class);
                    intent.putExtra("id_subject", id_subject);
                    startActivity(intent);

                    Toast.makeText(ActivityAddStudent.this, "Add Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    private Student createStudent(int id_subject) {
        String name = editStudentName.getText().toString().trim();
        String code = editStudentCode.getText().toString().trim();
        String birthday = editStudentDateofBirth.getText().toString().trim();
        String sex = "";
        if(radMale.isChecked()) {
            sex = "Male";
        }else if(radFemale.isChecked()) {
            sex = "Female";
        }

        Student student = new Student(name, code, sex, birthday, id_subject);
        return student;

    }
}