package org.training.jobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.util.mail.MailUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.training.model.DishNewsModel;
import org.training.service.DishNewsService;

import java.util.Date;
import java.util.List;

@Component
public class SendDishNewsJob extends AbstractJobPerformable<CronJobModel> {
    private static final Logger LOG = Logger.getLogger(SendDishNewsJob.class);
    private final DishNewsService newsService;
    private final ConfigurationService configurationService;

    public SendDishNewsJob(DishNewsService newsService, ConfigurationService configurationService) {
        this.newsService = newsService;
        this.configurationService = configurationService;
    }

    @Override
    public PerformResult perform(final CronJobModel cronJob) {
        LOG.info("Sending news mails. Note that org.apache.commons.mail.send() can block if behind a firewall/proxy.");
        final List<DishNewsModel> newsItems = newsService.getNewsOfDay(new Date());
        if (newsItems.isEmpty()) {
            LOG.info("No news items for today, skipping send of ranking mails");
            return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        }
        final StringBuilder mailContentBuilder = new StringBuilder(2000);
        int index = 1;
        mailContentBuilder.append("Todays news summary:\n\n");
        for (final DishNewsModel news : newsItems) {
            mailContentBuilder.append(index++);
            mailContentBuilder.append(". ");
            mailContentBuilder.append(news.getHeadline());
            mailContentBuilder.append("\n");
            mailContentBuilder.append(news.getContent());
            mailContentBuilder.append("\n\n");
        }
        try {
            sendEmail(mailContentBuilder.toString());
        } catch (final EmailException e) {
            LOG.error("Problem sending new email. Note that org.apache.commons.mail.send() can block if behind a firewall/proxy.)");
            LOG.error("Problem sending new email.", e);
            return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
        }
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    private void sendEmail(final String message) throws EmailException {
        final String subject = "Daily News Summary";
        // get mail service configuration
        final Email email = MailUtils.getPreConfiguredEmail();
        //send message
        Configuration config = configurationService.getConfiguration();
        String recipient = config.getString("news_summary_mailing_address", null);
        email.addTo(recipient);
        email.setSubject(subject);
        email.setMsg(message);
        email.setTLS(true);
        email.send();
        LOG.info(subject);
        LOG.info(message);
    }
}
