/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package seu.wh.seuwh_mstc.service;

import seu.wh.seuwh_mstc.model.ArticleRecive;
import seu.wh.seuwh_mstc.result.ResultInfo;

public interface ArticleService {
 public ResultInfo publish(ArticleRecive articleRecive);
 public ResultInfo view(Integer id);
 public ResultInfo getAllArticle(Integer pageNumber,Integer pageSize);
}
