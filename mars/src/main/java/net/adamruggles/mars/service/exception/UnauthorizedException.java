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
package net.adamruggles.mars.service.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Unauthorized Exception.
 * @author adamruggles
 * @version $Id$
 *
 * Created on Sep 5, 2011 at 7:08:22 PM
 */
public class UnauthorizedException extends WebApplicationException {
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 2124057744873309569L;
    /**
     * Constructs an Unauthorized Exception.
     * @param message The message to add to the exception.
     */
    public UnauthorizedException(final String message) {
        super(Response.status(Response.Status.UNAUTHORIZED)
            .entity(message).type(MediaType.TEXT_PLAIN).build());
    }
}
