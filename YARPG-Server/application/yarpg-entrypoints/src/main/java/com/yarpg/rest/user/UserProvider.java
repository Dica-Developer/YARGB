package com.yarpg.rest.user;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.StringUtils;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import com.yarpg.core.entity.User;

@Provider
public class UserProvider implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        System.out.println("try to get the user");
        String userId = containerRequestContext.getHeaders()
                .getFirst("User-Id");
        if (StringUtils.isEmpty(userId)) {
            Response response = Response.status(Response.Status.BAD_REQUEST)
                    .type(MediaType.TEXT_PLAIN_TYPE)
                    .entity("User-Id header is missing.")
                    .build();
            containerRequestContext.abortWith(response);
            return;
        }

        // do your logic to obtain the User object by userId

        ResteasyProviderFactory.pushContext(User.class, new User(1, "testLogedinUser", "mail"));
    }
}
