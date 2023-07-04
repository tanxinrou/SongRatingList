package sg.edu.rp.c364.id22014057.songratinglist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ListView lvSongs;
    ArrayList<Song> songs;
    ArrayList<String> alSongs;
    ArrayAdapter<String> aaSongs;
    DBHelper db = new DBHelper(SecondActivity.this);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        Intent intentSelected = getIntent();
        alSongs = intentSelected.getStringArrayListExtra("songs");
        lvSongs = findViewById(R.id.lv);

        aaSongs = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alSongs);
        lvSongs.setAdapter(aaSongs);

    }
}
