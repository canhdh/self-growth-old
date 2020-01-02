package com.selfgrowth.core.diary.service;


import com.selfgrowth.model.diary.Diary;
import com.selfgrowth.model.diary.DiaryDto;

import java.util.List;

public interface DiaryService {
    Diary create(Diary diary);

    Diary findByDiaryId(int diaryId);

    List<Diary> findAll();

    Diary update(Diary diary);

    Diary delete(int diaryId);
}
