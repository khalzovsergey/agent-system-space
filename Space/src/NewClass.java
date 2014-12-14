
import jade.lang.acl.ACLMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import space.common.Copier;
import space.common.Variable;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sergey
 */
public class NewClass
{
    public static void main(String[] args)
    {
        Map<String, Object> planet = new HashMap<>();


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

        List<Object> civilizations = new ArrayList<>();
        Map<String, Object> civilization = new HashMap<>();
        civilization.put("name", "Earthmen");
        civilization.put("number", new Variable<>(1000.0));
//        civilization.put("birth_rate", new Variable<>(0.001));
        civilizations.add(civilization);

        planet.put("civilizations", civilizations);
        
        
        Map<String, Object> createShipMsg;
        createShipMsg = new HashMap<>();
        createShipMsg.put("type", "createShip");
        Map<String, Object> content = new HashMap<>();
        content.put("planet", planet);
        content.put("civilization", civilization);
        content.put("shipType", "passenger_transport");
        createShipMsg.put("content", content);
        
        ACLMessage msg = new ACLMessage(ACLMessage.UNKNOWN);
        createShipMsg.put("time", System.currentTimeMillis());
        try
        {
            msg.setContentObject((Serializable)createShipMsg);
        }
        catch (Exception e)
        {
            System.err.println("Not serialized.");
            System.err.println(e);
            //System.err.println(Arrays.toString(e.getStackTrace()));
        }
        try
        {
            Object o = Copier.copy((Serializable)createShipMsg);
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
        
        int i;
        
//        java.io.ByteArrayOutputStream o = new ByteArrayOutputStream();
//        java.io.ObjectInputStream oi = new ObjectInputStream(null);
        
//        Object obj = new HashMap<String, String>();
//        Map<String, Object> ms = (Map<String, Object>)obj;
//        Map<Object, Object> mmmm = (Map<Object, Object>)obj;
//        mmmm.put(new Double(99.0), new Double(88.0));
//        Object str;
//        str = ms.get(new Double(99.0));
//        List<String> l = new ArrayList();
//        List y = new ArrayList<Double>();
//        y.add(new Double(99.0));
//        l = y;
//        System.out.println(((Object)l.get(0)).toString());
//        //Map<String, Object> m = new HashMap();
//        //l.add(m);
//        l.add("dsafsdf");
//        List<Map<String, Object>> k;
//        //k = (List<Map<String, Object>>)l;
//        Object o = l;
//        k = (List<Map<String, Object>>)o;
//        
//        
//        //m = k.get(0);
//        //m = k.get(1);
//        List<Double> d = new ArrayList();
//        d.add(Double.NaN);
//        Double dd = d.get(0);
//        
//        //dd = (Double)o;
//        
//        List<Integer> i = new ArrayList<Integer>();
//        System.out.println(List.class.equals(List.class));
//        System.out.println(d.getClass().equals(i.getClass()));
//        //m.put(new Object(), new Object());
//        //String s = (String)o;
//        System.out.println(l.getClass().getName());
        //List<Integer> lll = new NewClass1<>();
        
        
        
        
//        Map<String, Double> coord = new HashMap<String, Double>();
//        coord.put("x", Double.valueOf(123));
//        coord.put("x.x", Double.valueOf(44));
//        Double d = coord.get("x");
//        System.out.println("x = " + d);
//        d = coord.get("x.x");
//        System.out.println("x.x = " + d);
//        d = coord.get("x.y");
//        System.out.println(d == null);
//        VariableDouble v = new VariableDouble();
        //v.value
        //System.currentTimeMillis();
//        java.util.
        
    }
}
