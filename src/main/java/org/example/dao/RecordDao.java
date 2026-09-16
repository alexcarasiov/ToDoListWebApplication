package org.example.dao;

import org.example.entity.Record;
import org.example.entity.RecordPriority;
import org.example.entity.RecordStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class RecordDao {
    private final List<Record> records = new ArrayList<>(
            Arrays.asList(
                    new Record("Teka a shower", RecordStatus.ACTIVE, RecordPriority.LOW),
                    new Record("Buy flowers", RecordStatus.DONE, RecordPriority.MEDIUM),
                    new Record("Go to the gym", RecordStatus.DONE, RecordPriority.HIGH)
            )
    );

    public List<Record> getRecords() {
        return new ArrayList<>(records);
    }

    public void saveRecord(Record record) {
        records.add(record);
    }

    public void updateRecordStatus(int recordId, RecordStatus status) {
        for (Record record : records) {
            if (record.getId() == recordId) {
                record.setStatus(status);
                break;
            }
        }
    }

    public void removeRecord(int id) {
        records.removeIf(r -> r.getId() == id);
    }

}
