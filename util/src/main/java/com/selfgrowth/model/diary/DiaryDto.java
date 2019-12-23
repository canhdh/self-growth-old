package com.selfgrowth.model.diary;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Objects;

public class DiaryDto {

    private int id;
    @JsonProperty("diary_id")
    private int diaryId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("category")
    private String category;
    @JsonProperty("severity")
    private int severity;
    @JsonProperty("priority")
    private int priority;
    @JsonProperty("mood")
    private String mood;
    @JsonProperty("location")
    private String location;
    @JsonProperty("picture")
    private byte picture;

    public DiaryDto() {}

    public static DiaryDto.Builder getBuilder(){return new DiaryDto.Builder();}

    public DiaryDto(DiaryDto.Builder builder){
        this.diaryId = builder.diaryId;
        this.title = builder.title;
        this.severity = builder.severity;
        this.category = builder.category;
        this.priority = builder.priority;
        this.mood = builder.mood;
        this.location = builder.location;
        this.picture = builder.picture;
    }
    public static class Builder{
        private int diaryId;
        private String title;
        private String category;
        private int  severity;
        private int priority;
        private String mood;
        private String location;
        private byte picture;

        public Builder() {
        }

        public DiaryDto.Builder diaryId(int diaryId){
            this.diaryId = diaryId;
            return  this;
        }

        public DiaryDto.Builder title(String title){
            this.title = title;
            return this;
        }
        public DiaryDto.Builder category(String category){
            this.category = category;
            return this;
        }

        public DiaryDto.Builder severity(int severity){
            this.severity = severity;
            return  this;
        }

        public DiaryDto.Builder priority(int priority){
            this.priority = priority;
            return this;
        }

        public DiaryDto.Builder mood(String mood){
            this.mood = mood;
            return this;
        }

        public DiaryDto.Builder location(String location){
            this.location = location;
            return this;
        }

        public DiaryDto.Builder picture(byte picture){
            this.picture = picture;
            return this;
        }

        public DiaryDto build(){
            DiaryDto build = new DiaryDto(this);
            return build;

        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(int diaryId) {
        this.diaryId = diaryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public byte getPicture() {
        return picture;
    }

    public void setPicture(byte picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiaryDto)) return false;
        DiaryDto diaryDto = (DiaryDto) o;
        return getId() == diaryDto.getId() &&
                getDiaryId() == diaryDto.getDiaryId() &&
                getSeverity() == diaryDto.getSeverity() &&
                getPriority() == diaryDto.getPriority() &&
                getPicture() == diaryDto.getPicture() &&
                getTitle().equals(diaryDto.getTitle()) &&
                getCategory().equals(diaryDto.getCategory()) &&
                getMood().equals(diaryDto.getMood()) &&
                getLocation().equals(diaryDto.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDiaryId(), getTitle(), getCategory(), getSeverity(), getPriority(), getMood(), getLocation(), getPicture());
    }

    @Override
    public String toString() {
        return "DiaryDto{" +
                "id=" + id +
                ", diaryId=" + diaryId +
                ", title='" + title + '\'' +
                ", dueDate='" + category + '\'' +
                ", severity=" + severity +
                ", priority=" + priority +
                ", mood='" + mood + '\'' +
                ", location='" + location + '\'' +
                ", picture=" + picture +
                '}';
    }
}
