/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : HibernateTemplate.java
 * 描述        : 数据库处理
 * 作者        : 王弘
 * 日期        : 2011/12/02 下午5:17:30
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       下午5:17:30  王弘
 */
package com.menksoft.cms.application.dao.hibernate;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * 
 * @author David
 */
@Repository
public class HibernateTemplate {
	@Resource
	private SessionFactory sessionFactory;

	/**
	 * 保存一个业务实体
	 * 
	 * @param entity 待保存的业务实体。
	 * 
	 * @throws Throwable 方法执行过程中发生异常时抛出。例如：操作对象为空或无法连接数据库。
	 */
	public <T> void save(T entity) throws Throwable {
		Assert.notNull(entity, "待保存的对象为NULL.");

		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	/**
	 * 更新一个业务实体
	 * 
	 * @param entity 待更新的业务实体。
	 * 
	 * @throws Throwable 方法执行过程中发生异常时抛出。例如：操作对象为空或无法连接数据库。
	 */
	public <T> void update(T entity) throws Throwable {
		Assert.notNull(entity, "待更新的对象为NULL.");

		Session session = sessionFactory.getCurrentSession();
		session.merge(entity);
	}

	/**
	 * 删除一个业务实体
	 * 
	 * @param entity 待删除的业务实体。
	 * 
	 * @throws Throwable 方法执行过程中发生异常时抛出。例如：操作对象为空或无法连接数据库。
	 */
	public <T> void delete(T entity) throws Throwable {
		Assert.notNull(entity, "待删除的对象为NULL.");

		Session session = sessionFactory.getCurrentSession();
		session.delete(entity);
	}

	/**
	 * 根据实体唯一标识删除一个业务实体
	 * 
	 * @param clazz 业务实体的类型
	 * @param idName 业务实体唯一标识的名称
	 * 
	 * @param id 业务实体的唯一标识, 通常对应关系数据库中的实体主键。
	 * 
	 * @throws Throwable 方法执行过程中发生异常时抛出。例如：操作对象为空或无法连接数据库。
	 */
	public <T> void deleteById(Class<T> clazz, String idName, Serializable id) throws Throwable {
		Assert.notNull(clazz, "待删除的对象的类型为NULL.");
		Assert.notNull(id, "待删除的对象的唯一标识为NULL.");

		//将类型转换为全限定类名称
		final String classType = "CLASS ";
		final String interfaceType = "INTERFACE ";
		String className = clazz.toString().toUpperCase();
		className = className.startsWith(classType) ? className.substring(classType.length()) : className.substring(interfaceType.length());

		//构造hql删除语句
		StringBuffer buffer = new StringBuffer("DELETE FROM ").append(className).append(" WHERE ").append(idName).append(" = ?");

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(buffer.toString());
		query.setParameter(0, id);
		query.executeUpdate();
	}

	/**
	 * 根据实体的唯一标识获得业务实体。
	 * 
	 * @param clazz 业务实体的类型
	 * @param id 业务实体的唯一标识，通常对应关系数据库中的实体主键。
	 * 
	 * @return 与唯一标识相同的业务实体。找不到相同的业务实体时，返回null。
	 * 
	 * @throws Throwable 方法执行过程中发生异常时抛出，当发生异常时，返回值为null。
	 */
	@SuppressWarnings("unchecked")
	public <T> T getEntityById(Class<T> clazz, Serializable id) throws Throwable {
		Assert.notNull(clazz, "待获取的对象的类型为NULL.");
		Assert.notNull(id, "待获取的对象唯一标识为NULL.");

		Session session = sessionFactory.getCurrentSession();
		Object object = session.get(clazz, id);

		return (object == null) ? null : (T) object;
	}

	/**
	 * 根据离线的detachedCriteria 获得业务实体，该方法只能用来返回一个唯一存在的业务实体。<br/>
	 * 方法的调用者需要确保所设置的detachedCriteria只会返回一个唯一的业务实体，否则方法将会抛出异常。
	 * 
	 * @param detachedCriteria 离线查询条件对象。<br/>
	 * 可使用DetachedCriteria.forClass(clazz)创建基于特定业务实体的DetachedCriteria实例。<br/>
	 * 如果需要为DetachedCriteria添加查询条件，可使用detachedCriteria.add(Restrictions)的方式进行添加。<br/>
	 * 例如，可以通过以下方式查询id = 1的person实体。<br/>
	 * <pre>
	 * DetachedCriteria.forClass(Person.class)
	 * 		.add(Restrictions.eq("id", 1))
	 * 		.getExecutableCriteria(session)
	 * 		.list();
	 * </pre>
	 * 更多信息请参考{@link org.hibernate.criterion.DetachedCriteria}
	 * 
	 * @return 符合查询条件的业务实体。找不到业务实体时，返回null。
	 * 
	 * @throws Throwable 方法执行过程中发生异常时抛出，当发生异常时，返回值为null。
	 */
	@SuppressWarnings("unchecked")
	public <T> T getEntityByDetachedCriteria(DetachedCriteria detachedCriteria) throws Throwable {
		Assert.notNull(detachedCriteria, "离线查询对象为NULL.");

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		Object object = criteria.uniqueResult();

		return (object == null) ? null : (T) object;
	}

	/**
	 * 根据离线的detachedCriteria 获得业务实体的集合(不分页不排序)
	 * 
	 * @param detachedCriteria 离线查询条件对象。<br/>
	 * 可使用DetachedCriteria.forClass(clazz)创建基于特定业务实体的DetachedCriteria实例。<br/>
	 * 如果需要为DetachedCriteria添加查询条件，可使用detachedCriteria.add(Restrictions)的方式进行添加。<br/>
	 * 例如，可以通过以下方式查询id = 1的person实体。<br/>
	 * <pre>
	 * DetachedCriteria.forClass(Person.class)
	 * 		.add(Restrictions.eq("id", 1))
	 * 		.getExecutableCriteria(session)
	 * 		.list();
	 * </pre>
	 * 更多信息请参考{@link org.hibernate.criterion.DetachedCriteria}
	 * 
	 * @return 符合查询条件的业务实体的集合。找不到业务实体时，返回null。
	 * @throws Throwable 方法执行过程中发生异常时抛出，当发生异常时，返回值为null。
	 */
	public <T> List<T> getEntitiesByDetachedCriteria(DetachedCriteria detachedCriteria) throws Throwable {
		return getEntitiesByDetachedCriteriaWithOrders(detachedCriteria, null);
	}

	/**
	 * 根据离线的detachedCriteria 获得业务实体的集合(排序不分页)
	 * 
	 * @param detachedCriteria 离线查询条件对象。<br/>
	 * 可使用DetachedCriteria.forClass(clazz)创建基于特定业务实体的DetachedCriteria实例。<br/>
	 * 如果需要为DetachedCriteria添加查询条件，可使用detachedCriteria.add(Restrictions)的方式进行添加。<br/>
	 * 例如，可以通过以下方式查询id = 1的person实体。<br/>
	 * <pre>
	 * DetachedCriteria.forClass(Person.class)
	 * 		.add(Restrictions.eq("id", 1))
	 * 		.getExecutableCriteria(session)
	 * 		.list();
	 * </pre>
	 * 更多信息请参考{@link org.hibernate.criterion.DetachedCriteria}
	 * @param orders 排序方式。key为业务实体属性名称；value为排序方式
	 * 
	 * @return 符合查询条件的业务实体的集合。找不到业务实体时，返回null。
	 * 
	 * @throws Throwable 方法执行过程中发生异常时抛出，当发生异常时，返回值为null。
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getEntitiesByDetachedCriteriaWithOrders(DetachedCriteria detachedCriteria,
			LinkedHashMap<String, OrderPattern> orders) throws Throwable {

		Assert.notNull(detachedCriteria, "查询条件为NULL. 无法获取业务实体信息.");

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);

		buildOrderCriteria(criteria, orders);

		return criteria.list();
	}

	/**
	 * 根据离线的detachedCriteria 获得业务实体的集合(分页不排序)
	 * 
	 * @param detachedCriteria 离线查询条件对象。<br/>
	 * 可使用DetachedCriteria.forClass(clazz)创建基于特定业务实体的DetachedCriteria实例。<br/>
	 * 如果需要为DetachedCriteria添加查询条件，可使用detachedCriteria.add(Restrictions)的方式进行添加。<br/>
	 * 例如，可以通过以下方式查询id = 1的person实体。<br/>
	 * <pre>
	 * DetachedCriteria.forClass(Person.class)
	 * 		.add(Restrictions.eq("id", 1))
	 * 		.getExecutableCriteria(session)
	 * 		.list();
	 * </pre>
	 * 更多信息请参考{@link org.hibernate.criterion.DetachedCriteria}
	 * 
	 * @param startIndex 分页起始的索引
	 * @param count 分页获取的记录数量
	 * 
	 * @return 符合查询条件的业务实体的集合。找不到业务实体时，返回null。
	 * 
	 * @throws Throwable 方法执行过程中发生异常时抛出，当发生异常时，返回值为null。
	 */
	public <T> PagingTools<T> getEntitiesByDetachedCriteriaWithPaging(DetachedCriteria detachedCriteria,
			Integer startIndex, Integer count) throws Throwable {

		return getEntitiesByDetachedCriteriaWithPagingAndOrders(detachedCriteria,startIndex, count, null);
	}

	/**
	 * 根据离线的detachedCriteria 获得业务实体的集合(分页排序)
	 * 
	 * @param detachedCriteria 离线查询条件对象。<br/>
	 * 可使用DetachedCriteria.forClass(clazz)创建基于特定业务实体的DetachedCriteria实例。<br/>
	 * 如果需要为DetachedCriteria添加查询条件，可使用detachedCriteria.add(Restrictions)的方式进行添加。<br/>
	 * 例如，可以通过以下方式查询id = 1的person实体。<br/>
	 * <pre>
	 * DetachedCriteria.forClass(Person.class)
	 * 		.add(Restrictions.eq("id", 1))
	 * 		.getExecutableCriteria(session)
	 * 		.list();
	 * </pre>
	 * 更多信息请参考{@link org.hibernate.criterion.DetachedCriteria}
	 * 
	 * @param startIndex 分页起始的索引
	 * @param count 分页获取的记录数量
	 * @param orders 排序方式。key为业务实体属性名称；value为排序方式
	 * 
	 * @return 符合查询条件的业务实体的集合。找不到业务实体时，返回null。
	 * 
	 * @throws Throwable 方法执行过程中发生异常时抛出，当发生异常时，返回值为null。
	 */
	@SuppressWarnings("unchecked")
	public <T> PagingTools<T> getEntitiesByDetachedCriteriaWithPagingAndOrders(DetachedCriteria detachedCriteria,
			Integer startIndex, Integer count, LinkedHashMap<String, OrderPattern> orders) throws Throwable {

		Assert.notNull(detachedCriteria, "查询条件为NULL. 无法获取业务实体信息.");

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		long totalCount = buildPagingCriteria(criteria, startIndex, count);
		buildOrderCriteria(criteria, orders);

		List<T> list = criteria.list();
		return new PagingTools<T>(totalCount, count, startIndex, list);
	}

	/**
	 * 根据Hql获得业务实体的集合。
	 * 
	 * @param hql hql语句
	 * @param properties 查询语句对应的条件参数
	 * 
	 * @return 符合条件的业务实体的集合。找不到业务实体时，返回null。
	 * 
	 * @throws Throwable 方法执行过程中发生异常时抛出，当发生异常时，返回值为null。
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getEntitiesByHql(String hql, Map<String,Object> properties) throws Throwable {

		Assert.hasText(hql, "查询语句为NULL. 无法获取业务实体信息.");

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		if(properties != null) {
			query.setProperties(properties);
		}

		return query.list();
	}

	/**
	 * 根据Hql获得业务实体的集合。(分页)
	 * 
	 * @param hql hql语句
	 * @param properties 查询语句对应的条件参数
	 * @param startIndex 分页起始的索引
	 * @param count 分页获取的记录数量
	 * @param properties 查询语句对应的条件参数
	 * 
	 * @return 符合条件的业务实体的集合。找不到业务实体时，返回null。
	 * 
	 * @throws Throwable 方法执行过程中发生异常时抛出，当发生异常时，返回值为null。
	 */
	@SuppressWarnings("unchecked")
	public <T> PagingTools<T> getEntitiesByHqlWithPaging(String hql, Map<String,Object> properties,
			Integer startIndex, Integer count) throws Throwable {

		Assert.hasText(hql, "查询语句为NULL. 无法获取业务实体信息.");

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		if(properties != null) {
			query.setProperties(properties);
		}

		long totalCount = buildPagingCriteria(query, startIndex, count);

		List<T> list = query.list();
		return new PagingTools<T>(totalCount, count, startIndex, list);
	}

	/**
	 * 根据sql获得业务实体的集合。
	 * 
	 * @param clazz 业务实体的类型
	 * @param sql sql语句
	 * @param properties 查询语句对应的条件参数
	 * 
	 * @return 符合条件的业务实体的集合。找不到业务实体时，返回null。
	 * 
	 * @throws Throwable 方法执行过程中发生异常时抛出，当发生异常时，返回值为null。
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getEntitiesBySql(Class<T> clazz, String sql, Map<String,Object> properties) throws Throwable {
		Assert.hasText(sql, "查询语句为NULL. 无法获取业务实体信息.");
		Assert.notNull(clazz, "待获取的对象的类型为NULL.");

		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql).addEntity(clazz);

		if(properties != null) {
			query.setProperties(properties);
		}

		return query.list();
	}

	/**
	 * 根据sql获得业务实体的集合。(分页)
	 * 
	 * @param clazz 业务实体的类型
	 * @param sql sql语句
	 * @param startIndex 分页起始的索引
	 * @param count 分页获取的记录数量
	 * @param properties 查询语句对应的条件参数
	 * 
	 * @return 符合条件的业务实体的集合。找不到业务实体时，返回null。
	 * 
	 * @throws Throwable 方法执行过程中发生异常时抛出，当发生异常时，返回值为null。
	 */
	@SuppressWarnings("unchecked")
	public <T> PagingTools<T> getEntitiesBySqlWithPaging(Class<T> clazz, String sql, Map<String,Object> properties,
			Integer startIndex, Integer count) throws Throwable {
		Assert.hasText(sql, "查询语句为NULL. 无法获取业务实体信息.");
		Assert.notNull(clazz, "待获取的对象的类型为NULL.");

		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql).addEntity(clazz);
		if(properties != null) {
			query.setProperties(properties);
		}
		long totalCount = buildPagingCriteria(query, startIndex, count);
		List<T> list = query.list();

		return new PagingTools<T>(totalCount, count, startIndex, list);
	}

	/**
	 * 执行一条hql语句，主要用于批量的更新或删除
	 * 
	 * @param hql hql语句
	 * @param properties 查询语句对应的条件参数
	 * 
	 * @return 执行后受影响的行数
	 * 
	 * @throws Throwable 方法执行过程中发生异常时抛出，当发生异常时，返回值为null。
	 */
	public int executeHql(String hql, Map<String,Object> properties) throws Throwable  {
		Assert.hasText(hql, "执行语句为NULL. 无法执行请求.");

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);

		if(properties != null) {
			query.setProperties(properties);
		}

		return query.executeUpdate();
	}

	/**
	 * 执行一条sql语句，主要用于批量的更新或删除
	 * 
	 * @param sql sql语句
	 * @param properties 查询语句对应的条件参数
	 * 
	 * @return 执行后受影响的行数
	 * 
	 * @throws Throwable 方法执行过程中发生异常时抛出，当发生异常时，返回值为null。
	 */
	public int executeSql(String sql, Map<String,Object> properties) throws Throwable  {
		Assert.hasText(sql, "执行语句为NULL. 无法执行请求.");

		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);

		if(properties != null) {
			query.setProperties(properties);
		}

		return query.executeUpdate();
	}

	/**
	 * 为查询条件拼装排序条件
	 * 
	 * @param criteria 查询条件对象
	 * @param orders 排序信息
	 * @throws Throwable 方法执行过程中发生异常时抛出，当发生异常时，返回原始的查询条件对象。
	 */
	protected void buildOrderCriteria(Criteria criteria, LinkedHashMap<String, OrderPattern> orders) throws Throwable {
		Assert.notNull(criteria, "查询对象为NULL. 无法为其拼装查询条件.");

		if (orders != null) {
			Iterator<Entry<String, OrderPattern>> iterator = orders.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, OrderPattern> entry = iterator.next();
				String propertyName = entry.getKey();
				OrderPattern order = entry.getValue();
				if (OrderPattern.desc.equals(order)) {
					criteria.addOrder(Order.desc(propertyName));
				} else {
					criteria.addOrder(Order.asc(propertyName));
				}
			}
		}
	}

	/**
	 * 为查询条件拼装分页条件
	 * 
	 * @param criteria 查询条件对象
	 * @param startIndex 分页起始的索引
	 * @param count 分页获取的记录数量
	 * @return 分页查询总数
	 * @throws Throwable 方法执行过程中发生异常时抛出，当发生异常时，返回值为0。
	 */
	protected long buildPagingCriteria(Criteria criteria, Integer startIndex, Integer count) throws Throwable {
		long totalCount = 0;
		CriteriaImpl impl = (CriteriaImpl) criteria;
		// 先把Projection取出来,清空来执行Count操作
		Projection projection = impl.getProjection();
		totalCount = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).longValue();
		// 将之前的Projection重新设回去
		criteria.setProjection(projection);
		if (projection == null) {
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}

		if (startIndex >= 0) {
			criteria.setFirstResult(startIndex);
		} else {
			criteria.setFirstResult(0);
		}

		if (count > 0) {
			criteria.setMaxResults(count);
		} else {
			criteria.setMaxResults(Integer.MAX_VALUE);
		}

		return totalCount;
	}

	/**
	 * 为查询拼装分页条件
	 * 
	 * @param query 查询对象
	 * @param startIndex 分页起始的索引
	 * @param count 分页获取的记录数量
	 * @return 分页查询总数
	 * @throws Throwable 方法执行过程中发生异常时抛出，当发生异常时，返回值为0。
	 */
	protected long buildPagingCriteria(Query query, Integer startIndex, Integer count) throws Throwable {
		long totalCount = 0;

		ScrollableResults scrollResults = query.scroll();
		scrollResults.last();
		if (scrollResults.getRowNumber() >= 0) {
			totalCount = scrollResults.getRowNumber() + 1;
		}

		if (startIndex >= 0) {
			query.setFirstResult(startIndex);
		} else {
			query.setFirstResult(0);
		}

		if (count > 0) {
			query.setMaxResults(count);
		} else {
			query.setMaxResults(Integer.MAX_VALUE);
		}

		return totalCount;
	}
	@SuppressWarnings({ "unchecked"})
	public List<Object[]> getBySql(String sql) throws Throwable {
		Assert.hasText(sql, "查询语句为NULL. 无法获取业务实体信息.");

		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		//query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
	public int getCountBySql(String sql) throws Throwable {
		Assert.hasText(sql, "查询语句为NULL. 无法获取业务实体信息.");

		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		int count=0;
		count= ((Number)query.uniqueResult()).intValue(); 
		//query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		return count;
	}
}