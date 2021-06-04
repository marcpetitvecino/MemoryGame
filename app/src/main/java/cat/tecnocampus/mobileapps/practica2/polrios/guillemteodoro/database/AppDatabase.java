package cat.tecnocampus.mobileapps.practica2.polrios.guillemteodoro.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Game.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GameDao gameDao();

}
