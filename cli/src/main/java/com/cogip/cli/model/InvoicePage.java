package com.cogip.cli.model;

import lombok.Data;

import java.util.List;
@Data
public class InvoicePage {
    private List<Invoice> content;
    private Pageable pageable;
    private boolean last;
    private int totalPages;
    private int totalElements;
    private int size;
    private int number;
    private Sort sort;
    private int numberOfElements;
    private boolean first;
    private boolean empty;

    @Data
    public static class Pageable {
        private int pageNumber;
        private int pageSize;
        private Sort sort;
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
