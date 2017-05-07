package batch;

import domain.User;
import service.UserService;

import javax.batch.api.chunk.ItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Dependent
@Named("UserWriter")
public class UserWriter implements ItemWriter {
    
    @Inject
    private UserService service;


    public void close() throws Exception {
    }

    public void writeItems(List<Object> items) throws Exception {

        for (Object item : items) {
            User user = (User) item;
            service.addUser(user);
        }
    }

    public Serializable checkpointInfo() throws Exception {
       return null;
    }

    public void open(Serializable checkpoint) throws Exception {
    }
   
}
