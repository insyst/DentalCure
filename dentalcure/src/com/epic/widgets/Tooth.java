package com.epic.widgets;

import java.awt.Point;

public class Tooth {
	Point stpnt,endpoint,midpnt,depthPt,step1,step2;
	String toothNo;
	int width,depth;
	public Point getStep1() {
		return step1;
	}

	public int getWidth() {
		return width;
	}

	public Point getDepthPt() {
		return depthPt;
	}

	public void setDepthPt(Point depthPt) {
		this.depthPt = depthPt;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public void setStep1(Point step1) {
		this.step1 = step1;
	}

	public Point getStep2() {
		return step2;
	}

	public void setStep2(Point step2) {
		this.step2 = step2;
	}

	public Point getStpnt() {
		return stpnt;
	}

	public String getToothNo() {
		return toothNo;
	}

	public void setToothNo(String toothNo) {
		this.toothNo = toothNo;
	}

	public int getDepth() {
		return depth;
	}

	public void setStpnt(Point stpnt) {
		this.stpnt = stpnt;
	}

	public Point getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(Point endpoint) {
		this.endpoint = endpoint;
	}

	public Point getMidpnt() {
		return midpnt;
	}

	public void setMidpnt(Point midpnt) {
		this.midpnt = midpnt;
	}

}
