package archiveModule;

public class TestModule {

	public static void main(String[] args) {
		
		ManageAuto ma=new ManageAuto();
	    System.out.println("Add auto");
		ma.addAuto(new Auto("EF896ML", "Lancia Phedra", 102000.0, 199910, 5000.0));
		ma.addAuto(new Auto("DX708ED", "Hyundai Matrix", 109000.0, 199908, 500.0));
		ma.addAuto(new Auto("FK456BP", "Ferrari testa rossa", 1.0, 201910, 150000.0));
		ma.addAuto(new Auto("KK456BP", "Renault", 1.0, 201910, 150000.0));
		
		System.out.println("Cercola!!");
		System.out.println(ma.getAuto("DX708ED"));
		
		System.out.println("--------- Tutte le auto --------------");
		getAllAuto(ma, 'A');
		
		ma.deleteAuto("JK456BP");
		ma.deleteAuto("KK456BP");
		ma.deleteAuto("FK456BP");
		System.out.println("--------- Dopo cancellazione --------------");
		getAllAuto(ma,'V');
		
		System.out.println("--------- Solo le cancellate --------------");
		getAllAuto(ma,'D');
		
		ma.compressArch();
		System.out.println("--------- Dopo compressione --------------");
		getAllAuto(ma,'V');
		
		System.out.println("--------- Solo le cancellate --------------");
		getAllAuto(ma,'D');
	}
	public static void getAllAuto(ManageAuto ma, char mode) 	{
		Auto a=null;
		for (a=ma.getFirst(mode);a!=null;a=ma.getNext()) {
			System.out.println(a);
		}    
    }

}
