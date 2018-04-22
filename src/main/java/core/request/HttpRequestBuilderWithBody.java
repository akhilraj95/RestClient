package core.request;

import core.EntityType;

/**
 * Created by akhil raj azhikodan on 22/4/18.
 */
abstract class HttpRequestBuilderWithBody extends HttpRequestBuilder
{
    public HttpRequestBuilder jsonEntity(Object object) {
        this.entityType = EntityType.JSON;
        entity = object;
        return this;
    }

    public HttpRequestBuilder urlEncodedEntity(Object object) {
        this.entityType = EntityType.URLENCODED;
        entity = object;
        return this;
    }

    public HttpRequestBuilder stringEntity(Object object) {
        this.entityType = EntityType.STRING;
        entity = object;
        return this;
    }

}
