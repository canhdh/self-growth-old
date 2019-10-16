package com.selfgrowth.model.step;

import com.google.common.base.MoreObjects;
import com.selfgrowth.model.owner.Owner;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "step")
public class Step {
    @Id
    @GeneratedValue
    @Column(name = "stepID", nullable = false)
    private int stepID;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "isComplete", nullable = false)
    private boolean isComplete;

    public Step() {
    }

    public Step(Builder builder){
        this.stepID = builder.stepID;
        this.title = builder.title;
        this.description = builder.description;
        this.isComplete = builder.isComplete;
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

    public static class Builder{
        private int stepID;
        private String title;
        private String description;
        private boolean isComplete;

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
                Objects.equals(isComplete,other.isComplete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stepID,
                title,
                description,
                isComplete);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("step_id",stepID)
                .add("title",title)
                .add("description",description)
                .add("is_complete",isComplete)
                .toString();
    }
}
