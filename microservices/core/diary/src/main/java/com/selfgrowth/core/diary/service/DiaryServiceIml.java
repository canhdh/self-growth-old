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
import java.util.Date;
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
            put = {@CachePut(value = "diaryCache", key = "#diaryDto.diaryId")},
            evict = {@CacheEvict(value = "allDiaryCache", allEntries = true)}
    )
    public DiaryDto create(DiaryDto diaryDto) {
        Date now = new Date();
        now.getTime();
        Diary persisted = Diary.getBuilder()
                .diaryId(diaryDto.getDiaryId())
                .title(diaryDto.getTitle())
                .category(diaryDto.getCategory())
                .severity(diaryDto.getSeverity())
                .priority(diaryDto.getPriority())
                .mood(diaryDto.getMood())
                .location(diaryDto.getLocation())
                .picture(diaryDto.getPicture())
                .createAt(now)
                .updateAt(now)
                .userId(diaryDto.getUserId())
                .build();
        persisted = repository.save(persisted);
        return convertToDTO(persisted);
    }

    @Override
    @Caching(
            put = {@CachePut(value = "diaryCache", key = "#diaryDto.diaryId")},
            evict = {@CacheEvict(value = "allDiaryCache", allEntries = true)}
    )
    public DiaryDto update(DiaryDto diaryDto) {
        Date now = new Date();
        now.getTime();
        Diary updated = findByDiaryId(diaryDto.getDiaryId());
        if (updated != null) {
            updated.setDiaryId(diaryDto.getDiaryId());
            updated.setTitle(diaryDto.getTitle());
            updated.setCategory(diaryDto.getCategory());
            updated.setSeverity(diaryDto.getSeverity());
            updated.setPriority(diaryDto.getPriority());
            updated.setMood(diaryDto.getMood());
            updated.setLocation(diaryDto.getLocation());
            updated.setPicture(diaryDto.getPicture());
            updated.setCreateAt(diaryDto.getCreateAt());
            updated.setUpdateAt(now);
            updated.setUserId(diaryDto.getUserId());
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
        Diary deleter = findByDiaryId(diaryId);
        if (deleter != null) {
            repository.delete(deleter);
            return convertToDTO(deleter);
//            return deleter;
        } else return null;
    }

    @Override
    public Diary findByDiaryId(int diaryId) {
        Diary result = repository.findByDiaryId(diaryId).orElse(null);
        return result;
    }

    @Override
    @Cacheable(value = "diaryCache", key = "#diaryId")
    public DiaryDto findByDiaryIdConvertToDto(int diaryId) {
        Diary result = repository.findByDiaryId(diaryId).orElse(null);
        return convertToDTO(result);
    }

    @Override
    @Cacheable(value = "allDiaryCache", key = "#userId",unless = "#result.size() == 0")
    public List<DiaryDto> findAll(Long userId) {
        List<Diary> diaryList = repository.findAllByUserId(userId);
        return convertToDTOs(diaryList);
    }

    private List<DiaryDto> convertToDTOs(List<Diary> models) {
        if (models != null)
            return models.stream().map(this::convertToDTO).collect(toList());
        else return null;
    }

    private DiaryDto convertToDTO(Diary model) {
        DiaryDto dto = new DiaryDto();
        if (model != null) {
            dto.setDiaryId(model.getDiaryId());
            dto.setTitle(model.getTitle());
            dto.setCategory(model.getCategory());
            dto.setSeverity((model.getSeverity()));
            dto.setPriority(model.getPriority());
            dto.setMood(model.getMood());
            dto.setLocation(model.getLocation());
            dto.setPicture(model.getPicture());
            dto.setCreateAt(model.getCreateAt());
            dto.setUpdateAt(model.getUpdateAt());
            dto.setUserId(model.getUserId());
            return dto;
        } else return null;
    }
}
