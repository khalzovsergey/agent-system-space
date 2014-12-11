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
import java.util.List;
import java.util.Map;
import space.Scheduler;
import space.VariableDouble;

/**
 *
 * @author Sergey
 */
public class CivilizationsBehaviour extends Behaviour
{
    private Map<String, Object> planet;
    private VariableDouble count;
    private VariableDouble increment;
    private int delay = 2000;
    private Scheduler scheduler;

    public CivilizationsBehaviour(Map<String, Object> planet)
    {
        this.planet = planet;
        List<Object> civilizations = (List<Object>)planet.get("civilizations");
        Map<String, Object> civilization = (Map<String, Object>)civilizations.get(0);
        count = (VariableDouble)civilization.get("number");
        increment = (VariableDouble)civilization.get("birth_rate");
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
            if (count.value > 0)
            {
                count.value += count.value * increment.value;
            }
            
            String str = "number of civilization = " + count.value;
            System.out.println(str);
            
            DFAgentDescription[] monitors = serviceSearch("monitor");
            if (monitors != null)
            {
                ACLMessage msg = new ACLMessage(ACLMessage.UNKNOWN);
                
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
