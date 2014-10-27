package UI.game;

import java.awt.Color;
import java.awt.Graphics;

import core.contract.MapConstants;
import core.domain.maps.Grid;
import core.domain.warriors.defenders.towers.Tower;

public class Map extends Grid {

	Tower[][] towers;

	public Map(int width, int height) {
		super(width, height);
	}

	public Map(Grid grid) {
		super(grid);
	}

	public void updateTowers(Tower[][] towers) {
		this.towers = towers;

	}
	
	public void draw(Graphics g) {
		// simpleGrid.draw(g);
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				Color color = Color.green;
				switch (getCell(x, y)){
				case PATH:
					color = MapConstants.PATH_COLOR;
					break;
				case SCENERY:
					color = MapConstants.SCENERY_COLOR;
					break;
				case ENTRANCE:
					color = MapConstants.ENTRANCE_COLOR;
					break;
				case EXIT:
					color = MapConstants.EXIT_COLOR;
					break;
				case TOWER:
					color = towers[x][y].display();
					break;
				}
				g.setColor(color);
				g.fillRect(x * getUnitSize(), y * getUnitSize(), getUnitSize(),
						getUnitSize());

			}

		}

	}

}