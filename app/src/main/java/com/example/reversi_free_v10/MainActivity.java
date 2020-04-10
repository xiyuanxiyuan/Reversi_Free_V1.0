package com.example.reversi_free_v10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button singlePlayer=(Button)findViewById(R.id.single_player);
        Button twoPlayer=(Button)findViewById(R.id.two_player);
        Button options=(Button)findViewById(R.id.options);
        Button statistics=(Button)findViewById(R.id.statistics);
        singlePlayer.setOnClickListener(this);
        twoPlayer.setOnClickListener(this);
        options.setOnClickListener(this);
        statistics.setOnClickListener(this);


    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.single_player:
                intent=new Intent(MainActivity.this,SinglePlayerActivity.class);
                startActivity(intent);
                break;
            case R.id.two_player:
                intent=new Intent(MainActivity.this,TwoPlayerActivity.class);
                startActivity(intent);
                break;
            case R.id.options:
                intent =new Intent(MainActivity.this,OptionsActivity.class);
                startActivity(intent);
                break;
            case R.id.statistics:
                intent =new Intent(MainActivity.this,StatisticsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }


    }
}
