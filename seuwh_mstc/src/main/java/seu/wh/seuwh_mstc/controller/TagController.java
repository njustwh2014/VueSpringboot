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
import seu.wh.seuwh_mstc.result.ResultInfo;
import seu.wh.seuwh_mstc.service.TagService;

@RestController
@CrossOrigin
public class TagController {

    @Autowired
    TagService tagService;

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public ResultInfo getAllTags(){
        return tagService.getAllTag();
    }
    @RequestMapping(value = "/tags/category/{id}",method = RequestMethod.GET)
    public ResultInfo getTagByCategory(@PathVariable Integer id){
        return tagService.getTagByCategory(id);
    }
}
