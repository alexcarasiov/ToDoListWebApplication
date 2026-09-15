package org.example.entity;

public class Record {
    private static int counterSequence = 0;
    private final int id;
    private final String titel;
    private RecordStatus status;

    public Record(String titel, RecordStatus status) {
        this.id = counterSequence++;
        this.titel = titel;
        this.status = status;
    }

    public Record(String titel) {
        this.id = counterSequence++;
        this.titel = titel;
        this.status = RecordStatus.ACTIVE;
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


}
