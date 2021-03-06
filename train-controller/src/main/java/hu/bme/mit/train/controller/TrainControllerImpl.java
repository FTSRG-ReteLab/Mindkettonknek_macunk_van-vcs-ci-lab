package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;

import java.util.Timer;
import java.util.TimerTask;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	public Tachograph tachograph = new Tachograph();
	//A change
	//B change

	@Override
	public void followSpeed() {
		System.out.println("Change");
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}

		enforceSpeedLimit();
	}

	@Override
	public boolean isEmpty() {
		return tachograph.table.isEmpty();
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();

	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;
		tachograph.table.put(System.currentTimeMillis() , joystickPosition, referenceSpeed);

		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				followSpeed();
			}
		};

		Timer timer = new Timer("Timer");
		timer.scheduleAtFixedRate(task, 0, 1000);
	}

}
