package webService;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style=SOAPBinding.Style.RPC,use=SOAPBinding.Use.ENCODED)
public interface WebTemperatureService {

    Double takeTemperature();
}
