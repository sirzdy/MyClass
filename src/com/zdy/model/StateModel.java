package com.zdy.model;

import java.sql.Blob;

/**
 * Created by zdy on 2017/1/18.
 */
public class StateModel {
    private String id;
    private String avatar;
    private String blog;
    private String signature;
    private String statement;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "StateModel{" +
                "id='" + id + '\'' +
                ", blog='" + blog + '\'' +
                ", signature='" + signature + '\'' +
                ", statement='" + statement + '\'' +
                '}';
    }
}
