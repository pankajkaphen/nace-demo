package com.pankaj.nace.model;

import javax.persistence.Id;

public class NaceBean {
	@Id
	private String orderId;
	private String level;
	private String code;
	private String parent;
	private String description;
	private String thisItemIncludes;
	private String thisItemAlsoIncludes;
	private String rulings;
	private String thisItemExcludes;
	private String referenceToIsicRev4;

	public NaceBean() {
	}

	public NaceBean(String order, String level, String code, String parent, String description, String thisItemIncludes,
			String thisItemAlsoIncludes, String rulings, String thisItemExcludes, String referenceToIsicRev4) {
		super();
		this.orderId = order;
		this.level = level;
		this.code = code;
		this.parent = parent;
		this.description = description;
		this.thisItemIncludes = thisItemIncludes;
		this.thisItemAlsoIncludes = thisItemAlsoIncludes;
		this.rulings = rulings;
		this.thisItemExcludes = thisItemExcludes;
		this.referenceToIsicRev4 = referenceToIsicRev4;
	}


	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String order) {
		this.orderId = order;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThisItemIncludes() {
		return thisItemIncludes;
	}

	public void setThisItemIncludes(String thisItemIncludes) {
		this.thisItemIncludes = thisItemIncludes;
	}

	public String getThisItemAlsoIncludes() {
		return thisItemAlsoIncludes;
	}

	public void setThisItemAlsoIncludes(String thisItemAlsoIncludes) {
		this.thisItemAlsoIncludes = thisItemAlsoIncludes;
	}

	public String getRulings() {
		return rulings;
	}

	public void setRulings(String rulings) {
		this.rulings = rulings;
	}

	public String getThisItemExcludes() {
		return thisItemExcludes;
	}

	public void setThisItemExcludes(String thisItemExcludes) {
		this.thisItemExcludes = thisItemExcludes;
	}

	public String getReferenceToIsicRev4() {
		return referenceToIsicRev4;
	}

	public void setReferenceToIsicRev4(String referenceToIsicRev4) {
		this.referenceToIsicRev4 = referenceToIsicRev4;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + ((referenceToIsicRev4 == null) ? 0 : referenceToIsicRev4.hashCode());
		result = prime * result + ((rulings == null) ? 0 : rulings.hashCode());
		result = prime * result + ((thisItemAlsoIncludes == null) ? 0 : thisItemAlsoIncludes.hashCode());
		result = prime * result + ((thisItemExcludes == null) ? 0 : thisItemExcludes.hashCode());
		result = prime * result + ((thisItemIncludes == null) ? 0 : thisItemIncludes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NaceBean other = (NaceBean) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (referenceToIsicRev4 == null) {
			if (other.referenceToIsicRev4 != null)
				return false;
		} else if (!referenceToIsicRev4.equals(other.referenceToIsicRev4))
			return false;
		if (rulings == null) {
			if (other.rulings != null)
				return false;
		} else if (!rulings.equals(other.rulings))
			return false;
		if (thisItemAlsoIncludes == null) {
			if (other.thisItemAlsoIncludes != null)
				return false;
		} else if (!thisItemAlsoIncludes.equals(other.thisItemAlsoIncludes))
			return false;
		if (thisItemExcludes == null) {
			if (other.thisItemExcludes != null)
				return false;
		} else if (!thisItemExcludes.equals(other.thisItemExcludes))
			return false;
		if (thisItemIncludes == null) {
			if (other.thisItemIncludes != null)
				return false;
		} else if (!thisItemIncludes.equals(other.thisItemIncludes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [order=" + orderId + ", level=" + level + ", code=" + code + ", parent=" + parent
				+ ", description=" + description + ", thisItemIncludes=" + thisItemIncludes + ", thisItemAlsoIncludes="
				+ thisItemAlsoIncludes + ", rulings=" + rulings + ", thisItemExcludes=" + thisItemExcludes
				+ ", referenceToIsicRev4=" + referenceToIsicRev4 + "]";
	}

}
