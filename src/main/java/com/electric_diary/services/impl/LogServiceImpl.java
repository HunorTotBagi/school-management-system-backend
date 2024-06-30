package com.electric_diary.services.impl;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.electric_diary.exception.NotFoundException;
import com.electric_diary.services.LogService;

@Service
public class LogServiceImpl implements LogService {

	public byte[] obterLog() {
		try {
			InputStream inputStream = new ClassPathResource("/electric_diary/logs/spring-boot-logging.log").getInputStream();
			byte[] log = FileCopyUtils.copyToByteArray(inputStream);
			return log;
		} catch (IOException e) {
			// Change this exception
			throw new NotFoundException("Student", "4");
		}
	}
}
