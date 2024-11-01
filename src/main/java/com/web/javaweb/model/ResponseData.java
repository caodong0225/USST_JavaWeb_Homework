package com.web.javaweb.model;

import java.util.List;
import java.util.Set;

/**
 * @author jyzxc
 * @since 2024-11-01
 */
public class ResponseData {
    private List<Message> messageList;
    private boolean success;
    private Set<String> userList;

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Set<String> getUserList() {
        return userList;
    }

    public void setUserList(Set<String> userList) {
        this.userList = userList;
    }
}
