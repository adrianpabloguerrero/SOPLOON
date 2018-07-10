package main;

import java.util.*;

import base.Musica;
import base.Pista;
import base.PlayListSimple;

import busquedas.*;
import extraccion.*;

public class Main {

	public static void mostrar_resultado(Vector <Pista> P){
		if(P.isEmpty()){
			System.out.println("Sin resultados");

		}
		else
		{
			for(Enumeration<Pista> E = P.elements(); E.hasMoreElements();){
					E.nextElement().mostrar();
			}
		}
	}
	
	public static void main(String[] args) {
		//SE CREA LA COLECCION--------------------------------------------------------------------------------------------------------
		PlayListSimple coleccion = new PlayListSimple("Coleccion");
		//--------------------------------------------------------------------------------------------------------
		//PARTE 1 - PUNTO 1 -------------------------------------------------------------------------------------------------------------
		
		//CREO LAS PISTAS
		Pista p1 = new Pista(1, 311, "Bersuit Vergarabat", "De la cabeza", "Rock nacional", "", 2002, "El tiempo no para");
		Pista p2 = new Pista(2, 290, "Bersuit Vergarabat", "De la cabeza", "Rock nacional", "", 2002, "Mi caramelo");
		Pista p3 = new Pista(3, 408, "LMFAO", "Sorry for party rocking", "Electro pop", "", 2011, "Party rock anthem");
		Pista p4 = new Pista(4, 421, "LMFAO", "Sorry for party rocking", "Electro pop", "", 2011, "Sorry for party rocking");
		Pista p5 = new Pista(5, 255, "Coldplay", "X&Y", "Rock alternativo", "", 2005, "Fix you");
		Pista p6 = new Pista(6, 455, "Coldplay", "X&Y", "Rock alternativo", "", 2005, "Speed of sound");
		Pista p7 = new Pista(7, 320, "Coldplay", "Viva la vida", "Rock alternativo", "", 2008, "Viva la vida");
		Pista p8 = new Pista(8, 360, "U2", "The Joshua Tree", "Rock", "", 1987, "Whit or whitout you");
		Pista p9 = new Pista(9, 355, "U2", "How to Dismantle an Atomic Bomb", "Rock", "", 2004, "Vertigo");
		Pista p10 = new Pista(10, 284, "U2", "How to Dismantle an Atomic Bomb", "Rock", "", 2004, "City of blinding lights");
		Pista p11 = new Pista(11, 438, "El Indio Solari", "Pajaritos, bravos muchachitos", "Rock nacional","", 2013, "A la luz de la luna");
		Pista p12 = new Pista(12, 258, "Patricio rey y sus redonditos de ricota", "Lobo Suelto, Cordero atado", "Rock nacional","", 1993, "Yo Canibal");
				
		//---------------------------------------------------------------------------------------------------------------------
		//PARTE 1 - PUNTO 2 -------------------------------------------------------------------------------------------------------------
		PlayListSimple clasicosDelRock = new PlayListSimple("Clasicos del rock");
		coleccion.addElemento(clasicosDelRock);
		clasicosDelRock.addElemento(p1);
		clasicosDelRock.addElemento(p2);
		clasicosDelRock.addElemento(p8);
		clasicosDelRock.addElemento(p9);
		clasicosDelRock.addElemento(p10);
		clasicosDelRock.addElemento(p12);	
				
		PlayListSimple loMejor = new PlayListSimple("Lo mejor");
		coleccion.addElemento(loMejor);
		loMejor.addElemento(p3);
		loMejor.addElemento(p4);
		loMejor.addElemento(p7);
		loMejor.addElemento(p12);
		
		PlayListSimple coldplay = new PlayListSimple("Coldplay");
		coleccion.addElemento(coldplay);
		coldplay.addElemento(p5);
		coldplay.addElemento(p6);
		coldplay.addElemento(p7);
		
		PlayListSimple elIndio = new PlayListSimple("El Indio");
		coleccion.addElemento(elIndio);
		elIndio.addElemento(p12);
		elIndio.addElemento(p11);
		
		//---------------------------------------------------------------------------------------------------------------------
		//PARTE 1 - PUNTO 3 -------------------------------------------------------------------------------------------------------------
		System.out.println("Parte 1 Punto 3 ------------------------------------------------------------------------------------------");
		clasicosDelRock.mostrar();
		loMejor.mostrar();
		coldplay.mostrar();
		System.out.println();
		
		//---------------------------------------------------------------------------------------------------------------------
		//PARTE 1 - PUNTO 4 -------------------------------------------------------------------------------------------------------------
		System.out.println("Parte 1 Punto 4 ------------------------------------------------------------------------------------------");
		clasicosDelRock.mostrarDuracion();
		loMejor.mostrarDuracion();
		coldplay.mostrarDuracion();
		elIndio.mostrarDuracion();
		System.out.println();
		
		System.out.println("------------------------------------------------PARTE 2------------------------------------------------");
		System.out.println();
		
		//---------------------------------------------------------------------------------------------------------------------
		//PARTE 2 - PUNTO 1 A-------------------------------------------------------------------------------------------------------------
		System.out.println("Parte 2 Punto 1A ------------------------------------------------------------------------------------------");
		CriterioIntervalo mayorDuracion = new CriterioIntervalo(new Duracion(), 400, CriterioIntervalo.MAYOR);
		System.out.println("Pistas cuya duracion sea mayor a 400");
		Vector<Pista> resultado1A = coleccion.busqueda(mayorDuracion);
		mostrar_resultado(resultado1A);

		System.out.println();
		
		//PARTE 2 - PUNTO 1 B-------------------------------------------------------------------------------------------------------------
		System.out.println("Parte 2 Punto 1B ------------------------------------------------------------------------------------------");
		CriterioParcial rock = new CriterioParcial(new Genero(), "rOCk");
		System.out.println("Pistas cuyo genero contiene 'Rock'");
		Vector<Pista> resultado1B = coleccion.busqueda(rock);
		mostrar_resultado(resultado1B);
		System.out.println();
		
		//PARTE 2 - PUNTO 1 C---------------------------------------------------------------------------------------------------------------
		System.out.println("Parte 2 Punto 1C ------------------------------------------------------------------------------------------");
		CriterioParcial nomRock = new CriterioParcial(new Nombre(), "rock");
		CriterioParcial lmfao = new CriterioParcial(new Interprete(), "LMFAO");
		CriterioNot critNot = new CriterioNot(lmfao);
		CriterioAnd critAnd = new CriterioAnd(nomRock, critNot);
		System.out.println("Pistas cuyo nombre contiene 'rock' y el interprete NO es 'LMFAO'");
		Vector<Pista> resultado1C = coleccion.busqueda(critAnd);
		mostrar_resultado(resultado1C);		
		System.out.println();
		
		//PARTE 2 - PUNTO 1 D---------------------------------------------------------------------------------------------------------------
		System.out.println("Parte 2 Punto 1D ------------------------------------------------------------------------------------------");
		CriterioIntervalo critFecha = new CriterioIntervalo(new Anio(), 2006, CriterioIntervalo.MAYOR);
		CriterioAnd critAnd2 = new CriterioAnd(rock, critFecha);
		CriterioParcial cP = new CriterioParcial(new Interprete(), "Coldplay");
		CriterioAnd critAnd3 = new CriterioAnd(rock, cP);
		CriterioOr critOr = new CriterioOr(critAnd2, critAnd3);
		System.out.println("pistas cuyo genero contenga 'rock' y cuya fecha sea mayor a '2006', o cuyo genero contenga 'rock' y cuyo interprete sea 'coldplay'");
		Vector<Pista> resultado1D = coleccion.busqueda(critOr);
		mostrar_resultado(resultado1D);				
		System.out.println();
		
		
		//---------------------------------------------------------------------------------------------------------------------
		//PARTE 2 PUNTO 2 -------------------------------------------------------------------------------------------------------------
		System.out.println("Parte 2 Punto 2------------------------------------------------------------------------------------------");
		Pista p13 = new Pista(13, 365, "Coldplay", "Mylo Xyloto", "Rock alternativo","", 2011, "Paradise");
		coleccion.addElemento(p13);
		
		System.out.println("Pistas cuya duracion sea mayor a 400");
		Vector<Pista> resultado21A = coleccion.busqueda(mayorDuracion);
		mostrar_resultado(resultado21A);
		System.out.println();
		
		System.out.println("Pistas cuyo genero contiene 'Rock'");
		Vector<Pista> resultado21B = coleccion.busqueda(rock);
		mostrar_resultado(resultado21B);				
		System.out.println();
		
		System.out.println("Pistas cuyo nombre contiene 'rock' y el interprete NO es 'LMFAO'");
		Vector<Pista> resultado21C = coleccion.busqueda(critAnd);
		mostrar_resultado(resultado21C);
		System.out.println();
		
		System.out.println("pistas cuyo genero contenga 'rock' y cuya fecha sea mayor a '2006', o cuyo genero contenga 'rock' y cuyo interprete sea 'coldplay'");
		Vector<Pista> resultado21D = coleccion.busqueda(critOr);
		mostrar_resultado(resultado21D);
		System.out.println();
		//---------------------------------------------------------------------------------------------------------------------
				
		
		System.out.println("termino------------------------------------------------------------------------------------------");
		
	}
}
