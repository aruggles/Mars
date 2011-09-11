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

import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import net.adamruggles.mars.entity.User;

/**
 * A JPA implementation of the {@link UserDAO}.
 * @author Adam
 * @version $Id$
 *
 * Created on Sep 2, 2011 at 11:31:03 PM
 */
@Named("userDAO")
public class UserJpaDAO extends AbstractEntityJpaDAO<User> implements UserDAO {
    /**
     * {@inheritDoc}
     * @see net.adamruggles.mars.dao.UserDAO#findByUsername(java.lang.String)
     */
    @Override
    public User findByUsername(final String username) {
        final Query q = getEm().createNamedQuery("user.findByUsername");
        q.setParameter("username", username);
        try {
            return (User) q.getSingleResult();
        } catch (final NoResultException ex) {
            return null;
        }
    }

}
