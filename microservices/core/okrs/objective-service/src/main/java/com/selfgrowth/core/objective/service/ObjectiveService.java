package com.selfgrowth.core.objective.service;

import com.selfgrowth.model.objective.Objective;
import com.selfgrowth.model.objective.ObjectiveDto;

import java.util.List;

public interface ObjectiveService {
    ObjectiveDto create(ObjectiveDto ObjectiveDto);
    Objective findByObjectiveID(int objectiveID);
    List<ObjectiveDto> findAll();
    ObjectiveDto update(ObjectiveDto user);
    ObjectiveDto delete(int objectiveID);
}
