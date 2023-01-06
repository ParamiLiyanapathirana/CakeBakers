package com.example.tha_app_194094e;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {


    EditText name;
    EditText description;
    EditText price;
    Button update_button,delete_button,add_button;
    String id,name1,description1,price1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name=findViewById(R.id.name_input);
        description=findViewById(R.id.description_input);
        price=findViewById(R.id.price_input);
        add_button = findViewById(R.id.add_button);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        getAndSetIntentData();
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper db=new MyDatabaseHelper(AddActivity.this);
                db.add(name.getText().toString().trim(),
                        description.getText().toString().trim(),
                        Float.valueOf(price.getText().toString().trim())
                        );
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);

                myDB.updatedata(id,name1,description1,price1);

                name1 = name.getText().toString().trim();
                description1= description.getText().toString().trim();
                price1 = price.getText().toString().trim();
                if (description1.length() <= 75) {
                    myDB.updatedata(id, name1, description1, price1);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Validation faild!",
                            Toast.LENGTH_SHORT).show();
                }

                //finish();

                /*name = name;
                description = description;
                price = price;
                myDB.updatedata(name,description, price);*/

            }
        });




    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
    void getAndSetIntentData()
    {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("description") && getIntent().hasExtra("price")){

            id=getIntent().getStringExtra("id");
            name1=getIntent().getStringExtra("name");
            description1=getIntent().getStringExtra("description");
            price1=getIntent().getStringExtra("price");

            name.setText(name1);
            description.setText(description1);
            price.setText(price1);
        }
        else
        {
            Toast.makeText(this, "No data",Toast.LENGTH_SHORT).show();
        }
    }
}