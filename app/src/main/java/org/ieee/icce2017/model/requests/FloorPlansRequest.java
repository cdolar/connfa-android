package org.ieee.icce2017.model.requests;

import org.ieee.drupal.DrupalClient;
import org.ieee.icce2017.model.data.FloorPlan;
import org.ieee.http.base.BaseRequest;

import java.util.Map;

public class FloorPlansRequest extends BaseSafeConsumeContainerRequest<FloorPlan.Holder> {

    public FloorPlansRequest(DrupalClient client) {
        super(client, new FloorPlan.Holder());
    }

    @Override
    protected String getPath() {
        return "getFloorPlans";
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
