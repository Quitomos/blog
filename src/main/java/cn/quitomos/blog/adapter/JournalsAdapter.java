package cn.quitomos.blog.adapter;

import cn.quitomos.blog.adapter.base.PageInfoAdapter;
import cn.quitomos.blog.adapter.interf.Journal;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

public class JournalsAdapter extends PageInfoAdapter<Journal, cn.quitomos.blog.entity.Journal> {

    public JournalsAdapter(PageInfo<cn.quitomos.blog.entity.Journal> pageInfo, String uri) {
        super(pageInfo, uri);
    }

    @Override
    protected List<Journal> typeChange(List<cn.quitomos.blog.entity.Journal> v) {
        List<Journal> ret = new ArrayList<>();
        for (cn.quitomos.blog.entity.Journal j: v) {
            ret.add(new JournalAdapter(j));
        }
        return ret;
    }
}
