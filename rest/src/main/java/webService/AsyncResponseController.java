package webService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.Suspended;

@Path("/async")
public class AsyncResponseController {


    @GET
    public void longRunningOp(@Suspended final AsyncResponseController ar) {
//        executor.submit(
//                new Runnable() {
//                    public void run() {
//                        executeLongRunningOp();
//                        ar.resume("Hello async world!");
//                    }
//                });
    }
}
