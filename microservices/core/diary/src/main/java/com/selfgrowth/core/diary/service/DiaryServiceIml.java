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
            put = {@CachePut(value = "DiaryCache", key = "#diaryId")},
            evict = {@CacheEvict(value = "allDiaryCache", allEntries = true)}
    )
    public DiaryDto create(DiaryDto diaryDto) {
        Diary create = findByDiaryId(diaryDto.getDiaryId());
        if (create == null){
            Diary persisted = Diary.getBuilder()
                    .diaryId(diaryDto.getDiaryId())
                    .completionPoint(diaryDto.getCompletionPoint())
                    .duedate(diaryDto.getDueDate())
                    .steps(diaryDto.getSteps())
                    .title(diaryDto.getTitle())
                    .build();
            persisted = repository.save(persisted);
            return convertToDTO(persisted);
        } else {
            return null;
        }
    }

    @Override
    @Caching(
            put = {@CachePut(value = "DiaryCache", key = "#diaryId")},
            evict = {@CacheEvict(value = "allDiaryCache", allEntries = true)}
    )
    public DiaryDto update(DiaryDto diaryDto) {
        Diary updated = findByDiaryId(diaryDto.getDiaryId());
        if (updated != null) {
            updated.setDiaryId(diaryDto.getDiaryId());
            updated.setCompletionPoint(diaryDto.getCompletionPoint());
            updated.setDueDate(diaryDto.getDueDate());
            updated.setSteps(diaryDto.getSteps());
            updated.setTitle(diaryDto.getTitle());
            repository.saveAndFlush(updated);
        }
        return convertToDTO(updated);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "diaryCache", key = "#diaryId"),
                    @CacheEvict(value = "allDiaryCache", allEntries = true)
            }
    )
    public DiaryDto delete(int diaryId) {
        Diary deleted = findByDiaryId(diaryId);
        if (deleted != null) {
            repository.deleteByDiaryId(deleted.getDiaryId());
            return convertToDTO(deleted);
        } else return null;
    }

    @Override
    public Diary findByDiaryId(int diaryId) {
        Diary diary = repository.findByDiaryId(diaryId).orElse(null);
        return diary;
    }

    @Override
    @Cacheable(value = "diaryCache", key = "#diaryId")
    public DiaryDto findByDiaryIdConvertToDto(int diaryId) {
        Diary diary = repository.findByDiaryId(diaryId).orElse(null);
        return convertToDTO(diary);
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
        DiaryDto diaryDto = new DiaryDto();
        if (model != null) {
            diaryDto.setDiaryId(model.getDiaryId());
            diaryDto.setCompletionPoint(model.getCompletionPoint());
            diaryDto.setDueDate(model.getDueDate());
            diaryDto.setSteps(model.getSteps());
            diaryDto.setTitle(model.getTitle());
            return diaryDto;
        } else return null;
    }
}
