package org.example.entity;

import java.util.concurrent.atomic.AtomicInteger;

public class Record {
    private static final AtomicInteger counterSequence = new AtomicInteger(0);
    private final int id;
    private final String title;
    private RecordStatus status;
    private RecordPriority priority;

    public Record(String title, RecordStatus status, RecordPriority priority) {
        this.id = counterSequence.getAndIncrement();
        this.title = title;
        this.status = status;
        this.priority = priority;
    }

    public Record(String title, RecordPriority priority) {
        this.id = counterSequence.getAndIncrement();
        this.title = title;
        this.status = RecordStatus.ACTIVE;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
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
