package com.selfgrowth.core.keyresult.service;

import com.selfgrowth.model.keyResult.KeyResult;
import com.selfgrowth.model.keyResult.KeyResultDto;

import java.util.List;

public interface KeyResultService {
    KeyResultDto create(KeyResultDto keyResultDto);
    KeyResult findKeyResultByID(int keuResultID);
    List<KeyResultDto> findAll();
    KeyResultDto findOne(int keyResultID);
    KeyResultDto update(KeyResultDto user);
    KeyResultDto delete(int keyResultID);
}
