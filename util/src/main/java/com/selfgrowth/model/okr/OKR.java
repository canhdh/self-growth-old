package com.selfgrowth.model.okr;

import com.google.common.base.MoreObjects;
import com.selfgrowth.model.owner.Owner;
import com.selfgrowth.model.keyResult.KeyResult;
import com.selfgrowth.model.okrtype.OKRType;

import java.util.Calendar;
import java.util.List;
import java.util.Object;

public class OKR{
    private int okrID;
    private String objective;
    private OKRType okrType;
    private Calendar dueDate;
    private Owner owner;
    private List<KeyResult> keyResults;
    private double completionPoint;

    public OKR(){

    }

    public OKR(int okrID, String objective, OKRType okrType, Calendar dueDate, Onwer owner, List<KeyResult> keyResults, double completionPoint){
        this.okrID = okrID;
        this.objective = objective;
        this.okrType = okrType;
        this.dueDate = dueDate;
        this.owner = owner;
        this.keyResults = keyResults;
        this.completionPoint = completionPoint;
    }

    public int getOkrID() {
        return okrID;
    }

    public void setOkrID(int okrID) {
        this.okrID = okrID;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public OKRType getOkrType() {
        return okrType;
    }

    public void setOkrType(OKRType okrType) {
        this.okrType = okrType;
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

    public List<KeyResult> getKeyResults() {
        return keyResults;
    }

    public void setKeyResults(List<KeyResult> keyResults) {
        this.keyResults = keyResults;
    }

    public double getCompletionPoint() {
        return completionPoint;
    }

    public void setCompletionPoint(double completionPoint) {
        this.completionPoint = completionPoint;
    }

    public static Builder getBuilder() { return new Builder();}

    private static class Builder(){
        private int okrID;
        private String objective;
        private OKRType okrType;
        private Calendar dueDate;
        private Owner owner;
        private List<KeyResult> keyResults;
        private double completionPoint;

        private Builder(){
        }

        public Builder okrID(int okrID){
            this.okrID = okrID;
            return this;
        }

        public Builder objective(String objective){
            this.objective = objective;
            return this;
        }

        public Builder okrType(OKRType okrType){
            this.okrType = okrType;
            return this;
        }

        public Builder dueDate(Calendar dueDate){
            this.dueDate = dueDate;
            return this;
        }

        public Builder owner(Owner owner){
            this.owner = owner;
            return this;
        }

        public Builder keyResult(List<KeyResult> keyResult){
            this.keyResults = keyResults;
            return this;
        }

        public Builder completionPoint(double completionPoint){
            this.completionPoint = completionPoint;
            return this;
        }

        public OKR build(){
            OKR build = new OKR(this);
            return build;
        }
    }
    @Override
    public boolean equals(final Object object){
        if (this == object){
            return true;
        }

        if (object == null || this.getClass() != object.getClass()){
            return false;
        }

        OKR other = (OKR) object;

        return Object.equals(okrID, other.okrID) &&
                Object.equals(objective, other.objective) &&
                Object.equals(okrType, other.okrType) &&
                Object.equals(dueDate, other.dueDate) &&
                Object.equals(owner, other.owner) &&
                Object.equals(keyResult, other.keyResults) &&
                Object.equals(completionPoint, other.completionPoint);
    }

    @Override
    public int hashCode(){
        return Object.hash(okrID,
                objective,
                okrType,
                dueDate,
                owner,
                keyResults,
                completionPoint);
    }

    @Override
    public String toString(){
        return MoreObject.toStringHelper(this)
                .add("orkID",okrID)
                .add("objective",objective)
                .add("okrType",okrType)
                .add("dueDate",dueDate)
                .add("owner",owner)
                .add("keyResults",keyResults)
                .add("completionPoint",completionPoint)
                .toString;
    }
}