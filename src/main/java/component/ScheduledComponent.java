package component;



import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import entity.TelegramChat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import repositories.TelegramBotRepository;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

@Component
public class ScheduledComponent {
    private final Logger logger = LoggerFactory.getLogger(ScheduledComponent.class);

    private final TelegramBotRepository repository;
    private final TelegramBot telegramBot;

    public ScheduledComponent(TelegramBotRepository repository, TelegramBot telegramBot) {
        this.repository = repository;
        this.telegramBot = telegramBot;
    }

    @Scheduled(cron = "0 0/1 * * * *")
    private void checkSchedule() {
        logger.info("Производится метод CheckSchedule");
        Collection<TelegramChat> list = repository.findByDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        list.forEach(telegramChat -> {
            logger.info("напоминание выполнено");
            telegramBot.execute(new SendMessage(telegramChat.getChatId(), telegramChat.getChatMessage()));
            repository.delete(telegramChat);
            logger.info("Окончание работы метода Checkschedule");
        });
    }








}
