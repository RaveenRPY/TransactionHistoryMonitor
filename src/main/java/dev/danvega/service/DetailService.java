package dev.danvega.service;

import dev.danvega.domain.Detail;
import dev.danvega.domain.User;
import dev.danvega.repository.DetailRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DetailService {

    private DetailRepository detailRepository;

    public DetailService(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    // Retrieve all details
    public Iterable<Detail> det() {
        return detailRepository.findAll();
    }

    // Save a list of details (transactional)
    @Transactional
    public void saveAllEntities(List<Detail> details) {
        for (Detail entity : details) {
            detailRepository.save(entity);
        }
    }

    // Find details by invoiceId
    @Transactional
    public List<Detail> findByInvoiceId(long invoiceId) {
        List<Detail> records = detailRepository.findAll();
        List<Detail> filtered = new ArrayList<>();

        if (records != null) {
            for (Detail record : records) {
                if (record.getInvoiceId() == invoiceId) {
                    filtered.add(record);
                }
            }
            return filtered;
        }
        return null;
    }

    // Update users with their total paid amounts
    public static List<User> updateUsersWithTotalPaidAmount(List<User> invoices, List<Detail> details) {
        Map<Long, Double> totalPaidAmounts = calculateTotalPaidAmount(invoices, details);

        for (User user : invoices) {
            long invoiceId = user.getId();
            if (totalPaidAmounts.containsKey(invoiceId)) {
                user.setTotalPaidAmount(totalPaidAmounts.get(invoiceId));
            } else {
                user.setTotalPaidAmount(0.0); // Set to 0 if no payments found for this invoiceId
            }
        }

        return invoices;
    }

    // Calculate total paid amounts per invoice
    public static Map<Long, Double> calculateTotalPaidAmount(List<User> invoices, Iterable<Detail> details) {
        Map<Long, Double> totalPaidAmounts = new HashMap<>();

        for (Detail detail : details) {
            long invoiceId = detail.getInvoiceId();
            double paidAmount = detail.getPaidAmount();

            totalPaidAmounts.put(invoiceId, totalPaidAmounts.getOrDefault(invoiceId, 0.0) + paidAmount);
        }
        return totalPaidAmounts;
    }
}
