package com.example.tha_app_194094e;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button edit_button;
    RecyclerView recyclerView;
    FloatingActionButton add_Button;
    MyDatabaseHelper myDatabaseHelper;
    ArrayList<String> id,name,description,price;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycleView);
        add_Button=findViewById(R.id.add_Button);
        add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });




        myDatabaseHelper=new MyDatabaseHelper(MainActivity.this);
        id=new ArrayList<>();
        name=new ArrayList<>();
        description=new ArrayList<>();
        price=new ArrayList<>();


        displayData();
        customAdapter =new CustomAdapter(MainActivity.this,this,id,name,description,price);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager (new LinearLayoutManager(MainActivity.this));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode,data);
        if(requestCode==1)
        {
            recreate();
        }
    }

    void displayData() {
        Cursor cursor = myDatabaseHelper.readAllData();
        if (cursor.getCount() == 0)
        {
            Toast.makeText(this,"No data", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext())
            {
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                description.add(cursor.getString(2));
                price.add(cursor.getString(3));
            }
        }
    }

}