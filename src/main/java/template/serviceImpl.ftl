package ${serviceImplpackage};

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${daopackage}.I${className}Dao;
import ${pojopackage}.${className};
import ${servicepackage}.I${className}Service;

@Transactional
@Service
@SuppressWarnings("all")
public class ${className}ServiceImpl implements I${className}Service {

	private Logger logger = LoggerFactory.getLogger(${className}ServiceImpl.class);

	@Autowired
	private I${className}Dao ${className?uncap_first}Dao;

	@Override
	public void save${className}(${className} t) {
		t.setCreatedAt(new Date());
		${className?uncap_first}Dao.save(t);
	}

	@Override
	public void delete${className}(${className} t) {
		${className?uncap_first}Dao.delete(t);
	}

	@Override
	public void delete${className}ById(Integer id) {
		${className} ${className} = find${className}ById(id);
		if (${className} != null) {
			delete${className}(${className});
		} else {
			logger.info("Not found ${className}");
		}
	}

	@Override
	public ${className} find${className}ById(Integer id) {
		return ${className?uncap_first}Dao.query(id);
	}

	@Override
	public void update${className}(${className} t) {
		${className} db${className} = find${className}ById(t.getId());
		db${className}.setUpdatedAt(new Date());
		${className?uncap_first}Dao.update(db${className});
	}

	@Override
	public List<${className}> queryForList(String hqlString, Object... values) {
		return ${className?uncap_first}Dao.queryForList(hqlString, values);
	}

}
