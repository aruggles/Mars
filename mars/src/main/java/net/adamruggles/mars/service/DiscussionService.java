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
package net.adamruggles.mars.service;

import net.adamruggles.mars.entity.Discussion;
import net.adamruggles.mars.service.exception.ServiceException;

/**
 * Discussion Service.
 * @author Adam
 * @version $Id$
 *
 * Created on Sep 16, 2011 at 10:59:37 PM
 */
public interface DiscussionService {
    /**
     * Creates a discussion.
     * @param discussion The discussion to create.
     * @throws ServiceException if an error occurs saving the discussion.
     */
    void create(Discussion discussion) throws ServiceException;
    /**
     * Deletes a discussion.
     * @param discussion The discussion to delete.  ID is a required field.
     * @throws ServiceException If the an error occurs deleting the discussion.
     */
    void delete(Discussion discussion) throws ServiceException;
    /**
     * Returns a discussion by id.
     * @param id The id of the discussion.
     * @return A {@link Discussion) or null.
     */
    Discussion get(Long id);
    /**
     * Updates a discussion.
     * Any fields that are null will not be updated.  ID is a required field.
     * @param discussion The discussion to update.
     * @throws ServiceException if an error occurs updating the discussion.
     */
    void update(Discussion discussion) throws ServiceException;
}
