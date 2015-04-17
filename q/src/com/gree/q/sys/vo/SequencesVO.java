package com.gree.q.sys.vo;

public class SequencesVO extends ValueObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String KEYN = "";

	private String HEAD = "";

	private long CVAL = -1;

	private int STEP = -1;

	private int RBAK = -1;

	private int SVAL = -1;

	private long MVAL = -1;

	public long getCVAL() {
		return CVAL;
	}

	public void setCVAL(long CVAL) {
		this.CVAL = CVAL;
	}

	public String getHEAD() {
		return HEAD;
	}

	public void setHEAD(String HEAD) {
		this.HEAD = HEAD;
	}

	public String getKEYN() {
		return KEYN;
	}

	public void setKEYN(String KEYN) {
		this.KEYN = KEYN;
	}

	public long getMVAL() {
		return MVAL;
	}

	public void setMVAL(long MVAL) {
		this.MVAL = MVAL;
	}

	public int getRBAK() {
		return RBAK;
	}

	public void setRBAK(int RBAK) {
		this.RBAK = RBAK;
	}

	public int getSTEP() {
		return STEP;
	}

	public void setSTEP(int STEP) {
		this.STEP = STEP;
	}

	public int getSVAL() {
		return SVAL;
	}

	public void setSVAL(int SVAL) {
		this.SVAL = SVAL;
	}
}
