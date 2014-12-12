/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import jade.lang.acl.ACLMessage;
import java.util.Map;


public class SimpleACLMessageHandler implements ACLMessageHandler
{
    private KeyBuilder keyBuilder;
    private Map<String, MessageHandler> messageHandlers;
    
    public SimpleACLMessageHandler(KeyBuilder keyBuilder, Map<String, MessageHandler> messageHandlers)
    {
        this.keyBuilder = keyBuilder;
        this.messageHandlers = messageHandlers;
    }
    
    public void Invoke(ACLMessage msg)
    {
        Map<String, Object> content = null;
        try
        {
            content = (Map<String, Object>)msg.getContentObject();
        }
        catch (Exception e)
        {
        }
        if (content != null)
        {
            MessageHandler handler = messageHandlers.get(keyBuilder.build(content));
            if (handler != null)
            {
                handler.Invoke(msg, content);
            }
        }
    }
}
