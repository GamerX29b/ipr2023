package webService;

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

@WebServlet(value = "/temperature", name = "webTemperature", displayName = "Temp")
public class WebTemperature extends HttpServlet{
    Logger log = Logger.getLogger(WebTemperature.class.getName());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>Temperature: " + getWeatherFacade().temperature().getTemperature() + "c Humidity: " + getWeatherFacade().temperature().getHumidity() + "%</h1>");
        out.println("</body>");
        out.println("</html>");
        out.close();
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
