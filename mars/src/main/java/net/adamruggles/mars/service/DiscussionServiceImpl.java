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

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

import net.adamruggles.mars.dao.DiscussionDAO;
import net.adamruggles.mars.dao.UserDAO;
import net.adamruggles.mars.entity.Discussion;
import net.adamruggles.mars.entity.User;
import net.adamruggles.mars.service.exception.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * An Implementation of the {@link DiscussionService}.
 * @author Adam
 * @version $Id$
 *
 * Created on Sep 16, 2011 at 11:00:05 PM
 */
@Named("discussionService")
public class DiscussionServiceImpl implements DiscussionService {
    /**
     * Discussion Data Access Object.
     */
    @Inject
    private DiscussionDAO discussionDAO;
    /**
     * The User Data Access Object.
     */
    @Inject
    private UserDAO userDAO;
    /**
     * {@inheritDoc}
     * @see net.adamruggles.mars.service.DiscussionService#create(net.adamruggles.mars.entity.Discussion)
     */
    @Override @Transactional
    public void create(final Discussion discussion) throws ServiceException {
        if (discussion == null || discussion.getUser() == null) {
            throw new ServiceException("Invalid parameters, discussion or user was null");
        }
        final String username = discussion.getUser().getUsername();
        try {
            final User user = userDAO.findByUsername(username);
            if (user == null) {
                throw new ServiceException("Invalid user");
            }
            final Discussion newDiscussion = new Discussion();
            newDiscussion.setUser(user);
            newDiscussion.setBody(discussion.getBody());
            newDiscussion.setSubject(discussion.getSubject());
            discussionDAO.persist(newDiscussion);
            discussion.setId(newDiscussion.getId());
            discussion.setUser(user);
        } catch (final PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        } catch (final IllegalArgumentException illegalArgEx) {
            new ServiceException(illegalArgEx);
        }
    }
    /**
     * {@inheritDoc}
     * @see net.adamruggles.mars.service.DiscussionService#delete(net.adamruggles.mars.entity.Discussion)
     */
    @Override @Transactional
    public void delete(final Discussion discussion) throws ServiceException {
        if (discussion == null || discussion.getId() == null) {
            throw new ServiceException("Attempting to delete discussion without an id.");
        }
        try {
            final Discussion pDiscussion = discussionDAO.findById(discussion.getId());
            if (pDiscussion == null) {
                throw new ServiceException("Discussion not found.");
            }
            discussionDAO.remove(pDiscussion);
        } catch (final TransactionRequiredException transRequiredEx) {
            throw new ServiceException(transRequiredEx);
        } catch (final IllegalArgumentException illegalArgEx) {
            throw new ServiceException(illegalArgEx);
        }
    }
    /**
     * {@inheritDoc}
     * @see net.adamruggles.mars.service.DiscussionService#get(java.lang.Long)
     */
    @Override @Transactional
    public Discussion get(final Long id) {
        if (id == null) {
            throw new ServiceException("Attempting to return discussion without an id.");
        }
        try {
            return discussionDAO.findById(id);
        } catch (final TransactionRequiredException transRequiredEx) {
            throw new ServiceException(transRequiredEx);
        } catch (final IllegalArgumentException illegalArgEx) {
            throw new ServiceException(illegalArgEx);
        }
    }
    /**
     * Sets discussionDAO.
     * @param discussionDAO the discussionDAO to set.
     */
    public void setDiscussionDAO(final DiscussionDAO discussionDAO) {
        this.discussionDAO = discussionDAO;
    }
    /**
     * Sets userDAO.
     * @param userDAO the userDAO to set.
     */
    public void setUserDAO(final UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    /**
     * {@inheritDoc}
     * @see net.adamruggles.mars.service.DiscussionService#update(net.adamruggles.mars.entity.Discussion)
     */
    @Override @Transactional
    public void update(final Discussion discussion) throws ServiceException {
        if (discussion == null || discussion.getId() == null) {
            throw new ServiceException("Attempting to update discussion without an id.");
        }
        try {
            final Discussion pDiscussion = discussionDAO.findById(discussion.getId());
            if (pDiscussion == null) {
                throw new ServiceException("Discussion not found.");
            }
            if (StringUtils.isNotEmpty(discussion.getBody())) {
                pDiscussion.setBody(discussion.getBody());
            }
            if (StringUtils.isNotEmpty(discussion.getSubject())) {
                pDiscussion.setSubject(discussion.getSubject());
            }
            final User user = discussion.getUser();
            if (user != null && StringUtils.isNotEmpty(user.getUsername())) {
                final User pUser = userDAO.findByUsername(user.getUsername());
                if (pUser != null) {
                    pDiscussion.setUser(pUser);
                }
            }
            discussion.setBody(pDiscussion.getBody());
            discussion.setSubject(pDiscussion.getSubject());
            discussion.setUser(pDiscussion.getUser());
        } catch (final TransactionRequiredException transRequiredEx) {
            throw new ServiceException(transRequiredEx);
        } catch (final IllegalArgumentException illegalArgEx) {
            throw new ServiceException(illegalArgEx);
        }
    }
}
