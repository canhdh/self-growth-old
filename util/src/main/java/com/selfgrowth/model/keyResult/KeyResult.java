package com.selfgrowth.model.keyResult;

import com.google.common.base.MoreObjects;
import com.selfgrowth.model.owner.Owner;
import com.selfgrowth.model.step.Step;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class KeyResult {
    private int keyResultID;
    private String title;
    private Calendar dueDate;
    private Owner owner;
    private double comletionPoint;
    private List<Step> steps;

    public KeyResult() {
    }

    public KeyResult(Builder builder){
        this.keyResultID = builder.keyResultID;
        this.title = builder.title;
        this.dueDate = builder.dueDate;
        this.owner = builder.owner;
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

    public Calendar getDueDate() {
        return dueDate;
    }

    public void setDueDate(Calendar dueDate) {
        this.dueDate = dueDate;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public double getComletionPoint() {
        return comletionPoint;
    }

    public void setComletionPoint(double comletionPoint) {
        this.comletionPoint = comletionPoint;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public static class Builder{
        private int keyResultID;
        private String title;
        private Calendar dueDate;
        private Owner owner;
        private double comletionPoint;
        private List<Step> steps;

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

        public Builder Duedate(Calendar dueDate){
            this.dueDate = dueDate;
            return  this;
        }

        public Builder Owner(Owner owner){
            this.owner = owner;
            return this;
        }

        public Builder CompletionPoint(double comletionPoint){
            this.comletionPoint = comletionPoint;
            return this;
        }

        public Builder Steps(List<Step> steps){
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
                Objects.equals(other.owner,this.owner) &&
                Objects.equals(other.steps,this.steps) &&
                Objects.equals(other.title,this.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner,
                comletionPoint,
                dueDate,
                keyResultID,
                owner,
                steps,
                title);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("key result id",keyResultID)
                .add("title",title)
                .add("due date",dueDate)
                .add("owner",owner)
                .add("completion point",comletionPoint)
                .add("steps",steps)
                .toString();
    }
}