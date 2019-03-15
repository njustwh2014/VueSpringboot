/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package seu.wh.seuwh_mstc.service;

import seu.wh.seuwh_mstc.model.comment.CommentRecive;
import seu.wh.seuwh_mstc.result.ResultInfo;

public interface CommentService {
    public ResultInfo publish(CommentRecive commentRecive);
    public ResultInfo getCommentByArticleId(Integer id);
    public ResultInfo commentLike(Integer type,Integer entityid,Integer userid);
}
