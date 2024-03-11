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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    EditText UsernameSU, PasswordSU, PhoneSU, EmailSU;
    TextView SignInText;
    Button btnSU;

    //Khởi tạo reference cho firebase
    DatabaseReference databaseReference =
            FirebaseDatabase.getInstance().getReferenceFromUrl("https://team-b52e0-default-rtdb.firebaseio.com/");
    //Đối với mỗi project firebase trên các thiết bị khác nhau sẽ cần phải đổi link URL này

    FirebaseAuth rAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        UsernameSU = (EditText) findViewById(R.id.UsernameSU);
        PasswordSU = (EditText) findViewById(R.id.PasswordSU);
        PhoneSU = (EditText) findViewById(R.id.PhoneSU);
        EmailSU = (EditText) findViewById(R.id.EmailSU);
        SignInText = findViewById(R.id.SignInText);
        btnSU = (Button) findViewById(R.id.btnSU);
        rAuth = FirebaseAuth.getInstance();

        //Chuyển qua màn hình đăng nhập
        SignInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, SignIn.class));
                finish();
            }
        });

        //Đăng ký
        btnSU.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String us_nameSU = UsernameSU.getText().toString();
                String phoneSU = PhoneSU.getText().toString();
                String emailSU = EmailSU.getText().toString();
                String passwordSU = PasswordSU.getText().toString();

                //Kiểm tra nhập thông tin đầy đủ
                if (us_nameSU.isEmpty() || phoneSU.isEmpty() || emailSU.isEmpty() || passwordSU.isEmpty()) {
                    Toast.makeText(SignUp.this, "Please enter your information", Toast.LENGTH_SHORT).show();

                } else {
                    databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //Kiểm tra số điện thoại đã được đăng ký chưa
                            if(snapshot.hasChild(phoneSU)){
                                Toast.makeText(SignUp.this,
                                        "Your phone number is already registered",
                                        Toast.LENGTH_SHORT).show();
                            }
                            else {
                                //Sử dụng firebase Authentication để tạo user
                                CreateUser(emailSU, phoneSU);
                                //Gửi data tới Realtime Database
                                //Sử dụng phone làm thông tin đăng nhập chính để tránh tối thiểu việc trùng dữ liệu
                                databaseReference.child("Users").child(phoneSU).child("Username").setValue(us_nameSU);
                                databaseReference.child("Users").child(phoneSU).child("Email").setValue(emailSU);
                                databaseReference.child("Users").child(phoneSU).child("Password").setValue(passwordSU);
                                //Hiển thị thông báo đăng ký thành công
                                Toast.makeText(SignUp.this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();

                                //Đưa các ô dữ liệu về trạng thái trống
                                UsernameSU.setText("");
                                PhoneSU.setText("");
                                EmailSU.setText("");
                                PasswordSU.setText("");
                            }
                        }
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }

    private void CreateUser (String emailSU, String passwordSU){
        //Kiểm tra dữ liệu trùng
        rAuth.createUserWithEmailAndPassword(emailSU, passwordSU)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(SignUp.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(
                                                SignUp.this,
                                                "Registration failed " + task.getException().getMessage(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
    }
}