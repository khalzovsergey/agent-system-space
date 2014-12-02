package space.planets;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ReceiverBehaviour;
import java.util.List;

public class PlanetAgent extends Agent
{
    private double resource = 100.0;
    //private List<Resource> resources;
    
    protected void setup()
    {
        addBehaviour(new LoadBehaviour());
        addBehaviour(new CivilizationBehaviour("QS1"));
        addBehaviour(new CivilizationBehaviour("HK5"));
        addBehaviour(new ResourceBehaviour());
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
        }
        
        public boolean done()
        {
            //myAgent.doDelete();
            return true;
            //return finished;
        }
    }
    
    private class CivilizationBehaviour extends Behaviour
    {
        //private boolean finished = false;
        
        private String name = "no name";
        private double count = 100.0;
        private double increment = 0.001;
        private int delay = 1000;
                
        public CivilizationBehaviour(String name)
        {
            if (name != null)
            {
                this.name = name;
            }
        }
        
        public void action()
        {
            if (count > 0)
            {
                count += count * increment;
            }
            System.out.println("number of civilization " + name + " = " + count);
            block(delay);
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
}
