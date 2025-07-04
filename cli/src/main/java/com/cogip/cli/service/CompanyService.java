package com.cogip.cli.service;

import com.cogip.cli.model.Company;
import com.cogip.cli.model.CompanyPage;
import com.cogip.cli.model.ContactPage;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CompanyService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final  String url = "http://localhost:8080/company";

    public List<Company> getCompanies() {
        try {
            CompanyPage page = restTemplate.getForObject(url, CompanyPage.class);
            return page != null ? page.getContent() : List.of();
        } catch (HttpClientErrorException.NotFound e) {
            return List.of();
        }
    }

    public Company addCompany(Company company) {
        try {
            return restTemplate.postForObject(url, company, Company.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // Gère l’erreur (log, message utilisateur, etc.)
            System.out.println("Erreur lors de l’ajout de la société : " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return null;
        }
    }

    public CompanyPage getCompanyPage(int page, int size) {
        try {
            String pagedUrl = url + "?page=" + page + "&size=" + size;
            return restTemplate.getForObject(pagedUrl, CompanyPage.class);
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }
}
