package ke.com.eduhmik.ghosty.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Stories {




    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "message")
    private  String message;

    @ColumnInfo(name = "time")
    private Long time;

    public Stories(String title, String message, Long time) {
        this.title = title;
        this.message = message;
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public Long getTime() {
        return time;
    }
    public int getId() {
        return id;
    }
}
