/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package seu.wh.seuwh_mstc.model;

import java.util.Date;
import java.util.List;

public class ArticleInfoSend {
    private Integer id;
    private User author;//作者id
    private Date publishtime;
    private String summary;
    private String title;
    private Integer commentcount;
    private Integer viewcount;
    private List<ArticleTag> tags;
    private Integer weight=0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(Integer commentcount) {
        this.commentcount = commentcount;
    }

    public Integer getViewcount() {
        return viewcount;
    }

    public void setViewcount(Integer viewcount) {
        this.viewcount = viewcount;
    }

    public List<ArticleTag> getTags() {
        return tags;
    }

    public void setTags(List<ArticleTag> tags) {
        this.tags = tags;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "ArticleInfoSend{" +
                "id=" + id +
                ", author=" + author +
                ", publishtime=" + publishtime +
                ", summary='" + summary + '\'' +
                ", title='" + title + '\'' +
                ", commentcount=" + commentcount +
                ", viewcount=" + viewcount +
                ", tags=" + tags +
                ", weight=" + weight +
                '}';
    }
}
