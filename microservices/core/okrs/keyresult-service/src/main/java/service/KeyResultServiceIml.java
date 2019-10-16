package service;

import com.selfgrowth.model.keyResult.KeyResult;
import com.selfgrowth.model.keyResult.KeyResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.KeyResultRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class KeyResultServiceIml implements KeyResultService {
    private final KeyResultRepository repository;

    @Autowired
    KeyResultServiceIml(KeyResultRepository repository) {
        this.repository = repository;
    }

    @Override
    public KeyResultDto create(KeyResultDto keyResultDto) {
        KeyResult keyResults = (KeyResult)repository.findKeyResultById(keyResultDto.getKeyResultID());
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
            return convertToDTO(keyResults);
        }
    }

    @Override
    public KeyResultDto update(KeyResultDto user) {
        KeyResult updated = findKeyResultByID(user.getKeyResultID());
        updated.setKeyResultID(user.getKeyResultID());
        updated.setComletionPoint(user.getComletionPoint());
        updated.setDueDate(user.getDueDate());
        updated.setSteps(user.getSteps());
        updated.setTitle(user.getTitle());
        return convertToDTO(updated);
    }

    @Override
    public KeyResultDto delete(int keyResultID) {
        KeyResult deleted = findKeyResultByID(keyResultID);
        repository.delete(deleted);
        return convertToDTO(deleted);
    }

    @Override
    public KeyResult findKeyResultByID(int keyResultID) {
        KeyResult result = repository.findOne(keyResultID);
        return result;
    }

    @Override
    public KeyResultDto findOne(int keyResultID) {
        KeyResult keyResults = repository.findOne(keyResultID);
        if (keyResults != null){
            return convertToDTO(keyResults);
        }
        return null;
    }

    @Override
    public List<KeyResultDto> findAll() {
        List<KeyResult> keyResults = repository.findAll();
        return convertToDTOs(keyResults);
    }

    private List<KeyResultDto> convertToDTOs(List<KeyResult> models) {
        return models.stream().map(this::convertToDTO).collect(toList());
    }

    private KeyResultDto convertToDTO(KeyResult model) {
        KeyResultDto dto = new KeyResultDto();
        dto.setKeyResultID(model.getKeyResultID());
        dto.setComletionPoint(model.getComletionPoint());
        dto.setDueDate(model.getDueDate());
        dto.setSteps(model.getSteps());
        dto.setTitle(model.getTitle());

        return dto;
    }
}
