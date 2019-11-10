package com.selfgrowth.model.keyResult;

import com.google.common.base.MoreObjects;
import com.selfgrowth.model.step.Step;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "keyresult")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KeyResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;
    @Column(name = "keyresultid", nullable = false)
    private int keyResultID;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "duedate", nullable = false)
    private Date dueDate;

    @Column(name = "completionpoint", nullable = false)
    private double comletionPoint;
    @Column(name = "step",nullable = false)
    private String steps;
    public KeyResult() {
    }

    public KeyResult(Builder builder){
        this.keyResultID = builder.keyResultID;
        this.title = builder.title;
        this.dueDate = builder.dueDate;
        this.comletionPoint = builder.comletionPoint;
        this.steps = builder.steps;
    }
    public static Builder getBuilder(){return new Builder();}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

        public KeyResult build(){
            KeyResult build = new KeyResult(this);
            return build;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return  true;
        }
        if (o == null || (o.getClass() != this.getClass())) return false;
        KeyResult other = (KeyResult)o;
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