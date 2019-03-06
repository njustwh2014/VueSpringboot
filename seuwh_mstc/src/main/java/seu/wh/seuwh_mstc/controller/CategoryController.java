/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package seu.wh.seuwh_mstc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import seu.wh.seuwh_mstc.result.ResultInfo;
import seu.wh.seuwh_mstc.service.CategoryService;

@RestController
@CrossOrigin
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/categorys", method = RequestMethod.GET)
    public ResultInfo getAllCaregory(){
        return categoryService.getAllCategory();
    }


}
