package com.selfgrowth.core.okrs.service;

import com.selfgrowth.model.okr.OKR;
import com.selfgrowth.model.okrtype.OKRType;
import com.selfgrowth.model.owner.Owner;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OKRServiceIml implements OKRService {
    public OKR create(OKR okr) {
        return null;
    }

    public OKR findOKRById(int okrID) {
        return null;
    }

    public List<OKR> findAll() {
        return null;
    }

    public List<OKR> findOKRByDueDate(Calendar dueDate) {
        return null;
    }

    public List<OKR> findOKRByType(OKRType okrType) {
        return null;
    }

    public List<OKR> findOKRByOwner(Owner owner) {
        return null;
    }

    public List<OKR> findOKRByCompletionPoint(double completionPoint) {
        return null;
    }

    public OKR update(OKR okr) {
        return null;
    }

    public OKR delete(int okrID) {
        return null;
    }
}
