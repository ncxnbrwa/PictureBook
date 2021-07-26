package com.example.picturebook.view.custom_study.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.picturebook.R;

public class HorizontalViewActivity extends AppCompatActivity {
    private ListView lvOne, lvTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_view);
        lvOne = findViewById(R.id.lv_one);
        lvTwo = findViewById(R.id.lv_two);
        String[] strs1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
        lvOne.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, strs1));
        String[] strs2 = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"};
        lvTwo.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, strs2));
    }
}