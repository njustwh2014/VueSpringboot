/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package seu.wh.seuwh_mstc.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import seu.wh.seuwh_mstc.result.ResultInfo;
import seu.wh.seuwh_mstc.service.TagService;

@RestController
@CrossOrigin
@RequestMapping(value="/tag")
public class TagController {

    @Autowired
    TagService tagService;

    @RequestMapping(value = "/getall", method = RequestMethod.POST)
    public ResultInfo getAllTag(@RequestBody JSONObject jsonObject){
        Integer pageNumber=jsonObject.getInteger("pageNumber");
        Integer pageSize=jsonObject.getInteger("pageSize");
        return tagService.getAllTag(pageNumber,pageSize);
    }

    @RequestMapping(value="/changestatus",method = RequestMethod.POST)
    public ResultInfo changeStatus(@RequestBody JSONObject jsonObject){
        Integer id=jsonObject.getInteger("id");
        String tagstatus=jsonObject.getString("tagstatus");
        return tagService.changeStatus(id,tagstatus);
    }
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ResultInfo deleteTag(@RequestBody JSONObject jsonObject){
        Integer id=jsonObject.getInteger("id");
        return tagService.deleteTag(id);
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResultInfo addTag(@RequestBody JSONObject jsonObject){
        String newtag=jsonObject.getString("newtag");
        Integer categoryid=jsonObject.getInteger("categoryid");
        return tagService.addTag(newtag,categoryid);
    }

    @RequestMapping(value = "/category/{id}",method = RequestMethod.GET)
    public ResultInfo getTagByCategory(@PathVariable Integer id){
        return tagService.getTagByCategory(id);
    }
}
