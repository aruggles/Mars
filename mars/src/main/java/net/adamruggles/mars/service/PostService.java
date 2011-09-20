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

import net.adamruggles.mars.entity.Post;
import net.adamruggles.mars.service.exception.ServiceException;

/**
 * Post Service.
 * @author Adam
 * @version $Id$
 *
 * Created on Sep 19, 2011 at 10:58:50 PM
 */
public interface PostService {
    /**
     * Creates a post.
     * @param post The post to create.
     * @throws ServiceException if an error occurs saving the post.
     */
    void create(Post post) throws ServiceException;
    /**
     * Deletes a post.
     * @param post The post to delete.  ID is a required field.
     * @throws ServiceException If the an error occurs deleting the post.
     */
    void delete(Post post) throws ServiceException;
    /**
     * Returns a post by id.
     * @param id The id of the post.
     * @return A {@link Discussion) or null.
     */
    Post get(Long id);
    /**
     * Updates a post.
     * Any fields that are null will not be updated.  ID is a required field.
     * @param post The post to update.
     * @throws ServiceException if an error occurs updating the post.
     */
    void update(Post post) throws ServiceException;
}
