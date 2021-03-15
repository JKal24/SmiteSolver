package com.astro.SmiteSolver.service;

import com.astro.SmiteSolver.config.utils;
import com.astro.SmiteSolver.entity.GodName;
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

    public Double getVersion(LocalDate date) {
        Optional<UpdateData> data = updateRepository.findById(date);
        return data.isPresent() ? data.get().getVersion() : 0;
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

    public LocalDate getVersionUpdateDate() {
        UpdateData updateData = getMostRecentUpdate();
        LocalDate updateDate;
        Double version;
        if (updateData != null) {
            updateDate = updateData.getDate();
            version = updateData.getVersion();
        } else {
            updateDate = utils.getComparableDate(0);
            version = 0.0;
        }

        for (UpdateData data : updateRepository.findAll()) {
            if (!data.getVersion().equals(version) && data.getDate().isAfter(updateDate)) {
                updateDate = data.getDate();
            }
        }
        return updateDate;
    }

    public UpdateData getMostRecentUpdate() {
        UpdateData data = null;

        for (UpdateData nextUpdate : updateRepository.findAll()) {
            if (data == null) {
                data = nextUpdate;
            } else if (nextUpdate.getDate().isBefore(data.getDate())) {
                data = nextUpdate;
            }
        }
        return data;
    }

    public boolean hasBeenUpdated() {
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
