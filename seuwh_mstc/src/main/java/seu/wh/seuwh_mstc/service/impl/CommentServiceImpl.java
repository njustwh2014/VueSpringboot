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
import seu.wh.seuwh_mstc.dao.ArticleInfoDao;
import seu.wh.seuwh_mstc.dao.ArticleViewInfoDao;
import seu.wh.seuwh_mstc.dao.CommentDao;
import seu.wh.seuwh_mstc.dao.UserDao;
import seu.wh.seuwh_mstc.model.ArticleViewInfo;
import seu.wh.seuwh_mstc.model.comment.Comment;
import seu.wh.seuwh_mstc.model.comment.CommentChildren;
import seu.wh.seuwh_mstc.model.comment.CommentRecive;
import seu.wh.seuwh_mstc.model.comment.CommentSend;
import seu.wh.seuwh_mstc.result.ResultInfo;
import seu.wh.seuwh_mstc.service.CommentService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ArticleInfoDao articleInfoDao;
    @Autowired
    ArticleViewInfoDao articleViewInfoDao;

    @Override
    public ResultInfo publish(CommentRecive commentRecive) {
        Comment comment=new Comment();
        CommentSend commentSend=new CommentSend();
        CommentChildren commentChildren=new CommentChildren();
        comment.setArticleid(commentRecive.getArticleid());
        comment.setAuthorid(commentRecive.getAuthorid());
        comment.setContent(commentRecive.getContent());
        comment.setLikecount(0);
        comment.setCreatedate(new Date());
        comment.setAtlevel(0);//对文章进行评论
        ArticleViewInfo articleViewInfo=new ArticleViewInfo();
        if(commentRecive.getParentid()==null){
            //对文章评论
            commentDao.AddComment(comment);
            articleViewInfo=articleViewInfoDao.SelectByArticleID(comment.getArticleid());
            articleViewInfo.setCommentcount(articleViewInfo.getCommentcount()+1);
            articleViewInfoDao.updateArticleCommentCount(articleViewInfo);
            commentSend.setAuthor(userDao.selectById(comment.getAuthorid()));
            commentSend.setChildrens(null);
            commentSend.setAtlevel(0);
            commentSend.setArticleInfo(articleInfoDao.selectByid(comment.getArticleid()));
            commentSend.setContent(comment.getContent());
            commentSend.setCreatedate(comment.getCreatedate());
            commentSend.setLikecount(comment.getLikecount());
            commentSend.setId(comment.getId());
            return ResultInfo.ok(commentSend);
        }else{
            comment.setParentid(commentRecive.getParentid());
            if(commentRecive.getToid()==null){
                //对评论进行评论
                comment.setAtlevel(1);
                commentChildren.setAtlevel(comment.getAtlevel());
                commentDao.AddComment(comment);
            }
            else{
                comment.setAtlevel(2);
                comment.setToid(commentRecive.getToid());
                commentDao.AddComment(comment);
                commentChildren.setAtlevel(comment.getAtlevel());
                commentChildren.setToComment(commentDao.SelectById(comment.getToid()));
                commentChildren.setToUser(userDao.selectById(commentChildren.getToComment().getAuthorid()));
            }
            commentChildren.setArticleid(comment.getId());
            commentChildren.setAtlevel(comment.getAtlevel());
            commentChildren.setAuthor(userDao.selectById(comment.getAuthorid()));
            commentChildren.setContent(comment.getContent());
            commentChildren.setCreatedate(comment.getCreatedate());
            commentChildren.setId(comment.getId());
            commentChildren.setLikecount(comment.getLikecount());
            commentChildren.setParentcomment(commentDao.SelectById(comment.getParentid()));
            return ResultInfo.ok(commentChildren);

        }



    }


    @Override
    public ResultInfo getCommentByArticleId(Integer id) {
        List<Comment> commentList=commentDao.SelectByArticleId(id);
        List<CommentSend> commentSendList=new ArrayList<CommentSend>();
        CommentSend commentSend=null;
        List<Comment> commentstemp=null;
        CommentChildren commentChildren=null;
        List<CommentChildren> commentChildrenList=null;
        for(Comment item:commentList){
            commentSend=new CommentSend();
            commentChildrenList=new ArrayList<>();
            commentSend.setId(item.getId());
            commentSend.setLikecount(item.getLikecount());
            commentSend.setCreatedate(item.getCreatedate());
            commentSend.setContent(item.getContent());
            commentSend.setAtlevel(item.getAtlevel());
            commentSend.setArticleInfo(articleInfoDao.selectByid(item.getArticleid()));
            commentSend.setAuthor(userDao.selectById(item.getAuthorid()));
            commentstemp=commentDao.SelectChildren(item.getId());
            for(Comment temp:commentstemp){
                commentChildren=new CommentChildren();
                commentChildren.setArticleid(temp.getId());
                commentChildren.setAtlevel(temp.getAtlevel());
                commentChildren.setAuthor(userDao.selectById(temp.getAuthorid()));
                commentChildren.setContent(temp.getContent());
                commentChildren.setCreatedate(temp.getCreatedate());
                commentChildren.setId(temp.getId());
                commentChildren.setLikecount(temp.getLikecount());
                if(temp.getToid()!=null){
                    commentChildren.setToComment(commentDao.SelectById(temp.getToid()));
                    commentChildren.setToUser(userDao.selectById(commentChildren.getToComment().getAuthorid()));
                }
                commentChildren.setParentcomment(item);
                commentChildrenList.add(commentChildren);
            }
            commentSend.setChildrens(commentChildrenList);
            commentSendList.add(commentSend);
        }


        return ResultInfo.ok(commentSendList);
    }
}
