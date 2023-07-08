package webService;

import dto.Weather;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style=SOAPBinding.Style.RPC,use=SOAPBinding.Use.ENCODED)
public interface WebTemperatureService {

    Weather takeTemperature();
}
