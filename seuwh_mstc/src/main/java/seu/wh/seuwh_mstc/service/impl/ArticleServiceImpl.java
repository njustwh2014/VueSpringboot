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
import seu.wh.seuwh_mstc.async.EventModel;
import seu.wh.seuwh_mstc.async.EventProducer;
import seu.wh.seuwh_mstc.async.EventType;
import seu.wh.seuwh_mstc.dao.*;
import seu.wh.seuwh_mstc.jedis.JedisClient;
import seu.wh.seuwh_mstc.model.*;
import seu.wh.seuwh_mstc.result.ResultInfo;
import seu.wh.seuwh_mstc.service.ArticleService;
import seu.wh.seuwh_mstc.utils.RedisKeyUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

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
    @Autowired
    JedisClient jedisClient;
    @Autowired
    EventProducer eventProducer;

    @Override
    public ResultInfo publish(ArticleRecive articleRecive) {
        if(articleRecive.getId()!=null){
            //update
            ArticleInfo articleInfo=articleInfoDao.selectByid(articleRecive.getId());
            if(articleInfo!=null){
                articleInfo.setCategory(articleRecive.getCategory());
                articleInfo.setPublishtime(new Date());
                articleInfo.setTitle(articleRecive.getTitle());
                articleInfo.setSummary(articleRecive.getSummary());
                articleInfo.setCover(articleRecive.getCover());
                articleInfo.setArticlestatus("show");
                articleInfoDao.updateArticle(articleInfo);



                ArticleBody articleBody=articleBodyDao.selectByArticleId(articleRecive.getId());
                articleBody.setContent(articleRecive.getBody().getContent());
                articleBody.setContenthtml(articleRecive.getBody().getContenthtml());
                articleBodyDao.updateArticleBodyByArticleId(articleBody);


//                先删除tag
                articleTagDao.deleteArticleTagByArticleId(articleRecive.getId());
                List<ArticleTag> articleTagList=new ArrayList<ArticleTag>();
                ArticleTag articleTag=null;
                List<TagId> tagIdList=articleRecive.getTags();
                for(TagId item:tagIdList){
                    articleTag=new ArticleTag();//值引用
                    articleTag.setArticleid(articleInfo.getId());
                    articleTag.setTagid(item.getId());
                    articleTagList.add(articleTag);
                }
                articleTagDao.addArticleTagBatch(articleTagList);
                return ResultInfo.ok(articleInfo);
            }
            return ResultInfo.build(500,"更新错误！",null);

        }else{
            ArticleBody articleBody=new ArticleBody();
            ArticleInfo articleInfo=new ArticleInfo();
            ArticleViewInfo articleViewInfo=new ArticleViewInfo();
            ArticleWeight articleWeight=new ArticleWeight();
            User author=hostHolder.getUser();


            author=userDao.selectById(author.getId());
            articleInfo.setAuthor(author.getId());
            articleInfo.setCategory(articleRecive.getCategory());
            articleInfo.setPublishtime(new Date());
            articleInfo.setTitle(articleRecive.getTitle());
            articleInfo.setSummary(articleRecive.getSummary());
            articleInfo.setCover(articleRecive.getCover());
            articleInfo.setArticlestatus("show");
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
                articleTagList.add(articleTag);

            }
            articleTagDao.addArticleTagBatch(articleTagList);


            articleViewInfo.setArticleid(articleInfo.getId());
            articleViewInfoDao.addArticleViewInfo(articleViewInfo);
            articleWeight.setArticleid(articleInfo.getId());
            articleWeightDao.addArticleWeight(articleWeight);
            return ResultInfo.ok(articleInfo);
        }




    }


    @Override
    public ResultInfo view(Integer id) {

//        //获取ArticleInfo
//        ArticleInfo articleInfo=articleInfoDao.selectByid(id);
//        Category category=new Category();
//        category.setId(articleInfo.getCategory());
//        category.setCategorydescription(articleInfo.getCategorydescription());
//
//        //获取body
//        ArticleBody articleBody=articleBodyDao.selectByArticleId(id);
//

//
//
//

//
//
//        //组装成返回对象
//        ArticleSend articleSend=new ArticleSend();
//        articleSend.setAuthor(userDao.selectById(articleInfo.getAuthor()));
//        articleSend.setBody(articleBody);
//        articleSend.setCategory(category);
//        articleSend.setCommentcount(commentDao.CountComment(articleInfo.getId()));
//        articleSend.setId(articleInfo.getId());
//        articleSend.setPublishtime(articleInfo.getPublishtime());
//        articleSend.setSummary(articleInfo.getSummary());
//        articleSend.setTags(articleTagList);
//        articleSend.setViewcount(jedisClient.scard(viewKey));
//        articleSend.setTitle(articleInfo.getTitle());
        Map<String,Object> articlebylink=articleLinkTableDao.getArticleByid(id);


        //将viewcount写入redis
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate=new Date();
        String dateYmd=simpleDateFormat.format(nowDate).substring(0,13);//限定一个用户一小时内只有一次访问数据有效
        String viewKey= RedisKeyUtils.getViewKey(id);
        if(hostHolder.getUser()!=null){
            jedisClient.sadd(viewKey,String.valueOf(hostHolder.getUser().getId())+dateYmd);
        }
        //计算文章热度
        EventModel weightEventModel=new EventModel();
        weightEventModel.setAuthorid(hostHolder.getUser().getId());
        weightEventModel.setEntityid(id);
        weightEventModel.setEntityownerid(Integer.parseInt(articlebylink.get("authorid").toString()));
        weightEventModel.setEventType(EventType.WEIGHT);
        eventProducer.fireEvent(weightEventModel);

        //获取Tags
//        List<ArticleTag> articleTagList=articleTagDao.SelectByArticleId(id);
        List<Map<String,Object>> articleTagList=articleLinkTableDao.getTagByArticleid(id);
        articlebylink.put("tags",articleTagList);

        return ResultInfo.ok(articlebylink);
    }

    // 首页获取文章简要信息
    @Override
    public ResultInfo getAllArticle(Integer pageNumber, Integer pageSize) {
        List<Map<String,Object>> articleslist=articleLinkTableDao.GetAllArticle((pageNumber-1)*pageSize,pageSize);
        for(Map<String,Object> item:articleslist){
            item.put("tags",articleLinkTableDao.getTagByArticleid(Integer.parseInt(item.get("id").toString())));
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


    //根据category 获取文章


    @Override
    public ResultInfo getArticlesByCategory(Integer pageNumber, Integer pageSize, Integer id) {
        List<Map<String,Object>> articleslist=articleLinkTableDao.GetArticlesByCategory((pageNumber-1)*pageSize,pageSize,id);
        for(Map<String,Object> item:articleslist){
            item.put("tags",articleLinkTableDao.getTagByArticleid(Integer.parseInt(item.get("id").toString())));
        }
        return ResultInfo.ok(articleslist);
    }
    //根据Tag 获取文章

    @Override
    public ResultInfo getArticlesByTag(Integer pageNumber, Integer pageSize, Integer id) {
        List<Map<String,Object>> articleslist=articleLinkTableDao.GetArticlesByTag((pageNumber-1)*pageSize,pageSize,id);
        for(Map<String,Object> item:articleslist){
            item.put("tags",articleLinkTableDao.getTagByArticleid(Integer.parseInt(item.get("id").toString())));
        }
        return ResultInfo.ok(articleslist);
    }

    @Override
    public ResultInfo searchArticles(Integer pageNumber, Integer pageSize, String searchData) {
        List<Map<String,Object>> articleslist=articleLinkTableDao.getAllArticleByKeyWords((pageNumber-1)*pageSize,pageSize,searchData);
        for(Map<String,Object> item:articleslist){
            item.put("tags",articleLinkTableDao.getTagByArticleid(Integer.parseInt(item.get("id").toString())));
        }
        return ResultInfo.ok(articleslist);
    }
}
