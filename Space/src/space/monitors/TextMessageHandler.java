/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.monitors;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import java.util.Map;
import space.common.MessageHandler;

/**
 *
 * @author Sergey
 */
public class TextMessageHandler implements MessageHandler
{
    private String getString(Object obj)
    {
        String result;
        if (obj == null)
        {
            result = "";
        }
        else
        {
            result = obj.toString();
        }
        return result;
    }

    public void Invoke(Agent agent, ACLMessage msg, Map<String, Object> msgContent)
    {
        StringBuilder str = new StringBuilder();
        str = str.append(msg.getSender().getLocalName());
        str = str.append(": ");
        str = str.append(getString(msgContent.get("content")));
        System.out.println(str.toString());
    }
}
