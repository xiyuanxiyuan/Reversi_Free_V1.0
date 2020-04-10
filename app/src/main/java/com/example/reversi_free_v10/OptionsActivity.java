package com.example.reversi_free_v10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OptionsActivity extends AppCompatActivity implements View.OnClickListener{

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_layout);
        Button back=(Button)findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.back:
                intent =new Intent(OptionsActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
