package com.astro.SmiteSolver.service;

import com.astro.SmiteSolver.entity.UpdateData;
import com.astro.SmiteSolver.repository.UpdateRepository;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UpdateService {

    private final int DATA_DELETION_DAY_LIMIT = 30;

    @Autowired
    private UpdateRepository updateRepository;

    public UpdateData getUpdateData(LocalDate date) {
        Optional<UpdateData> data = updateRepository.findById(date);
        return data.orElse(null);
    }

    public void addUpdate(LocalDate date, Double versionID) {
        UpdateData data = new UpdateData();
        data.setUpdatedDate(date);
        data.setVersion(versionID);
        updateRepository.save(data);
    }

    public List<LocalDate> getUpdatableDates() {
        LocalDate date = LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC"));
        List<LocalDate> updatableDates = new ArrayList<>();

        for (int parseDates = 1; parseDates <= DATA_DELETION_DAY_LIMIT; parseDates++) {
            LocalDate prevDate = date.minusDays(parseDates);
            if(updateRepository.findById(prevDate).isEmpty()) {
                updatableDates.add(prevDate);
            } else {
                return updatableDates;
            }
        }
        return updatableDates;
    }

    public boolean hasBeenUpdatedToday() {
        LocalDate dateToday = getComparableDate(0);
        return updateRepository.findById(dateToday).isPresent();
    }

    public boolean isUpdatableDate(double versionID) {
        LocalDate dateToday = getComparableDate(0);

        for (int parseDates = 1; parseDates < DATA_DELETION_DAY_LIMIT; parseDates++) {
            LocalDate prevDay = dateToday.minusDays(parseDates);
            Optional<UpdateData> data = updateRepository.findById(prevDay);
            if (data.isPresent()) {
                return data.get().getVersion() == versionID;
            }
        }
        return false;
    }

    public void cleanUpdates() {
        LocalDate comparableDate = getComparableDate(DATA_DELETION_DAY_LIMIT);

        for (UpdateData data : updateRepository.findAll()) {
            if (comparableDate.isAfter(data.getUpdatedDate())) {
                updateRepository.delete(data);
            }
        }
    }

    public int getDaysStored() {
        AtomicInteger size = new AtomicInteger(0);
        updateRepository.findAll().forEach(updateData -> size.getAndIncrement());
        return size.get();
    }

    private LocalDate getComparableDate(int daysBehind) {
        return LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC")).minusDays(daysBehind);
    }
}
