package com.tistory.lky1001.sns.application.post.newpost;

import com.tistory.lky1001.sns.application.configuration.commands.ICommandService;
import org.springframework.stereotype.Service;

@Service
public class NewPostCommandService implements ICommandService<NewPostCommand, NewPostResult> {

    @Override
    public NewPostResult handle(NewPostCommand command) {
        return null;
    }
}
