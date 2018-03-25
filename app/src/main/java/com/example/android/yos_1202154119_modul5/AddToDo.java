package com.example.android.yos_1202154119_modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddToDo extends AppCompatActivity {
    EditText mTodo;
    EditText Description;
    EditText mPriority;

    DatabaseSetting db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);

        setTitle("Add Todo");

        mTodo = (EditText) findViewById(R.id.et_todo);
        Description = (EditText) findViewById(R.id.et_description);
        mPriority = (EditText) findViewById(R.id.et_priority);

        db = new DatabaseSetting(this);
    }

    //method ketika melakukan klik back
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddToDo.this, MainActivity.class);
        startActivity(intent);

        //menandakan selesai setelah menjalankan intent
        this.finish();
    }

    public void onAddTodo(View view) {
        //ketika data terdapat isi-nya
        if (db.inputdata(new Todo(mTodo.getText().toString(), Description.getText().toString(), mPriority.getText().toString()))){
            Toast.makeText(this, "Data added", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddToDo.this, MainActivity.class));
            this.finish();
        }else {
            Toast.makeText(this, "List cannot empty", Toast.LENGTH_SHORT).show();
            //edittext semua kosong
            mTodo.setText(null);
            Description.setText(null);
            mPriority.setText(null);
        }
    }
}