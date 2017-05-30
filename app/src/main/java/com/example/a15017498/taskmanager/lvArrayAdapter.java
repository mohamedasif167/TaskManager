package com.example.a15017498.taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 15017498 on 30/5/2017.
 */

public class lvArrayAdapter extends ArrayAdapter<Task> {

    Context context;
    ArrayList<Task> tasks;
    int resource;
    TextView txtTitle,txtDesc;


    public lvArrayAdapter(Context context, int resource, ArrayList<Task> notes) {
        super(context, resource, notes);
        this.context = context;
        this.tasks = notes;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        //Match the UI components with Java variables

        txtTitle = (TextView) rowView.findViewById(R.id.Title);
        txtDesc = (TextView) rowView.findViewById(R.id.Desc);

        Task task = tasks.get(position);


        txtTitle.setText(task.getID()+" "+  task.getName());
        txtDesc.setText(task.getDesc());


        return rowView;
    }


}
