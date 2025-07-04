package com.cogip.cli.model;

import lombok.Data;

import java.util.List;
@Data
public class CompanyPage {
    private List<Company> content;
    private ContactPage.Pageable pageable;
    private boolean last;
    private int totalPages;
    private int totalElements;
    private int size;
    private int number;
    private ContactPage.Sort sort;
    private int numberOfElements;
    private boolean first;
    private boolean empty;

    @Data
    public static class Pageable {
        private int pageNumber;
        private int pageSize;
        private ContactPage.Sort sort;
        private int offset;
        private boolean unpaged;
        private boolean paged;
    }

    @Data
    public static class Sort {
        private boolean empty;
        private boolean unsorted;
        private boolean sorted;
    }
}
