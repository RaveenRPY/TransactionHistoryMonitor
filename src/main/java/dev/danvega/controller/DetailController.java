package dev.danvega.controller;

import dev.danvega.domain.Detail;
import dev.danvega.service.DetailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Det")
public class DetailController {
    private DetailService detailService;

    public DetailController(DetailService detailService) {
        this.detailService = detailService;
    }

    // Endpoint to retrieve a list of all details
    @GetMapping("/list")
    public Iterable<Detail> list() {
        return detailService.det();
    }

    // Endpoint to retrieve a list of details by a specific invoiceId
    @GetMapping("/list/{invoiceId}")
    public List<Detail> listByInvoiceId(@PathVariable Long invoiceId) {
        // Use the invoiceId parameter to fetch details matching the invoiceId
        return detailService.findByInvoiceId(invoiceId);
    }
}
