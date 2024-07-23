package com.mediSlot.dao;

import java.util.List;

public interface IDao<T> {
	T create(T t);
	
	boolean update(int id, T t);

	boolean delete(int id);

	List<T> findAll() throws Exception;
}
