package com.example.studentmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IInterface;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.studentmanagement.adapter.AdapterSubject;
import com.example.studentmanagement.database.Database;
import com.example.studentmanagement.model.Subject;

import java.util.ArrayList;

public class ActivitySubject extends AppCompatActivity {

    Toolbar toolbar;
    ListView listViewSubject;
    ArrayList<Subject> subjectArrayList;
    com.example.studentmanagement.database.Database database;
    com.example.studentmanagement.adapter.AdapterSubject adapterSubject;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        toolbar = findViewById(R.id.toolbarSubject);
        listViewSubject = findViewById(R.id.listviewSubject);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new Database(this);
        subjectArrayList = new ArrayList<>();

        Cursor cursor = database.getDataSubject();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            int credit = cursor.getInt(2);
            String time = cursor.getString(3);
            String place = cursor.getString(4);

            subjectArrayList.add(new Subject(id,title,credit,time,place));
        }

        adapterSubject = new AdapterSubject(ActivitySubject.this, subjectArrayList);
        listViewSubject.setAdapter(adapterSubject);
        cursor.moveToFirst();
        cursor.close();

        listViewSubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ActivitySubject.this, ActivityStudent.class);
                int id_subject = subjectArrayList.get(position).getId();
                //truyen id subject qua activity student
                intent.putExtra("id_subject", id_subject);
                startActivity(intent);
            }
        });
    }

    //them menu Add vao Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuadd, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            //click vao add chuyen qua man hinh add subject
            case R.id.menuAdd:
                Intent intent1 = new Intent(ActivitySubject.this, ActivityAddSubject.class);
                startActivity(intent1);
                break;
            //neu click vao nut con lai thi quay lai main
            default:
                Intent intent = new Intent(ActivitySubject.this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        count++;
        if(count>=1){
            Intent intent = new Intent(ActivitySubject.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}