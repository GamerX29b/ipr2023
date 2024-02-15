package webService;

import com.google.gson.Gson;
import facade.WeatherFacade;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(value = "/temperature", name = "temperature")
public class WebTemperature extends HttpServlet{
    Logger log = Logger.getLogger(WebTemperature.class.getName());

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAccessControlHeaders(response);
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String json = gson.toJson(getWeatherFacade().temperature());
        log.info(json);
        out.println(json);
        out.close();
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        setAccessControlHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
    }

    private WeatherFacade getWeatherFacade(){
        Context ctx = null;
        WeatherFacade wf = null;
        try {
            ctx = new InitialContext();
           wf = (WeatherFacade)ctx.lookup("java:global/bettaProject-1.0/WeatherFacadeBean");
        } catch (Exception e) {
            log.log(Level.SEVERE,"Unable to retrieve the UserServiceBean.",e);
        } finally {
            if(ctx != null) try { ctx.close(); } catch (Throwable t) {}
        }
        return wf;
    }

}
