package sg.edu.rp.c364.id22014057.songratinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnShowList;
    EditText etSongTitle, etSinger, etYear;
    RadioGroup rg;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    ArrayList<String> alSongs;
    ArrayList<String> songs;

    DBHelper db = new DBHelper(MainActivity.this);
    int star;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);
        etSongTitle = findViewById(R.id.etSongTitle);
        etSinger = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rg = findViewById(R.id.rg);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb1) {
                    star = 1;
                } else if (checkedId == R.id.rb2) {
                    star = 2;
                } else if (checkedId == R.id.rb3) {
                    star = 3;
                } else if (checkedId == R.id.rb4) {
                    star = 4;
                } else if (checkedId == R.id.rb5) {
                    star = 5;
                }

                btnInsert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Create the DBHelper object, passing in the
                        // activity's Context
                        // Insert a task
                        db.insertSong(etSongTitle.getText().toString(), etSinger.getText().toString(), Integer.parseInt(etYear.getText().toString()), star);

                    }
                });

                btnShowList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<String> data = db.getAllSongs();
                        songs = db.song();
                        db.close();

                        for (int i = 0; i < data.size(); i++) {
                            String lvTxt = songs.get(i).toString();
                            alSongs.add(lvTxt);
                        }
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putStringArrayListExtra("songs", alSongs);
                        startActivity(intent);

                        alSongs = new ArrayList<>();
                    }
                });

            }
        });
    }
}