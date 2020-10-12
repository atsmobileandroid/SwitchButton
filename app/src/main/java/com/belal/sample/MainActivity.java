package com.belal.sample;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.belal.switchbutton.OnSwitchControlChanged;
import com.belal.switchbutton.SwitchControl;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ((SwitchControl) findViewById(R.id.switchButton)).setOnSwitchControlChanged(new OnSwitchControlChanged() {
            @Override
            public void onChanged(int id) {
                Toast.makeText(MainActivity.this, "Button number: " + (id + 1) + " clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
