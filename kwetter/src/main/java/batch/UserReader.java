package batch;

import javax.batch.api.chunk.ItemReader;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.InputStream;
import java.io.Serializable;

@Dependent
@Named("UserReader")
public class UserReader implements ItemReader {

    @Inject
    private JobContext jobContext;

    private String fileName;

    private JsonParser parser;

    private Checkpoint checkpoint;

    private boolean start;


    public void open(Serializable srlzbl) throws Exception {
        if (checkpoint == null) {
            this.checkpoint = new Checkpoint();
        } else {
            this.checkpoint = (Checkpoint) checkpoint;
        }

        fileName = jobContext.getProperties().getProperty("input_file");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream iStream = classLoader.getResourceAsStream(fileName);

        parser = Json.createParser(iStream);

        start = false;
        for (long i = 0; i < this.checkpoint.getCount(); ++i) {
            JsonParser.Event event = parser.next();
            if (event == JsonParser.Event.START_ARRAY) {
                start = true;
            }
        }
    }

    public void close() throws Exception {
        parser.close();
    }

    public Object readItem() throws Exception {
        boolean itemFound = false;
        InputUser item = new InputUser();

        System.out.println("Read Item");

        while (!itemFound && parser.hasNext()) {
            JsonParser.Event event = parser.next();
            checkpoint.eventHappened();

            switch (event) {
                case START_ARRAY:
                    start = true;
                    break;
                case VALUE_STRING:
                    if (start) {
                        if (item.getName() == null) {
                            item.setName(parser.getString());
                        } else if (item.getEmail() == null) {
                            item.setEmail(parser.getString());
                        } else if (item.getPassword() == null) {
                            item.setPassword(parser.getString());
                            itemFound = true;
                        }
                    }
                    break;
                case VALUE_NUMBER:
                    if (start) {
                        if (item.getAge() == null) {
                            item.setAge(parser.getInt());
                        }
                    }
                    break;
                case END_ARRAY:
                    item = null;
                    break;
            }
        }
        return item;
    }

    public Serializable checkpointInfo() throws Exception {
        return new Checkpoint();
    }

}
