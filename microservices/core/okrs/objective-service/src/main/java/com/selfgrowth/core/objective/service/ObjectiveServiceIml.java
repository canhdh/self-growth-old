package com.selfgrowth.core.objective.service;

import com.selfgrowth.model.objective.Objective;
import com.selfgrowth.model.objective.ObjectiveDto;
import com.selfgrowth.core.objective.repository.ObjectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ObjectiveServiceIml implements ObjectiveService {
    private final ObjectiveRepository objectiveRepository;
    @Autowired

    public ObjectiveServiceIml(ObjectiveRepository objectiveRepository) {
        this.objectiveRepository = objectiveRepository;
    }

    @Override
    @Caching(
            put = {@CachePut(value = "objectiveCache", key = "#objectiveDto.objectiveID")},
            evict = {@CacheEvict(value = "allObjectiveCache", allEntries = true)}
    )
    public ObjectiveDto create(ObjectiveDto objectiveDto) {
        Objective objective = findByObjectiveID(objectiveDto.getObjectiveID());
        if (objective == null){
            Objective persisted = Objective.getBuilder()
                    .ObjectiveID(objectiveDto.getObjectiveID())
                    .Title(objectiveDto.getTitle())
                    .build();
            persisted = objectiveRepository.save(persisted);
            return convertToDTO(persisted);
        } else return null;
    }

    @Override
    public Objective findByObjectiveID(int objectiveID) {
        Objective objective = objectiveRepository.findByObjectiveID(objectiveID).orElse(null);
        return objective;
    }

    @Override
    @Cacheable(value = "objectiveCache", key = "#objectiveID")
    public ObjectiveDto findByObjectiveIDConvertToDto(int objectiveID) {
        Objective objective = objectiveRepository.findByObjectiveID(objectiveID).orElse(null);
        return convertToDTO(objective);
    }

    @Override
    @Cacheable(value = "allObjectiveCache", unless = "#result.size() == 0")
    public List<ObjectiveDto> findAll() {
        List<Objective> objectives = objectiveRepository.findAll();
        return convertToDTOs(objectives);
    }

    @Override
    @Caching(
            put = {@CachePut(value = "objectiveCache", key = "#user.objectiveID")},
            evict = {@CacheEvict(value = "allObjectiveCache", allEntries = true)}
    )
    public ObjectiveDto update(ObjectiveDto user) {
        Objective updated = findByObjectiveID(user.getObjectiveID());
        if (updated != null) {
            updated.setObjectiveID(user.getObjectiveID());
            updated.setTitle(user.getTitle());
            objectiveRepository.save(updated);
        }
        return convertToDTO(updated);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "objectiveCache", key = "#objectiveID"),
                    @CacheEvict(value = "allObjectiveCache", allEntries = true)
            }
    )
    public ObjectiveDto delete(int objectiveID) {
        Objective deteler = findByObjectiveID(objectiveID);
        if (deteler != null){
            objectiveRepository.delete(deteler);
            return convertToDTO(deteler);
        } else return null;
    }

    private ObjectiveDto convertToDTO(Objective objective){
        ObjectiveDto objectiveDto = new ObjectiveDto();
        if (objective != null){
            objectiveDto.setObjectiveID(objective.getObjectiveID());
            objectiveDto.setTitle(objective.getTitle());
            return objectiveDto;
        } else return null;
    }

    private List<ObjectiveDto> convertToDTOs(List<Objective> models){
        if (models != null){
            return models.stream().map(this::convertToDTO).collect(Collectors.toList());
        } else return null;
    }
}
