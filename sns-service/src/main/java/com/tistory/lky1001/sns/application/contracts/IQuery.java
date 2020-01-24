package com.tistory.lky1001.sns.application.contracts;

import java.util.UUID;

public interface IQuery<T extends IResult> {

    UUID getId();
}
