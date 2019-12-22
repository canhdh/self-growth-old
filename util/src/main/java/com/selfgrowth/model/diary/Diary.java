package com.selfgrowth.model.diary;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "diary")
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "diaryid", nullable = false)
    private int diaryId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "severity", nullable = false)
    private int severity;

    @Column(name = "priority", nullable = false)
    private int priority;

    @Column(name = "mood", nullable = false)
    private String mood;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "picture", nullable = false)
    private byte picture;

    public Diary() {}

    public static Diary.Builder getBuilder(){return new Diary.Builder();}

    public Diary(Diary.Builder builder){
        this.diaryId = builder.diaryId;
        this.title = builder.title;
        this.category = builder.category;
        this.severity = builder.severity;
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

        public Diary.Builder diaryId(int diaryId){
            this.diaryId = diaryId;
            return  this;
        }

        public Diary.Builder title(String title){
            this.title = title;
            return this;
        }
        public Diary.Builder category(String cagetory){
            this.category = cagetory;
            return this;
        }

        public Diary.Builder severity(int severity){
            this.severity = severity;
            return  this;
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

        public Diary.Builder picture(byte picture){
            this.picture = picture;
            return this;
        }

        public Diary build(){
            Diary build = new Diary(this);
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

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
        if (!(o instanceof Diary)) return false;
        Diary diary = (Diary) o;
        return getId() == diary.getId() &&
                getDiaryId() == diary.getDiaryId() &&
                getSeverity() == diary.getSeverity() &&
                getPriority() == diary.getPriority() &&
                getPicture() == diary.getPicture() &&
                getTitle().equals(diary.getTitle()) &&
                getCategory().equals(diary.getCategory()) &&
                getMood().equals(diary.getMood()) &&
                getLocation().equals(diary.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDiaryId(), getTitle(), getCategory(), getSeverity(), getPriority(), getMood(), getLocation(), getPicture());
    }

    @Override
    public String toString() {
        return "Diary{" +
                "id=" + id +
                ", diaryId=" + diaryId +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", severity=" + severity +
                ", priority=" + priority +
                ", mood='" + mood + '\'' +
                ", location='" + location + '\'' +
                ", picture=" + picture +
                '}';
    }
}