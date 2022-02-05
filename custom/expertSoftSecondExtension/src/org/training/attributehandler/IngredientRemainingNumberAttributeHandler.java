package org.training.attributehandler;

import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;
import org.springframework.stereotype.Component;
import org.training.model.IngredientModel;

@Component
public class IngredientRemainingNumberAttributeHandler extends AbstractDynamicAttributeHandler<Long, IngredientModel> {
    @Override
    public Long get(IngredientModel model) {
        //Logically wrong handler
        if(model == null){
            return null;
        }
        String name = model.getName();
        if(name == null || name.isEmpty()){
            return null;
        }
        return (long) (Math.random() * 10) * name.length();
    }

}
