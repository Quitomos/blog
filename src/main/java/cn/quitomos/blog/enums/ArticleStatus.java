package cn.quitomos.blog.enums;

import lombok.Getter;

@Getter
public enum ArticleStatus {
    DRAFT(0),
    PUBLISHED(1);

    private int code;

    ArticleStatus(int code) {
        this.code = code;
    }

    /**
     * 通过code获得ArticleStatus
     *
     * @param code code
     * @return ArticleStatus
     */
    public static ArticleStatus getArticleStatus(int code) {
       ArticleStatus[] articleStatuses = ArticleStatus.values();
       for (ArticleStatus articleStatus: articleStatuses) {
           if (articleStatus.getCode() == code)
               return articleStatus;
       }
       return null;
    }
}
