package org.training.listener;

import de.hybris.platform.servicelayer.event.events.AfterItemCreationEvent;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.training.model.DishModel;
import org.training.model.DishNewsModel;

import java.util.Date;

@Component
public class NewDishEventListener extends AbstractEventListener<AfterItemCreationEvent> {
    public static final String NEW_DISH_HEADLINE = "Congratulation, new dish %s!!!";
    public static final String NEW_DISH_CONTENT = "We like to introduce our new dish, %s";
    private final ModelService modelService;

    public NewDishEventListener(ModelService modelService) {
        this.modelService = modelService;
    }

    @Override
    protected void onEvent(AfterItemCreationEvent event) {
        if (event != null && event.getSource() != null) {
            Object object = modelService.get(event.getSource());
            if (object instanceof DishModel) {
                DishModel dishModel = (DishModel) object;
                String headline = String.format(NEW_DISH_HEADLINE, dishModel.getName());
                String content = String.format(NEW_DISH_CONTENT, dishModel.getName());
                DishNewsModel news = modelService.create(DishNewsModel.class);
                news.setDate(new Date());
                news.setHeadline(headline);
                news.setContent(content);
                modelService.save(news);
            }
        }
    }
}
