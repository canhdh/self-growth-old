package com.selfgrowth.core.diary.repository;


import com.selfgrowth.model.diary.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Integer> {
    void delete(Diary deleter);

    void deleteByDiaryID(int DiaryID);

     List<Diary> findAll();

    Optional<Diary> findByDiaryID(int DiaryID);

    Diary save(Diary saved);
}

