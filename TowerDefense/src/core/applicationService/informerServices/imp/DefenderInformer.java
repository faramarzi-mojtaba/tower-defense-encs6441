package core.applicationService.informerServices.imp;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import Infrastructure.loggin.Log4jLogger;
import core.applicationService.informerServices.IDefenderInformer;
import core.applicationService.informerServices.Observer;
import core.domain.Subject;
import core.domain.waves.Position;


/**
 * <b>In this class we implement observer design pattern to inform all towers the wave's head position</b>
 * @author Ali, Mojtaba
 * @version 0.1
 */
@Component
public class DefenderInformer implements Subject, IDefenderInformer {
	
	/**
	 * <b>The wave head position.
	 * this member is the wave's head reperesenteter</b>
	 */
	private Position waveHeadPosition;
	
	/** <b>List of observer that contains the all observer for implementing our observer design pattern</b>
	 * 
	 */
	private List<Observer> observers;
	
	/** The logger that was implemented by log4j2 and the logger class is located in Infrastructure
	 */
	private static final Log4jLogger logger = new Log4jLogger();
	
	/**
	 * Instantiates a new defender informer.
	 */
	public DefenderInformer(){
		observers = new ArrayList<Observer>(); 
	}
	
	/**
	 * <b>
	 * this method can register all observers in observer's list
	 * </b
	 * @param Observer
	 */
	@Override
	public void registerObserver(Observer o) {
		try {
			if( o !=null)
				observers.add(o);
			
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
	}


	/**
	 * <b>
	 * this method can remove a observer from observer's list
	 * </b
	 * @param Observer
	 */
	@Override
	public void removeObserver(Observer o) {
		try {
			if (o !=null)
				observers.remove(o);
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
	}

	
	/**
	 * <b>
	 * this method can force observers to update 
	 * and towers are informed the position of aliens by this method.
	 * 
	 * <code>
	 * for (Observer ob : observers) {
	 *			ob.update(waveHeadPosition);
	 *		}
	 * </code>
	 * </b>
	 */
	@Override
	public void notifyObservers() {
		try {
			for (Observer ob : observers) {
				ob.update(waveHeadPosition);
			}
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		
	}
	
	/**
	 * this method sets the wave'poisition
	 * @param x as integer 
	 * @param y as integer
	 */
	@Override
	public void setPosition(int x, int y){
		try {
			this.waveHeadPosition.setX(x);
			this.waveHeadPosition.setY(y);
			positionChange();
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
	}
	
	/**
	 * 
	 * if the x and y of the head changes the setPosition method will call this method tho notify the observers 
	 * that are towers
	 */
	@Override
	public void positionChange(){
		try {
			notifyObservers();
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
	}

	/**
	 * it can return the current position of the wave's head
	 * @return Position 
	 */
	@Override
	public Position getPosition() {
		return waveHeadPosition;
	}

}
