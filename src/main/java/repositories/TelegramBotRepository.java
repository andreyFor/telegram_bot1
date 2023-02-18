package repositories;


import entity.TelegramChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.awt.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public interface TelegramBotRepository extends JpaRepository<TelegramChat, Long> {
    Collection<TelegramChat> findByDateTime(LocalDateTime dateTime);


}