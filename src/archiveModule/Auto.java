package archiveModule;

import java.io.Serializable;

public class Auto extends Serializer<Auto> implements Serializable  {

	private String targa;
	private String descrizione;
	private double km;
	private int anno;
	private double prezzo;
	
	public static final int BLEN=396;
	
	public Auto(String targa, String descrizione, double km, int anno, double prezzo) {
		super();
		setTarga(targa);
		setDescrizione(descrizione);
		this.km = km;
		setAnno(anno);
		this.prezzo = prezzo;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public String getDescrizione() {
		return descrizione.trim();
	}
	public void setDescrizione(String descrizione) {
		
		StringBuilder sb = new StringBuilder();
		for (int i = descrizione.length(); i < 255; i++) {
		    sb.append(' ');
		}
		if (descrizione.length()>255)
			this.descrizione=descrizione.substring(0, 255);
		else 
			this.descrizione=descrizione+sb;
	}
	
	public double getKm() {
		return km;
	}
	public void setKm(double km) {
		this.km = km;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public int getAnno() {
		return anno;
	}
	
	public void setAnno(int anno) {
		this.anno=anno;
	}
	

	@Override
	public String toString() {
		return "Auto [targa=" + targa + ", descrizione=" + getDescrizione() + ", km=" + km + ", anno=" + anno + ", prezzo="
				+ prezzo + "]";
	}
}
