package com.selfgrowth.core.objective.repository;

import com.selfgrowth.model.objective.Objective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ObjectiveRepository extends JpaRepository<Objective,Integer> {
    /**
     * Delete a user entry from  the database.
     *
     * @param deleter The deleted user entry.
     */
    void delete(Objective deleter);

    void deleteByObjectiveID(int objectiveID);

    /**
     * Find all Objective entries from the database.
     *
     * @return The information of all Objective entries that are found from database.
     */
    List<Objective> findAll();

    /**
     * Finds the information of a single Objective entry.
     *
     * @param objectiveID The keyResultID of the requested user entry
     * @return The information of the found Objective entry
     */
    Optional<Objective> findByObjectiveID(int objectiveID);

    /**
     * r
     * Saves a new Objective entry to the database.
     *
     * @param saved The information of the saved Objective entry.
     * @return The information of the saved Objective entry.
     */

    Objective save(Objective saved);
}
