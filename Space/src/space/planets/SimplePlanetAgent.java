package space.planets;

import space.common.SimpleReceiverBehaviour;
import space.common.Variable;
import space.common.KeyBuilder;
import space.common.SimpleACLMessageHandler;
import space.common.KeyValueList;
import space.common.MessageHandler;
import space.common.ACLMessageHandler;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import space.common.SimplePingMH;

public class SimplePlanetAgent extends Agent
{
    private Map<String, Object> planet;
    private List<Map<String, Object>> civilizations;
    private ACLMessageHandler msgHandler;
    
    private void messageHandlersInitialization()
    {
        KeyValueList list = new KeyValueList("type");
        KeyBuilder keyBuilder = new KeyBuilder(list);
        Map<String, MessageHandler> messageHandlers = new HashMap<>();
//        list.setValue("type", "text");
//        messageHandlers.put(KeyBuilder.build(list), new TextMassageHandler());
        list.setValue("type", "ping");
        messageHandlers.put(KeyBuilder.build(list), new SimplePingMH());
        msgHandler = new SimpleACLMessageHandler(keyBuilder, messageHandlers);
    }
    
    private void planetInitialization()
    {
        planet = new HashMap<>();

        planet.put("name", getLocalName());

        Map<String, Object> resources = new HashMap<>();
        resources.put("value", new Variable<>(1000.0));
        resources.put("max", new Variable<>(2000.0));
        planet.put("resources", resources);
        
        Map<String, Object> coordinates = new HashMap<>();
        coordinates.put("x", new Variable<>(2000.0));
        coordinates.put("y", new Variable<>(2000.0));
        planet.put("coordinates", coordinates);
        
        Map<String, Object> velocities = new HashMap<>();
        velocities.put("angular_velocity", new Variable<>(Math.PI / 180.0));
        planet.put("velocities", velocities);

        civilizations = new ArrayList<>();
        Map<String, Object> civilization = new HashMap<>();
        civilization.put("name", "Earthmen");
        civilization.put("number", new Variable<>(1000.0));
//        civilization.put("birth_rate", new Variable<>(0.001));
        civilizations.add(civilization);

        planet.put("civilizations", civilizations);
    }
    
    private void servicesRegistration()
    {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());

        ServiceDescription sd = new ServiceDescription();
        String type = "getCoordinates";
        sd.setType(type);
        sd.setName(KeyBuilder.build(getLocalName(), type));
        dfd.addServices(sd);

        sd = new ServiceDescription();
        type = "getResources";
        sd.setType(type);
        sd.setName(KeyBuilder.build(getLocalName(), type));
        dfd.addServices(sd);
        
        sd = new ServiceDescription();
        type = "getCivilizations";
        sd.setType(type);
        sd.setName(KeyBuilder.build(getLocalName(), type));
        dfd.addServices(sd);
        
        sd = new ServiceDescription();
        type = "getPlanet";
        sd.setType(type);
        sd.setName(KeyBuilder.build(getLocalName(), type));
        dfd.addServices(sd);
        
        sd = new ServiceDescription();
        type = "ping";
        sd.setType(type);
        sd.setName(KeyBuilder.build(getLocalName(), type));
        dfd.addServices(sd);

        try
        {
            DFService.register(this, dfd);
        }
        catch (Exception e)
        {
            System.err.println(getLocalName() + ": services not registered.");
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
        addBehaviour(new ResourcesBehaviour(planet));
        Random r = new Random(System.currentTimeMillis());
        int maxDelay = 1000;
        for (Map<String, Object> civilization : civilizations)
        {
            addBehaviour(new CivilizationBehaviour(planet, civilization, r.nextInt() % maxDelay));
        }
        addBehaviour(new SimpleReceiverBehaviour(msgHandler));
        addBehaviour(new MovingBehaviour(planet));
        //addBehaviour(new PingBehaviour());
        servicesRegistration();
    }

    protected void takeDown()
    {
        servicesDeregistration();
        System.exit(0);
    }
}
