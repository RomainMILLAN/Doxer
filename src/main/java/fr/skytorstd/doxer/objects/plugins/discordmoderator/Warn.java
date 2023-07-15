package fr.skytorstd.doxer.objects.plugins.discordmoderator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Warn {

    private String id;
    private String user_id;
    private String name;
    private String staff_id;
    private String created_at;

    public Warn(String id, String user_id, String name, String staff_id) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.staff_id = staff_id;
        this.created_at = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    public Warn(String id, String user_id, String name, String staff_id, String created_at) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.staff_id = staff_id;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public String getCreated_at() {
        return created_at;
    }
}
