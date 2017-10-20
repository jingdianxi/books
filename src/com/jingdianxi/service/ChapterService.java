package com.jingdianxi.service;

import java.util.List;
import java.util.Map;

import com.jingdianxi.domain.Chapter;

public interface ChapterService {
	// 按小说查询章节
	List<Chapter> getChapterByNovel(int novelid);
	// 按id查询章节
	Chapter getChapterById(int chapterid);
	// 查询前一章节id
	int getPrevChapter(int chapterid);
	// 查询后一章节id
	int getNextChapter(int chapterid);
	// 增加章节
	int addChapter(Chapter chapter);
	// 删除章节
	int delChapter(int chapterid);
	// 修改章节
	int editChapter(Chapter chapter);
	// 按章节id批量查询小说
	List<Map<String, Object>> batchGetChapters(List<Integer> chapters);
}
