package archiveModule;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer<T> {
	public byte[] serialize(T entry)
            throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(entry);
		oos.flush();
		return baos.toByteArray();
	}
 
	public T deserialize(byte[] byteArray)
            throws IOException, ClassNotFoundException {
        T entry;
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(byteArray));
		entry = (T)ois.readObject();
		return entry;
	}
}
