package fr.skytorstd.doxer.manager.embedCrafter;

import fr.skytorstd.doxer.manager.DateHourFormatter;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class EmbedCrafterBase {

    protected static String thumbnailURL = "https://cdn.discordapp.com/avatars/1069935481418166344/793209a16162577dff8b608dc02cfa9b.webp?size=128";

    private static Color colorError = Color.RED;

    protected static String getFooterEmbed(){
        return "Le " + DateHourFormatter.getInstance().getDateTimeFormat() + " à " + DateHourFormatter.getInstance().getHourTimeFormat();
    }

    protected static EmbedBuilder craftEmbedBase() {
        EmbedBuilder embed = new EmbedBuilder();

        embed.setFooter(getFooterEmbed());

        return embed;
    }

}
