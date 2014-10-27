package core.domain.warriors.defenders.towers.towerType;


import java.awt.Color;

import core.contract.DefenderConstants;
import core.contract.MapConstants;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.behaviourImp.NoMove;
import core.domain.warriors.defenders.towers.behaviourImp.NoSound;
import core.domain.warriors.defenders.towers.behaviourImp.ShootTrap;
/**
 * <b>this type of tower has shoot trap as a weapon and it doesn't have any sound and moving </b>
 * @author mojtaba
 * @version0.1
 */
public class AncientTower extends Tower {
	/**
	 * by this constructor we can set the behaviors of a tower
	 */
	public AncientTower() {
		setMovingBehaviour(new NoMove());
		setShootingBehaviour(new ShootTrap());
		setSoundBehaviour(new NoSound());
		//this.description = "Ancient Tower";
	}
	/**
	 * 
	 * it will be show the appearance of the tower until now we used for color
	 * @return Color, that is color of the button that is representative of a tower
	 */
	@Override
	public Color display() {
		return MapConstants.ANCIENT_TOWER_COLOR;

	}
	/**
	 * 
	 * <b>by this method we can calculate the cost of decorated tower type that is a base tower</b>
	 * <code>
	 *  return DefenderConstants.ANCIENT_TOWER;
	 * </code>
	 */
	@Override
	public long cost() {
		return DefenderConstants.ANCIENT_TOWER;
	}

	
}


