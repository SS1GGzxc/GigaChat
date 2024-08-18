package com.SS1GGzxc.Language;

import com.SS1GGzxc.Settings.Settings;

public class Phrases {
    private static final String chooseLangRu = "Выбирите язык.";
    private static final String chooseLangEn = "Select a language.";
    private static final String forgotSetKeyRu = "Вы не указали ключ.";
    private static final String forgotSetKeyEn = "You did not specify a key.";
    private static final String errorSetKeyRu = "Ошибка: Укажите ключ авторизации!";
    private static final String errorSetKeyEn = "Error: Enter authorization key!";
    private static final String gettingKeyRu = "Получение ключа доступа.\nОжижайте ответа.";
    private static final String gettingKeyEn = "Receive the access key.\nWait for the answer.";
    private static final String successKeyRu = "Ключ доступа обновлен!\nСоздается ответ на ваш вопрос!";
    private static final String successKeyEn = "The access key has been updated!\nThe answer to your question is being created!";
    private static final String consoleErrorRu = "Данную комманду можно использовать только игрокам!";
    private static final String consoleErrorEn = "This command can only be used by players!";

    public static String getChooseLang() {
        return Settings.getInstance().isEn() ? chooseLangEn : chooseLangRu;
    }

    public static String getConsoleError() {
        return Settings.getInstance().isEn() ? consoleErrorEn : consoleErrorRu;
    }

    public static String getSuccessKey() {
        return Settings.getInstance().isEn() ? successKeyEn : successKeyRu;
    }

    public static String getGettingKey() {
        return Settings.getInstance().isEn() ? gettingKeyEn : gettingKeyRu;
    }

    public static String getErrorSetKey() {
        return Settings.getInstance().isEn() ? errorSetKeyEn : errorSetKeyRu;
    }

    public static String getForgotSetKey() {
        return Settings.getInstance().isEn() ? forgotSetKeyEn : forgotSetKeyRu;
    }
}
