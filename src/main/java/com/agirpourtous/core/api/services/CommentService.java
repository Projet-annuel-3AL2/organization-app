package com.agirpourtous.core.api.services;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Comment;

public class CommentService extends Service<Comment>{
    public CommentService(APIClient client) {
        super(client, "/comment/", Comment.class);
    }
}
