package service;



import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import entity.TelegramChat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.TelegramBotRepository;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TelegramChatService {
    @Autowired
    TelegramBot telegramBot;

    @Autowired
    TelegramBotRepository repository;

    public final static Pattern PATTERN = Pattern.compile("([0-9\\.\\:\\s]{16})(\\s)([\\P{M}\\p{M}*+]+)");
    private Logger logger = LoggerFactory.getLogger(TelegramChatService.class);

    public void checkUpdate(Update update) {

        if (update.message().text() != null) {
            String input = update.message().text();
            Long chatId = update.message().chat().id();

            if (input.equals("/start")) {
                logger.info("Начата работа бота");
                SendMessage message = new SendMessage(chatId, "Hello, dear friend");
                telegramBot.execute(message);

            } else {
                logger.info("Сравнение входа");
                Matcher matcher = PATTERN.matcher(input);

                if (matcher.matches()) {
                    logger.info("Производится создание телеграмЧата.");
                    String date = matcher.group(1);
                    String text = matcher.group(3);
                    LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
                    createTask(chatId, text, localDateTime);

                } else {
                    logger.info("Неверный формат сообщения");
                    SendMessage message = new SendMessage(chatId, "Введена неверная комманда.");
                    telegramBot.execute(message);
                }
            }
        }
    }

    private void createTask(Long chatId, String text, LocalDateTime localDateTime) {
        logger.info("Записывание в БД.");
        repository.save(new TelegramChat(chatId, text, localDateTime));
    }

}