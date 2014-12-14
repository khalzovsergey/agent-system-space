/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Sergey
 */
public class Copier
{
    public static final Object copy(Object obj) throws IOException, ClassNotFoundException
    {
        ByteArrayOutputStream c = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(c);
        oos.writeObject(obj);
        oos.flush();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(c.toByteArray()));
        obj = ois.readObject();
        return obj;
    }
    
//    public static final byte[] getByteSequence(Serializable s) throws IOException
//    {
//        ByteArrayOutputStream c = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(c);
//        oos.writeObject(s);
//        oos.flush();
//        return c.toByteArray();
//    }
}
