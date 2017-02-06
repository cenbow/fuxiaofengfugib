package com.cqliving.framework.common.jpa.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.domain.Persistable;

/**
 * <p>
 * 抽象实体基类
 * <p/>
 * 
 */
public abstract class AbstractEntity<ID extends Serializable> implements Persistable<ID> {

	private static final long serialVersionUID = 1L;

	public abstract void setId(ID id);

	/**
	 * @see org.springframework.data.domain.Persistable#isNew()
	 */
	public boolean isNew() {
		return null == getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!getClass().equals(obj.getClass())) {
			return false;
		}
		AbstractEntity<?> that = (AbstractEntity<?>) obj;
		return null == this.getId() ? false : this.getId().equals(that.getId());
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += null == getId() ? 0 : getId().hashCode() * 31;
		return hashCode;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
