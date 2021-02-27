package com.astro.SmiteSolver.service;

import com.astro.SmiteSolver.config.utils;
import com.astro.SmiteSolver.entity.GodName;
import com.astro.SmiteSolver.entity.UpdateData;
import com.astro.SmiteSolver.repository.GodNameRepository;
import com.astro.SmiteSolver.repository.UpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UpdateService {

    public static final int DATA_DELETION_DAY_LIMIT = 30;

    @Autowired
    private UpdateRepository updateRepository;

    @Autowired
    private GodNameRepository godNameRepository;

    public UpdateData getUpdateData(LocalDate date) {
        Optional<UpdateData> data = updateRepository.findById(date);
        return data.orElse(null);
    }

    public void registerGod(Integer godID, String godName) {
        godNameRepository.save(new GodName(godID, godName));
    }

    public void addUpdate(LocalDate date, Double versionID) {
        UpdateData data = new UpdateData();
        data.setDate(date);
        data.setVersion(versionID);
        updateRepository.save(data);
    }

    public LocalDate getUpdatableDate() {
        LocalDate date = LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC"));

        for (int parseDates = 1; parseDates <= DATA_DELETION_DAY_LIMIT; parseDates++) {
            LocalDate prevDate = date.minusDays(parseDates);
            if(updateRepository.findById(prevDate).isEmpty()) {
                return prevDate;
            }
        }

        return null;
    }

    public boolean hasBeenUpdatedToday() {
        LocalDate dateToday = utils.getComparableDate(0);
        return updateRepository.findById(dateToday).isPresent();
    }

    public boolean isUpdatableVersion(double versionID) {
        LocalDate dateToday = utils.getComparableDate(0);

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
        LocalDate comparableDate = utils.getComparableDate(DATA_DELETION_DAY_LIMIT);

        for (UpdateData data : updateRepository.findAll()) {
            if (comparableDate.isAfter(data.getDate())) {
                updateRepository.delete(data);
            }
        }
    }

    public int getDaysStored() {
        AtomicInteger size = new AtomicInteger(0);
        updateRepository.findAll().forEach(updateData -> size.getAndIncrement());
        return size.get();
    }

}
