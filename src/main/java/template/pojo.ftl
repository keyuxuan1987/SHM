package ${pojopackage};

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity(name = "${className}")
public class ${className} implements Serializable{
	
	private final static long serialVersionUID = 1L;
	
	<#list attrs as a>
	<#if "${a.field}" == "id">
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	</#if>
	@Column(name = "${a.field}", length = ${a.length})
	private ${a.type} ${a.field};//${a.description}
	
	</#list>
	
	<#list attrs as a>
	public void set${a.field?cap_first}(${a.type} ${a.field}){
		this.${a.field} = ${a.field};
	}
	
	public ${a.type} get${a.field?cap_first}(){
		return this.${a.field};
	}
	</#list>
	
}