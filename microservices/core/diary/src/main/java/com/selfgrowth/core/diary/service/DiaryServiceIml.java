package com.selfgrowth.core.diary.service;


import com.selfgrowth.core.diary.repository.DiaryRepository;
import com.selfgrowth.model.diary.Diary;
import com.selfgrowth.model.diary.DiaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class DiaryServiceIml implements DiaryService {
    private final DiaryRepository repository;

    @Autowired
    DiaryServiceIml(DiaryRepository repository) {

        this.repository = repository;
    }

    @Override
    @Caching(
            put = {@CachePut(value = "DiaryCache", key = "#diaryID")},
            evict = {@CacheEvict(value = "allDiaryCache", allEntries = true)}
    )
    public DiaryDto create(DiaryDto diaryDto) {
        Diary diary = findByDiaryID(diaryDto.getDiaryID());
        if (diary == null){
            Diary persisted = Diary.getBuilder()
                    .DiaryID(diarytDto.getDiaryID())
                    .CompletionPoint(diaryDto.getComletionPoint())
                    .Duedate(diaryDto.getDueDate())
                    .Steps(diaryDto.getSteps())
                    .Title(diaryDto.getTitle())
                    .build();
            persisted = repository.save(persisted);
            return convertToDTO(persisted);
        } else {
            return null;
        }
    }

    @Override
    @Caching(
            put = {@CachePut(value = "DiaryCache", key = "#diaryID")},
            evict = {@CacheEvict(value = "allDiaryCache", allEntries = true)}
    )
    public DiaryDto update(DiaryDto user) {
        DiaryDto updated = findByDairyID(user.getDiaryID());
        if (updated != null) {
            updated.setDiaryID(user.getDiaryID());
            updated.setComletionPoint(user.getComletionPoint());
            updated.setDueDate(user.getDueDate());
            updated.setSteps(user.getSteps());
            updated.setTitle(user.getTitle());
            repository.saveAndFlush(updated);
        }
        return convertToDTO(updated);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "diaryCache", key = "#diaryID"),
                    @CacheEvict(value = "allDiaryCache", allEntries = true)
            }
    )
    public DiaryDto delete(int diaryID) {
        Diary deleted = findByDiaryID(diaryID);
        if (deleted != null) {
            repository.deleteByDiaryID(deleted.getDiaryID());
            return convertToDTO(deleted);
        } else return null;
    }

    @Override
    public Diary findByDiaryID(int diaryID) {
        Diary result = repository.findByDiaryID(DiaryID).orElse(null);
        return result;
    }

    @Override
    @Cacheable(value = "diaryCache", key = "#diaryID")
    public DiaryDto findByDiaryIDConvertToDto(int diaryID) {
        Diary result = repository.findByDiaryID(diaryID).orElse(null);
        return convertToDTO(result);
    }

    @Override
    @Cacheable(value = "allDiaryCache", unless = "#result.size() == 0")
    public List<DiaryDto> findAll() {
        List<Diary> diary = repository.findAll();
        return convertToDTOs(diary);
    }

    private List<DiaryDto> convertToDTOs(List<Diary> models) {
        if (models != null)
            return models.stream().map(this::convertToDTO).collect(toList());
        else return null;
    }

    private DiaryDto convertToDTO(Diary model) {
        DiaryDto dto = new DiaryDto();
        if (model != null) {
            dto.setDiaryID(model.getDiaryID());
            dto.setComletionPoint(model.getComletionPoint());
            dto.setDueDate(model.getDueDate());
            dto.setSteps(model.getSteps());
            dto.setTitle(model.getTitle());
            return dto;
        } else return null;
    }
}
