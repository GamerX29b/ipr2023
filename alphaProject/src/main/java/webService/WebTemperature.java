package webService;

import dto.Weather;
import facade.WeatherFacade;

import javax.annotation.Resource;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/temperature")
public class WebTemperature extends HttpServlet implements WebTemperatureService{

    @Resource(name = "jdbc/WeatherFacade")
    WeatherFacade weatherFacade;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>" + weatherFacade.temperature() + "</h1>");
        out.println("</body>");
        out.println("</html>");
    }
}
