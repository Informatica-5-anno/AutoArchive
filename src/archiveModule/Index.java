package archiveModule;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Index extends Serializer<Index> implements Serializable, Comparable<Index> {
	private String targa;
	private long position;
	
	public static final int BLEN=95;
	
	public Index(String targa, long position) {
		super();
		setTarga(targa);
		this.position=position;
	}

	public Index() {
		super();
	}
	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}

	@Override
	public int compareTo(Index o) {
		return this.getTarga().compareTo(o.getTarga());
	}
	
}
