package cn.quitomos.blog.service.impl;

import cn.quitomos.blog.adapter.JournalsAdapter;
import cn.quitomos.blog.entity.Article;
import cn.quitomos.blog.entity.Journal;
import cn.quitomos.blog.mapper.JournalMapper;
import cn.quitomos.blog.service.JournalService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JournalServiceImpl implements JournalService {

    private JournalMapper journalMapper;

    @Override
    public PageInfo<Journal> listJournalPaged(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Journal> journalList = journalMapper.list();
        return new PageInfo<>(journalList);
    }

    @Override
    public void deleteJournal(Integer journalId) {
        journalMapper.deleteJournalById(journalId);
    }

    @Override
    public void saveJournal(Journal journal) {
        if (journal.getJournalCreateTime() == null)
            journal.setJournalCreateTime(new Date());
        journalMapper.insertJournal(journal);
    }

    @Override
    public JournalsAdapter getJournals(String uri, Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Journal> journalEntityList = journalMapper.list();
        PageInfo<Journal> pageInfo = new PageInfo<>(journalEntityList);
        return new JournalsAdapter(pageInfo, uri);
    }

    @Autowired
    public void setJournalMapper(JournalMapper journalMapper) {
        this.journalMapper = journalMapper;
    }
}
