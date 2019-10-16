package com.selfgrowth.model.okr;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.selfgrowth.model.keyResult.KeyResult;
import com.selfgrowth.model.okrtype.OKRType;
import com.selfgrowth.model.owner.Owner;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class OKRDto {
    @JsonProperty("okr_id")
    private int okrID;
    @JsonProperty("objective")
    private String objective;
    @JsonProperty("okr_type")
    private OKRType okrType;
    @JsonProperty("due_date")
    private Calendar dueDate;
    @JsonProperty("owner")
    private Owner owner;
    @JsonProperty("list_keyresult")
    private List<KeyResult> keyResults;
    @JsonProperty("component_poinr")
    private double completionPoint;

    public OKRDto(){
    }

    public OKRDto(int okrID, String objective, OKRType okrType, Calendar dueDate, Owner owner, List<KeyResult> keyResults, double completionPoint) {
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

    public static class Builder{
        private int okrID;
        private String objective;
        private OKRType okrType;
        private Calendar dueDate;
        private Owner owner;
        private List<KeyResult> keyResults;
        private double completionPoint;

        private Builder(){
        }

        public OKRDto.Builder okrID(int okrID){
            this.okrID = okrID;
            return this;
        }

        public OKRDto.Builder objective(String objective){
            this.objective = objective;
            return this;
        }

        public OKRDto.Builder okrType(OKRType okrType){
            this.okrType = okrType;
            return this;
        }

        public OKRDto.Builder dueDate(Calendar dueDate){
            this.dueDate = dueDate;
            return this;
        }

        public OKRDto.Builder owner(Owner owner){
            this.owner = owner;
            return this;
        }

        public OKRDto.Builder keyResult(List<KeyResult> keyResult){
            this.keyResults = keyResults;
            return this;
        }

        public OKRDto.Builder completionPoint(double completionPoint){
            this.completionPoint = completionPoint;
            return this;
        }

        public OKRDto build(){
            OKRDto build = new OKRDto();
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

        OKRDto other = (OKRDto) object;

        return Objects.equals(okrID, other.okrID) &&
                Objects.equals(objective, other.objective) &&
                Objects.equals(okrType, other.okrType) &&
                Objects.equals(dueDate, other.dueDate) &&
                Objects.equals(owner, other.owner) &&
                Objects.equals(keyResults, other.keyResults) &&
                Objects.equals(completionPoint, other.completionPoint);
    }

    @Override
    public int hashCode(){
        return Objects.hash(okrID,
                objective,
                okrType,
                dueDate,
                owner,
                keyResults,
                completionPoint);
    }

    @Override
    public String toString(){
        return MoreObjects.toStringHelper(this)
                .add("orkID",okrID)
                .add("objective",objective)
                .add("okrType",okrType)
                .add("dueDate",dueDate)
                .add("owner",owner)
                .add("keyResults",keyResults)
                .add("completionPoint",completionPoint)
                .toString();
    }
}
