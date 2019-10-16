package com.selfgrowth.core.okrs.service;

import com.selfgrowth.model.okr.OKR;
import com.selfgrowth.model.okr.OKRDto;
import com.selfgrowth.model.okrtype.OKRType;
import com.selfgrowth.model.owner.Owner;

import java.util.Calendar;
import java.util.List;


public interface OKRService {

    OKRDto create(OKRDto okrDto);

    OKR findOKRById(int okrDtoId);

    List<OKRDto> findAll();

    OKRDto findOne(int okrDtoId);

    /**List<OKR> findOKRByDueDate(Calendar dueDate);

    * List<OKR> findOKRByType(OKRType okrType);

    * List<OKR> findOKRByOwner(Owner owner);

    * List<OKR> findOKRByCompletionPoint(double completionPoint);
    */
    OKRDto update(OKRDto user);

    OKRDto delete(int okrId);
}
