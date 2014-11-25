import jade.core.Agent;
import jade.core.behaviours.*;

public class Planet extends Agent
{
    protected void setup()
    {
        addBehaviour(new myBehaviour(this));
    }
    
    protected void takeDown()
    {
        System.exit(0);
    }
    
    class myBehaviour extends SimpleBehaviour
    {
        public myBehaviour(Agent agent)
        {
            super(agent);
        }
        
        //private boolean finished = false;
        
        public void action()
        {
            System.out.println("This is planet.");
            System.out.println("My name is "+ myAgent.getLocalName());
            //finished = true;
            myAgent.addBehaviour(new myBehaviour2(myAgent));
            //block(0);
            //doDelete();
        }
        
        public boolean done()
        {
            return true;
        }
    }
    
    class myBehaviour2 extends SimpleBehaviour
    {
        public myBehaviour2(Agent agent)
        {
            super(agent);
        }
        
        //private boolean finished = false;
        
        public void action()
        {
            System.out.println("This is myBehaviour2.action()");
            //finished = true;
            //block(0);
            myAgent.doDelete();
        }
        
        public boolean done()
        {
            return true;
        }
    }
}
