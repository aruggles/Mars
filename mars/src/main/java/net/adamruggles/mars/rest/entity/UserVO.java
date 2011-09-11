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
package net.adamruggles.mars.rest.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import net.adamruggles.mars.entity.User;

/**
 * REST User entity.
 * @author Adam
 * @version $Id$
 *
 * Created on Sep 3, 2011 at 9:25:17 AM
 */
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserVO {
    /**
     * Identifier.
     */
    private Long id;
    /**
     * The password for the user.
     */
    private String password;
    /**
     * The username for the user.
     */
    private String username;
    /**
     * Default constructor.
     */
    public UserVO() { }
    /**
     * Constructs a REST User entity from a {@link User}.
     * @param user A {@link User}.
     */
    public UserVO(final User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
    /**
     * Returns id.
     * @return the id.
     */
    public Long getId() {
        return id;
    }
    /**
     * Returns password.
     * @return the password.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Returns username.
     * @return the username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Sets id.
     * @param id the id to set.
     */
    public void setId(final Long id) {
        this.id = id;
    }
    /**
     * Sets password.
     * @param password the password to set.
     */
    public void setPassword(final String password) {
        this.password = password;
    }
    /**
     * Sets username.
     * @param username the username to set.
     */
    public void setUsername(final String username) {
        this.username = username;
    }
}
