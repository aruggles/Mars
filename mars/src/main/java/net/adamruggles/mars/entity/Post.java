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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Post model object.
 * @author Adam
 * @version $Id$
 *
 * Created on Sep 2, 2011 at 9:19:43 PM
 */
@Entity @Table(name = "posts")
@XmlRootElement(name = "post")
@XmlAccessorType(XmlAccessType.FIELD)
public class Post implements Serializable {
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -5793166658253296596L;
    /**
     * The body of the post.
     */
    @Lob
    private String body;
    /**
     * The discussion the posts belongs to.
     */
    @ManyToOne
    private Discussion discussion;
    /**
     * The ID.
     */
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * The user that created the post.
     */
    @ManyToOne
    private User user;
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
        if (!(obj instanceof Post)) {
            return false;
        }
        final Post other = (Post) obj;
        if (body == null) {
            if (other.body != null) {
                return false;
            }
        } else if (!body.equals(other.body)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }
    /**
     * Returns body.
     * @return the body.
     */
    public String getBody() {
        return body;
    }
    /**
     * Returns discussion.
     * @return the discussion.
     */
    public Discussion getDiscussion() {
        return discussion;
    }
    /**
     * Returns id.
     * @return the id.
     */
    public Long getId() {
        return id;
    }
    /**
     * Returns user.
     * @return the user.
     */
    public User getUser() {
        return user;
    }
    /**
     * {@inheritDoc}
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 443;
        int result = 1;
        result = prime * result + ((body == null) ? 0 : body.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    /**
     * Sets body.
     * @param body the body to set.
     */
    public void setBody(final String body) {
        this.body = body;
    }
    /**
     * Sets discussion.
     * @param discussion the discussion to set.
     */
    public void setDiscussion(final Discussion discussion) {
        this.discussion = discussion;
    }
    /**
     * Sets id.
     * @param id the id to set.
     */
    public void setId(final Long id) {
        this.id = id;
    }
    /**
     * Sets user.
     * @param user the user to set.
     */
    public void setUser(final User user) {
        this.user = user;
    }
}
