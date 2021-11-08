package com.example.studentmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.studentmanagement.ActivityStudent;
import com.example.studentmanagement.R;
import com.example.studentmanagement.model.Student;

import java.util.ArrayList;

public class AdapterStudent extends BaseAdapter {

    private ActivityStudent context;
    private ArrayList<Student> ArrayListStudent;

    public AdapterStudent(ActivityStudent context, ArrayList<Student> arrayListStudent) {
        this.context = context;
        ArrayListStudent = arrayListStudent;
    }

    @Override
    public int getCount() {
        return ArrayListStudent.size();
    }

    @Override
    public Object getItem(int i) {
        return ArrayListStudent.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.liststudent, null);

        TextView txtName = view.findViewById(R.id.TextViewStudentName);
        TextView txtCode = view.findViewById(R.id.TextViewStudentCode);

        ImageButton imgbtnDelete = view.findViewById(R.id.studentDelete);
        ImageButton imgbtnUpdate = view.findViewById(R.id.studentUpdate);
        ImageButton imgInformation = view.findViewById(R.id.studentInformation);

        Student student = ArrayListStudent.get(i);

        txtName.setText(student.getStudent_name());
        txtCode.setText(student.getStudent_code());

        int id = student.getId_student();
        imgbtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        imgbtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        imgInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }
}
