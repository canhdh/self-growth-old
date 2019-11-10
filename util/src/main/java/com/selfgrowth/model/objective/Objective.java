package com.selfgrowth.model.objective;

import com.google.common.base.MoreObjects;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "objective")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Objective {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;
    @Column(name = "objectiveID",nullable = false)
    private int objectiveID;
    @Column(name = "title",nullable = false)
    private String title;

    public Objective() {
    }

    public Objective(Builder builder) {
        this.objectiveID = builder.objectiveID;
        this.title = builder.title;
    }

    public static Builder getBuilder(){return new Builder();}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

        public Objective build(){
            Objective build = new Objective(this);
            return build;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Objective objective = (Objective) o;
        return id == objective.id &&
                objectiveID == objective.objectiveID &&
                Objects.equals(title, objective.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, objectiveID, title);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("objective id",objectiveID)
                .add("title",title)
                .toString();
    }
}
