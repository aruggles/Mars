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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;

import net.adamruggles.mars.dao.UserDAO;
import net.adamruggles.mars.entity.User;
import net.adamruggles.mars.rest.entity.UserVO;
import net.adamruggles.mars.service.exception.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

/**
 * An Implementation of the user service.
 * @author Adam
 * @version $Id$
 *
 * Created on Sep 3, 2011 at 10:36:22 AM
 */
@Named("userService")
public class UserServiceImpl implements UserService {
    /**
     * Logger.
     */
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    /**
     * The User Data Access Object.
     */
    @Inject
    private UserDAO userDAO;
    /**
     * Password encoder from spring security.
     */
    @Inject
    private PasswordEncoder passwordEncoder;
    /**
     * {@inheritDoc}
     * @see net.adamruggles.mars.service.UserService#create(net.adamruggles.mars.rest.entity.UserVO)
     */
    @Override @Transactional
    public void create(final UserVO userVO) throws ServiceException {
        logger.info("Creating a user with the following information: {}", ToStringBuilder.reflectionToString(userVO));
        final User user = new User(userVO);
        // Create the hashed password.
        user.setPassword(passwordEncoder.encodePassword(userVO.getPassword(), userVO.getUsername()));
        try {
            userDAO.persist(user);
        } catch (final PersistenceException ex) {
            throw new ServiceException(ex);
        }
        // Update the user record with the id and password hash.
        userVO.setId(user.getId());
        userVO.setPassword(user.getPassword());
    }
    /**
     * {@inheritDoc}
     * @see net.adamruggles.mars.service.UserService#delete(net.adamruggles.mars.rest.entity.UserVO)
     */
    @Override @Transactional
    public void delete(final UserVO userVO) {
        if (userVO == null || userVO.getId() == null) {
            throw new ServiceException("Attempting to delete user without a user id.");
        }
        final User user = userDAO.findById(userVO.getId());
        if (user == null) {
            throw new ServiceException("User not found.");
        }
        userDAO.remove(user);
    }
    /**
     * {@inheritDoc}
     * @see net.adamruggles.mars.service.UserService#get(java.lang.Long)
     */
    @Override @Transactional(readOnly = true)
    public UserVO get(final Long id) {
        if (id == null) {
            throw new ServiceException("Attempting to return user without a id.");
        }
        final User user = userDAO.findById(id);
        if (user == null) {
            return null;
        }
        return new UserVO(user);
    }
    /**
     * {@inheritDoc}
     * @see net.adamruggles.mars.service.UserService#getByUsername(java.lang.String)
     */
    @Override @Transactional(readOnly = true)
    public UserVO getByUsername(final String username) {
        if (username == null) {
            throw new ServiceException("Attempting to get user without a username.");
        }
        final User user = userDAO.findByUsername(username);
        if (user == null) {
            return null;
        }
        return new UserVO(user);
    }
    /**
     * Returns a hashed version of the password.
     * @param password The password to hash.
     * @return a hashed password.
     */
    private String getHash(final String password) {
        try {
            final byte[] message = password.getBytes("UTF-8");
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update("Sasdf#$A7T0^00asdf0000!@1".getBytes());
            return new String(digest.digest(message), "UTF-8");
        } catch (final UnsupportedEncodingException ex) {
            throw new ServiceException("Unable to hash the password.", ex);
        } catch (final NoSuchAlgorithmException ex) {
            throw new ServiceException("Unable to hash the password.", ex);
        }
    }
    /**
     * Sets passwordEncoder.
     * @param passwordEncoder the passwordEncoder to set.
     */
    public void setPasswordEncoder(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
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
     * @see net.adamruggles.mars.service.UserService#update(net.adamruggles.mars.rest.entity.UserVO)
     */
    @Override @Transactional
    public void update(final UserVO userVO) {
        if (userVO == null || userVO.getId() == null) {
            throw new ServiceException("Attempting to update user without a user id.");
        }
        final User user = userDAO.findById(userVO.getId());
        if (user == null) {
            throw new ServiceException("User not found.");
        }
        if (StringUtils.isNotEmpty(userVO.getPassword())) {
            user.setPassword(getHash(userVO.getPassword()));
        }
        if (StringUtils.isNotEmpty(userVO.getUsername())) {
            user.setUsername(getHash(userVO.getUsername()));
        }
        // Update the values from the data store.
        userVO.setUsername(user.getUsername());
        userVO.setPassword(user.getPassword());
    }
}
