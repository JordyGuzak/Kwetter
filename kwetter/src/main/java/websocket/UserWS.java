package websocket;

import domain.Kweet;
import domain.User;
import event.PostKweetEvent;
import service.KweetService;
import service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jordy on 05/06/2017.
 */
@ServerEndpoint("/websocket")
@ApplicationScoped
public class UserWS {

    @Inject
    private UserSessionHandler userSessionHandler;

    @Inject
    private UserService userService;

    @Inject
    private KweetService kweetService;

    @Inject
    private Event<PostKweetEvent> postKweetEvent;

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Client connected: " + session.getId());
        System.out.println("User principle: " + session.getUserPrincipal());
        userSessionHandler.addSession(session);
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Client disconnected: " + session.getId());
        userSessionHandler.removeSession(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Received message: " + message);

        try(JsonReader reader = Json.createReader(new StringReader(message))) {
            JsonObject jsonMessage = reader.readObject();

            if ("postKweet".equals(jsonMessage.getString("action"))) {
                //get kweet data
                String username = jsonMessage.getString("username");
                String kweetMessage = jsonMessage.getString("message");

                //Add kweet
                User user = userService.findUserByName(username);
                Kweet kweet = new Kweet(user, kweetMessage);
                kweetService.addKweet(kweet);

                //send to all followers
                JsonObject jsonReply = userSessionHandler.createPostKweetMessage(kweet);
                userSessionHandler.sendToAllFollowers(jsonReply);
            }
        }
    }
}
