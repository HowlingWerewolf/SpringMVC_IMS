package springmvc_ims.repository.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import springmvc_ims.repository.db.DB;

public abstract class Dao {

    @Autowired
	protected DB db;

    public abstract List<?> queryAllAsList();
    public abstract void save(Object product);
    public abstract void delete(Object product);
	public abstract void update(Object product);
    
}