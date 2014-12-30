## SeekBarStep

SeekBarStep is seekbar that you can set step, min, max

**HOW TO USE:**
 mSeekBarStep = (SeekBarStep) findViewById(R.id.mySeekBarStep);



**Set min, max,step**
 try {
 	mSeekBarStep.setMaxMin(100, 35, 1);
  }catch (SeekBarStepException e){
  }
  
  
  **Set progress**


 try {
   mSeekBarStep.setCurrentProgress(10);
  }catch (SeekBarStepException e){
 }
 
**Listener**


 mSeekBarStep.setOnSeekBarStepChangeListener(new SeekBarStep.OnSeekBarStepChangeListener() {
    @Override
    public void onProgressChanged(float progress) {
        int value = (int) progress;
        mTextView.setText("" + value);
    }

    @Override
    public void onStartTrackingTouch(float progress) {

    }

    @Override
    public void onStopTrackingTouch(float progress) {

    }
});
