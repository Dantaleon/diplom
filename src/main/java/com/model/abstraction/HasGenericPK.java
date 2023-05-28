package com.model.abstraction;

public interface HasGenericPK<T> {
	
	Object getRecordByPK(T pkey);
}
