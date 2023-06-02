package com.mycgv_jsp.dao;

import java.util.List;

public interface MycgvDao {
	int insert(Object obj);
	List<Object> select(int startCount, int endCount);
}
