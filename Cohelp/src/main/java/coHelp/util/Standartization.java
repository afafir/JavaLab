package coHelp.util;


import org.springframework.stereotype.Service;
import ru.redcom.lib.integration.api.client.dadata.DaDataClient;
import ru.redcom.lib.integration.api.client.dadata.DaDataClientFactory;
import ru.redcom.lib.integration.api.client.dadata.DaDataException;
import ru.redcom.lib.integration.api.client.dadata.dto.Address;

import java.util.AbstractMap;
import java.util.Map;


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

    public Map.Entry<Double, Double> getCoord(String address) throws DaDataException {
        Address address1 = dadata.cleanAddress(address);
        //ширина и долгота
        return new AbstractMap.SimpleEntry<Double, Double>(address1.getGeoLat(), address1.getGeoLon());
    }


    //cos(d) = sin(φА)·sin(φB) + cos(φА)·cos(φB)·cos(λА − λB),
    //L = d·R, r=6371
    public Double getDistance(Map.Entry<Double, Double> coord1, Map.Entry<Double, Double> coord2) throws DaDataException {
        Double firstLatitude = (Double) coord1.getKey();
        Double firstLongitude = (Double) coord1.getValue();
        Double secondLatitude = (Double) coord2.getKey();
        Double secondLongitude = (Double) coord2.getValue();
        double radian = Math.sin(firstLatitude) * Math.sin(secondLatitude) +
                     Math.cos(firstLatitude)*Math.cos(secondLatitude)*Math.cos(firstLongitude-secondLongitude);
        return 6371*Math.acos(radian);
    }






}
