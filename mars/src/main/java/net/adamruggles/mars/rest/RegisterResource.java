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
package net.adamruggles.mars.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.adamruggles.mars.rest.entity.UserVO;
import net.adamruggles.mars.service.UserService;
import net.adamruggles.mars.service.exception.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.core.InjectParam;

/**
 * Register REST web service.
 * @author adamruggles
 * @version $Id$
 *
 * Created on Sep 6, 2011 at 10:19:40 PM
 */
@Path("/register")
public class RegisterResource {
    /**
     * Logger.
     */
    private final Logger logger = LoggerFactory.getLogger(RegisterResource.class);
    /**
     * User Data Access Object.
     */
    @InjectParam
    private UserService userService;
    /**
     * Creates new user.
     * @param userVO The user to create.
     * @return {@link Response}.
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response register(final UserVO userVO) {
        try {
            userService.create(userVO);
        } catch (final ServiceException ex) {
            logger.error("Error occured creating user.", ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getLocalizedMessage()).build();
        }
        return Response.ok(userVO).build();
    }
    /**
     * Sets userService.
     * @param userService the userService to set.
     */
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }
}
