/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package seu.wh.seuwh_mstc.model;

public class Tag {
    private Integer id;
    private String tagdescription;
    private String tagstatus;
    private Integer categoryid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagdescription() {
        return tagdescription;
    }

    public void setTagdescription(String tagdescription) {
        this.tagdescription = tagdescription;
    }

    public String getTagstatus() {
        return tagstatus;
    }

    public void setTagstatus(String tagstatus) {
        this.tagstatus = tagstatus;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }
}
