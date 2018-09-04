package com.example.mark.myapplication;



//import static java.lang.Math.sin;
import static java.lang.Math.*;

import java.util.ArrayList;
public class WaveGenerator {

    public static ArrayList<Float> getSine(float amp,float freq,float time,float incr){
        float temp;
        int ctr;
        float l=time/incr;
        ArrayList<Float> s=new ArrayList<Float>();
        
        for(ctr=0;ctr< l;ctr++) {
            temp= (float) (2*PI*(ctr*incr)*freq);
            s.add((float)(amp*sin(temp)));
        }
        return s;

    }
    public static ArrayList<Float>getSquare(float amp,float freq,float time,float incr){
    	float temp;
        int ctr;
        float l=time/incr;
        ArrayList<Float> s=new ArrayList<Float>();
        float ft=1/freq;
        float curr=amp;
        for(ctr=0;ctr< l;ctr++) {
        	s.add(WaveGenerator.square(amp,freq,ctr*incr));
        }
    	
		return s;
    }
    public static float square(float amp,float f,float t) {
    	int k=10;
    	int ctr;
    	float sum=0;
    	for(ctr=1;ctr<=k;ctr++) {
    		sum=(float) (sum+(sin(2*PI*((2*ctr)-1)*f*t))/((2*ctr)-1));
    	}
    	return (float) ((sum*4)/PI)*amp;
 //   	return (float) (amp*(4/PI)*(sin(2*PI*f*t)+(1/3)*sin(6*PI*f*t)+(1/5)*sin(10*PI*f*t))+(1/5)*sin(10*PI*f*t)));
    }
    public static ArrayList<Float>getTriangle(float amp,float freq,float time,float incr){
    	float temp;
        int ctr;
        float l=time/incr;
        ArrayList<Float> s=new ArrayList<Float>();
        float ft=1/freq;
        float curr=amp;
        System.out.println("Triangle");
        for(ctr=0;ctr< l;ctr++) {

        	s.add(WaveGenerator.triangle(amp,freq,ctr*incr));
        }
    	
		return s;
    }
    public static float triangle(float amp,float f,float t) {
    	int k=10;
    	int ctr;
    	float sum=0;
    	float n;
    	//n=(float) (1/(sqrt(f)));
    	int odd;
    	for(ctr=1;ctr<k;ctr=ctr+2){
    		n=ctr;
    		sum=sum+(float) (pow(-1,(n-1)/2)*pow(n,-2)*(sin(n*t*(2*f)*PI)));
    		//sum=(float) (pow(-1,ctr)*pow(n,-2)*(sin(n*t)));
    		
    	}
    	sum=(float) (sum*8/pow(PI,2))*amp;
    	return sum;
    }
}
