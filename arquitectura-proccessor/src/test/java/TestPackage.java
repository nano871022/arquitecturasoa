import co.com.arquitectura.proccessor.groupedAnotation.FactoryGrouped;

public class TestPackage {
 public static void main(String...strings) {
	 Class fac = FactoryGrouped.class;
	 System.out.println(fac.getPackage().getName());
 }
}
