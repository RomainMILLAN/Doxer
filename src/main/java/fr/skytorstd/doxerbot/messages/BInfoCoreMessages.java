package fr.skytorstd.doxerbot.messages;

public enum BInfoCoreMessages {
    DESCRIPTION("Plugin du serveur BUT Informatique"),
    DESCRIPTION_TUTORAT_COMMAND("Permet de crée une inscription au tutorat"),
    DESCRIPTION_TUTORAT_DATE_ARGUMENT("Permet d'inscrire la date du tutorat"),
    DESCRIPTION_TUTORAT_HEURE_ARGUMENT("Permet d'inscrire l'heure du tutorat"),
    INSCRIPTION_TUTORAT_CREATE("Inscription au tutorat crée"),

    DESCRIPTION_EDT_COMMAND("Permet d'utiliser le plugin d'emploi du temp"),
    DESCRIPTION_EDT_ACTION_ARGUMENT("Permet de définir l'action à effectué"),
    DESCRIPTION_EDT_GROUPE_ARGUMENT("Permet de définir le groupe à voir"),
    DESCRIPTION_EDT_DATE_ARGUMENT("Permet de définir la date du cour"),
    DESCRIPTION_EDT_INFO_ARGUMENT("Permet de définir l'information à modifier"),
    DESCRIPTION_EDT_ID_ARGUMENT("Permet de définir l'id du cour à regarder"),
    NO_GROUP_SELECT("Aucun groupe sélectionner et aucun groupe dans les rôles"),
    NONE_COUR_WITH_ID("Aucun cour avec cette identifiant"),
    EDT_DOING_REFRSH("Emploi du temps en cours de rafraichissement"),
    EDT_REFRESH("Emploi du temp refresh"),
    NO_COUR_TO_DATE("Aucun cour pour cette journée"),
    SHOW_COUR_LIST("Affichage liste des cours"),
    SHOW_COUR("Affichage de cour"),
    NO_INFORMATION_ARGUMENT("Aucune information rentré en parametre"),
    NO_ID_ARGUMENT("Aucun identifiant renseigner"),
    INFORMATION_UPDATE("Information du cour mise à jour");

    private String message;

    BInfoCoreMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
