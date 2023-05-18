package com.example.musicplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username , password;
    Button btnLogin;
    TextView txt;
    DBHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.usernameform);
        password = findViewById(R.id.passwordform);
        btnLogin = findViewById(R.id.btnloginform);
        mydb = new DBHelper(this);
    btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("") || pass.equals("") ) {
                    Toast.makeText(LoginActivity.this, " Pls fill all field", Toast.LENGTH_SHORT).show();
                }else
                {
                  Boolean result = mydb.checkuserspassword(user,pass);
                  if(result == true)
                  {
                      Intent intent = new Intent(getApplicationContext(),CenterOfMuisc.class);
                      startActivity(intent);
                  }
                  else {
                      Toast.makeText(LoginActivity.this, "Invald!!" , Toast.LENGTH_SHORT).show();
                  }
                }

            }
        });
    }
}