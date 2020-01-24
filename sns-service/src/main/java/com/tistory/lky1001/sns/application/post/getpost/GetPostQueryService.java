package com.tistory.lky1001.sns.application.post.getpost;

import com.tistory.lky1001.sns.application.configuration.queries.IQueryService;
import org.springframework.stereotype.Service;

@Service
public class GetPostQueryService implements IQueryService<GetPostQuery, GetPostResult> {

    @Override
    public GetPostResult handle(GetPostQuery query) {
        return null;
    }
}
