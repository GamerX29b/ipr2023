package webService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.Response;

import static java.util.concurrent.TimeUnit.SECONDS;
import static javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE;

@Path("/sync")
public class SyncResponseWithTimeout {

    //https://jakarta.ee/specifications/restful-ws/3.0/jakarta-restful-ws-spec-3.0.html#async_response
    @GET
    public void longRunningOp(@Suspended final AsyncResponse ar) {
        // Register handler and set timeout
        ar.setTimeoutHandler(new TimeoutHandler() {
            public void handleTimeout(AsyncResponse ar) {
                ar.resume(Response.status(SERVICE_UNAVAILABLE).entity(
                        "Operation timed out -- please try again").build());
            }
        });
        ar.setTimeout(15, SECONDS);

        // Execute long running operation in new thread
//        executor.execute(
//                new Runnable() {
//                    public void run() {
//                        executeLongRunningOp();
//                        ar.resume("Hello async world!");
//                    }
//                });
    }
}
