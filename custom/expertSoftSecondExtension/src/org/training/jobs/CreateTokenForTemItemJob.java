package org.training.jobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.training.model.TempItemModel;
import org.training.service.DishNewsService;
import org.training.service.TempItemService;

import java.util.Random;

@Component
public class CreateTokenForTemItemJob extends AbstractJobPerformable<CronJobModel> {
    private static final Logger LOG = Logger.getLogger(SendDishNewsJob.class);
    public static final String ALPHABET = "abcdefghigklmnopqrstuvwxyz";
    public static final int RANDOM_STRING_SIZE = 10;
    public static final String CREATING_NEW_TOKEN = "Creating new token for tempItem";
    public static final String NO_TEMP_ITEM = "There are no TempItem";
    private final TempItemService itemService;

    public CreateTokenForTemItemJob(TempItemService itemService) {
        this.itemService = itemService;
    }


    @Override
    public PerformResult perform(CronJobModel cronJobModel) {
        LOG.info(CREATING_NEW_TOKEN);
        TempItemModel itemModel = itemService.getOneTempItem();
        if(itemModel == null){
            LOG.info(NO_TEMP_ITEM);
            return new PerformResult(CronJobResult.FAILURE, CronJobStatus.ABORTED);
        }
        itemModel.setToken(getRandomString());
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    private String getRandomString(){
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        int length = ALPHABET.length();
        for(int i = 0; i < RANDOM_STRING_SIZE; i++){
            int index = random.nextInt(length);
            char randomChar = ALPHABET.charAt(index);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }
}
