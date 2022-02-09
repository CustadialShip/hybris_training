package org.training.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.training.model.CookModel;
import org.training.service.CookService;

import java.util.List;

@Controller
public class CookController {
    public static final String COOK_LIST = "cookList";
    public static final String COOK_PAGE = "cookPage";

    private final CookService cookService;

    public CookController(CookService cookService) {
        this.cookService = cookService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cooks")
    public String getCooks(Model model){
        List<CookModel> cookModelList = cookService.getCooks();
        model.addAttribute(COOK_LIST, cookModelList);
        return COOK_PAGE;
    }
}
