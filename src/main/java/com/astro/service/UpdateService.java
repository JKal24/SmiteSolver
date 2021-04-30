package com.astro.service;

import com.astro.config.utils;
import com.astro.entity.BaseItemName;
import com.astro.entity.UpdateData;
import com.astro.repository.GodNameRepository;
import com.astro.repository.ItemNameRepository;
import com.astro.repository.UpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UpdateService {

    public static final int DATA_DELETION_DAY_LIMIT = 30;

    @Autowired
    private UpdateRepository updateRepository;

    @Autowired
    private GodNameRepository godNameRepository;

    @Autowired
    private ItemNameRepository itemNameRepository;

    public void addUpdate(LocalDate date, Double versionID) {
        this.addUpdate(new UpdateData(date, versionID));
    }

    public void addUpdate(UpdateData data) {
        updateRepository.save(data);
    }

    public void updateItem(BaseItemName baseItemName) {
        itemNameRepository.save(baseItemName);
    }

    public List<BaseItemName> getUpdatedItemList() {
        Iterable<BaseItemName> baseItemNames = itemNameRepository.findAll();
        List<BaseItemName> baseItemNameList = new ArrayList<>();
        baseItemNames.iterator().forEachRemaining(baseItemNameList::add);
        return baseItemNameList;
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

    public boolean hasBeenUpdatedOnDay(LocalDate date) {
        return updateRepository.findById(date).isPresent();
    }

    public boolean hasBeenUpdatedOnDay() {
        LocalDate dateToday = utils.getComparableDate(1);
        return this.hasBeenUpdatedOnDay(dateToday);
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

}
