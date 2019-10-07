package archiveModule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.TreeMap;

public class ManageAuto {
	private final String FileDati="FileAuto.dat";
	private final String FileIndice="FileIndex.dat";
	
	private FileUtil<Auto>  fauto=new FileUtil<>(Auto.BLEN);
	private Iterator<String> iterator=null;
	private char mode='A';
	
	private TreeMap<String, Long> mindex=new TreeMap<String, Long>();
	
	public ManageAuto() {
		fauto.FileOpen(FileDati);
		indexRead(FileIndice);
	}
	
	public void compressArch() {
		FileUtil<Auto>  fTempAuto=new FileUtil<>(Auto.BLEN);
		File temp = new File("TempAuto.dat");
		temp.delete();
		fTempAuto.FileOpen(temp);
		TreeMap<String, Long> tindex=new TreeMap<String, Long>();
		for (Auto a=this.getFirst('V'); a!=null; a=this.getNext()) {
			writeAuto(tindex, a, fTempAuto); 
		}
		mindex=tindex;
		indexWrite(FileIndice);
		fauto.FileClose();
		fTempAuto.FileClose();
		File f=new File(FileDati);
		f.delete();
		temp.renameTo(f);
		fauto.FileOpen(FileDati);
	}
	
	public void CloseALL() {
		fauto.FileClose();
		indexWrite(FileIndice);
	}
	
	
	public void addAuto(Auto a) {
		
		if (mindex.get(a.getTarga())!=null) {
			System.out.println("Targa gia presente");
		} else {
			writeAuto(mindex, a,fauto);
		}
		indexWrite(FileIndice);
	}
		
		
	public Auto getAuto(String targa)	{
		Auto a=null;
		
		Long posm=mindex.get(targa);
		
		if (posm!=null) {
			if (fauto.readStatusAT(posm)==1) { 
				a=fauto.readObj(posm);
			}
		}
		return a;
	}
	
	public void deleteAuto(String targa)	{
		if (mindex.containsKey(targa)) {
			long position=mindex.get(targa);
			if (position>=0) {
				fauto.setStatusAT(0,position);
			}
		}
	}
	
	public void undeleteAuto(String targa)	{
		long position=mindex.get(targa);
		if (position>=0) {
			fauto.setStatusAT(1,position);
		}
	}

	public Auto getNext() {
		long position;
		if (iterator !=null) {
			while(iterator.hasNext()) {
				String targa = iterator.next();
				position=mindex.get(targa);
				if (('V' == mode && fauto.readStatusAT(position)==1) ||
				    ('D' == mode && fauto.readStatusAT(position)==0) || 
            	    ('A' == mode ) ) {
            	   return fauto.readObj(position);
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param mode: A=all, V=valid, D=delete
	 */

	public Auto getFirst(char mode) {
		this.mode=mode;
		this.iterator =  mindex.keySet().iterator();
		return getNext();
	}
	
	private void writeAuto(TreeMap<String, Long>ix, Auto a, FileUtil<Auto> f) {
		long posa=f.appendObj(a);
		ix.put(a.getTarga(),posa);
	}
	
	private void indexWrite(String filename) {
		try {    
			//Saving of object in a file 
	         FileOutputStream file = new FileOutputStream(filename); 
	         ObjectOutputStream out = new ObjectOutputStream(file); 
	              
	         // Method for serialization of object 
	         out.writeObject(mindex); 
	              
	         out.close(); 
	         file.close(); 
	    } catch(IOException ex) { 
	       ex.printStackTrace();; 
	    } 
	}
	

	private void indexRead(String filename) {
		try {    
			// Reading the object from a file 
			File ixfile=new File(filename);
			if (ixfile.canRead()) {
				FileInputStream file = new FileInputStream(ixfile); 
				ObjectInputStream in = new ObjectInputStream(file); 
	           
				// Method for deserialization of object 
				mindex = (TreeMap<String,Long>)in.readObject(); 
	           
				in.close(); 
				file.close();
			}
	     } catch(IOException ex) { 
	         ex.printStackTrace();
	     } catch(ClassNotFoundException ex) { 
	         ex.printStackTrace(); 
	     } 
	} 

}
