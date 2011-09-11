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
package net.adamruggles.mars.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import net.adamruggles.mars.dao.AbstractEntityJpaDAO;
import net.adamruggles.mars.entity.User;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 * An implementation of the spring security {@link UserDetailsService}.
 * @author adamruggles
 * @version $Id$
 *
 * Created on Sep 5, 2011 at 9:21:32 PM
 */
@Named("securityDetailsService")
public class SecurityDetailsService extends AbstractEntityJpaDAO<User> implements UserDetailsService {

    /**
     * Returns a collection of granted authority for a given user entity.
     * @param user The user entity.
     * @return A collection of granted authority.
     */
    private Collection<GrantedAuthority> getAuthorities(final User user) {
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(2);
        authorities.add(new GrantedAuthorityImpl("ROLE_USER"));
        if (user.isAdmin()) {
            authorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
        }
        return authorities;
    }

    /**
     * {@inheritDoc}
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override @Transactional
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException, DataAccessException {
        final Query q = getEm().createNamedQuery("user.findByUsername");
        q.setParameter("username", username);
        try {
            final User user = (User) q.getSingleResult();
            final org.springframework.security.core.userdetails.User userDetails
                = new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        user.isEnabled(), true, true, true, getAuthorities(user));
            return userDetails;
        } catch (final NoResultException ex) {
            throw new UsernameNotFoundException(ex.getMessage());
        }
    }

}
