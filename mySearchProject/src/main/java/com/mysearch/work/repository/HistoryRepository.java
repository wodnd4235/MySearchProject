package com.mysearch.work.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mysearch.work.entity.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long>{
	List<History> findByMbrIdOrderByCreatedDateDesc(Long mbrId);
}
