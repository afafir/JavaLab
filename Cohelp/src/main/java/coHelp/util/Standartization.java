package coHelp.util;


import org.springframework.stereotype.Service;
import ru.redcom.lib.integration.api.client.dadata.DaDataClient;
import ru.redcom.lib.integration.api.client.dadata.DaDataClientFactory;
import ru.redcom.lib.integration.api.client.dadata.DaDataException;
import ru.redcom.lib.integration.api.client.dadata.dto.Address;


@Service
public class Standartization {

    private static final String API_KEY = "7f7054b12802b7111e7a0d4c82df2590a50f4b03";
    private static final String SECRET_KEY = "d295db294039860478194298c10850b552676ec3";
    private final DaDataClient dadata = DaDataClientFactory.getInstance(API_KEY, SECRET_KEY);

    public Address cleanAddress(final String source) throws DaDataException {
        final Address a = dadata.cleanAddress(source);
        System.out.println("cleaned address: " + a);
        return a;
    }
}
