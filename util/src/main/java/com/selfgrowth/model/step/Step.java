package com.selfgrowth.model.step;

import com.google.common.base.MoreObjects;
import com.selfgrowth.model.owner.Owner;

import java.util.Objects;

public class Step {
    private int stepID;
    private String title;
    private String description;
    private boolean isComplete;
    private Owner owner;

    public Step() {
    }

    public Step(Builder builder){
        this.stepID = builder.stepID;
        this.title = builder.title;
        this.description = builder.description;
        this.isComplete = builder.isComplete;
        this.owner = builder.owner;
    }

    public static Builder getBuilder(){return new Builder();}

    public int getStepID() {
        return stepID;
    }

    public void setStepID(int stepID) {
        this.stepID = stepID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public static class Builder{
        private int stepID;
        private String title;
        private String description;
        private boolean isComplete;
        private Owner owner;

        public Builder() {
        }

        public Builder StepID(int stepID){
            this.stepID = stepID;
            return this;
        }

        public Builder Title(String title){
            this.title = title;
            return  this;
        }

        public Builder Description(String description){
            this.description = description;
            return  this;
        }

        public Builder IsComplete(boolean isComplete){
            this.isComplete = isComplete;
            return this;
        }

        public Builder Owner(Owner owner){
            this.owner = owner;
            return  this;
        }

        public Step build(){
            Step build = new Step(this);
            return build;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return  true;
        if (o == null || (getClass() != o.getClass())) return false;
        Step other = (Step)o;
        return Objects.equals(stepID,other.stepID) &&
                Objects.equals(title,other.title) &&
                Objects.equals(description,other.description) &&
                Objects.equals(isComplete,other.isComplete) &&
                Objects.equals(owner,other.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stepID,
                title,
                description,
                isComplete,
                owner);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("step_id",stepID)
                .add("title",title)
                .add("description",description)
                .add("is_complete",isComplete)
                .add("owner",owner)
                .toString();
    }
}
