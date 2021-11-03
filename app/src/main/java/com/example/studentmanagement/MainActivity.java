package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnSubject, btnAuthor , btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAuthor = findViewById(R.id.buttonAuthor);
        btnExit = findViewById(R.id.buttonExit);
        btnSubject =findViewById(R.id.buttonSubject);

        //Click tac gia
        btnAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAuthor();

            }
        });
        //Click subject
        btnSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ActivitySubject.class);
                startActivity(intent);
            }
        });
        //Click Exit app
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogExit();
            }
        });

    }
//Ham hien thi cua so dialog exit
    private void DialogExit(){
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogexit);
        //Tat click ngoai la thoat
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYes);
        Button btnNo = dialog.findViewById(R.id.buttonNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this,MainActivity.class);
                startActivity(intent);
                // Thoat
                Intent intent1 = new Intent(Intent.ACTION_MAIN);
                intent1.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent1);
            }
        });
        //Neu click no thi  dong cua so
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        //show dialog
        dialog.show();
    }
    //Hien thi thong tin tac gia
    private void DialogAuthor(){
        //Tao doi tuong cua so dialog
        Dialog dialog = new Dialog( this);

        //Nap layout vao dialog
        dialog.setContentView(R.layout.dialoginformation);
        dialog.show();
    }
}