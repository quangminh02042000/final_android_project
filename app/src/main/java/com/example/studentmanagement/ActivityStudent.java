package com.example.studentmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.studentmanagement.adapter.AdapterStudent;
import com.example.studentmanagement.database.Database;
import com.example.studentmanagement.model.Student;

import java.util.ArrayList;

public class ActivityStudent extends AppCompatActivity {

    Toolbar toolbar;
    ListView listViewStudent;

    ArrayList<Student> ArrayListStudent;
    Database database;
    AdapterStudent adapterStudent;

    int id_subject = 0;
    int counter = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        toolbar = findViewById(R.id.toolbarstudent);
        listViewStudent = findViewById(R.id.listviewstudent);

        Intent intent = getIntent();
        id_subject = intent.getIntExtra("ib_subject", 0);

        //toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new Database(this);

        ArrayListStudent = new ArrayList<>();
        ArrayListStudent.clear();

        Cursor cursor = database.getDataStudent(id_subject);
        while(cursor.moveToNext()) {
            int id_sub = cursor.getInt(5);
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String sex = cursor.getString(2);
            String code = cursor.getString(3);
            String birthday = cursor.getString(4);

            ArrayListStudent.add(new Student(id, name, sex, code, birthday, id_sub));
        }

        adapterStudent = new AdapterStudent(ActivityStudent.this, ArrayListStudent);

        //display listview
        listViewStudent.setAdapter(adapterStudent);
        cursor.moveToFirst();
        cursor.close();
    }

    // Them icon add
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuaddstudent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuaddstudent:

                Intent intent = new Intent(ActivityStudent.this, ActivityAddStudent.class);
                intent.putExtra("id_subject", id_subject);
                startActivity(intent);
                break;

            default:
                Intent intent1 = new Intent(ActivityStudent.this, ActivitySubject.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //back tren dien thoai ra subject activity
    @Override
    public void onBackPressed() {
        counter++;
        if(counter >= 1) {
            Intent intent = new Intent(this, ActivitySubject.class);
            startActivity(intent);
            finish();
        }
    }
}