package ua.nenya.dto;

import java.io.Serializable;

public class QuoteDTO implements Serializable {

    private Long id;

    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuoteDTO quoteDTO = (QuoteDTO) o;

        return id != null ? id.equals(quoteDTO.id) : quoteDTO.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "QuoteDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
