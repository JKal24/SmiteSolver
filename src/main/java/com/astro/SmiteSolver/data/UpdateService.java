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

    @Autowired
    private UpdateRepository updateRepository;

    public void addUpdate(LocalDate date, Double version) {
        UpdateData data = new UpdateData();
        data.setUpdatedDate(date);
        data.setVersion(version);
        updateRepository.save(data);
    }

    public boolean isUpdatableDate() {
        Integer date = getDate(1);
        return updateRepository.findById(date).isEmpty();
    }

    public void cleanUpdates(int daysBehind) {
        Integer date = getDate(daysBehind);

        while (true) {
            if (updateRepository.findById(date).isPresent()) {
                updateRepository.delete(updateRepository.findById(date).get());
            } else {
                return;
            }

            date = getDate(++daysBehind);
        }
    }

    public int getDaysStored() {
        AtomicInteger size = new AtomicInteger(0);
        updateRepository.findAll().forEach(updateData -> size.getAndIncrement());
        return size.get();
    }

    private Integer getDate(int daysBehind) {
        LocalDate currentDate = LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC")).minusDays(daysBehind);
        return Integer.parseInt(currentDate.toString().replaceAll("[^\\d]", ""));
    }
}
