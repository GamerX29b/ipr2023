package webService;

import dto.Weather;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebService
@SOAPBinding(style=SOAPBinding.Style.RPC,use=SOAPBinding.Use.ENCODED)
public interface WebTemperatureService {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
