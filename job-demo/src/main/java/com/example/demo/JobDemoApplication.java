package com.example.demo;

import com.example.demo.dao.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JobDemoApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(JobDemoApplication.class, args);
	}

	@Autowired
	NoticeDao noticeDao;

	@Override
	public void run(String... strings) throws Exception {
		System.out.println(noticeDao.findAll());
	}
}
