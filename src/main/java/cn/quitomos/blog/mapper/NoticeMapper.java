package cn.quitomos.blog.mapper;

import cn.quitomos.blog.entity.Notice;
import cn.quitomos.blog.entity.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NoticeMapper {

    /**
     * 查询公告列表
     *
     * @return 公告列表
     */
    List<Notice> list();

    /**
     * 通过noticeId获取公告
     *
     * @param id noticeId
     * @return Notice
     */
    Notice getNoticeById(int id);

    /**
     * 创建新公告
     *
     * @param notice notice
     */
    void insertNotice(Notice notice);

    /**
     * 删除公告
     *
     * @param id noticeId
     */
    void deleteNoticeById(@Param("notice_id") int id);

    /**
     * 根据公告id更新公告
     *
     * @param notice 公告
     */
    void updateNotice(Notice notice);
}
