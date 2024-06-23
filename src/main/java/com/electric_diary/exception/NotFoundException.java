package com.electric_diary.exception;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {
	private String entityType;
	private String entityId;

	public NotFoundException(String entityType, String entityId) {
		this.entityType = entityType;
		this.entityId = entityId;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getEntityType() {
		return entityType;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
}
