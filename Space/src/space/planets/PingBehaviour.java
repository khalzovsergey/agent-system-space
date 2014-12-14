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
import space.common.Scheduler;

/**
 *
 * @author Sergey
 */
public class PingBehaviour extends Behaviour
{
    private int delay = 3000;
    private Scheduler scheduler;
    
    public PingBehaviour()
    {
        scheduler = new Scheduler();
    }
    
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
        if (scheduler.mayExecute())
        {
            DFAgentDescription[] monitors = serviceSearch("ping");
            if (monitors != null)
            {
                ACLMessage msg = new ACLMessage(ACLMessage.UNKNOWN);
                Map<String, Object> message = new HashMap<>();
                message.put("type", "ping");
                message.put("message_type", "request");
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
                    if (!tmp.getName().equals(myAgent.getAID()))
                    {
                        msg.addReceiver(tmp.getName());
                    }
                }
                myAgent.send(msg);
            }
            scheduler.addDelay(delay);
        }
        scheduler.block(this);
    }

    public boolean done()
    {
        return false;
    }
}
