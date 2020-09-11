package com.redd4ford.firebase_test.mapper;

import java.util.concurrent.ExecutionException;

public abstract class AbstractMapper<S, T> {

  public abstract T mapObjectToDto(S object);

  public abstract S mapDtoToObject(T dto) throws ExecutionException, InterruptedException;

}
