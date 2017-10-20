package com.jingdianxi.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingdianxi.domain.Chapter;
import com.jingdianxi.mapper.ChapterMapper;
import com.jingdianxi.service.ChapterService;

@Service
public class ChapterServiceImpl implements ChapterService {

	@Autowired
	ChapterMapper chapterMapper;

	@Override
	public List<Chapter> getChapterByNovel(int novelid) {
		// TODO Auto-generated method stub
		List<Chapter> chapters = chapterMapper.getChapterByNovel(novelid);
		return chapters;
	}

	@Override
	public Chapter getChapterById(int chapterid) {
		// TODO Auto-generated method stub
		Chapter chapter = chapterMapper.getChapterById(chapterid);
		return chapter;
	}

	@Override
	public int getPrevChapter(int chapterid) {
		// TODO Auto-generated method stub
		return chapterMapper.getPrevChapter(chapterid);
	}

	@Override
	public int getNextChapter(int chapterid) {
		// TODO Auto-generated method stub
		return chapterMapper.getNextChapter(chapterid);
	}

	@Override
	public int addChapter(Chapter chapter) {
		// TODO Auto-generated method stub
		return chapterMapper.addChapter(chapter);
	}

	@Override
	public int delChapter(int chapterid) {
		// TODO Auto-generated method stub
		return chapterMapper.delChapter(chapterid);
	}

	@Override
	public int editChapter(Chapter chapter) {
		// TODO Auto-generated method stub
		return chapterMapper.editChapter(chapter);
	}
	

	@Override
	public List<Map<String, Object>> batchGetChapters(List<Integer> chapters) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> maps = chapterMapper.batchGetChapters(chapters);
		return maps;
	}
}
