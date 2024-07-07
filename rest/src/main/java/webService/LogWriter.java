package webService;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;
import java.util.logging.Logger;

@LogResp
public class LogWriter implements ContainerRequestFilter {

    Logger logger = Logger.getLogger(LogWriter.class.getName());

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        logger.info("Log writer intercept " + requestContext.getMethod());
    }
}
