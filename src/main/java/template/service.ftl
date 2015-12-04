package ${servicepackage};

import java.util.List;
import ${pojopackage}.${className};

public interface I${className}Service {

	public void save${className}(${className} t);

	public void delete${className}(${className} t);
	
	public void delete${className}ById(Integer id);

	public ${className} find${className}ById(Integer id);

	public void update${className}(${className} t);

	public List<${className}> queryForList(String hqlString, Object... values);
	
}
