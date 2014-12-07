package space.planets;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import space.*;

public class PlanetAgent extends Agent
{
    private Planet planet;
    
    private void PlanetInitialization()
    {
        planet = new Planet();
        
        Map<String, Object> planet_attributes = planet.getAttributes();
        
        planet_attributes.put("name", getLocalName());
        
        Map<String, VariableDouble> resources = new HashMap<>();
        resources.put("energy.current_value", new VariableDouble(100));
        resources.put("energy.max_value", new VariableDouble(200));
        planet_attributes.put("resources", resources);
        
        List<Civilization> civilizations = new ArrayList<>();
        Civilization civilization = new Civilization();
        Map<String, Object> civilization_attributes = civilization.getAttributes();
        civilization_attributes.put("name", "SLT1");
        civilization_attributes.put("number", new VariableDouble(99));
        civilization_attributes.put("birth_rate", new VariableDouble(0.001));
        civilizations.add(civilization);
        planet_attributes.put("civilizations", civilizations);
    }
    
    
    
    
    
    
    
    
    
    
    private double resource = 80.0;
    //private List<Resource> resources;
    
    protected void setup()
    {
        PlanetInitialization();
        try
        {
            addBehaviour(new LoadBehaviour());
            addBehaviour(new CivilizationsBehaviour(planet));
            //addBehaviour(new ResourceBehaviour());
            addBehaviour(new ReceiverBehaviour(planet));
        }
        catch (NullArgumentException e)
        {
            System.err.println("planet == null");
        }
    }
    
    protected void takeDown()
    {
        System.exit(0);
    }
    
    private class LoadBehaviour extends Behaviour
    {
        //private boolean finished = false;

        public void action()
        {
            System.out.println("This is planet. My name is " + myAgent.getLocalName() + ".");
            //block();
            //finished = true;
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.setContent("Planet named " + myAgent.getLocalName() + " was created.");
            msg.addReceiver(new AID("Monitor", AID.ISLOCALNAME));
            myAgent.send(msg);
        }
        
        public boolean done()
        {
            //myAgent.doDelete();
            return true;
            //return finished;
        }
    }
    
    private class CivilizationsBehaviour extends Behaviour
    {
        //private boolean finished = false;
        private Planet planet;
        
        private VariableDouble count;
        private VariableDouble increment;
        private int delay = 500;
        private Scheduler scheduler;
                
        public CivilizationsBehaviour(Planet planet) throws NullArgumentException
        {
            if (planet == null)
            {
                throw new NullArgumentException("planet");
            }
            this.planet = planet;
            List<Civilization> civilizations = (List<Civilization>)planet.getAttributes().get("civilizations");
            Civilization civilization = civilizations.get(0);
            count = (VariableDouble)civilization.getAttributes().get("number");
            increment = (VariableDouble)civilization.getAttributes().get("birth_rate");
            scheduler = new Scheduler();
        }
        
        public void action()
        {
            if (scheduler.execute())
            {
                if (count.value > 0)
                {
                    count.value += count.value * increment.value;
                }
                System.out.println("number of civilization = " + count.value);
                scheduler.next(delay);
            }
            long dt = scheduler.getDelay();
            if (dt > 0)
            {
                block(dt);
            }
        }
        
        public boolean done()
        {
            //myAgent.doDelete();
            return false;
        }
    }
    
    private class ResourceBehaviour extends Behaviour
    {
        //private boolean finished = false;

        private double increment = 1.0;
        private int delay = 900;
        private double maxValue = 150;
        
        public void action()
        {
            if (((PlanetAgent)myAgent).resource < maxValue)
            {
                ((PlanetAgent)myAgent).resource += increment;
            }
            System.out.println("amount of resource = " + ((PlanetAgent)myAgent).resource);
            block(delay);
        }
        
        public boolean done()
        {
            //myAgent.doDelete();
            return false;
            //return finished;
        }
    }
    
    private class ReceiverBehaviour extends Behaviour
    {
        private VariableDouble count;
        
        public ReceiverBehaviour(Planet planet) throws NullArgumentException
        {
            if (planet == null)
            {
                throw new NullArgumentException("planet");
            }
            List<Civilization> civilizations = (List<Civilization>)planet.getAttributes().get("civilizations");
            Civilization civilization = civilizations.get(0);
            count = (VariableDouble)civilization.getAttributes().get("number");
        }
        
        public void action()
        {
            MessageTemplate mt = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
                    MessageTemplate.MatchSender(new AID("Monitor", AID.ISLOCALNAME)));
            ACLMessage msg = myAgent.receive(mt);
            if (msg != null)
            {
                if (msg.getContent().equals("civilization number"))
                {
                    ACLMessage reply = msg.createReply();
                    reply.setPerformative(ACLMessage.INFORM);
                    reply.setContent("civilization number = " + count.value);
                    myAgent.send(reply);
                }
            }
            block();
        }
        
        public boolean done()
        {
            return false;
        }
    }
}
