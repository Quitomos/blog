package cn.quitomos.blog.service.impl;

import cn.quitomos.blog.entity.Notice;
import cn.quitomos.blog.mapper.NoticeMapper;
import cn.quitomos.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    private NoticeMapper noticeMapper;

    @Override
    public void saveNotice(Notice notice) {
        notice.setNoticeUpdateTime(new Date());
        if (notice.getNoticeId() != null) {
            noticeMapper.updateNotice(notice);
        } else {
            notice.setNoticeCreateTime(new Date());
            noticeMapper.insertNotice(notice);
        }
    }

    @Override
    public List<Notice> listNotice() {
        return noticeMapper.list();
    }

    @Override
    public void deleteNotice(Integer noticeId) {
        noticeMapper.deleteNoticeById(noticeId);
    }

    @Override
    public Notice getNoticeById(Integer noticeId) {
        return noticeMapper.getNoticeById(noticeId);
    }

    @Autowired
    public void setNoticeMapper(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }
}
