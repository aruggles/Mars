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

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.adamruggles.mars.rest.entity.UserVO;
import net.adamruggles.mars.service.UserService;

import com.sun.jersey.api.core.InjectParam;

/**
 * User REST Resource.
 * @author Adam
 * @version $Id$
 *
 * Created on Sep 2, 2011 at 11:01:48 PM
 */
@Path("/user")
public class UserResource {
    /**
     * User Data Access Object.
     */
    @InjectParam
    private UserService userService;

    /**
     * Returns a user by id.
     * @param id The id of the user.
     * @return The user.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/{id}")
    public Response getById(@PathParam("id") final Long id) {
        final UserVO userVO = userService.get(id);
        if (userVO == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found with id: " + id).build();
        }
        return Response.ok(userVO).build();
    }

    /**
     * Returns a user by username.
     * @param username The username of the user.
     * @return The user or null.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/username/{username}")
    public UserVO getByUsername(@PathParam("username") final String username) {
        return userService.getByUsername(username);
    }

    /**
     * Sets userService.
     * @param userService the userService to set.
     */
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }
}
