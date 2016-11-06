package org.ieee.icce2017.model.requests;

import org.ieee.drupal.DrupalClient;
import org.ieee.icce2017.model.data.SettingsHolder;
import org.ieee.http.base.BaseRequest;

import java.util.Map;

public class SettingsRequest extends BaseSafeConsumeContainerRequest<SettingsHolder> {

    public SettingsRequest(DrupalClient client) {
        super(client, new SettingsHolder());
    }

    @Override
    protected String getPath() {
        return "getSettings";
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
