package space.planets;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import space.*;

public class SimplePlanetAgent extends Agent
{
    private Map<String, Object> planet;
    private ACLMessageHandler msgHandler;
    
    private void messageHandlersInitialization()
    {
        KeyValueList list = new KeyValueList("type");
        KeyBuilder keyBuilder = new KeyBuilder(list);
        Map<String, MessageHandler> messageHandlers = new HashMap<>();
//        list.setValue("type", "text");
//        messageHandlers.put(KeyBuilder.build(list), new TextMassageHandler());
        msgHandler = new SimpleACLMessageHandler(keyBuilder, messageHandlers);
    }
    
    private void planetInitialization()
    {
        planet = new HashMap<>();

        planet.put("name", getLocalName());

        Map<String, Object> resources = new HashMap<>();
        resources.put("energy.current_value", new Variable<>(100.0));
        resources.put("energy.max_value", new Variable<>(200.0));
        planet.put("resources", resources);

        List<Object> civilizations = new ArrayList<>();
        Map<String, Object> civilization = new HashMap<>();
        civilization.put("name", "PGH1");
        civilization.put("number", new Variable<>(100.0));
        civilization.put("birth_rate", new Variable<>(0.001));
        civilizations.add(civilization);

        planet.put("civilizations", civilizations);
    }
    
    private void servicesRegistration()
    {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());

        ServiceDescription sd = new ServiceDescription();
        sd.setType("getCoordinates");
        sd.setName(KeyBuilder.build(getLocalName(), "getCoordinates"));
        dfd.addServices(sd);

        sd = new ServiceDescription();
        sd.setType("getResources");
        sd.setName(KeyBuilder.build(getLocalName(), "getResources"));
        dfd.addServices(sd);
        
        sd = new ServiceDescription();
        sd.setType("getCivilizations");
        sd.setName(KeyBuilder.build(getLocalName(), "getCivilizations"));
        dfd.addServices(sd);
        
        sd = new ServiceDescription();
        sd.setType("getPlanet");
        sd.setName(KeyBuilder.build(getLocalName(), "getPlanet"));
        dfd.addServices(sd);

        try
        {
            DFService.register(this, dfd);
        }
        catch (FIPAException e)
        {
        }
    }

    private void servicesDeregistration()
    {
        try
        {
            DFService.deregister(this);
        }
        catch (Exception e)
        {
        }
    }
    
    private void initialization()
    {
        //Object[] args = this.getArguments();
    }

    protected void setup()
    {
        initialization();
        planetInitialization();
        messageHandlersInitialization();
        addBehaviour(new LoadBehaviour());
        addBehaviour(new CivilizationsBehaviour(planet));
        addBehaviour(new ResourceBehaviour(planet));
        addBehaviour(new ReceiverBehaviour(msgHandler));
        servicesRegistration();
    }

    protected void takeDown()
    {
        servicesDeregistration();
        System.exit(0);
    }
}
