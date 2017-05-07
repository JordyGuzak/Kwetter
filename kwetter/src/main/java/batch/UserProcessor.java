package batch;

import domain.User;

import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Dependent
@Named("UserProcessor")
public class UserProcessor implements ItemProcessor {

    public Object processItem(Object item) throws Exception {
         
      InputUser input = (InputUser) item;

      User user = new User(input.getName(), input.getAge(), input.getEmail(), input.getPassword());

      return user;
    }
  
}
