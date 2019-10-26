package com.selfgrowth.model.owner;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.util.Objects;

//@Entity
//@Table(name = "owner")
public class Owner {
//    @Id
//    @GeneratedValue
//    @Column(name = "ownerID", nullable = false)
    @JsonProperty("ownerID_Owner")
    private int ownerID;

//    @Column(name = "name", length = 45, nullable = false)
    @JsonProperty("name")
    private String name;

//    @Column(name = "email",length = 45, nullable = false)
    @JsonProperty("email")
    private String email;

    public Owner() {
    }

    public Owner(Builder builder){
        this.ownerID = builder.ownerID;
        this.name = builder.name;
        this.email = builder.email;
    }

    public static Builder getBuilder(){return new Builder();}

    public int getownerID() {
        return ownerID;
    }

    public void setownerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static class Builder{
        private int ownerID;
        private String name;
        private String email;

        public Builder() {
        }

        public Builder ownerID(int ownerID){
            this.ownerID = ownerID;
            return this;
        }

        public Builder Name(String name){
            this.name = name;
            return this;
        }

        public Builder Email(String email){
            this.email = email;
            return this;
        }

        public Owner build(){
            Owner build = new Owner(this);
            return build;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || (getClass() != o.getClass())){
            return  false;
        }
        Owner other = (Owner)o;
        return Objects.equals(ownerID,other.ownerID) &&
                Objects.equals(name,other.name) &&
                Objects.equals(email,other.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerID,name,email);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("ownerID",ownerID)
                .add("name",name)
                .add("email",email)
                .toString();
    }
}
