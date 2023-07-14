package fr.skytorstd.doxer.manager.database.plugins.discordmoderator;

import fr.skytorstd.doxer.manager.database.DatabaseConnection;
import net.dv8tion.jda.api.entities.Member;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscordModeratorWarnsDatabase {

    final private static String table_name = "discord_moderator_wanrs";

    public int memberCountWarns(Member member) {
        final String sql = String.format("SELECT COUNT(*) as warns FROM %s WHERE user_id=%s", table_name, member.getId());

        try {
            ResultSet resultatSQL = DatabaseConnection.getInstance().getStatement().executeQuery(sql);

            while(resultatSQL.next()){
                return resultatSQL.getInt("warns");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

}
