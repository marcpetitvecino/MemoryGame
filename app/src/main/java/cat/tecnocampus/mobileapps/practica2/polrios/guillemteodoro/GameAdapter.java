package cat.tecnocampus.mobileapps.practica2.polrios.guillemteodoro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameViewHolder> {
    private final List<Card> cards;
    private final Context context;
    private final View.OnClickListener cardOnClickListener;

    public GameAdapter(List<Card> cards, Context context, View.OnClickListener cardOnClickListener) {
        this.cards = cards;
        this.context = context;
        this.cardOnClickListener = cardOnClickListener;
    }


    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GameViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_hidden, parent, false), cardOnClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        holder.getShownCard().setImageResource(cards.get(position).getImage());
        holder.bind(cards.get(position));
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
