/**
 * Copyright 2011 Adam Ruggles.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.adamruggles.mars.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Entity Data Access Object.
 * @author Adam
 * @version $Id$
 *
 * Created on Sep 2, 2011 at 9:43:07 PM
 * @param <T> Model object the entity data access object operates on.
 */
public interface EntityDAO<T> {
    /**
     * Returns all objects of type T.
     * @return all objects of type T.
     */
    List<T> findAll();
    /**
     * Returns a list of all objects of type T.
     * @param index The index of the starting result.
     * @param max The max number of results to return.
     * @return A list of all objects of type T.
     */
    List<T> findAll(int index, int max);
    /**
     * Returns an entity by it's unique identifier.
     * @param id The identifier.
     * @return object of type T or null.
     */
    T findById(Serializable id);
    /**
     * Returns the total number of objects of type T.
     * @return The total number of objects of type T.
     */
    Long getCount();
    /**
     * Merges/Updates the entity in the data store.
     * @param entity The entity to merge.
     */
    void merge(T entity);
    /**
     * Persists/Inserts/Adds the entity to the data store.
     * @param entity The entity to persist.
     */
    void persist(T entity);
    /**
     * Removes/Deletes the entity from the data store.
     * @param entity The entity to remove.
     */
    void remove(T entity);
}
