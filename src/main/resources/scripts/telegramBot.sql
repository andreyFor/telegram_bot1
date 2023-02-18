-- liquibase formatted sql

-- changeset eosreign:1
CREATE TABLE telegramBot (
                             id SERIAL,
                             chatId Integer not null,
                             chatMessage TEXT not null,
                             dateAndTime TEXT not null
)