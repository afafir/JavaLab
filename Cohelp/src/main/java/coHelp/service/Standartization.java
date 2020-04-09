package coHelp.service;


import org.springframework.stereotype.Service;
import ru.redcom.lib.integration.api.client.dadata.DaDataClient;
import ru.redcom.lib.integration.api.client.dadata.DaDataClientFactory;
import ru.redcom.lib.integration.api.client.dadata.DaDataException;
import ru.redcom.lib.integration.api.client.dadata.dto.Address;


@Service
public class Standartization {

    private static final String API_KEY = "25be1c14037092d11a6c81203e67e220b9a475d6";
    private static final String SECRET_KEY = "cd533dee38fe4a5e3e845cb3a41d45374d637122";
    private final DaDataClient dadata = DaDataClientFactory.getInstance(API_KEY, SECRET_KEY);

    public Address cleanAddress(final String source) throws DaDataException {
        final Address a = dadata.cleanAddress(source);
        System.out.println("cleaned address: " + a);
        return a;
    }
}
