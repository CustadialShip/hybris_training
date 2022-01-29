package org.training.controller;

import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.model.DishModel;
import org.training.service.DishService;

import java.util.List;

@Controller
public class DishController {
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String DISH = "dish";
    public static final String DISH_PAGE = "dishPage";
    public static final String DISH_LIST = "dishList";
    public static final String DISH_PAGE_ALL = "dishAllPage";
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/dish/get")
    public String getDish(@RequestParam(required = false) String code, Model model) {
        try {
            DishModel dishModel = dishService.getDishByCode(code);
            model.addAttribute(DISH, dishModel);
        } catch (UnknownIdentifierException | AmbiguousIdentifierException e) {
            model.addAttribute(ERROR_MESSAGE, e.getMessage());
        }
        return DISH_PAGE;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/dish/all")
    public String getAllDish(Model model){
        List<DishModel> dishModelList = dishService.getDishes();
        model.addAttribute(DISH_LIST, dishModelList);
        return DISH_PAGE_ALL;
    }
}
