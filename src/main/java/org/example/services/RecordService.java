package org.example.services;

import org.example.controller.DatabaseConnector;
import org.example.dao.RecordDao;
import org.example.entity.Record;
import org.example.entity.RecordPriority;
import org.example.entity.RecordStatus;
import org.example.entity.dto.RecordsContainerDto;
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

    public RecordsContainerDto findAllRecords(String filterMode) {
        List<Record> recordList = recordDao.getRecords();
        int numberOfDoneRecords = (int) recordList.stream().filter(record -> record.getStatus() == RecordStatus.DONE).count();
        int numberOfActiveRecords = (int) recordList.stream().filter(record -> record.getStatus() == RecordStatus.ACTIVE).count();

        if (filterMode == null || filterMode.isBlank()) {
            return new RecordsContainerDto(recordList, numberOfActiveRecords, numberOfDoneRecords);
        }

        List<String> allowedFilterModes = Arrays.stream(RecordStatus.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        if (allowedFilterModes.contains(filterMode.toUpperCase())) {
            List<Record> filteredList = recordList.stream()
                    .filter(record -> record.getStatus().name().equals(filterMode.toUpperCase()))
                    .collect(Collectors.toList());
            return new RecordsContainerDto(filteredList, numberOfActiveRecords, numberOfDoneRecords);
        } else  {
            return new RecordsContainerDto(recordList, numberOfActiveRecords, numberOfDoneRecords);
        }
    }

    public List<Record> findAllRecordsByStatus(RecordStatus status) {
        return databaseConnector.executeSQL("Select * from records where status = "+ status.name());
    }

    public void saveRecord(String title, RecordPriority priority) {
        recordDao.saveRecord(new Record(title, priority));
    }

    public void updateRecordStatus(int recordId, RecordStatus status) {
        recordDao.updateRecordStatus(recordId, status);
    }

    public void removeRecord(int recordId) {
        recordDao.removeRecord(recordId);
    }

}
