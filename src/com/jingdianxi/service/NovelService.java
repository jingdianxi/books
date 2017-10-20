package com.jingdianxi.service;

import java.util.List;

import com.jingdianxi.domain.Novel;
import com.jingdianxi.domain.PagedParam;

public interface NovelService {
	// 增加小说
	int addNovel(Novel novel);
	// 删除小说
	int delNovel(int novelid);
	// 修改小说
	int editNovel(Novel novel);
	// 查询全部小说
	List<Novel> getAllNovel();
	// 按id查询小说
	Novel getNovelById(int novelid);
	// 按分类查询小说
	List<Novel> getNovelByCategory(int categoryid);
	// 按作者查询小说
	List<Novel> getNovelByAuthor(String author);
	// 修改hits字段值
	int updateHits(int chapterid);
	// 分页查询小说
	List<Novel> getNovelPaged(PagedParam pagedParam);
	// 查询小说总数
	int getCount(PagedParam pagedParam);
}
