package cat.tecnocampus.mobileapps.practica2.polrios.guillemteodoro.archive;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cat.tecnocampus.mobileapps.practica2.polrios.guillemteodoro.R;

public class ArchiveViewHolder extends RecyclerView.ViewHolder {
    private final TextView nameTV;
    private final TextView scoreTV;
    private final TextView pairsTV;

    public ArchiveViewHolder(@NonNull View itemView) {
        super(itemView);
        this.nameTV = itemView.findViewById(R.id.historyUserName);
        this.scoreTV = itemView.findViewById(R.id.historyScore);
        this.pairsTV = itemView.findViewById(R.id.historyPairNumber);
    }

    public TextView getNameTV() {
        return nameTV;
    }

    public TextView getScoreTV() {
        return scoreTV;
    }

    public TextView getPairsTV() {
        return pairsTV;
    }
}
