package com.example.setvalue;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private  DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        // trường hợp 1
        mDatabase.child("Hoten").setValue("Trần Chức Thiện");

        //trường hợp 2
        SinhVien sv = new SinhVien("Thiện", "HCM");
        mDatabase.child("SinhVien").setValue(sv);

        //Trường hợp 3 - set value với Map <String,Object>
        Map<String,Integer> myMap = new HashMap<String,Integer>();
        myMap.put("XeMay",2);
        mDatabase.child("PhuongTien").setValue(myMap);

        //Dùng push
        SinhVien sv1 = new SinhVien("Vãi chưởng", "Phú Điềm");
        mDatabase.child("Hocvien").push().setValue(sv1);

        //bắt sự kiện hoàn thành khi setvalue
        mDatabase.child("Chucthien").setValue("hehe", new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error == null)
                {
                    Toast.makeText(MainActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "FAIL", Toast.LENGTH_SHORT).show();
                }
            }
        });






    }
}