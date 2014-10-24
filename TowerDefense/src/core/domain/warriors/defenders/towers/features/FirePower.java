package core.domain.warriors.defenders.towers.features;

import core.domain.warriors.defenders.DefenderConstatns;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.TowerFeatureDecorator;

public class FirePower extends TowerFeatureDecorator {

	private Tower tower;
	public FirePower(Tower tower) {
		this.tower = tower;
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return this.tower.getDescription() + ",FirePower";
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub

		this.tower.display();
	}

	@Override
	public long cost() {
		return DefenderConstatns.FIRE_POWER + this.tower.cost();
	}

}
