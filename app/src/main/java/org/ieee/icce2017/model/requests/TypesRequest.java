package org.ieee.icce2017.model.requests;

import org.ieee.drupal.DrupalClient;
import org.ieee.icce2017.model.data.Type;
import org.ieee.http.base.BaseRequest;

import java.util.Map;

public class TypesRequest extends BaseSafeConsumeContainerRequest<Type.Holder> {

    public TypesRequest(DrupalClient client) {
        super(client, new Type.Holder());
    }

    @Override
    protected String getPath() {
        return "getTypes";
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
