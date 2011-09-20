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
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Discussion entity.
 * @author Adam
 * @version $Id$
 *
 * Created on Sep 2, 2011 at 9:19:43 PM
 */
@Entity @Table(name = "discussions")
@XmlRootElement(name = "discussion")
@XmlAccessorType(XmlAccessType.FIELD)
public class Discussion implements Serializable {
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 5159937823563513317L;
    /**
     * The content of the discussion.
     */
    @Lob
    private String body;
    /**
     * The ID.
     */
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * A List of posts for the discussion.
     */
    @OneToMany(mappedBy = "discussion")
    private List<Post> posts = new ArrayList<Post>();
    /**
     * The subject of the discussion.
     */
    private String subject;
    /**
     * The user that created the discussion.
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
        if (!(obj instanceof Discussion)) {
            return false;
        }
        final Discussion other = (Discussion) obj;
        if (body == null) {
            if (other.body != null) {
                return false;
            }
        } else if (!body.equals(other.body)) {
            return false;
        }
        if (subject == null) {
            if (other.subject != null) {
                return false;
            }
        } else if (!subject.equals(other.subject)) {
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
     * Returns id.
     * @return the id.
     */
    public Long getId() {
        return id;
    }
    /**
     * Returns posts.
     * @return the posts.
     */
    public List<Post> getPosts() {
        return posts;
    }
    /**
     * Returns subject.
     * @return the subject.
     */
    public String getSubject() {
        return subject;
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
        final int prime = 997;
        int result = 1;
        result = prime * result + ((body == null) ? 0 : body.hashCode());
        result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
     * Sets id.
     * @param id the id to set.
     */
    public void setId(final Long id) {
        this.id = id;
    }
    /**
     * Sets posts.
     * @param posts the posts to set.
     */
    public void setPosts(final List<Post> posts) {
        this.posts = posts;
    }
    /**
     * Sets subject.
     * @param subject the subject to set.
     */
    public void setSubject(final String subject) {
        this.subject = subject;
    }
    /**
     * Sets user.
     * @param user the user to set.
     */
    public void setUser(final User user) {
        this.user = user;
    }
}
