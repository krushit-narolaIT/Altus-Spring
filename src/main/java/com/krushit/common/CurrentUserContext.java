package com.krushit.common;

import com.krushit.entity.User;

public class CurrentUserContext {

    private static ThreadLocal<String> currentUserId = new ThreadLocal<>();
    private static ThreadLocal<User> currentUser = new ThreadLocal<>();

    public static String getCurrentUserId() {
        return currentUserId.get();
    }

    public static void setCurrentUserId(String userId) {
        currentUserId.set(userId);
    }

    public static User getCurrentUser() {
        return currentUser.get();
    }

    public static void setCurrentUser(User user) { currentUser.set(user); }

    public static void clear() {
        currentUserId.remove();
    }

    public static void clearCurrentUser() {
        currentUser.remove();
    }
}
