package com.example.match3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btn1);
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this, Match3Game.class
            );
            startActivity(intent);
        });
    }
}