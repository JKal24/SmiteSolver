package com.astro.SmiteSolver.data;

import com.astro.SmiteSolver.object.UpdateData;
import com.astro.SmiteSolver.repository.UpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UpdateService {

    private final int DATA_DELETION_DAY_LIMIT = 30;

    @Autowired
    private UpdateRepository updateRepository;

    public void addUpdate(LocalDate date, Double version) {
        UpdateData data = new UpdateData();
        data.setUpdatedDate(date);
        data.setVersion(version);
        updateRepository.save(data);
    }

    public boolean isUpdatableDate() {
        LocalDate date = getComparableDate(1);
        return updateRepository.findById(date).isEmpty();
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
