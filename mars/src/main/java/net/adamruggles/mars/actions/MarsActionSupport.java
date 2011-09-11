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

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Mars Action Support class containing default properties for actions.
 * @author Adam
 * @version $Id$
 *
 * Created on Sep 10, 2011 at 7:54:46 PM
 */
public class MarsActionSupport extends ActionSupport {
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -4465526881297451839L;
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
     * Returns title.
     * @return the title.
     */
    public String getTitle() {
        return title;
    }
    /**
     * Sets title.
     * @param title the title to set.
     */
    public void setTitle(final String title) {
        this.title = title;
    }
}
