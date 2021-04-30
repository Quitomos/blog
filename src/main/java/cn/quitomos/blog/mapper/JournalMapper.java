package cn.quitomos.blog.mapper;

import cn.quitomos.blog.entity.Journal;

import java.util.List;

public interface JournalMapper {

    /**
     * 查询日志列表
     *
     * @return 日志列表
     */
    List<Journal> list();

    /**
     * 创建新日志
     *
     * @param journal journal
     */
    void insertJournal(Journal journal);

    /**
     * 删除日志
     *
     * @param id 日志id
     */
    void deleteJournalById(int id);
}
