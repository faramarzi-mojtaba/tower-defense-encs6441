package core.domain.warriors.defenders.towers.behaviourImp;

import core.domain.warriors.defenders.towers.behaviours.SoundBehaviour;

/**
 * <b>this is used for strategy design pattern
 * this class is defined to implement the one of the sound behavior of a tower
 * that is a kind of dying wail sound</b>
 * @author mojtaba
 *@version 0.1
 */
public class LaughSound implements SoundBehaviour {

/**
 * sound that will be add in second build for tower
 */
	@Override
	public void sound() {
		System.out.println("I can laughing!");

	}

}
