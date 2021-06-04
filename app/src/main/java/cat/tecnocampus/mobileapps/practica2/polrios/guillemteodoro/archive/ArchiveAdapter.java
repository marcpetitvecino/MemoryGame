package cat.tecnocampus.mobileapps.practica2.polrios.guillemteodoro.archive;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.tecnocampus.mobileapps.practica2.polrios.guillemteodoro.R;
import cat.tecnocampus.mobileapps.practica2.polrios.guillemteodoro.database.Game;

public class ArchiveAdapter extends RecyclerView.Adapter<ArchiveViewHolder> {
    private final List<Game> games;

    public ArchiveAdapter(List<Game> games) {
        this.games = games;
    }

    @NonNull
    @Override
    public ArchiveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArchiveViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.history_cell, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArchiveViewHolder holder, int position) {
        holder.getNameTV().setText("Name: " + games.get(position).player);
        holder.getScoreTV().setText("Score: " + games.get(position).score);
        holder.getPairsTV().setText("Pairs: " + games.get(position).pairNumber);
    }

    @Override
    public int getItemCount() {
        return games.size();
    }
}
