package com.jingdianxi.mapper;

import java.util.List;
import java.util.Map;

import com.jingdianxi.domain.Chapter;

public interface ChapterMapper {
	// �����½�
	int addChapter(Chapter chapter);
	// ɾ���½�
	int delChapter(int chapterid);
	// �޸��½�
	int editChapter(Chapter chapter);
	// ��С˵��ѯ�½�
	List<Chapter> getChapterByNovel(int novelid);
	// ��id��ѯ�½�
	Chapter getChapterById(int chapterid);
	// ��ѯǰһ�½�id
	int getPrevChapter(int chapterid);
	// ��ѯ��һ�½�id
	int getNextChapter(int chapterid);
	// ���½�id������ѯС˵
	List<Map<String, Object>> batchGetChapters(List<Integer> chapters);
}
