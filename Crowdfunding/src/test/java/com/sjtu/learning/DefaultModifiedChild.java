package com.sjtu.learning;

public class DefaultModifiedChild extends DefaultModified{
	public static void main(String [] argv){
		DefaultModifiedChild dmc = new DefaultModifiedChild();
		System.out.println("********Modifier test begin*********");
		System.out.println(dmc.i_default);
		System.out.println(dmc.j_default);
		
		System.out.println(dmc.i_public);
		System.out.println(dmc.j_public);
		
/*		System.out.println(dmc.i_private);
		System.out.println(dmc.j_private);*/
		
		System.out.println(dmc.i_protected);
		System.out.println(dmc.j_protected);


		System.out.println("********Modifier test end*********");
	}
}
