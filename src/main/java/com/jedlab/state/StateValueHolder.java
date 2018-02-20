package com.jedlab.state;

import java.io.Serializable;

public class StateValueHolder implements Serializable
{

    private String messageId;

    public StateValueHolder(String messageId)
    {
        this.messageId = messageId;
    }

    public String getMessageId()
    {
        return messageId;
    }

}
