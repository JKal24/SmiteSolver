package com.astro.SmiteSolver.service;

import com.astro.SmiteSolver.config.utils;
import com.astro.SmiteSolver.entity.auxillary.BaseItemName;
import com.astro.SmiteSolver.entity.auxillary.UpdateData;
import com.astro.SmiteSolver.repository.GodNameRepository;
import com.astro.SmiteSolver.repository.ItemNameRepository;
import com.astro.SmiteSolver.repository.UpdateRepository;
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

    /**
     * @param date this is the date of when the data was updated
     * @param versionID this is the version which was recorded on the day the update went live,
     * The versionID is not the version for the date that was updated.
     */

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
        return updateRepository.findAll().stream().noneMatch(updateData -> updateData.getVersion().equals(versionID));
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
