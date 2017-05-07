package bean;

import domain.Kweet;
import domain.User;
import interceptor.Logged;
import service.KweetService;
import service.UserService;

import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by jordy on 05/01/2017.
 */
@MessageDriven(mappedName = "jms/KwetterMessage/queue")
public class MessageBean implements MessageListener {

    @Inject
    private UserService userService;

    @Inject
    private KweetService kweetService;

    public MessageBean() {
    }

    @Logged
    public void onMessage(Message message) {

        System.out.println("Received message: " + message);

        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                String[] values = textMessage.getText().split(",");
                if (values.length == 2) {
                    User user = userService.findUserByName(values[0]);
                    Kweet kweet = new Kweet(user, values[1]);
                    kweetService.addKweet(kweet);
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
