package fr.skytorstd.doxer.objects;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public abstract class commandSlashInterface extends ListenerAdapter {

    public abstract void onSlashCommandInteraction(SlashCommandInteractionEvent event);

}
