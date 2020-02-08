package com.selfgrowth.model.diary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.selfgrowth.model.audit.DateAudit;

import java.util.Date;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DiaryDto extends DateAudit {

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
    private String picture;


    public DiaryDto() {
    }

    public DiaryDto(Builder builder){
        super(builder.createAt, builder.updateAt);
        this.diaryId = builder.diaryId;
        this.title = builder.title;
        this.category = builder.category;
        this.severity = builder.severity;
        this.priority = builder.priority;
        this.mood = builder.mood;
        this.location = builder.location;
        this.picture = builder.picture;
//        this.setCreateAt(builder.createAt);
//        this.setUpdateAt(builder.updateAt);
    }

    public static DiaryDto.Builder getBuilder(){return new Builder();}

    public int getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(int diaryId) {
        this.diaryId = diaryId;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public static class Builder{
        private int diaryId;
        private String title;
        private String category;
        private int severity;
        private int priority;
        private String mood;
        private String location;
        private String picture;
        private Date createAt;
        private Date updateAt;

        public Builder() {
        }

        public DiaryDto.Builder diaryId(int diaryId){
            this.diaryId = diaryId;
            return this;
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
            return this;
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

        public DiaryDto.Builder picture(String picture){
            this.picture = picture;
            return this;
        }

        public DiaryDto.Builder createAt(Date createAt){
            this.createAt = createAt;
            return this;
        }

        public DiaryDto.Builder updateAt(Date updateAt){
            this.updateAt = updateAt;
            return this;
        }

        public DiaryDto build(){
            DiaryDto build = new DiaryDto(this);
            return build;

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiaryDto diaryDto = (DiaryDto) o;
        return diaryId == diaryDto.diaryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(diaryId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("diary id", diaryId)
                .add("title", title)
                .add("category", category)
                .add("severity", severity)
                .add("priority", priority)
                .add("mood", mood)
                .add("location", location)
                .add("picture", picture)
                .add("createAt", getCreateAt())
                .add("updateAt", getUpdateAt())
                .toString();
    }
}
