package jacksonmeyer.com.sidekck.constructors;

import java.util.ArrayList;

public class Post {
    String postOwnersUid;
    String postMessagePost;
    Integer numberOfTimesFlagged;
    Integer numberOfSideKicksNeeded;
    ArrayList<String> potentialSidekicks;

    public Post(String postOwnersUid, String postMessagePost, Integer numberOfTimesFlagged, Integer numberOfSideKicksNeeded, ArrayList<String> potentialSidekicks) {
        this.postOwnersUid = postOwnersUid;
        this.postMessagePost = postMessagePost;
        this.numberOfTimesFlagged = numberOfTimesFlagged;
        this.numberOfSideKicksNeeded = numberOfSideKicksNeeded;
        this.potentialSidekicks = potentialSidekicks;
    }

    public String getPostOwnersUid() {
        return postOwnersUid;
    }

    public String getPostMessagePost() {
        return postMessagePost;
    }

    public Integer getNumberOfTimesFlagged() {
        return numberOfTimesFlagged;
    }

    public Integer getNumberOfSideKicksNeeded() {
        return numberOfSideKicksNeeded;
    }

    public ArrayList<String> getPotentialSidekicks() {
        return potentialSidekicks;
    }
}