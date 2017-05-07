package service;

import dao.JPA;
import dao.MentionDaoJPA;
import domain.Kweet;
import domain.Mention;
import domain.User;
import event.KweetCreatedEvent;
import event.PostKweetEvent;
import interceptor.Logged;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jordy on 04/10/2017.
 */
@Stateless
public class MentionService {

    @Inject @JPA
    private MentionDaoJPA mentionDao;

    @Inject
    private UserService userService;

    @Logged
    @PermitAll
    public void findMentions(@Observes KweetCreatedEvent event) {
        Kweet kweet = event.kweet;

        String content = kweet.getMessage();
        String pattern = "@([a-zA-Z0-9]+)";

        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(content);
        while (m.find()) {
            User user = userService.findUserByName(m.group(0).replace("@", ""));
            if (user != null) {
                Mention mention = new Mention(kweet, user);
                mentionDao.create(mention);
                user.addMention(mention);
                userService.update(user);
            }
        }
    }

    @PermitAll
    public Collection<Mention> getMentions(String username) {
        User user = userService.findUserByName(username);
        if (user != null) {
            Collection<Mention> mentions = mentionDao.getMentions(username);
            return mentions;
        }
        return null;
    }
}
