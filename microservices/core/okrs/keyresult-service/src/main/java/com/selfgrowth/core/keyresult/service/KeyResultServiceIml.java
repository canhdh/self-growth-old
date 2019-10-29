package com.selfgrowth.core.keyresult.service;

import com.selfgrowth.core.keyresult.repository.KeyResultRepository;
import com.selfgrowth.model.keyResult.KeyResult;
import com.selfgrowth.model.keyResult.KeyResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class KeyResultServiceIml implements KeyResultService {
    private final KeyResultRepository repository;

    @Autowired
    KeyResultServiceIml(KeyResultRepository repository) {

        this.repository = repository;
    }

    @Override
    public KeyResultDto create(KeyResultDto keyResultDto) {
        KeyResult keyResults = findByKeyResultID(keyResultDto.getKeyResultID());
        if (keyResults == null){
            KeyResult persisted = KeyResult.getBuilder()
                    .KeyResultID(keyResultDto.getKeyResultID())
                    .CompletionPoint(keyResultDto.getComletionPoint())
                    .Duedate(keyResultDto.getDueDate())
                    .Steps(keyResultDto.getSteps())
                    .Title(keyResultDto.getTitle())
                    .build();
            persisted = repository.save(persisted);
            return convertToDTO(persisted);
        } else {
            return null;
        }
    }

    @Override
    public KeyResultDto update(KeyResultDto user) {
        KeyResult updated = findByKeyResultID(user.getKeyResultID());
        if (updated != null) {
            updated.setKeyResultID(user.getKeyResultID());
            updated.setComletionPoint(user.getComletionPoint());
            updated.setDueDate(user.getDueDate());
            updated.setSteps(user.getSteps());
            updated.setTitle(user.getTitle());
            repository.saveAndFlush(updated);
        }
        return convertToDTO(updated);
    }

    @Override
    public KeyResultDto delete(int keyResultID) {
        KeyResult deleted = findByKeyResultID(keyResultID);
        if (deleted != null) {
            repository.deleteByKeyResultID(deleted.getKeyResultID());
            return convertToDTO(deleted);
        } else return null;
    }

    @Override
    public KeyResult findByKeyResultID(int keyResultID) {
        KeyResult result = repository.findByKeyResultID(keyResultID).orElse(null);
        return result;
    }

    @Override
    public List<KeyResultDto> findAll() {
        List<KeyResult> keyResults = repository.findAll();
        return convertToDTOs(keyResults);
    }

    private List<KeyResultDto> convertToDTOs(List<KeyResult> models) {
        if (models != null)
            return models.stream().map(this::convertToDTO).collect(toList());
        else return null;
    }

    private KeyResultDto convertToDTO(KeyResult model) {
        KeyResultDto dto = new KeyResultDto();
        if (model != null) {
            dto.setKeyResultID(model.getKeyResultID());
            dto.setComletionPoint(model.getComletionPoint());
            dto.setDueDate(model.getDueDate());
            dto.setSteps(model.getSteps());
            dto.setTitle(model.getTitle());
            return dto;
        } else return null;
    }
}
