package com.bombomstudio.ngoctien.seekbarstep;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SeekBarStep extends SeekBar implements OnSeekBarChangeListener{
	
	private float mMax = 100;
	private float mMin = 0;
	private float mStep = 1;
	private int MAX = 100;
	
	public interface OnSeekBarStepChangeListener {
       
        void onProgressChanged(float progress);
    
       
        void onStartTrackingTouch(float progress);
       
        void onStopTrackingTouch(float progress);
    }

	private OnSeekBarStepChangeListener mOnSeekBarStepChangeListener = null;
	
	public void setOnSeekBarStepChangeListener(OnSeekBarStepChangeListener l){
		mOnSeekBarStepChangeListener = l;
	}
	
	public SeekBarStep(Context context) {
        this(context, null);
        this.setOnSeekBarChangeListener(this);
        try {
            setMaxMin(mMax, mMin, mStep);
        }catch (SeekBarStepException e){

        }

    }
    
    public SeekBarStep(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.seekBarStyle);
        this.setOnSeekBarChangeListener(this);
        try {
            setMaxMin(mMax, mMin, mStep);
        }catch (SeekBarStepException e){

        }
    }

    
    public SeekBarStep(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setOnSeekBarChangeListener(this);
        try {
            setMaxMin(mMax, mMin, mStep);
        }catch (SeekBarStepException e){

        }
    }

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
		// TODO Auto-generated method stub
		float progress1 = progress;
		float d = mMax - mMin;
		float x1 = progress1*d/MAX;
		x1+= mMin;
		if(mOnSeekBarStepChangeListener != null){
			mOnSeekBarStepChangeListener.onProgressChanged(x1);
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		float progress = seekBar.getProgress();
		float d = mMax - mMin;
		float x1 = progress*d/MAX;
		x1+= mMin;
		if(mOnSeekBarStepChangeListener != null){
			mOnSeekBarStepChangeListener.onStartTrackingTouch(x1);
		}
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		float progress = seekBar.getProgress();
		float d = mMax - mMin;
		float x1 = progress*d/MAX;	
		x1+= mMin;
		if(mOnSeekBarStepChangeListener != null){
			mOnSeekBarStepChangeListener.onStopTrackingTouch(x1);
		}
	}



	public void setMaxMin(float max, float min, float step)throws SeekBarStepException{
		if(step <= 0){
            throw new SeekBarStepException("step = " + step + " < 0");
//			return false;
		}
		float value = (max - min);
		if(value <= 0){
            throw new SeekBarStepException("max = " + max + " < min = "  + min);

		}
		
		if(value <= step){
            String str = "Distance of min & max is less than step.\n";
            str += "Distance = " + value;
            str += "step = " + step;
            throw new SeekBarStepException(str);
		}
		
		float temp = (max - min)/step;
		MAX = (int) temp;
		mMax = max;
		mMin = min;
		mStep = step;
		this.setMax(MAX);

	}
	
	public void setCurrentProgress(float progress)throws SeekBarStepException{
//		Log.e("test1", "progress: " + progress);
		if(progress >= mMin && progress <= mMax){
			float d = mMax - mMin;
			float x = (progress-mMin)*MAX/d;
			int value = (int) x;
//			value -= mMin;
//			Log.e("test1", "value: " + value);
//			Log.e("test1", "MAX: " + MAX);
			this.setProgress(value);
		}else{
            throw new SeekBarStepException("progress = " + progress + " out of range min-max");
        }
	}
	
	public float getSeekBarStepMax(){
		return mMax;
	}
	
	public float getSeekBarStepMin(){
		return mMin;
	}
	
	public float getSeekBarStepStep(){
		return mStep;
	}

}
