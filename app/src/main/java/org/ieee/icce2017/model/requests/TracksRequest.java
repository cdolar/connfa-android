package org.ieee.icce2017.model.requests;

import org.ieee.drupal.DrupalClient;
import org.ieee.icce2017.model.data.Track;
import org.ieee.http.base.BaseRequest;

import java.util.Map;

public class TracksRequest extends BaseSafeConsumeContainerRequest<Track.Holder> {

    public TracksRequest(DrupalClient client) {
        super(client, new Track.Holder());
    }

    @Override
    protected String getPath() {
        return "getTracks";
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
