package se.kth.id1212.tudd.project.api.reader.model;

/**
 *
 * @author udde
 */
public class Match {

    private String match_id;
    private int player_slot;
    private boolean radiant_win;
    private int duration;
    private int game_mode;
    private int lobby_type;
    private int hero_id;
    private int start_time;
    private int version;
    private int kills;
    private int assists;
    private int skill;
    private int leaver_status;
    private int party_size;

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public int getDuration() {
        return duration;
    }
}
