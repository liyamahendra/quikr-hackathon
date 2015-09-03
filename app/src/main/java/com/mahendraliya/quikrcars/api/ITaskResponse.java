package com.mahendraliya.quikrcars.api;

/**
 * Created by mahendraliya on 13/07/15.
 */
public interface ITaskResponse {
    void onSuccess(String response);
    void onFailure(String response);
}
