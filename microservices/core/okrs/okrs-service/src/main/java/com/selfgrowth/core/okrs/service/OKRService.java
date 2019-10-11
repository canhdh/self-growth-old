package com.selfgrowth.core.okrs.service;

import com.selfgrowth.model.okr.OKR;
import com.selfgrowth.model.okrtype.OKRType;
import com.selfgrowth.model.owner.Owner;

import java.util.Calendar;
import java.util.List;


public interface OKRService {

    OKR create(OKR okr);

    OKR findOKRById(int okrID);

    List<OKR> findAll();

    List<OKR> findOKRByDueDate(Calendar dueDate);

    List<OKR> findOKRByType(OKRType okrType);
//
//    List<OKR> findOKRByOwner(Owner owner);
//
//    List<OKR> findOKRByCompletionPoint(double completionPoint);

    OKR update(OKR okr);

    OKR delete(int okrID);
}
