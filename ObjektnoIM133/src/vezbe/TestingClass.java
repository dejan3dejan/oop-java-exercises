package vezbe;

import geometry.Line;
import geometry.Point;

public class TestingClass {

	public static void main(String[] args) {
		
//		int prviBroj = 16;
//		int drugiBroj = 10;
//		String tekst = "Danasnji datum je: " + prviBroj + "."
//				+ drugiBroj + ".2024.";
//		System.out.println(tekst);
//		
//		int dnevnaTemperatura = 24;
//		int jutarnjaTemperatura = 7;
//		String testTekst = "Danas je suncano";
//		System.out.println("Razlika u temperaturi je: " + 
//		(dnevnaTemperatura - jutarnjaTemperatura));
//		System.out.println(testTekst + " sa " + dnevnaTemperatura
//				+ " stepeni celzijusa");
//		boolean tacnost = false;
//		System.out.println(testTekst + ":" + tacnost);
//		
//		double decimalniBroj = 40;
//		System.out.println(decimalniBroj);
//		int randomBroj = 40.40;
		
		
		//Zadatak 2
//		dnevnaTemperatura = 8;
//		jutarnjaTemperatura = 3;
//		
//		int razlika = dnevnaTemperatura - jutarnjaTemperatura;
//		if(razlika > 20) {
//			System.out.println("Obucite se slojevito");
//		}else {
//			System.out.println("Danas nas ocekuje prijatan raspon dnevne"
//					+ " i  jutarnje temperature");
//		}
//		if(dnevnaTemperatura > 30) {
//			System.out.println("Nanesite zastitu od UV zracenja na izlozene"
//					+ " deloze koze");
//			
//		}
//		else {
//			System.out.println("Danasnji UV indeks je nizak");
//		}
//		if(dnevnaTemperatura < 10) {
//			System.out.println("Toplo se obucite!");
//		}
//		
		//Petlje - Zadatak 3
//		int zbir = 0;
//		for(int brojac = 0; brojac<=200; brojac++) {
//			if(brojac % 2 == 0) {
//				zbir = zbir + brojac;
//			}
//		}
//		System.out.println(zbir);
//		
//		int brojac = 0;
//		zbir = 0;
//		while(brojac<=200) {
//			zbir += brojac;
//			brojac = brojac +2;
////			brojac += 2;
//		}
//		System.out.println(zbir);
		
//		Zadatak 4
//		int brojPrestupnihGodina = 0;
//		for(int godina = 2024; godina <=2100; godina+=4) {
//			if(godina == 2024) {
//				continue;
//			}
//			if(godina  % 4 == 0) {
//				if(godina % 100 == 0) {
//					if(godina % 400 == 0) {
//						brojPrestupnihGodina++;
//					}
//				}
//				else {
//					brojPrestupnihGodina++;
//				}
//			}
//		}
//		
//		System.out.println(brojPrestupnihGodina);
		
		
		/*
		 * =============================================
		 * =============================================
		 * 					VEZBE 3
		 */
		
		
//		int broj = 20;
		Point p1 = new Point();
//		System.out.println(p1);
//		System.out.println("Vrednost x koordinate je: " + p1.x);
//		System.out.println("Vrednost y koordinate je: " + p1.y);
//		System.out.println("Vrednost selected koordinate je: " + p1.selected);
//		p1.x = 50;
//		p1.y = 10;
//		p1.selected = true;
//		System.out.println("Vrednost x koordinate je: " + p1.x);
//		System.out.println("Vrednost y koordinate je: " + p1.y);
//		System.out.println("Vrednost selected koordinate je: " + p1.selected);
		
		Point p2 = new Point();
//		p1 = p2;
//		System.out.println("Vrednost x koordinate tacke p1 je: " + p1.x);
//		p1.x = 30;
//		p2.x = 10;
//		System.out.println("Vrednost x koordinate p2 je: " + p2.x);
//		System.out.println("Vrednost x koordinate p1 je: " + p1.x);
//		p1 = new Point();
//		p1.x = 20;	
		
//		System.out.println(p1.distance(p2));
		
		/* Od ove linije se koriste get i set metode */
		p1.setX(30);
//		System.out.println(p1);
		
		/*
		 * =============================================
		 * =============================================
		 * 					VEZBE 6
		 */

		
		
		System.out.println(p1 instanceof Object);
		Object obj = new Object();
		System.out.println(obj instanceof Point);
		
		System.out.println(p1);
		System.out.println(p2);
		p2.setX(20);
		System.out.println(p1.equals(p2));
	}
}
