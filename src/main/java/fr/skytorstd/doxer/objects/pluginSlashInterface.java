package fr.skytorstd.doxer.objects;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public abstract class pluginSlashInterface extends pluginInterface {
    public abstract void initPlugin();

    public abstract void onSlashCommandInteraction(SlashCommandInteractionEvent event);
}
