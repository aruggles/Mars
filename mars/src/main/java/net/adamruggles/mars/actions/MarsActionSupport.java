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
package net.adamruggles.mars.actions;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Mars Action Support class containing default properties for actions.
 * @author Adam
 * @version $Id$
 *
 * Created on Sep 10, 2011 at 7:54:46 PM
 */
public class MarsActionSupport extends ActionSupport implements ServletRequestAware {
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -4465526881297451839L;
    /**
     * Used to track errors.
     */
    private boolean error = false;
    /**
     * The HTTP Servlet Request.
     */
    private HttpServletRequest request;
    /**
     * Default page title.
     */
    private String title = "Mars Application";
    /**
     * {@inheritDoc}
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    @Actions({
        @Action("/login"),
        @Action("/logout-success"),
        @Action("/index")
    })
    @Override
    public String execute() {
        return SUCCESS;
    }
    /**
     * Returns the security principal user.
     * @return The Principal or null.
     */
    public Principal getPrincipal() {
        if (request == null) {
            return null;
        }
        return request.getUserPrincipal();
    }
    /**
     * Returns title.
     * @return the title.
     */
    public String getTitle() {
        return title;
    }
    /**
     * Returns error.
     * @return the error.
     */
    public boolean isError() {
        return error;
    }
    /**
     * Sets error.
     * @param error the error to set.
     */
    public void setError(final boolean error) {
        this.error = error;
    }
    /**
     * {@inheritDoc}
     * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void setServletRequest(final HttpServletRequest request) {
        this.request = request;
    }
    /**
     * Sets title.
     * @param title the title to set.
     */
    public void setTitle(final String title) {
        this.title = title;
    }
}
