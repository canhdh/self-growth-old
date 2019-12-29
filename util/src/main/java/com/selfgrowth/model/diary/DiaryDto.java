package com.selfgrowth.model.diary;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.util.Objects;

public class DiaryDto {
    private int id;
    @JsonProperty("diary_id")
    private int diaryId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("due_date")
    private Date dueDate;
    @JsonProperty("completion_point")
    private double completionPoint;
    @JsonProperty("steps")
    private String steps;

    public DiaryDto() {
    }

    public DiaryDto(DiaryDto.Builder builder){
        this.diaryId = builder.diaryId;
        this.title = builder.title;
        this.dueDate = builder.dueDate;
        this.completionPoint = builder.completionPoint;
        this.steps = builder.steps;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public double getCompletionPoint() {
        return completionPoint;
    }

    public void setCompletionPoint(double completionPoint) {
        this.completionPoint = completionPoint;
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
        private double completionPoint;
        private String steps;

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

        public DiaryDto.Builder duedate(Date dueDate){
            this.dueDate = dueDate;
            return  this;
        }

        public DiaryDto.Builder completionPoint(double comletionPoint){
            this.completionPoint = comletionPoint;
            return this;
        }

        public DiaryDto.Builder steps(String steps){
            this.steps = steps;
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
        if (!(o instanceof DiaryDto)) return false;
        DiaryDto diaryDto = (DiaryDto) o;
        return id == diaryDto.id &&
                diaryId == diaryDto.diaryId &&
                Double.compare(diaryDto.completionPoint, completionPoint) == 0 &&
                Objects.equals(title, diaryDto.title) &&
                Objects.equals(dueDate, diaryDto.dueDate) &&
                Objects.equals(steps, diaryDto.steps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, diaryId, title, dueDate, completionPoint, steps);
    }

    public DiaryDto(int id, int diaryId, String title, Date dueDate, double completionPoint, String steps) {
        this.id = id;
        this.diaryId = diaryId;
        this.title = title;
        this.dueDate = dueDate;
        this.completionPoint = completionPoint;
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "DiaryDto{" +
                ", diaryId=" + diaryId +
                ", title='" + title + '\'' +
                ", dueDate=" + dueDate +
                ", comletionPoint=" + completionPoint +
                ", steps='" + steps + '\'' +
                '}';
    }
}
