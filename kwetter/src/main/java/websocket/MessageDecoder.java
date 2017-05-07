package websocket;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Created by jordy on 05/06/2017.
 */
public class MessageDecoder implements Decoder.Text {
    public Object decode(String s) throws DecodeException {
        return null;
    }

    public boolean willDecode(String s) {
        return false;
    }

    public void init(EndpointConfig endpointConfig) {

    }

    public void destroy() {

    }
}
