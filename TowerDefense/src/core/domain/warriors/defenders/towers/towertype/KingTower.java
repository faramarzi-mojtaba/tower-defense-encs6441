package core.domain.warriors.defenders.towers.towertype;

import core.contract.DefenderConstants;
import core.contract.MapConstants;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.behaviourimp.BulletShooting;
import core.domain.warriors.defenders.towers.behaviourimp.IceShooting;
import core.domain.warriors.defenders.towers.behaviourimp.LineShooting;
import core.domain.warriors.defenders.towers.behaviourimp.NoMove;
import core.domain.warriors.defenders.towers.behaviourimp.NoSound;
import core.domain.warriors.defenders.towers.behaviourimp.ShootTrap;
/**
 * <b>this type of tower has shoot trap as a weapon and it doesn't have any sound and moving </b>
 * @author Team5
 * @version 0.1
 */
@SuppressWarnings("serial")
public class KingTower extends Tower {
	/**
	 * by this constructor we can set the behaviors of a tower
	 */
	public KingTower() {
		setMovingBehaviour(new NoMove());
		setShootingBehaviour(new ShootTrap());
		setSoundBehaviour(new NoSound());
		setBulletShootingBehaviour(new BulletShooting());
		setIceShootingBehaviour( new IceShooting());
		setLineShootingBehaviour(new LineShooting());
//		crittersLocation = new HashMap<Critter, Position>();
	}
	/**
	 * 
	 * it will be show the appearance of the tower until now we used for color
	 * @return Color, that is color of the button that is representative of a tower
	 */
	@Override
	public String display() {
		return MapConstants.KING_TOWER_IMG;
	}
	/**
	 * 
	 * <b>by this method we can calculate the cost of decorated tower type that is a base tower</b>
	 * <code>
	 *  return DefenderConstants.KING_TOWER;
	 * </code>
	 */
	@Override
	public long cost() {
		return DefenderConstants.KING_TOWER;
	}
	

}
