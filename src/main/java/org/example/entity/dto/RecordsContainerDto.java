package org.example.entity.dto;
import org.example.entity.Record;

import java.util.List;

public class RecordsContainerDto {
    private final List<Record> records;
    private final int numberOfActiveRecords;
    private final int numberOfDoneRecords;

    public RecordsContainerDto(List<Record> records, int numberOfActiveRecords, int numberOfDoneRecords) {
        this.records = records;
        this.numberOfDoneRecords = numberOfDoneRecords;
        this.numberOfActiveRecords = numberOfActiveRecords;
    }

    public List<Record> getRecords() {
        return records;
    }

    public int getNumberOfActiveRecords() {
        return numberOfActiveRecords;
    }

    public int getNumberOfDoneRecords() {
        return numberOfDoneRecords;
    }
}
