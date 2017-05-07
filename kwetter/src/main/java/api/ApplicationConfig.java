package api;

import provider.MyJacksonJsonProvider;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jordy on 3/5/2017.
 */
@ApplicationPath("/api")
public class ApplicationConfig extends Application {


    /*@Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> resources = new HashSet<Class<?>>();

        System.out.println("REST configuration starting: getClasses()");

        //features
        //this will register Jackson JSON providers
        resources.add(org.glassfish.jersey.jackson.JacksonFeature.class);
        //we could also use this:
        //resources.add(com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider.class);

        //instead let's do it manually:
        resources.add(MyJacksonJsonProvider.class);
        resources.add(UserResource.class);
        resources.add(KweetResource.class);
        //==> we could also choose packages, see below getProperties()

        System.out.println("REST configuration ended successfully.");

        return resources;
    }*/
}
