package com.selfgrowth.core.okrs.dao;

import com.selfgrowth.core.okrs.repository.OKRRepository;
import com.selfgrowth.model.okr.OKR;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OKRDAO implements OKRRepository {

    @Override
    public void delete(OKR deleted) {

    }

    @Override
    public void deleteAll(Iterable<? extends OKR> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends OKR> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<OKR> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<OKR> findAll() {
        return null;
    }

    @Override
    public Iterable<OKR> findAllById(Iterable<String> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public OKR findOne(int id) {
        return null;
    }

    @Override
    public OKR save(OKR saved) {
        return null;
    }

    @Override
    public List<OKR> findOKRById(int okrId) {
        return null;
    }

    @Override
    public Iterable<OKR> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<OKR> findAll(Pageable pageable) {
        return null;
    }
}
