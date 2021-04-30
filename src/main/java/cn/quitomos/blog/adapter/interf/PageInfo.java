package cn.quitomos.blog.adapter.interf;

import java.util.List;

/**
 * pageHelper适配器
 *
 * @param <T> 前台接口
 * @param <V> pageInfo<V>
 */
public interface PageInfo<T, V> {

    List<T> getContent();

    int getNumber();

    int getTotalPages();

    boolean isHasPrev();

    boolean isHasNext();

    String getPrevPageFullPath();

    String getNextPageFullPath();

    int getPages();

    int getTotal();

    int getPage();

    int getRpp();

    boolean isIsFirst();

    boolean isIsLast();

    boolean isIsEmpty();

    boolean isHasContent();

    int getLength();

    boolean isHasPrevious();
}
