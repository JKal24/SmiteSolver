package com.astro.SmiteSolver.service;

import com.astro.SmiteSolver.config.utils;
import com.astro.SmiteSolver.entity.UpdateData;
import com.astro.SmiteSolver.repository.GodNameRepository;
import com.astro.SmiteSolver.repository.UpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UpdateService {

    public static final int DATA_DELETION_DAY_LIMIT = 30;

    @Autowired
    private UpdateRepository updateRepository;

    @Autowired
    private GodNameRepository godNameRepository;

    public void addUpdate(LocalDate date, Double versionID) {
        this.addUpdate(new UpdateData(date, versionID));
    }

    public void addUpdate(UpdateData data) {
        updateRepository.save(data);
    }

    public LocalDate getVersionUpdateDate() {
        UpdateData updateData = getMostRecentUpdate();
        if (updateData != null) {
            return updateData.getDate();
        } else {
            return utils.getComparableDate(1);
        }
    }

    public UpdateData getMostRecentUpdate() {
        UpdateData data = null;

        for (UpdateData nextUpdate : updateRepository.findAll()) {
            if (data == null || nextUpdate.getDate().isBefore(data.getDate())) {
                data = nextUpdate;
            }
        }
        return data;
    }

    public boolean hasBeenUpdatedToday() {
        LocalDate dateToday = utils.getComparableDate(1);
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
        return true;
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
