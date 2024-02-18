package com.mld.customcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ListView historyListView;
    private ArrayAdapter<String> historyAdapter;
Button clrhistbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        clrhistbtn=findViewById(R.id.clearhist);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        historyListView = findViewById(R.id.historyListView);
        historyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        // Receive history data passed from MainActivity
        ArrayList<String> historyItems = getIntent().getStringArrayListExtra("history");

        if (historyItems != null) {
            // Add history items to the adapter
            historyAdapter.addAll(historyItems);
        }

        // Setting adapter to the ListView
        historyListView.setAdapter(historyAdapter);

        // Handling item click
        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Getting selected expression and returning it to MainActivity
                String selectedExpression = historyAdapter.getItem(position);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("selected_expression", selectedExpression);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
        clrhistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clearing the history items in the adapter
                historyAdapter.clear();

                // Notifying the adapter that the data set has changed
                historyAdapter.notifyDataSetChanged();
                ArrayList<String> emptyHistory = new ArrayList<>();

                // Return the empty history to the main activity
                Intent resultIntent = new Intent();
                resultIntent.putStringArrayListExtra("history", emptyHistory);
                setResult(RESULT_OK, resultIntent);
                // Informing the user that the history has been cleared
                Toast.makeText(HistoryActivity.this, "History cleared", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
