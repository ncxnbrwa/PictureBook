package com.example.picturebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TestRecyclerviewActivity extends AppCompatActivity {
    private RecyclerView list;
    private List<String> stringList = new ArrayList<>();
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 0x123) {
                list.smoothScrollBy(0, 300);
//                list.scrollBy(0, 300);
            } else if (msg.what == 0x124) {
                list.smoothScrollBy(0, -100);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_recyclerview);
        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i < 1000; i++) {
            stringList.add("第" + i + "条数据");
        }
        list.setAdapter(new TestAdapter());

        mHandler.sendEmptyMessageDelayed(0x123, 1000);
        mHandler.sendEmptyMessageDelayed(0x124, 2000);

    }

    class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {

        @NonNull
        @Override
        public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new TestViewHolder(LayoutInflater.from(TestRecyclerviewActivity.this)
                    .inflate(R.layout.test_list_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
            TextView textView = holder.itemView.findViewById(R.id.item_text);
            textView.setText(stringList.get(position));
        }

        @Override
        public int getItemCount() {
            return stringList.size();
        }

        class TestViewHolder extends RecyclerView.ViewHolder {

            public TestViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
}