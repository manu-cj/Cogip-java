package com.cogip.cli.service;

import com.cogip.cli.model.ContactPage;
import com.cogip.cli.model.Invoice;
import com.cogip.cli.model.InvoicePage;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class InvoiceService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String url = "http://localhost:8080/invoice";

    public Invoice addInvoice(Invoice invoice) {
        try {
            return restTemplate.postForObject(url,invoice, Invoice.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            System.out.println("Error when add invoice : " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return null;
        }
    }

    public List<Invoice> getInvoice(int page, int size) {
        try {
            String pagedUrl = url + "?page=" + page + "&size=" + size;
            InvoicePage invoicePage = restTemplate.getForObject(pagedUrl, InvoicePage.class);
            return invoicePage != null ? invoicePage.getContent() : List.of();
        } catch (HttpClientErrorException.NotFound e) {
            return List.of();
        }
    }

    public InvoicePage getInvoicePage(int page, int size) {
        try {
            String pagedUrl = url + "?page=" + page + "&size=" + size;
            return restTemplate.getForObject(pagedUrl, InvoicePage.class);
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }

}
