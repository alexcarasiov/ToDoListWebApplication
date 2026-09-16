package org.example.services;

import org.example.controller.DatabaseConnector;
import org.example.dao.RecordDao;
import org.example.entity.Record;
import org.example.entity.RecordPriority;
import org.example.entity.RecordStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    private final RecordDao recordDao;
    private final DatabaseConnector databaseConnector;

    public RecordService(RecordDao recordDao,  DatabaseConnector databaseConnector) {
        this.recordDao = recordDao;
        this.databaseConnector = databaseConnector;
    }

    public List<Record> findAllRecords() {
        return recordDao.getRecords();
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
