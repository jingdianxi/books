package com.jingdianxi.service;

import java.util.List;

import com.jingdianxi.domain.Novel;
import com.jingdianxi.domain.PagedParam;

public interface NovelService {
	// ����С˵
	int addNovel(Novel novel);
	// ɾ��С˵
	int delNovel(int novelid);
	// �޸�С˵
	int editNovel(Novel novel);
	// ��ѯȫ��С˵
	List<Novel> getAllNovel();
	// ��id��ѯС˵
	Novel getNovelById(int novelid);
	// �������ѯС˵
	List<Novel> getNovelByCategory(int categoryid);
	// �����߲�ѯС˵
	List<Novel> getNovelByAuthor(String author);
	// �޸�hits�ֶ�ֵ
	int updateHits(int chapterid);
	// ��ҳ��ѯС˵
	List<Novel> getNovelPaged(PagedParam pagedParam);
	// ��ѯС˵����
	int getCount(PagedParam pagedParam);
}
