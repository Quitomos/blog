package cn.quitomos.blog.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Journal {

    private Integer journalId;

    private String journalContent;

    private Date journalCreateTime;
}
