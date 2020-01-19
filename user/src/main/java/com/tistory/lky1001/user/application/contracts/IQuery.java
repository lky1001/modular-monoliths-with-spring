package com.tistory.lky1001.user.application.contracts;

import java.util.UUID;

public interface IQuery<T extends IResult> {

    UUID getId();
}
