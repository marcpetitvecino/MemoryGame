package cat.tecnocampus.mobileapps.practica2.polrios.guillemteodoro;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

public class GameViewHolder extends RecyclerView.ViewHolder {
    private final ImageView hiddenCard;
    private final ImageView shownCard;

    public GameViewHolder(@NonNull View itemView, View.OnClickListener cardOnClickListener) {
        super(itemView);
        hiddenCard = itemView.findViewById(R.id.card_hidden);
        shownCard = itemView.findViewById(R.id.card_shown);
        itemView.setOnClickListener(cardOnClickListener);
    }

    public void bind(Card card) {
        itemView.setTag(card);
    }


    public ImageView getHiddenCard() {
        return hiddenCard;
    }

    public ImageView getShownCard() {
        return shownCard;
    }
}
