package fr.skytorstd.doxer.manager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DateHourFormatter {

    private static DateHourFormatter instance = null;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
    private DateTimeFormatter hourTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private DateTimeFormatter dateAndHourTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /**
     * Constructure de singleton
     */
    public DateHourFormatter() {}

    /**
     * Retourne l'instance et la crée si elle n'existe pas
     * @return
     */
    public static DateHourFormatter getInstance() {
        if(instance == null){
            instance = new DateHourFormatter();
        }

        return instance;
    }

    /**
     * Retourne la date actuelle
     * @return
     */
    public String getDateTimeFormat(){
        return dateTimeFormatter.format(LocalDateTime.now());
    }

    /**
     * Retourne la date et l'heure actuelle
     * @return
     */
    public String getDateAndHourTimeFormat(){
        return dateAndHourTimeFormatter.format(LocalDateTime.now());
    }

    /**
     * Retourne l'heure actuelle
     * @return
     */
    public String getHourTimeFormat(){
        return hourTimeFormatter.format(LocalDateTime.now());
    }

}
