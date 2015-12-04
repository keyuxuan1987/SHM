package com.mypro.dao;

import java.io.Serializable;
import java.util.List;


@SuppressWarnings("all")
public interface IBaseDao<T extends Serializable> {

	/**
	 *  Function:  save
	 *  Author :  ASUS,    Version : 1.0,    First complete date : 2015年11月21日 上午11:39:04
	 *  Description :保存对象
	 *  Param and Desciption :
	 *  Return:
	 *  History:
	 *    1. Date:2015年11月21日 上午11:39:04
	 *       Author:kelvin
	 *       Version:
	 *       Modification:
	 *
	 */
	void save(T object);

	/**
	 *  Function:  delete
	 *  Author :  ASUS,    Version : 1.0,    First complete date : 2015年11月21日 上午11:39:25
	 *  Description :删除对象
	 *  Param and Desciption :
	 *  Return:
	 *  History:
	 *    1. Date:2015年11月21日 上午11:39:25
	 *       Author:kelvin
	 *       Version:
	 *       Modification:
	 *
	 */
	void delete(T object);

	/**
	 *  Function:  update
	 *  Author :  ASUS,    Version : 1.0,    First complete date : 2015年11月21日 上午11:40:08
	 *  Description :更新对象
	 *  Param and Desciption :
	 *  Return:
	 *  History:
	 *    1. Date:2015年11月21日 上午11:40:08
	 *       Author:kelvin
	 *       Version:
	 *       Modification:
	 *
	 */
	void update(T object);

	/**
	 *  Function:  query
	 *  Author :  ASUS,    Version : 1.0,    First complete date : 2015年11月21日 上午11:40:38
	 *  Description :根据id查询
	 *  Param and Desciption :
	 *  Return:
	 *  History:
	 *    1. Date:2015年11月21日 上午11:40:38
	 *       Author:kelvin
	 *       Version:
	 *       Modification:
	 *
	 */
	T query(Integer id) ;

	/**
	 *  Function:  queryAll
	 *  Author :  ASUS,    Version : 1.0,    First complete date : 2015年11月21日 上午11:41:06
	 *  Description :查询所有对象
	 *  Param and Desciption :
	 *  Return:
	 *  History:
	 *    1. Date:2015年11月21日 上午11:41:06
	 *       Author:kelvin
	 *       Version:
	 *       Modification:
	 *
	 */
	List<T> queryAll();

	/**
	 *  Function:  updateForHql;
	 *  Author :  ASUS,    Version : 1.0,    First complete date : 2015年11月21日 上午11:41:23;
	 *  Description :根据条件Hql更新
	 *  Param and Desciption :
	 *  Return:
	 *  History:
	 *    1. Date:2015年11月21日 上午11:41:23
	 *       Author:kelvin
	 *       Version:
	 *       Modification:
	 *
	 */
	Integer updateForHql(String hql, Object... values);
	
	/**
	 *  Function:  queryForSql;
	 *  Author :  ASUS,    Version : 1.0,    First complete date : 2015年11月21日 上午11:41:23;
	 *  Description :根据条件sql查询
	 *  Param and Desciption :
	 *  Return:
	 *  History:
	 *    1. Date:2015年11月21日 上午11:41:23
	 *       Author:kelvin
	 *       Version:
	 *       Modification:
	 *
	 */
	Integer queryForSql(String sql, Object... values);

	/**
	 *  Function:  queryForObject;
	 *  Author :  ASUS,    Version : 1.0,    First complete date : 2015年11月21日 上午11:41:35;
	 *  Description :查询得到一条记录
	 *  Param and Desciption :
	 *  Return:
	 *  History:
	 *    1. Date:2015年11月21日 上午11:41:35
	 *       Author:kelvin
	 *       Version:
	 *       Modification:
	 *
	 */
	Object queryForObject(String hql, Object... values);

	/**
	 *  Function:  queryForTopObject;
	 *  Author :  ASUS,    Version : 1.0,    First complete date : 2015年11月21日 上午11:41:47;
	 *  Description :查询得到第一条记录
	 *  Param and Desciption :
	 *  Return:
	 *  History:
	 *    1. Date:2015年11月21日 上午11:41:47
	 *       Author:kelvin
	 *       Version:
	 *       Modification:
	 *
	 */
	Object queryForTopObject(String hql, Object... values);

	/**
	 *  Function:  queryForList;
	 *  Author :  ASUS,    Version : 1.0,    First complete date : 2015年11月21日 上午11:42:01;
	 *  Description :查询得到记录列表
	 *  Param and Desciption :
	 *  Return:
	 *  History:
	 *    1. Date:2015年11月21日 上午11:42:01
	 *       Author:kelvin
	 *       Version:
	 *       Modification:
	 *
	 */
	List queryForList(String hql, Object... values);

	/**
	 *  Function:  queryForList;
	 *  Author :  ASUS,    Version : 1.0,    First complete date : 2015年11月21日 上午11:42:10;
	 *  Description :查询得到记录列表，限定返回的记录数量
	 *  Param and Desciption :
	 *  Return:
	 *  History:
	 *    1. Date:2015年11月21日 上午11:42:10
	 *       Author:kelvin
	 *       Version:
	 *       Modification:
	 *
	 */
	List queryForList(String hql, int recordNum, Object... values);

	/**
	 *  Function:  queryForList;
	 *  Author :  ASUS,    Version : 1.0,    First complete date : 2015年11月21日 上午11:42:25;
	 *  Description :查询得到记录列表，查询结果分页显示
	 *  Param and Desciption :
	 *  Return:
	 *  History:
	 *    1. Date:2015年11月21日 上午11:42:25
	 *       Author:kelvin
	 *       Version:
	 *       Modification:
	 *
	 */
	List queryForList(String hql, Page page, Object... values);

}