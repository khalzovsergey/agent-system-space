/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.planets;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import java.util.Map;
import space.MessageHandler;

/**
 *
 * @author Sergey
 */
public class PingReplyMessageHandler implements MessageHandler
{
    public void Invoke(Agent agent, ACLMessage msg, Map<String, Object> content)
    {
        long time = (Long)content.get("time");
        System.out.println("ping: " + (System.currentTimeMillis() - time) + " ms");
    }
}
