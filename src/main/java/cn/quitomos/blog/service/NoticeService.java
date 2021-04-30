package cn.quitomos.blog.service;

import cn.quitomos.blog.entity.Notice;

import java.util.List;

public interface NoticeService {

    /**
     * 新公告或修改公告
     *
     * @param notice notice
     */
    void saveNotice(Notice notice);

    /**
     * 公告列表
     *
     * @return noticeList
     */
    List<Notice> listNotice();

    /**
     * 删除公告
     *
     * @param noticeId 公告id
     */
    void deleteNotice(Integer noticeId);

    /**
     * 根据公告id获取公告
     *
     * @param noticeId 公告id
     * @return notice
     */
    Notice getNoticeById(Integer noticeId);
}
