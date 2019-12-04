package com.zjqiu.pwdmng.entity.dto;

public class SearchDto {

    private Integer rows;
    private Integer page;
    private String sort;
    private String sortOrder;
    private String search;

    @Override
    public String toString() {
        return "SearchDto{" +
                "rows=" + rows +
                ", page=" + page +
                ", sort='" + sort + '\'' +
                ", sortOrder='" + sortOrder + '\'' +
                ", search='" + search + '\'' +
                '}';
    }

    public Integer getRows() {
        return rows;
    }

    public Integer getPage() {
        return page;
    }

    public String getSort() {
        return sort;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public String getSearch() {
        return search;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
