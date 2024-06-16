package webService;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Logger;

@Provider
@PreMatching
public class CorsOptionsFilter implements ContainerRequestFilter {

    private final static Logger log = Logger.getLogger(CorsOptionsFilter.class.getName() );

    //По рецепту отсюда сделал перехватчик опции
    //https://jakarta.ee/specifications/restful-ws/3.0/jakarta-restful-ws-spec-3.0.html#resource_access
    @Override
    public void filter( ContainerRequestContext requestCtx ) throws IOException {
        if ( requestCtx.getRequest().getMethod().equals( "OPTIONS" ) ) {
            log.info( "Is - options method" );

            requestCtx.abortWith(Response.status(Response.Status.OK ).build() );
        }
    }
}