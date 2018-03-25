package com.example.android.yos_1202154119_modul5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Settings extends AppCompatActivity {
    //deklarasi variabel yang akan digunakan
    private TextView warna;
    int colorid;
    AlertDialog.Builder alert;
    SharedPreferences.Editor sharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");


        alert = new AlertDialog.Builder(this); //membuat alert dialog baru bernama alert

        //menginisialisasi shared preference digunakan untuk penyimpanan menggunakan bentuk tipe data berpasangan
        SharedPreferences sharedP = getApplicationContext().getSharedPreferences("Preferences", 0);
        sharedpref = sharedP.edit();
        colorid = sharedP.getInt("Colourground", R.color.white);

        warna = findViewById(R.id.shapecolor);

        //warna diset ketika dipilih
        warna.setText(getShapeColor(colorid));
    }

    //apabila tombol back di tekan
    @Override
    public void onBackPressed() {
        //intent menuju ke MainActivity
        Intent intent = new Intent(Settings.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //method untuk pilihan warna
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            //menjalankan method onbackpressed
            this.onBackPressed();
        }
        return true;
    }

    //warna digunakan ketika dipilih
    public String getShapeColor(int i){
        if (i == R.color.red){
            return "Red";
        }else if (i==R.color.green){
            return "Green";
        }else if (i==R.color.blue){
            return "Blue";
        }else{
            return "Default";
        }
    }

    //get ID dari warna yg akan digunakan
    public int getColorid(int i){
        if (i == R.color.red){
            return R.id.red;
        }else if (i == R.color.green){
            return R.id.green;
        }else if (i == R.color.blue){
            return R.id.blue;
        }else{
            return R.id.white;
        }
    }

    public void pilihWarna(View view) {
        //menampilkan tulisan Shape Color di dialog
        alert.setTitle("Shape Color");
        //view baru
        View v = getLayoutInflater().inflate(R.layout.setting_color, null);
        //set view yg telah dibuat
        alert.setView(v);

        //radio btn diakses
        final RadioGroup RGR = v.findViewById(R.id.radio_color);

        RGR.check(getColorid(colorid));

        //ketika OK ditekan akan menjalankan method berikut
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //mendapatkan ID ketika di pilih
                int a = RGR.getCheckedRadioButtonId();
                switch (a){
                    case R.id.red:
                        colorid = R.color.red;
                        break;
                    case R.id.green:
                        colorid = R.color.green;
                        break;
                    case R.id.blue:
                        colorid = R.color.blue;
                        break;
                    case R.id.white:
                        colorid = R.color.white;
                        break;
                }
                //warna diset ektika dipilih, diletakkan ke shared pref. dan melakukan commit
                warna.setText(getShapeColor(colorid));
                sharedpref.putInt("Colourground", colorid);
                sharedpref.commit();
            }
        });

        //ketika pilih Cancel akan menjalankan method berikut
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        //alert dialog ditampilkan
        alert.create().show();
    }
}
