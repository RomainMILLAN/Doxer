package fr.skytorstd.doxer.states;

public enum QueueAfterTimes {
    ERROR_TIME(15),
    SUCCESS_TIME(30);

    private long queueAfterTime;

    QueueAfterTimes(long queueAfterTime) {
        this.queueAfterTime = queueAfterTime;
    }

    /**
     * Retourne le temps sélectionné
     * @return
     */
    public long getQueueAfterTime() {
        return queueAfterTime;
    }
}
