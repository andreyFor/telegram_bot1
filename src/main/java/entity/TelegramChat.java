package entity;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "telegramBot")
public class TelegramChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private String chatMessage;
    @Column(name = "datetime")
    private LocalDateTime dateTime;

    public TelegramChat() {
    }

    public TelegramChat(Long chatId) {
        this.chatId = chatId;
    }

    public TelegramChat(Long chatId, String chatMessage, LocalDateTime dateTime) {
        this.chatId = chatId;
        this.chatMessage = chatMessage;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public Long getChatId() {
        return chatId;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }

    public void setDateAndTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TelegramChat that = (TelegramChat) o;
        return chatId == that.chatId && Objects.equals(id, that.id) && Objects.equals(chatMessage, that.chatMessage) && Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, chatMessage, dateTime);
    }

    @Override
    public String toString() {
        return "TelegramChat{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", chatMessage='" + chatMessage + '\'' +
                ", dateAndTime='" + dateTime + '\'' +
                '}';
    }
}