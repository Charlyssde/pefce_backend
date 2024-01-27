package com.devx.software.ferias.DTOs.Shared;

import java.util.List;

public class PaginationResponse<T> {
    private List<T> dataset;
    private Integer currentPage;
    private Long totalItems;
    private Integer totalPages;

    public List<T> getDataset() {
        return dataset;
    }

    public void setDataset(List<T> dataset) {
        this.dataset = dataset;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
