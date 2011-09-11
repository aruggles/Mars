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

/**
 * Service Exception.
 * @author Adam
 * @version $Id$
 *
 * Created on Sep 3, 2011 at 10:40:12 AM
 */
public class ServiceException extends RuntimeException {
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -3384384555347048043L;
    /**
     * Constructs a service exception.
     */
    public ServiceException() {
        super();
    }
    /**
     * Constructs a service exception.
     * @param message The message to add to the exception.
     */
    public ServiceException(final String message) {
        super(message);
    }
    /**
     * Constructs a service exception.
     * @param message The message to add to the exception.
     * @param throwable The throwable to add to the exception.
     */
    public ServiceException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
    /**
     * Constructs a service exception.
     * @param throwable The throwable to add to the exception.
     */
    public ServiceException(final Throwable throwable) {
        super(throwable);
    }
}
