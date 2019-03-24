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
import seu.wh.seuwh_mstc.dao.ArticleLinkTableDao;
import seu.wh.seuwh_mstc.dao.CategoryDao;
import seu.wh.seuwh_mstc.model.Category;
import seu.wh.seuwh_mstc.result.ResultInfo;
import seu.wh.seuwh_mstc.service.CategoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired
    CategoryDao categoryDao;
    @Autowired
    ArticleLinkTableDao articleLinkTableDao;

    @Override
    public ResultInfo getAllCategory(Integer pageNumber, Integer pageSize) {
        try{
            Map<String,Object> ret=new HashMap<>();
            ret.put("data",categoryDao.getAllCategory((pageNumber-1)*pageSize,pageSize));
            ret.put("total",categoryDao.countCategory());
            return ResultInfo.ok(ret);
        }catch (Exception e){
            logger.error("获取所有分类时出现异常",e.getMessage());
            e.printStackTrace();
            return ResultInfo.build(500,"获取所有分类时服务器出现异常");
        }
    }

    @Override
    public ResultInfo changeStatus(Integer id, String status) {
        try{
            categoryDao.changeStatus(id,status);
            return ResultInfo.ok();
        }catch (Exception e){
            logger.error("更改分类状态时出现异常",e.getMessage());
            e.printStackTrace();
            return ResultInfo.build(500,"更改分类状态时服务器出现异常！");
        }
    }

    @Override
    public ResultInfo deleteCategory(Integer id) {
        try{
            articleLinkTableDao.deleteArticleByCategoryid(id);//删除对应的文章
            categoryDao.deleteCategory(id);
            return ResultInfo.ok();
        }catch (Exception e){
            logger.error("删除分类时出现异常",e.getMessage());
            e.printStackTrace();
            return ResultInfo.build(500,"删除分类时服务器出现异常！");
        }
    }


    @Override
    public ResultInfo addCategory(String newcategory) {
        Category category=new Category();
        category.setCategorystatus("show");
        category.setCategorydescription(newcategory);
        try{
            if(categoryDao.selectIdByDescription(newcategory)!=null){
                return ResultInfo.build(500,"重复添加");
            }
            categoryDao.addCategory(category);
            return ResultInfo.ok();
        }catch (Exception e){
            logger.error("添加category时出现异常",e.getMessage());
            e.printStackTrace();
            return  ResultInfo.build(500,"添加category时服务器出现异常");
        }
    }

    @Override
    public ResultInfo getAllArtilcesByCategory(Integer id) {
        return null;
    }
}
