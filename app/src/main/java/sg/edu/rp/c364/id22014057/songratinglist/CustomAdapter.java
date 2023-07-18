package sg.edu.rp.c364.id22014057.songratinglist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Song> {
    private Context context;
    private int layoutResourceId;
    private ArrayList<Song> songList;

    public CustomAdapter(Context context, int layoutResourceId, ArrayList<Song> songList) {
        super(context, layoutResourceId, songList);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.songList = songList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View rowView = convertView;
        ViewHolder holder;

        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            rowView = inflater.inflate(layoutResourceId, parent, false);

            holder = new ViewHolder();
            holder.titleTextView = rowView.findViewById(R.id.tvSongTiltle);
            holder.yearTextView = rowView.findViewById(R.id.tvYear);
            holder.starTextView = rowView.findViewById(R.id.tvStar);
            holder.singerTextView = rowView.findViewById(R.id.tvSingers);


            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }


        Song currentSong = songList.get(position);

        holder.titleTextView.setText(currentSong.getTitle());
        holder.yearTextView.setText(String.valueOf(currentSong.getYear()));
        holder.starTextView.setText(currentSong.toString());
        holder.singerTextView.setText(currentSong.getSinger());

        return rowView;
    }

    private static class ViewHolder {
        TextView titleTextView;
        TextView yearTextView;
        TextView starTextView;
        TextView singerTextView;
    }
}
