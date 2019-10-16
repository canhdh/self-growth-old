package com.selfgrowth.core.okrs.repository;

import com.selfgrowth.model.okr.OKR;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

import java.util.List;

public interface OKRRepository extends PagingAndSortingRepository<OKR, String> {
    /**
     * Deletes a user entry from the database.
     *
     * @param deleted The deleted user entry.
     */
    void delete(OKR deleted);

    /**
     * Finds all OKR entries from the database.
     *
     * @return The information of all OKR entries that are found from the database.
     */
    List<OKR> findAll();

    /**
     * Finds the information of a single OKR entry.
     *
     * @param id The id of the requested user entry.
     * @return The information of the found OKR entry
     */
    OKR findOne(int id);

    /**
     * r
     * Saves a new OKR entry to the database.
     *
     * @param saved The information of the saved OKR entry.
     * @return The information of the saved OKR entry.
     */
    OKR save(OKR saved);

    List<OKR> findOKRById(int okrId);
    //List<OKR> findByType()
}