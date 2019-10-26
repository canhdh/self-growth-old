package com.selfgrowth.core.keyresult.repository;

import com.selfgrowth.model.keyResult.KeyResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeyResultRepository extends JpaRepository<KeyResult, Integer> {
    /**
     * Delete a user entry from  the database.
     *
     * @param deleter The deleted user entry.
     */
    void delete(KeyResult deleter);
    /**
     * Find all KeyResult entries from the database.
     *
     * @return The information of all KeyResult entries that are found from database.
     */
    List<KeyResult> findAll();

    /**
     * Finds the information of a single KeyResult entry.
     *
     * @param keyResultID The keyResultID of the requested user entry
     * @return The information of the found KeyResult entry
     */
    Optional<KeyResult> findByKeyResultID(int keyResultID);

    /**
     * r
     * Saves a new KeyResult entry to the database.
     *
     * @param saved The information of the saved KeyResult entry.
     * @return The information of the saved KeyResult entry.
     */

    KeyResult save(KeyResult saved);
}
