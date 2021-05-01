package com.agirpourtous.core.api.services;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Entity;


public abstract class Service<T extends Entity> {
    protected final Class<T> type;
    protected final APIClient client;
    protected final String baseRoute;

    public Service(APIClient client, String baseRoute, Class<T> type) {
        this.client = client;
        this.baseRoute = baseRoute;
        this.type = type;
    }
}
