package com.example.android.yos_1202154119_modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseSetting db;
    RecyclerView recyclerView;
    Adapter mAdapter;
    ArrayList<Todo> daftarData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //menampilkan toolbar ke dalam layout sehingga dapat digunakan
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Todo List");

        recyclerView = findViewById(R.id.recyclerView);

        //membuat ArrayList, database, dan method readData
        daftarData = new ArrayList<>();
        db = new DatabaseSetting(this);
        db.readData(daftarData);

        //melalkukan inisialisasi shared preferences
        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);

        //membuat adapter baru
        mAdapter = new Adapter(this, daftarData, color);

        //setting ukuran recycler sesuai ukuran dan membuat layoutmanager
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //melakukan set adapter pada recyclerview
        recyclerView.setAdapter(mAdapter);

        //menjalankan method swipeDelete pada data mList digunakan untuk menghapus item data
        swipedDelete();
    }

    public void swipedDelete(){
        //membuat item touch helper baru
        ItemTouchHelper.SimpleCallback touchcall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Todo current = mAdapter.getData(position);

                //ketika melakukan swipe ke kiri
                if(direction == ItemTouchHelper.LEFT){
                    //menghapus data menurut PK
                    if(db.deleteData(current.getTodo())){
                        mAdapter.deleteData(position);
                        Toast.makeText(MainActivity.this, "Data deleted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };

        //itemtouch untuk recyclerview
        ItemTouchHelper ITH = new ItemTouchHelper(touchcall);
        ITH.attachToRecyclerView(recyclerView);
    }

    // method untuk membuat menu pada activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //method ketika memilih item data
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //ketika diklik menu Settings
        if (id==R.id.action_settings){
            //menjalankan intent ke menu Settings
            Intent intent = new Intent(MainActivity.this, Settings.class);
            startActivity(intent);
            finish();
        }

        return true;
    }

    //method untuk menjalankan ketika FAB diklik
    public void tambahData(View view) {
        Intent intent = new Intent(MainActivity.this, AddToDo.class);
        startActivity(intent);
    }
}