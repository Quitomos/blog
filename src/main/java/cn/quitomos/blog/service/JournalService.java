package cn.quitomos.blog.service;

import cn.quitomos.blog.adapter.JournalsAdapter;
import cn.quitomos.blog.entity.Journal;
import com.github.pagehelper.PageInfo;

public interface JournalService {

    /**
     * 日志列表
     *
     * @param pageNum 页码
     * @param pageSize 每页行数
     * @return 日志列表
     */
    PageInfo<Journal> listJournalPaged(Integer pageNum, Integer pageSize);

    /**
     * 删除日志
     *
     * @param journalId 日志id
     */
    void deleteJournal(Integer journalId);

    /**
     * 保存新日志
     *
     * @param journal 日志
     */
    void saveJournal(Journal journal);

    /**
     * 获取前台日志列表
     *
     * @param uri 请求uri
     * @param pageNum 页数
     * @return
     */
    JournalsAdapter getJournals(String uri, Integer pageNum);
}
