package org.ieee.icce2017.model.requests;

import org.ieee.drupal.DrupalClient;
import org.ieee.icce2017.model.data.Location;
import org.ieee.http.base.BaseRequest;

import java.util.Map;

public class LocationRequest extends BaseSafeConsumeContainerRequest<Location.Holder> {

    public LocationRequest(DrupalClient client) {
        super(client, new Location.Holder());
    }

    @Override
    protected String getPath() {
        return "getLocations";
    }

    @Override
    protected Map<String, String> getItemRequestPostParameters() {
        return null;
    }

    @Override
    protected Map<String, Object> getItemRequestGetParameters(BaseRequest.RequestMethod method) {
        return null;
    }
}
