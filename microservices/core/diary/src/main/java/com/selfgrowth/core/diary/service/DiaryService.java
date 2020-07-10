package com.selfgrowth.core.diary.service;


import com.selfgrowth.model.diary.Diary;
import com.selfgrowth.model.diary.DiaryDto;

import java.util.List;

public interface DiaryService {
    DiaryDto create(DiaryDto diaryDto);

    Diary findByDiaryId(int diaryId);

    DiaryDto findByDiaryIdConvertToDto(int diaryId);

    List<DiaryDto> findAll(Long userId);

    DiaryDto update(DiaryDto diaryDto);

    DiaryDto delete(int diaryId);
}
