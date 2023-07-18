package sg.edu.rp.c364.id22014057.songratinglist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import kotlinx.coroutines.channels.Send;

public class ThirdActivity extends AppCompatActivity {
    EditText et1, et2, et3;
    RadioGroup rg;
    RadioButton rd1, rd2, rd3, rd4, rd5;
    Button btnUpdate, btnDelete, btnCancel;
    DBHelper db;
    int star;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        rd1 = findViewById(R.id.rd1);
        rd2 = findViewById(R.id.rd2);
        rd3 = findViewById(R.id.rd3);
        rd4 = findViewById(R.id.rd4);
        rd5 = findViewById(R.id.rd5);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        db = new DBHelper(ThirdActivity.this);

        // Retrieve the song data from the intent
        Intent intent = getIntent();
        data = (Song) intent.getSerializableExtra("song");

        // Set the initial values in the EditText views and RadioGroup
        et1.setText(data.getTitle());
        et2.setText(data.getSinger());
        et3.setText(String.valueOf(data.getYear()));
        setRating(data.getStars());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = et1.getText().toString();
                String singer = et2.getText().toString();
                int year = Integer.parseInt(et3.getText().toString());

                // Update the data object with the new values
                data.setSong(title, singer, year, star);

                // Update the song in the database using the DBHelper
                db.updateSong(data);
                db.close();

                Toast.makeText(ThirdActivity.this, "Song updated successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteSong(data.getId());
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // Helper method to set the selected RadioButton based on the given rating
    private void setRating(int rating) {
        switch (rating) {
            case 1:
                rd1.setChecked(true);
                break;
            case 2:
                rd2.setChecked(true);
                break;
            case 3:
                rd3.setChecked(true);
                break;
            case 4:
                rd4.setChecked(true);
                break;
            case 5:
                rd5.setChecked(true);
                break;
        }
        star = rating; // Set the star variable to the selected rating
    }
}