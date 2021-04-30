package cn.quitomos.blog.adapter.base;

import cn.quitomos.blog.adapter.interf.PageInfo;

import java.util.List;

public abstract class PageInfoAdapter<T, V> implements PageInfo<T, V> {

    private final com.github.pagehelper.PageInfo<V> pageInfo;
    private final String uri;

    public PageInfoAdapter(com.github.pagehelper.PageInfo<V> pageInfo, String uri) {
        this.pageInfo = pageInfo;
        int last = uri.lastIndexOf("/p/");
        if (last != -1) uri = uri.substring(0, last);
        this.uri = uri;
    }

    protected abstract List<T> typeChange(List<V> v);

    @Override
    public List<T> getContent() {
        return typeChange(pageInfo.getList());
    }

    @Override
    public int getNumber() {
        return pageInfo.getPageNum();
    }

    @Override
    public int getTotalPages() {
        return pageInfo.getPages();
    }

    @Override
    public boolean isHasPrev() {
        return pageInfo.isHasPreviousPage();
    }

    @Override
    public boolean isHasNext() {
        return pageInfo.isHasNextPage();
    }

    @Override
    public String getPrevPageFullPath() {
        int prePage = pageInfo.getPrePage();
        if (prePage <= 1)
            return uri;
        return uri + "/p/" + pageInfo.getPrePage();
    }

    @Override
    public String getNextPageFullPath() {
        return uri + "/p/" + pageInfo.getNextPage();
    }

    @Override
    public int getPages() {
        return pageInfo.getPages();
    }

    @Override
    public int getTotal() {
        return (int) pageInfo.getTotal();
    }

    @Override
    public int getPage() {
        return pageInfo.getSize();
    }

    @Override
    public int getRpp() {
        return pageInfo.getPageSize();
    }

    @Override
    public boolean isIsFirst() {
        return pageInfo.isIsFirstPage();
    }

    @Override
    public boolean isIsLast() {
        return pageInfo.isIsLastPage();
    }

    @Override
    public boolean isIsEmpty() {
        return pageInfo.getList().isEmpty();
    }

    @Override
    public boolean isHasContent() {
        return !pageInfo.getList().isEmpty();
    }

    @Override
    public int getLength() {
        return pageInfo.getSize();
    }

    @Override
    public boolean isHasPrevious() {
        return pageInfo.isHasPreviousPage();
    }
}
