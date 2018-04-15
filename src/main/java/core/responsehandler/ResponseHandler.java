package core.responsehandler;

import core.Response;

import java.util.HashMap;
import java.util.function.Predicate;

/**
 * Created by akhil raj azhikodan on 16/4/18.
 */
public abstract class ResponseHandler {
    HashMap<Predicate<Response>, RequestHandler> actionMap;
    Class returnType;

    public ResponseHandler(Class T) {
        returnType = T;

        actionMap = new HashMap<>();

        actionMap.put(response -> response.getStatusCode() >= 200 && response.getStatusCode() < 300, new Do2XX());
        actionMap.put(response -> response.getStatusCode() >= 300 && response.getStatusCode() < 400, new Do3XX());
        actionMap.put(response -> response.getStatusCode() >= 400 && response.getStatusCode() < 500, new Do4XX());
        actionMap.put(response -> response.getStatusCode() >= 500 && response.getStatusCode() < 600, new Do5XX());
    }

    public Object handle(Response response) throws Exception {
        for (Predicate<Response> predicate : actionMap.keySet()) {
            if (predicate.test(response)) {
                return actionMap.get(predicate).handle(response, returnType);
            }
        }
        return null;
    }

    abstract public Object on2XX(Response response) throws Exception;

    public Object on3XX(Response response) throws Exception {
        return null;
    }

    public Object on4XX(Response response) throws Exception {
        return null;
    }

    public Object on5XX(Response response) throws Exception {
        return null;
    }


    interface RequestHandler {
        Object handle(Response response, Class returnType) throws Exception;
    }

    class Do2XX implements RequestHandler {
        @Override
        public Object handle(Response response, Class returnType) throws Exception {
            return on2XX(response);
        }
    }

    class Do3XX implements RequestHandler {
        @Override
        public Object handle(Response response, Class returnType) throws Exception {
            return on3XX(response);
        }
    }

    class Do4XX implements RequestHandler {
        @Override
        public Object handle(Response response, Class returnType) throws Exception {
            return on4XX(response);
        }
    }

    class Do5XX implements RequestHandler {
        @Override
        public Object handle(Response response, Class returnType) throws Exception {
            return on5XX(response);
        }
    }
}
