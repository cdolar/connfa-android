package org.ieee.icce2017.model.requests;

import org.ieee.drupal.AbstractDrupalEntityContainer;
import org.ieee.drupal.DrupalClient;
import org.ieee.icce2017.model.PreferencesManager;
import org.ieee.icce2017.model.UpdatesManager;
import org.ieee.http.base.BaseRequest;
import org.ieee.http.base.ResponseData;

import java.util.HashMap;
import java.util.Map;

//import retrofit.http.GET;

public abstract class BaseSafeConsumeContainerRequest<T> extends AbstractDrupalEntityContainer<T> {

    public BaseSafeConsumeContainerRequest(DrupalClient client, T theData) {
        super(client, theData);
    }

    @Override
    protected void consumeObject(ResponseData entity) {
        if(entity.getData() != null) {
            super.consumeObject(entity);
        }
    }

    @Override
    protected Map<String, String> getItemRequestHeaders(BaseRequest.RequestMethod method)
    {
        if(method != BaseRequest.RequestMethod.GET){
            return super.getItemRequestHeaders(method);
        }

        String lastDate = PreferencesManager.getInstance().getLastUpdateDate();
        Map<String,String> result = new HashMap<>();
        result.put(UpdatesManager.IF_MODIFIED_SINCE_HEADER, lastDate);
        return result;
    }
}
