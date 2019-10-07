package archiveModule;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileUtil<T extends Serializer> {

	private RandomAccessFile random;
	public static final int FSTART=0;
	private int blen;
	
	Serializer<T> s=new Serializer<T>();
	
	public FileUtil() {
		super();
	}
	
	public FileUtil(int blen) {
		super();
		this.blen=blen+4;
	}
	
	public void FileOpen(File f) {		
		try {
			random = new RandomAccessFile(f, "rw");
			// gets length of file
    	} catch (IOException e) {
    		e.printStackTrace();
    	} 
	}
	public void FileOpen(String Filename) {		
		FileOpen(new File(Filename));
	}

	public long writeObj(T a) {
		long position=-1;
		try {
			position=random.getFilePointer();
			// record status
			random.writeInt(1);
			// write record
	        byte[] obj= a.serialize(a);
	        // System.out.println(obj.length);
			random.write(obj);
		} catch (IOException e) {
    		e.printStackTrace();
    	} 
		return position/blen;
	}
	
	public void seekPos(long position) {
		try {
			random.seek(position*blen);
		} catch (IOException e) {
    		e.printStackTrace();
    	} 
	}
	
	public long appendObj(T a) {
		long position=-1;
		try {
			random.seek(random.length());
			position=writeObj(a); 
		} catch (IOException e) {
	    	e.printStackTrace();
	    }
		return position;
	}	
	
	public void setStatusAT(int status, long position) {
		seekPos(position);
		try {
			random.writeInt(status);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public int readStatus() {
		try {
			return random.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int readStatusAT(long position) {
		seekPos(position);
		return readStatus();
	}
	
	public T readObj() {
		int buflen=blen-4;
		byte[] buf=new byte[buflen];
	    
		try {
			random.readInt();
			int letti=random.read(buf);
			if (buflen==letti) {
				return (T)(new Serializer()).deserialize(buf);
			} else { 
				return null;
			}
		} catch (EOFException e) {
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public long getFileLenght() {
		try {
			return random.length()/blen;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public T readObj(long position) {
		seekPos(position);
		return readObj();
	}
			
	public void FileClose() {
		try {
			random.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
