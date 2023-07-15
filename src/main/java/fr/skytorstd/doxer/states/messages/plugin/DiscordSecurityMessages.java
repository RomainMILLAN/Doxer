package fr.skytorstd.doxer.states.messages.plugin;

import fr.skytorstd.doxer.states.messages.IconMessages;
import fr.skytorstd.doxer.states.plugins.DiscordSecurityStates;

public enum DiscordSecurityMessages {

    PLUGIN_DESCRIPTION("Plugin permetant de filtrer les personnes qui rentre sur votre serveur discord"),

    PLUGIN_COMMAND_SECURITY("/security <default_group>"),
    PLUGIN_COMMAND_SECURITY_DESCRIPTION("Permet de désigner le groupe par défaut et visualiser le status de la sécurité"),
    PLUGIN_OPTION_SECURITY_DEFAULT_GROUP_DESCRIPTION("Désigne le groupe par défault si la sécurité n'est pas activé"),
    PLUGIN_COMMAND_CONFIRM("/confirm <utilisateur> <groupe>"),
    PLUGIN_COMMAND_CONFIRM_DESCRIPTION("Permet de confirmer un utilisateur quand la sécurité est activée sur votre serveur"),
    PLUGIN_OPTION_CONFIRM_USER_DESCRIPTION("Utilisateur à authentifier"),
    PLUGIN_OPTION_CONFIRM_GROUP_DESCRIPTION("Groupe à ajouté à l'utilisateur"),

    SECURITY_STATE("Le status de la sécurité est définie sur `%s`"),
    EMBED_TITLE(IconMessages.WRENCH.getIcon() + " **" + DiscordSecurityStates.PLUGIN_NAME.getState() + "**"),
    MESSAGE("MESSAGE");

    private final String message;

    DiscordSecurityMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
