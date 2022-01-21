package org.training.web.controllers;

import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.service.BandService;

@Controller
public class BandController {
    public static final String MESSAGE = "message";
    public static final String ERROR = "error";
    public static final String BAND = "band";
    private final BandService bandService;

    public BandController(BandService bandService) {
        this.bandService = bandService;
    }

    @GetMapping(value = "/band")
    public String showBand(@RequestParam String name, Model model){
        if(name != null && !name.isEmpty()){
            BandModel bandModel = null;
            try{
                bandModel = bandService.getBand(name);
            } catch (UnknownIdentifierException | AmbiguousIdentifierException e){
                model.addAttribute(MESSAGE, ERROR);
            }
            model.addAttribute(BAND, bandModel);
        }
        return "band";
    }
}
