package com.jp.study.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jp.study.entity.ShohinEntity;

@Mapper
public interface StudyDao {

	/**
	 * 商品リスト一覧の取得
	 * @return List<ShohinEntity> 
	 */
	List<ShohinEntity> findAllShohinList();

}
