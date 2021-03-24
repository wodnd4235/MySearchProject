package com.mysearch.work.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mysearch.work.entity.History;
import com.mysearch.work.entity.SearchHistory;

@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long>{
	List<SearchHistory> findTop10ByOrderByCntDesc();

	SearchHistory findByKeyword(String keyword);
}
