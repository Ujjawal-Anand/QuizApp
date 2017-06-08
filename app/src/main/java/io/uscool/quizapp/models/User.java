package io.uscool.quizapp.models;

/**
 * Created by ujjawal on 6/6/17.
 * Model of a user which is taking the test
 */

public class User {
    private String username;
    private String avatar;

    /**
     * constructor of User class requires the username and avatar
     * @param mUsername username of the user
     * @param mAvatar name of avatar
    * */

    public User() {}
    public User(String mUsername, String mAvatar) {
        this.username = mUsername;
        this.avatar = mAvatar;
    }
   /**
    * get the username of current user
    * @return username of user
    * */
    public String getUsername() {
        return username;
    }
   /**
    *get the avatar string
    * @return avatar
    * */
    public String getAvatar() {
        return avatar;
    }
}
