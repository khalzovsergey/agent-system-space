
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



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
        Object obj = new HashMap<String, String>();
        Map<String, Object> ms = (Map<String, Object>)obj;
        Map<Object, Object> mmmm = (Map<Object, Object>)obj;
        mmmm.put(new Double(99.0), new Double(88.0));
        Object str;
        str = ms.get(new Double(99.0));
        List<String> l = new ArrayList();
        List y = new ArrayList<Double>();
        y.add(new Double(99.0));
        l = y;
        System.out.println(((Object)l.get(0)).toString());
        //Map<String, Object> m = new HashMap();
        //l.add(m);
        l.add("dsafsdf");
        List<Map<String, Object>> k;
        //k = (List<Map<String, Object>>)l;
        Object o = l;
        k = (List<Map<String, Object>>)o;
        
        
        //m = k.get(0);
        //m = k.get(1);
        List<Double> d = new ArrayList();
        d.add(Double.NaN);
        Double dd = d.get(0);
        
        //dd = (Double)o;
        
        List<Integer> i = new ArrayList<Integer>();
        System.out.println(List.class.equals(List.class));
        System.out.println(d.getClass().equals(i.getClass()));
        //m.put(new Object(), new Object());
        //String s = (String)o;
        System.out.println(l.getClass().getName());
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
