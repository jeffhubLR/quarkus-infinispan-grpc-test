package org.acme;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path("/hello")
public class ExampleResource {
    @Inject
    RemoteCacheManager remoteCacheManager;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        Book b = new Book("hello","b",5, Set.of(new Author("Sean","Scott")));
        getCache().put("hi",b);
        return b.getTitle();
    }

    public RemoteCache<String,Book> getCache(){
        return remoteCacheManager.administration().getOrCreateCache("myCache","*-cache");
    }
}