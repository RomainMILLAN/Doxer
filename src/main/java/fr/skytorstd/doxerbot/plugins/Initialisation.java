package fr.skytorstd.doxerbot.plugins;

import fr.skytorstd.doxerbot.App;
import fr.skytorstd.doxerbot.databases.ConfigurationDoxerDatabase;
import fr.skytorstd.doxerbot.databases.ConfigurationPluginsDatabase;
import fr.skytorstd.doxerbot.embedCrafter.EmbedCrafter;
import fr.skytorstd.doxerbot.embedCrafter.ErrorCrafter;
import fr.skytorstd.doxerbot.embedCrafter.SetupCrafter;
import fr.skytorstd.doxerbot.manager.Logger;
import fr.skytorstd.doxerbot.messages.SetupMessages;
import fr.skytorstd.doxerbot.states.PluginName;
import fr.skytorstd.doxerbot.states.QueueAfterTimes;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.TimeUnit;

public class Initialisation extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if(e.getMessage().getContentRaw().startsWith("!init")){
            e.getMessage().delete().queue();
            String[] args = e.getMessage().getContentRaw().split(" ");

            if(args.length == 2){
                String idSudo = args[1];

                if(!ConfigurationDoxerDatabase.idGuildIsInConfiguration(e.getGuild().getId())){
                    ConfigurationDoxerDatabase.initBot(e.getGuild().getId(), idSudo);
                    ConfigurationPluginsDatabase.initPluginConfig(e.getGuild().getId());
                    App.updateSlashCommands();

                    e.getMessage().getChannel().sendMessageEmbeds(EmbedCrafter.embedCraftWithDescriptionOnly(":white_check_mark: " + SetupMessages.INIT_OK.getMessages())).queue((m) -> m.delete().queueAfter(QueueAfterTimes.SUCCESS_TIME.getQueueAfterTime(), TimeUnit.SECONDS));
                    Logger.getInstance().toLogOnConsoleAndFileOnly(PluginName.SETUP.getMessage(), SetupMessages.INIT_OK.getMessages(), e.getGuild(), e.getMember());
                }else {
                    e.getMessage().getChannel().sendMessageEmbeds(ErrorCrafter.errorEmbedCrafterWithDescription(":x: " + SetupMessages.BOT_ALREADY_INIT.getMessages())).queue((m) -> m.delete().queueAfter(QueueAfterTimes.ERROR_TIME.getQueueAfterTime(), TimeUnit.SECONDS));
                    Logger.getInstance().toLogOnConsoleAndFileOnly(PluginName.SETUP.getMessage(), SetupMessages.BOT_ALREADY_INIT.getMessages(), e.getGuild(), e.getMember());
                }
            }else {
                e.getMessage().getChannel().sendMessageEmbeds(SetupCrafter.initSudoId()).queue((m) -> m.delete().queueAfter(QueueAfterTimes.ERROR_TIME.getQueueAfterTime(), TimeUnit.SECONDS));
                Logger.getInstance().toLogOnConsoleAndFileOnly(PluginName.SETUP.getMessage(), "L'utilisateur n'as pas indiquer l'identifiant du rôle sudo", e.getGuild(), e.getMember());
            }
        }
    }
}
