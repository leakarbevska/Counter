/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package counter1.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leya
 */
public class StorageHelper {

    private static final String FILE_FORMAT = "%s.txt";
   
    public static void asyncSaveToStorage(Object object) {
        new Thread(() -> {
            try {
                saveToStorage(object);
            } catch (IOException ex) {
                Logger.getLogger(StorageHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    public static void saveToStorage(Object object) throws IOException {
        Files.write(Paths.get(String.format(FILE_FORMAT, object.getClass().getName())), serialize(object));
    }

    public static Object loadFromStorage(Class classObject) {
        try {
            return deserialize(Files.readAllBytes(Paths.get(String.format(FILE_FORMAT, classObject.getName()))));
        } catch (IOException ex) {
            return null;
        }
    }

    private static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream byteOutputStream = new ObjectOutputStream(outputStream);
        byteOutputStream.writeObject(obj);
        return outputStream.toByteArray();
    }

    public static Object deserialize(byte[] data) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        try {
            return objectInputStream.readObject();
        } catch (ClassNotFoundException ex) {
            return null;
        }
    }
}
