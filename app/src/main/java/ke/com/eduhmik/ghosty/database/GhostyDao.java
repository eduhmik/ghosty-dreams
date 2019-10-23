package ke.com.eduhmik.ghosty.database;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ke.com.eduhmik.ghosty.models.Stories;

@Dao
public interface GhostyDao {
    @Query("SELECT * FROM stories")
    List<Stories> getAll();

    @Insert
    void insertAll(List<Stories> stories);

    @Update
    void updateAll(Stories stories);

    @Delete
    void deleteAll(Stories stories);
}
