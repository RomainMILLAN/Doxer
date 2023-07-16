package fr.skytorstd.doxer.commands.plugins;

import fr.skytorstd.doxer.App;
import fr.skytorstd.doxer.manager.Sentry;
import fr.skytorstd.doxer.manager.embedCrafter.plugins.PollExclamerCrafter;
import fr.skytorstd.doxer.objects.Plugin;
import fr.skytorstd.doxer.objects.pluginSlashInterface;
import fr.skytorstd.doxer.states.LogState;
import fr.skytorstd.doxer.states.QueueAfterTimes;
import fr.skytorstd.doxer.states.messages.plugin.PollExclamerMessages;
import fr.skytorstd.doxer.states.plugins.DiscordSecurityStates;
import fr.skytorstd.doxer.states.plugins.PollExclamerStates;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class pollExclamer extends pluginSlashInterface {

    public pollExclamer() {
        initPlugin();
    }

    @Override
    public void initPlugin() {
        App.addPlugin(
                new Plugin(
                        PollExclamerStates.PLUGIN_NAME.getState(),
                        PollExclamerMessages.PLUGIN_DESCRIPTION.getMessage(),
                        new ArrayList<String>() {
                            {
                                add(PollExclamerMessages.PLUGIN_COMMAND_POLL.getMessage());
                            }
                        }
                )
        );
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if(event.getName().equalsIgnoreCase(PollExclamerStates.PLUGIN_COMMAND_POLL_PREFIX.getState())){

            String question = event.getOption(
                    PollExclamerStates.PLUGIN_OPTION_POLL_QUESTION_NAME.getState()
            ).getAsString();
            String reponses = event.getOption(
                    PollExclamerStates.PLUGIN_OPTION_POLL_ANSWER_NAME.getState()
            ).getAsString();
            String reponsesTab[] = reponses.split(" ");

            event.getChannel().sendMessageEmbeds(
                    PollExclamerCrafter.craftPollEmbed(question, event.getMember()).build()
            ).queue(message -> {

                for(String emojiString : reponsesTab){
                    message.addReaction(Emoji.fromFormatted(emojiString)).queue();
                }

            });

            event.replyEmbeds(
                    PollExclamerCrafter.craftSuccessPollCreatedEmbed().build()
            ).setEphemeral(true)
                    .queue(message -> {
                        message.deleteOriginal().queueAfter(
                                QueueAfterTimes.ERROR_TIME.getQueueAfterTime(),
                                TimeUnit.SECONDS
                        );
                    });
            Sentry.getInstance().toLog(
                    PollExclamerStates.PLUGIN_NAME.getState(),
                    String.format(
                            PollExclamerMessages.SENTRY_POLL_CREATED.getMessage(),
                            question
                    ),
                    event.getCommandString(),
                    LogState.SUCCESSFUL,
                    event.getMember(),
                    event.getGuild()
            );
        }

    }
}
