package ${daoImplpackage};



import org.springframework.stereotype.Repository;

import ${basepackage}.BaseDao;
import ${daopackage}.I${className}Dao;
import ${pojopackage}.${className};

@Repository
public class ${className}DaoImpl extends BaseDao<${className}> implements I${className}Dao {

	public ${className}DaoImpl() {
		super(${className}.class);
	}

}