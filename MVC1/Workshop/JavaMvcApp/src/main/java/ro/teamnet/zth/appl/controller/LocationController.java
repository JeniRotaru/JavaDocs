package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;

/**
 * Created by user on 7/15/2016.
 */
@MyController(urlPath = "/locations")
public class LocationController {

    @MyRequestMethod(urlPath = "/all")
    public String getAllEmployees() {
        return "allLocations";
    }

    @MyRequestMethod(urlPath = "/one")
    public  String getOneEmployee() {
        return "oneRandomLocation";
    }


}
