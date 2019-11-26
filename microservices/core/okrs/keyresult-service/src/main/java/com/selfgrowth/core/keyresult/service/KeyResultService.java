package com.selfgrowth.core.keyresult.service;

import com.selfgrowth.model.keyResult.KeyResult;
import com.selfgrowth.model.keyResult.KeyResultDto;

import java.util.List;

public interface KeyResultService {
    KeyResultDto create(KeyResultDto keyResultDto);

    KeyResult findByKeyResultID(int keyResultID);

    List<KeyResultDto> findAll();

    KeyResultDto update(KeyResultDto user);

    KeyResultDto delete(int keyResultID);
}
