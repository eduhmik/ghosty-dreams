package ke.co.ziqi.ghosty.models;

public class GhostModel {

    String title, message, time;

    public GhostModel(String title, String message, String time) {
        this.title = title;
        this.message = message;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }
}
