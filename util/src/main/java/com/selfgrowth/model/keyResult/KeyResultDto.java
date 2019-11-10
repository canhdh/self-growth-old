package com.selfgrowth.model.keyResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.sql.Date;
import java.util.Objects;

@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KeyResultDto {
    private int id;
    @JsonProperty("keyresult_id")
    private int keyResultID;
    @JsonProperty("title")
    private String title;
    @JsonProperty("due_date")
    private Date dueDate;
    @JsonProperty("completion_point")
    private double comletionPoint;
    @JsonProperty("steps")
    private String steps;

    public KeyResultDto() {
    }

    public KeyResultDto(Builder builder){
        this.keyResultID = builder.keyResultID;
        this.title = builder.title;
        this.dueDate = builder.dueDate;
        this.comletionPoint = builder.comletionPoint;
        this.steps = builder.steps;
    }
    public static Builder getBuilder(){return new Builder();}

    public int getKeyResultID() {
        return keyResultID;
    }

    public void setKeyResultID(int keyResultID) {
        this.keyResultID = keyResultID;
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
        private int keyResultID;
        private String title;
        private Date dueDate;
        private double comletionPoint;
        private String steps;

        public Builder() {
        }

        public Builder KeyResultID(int keyResultID){
            this.keyResultID = keyResultID;
            return  this;
        }

        public Builder Title(String title){
            this.title = title;
            return this;
        }

        public Builder Duedate(Date dueDate){
            this.dueDate = dueDate;
            return  this;
        }

        public Builder CompletionPoint(double comletionPoint){
            this.comletionPoint = comletionPoint;
            return this;
        }

        public Builder Steps(String steps){
            this.steps = steps;
            return this;
        }

        public KeyResultDto build(){
            KeyResultDto build = new KeyResultDto(this);
            return build;

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return  true;
        }
        if (o == null || (o.getClass() != this.getClass())) return false;
        KeyResultDto other = (KeyResultDto)o;
        return Objects.equals(other.comletionPoint,this.comletionPoint) &&
                Objects.equals(other.dueDate,this.dueDate) &&
                Objects.equals(other.keyResultID,this.keyResultID) &&
                Objects.equals(other.steps,this.steps) &&
                Objects.equals(other.title,this.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                comletionPoint,
                dueDate,
                keyResultID,
                steps,
                title);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("key result id",keyResultID)
                .add("title",title)
                .add("due date",dueDate)
                .add("completion point",comletionPoint)
                .add("steps",steps)
                .toString();
    }
}