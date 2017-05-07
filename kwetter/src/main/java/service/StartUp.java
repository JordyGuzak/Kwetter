package service;

import dao.GroupDaoJPA;
import dao.JPA;
import domain.Group;
import domain.Kweet;
import domain.User;
import util.PasswordHash;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * Created by Jordy on 15-2-2017.
 */
@Singleton
@Startup
public class StartUp {

    @Inject
    private UserService userService;

    @Inject
    private KweetService kweetService;

    @Inject @JPA
    private GroupDaoJPA groupDao;

    public StartUp() {}

    @PostConstruct
    public void initData() {

        //Create groups
        Group adminGroup = new Group(Group.ADMIN_GROUP);
        Group userGroup = new Group(Group.USER_GROUP);
        groupDao.create(adminGroup);
        groupDao.create(userGroup);

        //Instantiate users and assign them to a group
        User jordy = new User("Jordy", 25, "jordy@mail.com", PasswordHash.stringToHash("password"));
        jordy.getGroups().add(userGroup);
        jordy.getGroups().add(adminGroup);

        User anne = new User("Anne", 19, "anne@mail.com", PasswordHash.stringToHash("password"));
        User frank = new User("Frank", 29, "frank@mail.com", PasswordHash.stringToHash("password"));
        User tom = new User("Tom", 21, "tom@mail.com", PasswordHash.stringToHash("password"));
        User louise = new User("Louise", 33, "louise@mail.com", PasswordHash.stringToHash("password"));

        //add users
        userService.addUser(jordy);
        userService.addUser(anne);
        userService.addUser(frank);
        userService.addUser(tom);
        userService.addUser(louise);

        userService.followUser(anne, jordy);
        userService.followUser(anne, louise);

        userService.followUser(jordy, anne);
        userService.followUser(jordy, frank);
        userService.followUser(jordy, tom);
        userService.followUser(jordy, louise);

        userService.followUser(louise, anne);
        userService.followUser(louise, tom);
        userService.followUser(tom, frank);
        userService.followUser(frank, tom);


        Kweet kweet1 = new Kweet(jordy, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        Kweet kweet2 = new Kweet(jordy, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        Kweet kweet3 = new Kweet(jordy, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        Kweet kweet4 = new Kweet(jordy, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        Kweet kweet5 = new Kweet(anne, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        Kweet kweet6 = new Kweet(anne, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        Kweet kweet7 = new Kweet(frank, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        Kweet kweet8 = new Kweet(tom, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        Kweet kweet9 = new Kweet(tom, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        Kweet kweet10 = new Kweet(louise, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        Kweet kweet11 = new Kweet(louise, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        Kweet kweet12 = new Kweet(louise, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        Kweet kweet13 = new Kweet(anne, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        Kweet kweet14 = new Kweet(anne, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        Kweet kweet15 = new Kweet(anne, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");

        kweetService.addKweet(kweet1);
        kweetService.addKweet(kweet2);
        kweetService.addKweet(kweet3);
        kweetService.addKweet(kweet4);
        kweetService.addKweet(kweet5);
        kweetService.addKweet(kweet6);
        kweetService.addKweet(kweet7);
        kweetService.addKweet(kweet8);
        kweetService.addKweet(kweet9);
        kweetService.addKweet(kweet10);
        kweetService.addKweet(kweet11);
        kweetService.addKweet(kweet12);
        kweetService.addKweet(kweet13);
        kweetService.addKweet(kweet14);
        kweetService.addKweet(kweet15);



    }
}
