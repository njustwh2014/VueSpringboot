/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package seu.wh.seuwh_mstc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seu.wh.seuwh_mstc.dao.ArticleTagDao;
import seu.wh.seuwh_mstc.dao.TagDao;
import seu.wh.seuwh_mstc.model.Tag;
import seu.wh.seuwh_mstc.result.ResultInfo;
import seu.wh.seuwh_mstc.service.TagService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TagServiceImpl implements TagService {
    private static final Logger logger = LoggerFactory.getLogger(TagServiceImpl.class);
    @Autowired
    TagDao tagDao;
    @Autowired
    ArticleTagDao articleTagDao;
    @Override
    public ResultInfo getAllTag(Integer pageNumber, Integer pageSize) {
        try{
            Map<String,Object> ret=new HashMap<>();
            ret.put("data",tagDao.getAllTag((pageNumber-1)*pageSize,pageSize));
            ret.put("total",tagDao.countTag());
            return ResultInfo.ok(ret);
        }catch (Exception e){
            logger.error("获取所有标签时出现异常",e.getMessage());
            e.printStackTrace();
            return ResultInfo.build(500,"获取所有标签时服务器出现异常");
        }
    }

    @Override
    public ResultInfo changeStatus(Integer id, String status) {
        return null;
    }

    @Override
    public ResultInfo getAllArtilcesByTag(Integer id) {
        return null;
    }

    @Override
    public ResultInfo deleteTag(Integer id) {
        try{

            tagDao.deleteTag(id);
            articleTagDao.deleteArticleTagByTagId(id);//删除文章对应的标签
            return ResultInfo.ok();
        }catch (Exception e){
            logger.error("删除标签时出现异常",e.getMessage());
            e.printStackTrace();
            return ResultInfo.build(500,"删除标签时服务器出现异常！");
        }
    }

    @Override
    public ResultInfo addTag(String newtag,Integer categoryid) {
        Tag tag=new Tag();
        tag.setTagdescription(newtag);
        tag.setCategoryid(categoryid);
        tag.setTagstatus("show");
        try{
            if(tagDao.selectIdByDescriptionAndCategoryid(newtag,categoryid)!=null){
                return ResultInfo.build(500,"重复添加");
            }
            tagDao.addCategory(tag);
            return ResultInfo.ok();
        }catch (Exception e){
            logger.error("添加category时出现异常",e.getMessage());
            e.printStackTrace();
            return  ResultInfo.build(500,"添加category时服务器出现异常");
        }
    }
}
