package com.tistory.lky1001.sns.application.contracts;

public class VoidCommandResult implements IResult {

    @Override
    public String toString() {
        return VoidCommandResult.class.getSimpleName();
    }

    @Override
    public int hashCode() {
        return VoidCommandResult.class.getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof VoidCommandResult;
    }
}
