/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package seu.wh.seuwh_mstc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seu.wh.seuwh_mstc.dao.*;
import seu.wh.seuwh_mstc.model.*;
import seu.wh.seuwh_mstc.result.ResultInfo;
import seu.wh.seuwh_mstc.service.ArticleService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.lang.String.valueOf;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    UserDao userDao;
    @Autowired
    HostHolder hostHolder;
    @Autowired
    ArticleInfoDao articleInfoDao;
    @Autowired
    ArticleBodyDao articleBodyDao;
    @Autowired
    ArticleTagDao articleTagDao;
    @Autowired
    ArticleViewInfoDao articleViewInfoDao;
    @Autowired
    CommentDao commentDao;
    @Autowired
    ArticleLinkTableDao articleLinkTableDao;
    @Autowired
    ArticleWeightDao articleWeightDao;

    @Override
    public ResultInfo publish(ArticleRecive articleRecive) {
        ArticleBody articleBody=new ArticleBody();
        ArticleInfo articleInfo=new ArticleInfo();
        ArticleViewInfo articleViewInfo=new ArticleViewInfo();
        ArticleWeight articleWeight=new ArticleWeight();
        User author=hostHolder.getUser();


        author=userDao.selectById(author.getId());
        articleInfo.setAuthor(author.getId());
        articleInfo.setCategory(articleRecive.getCategory().getId());
        articleInfo.setCategorydescription(articleRecive.getCategory().getCategorydescription());
        articleInfo.setPublishtime(new Date());
        articleInfo.setTitle(articleRecive.getTitle());
        articleInfo.setSummary(articleRecive.getSummary());
        articleInfoDao.addArticle(articleInfo);


        articleBody=articleRecive.getBody();
        articleBody.setArticleid(articleInfo.getId());
        articleBodyDao.addArticleBody(articleBody);



        List<ArticleTag> articleTagList=new ArrayList<ArticleTag>();
        ArticleTag articleTag=null;
        List<TagId> tagIdList=articleRecive.getTags();
        for(TagId item:tagIdList){
            articleTag=new ArticleTag();//值引用
            articleTag.setArticleid(articleInfo.getId());
            articleTag.setTagid(item.getId());
            articleTag.setTagdescription(item.getTagdescription());
            articleTagList.add(articleTag);

        }
        articleTagDao.addArticleTagBatch(articleTagList);


        articleViewInfo.setArticleid(articleInfo.getId());
        articleViewInfoDao.addArticleViewInfo(articleViewInfo);
        articleWeight.setArticleid(articleInfo.getId());
        articleWeightDao.addArticleWeight(articleWeight);


        return ResultInfo.ok(articleInfo);
    }


    @Override
    public ResultInfo view(Integer id) {

        //获取ArticleInfo
        ArticleInfo articleInfo=articleInfoDao.selectByid(id);
        Category category=new Category();
        category.setId(articleInfo.getCategory());
        category.setCategorydescription(articleInfo.getCategorydescription());

        //获取body
        ArticleBody articleBody=articleBodyDao.selectByArticleId(id);

        //获取Tags
        List<ArticleTag> articleTagList=articleTagDao.SelectByArticleId(id);


        //获取viewInfo
        ArticleViewInfo articleViewInfo=articleViewInfoDao.SelectByArticleID(id);
        articleViewInfo.setViewcount(articleViewInfo.getViewcount()+1);
        articleViewInfoDao.updateArticleViewCount(articleViewInfo);

        //获取weight
        ArticleWeight articleWeight=articleWeightDao.selectByArticleid(id);
        articleWeight.setWeight(articleWeight.getWeight()+2);
        articleWeightDao.updateArticleWeight(articleWeight);

        //组装成返回对象
        ArticleSend articleSend=new ArticleSend();
        articleSend.setAuthor(userDao.selectById(articleInfo.getAuthor()));
        articleSend.setBody(articleBody);
        articleSend.setCategory(category);
        articleSend.setCommentcount(commentDao.CountComment(articleInfo.getId()));
        articleSend.setId(articleInfo.getId());
        articleSend.setPublishtime(articleInfo.getPublishtime());
        articleSend.setSummary(articleInfo.getSummary());
        articleSend.setTags(articleTagList);
        articleSend.setViewcount(articleViewInfo.getViewcount());

        articleSend.setTitle(articleInfo.getTitle());


        return ResultInfo.ok(articleSend);
    }

    // 首页获取文章简要信息
    @Override
    public ResultInfo getAllArticle(Integer pageNumber, Integer pageSize) {
        List<Map<String,Object>> articleslist=articleLinkTableDao.GetAllArticle((pageNumber-1)*pageSize,pageSize);
        for(Map<String,Object> item:articleslist){
            item.put("tags",articleTagDao.SelectByArticleId(Integer.parseInt(item.get("id").toString())));
        }
        return ResultInfo.ok(articleslist);
    }

    //获取最热文章
    //现阶段说对weight=（访问量*2+评论量*10）进行排序操作


    @Override
    public ResultInfo getHotArticles() {
        List<Map<String,Object>> hotArticles=articleWeightDao.GetHotArticles();
        return ResultInfo.ok(hotArticles);
    }
}
