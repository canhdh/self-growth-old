package com.selfgrowth.core.okrs.repository;


import com.selfgrowth.model.okr.OKR;
import com.selfgrowth.model.okrtype.OKRType;
import org.springframework.data.repository.CrudRepository;

import java.util.Calendar;
import java.util.List;

public interface OKRRepository extends CrudRepository<OKR, Integer>{
    /**
     * Save a new OKR entity to database
     * @param saved the information of saved OKR entry
     * @return the information of saved OKR entry
     */
    OKR save(OKR saved);

    /**
     * Find an OKR follow id
     * @param id the identification of OKR
     * @return the information of found OKR from database
     */
    OKR findOne(Integer id);

    /**
     * Find an OKR follow due date
     * @param dueDate deadline of OKR
     * @return the information of found OKR from database
     */
    OKR findOneByDueDate(Calendar dueDate);

    /**
     * Find an OKR follow okr type of okr
     * @param okrType type of okr
     * @return the information of found OKR from database
     */
    OKR findOneByType(OKRType okrType);

    /**
     * find all OKRs from database
     * @return all OKRs that are found from database
     */
    List<OKR> findAll();

    /**
     * change information of an OKR
     * @param okr the information of an OKR from database
     * @return this information of this updated OKR
     */
    OKR update (OKR okr);

    /**
     * delete an OKR is from database
     * @param okr an OKR is from database
     */
    void delete(OKR okr);

    /**
     * check an OKR exists or not
     * @param integer id of OKR user want to check
     * @return true if OKR exists or false if not
     */
    boolean existsById(Integer integer);
}
