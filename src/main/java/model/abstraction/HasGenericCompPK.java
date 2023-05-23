package model.abstraction;

public interface HasGenericCompPK<T, U> {
	
	Object getRecordByPK(T pkey1, U pkey2);
}
