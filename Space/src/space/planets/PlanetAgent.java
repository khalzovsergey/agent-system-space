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

public class PlanetAgent extends Agent
{
    private Map<String, Object> planet;
    private Map<String, MessageHandler> messageHandlers;
    
    private void messageHandlersInitialization()
    {
        messageHandlers = new HashMap<>();
        messageHandlers.put(KeyBuilder.build("coordinates"), null);
    }
    
    private void planetInitialization()
    {
        planet = new HashMap<>();

        planet.put("name", getLocalName());

        Map<String, VariableDouble> resources = new HashMap<>();
        resources.put("energy.current_value", new VariableDouble(100));
        resources.put("energy.max_value", new VariableDouble(200));
        planet.put("resources", resources);

        List<Object> civilizations = new ArrayList<>();
        Map<String, Object> civilization = new HashMap<>();
        civilization.put("name", "PGH1");
        civilization.put("number", new VariableDouble(100));
        civilization.put("birth_rate", new VariableDouble(0.001));
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
        addBehaviour(new ReceiverBehaviour(messageHandlers));
        servicesRegistration();
    }

    protected void takeDown()
    {
        servicesDeregistration();
        System.exit(0);
    }
}
