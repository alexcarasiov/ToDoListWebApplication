package org.example.entity;

public class Record {
    private static int counterSequence = 0;
    private final int id;
    private final String titel;
    private RecordStatus status;
    private RecordPriority priority;

    public Record(String titel, RecordStatus status, RecordPriority priority) {
        this.id = counterSequence++;
        this.titel = titel;
        this.status = status;
        this.priority = priority;
    }

    public Record(String titel, RecordPriority priority) {
        this.id = counterSequence++;
        this.titel = titel;
        this.status = RecordStatus.ACTIVE;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public RecordStatus getStatus() {
        return status;
    }

    public void setStatus(RecordStatus status) {
        this.status = status;
    }

    public RecordPriority getPriority() {
        return priority;
    }

    public void setPriority(RecordPriority priority) {
        this.priority = priority;
    }


}
