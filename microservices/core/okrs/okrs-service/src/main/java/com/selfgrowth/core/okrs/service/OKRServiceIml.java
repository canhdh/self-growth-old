package com.selfgrowth.core.okrs.service;

import com.selfgrowth.core.okrs.repository.OKRRepository;
import com.selfgrowth.model.okr.OKR;
import com.selfgrowth.model.okr.OKRDto;
import com.selfgrowth.model.okrtype.OKRType;
import com.selfgrowth.model.owner.Owner;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toList;

@Service
public class OKRServiceIml implements OKRService {

    private final OKRRepository repository;

    @Autowired
    OKRServiceIml(OKRRepository repository){
        this.repository = repository;
    }

    public List<OKRDto> findAll() {
        List<OKR> okrs = repository.findAll();
        return convertToDTOs(okrs);
    }

    @Override
    public OKRDto findOne(int okrDtoId) {
        OKR okrs = repository.findOne(okrDtoId);
        if(okrs != null){
            return convertToDTO(okrs);
        }
        return null;
    }

    public OKRDto create(OKRDto okrDto) {
        OKR okrs = (OKR) repository.findOKRById(okrDto.getOkrID());

        if(okrs == null) {
            OKR persisted = OKR.getBuilder()
                    .okrID(okrDto.getOkrID())
                    .objective(okrDto.getObjective())
                    .okrType(okrDto.getOkrType())
                    .dueDate(okrDto.getDueDate())
                    .owner(okrDto.getOwner())
                    .keyResult(okrDto.getKeyResults())
                    .completionPoint(okrDto.getCompletionPoint())
                    .build();

            persisted = repository.save(persisted);
            return convertToDTO(persisted);
        }else {
            return convertToDTO(okrs);
        }
    }

    public OKR findOKRById(int okrID) {
        OKR result = repository.findOne(okrID);
        return result;
    }

    /**public List<OKR> findOKRByDueDate(Calendar dueDate) {
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
    }*/

    public OKRDto update(OKRDto user) {
        OKR updated = findOKRById(user.getOkrID());
        updated.setObjective(user.getObjective());
        updated.setOkrType(user.getOkrType());
        updated.setDueDate(user.getDueDate());
        updated.setDueDate(user.getDueDate());
        updated.setKeyResults(user.getKeyResults());
        updated.setCompletionPoint(user.getCompletionPoint());

        return convertToDTO(updated);
    }

    public OKRDto delete(int okrDtoID) {
        OKR deleted = findOKRById(okrDtoID);
        repository.delete(deleted);
        return convertToDTO(deleted);
    }

    private OKRDto convertToDTO(OKR model){
        OKRDto dto = new OKRDto();

        dto.setOkrID(model.getOkrID());
        dto.setObjective(model.getObjective());
        dto.setOkrType(model.getOkrType());
        dto.setDueDate(model.getDueDate());
        dto.setOwner(model.getOwner());
        dto.setKeyResults(model.getKeyResults());
        dto.setCompletionPoint(model.getCompletionPoint());

        return dto;
    }

    private List<OKRDto> convertToDTOs(List<OKR> models){
        return models.stream().map(this::convertToDTO).collect(toList());
    }
}
