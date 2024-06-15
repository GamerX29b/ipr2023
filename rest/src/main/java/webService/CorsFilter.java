package webService;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Logger;

@Provider
@PreMatching
public class CorsFilter implements ContainerResponseFilter {
    //Контейнерный флильтр для ответа
    // https://jakarta.ee/specifications/restful-ws/3.0/jakarta-restful-ws-spec-3.0.html#filters_and_interceptors
    private final static Logger log = Logger.getLogger(CorsFilter.class.getName() );

    //https://jakarta.ee/specifications/restful-ws/3.0/jakarta-restful-ws-spec-3.0.html#head_and_options
    //Добавляем в ответ информацию что можно выполнять удалённые запросы
    @Override
    public void filter(ContainerRequestContext requestCtx, ContainerResponseContext responseCtx ) throws IOException {
        responseCtx.getHeaders().add( "Access-Control-Allow-Origin", "*" );
        responseCtx.getHeaders().add( "Access-Control-Allow-Credentials", "true" );
        responseCtx.getHeaders().add( "Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        log.warning("Filtred");
    }
}