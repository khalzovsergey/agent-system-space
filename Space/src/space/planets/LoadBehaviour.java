/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.planets;

import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sergey
 */
public class LoadBehaviour extends Behaviour
{
    private DFAgentDescription[] serviceSearch(String type)
    {
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd  = new ServiceDescription();
        sd.setType(type);
        dfd.addServices(sd);

        DFAgentDescription[] result = null;
        try
        {
            result = DFService.search(myAgent, dfd);
        }
        catch (FIPAException e)
        {
        }
        return result;
    }
    
    public void action()
    {
        System.out.println("This is planet. My name is " + myAgent.getLocalName() + ".");
        DFAgentDescription[] monitors = serviceSearch("monitor");
        if (monitors != null)
        {
            ACLMessage msg = new ACLMessage(ACLMessage.UNKNOWN);
            String str = "Planet named " + myAgent.getLocalName() + " was created.";
            Map<String, Object> message = new HashMap<>();
            message.put("type", "text");
            message.put("content", str);
            try
            {
                msg.setContentObject((Serializable)message);
            }
            catch (IOException e)
            {
            }
            for (DFAgentDescription tmp : monitors)
            {
                msg.addReceiver(tmp.getName());
            }
            myAgent.send(msg);
            
            msg = new ACLMessage(ACLMessage.UNKNOWN);
            message = new HashMap<>();
            message.put("type", "ping");
            message.put("time", System.currentTimeMillis());
            try
            {
                msg.setContentObject((Serializable)message);
            }
            catch (IOException e)
            {
            }
            for (DFAgentDescription tmp : monitors)
            {
                msg.addReceiver(tmp.getName());
            }
            myAgent.send(msg);
        }
    }

    public boolean done()
    {
        return true;
    }
}
