package com.menksoft.cms.manage.data.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.application.organization.entity.User;
import com.menksoft.cms.manage.data.entity.Message;
import com.menksoft.cms.manage.data.service.MessageService;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.entity.DataType;
import com.menksoft.cms.manage.webContent.entity.Language;
import com.menksoft.cms.manage.webContent.service.DataTypeService;
import com.menksoft.cms.manage.webContent.service.LanguageService;

@Service("MessageService")
public class MessageServiceImpl implements MessageService {
	 @Resource 
	    private HibernateTemplate hibernateTemplate;
     @Resource
     private DataTypeService dataTypeService;
     @Resource
     private LanguageService languageService;
		/**
		 * 创建新实体
		 * @param message
		 * @throws Throwable
		 */
		@Override
		public void save(Message message) throws Throwable {
			// TODO Auto-generated method stub
			hibernateTemplate.save(message);
		}
		/**
		 * 更新实体
		 * @param message
		 * @throws Throwable
		 */
		@Override
		public void update(Message message) throws Throwable {
			// TODO Auto-generated method stub
			hibernateTemplate.update(message);
		}
		/**
		 * 删除实体
		 * @param id
		 * @param idName
		 * @throws Throwable
		 */
		@Override
		public void delete(String id ) throws Throwable {
			// TODO Auto-generated method stub
//			hibernateTemplate.delete(message);
//			hibernateTemplate.deleteById(Message.class, idName, id);
		
			hibernateTemplate.executeSql("delete from r_data_message where id in("+id+")", null);
		}


		/**
		 * 查询所有的数据
		 * @return
		 * @throws Throwable
		 */
		@Override
		public List<Message> getMessages() throws Throwable {
			// TODO Auto-generated method stub
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Message.class);
			//add some condition
			return  hibernateTemplate.getEntitiesByDetachedCriteria(detachedCriteria);
		}
		/**
		 * 查询单个实体
		 * @param id 
		 * @return
		 * @throws Throwable
		 */
		@Override
		public Message getMessagebyId(long id) throws Throwable {
			// TODO Auto-generated method stub
			Message message = hibernateTemplate.getEntityById(Message.class, id);
			return message;
		}
		/**
		 * 检查实体是否重复
		 * @param message 
		 * @return
		 * @throws Throwable
		 */
		@Override
		public List<Message> checkRepeat(Message message) throws Throwable {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM r_data_message WHERE id <>"+message.getId();
//			String sqlStr = "select * from r_data_message where bm='"+message.getBm()+"or name='"+message.getName()+"'";
			return hibernateTemplate.getEntitiesBySql(Message.class, sql, null);
		}
		/**
		 * 依据栏目的id查询该栏目下的内容
		 * @param id 
		 * @return
		 * @throws Throwable
		 */
		@Override
		public PagingTools<Message> getMessages(Integer startIndex, Integer count) throws Throwable {
			// TODO Auto-generated method stub
			DetachedCriteria detachedCriteria =DetachedCriteria.forClass(Message.class);
			
			return hibernateTemplate.getEntitiesByDetachedCriteriaWithPaging(detachedCriteria, startIndex, count);
			
		}
		/**
		 * 依据栏目的id和审核人，查询该栏目下的内容
		 * @param id approve
		 * @return
		 * @throws Throwable
		 */
		@Override
		public PagingTools<Message> getMessagesByColumnAndApprove(Columns column,
				User approve, Integer startIndex, Integer count)
				throws Throwable {
			// TODO Auto-generated method stub
            DetachedCriteria detachedCriteria =DetachedCriteria.forClass(Message.class);
			detachedCriteria.add(Restrictions.eq("approve", approve));
			detachedCriteria.add(Restrictions.eq("parent",column));
			return hibernateTemplate.getEntitiesByDetachedCriteriaWithPaging(detachedCriteria, startIndex, count);
		}
		@Override
		public PagingTools<Message> queryMessages(Message message, Integer startIndex,
				Integer count) throws Throwable {
			// TODO Auto-generated method stub
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Message.class);
			String title = message.getTitle();
			if (title!=null&&title!="") {
				detachedCriteria.add(Restrictions.eq("title", title));
			}
			String name = message.getName();
			if (name!=null&&name!="") {
				detachedCriteria.add(Restrictions.eq("name", name));
			}
			String phone = message.getPhone();
			if (phone!=null&&phone!="") {
				detachedCriteria.add(Restrictions.eq("phone", phone));
			}
			String email = message.getEmail();
			if (email!=null&&email!="") {
				detachedCriteria.add(Restrictions.eq("email", email));
			}
			String state = message.getState();
			if (state!=null&&state!="") {
				detachedCriteria.add(Restrictions.eq("state", state));
			}
			return hibernateTemplate.getEntitiesByDetachedCriteriaWithPaging(detachedCriteria, startIndex, count);
		}
}
