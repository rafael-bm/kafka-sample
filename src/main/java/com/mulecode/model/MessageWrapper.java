package com.mulecode.model;

public class MessageWrapper {

    private String id;
    private String publisher;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "MessageWrapper{" +
                "id='" + id + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
