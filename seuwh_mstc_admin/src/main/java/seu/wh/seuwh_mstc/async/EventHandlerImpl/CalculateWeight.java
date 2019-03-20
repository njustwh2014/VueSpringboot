/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package seu.wh.seuwh_mstc.async.EventHandlerImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import seu.wh.seuwh_mstc.async.EventHandler;
import seu.wh.seuwh_mstc.async.EventModel;
import seu.wh.seuwh_mstc.async.EventType;
import seu.wh.seuwh_mstc.dao.ArticleInfoDao;
import seu.wh.seuwh_mstc.dao.ArticleViewInfoDao;
import seu.wh.seuwh_mstc.dao.ArticleWeightDao;
import seu.wh.seuwh_mstc.jedis.JedisClient;
import seu.wh.seuwh_mstc.model.ArticleInfo;
import seu.wh.seuwh_mstc.model.ArticleViewInfo;
import seu.wh.seuwh_mstc.model.ArticleWeight;
import seu.wh.seuwh_mstc.utils.RedisKeyUtils;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;



//计算文章热度
@Component
public class CalculateWeight implements EventHandler {
    private static final Logger logger = LoggerFactory.getLogger(CalculateWeight.class);
    @Autowired
    JedisClient jedisClient;
    @Autowired
    ArticleWeightDao articleWeightDao;
    @Autowired
    ArticleViewInfoDao articleViewInfoDao;
    @Override
    public void doHandler(EventModel eventModel) {
        try{
            String viewkey= RedisKeyUtils.getViewKey(eventModel.getEntityid());
            String commentkey=RedisKeyUtils.getCommentKey(eventModel.getEntityid());
            long viewcount=jedisClient.scard(viewkey);
            long commentcount=jedisClient.scard(commentkey);
            long weight=viewcount*2+commentcount*8;
            ArticleViewInfo articleViewInfo=articleViewInfoDao.SelectByArticleID(eventModel.getEntityid());
            articleViewInfo.setViewcount(viewcount);
            articleViewInfo.setCommentcount(commentcount);
            articleViewInfoDao.updateArticleViewInfo(articleViewInfo);
            ArticleWeight articleWeight=articleWeightDao.selectByArticleid(eventModel.getEntityid());
            articleWeight.setWeight(weight);
            articleWeightDao.updateArticleWeight(articleWeight);
        }catch (Exception e){
           logger.error("异步计算文章热度出现错误！:");
           logger.error(String.valueOf(e));
        }
    }

    @Override
    public List<EventType> getSupportEventType() {
        return Arrays.asList(EventType.WEIGHT);
    }
}
