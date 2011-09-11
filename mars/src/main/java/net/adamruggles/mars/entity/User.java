/**
 *
 */
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
package net.adamruggles.mars.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import net.adamruggles.mars.rest.entity.UserVO;

/**
 * User model object.
 * @author Adam
 * @version $Id$
 *
 * Created on Sep 2, 2011 at 9:19:43 PM
 */
@Entity @Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "user.findByUsername",
    query = "SELECT u FROM User u WHERE u.username = :username") })
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable {
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 2618258556687903452L;
    /**
     * If the account is an administrator or normal user.
     */
    @XmlTransient
    private boolean admin;
    /**
     * If the user record is enabled.
     */
    @XmlTransient
    private boolean enabled;
    /**
     * ID.
     */
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * The password of the user.
     */
    @XmlTransient
    private String password;
    /**
     * The username of the user.
     */
    @Column(unique = true)
    private String username;
    /**
     * Default constructor.
     */
    public User() { }
    /**
     * Constructs a user entity populated with by a {@link UserVO}.
     * @param userVO The {@link UserVO}.
     */
    public User(final UserVO userVO) {
        id = userVO.getId();
        password = userVO.getPassword();
        username = userVO.getUsername();
        enabled = true;
        admin = false;
    }
    /**
     * {@inheritDoc}
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        final User other = (User) obj;
        if (username == null) {
            if (other.username != null) {
                return false;
            }
        } else if (!username.equals(other.username)) {
            return false;
        }
        return true;
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
     * {@inheritDoc}
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }
    /**
     * Returns admin.
     * @return the admin.
     */
    public boolean isAdmin() {
        return admin;
    }
    /**
     * Returns enabled.
     * @return the enabled.
     */
    public boolean isEnabled() {
        return enabled;
    }
    /**
     * Sets admin.
     * @param admin the admin to set.
     */
    public void setAdmin(final boolean admin) {
        this.admin = admin;
    }
    /**
     * Sets enabled.
     * @param enabled the enabled to set.
     */
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
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
