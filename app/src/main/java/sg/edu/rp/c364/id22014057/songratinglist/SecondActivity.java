package sg.edu.rp.c364.id22014057.songratinglist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ListView lvSongs;
    ArrayList<Song> songs;
    ArrayAdapter<Song> aaSongs;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        lvSongs = findViewById(R.id.lv);

        songs = new ArrayList<>();
        aaSongs = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songs);
        lvSongs.setAdapter(aaSongs);

        db = new DBHelper(SecondActivity.this);
        songs.clear();
        songs.addAll(db.getSong());
        aaSongs.notifyDataSetChanged();

        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song data = songs.get(position);
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("data", String.valueOf(data));
                startActivity(intent);
            }
        });
    }
}