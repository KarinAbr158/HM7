package com.example.hm7;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText edKey, edValue;
    Button btnAdd, btnClear;

    ArrayList<Integer> mine = new ArrayList<>();
    LinearLayout linear;
    HashMap<String, String> dict = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edKey = findViewById(R.id.etKey);
        edValue = findViewById(R.id.etValue);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);
        linear = findViewById(R.id.llButtons);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dict.put(edKey.getText().toString(), edValue.getText().toString());
                Button btn = new Button(MainActivity.this);
                btn.setText(edKey.getText().toString());
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,
                                dict.get(edKey.getText().toString()),
                                Toast.LENGTH_LONG).show();
                    }
                });
                linear.addView(btn);
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear.removeAllViews();

                // Get the keys from the dictionary and sort them alphabetically
                ArrayList<String> keys = new ArrayList<>(dict.keySet());
                keys.sort(String::compareTo);

                // Iterate over the sorted keys
                for (String key : keys) {
                    // Create a new button
                    Button btn = new Button(MainActivity.this);
                    btn.setText(key);  // Set button text to the key

                    // Set an OnClickListener to show the value associated with the key in the dictionary
                    final String keyFinal = key;  // Use a final variable to access inside the listener
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Show a Toast with the value corresponding to the clicked key
                            Toast.makeText(MainActivity.this,
                                    dict.get(keyFinal),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                    // Add the button to the layout
                    linear.addView(btn);
                }
            }
        });
    }
}