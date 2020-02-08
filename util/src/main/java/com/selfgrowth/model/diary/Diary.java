package com.selfgrowth.model.diary;

import com.google.common.base.MoreObjects;
import com.selfgrowth.model.audit.DateAudit;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "diary")
public class Diary extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diaryId", nullable = false)
    private int diaryId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "severity", nullable = false)
    private int severity;

    @Column(name = "priority", nullable = false)
    private int priority;

    @Column(name = "mood",nullable = false)
    private String mood;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "picture", nullable = false)
    private String picture;

    public Diary() {
    }

    public Diary(Builder builder) {
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

    public static Diary.Builder getBuilder(){return new Builder();}

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

        public Diary.Builder diaryId(int diaryId){
            this.diaryId = diaryId;
            return this;
        }

        public Diary.Builder title(String title){
            this.title = title;
            return this;
        }

        public Diary.Builder category(String category){
            this.category = category;
            return this;
        }

        public Diary.Builder severity(int severity){
            this.severity = severity;
            return this;
        }

        public Diary.Builder priority(int priority){
            this.priority = priority;
            return this;
        }

        public Diary.Builder mood(String mood){
            this.mood = mood;
            return this;
        }

        public Diary.Builder location(String location){
            this.location = location;
            return this;
        }

        public Diary.Builder picture(String picture){
            this.picture = picture;
            return this;
        }

        public Diary.Builder createAt(Date createAt){
            this.createAt = createAt;
            return this;
        }

        public Diary.Builder updateAt(Date updateAt){
            this.updateAt = updateAt;
            return this;
        }

        public Diary build(){
            Diary build = new Diary(this);
            return build;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diary diary = (Diary) o;
        return diaryId == diary.diaryId;
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
