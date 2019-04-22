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
import seu.wh.seuwh_mstc.service.CategoryService;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/getall", method = RequestMethod.POST)
    public ResultInfo getAllCaregory(@RequestBody JSONObject jsonObject){
        Integer pageNumber=jsonObject.getInteger("pageNumber");
        Integer pageSize=jsonObject.getInteger("pageSize");
        return categoryService.getAllCategory(pageNumber,pageSize);
    }

    @RequestMapping(value="/changestatus",method = RequestMethod.POST)
    public ResultInfo changeStatus(@RequestBody JSONObject jsonObject){
        Integer id=jsonObject.getInteger("id");
        String categorystatus=jsonObject.getString("categorystatus");
        return categoryService.changeStatus(id,categorystatus);
    }
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ResultInfo deleteCategory(@RequestBody JSONObject jsonObject){
        Integer id=jsonObject.getInteger("id");
        return categoryService.deleteCategory(id);
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResultInfo addCategory(@RequestBody JSONObject jsonObject){
        String newcategory=jsonObject.getString("newcategory");
        return categoryService.addCategory(newcategory);
    }

    @RequestMapping(value = "getallwithoutsize",method = RequestMethod.GET)
    public ResultInfo getCategory(){
        return categoryService.getCategory();
    }

}
