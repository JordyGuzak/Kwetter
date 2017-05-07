package api;

import domain.User;
import service.UserService;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import util.PasswordHash;
import util.UserFilter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

/**
 * Created by jordy on 04/09/2017.
 */
@Path("/authenticate")
@Consumes("application/json")
@Produces("application/json")
public class AuthenticationResource {

    @Inject
    private UserService userService;

    @POST
    public Response logIn(@Context HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");
        StringTokenizer st = new StringTokenizer(authHeader);

        if (st.hasMoreTokens()) {
            String basic = st.nextToken();

            if (basic.equalsIgnoreCase("Basic")) {
                System.out.println("Basic Authenticatie gevonden");

                String credentials = st.nextToken();
                System.out.println("Credentials gevonden : " + credentials);

                int p = credentials.indexOf(":");
                if (p != -1) {
                    String username = credentials.substring(0, p);
                    String password = credentials.substring(p + 1);
                    try {
                        System.out.println("Username: " + username + ", Password: " + password);
                        request.login(username, password);
                        User user = UserFilter.filter(userService.findUserByName(username));
                        if (user != null) {
                            user.setToken(credentials);
                            return Response.ok(user).build();
                        }
                    } catch (ServletException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return Response.serverError().build();
    }
}
