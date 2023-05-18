package fr.skytorstd.doxer.manager;

public class Sentry {
    private static Sentry INSTANCE = null;
    private String fileName = "logs.txt";

    public Sentry() {}

    public static Sentry getInstance() {
        if(Sentry.INSTANCE == null)
            Sentry.INSTANCE = new Sentry();

        return INSTANCE;
    }



}
