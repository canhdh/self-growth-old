package com.selfgrowth.model.diary;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "diary")
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;
    @Column(name = "diaryid", nullable = false)
    private int diaryID;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "duedate", nullable = false)
    private Date dueDate;

    @Column(name = "completionpoint", nullable = false)
    private double comletionPoint;
    @Column(name = "step",nullable = false)
    private String steps;

    public Diary() {
    }



    public Diary(Diary.Builder builder){
        this.diaryID = builder.diaryID;
        this.title = builder.title;
        this.dueDate = builder.dueDate;
        this.comletionPoint = builder.comletionPoint;
        this.steps = builder.steps;
    }
    public static Diary.Builder getBuilder(){return new Builder();}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiaryID() {
        return diaryID;
    }

    public void setDiaryID(int diaryID) {
        this.diaryID = diaryID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public double getComletionPoint() {
        return comletionPoint;
    }

    public void setComletionPoint(double comletionPoint) {
        this.comletionPoint = comletionPoint;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public static class Builder{
        private int diaryID;
        private String title;
        private Date dueDate;
        private double comletionPoint;
        private String steps;

        public Builder() {
        }

        public Diary.Builder DiaryID(int diaryID){
            this.diaryID = diaryID;
            return  this;
        }

        public Diary.Builder Title(String title){
            this.title = title;
            return this;
        }

        public Diary.Builder Duedate(Date dueDate){
            this.dueDate = dueDate;
            return  this;
        }

        public Diary.Builder CompletionPoint(double comletionPoint){
            this.comletionPoint = comletionPoint;
            return this;
        }

        public Diary.Builder Steps(String steps){
            this.steps = steps;
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
        return getId() == diary.getId() &&
                getDiaryID() == diary.getDiaryID() &&
                Double.compare(diary.getComletionPoint(), getComletionPoint()) == 0 &&
                Objects.equals(getTitle(), diary.getTitle()) &&
                Objects.equals(getDueDate(), diary.getDueDate()) &&
                Objects.equals(getSteps(), diary.getSteps());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDiaryID(), getTitle(), getDueDate(), getComletionPoint(), getSteps());
    }

    @Override
    public String toString() {
        return "Diary{" +
                "id=" + id +
                ", diaryID=" + diaryID +
                ", title='" + title + '\'' +
                ", dueDate=" + dueDate +
                ", comletionPoint=" + comletionPoint +
                ", steps='" + steps + '\'' +
                '}';
    }
}
