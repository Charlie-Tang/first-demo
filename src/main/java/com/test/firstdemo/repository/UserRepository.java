package com.test.firstdemo.repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

import com.test.firstdemo.entity.User;

@Repository
public class UserRepository {
	/**
	 * 采用内存型的存储方式
	 */
	private final ConcurrentMap<Integer, User> repository = new ConcurrentHashMap<>();
	
	private final static AtomicInteger idGenerator = new AtomicInteger();
	/**
	 * 
	 * @param user 
	 * @return 如果保存成功，返回true，否则返回false
	 */
	public boolean save(User user) {
		//id从1开始，通过id生成器
		Integer id = idGenerator.incrementAndGet();
		user.setId(id);
		return repository.put(id, user) == null;
	}
	
	public Collection<User> findAll() {
        return repository.values();
    }
	
}
