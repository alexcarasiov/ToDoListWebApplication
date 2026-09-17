package org.example.services;

import org.example.controller.DatabaseConnector;
import org.example.dao.RecordDao;
import org.example.entity.Record;
import org.example.entity.RecordPriority;
import org.example.entity.RecordStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordService {
    private final RecordDao recordDao;
    private final DatabaseConnector databaseConnector;

    public RecordService(RecordDao recordDao,  DatabaseConnector databaseConnector) {
        this.recordDao = recordDao;
        this.databaseConnector = databaseConnector;
    }

    public List<Record> findAllRecords(String filterMode) {
        List<Record> recordList = recordDao.getRecords();
        if (filterMode == null || filterMode.isBlank()) {
            return recordList;
        }

        List<String> allowedFilterModes = Arrays.stream(RecordStatus.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        if (allowedFilterModes.contains(filterMode.toUpperCase())) {
            return recordList.stream()
                    .filter(record -> record.getStatus().name().equals(filterMode.toUpperCase()))
                    .collect(Collectors.toList());
        } else  {
            return recordList;
        }
    }

    public List<Record> findAllRecordsByStatus(RecordStatus status) {
        return databaseConnector.executeSQL("Select * from records where status = "+ status.name());
    }

    public void saveRecord(String titel, RecordPriority priority) {
        recordDao.saveRecord(new Record(titel, priority));
    }

    public void updateRecordStatus(int recordId, RecordStatus status) {
        recordDao.updateRecordStatus(recordId, status);
    }

    public void removeRecord(int recordId) {
        recordDao.removeRecord(recordId);
    }

}
