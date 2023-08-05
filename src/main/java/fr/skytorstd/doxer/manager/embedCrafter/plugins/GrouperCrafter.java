package fr.skytorstd.doxer.manager.embedCrafter.plugins;

import fr.skytorstd.doxer.manager.embedCrafter.EmbedCrafterBase;
import fr.skytorstd.doxer.states.messages.IconMessages;
import fr.skytorstd.doxer.states.messages.plugin.GrouperMessages;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class GrouperCrafter extends EmbedCrafterBase {

    private static EmbedBuilder craftGrouperBase() {
        EmbedBuilder embed = craftEmbedBase();

        embed
                .setColor(Color.ORANGE);

        return embed;
    }

    public static EmbedBuilder craftSuccessCreateGroup(String name){
        EmbedBuilder embed = craftGrouperBase();

        embed
                .setDescription(
                        IconMessages.SUCCESS.getIcon() + " " +
                        String.format(
                                GrouperMessages.GROUP_CREATED.getMessage(),
                                name
                        )
                )
                .setColor(Color.GREEN);

        return embed;
    }

}
