package ke.co.ziqi.ghosty.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ke.co.ziqi.ghosty.models.Stories;

@Database(entities = {Stories.class}, version = 1 ,exportSchema = false)
public abstract class GhostyDatabase extends RoomDatabase {

    public abstract GhostyDao dao();

    private static GhostyDatabase INSTANCE;

    public  static GhostyDatabase getData(final Context context){
        if (INSTANCE == null){
            synchronized (GhostyDatabase.class){
                if (INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            GhostyDatabase.class, "ghosty_stories")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    public  static void nukeInstance(){
        INSTANCE = null;
    }
}
