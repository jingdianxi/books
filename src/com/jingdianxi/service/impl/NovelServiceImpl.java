package com.jingdianxi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingdianxi.domain.Novel;
import com.jingdianxi.domain.PagedParam;
import com.jingdianxi.mapper.NovelMapper;
import com.jingdianxi.service.NovelService;

@Service
public class NovelServiceImpl implements NovelService {

	@Autowired
	NovelMapper novelMapper;
	
	@Override
	public List<Novel> getAllNovel() {
		// TODO Auto-generated method stub
		List<Novel> novels = novelMapper.getAllNovel();
		return novels;
	}

	@Override
	public List<Novel> getNovelPaged(PagedParam pagedParam) {
		// TODO Auto-generated method stub
		List<Novel> novels = novelMapper.getNovelPaged(pagedParam);
		return novels;
	}

	@Override
	public int getCount(PagedParam pagedParam) {
		// TODO Auto-generated method stub
		return novelMapper.getCount(pagedParam);
	}

	@Override
	public Novel getNovelById(int novelid) {
		// TODO Auto-generated method stub
		Novel novel = novelMapper.getNovelById(novelid);
		return novel;
	}

	@Override
	public int updateHits(int chapterid) {
		// TODO Auto-generated method stub
		return novelMapper.updateHits(chapterid);
	}

	@Override
	public int addNovel(Novel novel) {
		// TODO Auto-generated method stub
		return novelMapper.addNovel(novel);
	}

	@Override
	public int delNovel(int novelid) {
		// TODO Auto-generated method stub
		return novelMapper.delNovel(novelid);
	}

	@Override
	public int editNovel(Novel novel) {
		// TODO Auto-generated method stub
		return novelMapper.editNovel(novel);
	}

	@Override
	public List<Novel> getNovelByCategory(int categoryid) {
		// TODO Auto-generated method stub
		List<Novel> novels = novelMapper.getNovelByCategory(categoryid);
		return novels;
	}

	@Override
	public List<Novel> getNovelByAuthor(String author) {
		// TODO Auto-generated method stub
		List<Novel> novels = novelMapper.getNovelByAuthor(author);
		return novels;
	}
}
