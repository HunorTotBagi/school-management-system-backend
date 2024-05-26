package com.electric_diary.services;

import com.electric_diary.entities.EmailObject;

public interface EmailService {
	void sendSimpleMessage(EmailObject object);

	void sendTemplateMessage(EmailObject object) throws Exception;

	void sendMessageWithAttachment(EmailObject object, String pathToAttachment) throws Exception;
}
