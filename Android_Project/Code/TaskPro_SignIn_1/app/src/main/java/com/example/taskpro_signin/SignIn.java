package com.example.taskpro_signin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    //Khởi tạo reference cho firebase
    DatabaseReference databaseReference =
            FirebaseDatabase.getInstance().getReferenceFromUrl("https://team-b52e0-default-rtdb.firebaseio.com/");
    //Đối với mỗi project firebase trên các thiết bị khác nhau sẽ cần phải đổi link URL này
    TextView SignUpText;
    EditText PhoneSI, PasswordSI;
    Button btnSI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        SignUpText = (TextView) findViewById(R.id.SignUpText);
        btnSI = (Button) findViewById(R.id.btnSI);
        PhoneSI= findViewById(R.id.PhoneSI);
        PasswordSI= findViewById(R.id.PasswordSI);

        // Chuyển qua màn hình đăng ký
        SignUpText.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, SignUp.class));
                finish();
            }
        });


        // Đăng nhập
        btnSI.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String phoneSI = PhoneSI.getText().toString();
                String passSI = PasswordSI.getText().toString();

                //Kiểm tra nhập thông tin đầy đủ
                if (phoneSI.isEmpty() || passSI.isEmpty()) {
                    Toast.makeText(SignIn.this, "Please enter full your information", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener(){
                        //Kiểm tra sự tồn tại của tài khoản
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(phoneSI)){
                                //Nếu tồn tại, kiểm tra mật khẩu
                                String getPass = snapshot.child(phoneSI).child("Password").getValue(String.class);
                                if(getPass.equals(passSI)) {
                                    startActivity(new Intent(SignIn.this, Success.class));
                                    finish();
                                }
                                // nếu sai mật khẩu
                                else {
                                    Toast.makeText(SignIn.this,
                                            "Please check your information",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                            // nếu không có tài khoản
                            else{
                                Toast.makeText(SignIn.this, "Account not found", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
}