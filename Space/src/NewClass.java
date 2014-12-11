
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import space.KeyBuilder;
import space.VariableDouble;

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
        System.out.println("Hello, wold!");
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
        Object[] str = { null, "hello", "", null, "bdddd" };
        System.out.println(KeyBuilder.build(str));
        System.out.println(KeyBuilder.build((Object[]) null));
         System.out.println(KeyBuilder.build("dd", "ff", "", null));
        
        String str1 = "44";
        String str2 = "44";
        System.out.println(str1.hashCode());
        System.out.println(str2.hashCode());
        
        Runtime r = Runtime.getRuntime();
        Process p = null;
        String cmd = "notepad";
        try
        {
            p = r.exec(cmd);
        }
        catch (Exception e)
        {
        }
    }
}
