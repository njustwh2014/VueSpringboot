/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package seu.wh.seuwh_mstc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import seu.wh.seuwh_mstc.model.comment.CommentRecive;
import seu.wh.seuwh_mstc.result.ResultInfo;
import seu.wh.seuwh_mstc.service.CommentService;

@RestController
@CrossOrigin
@RequestMapping(value="/comments")
public class CommentController {


    @Autowired
    CommentService commentService;
    //发表评论
    @RequestMapping(value = "/publish",method = RequestMethod.POST)
    public ResultInfo publish(@RequestBody CommentRecive commentRecive){
        return commentService.publish(commentRecive);
    }

    @RequestMapping(value="/article/{id}", method=RequestMethod.GET)
    public ResultInfo getCommentByArticle(@PathVariable Integer id){
        return commentService.getCommentByArticleId(id);
    }

}
