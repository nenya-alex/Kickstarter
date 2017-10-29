package ua.nenya.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;


public class ProjectDTO implements Serializable{

    private Long id;

    private Long categoryId;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private long neededAmount;

    private long availableAmount;

    @NotNull
    private int remainingDays;

    private String history;

    private String video;

    private Long createdById;

    private ZonedDateTime createdAt;

    private Set<RewardDTO> rewards = new HashSet<>();

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getNeededAmount() {
        return neededAmount;
    }

    public void setNeededAmount(long neededAmount) {
        this.neededAmount = neededAmount;
    }

    public long getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(long availableAmount) {
        this.availableAmount = availableAmount;
    }

    public int getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(int remainingDays) {
        this.remainingDays = remainingDays;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Set<RewardDTO> getRewards() {
        return rewards;
    }

    public void setRewards(Set<RewardDTO> rewards) {
        this.rewards = rewards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectDTO that = (ProjectDTO) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ProjectDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", neededAmount=" + neededAmount +
                ", availableAmount=" + availableAmount +
                ", remainingDays=" + remainingDays +
                ", history='" + history + '\'' +
                ", video='" + video + '\'' +
                ", rewards=" + rewards +
                '}';
    }
}
