package ua.nenya.dto;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class RewardDTO implements Serializable {

    private Long id;

    private Long projectId;

    @NotNull
    private String title;

    @Range(min = 1, max = Integer.MAX_VALUE)
    private long amount;

    @Column
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RewardDTO rewardDTO = (RewardDTO) o;

        return id != null ? id.equals(rewardDTO.id) : rewardDTO.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "RewardDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
