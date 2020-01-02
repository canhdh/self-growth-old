package com.selfgrowth.model.diary;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "diary")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id", nullable = false)
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
        this.title = builder.title;
        this.category = builder.category;
        this.severity = builder.severity;
        this.priority = builder.priority;
        this.mood = builder.mood;
        this.location = builder.location;
        this.picture = builder.picture;
    }

    public static Diary.Builder getBuilder(){return new Builder();}

    public int getDiaryId() {
        return diaryId;
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
        private String title;
        private String category;
        private int severity;
        private int priority;
        private String mood;
        private String location;
        private String picture;

        public Builder() {
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

        public Diary build(){
            Diary build = new Diary(this);
            return build;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Diary)) return false;
        Diary diary = (Diary) o;
        return  diaryId == diary.getDiaryId() &&
                Objects.equals(title, diary.getTitle()) &&
                Objects.equals(category, diary.getCategory()) &&
                severity == diary.getSeverity() &&
                priority == diary.getPriority() &&
                Objects.equals(mood, diary.getMood()) &&
                Objects.equals(location, diary.getLocation()) &&
                Objects.equals(picture, diary.getPicture());
    }

    @Override
    public int hashCode() {
        return Objects.hash(diaryId, title, severity, priority, mood, location, picture);
    }

    @Override
    public String toString() {
        return "Diary{" +
                ", diaryId=" + diaryId +
                ", title='" + title + '\'' +
                ", category='" + category + "\'" +
                ", severity=" + severity +
                ", priority=" + priority +
                ", mood='" + mood + "\'" +
                ", location='" + location + "\'" +
                ", picture='" + picture + "\'" +
                '}';
    }
}
