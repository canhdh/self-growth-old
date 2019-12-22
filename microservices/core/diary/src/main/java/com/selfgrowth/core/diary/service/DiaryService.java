package com.selfgrowth.core.diary.service;


import com.selfgrowth.model.diary.Diary;
import com.selfgrowth.model.diary.DiaryDto;

import java.util.List;

public interface DiaryService {
    DiaryDto create(DiaryDto diaryDto);

    Diary findByDiaryId(int diary);

    DiaryDto findByDiaryIdConvertToDto(int diary);

    List<DiaryDto> findAll();

    DiaryDto update(DiaryDto user);

    DiaryDto delete(int diaryId);
}
