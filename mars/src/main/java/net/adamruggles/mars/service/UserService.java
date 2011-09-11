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

import net.adamruggles.mars.rest.entity.UserVO;
import net.adamruggles.mars.service.exception.ServiceException;

/**
 * User Service.
 * @author Adam
 * @version $Id$
 *
 * Created on Sep 3, 2011 at 10:30:35 AM
 */
public interface UserService {
    /**
     * Creates a user.
     * The id and hashed password will be populated when returning from this method.
     * @param userVO The user to create.
     * @throws ServiceException if an error occurs saving the user object.
     */
    void create(UserVO userVO) throws ServiceException;
    /**
     * Delets a user.
     * @param userVO The user to delete.
     */
    void delete(UserVO userVO);
    /**
     * Returns a user by id.
     * @param id The id of the user.
     * @return A {@link User) or null.
     */
    UserVO get(Long id);
    /**
     * Returns a user by their username.
     * @param username The username.
     * @return A {@link UserVO} or null.
     */
    UserVO getByUsername(String username);
    /**
     * Updates a user.
     * Any fields that are null will not be updated.
     * @param userVO The user to update.
     */
    void update(UserVO userVO);
}
