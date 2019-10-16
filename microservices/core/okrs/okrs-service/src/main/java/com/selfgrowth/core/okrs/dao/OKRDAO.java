package com.selfgrowth.core.okrs.dao;

import com.selfgrowth.core.okrs.model.DataOKR;
import com.selfgrowth.core.okrs.repository.OKRRepository;
import com.selfgrowth.model.okr.OKR;
import com.selfgrowth.model.okrtype.OKRType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class OKRDAO implements OKRRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public OKRDAO(){

    }

    @Override
    public OKR save(OKR saved) {
        return null;
    }

    @Override
    public OKR findOne(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(OKR.class, id);
    }

    @Override
    public OKR findOneByDueDate(Calendar dueDate) {
        return null;
    }

    @Override
    public OKR findOneByType(OKRType okrType) {
        return null;
    }

    @Override
    public List<OKR> findAll() {
        return null;
    }

    @Override
    public Iterable<OKR> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public OKR update(OKR okr) {
        return null;
    }

    @Override
    public void delete(OKR okr) {

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
    public Optional<OKR> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }
}
