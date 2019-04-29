package com.example.demo.db;

public class DBContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
    private static final ThreadLocal<String> urlHolder = new ThreadLocal<>();
    private static final ThreadLocal<String> usernameHolder = new ThreadLocal<>();
    private static final ThreadLocal<String> passwordHolder = new ThreadLocal<>();
    private static final ThreadLocal<String> nameHolder = new ThreadLocal<>();
    private static final ThreadLocal<String> serverIdHolder = new ThreadLocal<>();

    public static void setDB(String custId) {
        contextHolder.set(custId);
        urlHolder.set(null);
        usernameHolder.set(null);
        passwordHolder.set(null);
        nameHolder.set(null);
        serverIdHolder.set(null);
    }

    public static void clearDB() {
        contextHolder.remove();
        urlHolder.remove();
        usernameHolder.remove();
        passwordHolder.remove();
        nameHolder.remove();
        serverIdHolder.remove();
    }

    public static String getDB() {
        return contextHolder.get();
    }

    public static void setUrl(String url) {
        urlHolder.set(url);
    }

    public static String getUrl() {
        return urlHolder.get();
    }

    public static void setUsername(String username) {
        usernameHolder.set(username);
    }

    public static String getUsername() {
        return usernameHolder.get();
    }

    public static void setPassword(String password) {
        passwordHolder.set(password);
    }

    public static String getPassword() {
        return passwordHolder.get();
    }

    public static String getName() {
        return nameHolder.get();
    }

    public static void setName(String name) {
        nameHolder.set(name);
    }

    public static String getServerId() {
        return serverIdHolder.get();
    }

    public static void setServerId(String serverId) {
        serverIdHolder.set(serverId);
    }
}
