package com.example.asus1.project2;



public class ChatRow {

    private String content;
    private String senderName;
    private long time;

    public ChatRow(String content, String senderName, long time)
    {
        this.content = content;
        this.senderName = senderName;
        this.time = time;
    }

    public String getContent()
    {
        return this.content;
    }

    public String getSenderName()
    {
        return this.senderName;
    }


    public long getTime()
    {
        return this.time;
    }
}
