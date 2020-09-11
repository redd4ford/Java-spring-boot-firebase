package com.redd4ford.firebase_test.service;

import com.google.firebase.database.annotations.NotNull;
import com.redd4ford.firebase_test.api.repository.FirebaseRepository;
import com.redd4ford.firebase_test.mapper.AbstractMapper;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.stream.Collectors.toList;

public abstract class AbstractService<S, T> {

  protected abstract FirebaseRepository<S, Integer> getRepository();

  protected abstract AbstractMapper<S, T> getMapper();

  public List<T> getAll() throws ExecutionException, InterruptedException {
    return getRepository().findAll()
        .stream()
        .map(getMapper()::mapObjectToDto)
        .collect(toList());
  }

  public T getById(Integer id) throws ExecutionException, InterruptedException {
    S object = getRepository().findById(id);
    return getMapper().mapObjectToDto(object);
  }

  public T save(@NotNull T dto, Integer id) throws ExecutionException, InterruptedException {
    S object = getRepository()
        .save(getMapper()
                .mapDtoToObject(dto),
            id);
    return getMapper().mapObjectToDto(object);
  }

  public void delete(Integer id) {
    getRepository().deleteById(id);
  }

  public Integer getIdleId(T dto) throws ExecutionException, InterruptedException {
    Integer i = getRepository().countAll() + 1;
    while (getById(i) != null) {
      i++;
    }
    return i;
  }

  public boolean isExistById(Integer id) {
    try {
      return getById(id) != null;
    } catch (Exception e) {
      return false;
    }
  }


}
