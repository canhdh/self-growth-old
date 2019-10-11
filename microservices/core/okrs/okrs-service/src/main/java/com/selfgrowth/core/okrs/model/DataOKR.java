package com.selfgrowth.core.okrs.model;

import com.selfgrowth.model.okr.OKR;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class DataOKR {
    private List<OKR> data;

    public DataOKR(List<OKR> data){
        this.data = data;
    }
}
