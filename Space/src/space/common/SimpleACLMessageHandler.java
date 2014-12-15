/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.common;

import jade.core.Agent;
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
    
    public void Invoke(Agent agent, ACLMessage msg)
    {
        Map<String, Object> msgContent = null;
        try
        {
            msgContent = (Map<String, Object>)msg.getContentObject();
        }
        catch (Exception e)
        {
        }
        if (msgContent != null)
        {
            MessageHandler handler = messageHandlers.get(keyBuilder.getKey(msgContent));
            if (handler != null)
            {
                handler.Invoke(agent, msg, msgContent);
            }
        }
    }
}
