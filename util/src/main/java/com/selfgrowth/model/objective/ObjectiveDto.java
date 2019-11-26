package com.selfgrowth.model.objective;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;

public class ObjectiveDto {

    @JsonProperty("ObjectiveID")
    private int objectiveID;
    @JsonProperty("Title")
    private String title;

    public ObjectiveDto() {
    }

    public ObjectiveDto(Builder builder) {
        this.objectiveID = builder.objectiveID;
        this.title = builder.title;
    }

    public static Builder getBuilder(){return new Builder();}



    public int getObjectiveID() {
        return objectiveID;
    }

    public void setObjectiveID(int objectiveID) {
        this.objectiveID = objectiveID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class Builder{
        private int objectiveID;
        private String title;

        public Builder() {
        }

        public Builder ObjectiveID(int objectiveID){
            this.objectiveID = objectiveID;
            return this;
        }

        public Builder Title(String title){
            this.title = title;
            return this;
        }

        public ObjectiveDto build(){
            ObjectiveDto build = new ObjectiveDto(this);
            return build;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectiveDto that = (ObjectiveDto) o;
        return objectiveID == that.objectiveID &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectiveID, title);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("objective id",objectiveID)
                .add("title",title)
                .toString();
    }
}
