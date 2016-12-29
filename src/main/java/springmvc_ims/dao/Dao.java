package springmvc_ims.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import springmvc_ims.db.DB;

public abstract class Dao {

    @Autowired
	protected DB db;

    public abstract List<?> queryAllAsList();
    public abstract void save(Object product);
}
