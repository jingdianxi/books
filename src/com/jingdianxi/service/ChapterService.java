package com.jingdianxi.service;

import java.util.List;
import java.util.Map;

import com.jingdianxi.domain.Chapter;

public interface ChapterService {
	// ��С˵��ѯ�½�
	List<Chapter> getChapterByNovel(int novelid);
	// ��id��ѯ�½�
	Chapter getChapterById(int chapterid);
	// ��ѯǰһ�½�id
	int getPrevChapter(int chapterid);
	// ��ѯ��һ�½�id
	int getNextChapter(int chapterid);
	// �����½�
	int addChapter(Chapter chapter);
	// ɾ���½�
	int delChapter(int chapterid);
	// �޸��½�
	int editChapter(Chapter chapter);
	// ���½�id������ѯС˵
	List<Map<String, Object>> batchGetChapters(List<Integer> chapters);
}
