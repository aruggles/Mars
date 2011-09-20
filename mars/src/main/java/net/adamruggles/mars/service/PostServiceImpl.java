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
import net.adamruggles.mars.dao.PostDAO;
import net.adamruggles.mars.dao.UserDAO;
import net.adamruggles.mars.entity.Discussion;
import net.adamruggles.mars.entity.Post;
import net.adamruggles.mars.entity.User;
import net.adamruggles.mars.service.exception.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * An Implementation of the {@link PostService}.
 * @author Adam
 * @version $Id$
 *
 * Created on Sep 19, 2011 at 11:00:49 PM
 */
@Named("postService")
public class PostServiceImpl implements PostService {
    /**
     * The Post Data Access Object.
     */
    @Inject
    private PostDAO postDAO;
    /**
     * The User Data Access Object.
     */
    @Inject
    private UserDAO userDAO;
    /**
     * The Discussion Data Access Object.
     */
    @Inject
    private DiscussionDAO discussionDAO;
    /**
     * {@inheritDoc}
     * @see net.adamruggles.mars.service.PostService#create(net.adamruggles.mars.entity.Post)
     */
    @Override @Transactional
    public void create(final Post post) throws ServiceException {
        if (post == null || post.getUser() == null || post.getDiscussion() == null) {
            throw new ServiceException("Invalid parameters, post, discussion or user was null");
        }
        final String username = post.getUser().getUsername();
        try {
            final User user = userDAO.findByUsername(username);
            if (user == null) {
                throw new ServiceException("Invalid user");
            }
            final Discussion discussion = discussionDAO.findById(post.getDiscussion().getId());
            if (discussion == null) {
                throw new ServiceException("Invalid discussion");
            }
            final Post newPost = new Post();
            newPost.setUser(user);
            newPost.setDiscussion(discussion);
            newPost.setBody(post.getBody());
            postDAO.persist(newPost);
            discussion.setId(newPost.getId());
            discussion.setUser(user);
        } catch (final PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        } catch (final IllegalArgumentException illegalArgEx) {
            new ServiceException(illegalArgEx);
        }
    }
    /**
     * {@inheritDoc}
     * @see net.adamruggles.mars.service.PostService#delete(net.adamruggles.mars.entity.Post)
     */
    @Override @Transactional
    public void delete(final Post post) throws ServiceException {
        if (post == null || post.getId() == null) {
            throw new ServiceException("Attempting to delete post without an id.");
        }
        try {
            final Post pPost = postDAO.findById(post.getId());
            if (pPost == null) {
                throw new ServiceException("Post not found.");
            }
            postDAO.remove(pPost);
        } catch (final TransactionRequiredException transRequiredEx) {
            throw new ServiceException(transRequiredEx);
        } catch (final IllegalArgumentException illegalArgEx) {
            throw new ServiceException(illegalArgEx);
        }

    }
    /**
     * {@inheritDoc}
     * @see net.adamruggles.mars.service.PostService#get(java.lang.Long)
     */
    @Override @Transactional
    public Post get(final Long id) {
        if (id == null) {
            throw new ServiceException("Attempting to return post without an id.");
        }
        try {
            return postDAO.findById(id);
        } catch (final TransactionRequiredException transRequiredEx) {
            throw new ServiceException(transRequiredEx);
        } catch (final IllegalArgumentException illegalArgEx) {
            throw new ServiceException(illegalArgEx);
        }
    }

    /**
     * Sets postDAO.
     * @param postDAO the postDAO to set.
     */
    public void setPostDAO(final PostDAO postDAO) {
        this.postDAO = postDAO;
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
     * @see net.adamruggles.mars.service.PostService#update(net.adamruggles.mars.entity.Post)
     */
    @Override @Transactional
    public void update(final Post post) throws ServiceException {
        if (post == null || post.getId() == null) {
            throw new ServiceException("Attempting to update post without an id.");
        }
        try {
            final Post pPost = postDAO.findById(post.getId());
            if (pPost == null) {
                throw new ServiceException("Discussion not found.");
            }
            if (StringUtils.isNotEmpty(post.getBody())) {
                pPost.setBody(post.getBody());
            }
            final Discussion discussion = post.getDiscussion();
            if (discussion != null && discussion.getId() != null) {
                final Discussion pDiscussion = discussionDAO.findById(discussion.getId());
                pPost.setDiscussion(pDiscussion);
            }
            final User user = post.getUser();
            if (user != null && StringUtils.isNotEmpty(user.getUsername())) {
                final User pUser = userDAO.findByUsername(user.getUsername());
                if (pUser != null) {
                    pPost.setUser(pUser);
                }
            }
            post.setBody(pPost.getBody());
            post.setDiscussion(pPost.getDiscussion());
            post.setUser(pPost.getUser());
        } catch (final TransactionRequiredException transRequiredEx) {
            throw new ServiceException(transRequiredEx);
        } catch (final IllegalArgumentException illegalArgEx) {
            throw new ServiceException(illegalArgEx);
        }
    }

}
