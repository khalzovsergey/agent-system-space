/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.monitors;

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
import space.Scheduler;

/**
 *
 * @author Sergey
 */
public class RequestBehaviour extends Behaviour
{
    private int delay = 3000;
    private Scheduler scheduler = new Scheduler();
    private Map<String, Object> getCoordinatesMsg;

    public RequestBehaviour()
    {
        getCoordinatesMsg = new HashMap<>();
        getCoordinatesMsg.put("type", "getCoordinates");
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
            DFAgentDescription[] coordinatesServices = serviceSearch("getCoordinates");
            if (coordinatesServices != null)
            {
                ACLMessage msg = new ACLMessage(ACLMessage.UNKNOWN);
                try
                {
                    msg.setContentObject((Serializable)getCoordinatesMsg);
                }
                catch (IOException e)
                {
                }
                for (DFAgentDescription tmp : coordinatesServices)
                {
                    msg.addReceiver(tmp.getName());
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
