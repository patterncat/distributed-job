package com.example.demo.dao;


import com.example.demo.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeDao extends JpaRepository<Notice,Long> {
}
