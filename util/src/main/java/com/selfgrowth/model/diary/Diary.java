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
    private int diaryId;

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
        this.diaryId = builder.diaryId;
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
        private int diaryId;
        private String title;
        private Date dueDate;
        private double comletionPoint;
        private String steps;

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

        public Diary.Builder duedate(Date dueDate){
            this.dueDate = dueDate;
            return  this;
        }

        public Diary.Builder completionPoint(double comletionPoint){
            this.comletionPoint = comletionPoint;
            return this;
        }

        public Diary.Builder steps(String steps){
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
                getDiaryId() == diary.getDiaryId() &&
                Double.compare(diary.getComletionPoint(), getComletionPoint()) == 0 &&
                Objects.equals(getTitle(), diary.getTitle()) &&
                Objects.equals(getDueDate(), diary.getDueDate()) &&
                Objects.equals(getSteps(), diary.getSteps());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDiaryId(), getTitle(), getDueDate(), getComletionPoint(), getSteps());
    }

    @Override
    public String toString() {
        return "Diary{" +
                "id=" + id +
                ", diaryId=" + diaryId +
                ", title='" + title + '\'' +
                ", dueDate=" + dueDate +
                ", comletionPoint=" + comletionPoint +
                ", steps='" + steps + '\'' +
                '}';
    }
}
