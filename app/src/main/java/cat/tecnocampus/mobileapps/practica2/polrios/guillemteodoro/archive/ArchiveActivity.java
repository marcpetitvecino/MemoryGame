package cat.tecnocampus.mobileapps.practica2.polrios.guillemteodoro.archive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.TextView;

import java.util.List;

import cat.tecnocampus.mobileapps.practica2.polrios.guillemteodoro.R;
import cat.tecnocampus.mobileapps.practica2.polrios.guillemteodoro.database.AppDatabase;
import cat.tecnocampus.mobileapps.practica2.polrios.guillemteodoro.database.Game;
import cat.tecnocampus.mobileapps.practica2.polrios.guillemteodoro.database.GameDao;

public class ArchiveActivity extends AppCompatActivity {

    private ArchiveAdapter adapter;
    private RecyclerView list;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        setTitle("Game history");
        initViews();
        db = Room.databaseBuilder(this, AppDatabase.class, "database").allowMainThreadQueries().build();
        List<Game> gameList = db.gameDao().getAll();
        adapter = new ArchiveAdapter(gameList);
        list.setAdapter(adapter);
    }

    private void initViews() {
        list = findViewById(R.id.archive_list);
    }
}