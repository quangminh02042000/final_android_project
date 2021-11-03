package com.example.studentmanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.studentmanagement.ActivitySubject;
import com.example.studentmanagement.R;
import com.example.studentmanagement.model.Subject;

import java.util.ArrayList;

public class AdapterSubject extends BaseAdapter {

    private ActivitySubject context;
    private ArrayList<Subject> arrayListSubject;

    public AdapterSubject(ActivitySubject context, ArrayList<Subject> arrayListSubject) {
        this.context = context;
        this.arrayListSubject = arrayListSubject;
    }

    @Override
    public int getCount() {
        return arrayListSubject.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListSubject.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.listsubject, null);
        TextView TextViewSubjectTitle = convertView.findViewById(R.id.TextViewSubjectTitle);
        TextView TextViewCredit = convertView.findViewById(R.id.TextViewCredit);
        ImageButton imageInformation = convertView.findViewById(R.id.subjectInformation);
        ImageButton imageUpdate = convertView.findViewById(R.id.subjectUpdate);
        ImageButton imageDelete = convertView.findViewById(R.id.subjectDelete);

        Subject subject = arrayListSubject.get(position);

        TextViewSubjectTitle.setText(subject.getSubjectTitle());
        TextViewCredit.setText(Integer.toString(subject.getNumberOfCredit()));

        int id = subject.getId();

        imageInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }
}
