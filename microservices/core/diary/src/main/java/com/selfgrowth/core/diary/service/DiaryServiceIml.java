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
            put = {@CachePut(value = "diaryCache", key = "#diary.diaryId")},
            evict = {@CacheEvict(value = "allDiaryCache", allEntries = true)}
    )
    public Diary create(Diary diary) {
        Diary create = findByDiaryId(diary.getDiaryId());
        if (create == null){
            Diary persisted = Diary.getBuilder()
                    .title(diary.getTitle())
                    .category(diary.getCategory())
                    .severity(diary.getSeverity())
                    .priority(diary.getPriority())
                    .mood(diary.getMood())
                    .location(diary.getLocation())
                    .picture(diary.getPicture())
                    .build();
            persisted = repository.save(persisted);
//            return convertToDTO(persisted);
            return persisted;
        } else {
            return null;
        }
    }

    @Override
    @Caching(
            put = {@CachePut(value = "diaryCache", key = "#diary.diaryId")},
            evict = {@CacheEvict(value = "allDiaryCache", allEntries = true)}
    )
    public Diary update(Diary diary) {
        Diary updated = findByDiaryId(diary.getDiaryId());
        if (updated != null) {
            updated.setTitle(diary.getTitle());
            updated.setCategory(diary.getCategory());
            updated.setSeverity(diary.getSeverity());
            updated.setPriority(diary.getPriority());
            updated.setMood(diary.getMood());
            updated.setLocation(diary.getLocation());
            updated.setPicture(diary.getPicture());
            repository.saveAndFlush(updated);
        }
//        return convertToDTO(updated);
        return updated;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "diaryCache", key = "#diaryId"),
                    @CacheEvict(value = "allDiaryCache", allEntries = true)
            }
    )
    public Diary delete(int diaryId) {
        Diary deleted = findByDiaryId(diaryId);
        if (deleted != null) {
            repository.deleteByDiaryId(deleted.getDiaryId());
//            return convertToDTO(deleted);
            return deleted;
        } else return null;
    }

    @Override
    @Cacheable(value = "diaryCache", key = "#diaryId")
    public Diary findByDiaryId(int diaryId) {
        Diary result = repository.findByDiaryId(diaryId).orElse(null);
        return result;
    }

//    @Override
//
//    public DiaryDto findByDiaryIdConvertToDto(int diaryId) {
//        Diary result = repository.findByDiaryId(diaryId).orElse(null);
//        return convertToDTO(result);
//    }

    @Override
    @Cacheable(value = "allDiaryCache", unless = "#result.size() == 0")
    public List<Diary> findAll() {
        List<Diary> diaryList = repository.findAll();
//        return convertToDTOs(diaryList);
        return diaryList;
    }

//    private List<DiaryDto> convertToDTOs(List<Diary> models) {
//        if (models != null)
//            return models.stream().map(this::convertToDTO).collect(toList());
//        else return null;
//    }
//
//    private DiaryDto convertToDTO(Diary model) {
//        DiaryDto dto = new DiaryDto();
//        if (model != null) {
//            dto.setTitle(model.getTitle());
//            dto.setCategory(model.getCategory());
//            dto.setSeverity((model.getSeverity()));
//            dto.setPriority(model.getPriority());
//            dto.setMood(model.getMood());
//            dto.setLocation(model.getLocation());
//            dto.setPicture(model.getPicture());
//            return dto;
//        } else return null;
//    }
}
