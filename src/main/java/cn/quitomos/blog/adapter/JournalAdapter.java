package cn.quitomos.blog.adapter;

import cn.quitomos.blog.adapter.interf.Journal;
import cn.quitomos.blog.util.MarkdownUtil;

import java.util.Date;

public class JournalAdapter implements Journal {

    private final cn.quitomos.blog.entity.Journal journal;

    public JournalAdapter(cn.quitomos.blog.entity.Journal journal) {
        this.journal = journal;
    }

    @Override
    public int getId() {
        return journal.getJournalId();
    }

    @Override
    public String getContent() {
        return MarkdownUtil.renderHtml(journal.getJournalContent());
    }

    @Override
    public Date getCreateTime() {
        return journal.getJournalCreateTime();
    }
}
