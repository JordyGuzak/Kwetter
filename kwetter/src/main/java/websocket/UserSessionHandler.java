package websocket;

import domain.Kweet;
import domain.User;
import service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;
import java.io.IOException;
import java.util.*;

/**
 * Created by jordy on 05/07/2017.
 */
@ApplicationScoped
public class UserSessionHandler {

    @Inject
    private UserService userService;

    private final Set<Session> sessions = new HashSet<Session>();
    //private final Set<User> users = new HashSet<User>();
    //private final Map<String, Session> userSessions = new HashMap<>();

    public UserSessionHandler() {
    }

    public void sendToSession(JsonObject message, Session session) {
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToAllSessions(JsonObject message) {
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendText(message.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendToAllFollowers(JsonObject message) {
        String username = message.getString("username");
        User user = userService.findUserByName(username);

        for (User u : user.getFollowers()) {
            for (Session s : sessions) {
                if (u.getUsername().equals(s.getUserPrincipal().getName())) {
                    if (s.isOpen()) {
                        try {
                            s.getBasicRemote().sendText(message.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public JsonObject createPostKweetMessage(Kweet kweet) {
        JsonProvider provider = JsonProvider.provider();
        JsonObject postMessage = provider.createObjectBuilder()
                .add("action", "postKweet")
                .add("username", kweet.getOwner().getUsername())
                .add("message", kweet.getMessage())
                .add("postDate", kweet.getPostDate().toString())
                .build();
        return postMessage;
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }
}

