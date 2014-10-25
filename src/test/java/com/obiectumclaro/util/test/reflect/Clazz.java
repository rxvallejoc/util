/**
 * 29/01/2013
 *
 * Propiedad de obiectumclaro
 */
package com.obiectumclaro.util.test.reflect;

/**
 * @author Fausto De La Torre <fausto.delatorre@obiectumclaroec.com>
 *
 */
public class Clazz {

	private String a;
	private Long b;
	private Subclazz c;
	private int d;
	private Integer e;
	private Float f;

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public Long getB() {
		return b;
	}

	public void setB(Long b) {
		this.b = b;
	}

	public Subclazz getC() {
		return c;
	}

	public void setC(Subclazz c) {
		this.c = c;
	}

	public String getE() {
		return "error";
	}

	public void setE(Integer e) {
		this.e = e;
	}

	public Float getF(boolean p) {
		return f;
	}

	public void setF(Float f, boolean p) {
		this.f = f;
	}

	public Float getF() {
		return f;
	}

	public void setF(Float f) {
		this.f = f;
	}
}
