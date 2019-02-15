package com.github.socketio;

/**
 * @author ron
 * @version ChatObject, v 0.1 2019/2/14 11:42 Administrator Exp $
 * @contact raosay92@gmail.com
 */
public class ChatObject {


    private  String userName;

    private String  message;

    /**
     * @return Returns the  userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName The userName to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return Returns the  message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "{" +
                "userName='" + userName + '\'' +
                ", message=" + message +
                '}';
    }
}
