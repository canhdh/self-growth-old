package com.selfgrowth.model.okrtype;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name = "OKR")
public enum OKRType{
//    @Column(name = "okrType", nullable = false)
    WEEK, MONTH, QUARTER, YEAR;
}