package com.krushit.service;

import com.krushit.common.Message;
import com.krushit.common.exception.ApplicationException;
import com.krushit.dao.ILocationDAO;
import com.krushit.dao.LocationDAOImpl;
import com.krushit.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Component
public class LocationService {

    private static final String GEO_BASE_URL = "https://graphhopper.com/api/1/geocode";
    private static final String ROUTE_BASE_URL = "https://graphhopper.com/api/1/route";
    private static final String API_KEY = "6d96b6fb-13b1-43b6-99ab-2e1d55edd76b";
    private final ILocationDAO locationDAO = new LocationDAOImpl();
    @Autowired
    private RestTemplate restTemplate;

/*    public static String getCoordinates(String place) throws Exception {
        String query = URLEncoder.encode(place, StandardCharsets.UTF_8);
        String geoUrl = GEO_BASE_URL + "?q=" + query + "&limit=1&key=" + API_KEY;
        URL url = new URL(geoUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        String responseString = response.toString();
        int latIndex = responseString.indexOf("\"lat\":");
        int lngIndex = responseString.indexOf("\"lng\":");
        if (latIndex == -1 || lngIndex == -1) {
            throw new Exception(Message.Ride.COORDINATES_NOT_FOUND_FOR + place);
        }
        String lat = responseString.substring(latIndex + 6, responseString.indexOf(",", latIndex)).trim();
        String lng = responseString.substring(lngIndex + 6, responseString.indexOf("}", lngIndex)).trim();
        return lat + "," + lng;
    }*/

    public String getCoordinates(String place) throws Exception {
        String encodedPlace = URLEncoder.encode(place, StandardCharsets.UTF_8);
        String url = UriComponentsBuilder.fromUriString(GEO_BASE_URL)
                .queryParam("q", encodedPlace)
                .queryParam("limit", 1)
                .queryParam("key", API_KEY)
                .toUriString();

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        Map<String, Object> responseBody = response.getBody();

        if (responseBody == null || !responseBody.containsKey("hits")) {
            throw new Exception("Invalid response from geocoding API for " + place);
        }

        List<Map<String, Object>> hits = (List<Map<String, Object>>) responseBody.get("hits");
        if (hits.isEmpty()) {
            throw new Exception(Message.Ride.COORDINATES_NOT_FOUND_FOR + place);
        }

        Map<String, Object> point = (Map<String, Object>) hits.get(0).get("point");
        if (point == null || !point.containsKey("lat") || !point.containsKey("lng")) {
            throw new Exception(Message.Ride.COORDINATES_NOT_FOUND_FOR + place);
        }

        return point.get("lat").toString() + "," + point.get("lng").toString();
    }


    public List<Location> getAllLocations() throws ApplicationException {
        if (locationDAO.getAllLocations().isEmpty()) {
            throw new ApplicationException(Message.Location.LOCATIONS_NOT_FOUND);
        }
        return locationDAO.getAllLocations();
    }

    public void inactivateLocation(int locationId) throws ApplicationException {
        if (locationDAO.getLocationName(locationId) == null) {
            throw new ApplicationException(Message.Location.LOCATION_NOT_FOUND);
        }
        locationDAO.inactivateLocation(locationId);
    }

    public void activateLocation(int locationId) throws ApplicationException {
        if (locationDAO.getLocationName(locationId) == null) {
            throw new ApplicationException(Message.Location.LOCATION_NOT_FOUND);
        }
        locationDAO.activateLocation(locationId);
    }

    public double calculateDistance(int fromId, int toId) throws Exception {
        Location fromLocationOpt = locationDAO.getLocation(fromId)
                .orElseThrow(() -> new ApplicationException(Message.Location.LOCATION_NOT_FOUND));
        Location toLocationOpt = locationDAO.getLocation(toId)
                .orElseThrow(() -> new ApplicationException(Message.Location.LOCATION_NOT_FOUND));
        if (!fromLocationOpt.getIsActive()) {
            throw new ApplicationException(Message.Ride.SERVICE_NOT_AVAILABLE_FOR_THIS_LOCATION);
        }
        if (!toLocationOpt.getIsActive()) {
            throw new ApplicationException(Message.Ride.SERVICE_NOT_AVAILABLE_FOR_THIS_LOCATION);
        }
        String fromLocation = fromLocationOpt.getName();
        String toLocation = fromLocationOpt.getName();
        if (fromLocation == null || toLocation == null) {
            throw new ApplicationException(Message.Ride.PLEASE_ENTER_VALID_LOCATION);
        }
        String fromCoordinates = getCoordinates(fromLocation + ", Surat, Gujarat");
        String toCoordinates = getCoordinates(toLocation + ", Surat, Gujarat");
        String apiUrl = ROUTE_BASE_URL + "?point=" + fromCoordinates + "&point=" + toCoordinates +
                "&vehicle=car&locale=en&key=" + API_KEY + "&points_encoded=false";
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        String responseString = response.toString();
        String distanceKey = "\"distance\":";
        int distanceInd = responseString.indexOf(distanceKey);
        if (distanceInd == -1) {
            throw new Exception(Message.Ride.INVALID_GRAPH_HOPPER_API_RESPONSE);
        }
        distanceInd += distanceKey.length();
        int endIndex = responseString.indexOf(",", distanceInd);
        if (endIndex == -1) {
            endIndex = responseString.indexOf("}", distanceInd);
        }
        String distanceValue = responseString.substring(distanceInd, endIndex).trim();
        return Math.round(Double.parseDouble(distanceValue) / 1000 * 100.0) / 100.0;
        //return Double.parseDouble(distanceValue) / 1000;
    }

    public void addLocation(String location) throws ApplicationException {
        locationDAO.addLocation(location);
    }

    public String getLocationNameById(int pickLocationId) throws ApplicationException {
        return locationDAO.getLocationName(pickLocationId);
    }

    public BigDecimal getCommissionByDistance(double distance) throws ApplicationException {
        return locationDAO.getCommissionByDistance(distance);
    }
}
