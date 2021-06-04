package cat.tecnocampus.mobileapps.practica2.polrios.guillemteodoro.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Game {
    public Game(int score, String player, int pairNumber) {
        this.score = score;
        this.player = player;
        this.pairNumber = pairNumber;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "score")
    public int score;

    @ColumnInfo(name = "player")
    public String player;

    @ColumnInfo(name = "pairnumber")
    public int pairNumber;
}
