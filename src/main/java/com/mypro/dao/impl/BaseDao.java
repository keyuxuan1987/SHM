package com.mypro.dao.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.mypro.dao.IBaseDao;
import com.mypro.dao.Page;

@SuppressWarnings("all")
@Component
public class BaseDao<T extends Serializable> implements IBaseDao<T> {

	private Class<T> clazz;// 具体类型，主要用于query()方法
	
	@Autowired
	protected HibernateTemplate hibernateTemplate;// hibernate模板，spring注入
	
	public BaseDao(){}
	/**
	 * 构造方法
	 * BaseDao.
	 *
	 * @param clazz
	 */
	public BaseDao(Class<T> clazz) {
		this.clazz = clazz;
	}

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
	@Override
	public void save(T object) {
		hibernateTemplate.save(object);
	}

	
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
	@Override
	public void delete(T object) {
		hibernateTemplate.delete(object);
	}

	
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
	@Override
	public void update(T object) {
		hibernateTemplate.update(object);
	}

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
	@Override
	public T query(Integer id) {
		T t = (T) hibernateTemplate.get(clazz, id);
		return t;
	}

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
	@Override
	public List<T> queryAll() {
		String hql = "from " + clazz.getSimpleName();
		return queryForList(hql, null);
	}

	/**
	 *  Function:  updateForHql;
	 *  Author :  ASUS,    Version : 1.0,    First complete date : 2015年11月21日 上午11:41:23;
	 *  Description :根据条件更新
	 *  Param and Desciption :
	 *  Return:
	 *  History:
	 *    1. Date:2015年11月21日 上午11:41:23
	 *       Author:kelvin
	 *       Version:
	 *       Modification:
	 *
	 */
	public Integer updateForHql(final String hql, final Object... values) {
		HibernateCallback updateCallback = new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(hql);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				return query.executeUpdate();
			}
		};
		return hibernateTemplate.execute(updateCallback);
	}
	
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
	public Integer queryForSql(final String sql, final Object... values) {
		HibernateCallback queryCallback = new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createSQLQuery(sql);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				return query.uniqueResult();
			}
		};
		return hibernateTemplate.execute(queryCallback);
	}
	
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
	public Object queryForObject(final String hql, final Object... values) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(hql);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				return query.uniqueResult();
			}
		};
		return hibernateTemplate.execute(callback);
	}

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
	public Object queryForTopObject(final String hql, final Object... values) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(hql);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				return query.setFirstResult(0).setMaxResults(1).uniqueResult();
			}
		};
		return hibernateTemplate.execute(callback);
	}

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
	public List queryForList(final String hql, final Object... values) {
		HibernateCallback selectCallback = new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(hql);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				return query.list();
			}
		};
		return (List) hibernateTemplate.execute(selectCallback);
	}

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
	public List queryForList(final String hql, final int recordNum, final Object... values) {
		HibernateCallback selectCallback = new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(hql);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				return query.setFirstResult(0).setMaxResults(recordNum).list();
			}
		};
		return (List) hibernateTemplate.execute(selectCallback);
	}

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
	public List queryForList(final String hql, final Page page, final Object...values) {
		String selectCount = createCountSentence(hql);// 创建select count(*)语句
		Long count = (Long) queryForObject(selectCount, values);// 查询记录总数
		page.init(count.intValue());// 根据记录总数初始化Page对象
		if (page.isEmpty()) {
			return Collections.EMPTY_LIST;
		} else {
			HibernateCallback selectCallback = new HibernateCallback() {
				public Object doInHibernate(Session session) {
					Query query = session.createQuery(hql);
					if (values != null) {
						for (int i = 0; i < values.length; i++) {
							query.setParameter(i, values[i]);
						}
					}
					return query.setFirstResult(page.getFirstResult()).setMaxResults(page.getPageSize()).list();
				}
			};
			return (List) hibernateTemplate.execute(selectCallback);
		}
	}

	/**
	 *  Function:  createCountSentence;
	 *  Author :  ASUS,    Version : 1.0,    First complete date : 2015年11月21日 上午11:42:36;
	 *  Description :根据原始的select语句创建查询记录总数的select语句，原始语句必须有from语句。 生成的语句会去除order by语句，但不会去除group by语句
	 *  Param and Desciption :
	 *  Return:
	 *  History:
	 *    1. Date:2015年11月21日 上午11:42:36
	 *       Author:kelvin
	 *       Version:
	 *       Modification:
	 *
	 */
	private String createCountSentence(String originSentence) {
		if (originSentence.contains(" order")) {
			int begin = originSentence.indexOf("from");
			int end = originSentence.indexOf("order");
			return "select count(*) " + originSentence.substring(begin, end);
		} else {
			int begin = originSentence.indexOf("from");
			return "select count(*) " + originSentence.substring(begin);
		}
	}
}