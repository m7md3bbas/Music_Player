package com.example.musicplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password,repassword;
    Button btnsignup,btnlogin;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    username = findViewById(R.id.username);
    password = findViewById(R.id.password);
    repassword = findViewById(R.id.repassword);
    btnlogin = findViewById(R.id.btnlogin);
    btnsignup = findViewById(R.id.btnsignup);

    mydb = new DBHelper(this);

    btnsignup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            String repass = repassword.getText().toString();
            if(user.equals("") || pass.equals("") ||repass.equals(""))
            {
                Toast.makeText(MainActivity.this, "fill all field", Toast.LENGTH_SHORT).show();
            }else
            {
                if(pass.equals(repass))
                {
                   Boolean usercheckresult = mydb.checkusers(user);
                   if(usercheckresult == false)
                   {
                    Boolean regResult = mydb.insertData(user,pass);
                     if(regResult == true)
                    {
                        Toast.makeText(MainActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(intent);
                    }
                     else
                    {
                        Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                   }
                   else
                   {
                       Toast.makeText(MainActivity.this, "User already used", Toast.LENGTH_SHORT).show();
                   }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "password not matching", Toast.LENGTH_SHORT).show();
                }

            }
        }
    });
    btnlogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        }
    });

    }
}