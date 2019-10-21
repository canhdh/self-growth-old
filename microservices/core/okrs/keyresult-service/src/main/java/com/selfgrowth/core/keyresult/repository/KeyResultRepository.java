package repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.selfgrowth.model.keyResult.KeyResult;

import java.util.List;

public interface KeyResultRepository extends PagingAndSortingRepository<KeyResult,String> {
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
     * @param id The id of the requested user entry
     * @return The information of the found KeyResult entry
     */
    KeyResult findOne(int id);

    /**
     * r
     * Saves a new KeyResult entry to the database.
     *
     * @param saved The information of the saved KeyResult entry.
     * @return The information of the saved KeyResult entry.
     */

    KeyResult save(KeyResult saved);

    List<KeyResult> findKeyResultById(int keyResultId);
}
