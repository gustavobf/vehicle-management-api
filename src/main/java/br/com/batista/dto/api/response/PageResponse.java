package br.com.batista.dto.api.response;

import io.swagger.v3.oas.annotations.media.*;
import org.springframework.data.domain.*;

import java.util.*;

public class PageResponse<T> {

    @Schema(description = "List of items in the current page")
    private List<T> content;

    @Schema(description = "Current page number (zero-based)", example = "0")
    private int page;

    @Schema(description = "Size of the page (number of items per page)", example = "10")
    private int size;

    @Schema(description = "Total number of elements available", example = "100")
    private long totalElements;

    @Schema(description = "Total number of pages available", example = "10")
    private int totalPages;

    @Schema(description = "Indicates if this is the last page", example = "false")
    private boolean last;

    public PageResponse(Page<T> page) {
        this.content = page.getContent();
        this.page = page.getNumber();
        this.size = page.getSize();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.last = page.isLast();
    }

    public List<T> getContent () {
        return content;
    }

    public int getPage () {
        return page;
    }

    public int getSize () {
        return size;
    }

    public long getTotalElements () {
        return totalElements;
    }

    public int getTotalPages () {
        return totalPages;
    }

    public boolean isLast () {
        return last;
    }
}
