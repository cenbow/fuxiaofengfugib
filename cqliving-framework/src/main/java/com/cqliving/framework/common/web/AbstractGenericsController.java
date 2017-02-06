package com.cqliving.framework.common.web;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.cqliving.framework.common.domain.AbstractEntity;
import com.cqliving.framework.common.service.EntityService;
import com.cqliving.framework.utils.BeanUtils;
import com.cqliving.framework.utils.GenericsUtils;

/**
 * 抽象泛型控制器
 * 
 * 提供根据泛型获取子类的受管实体和实体服务的信息，包括名称，类名和实例等。
 * 
 * @author zhangpu
 * 
 * @param <T>
 * @param <M>
 */
public abstract class AbstractGenericsController<T extends AbstractEntity, M extends EntityService<T>>
		extends MultiActionController {

	/** 所管理的Entity类型. */
	protected Class<T> entityClass;
	/** 管理Entity所用的 Service */
	private M entityService;

	/**
	 * 取得entityClass的函数
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass() {
		entityClass = GenericsUtils.getSuperClassGenricType(getClass());
		return entityClass;
	}

	/** 获取所管理的对象名.首字母小写，如"user" */
	protected String getEntityName() {
		return StringUtils.uncapitalize(ClassUtils
				.getShortName(getEntityClass()));
	}

	/** 获取所管理的对象名.首字母小写，如"user" */
	protected String getEntityListName() {
		return getEntityName() + "s";
	}

	/**
	 * 获得EntityManager类进行CRUD操作，可以在子类重载.
	 */
	@SuppressWarnings("unchecked")
	protected M getEntityService() {
		if (entityService == null) {
			List<Field> fields = BeanUtils.getFieldsByType(this,
					GenericsUtils.getSuperClassGenricType(getClass(), 1));
			try {
				entityService = (M) BeanUtils.getDeclaredProperty(this, fields
						.get(0).getName());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
			Assert.notNull(entityService, "EntityService未能成功初始化");
		}
		return entityService;
	}

}
