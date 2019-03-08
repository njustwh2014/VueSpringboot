/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package seu.wh.seuwh_mstc.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import seu.wh.seuwh_mstc.dao.ArticleLinkTableDao;
import seu.wh.seuwh_mstc.dao.LinkTableQuery;
import seu.wh.seuwh_mstc.model.ArticleRecive;
import seu.wh.seuwh_mstc.model.Category;
import seu.wh.seuwh_mstc.result.ResultInfo;
import seu.wh.seuwh_mstc.service.ArticleService;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value="/articles")
public class ArrticleController {

    @Autowired
    ArticleService articleService;
    @Autowired
    LinkTableQuery linkTableQuery;
    @Autowired
    ArticleLinkTableDao articleLinkTableDao;

    //发表文章
    @RequestMapping(value="/publish",method= RequestMethod.POST,produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultInfo publish(@RequestBody ArticleRecive articleRecive){
        return articleService.publish(articleRecive);
    }

    //浏览文章
    @RequestMapping(value="/view/{id}", method=RequestMethod.GET)
    public ResultInfo view(@PathVariable Integer id)
    {
        return articleService.view(id);
    }

    //获取所有文章
//    @RequestMapping(value="/getall",method = RequestMethod.GET)
//    public ResultInfo getAllArticle(@RequestParam(value="pageNumber")Integer pageNumber,@RequestParam(value="pageSize")Integer pageSize,
//                                    @RequestParam(value="name")String name,@RequestParam(value="sort")String sort,
//                                    @RequestParam(value="year")String year, @RequestParam(value="month")String month,
//                                    @RequestParam(value="tagId")Integer tagId, @RequestParam(value="categoryId")Integer categoryId
//                                    ){
//        System.out.println(pageNumber);
//
//
//        return null;
//    }
//    @RequestMapping(value="/getall",method = RequestMethod.GET)
//    public ResultInfo getAllArticle(@RequestParam(value="pageNumber")Integer pageNumber,@RequestParam(value="pageSize")Integer pageSize){
//        System.out.println(pageNumber);
//        return null;
//    }
    @RequestMapping(value = "/getall",method=RequestMethod.POST)
    public ResultInfo getAllArticle(@RequestBody JSONObject jsonObject){
        Integer pageNumber=jsonObject.getInteger("pageNumber");
        Integer pageSize=jsonObject.getInteger("pageSize");

        return articleService.getAllArticle(pageNumber,pageSize);

    }

    @RequestMapping(value="/test", method=RequestMethod.GET)
    public ResultInfo testLinkTableQuery(){
        return ResultInfo.ok(null);
    }
}
