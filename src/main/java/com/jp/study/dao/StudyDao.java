package com.jp.study.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jp.study.dto.SearchItemDto;
import com.jp.study.entity.ShohinEntity;

@Mapper
public interface StudyDao {

	/**
	 * 商品リスト一覧の取得
	 * @return List<ShohinEntity> 
	 */
	List<ShohinEntity> findAllShohinList();

	ShohinEntity  selectByShohinKingaku(@Param("shohinCd") String shohinCd);

	/**
	 * 登録されている商品数量
	 * @return int
	 */
	String selectByLastShohinCd(@Param("color") String color);
	
	void insertShohin(ShohinEntity shohinEntity);
	
	ShohinEntity findByShohin(@Param("shohinCd") String shohinCd);
	
	int delete(@Param("shohinCd")String shohinCd);
	
	int updateByPrimary(ShohinEntity shohinEntity);
	
	List<ShohinEntity> searchItemList(SearchItemDto searchItemDto);
}
