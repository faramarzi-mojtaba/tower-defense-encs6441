package maps;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import critters.Critter;
import MapServacs.GraphNode;
import Towers.BasicTower;
import Towers.TowerInterface;
import UI.CanvaObject;

public class EmptyGrid implements Grid {
	public int height = 15;
	public int width = 15;
	public int sizeOfUnit = 30;
	public int [][] content = new int[height][width];
	
	
	public EmptyGrid(int newheiht, int newwidth){
	//	super();
		height = newheiht;
		width = newwidth;
	}
	


	public int  getHeight(){
		
		return height;
	}
	
	public int getWidth(){
		
		return width;
	}
	
	public int getUnitSize(){
		
		return sizeOfUnit;
	}
	
	public void setContent(int[][] newContent){
		
		content = newContent;
	}
	
	public int[][] getContent(){
		
		return content;
	}
	
	public void setSize(int newWidth, int newheight){
		
		height = newheight;
		width = newWidth;
		content = new int[height][width];
	};

	public void setPath(Stack<GraphNode> path){};
	
	public int getCellType(int i, int j)
	{
		if(i >= 0 && i < height && j >= 0 && j < width)
			return content[i][j];
		
		return -1;
	}
	
	public void setContentXY(int cordinatX, int cordinatY, int type)
	{
		if(cordinatY >= 0 && cordinatY < height && cordinatX >= 0 && cordinatX < width)
			content[cordinatY][cordinatX] = type;
	}
	
	@Override
public	void draw(Graphics g) {
		
		for (int i = 0; i < width * sizeOfUnit; i += sizeOfUnit) {
			for (int j = 0; j < height  * sizeOfUnit; j += sizeOfUnit) {
				g.drawLine(i, 0, i, height * sizeOfUnit);
				g.drawLine(0, j, width * sizeOfUnit, j);
			}
		}


		g.drawLine(width * sizeOfUnit - 1, height * sizeOfUnit - 1,
				width * sizeOfUnit - 1, 0); // x1y1 x2y2

		g.drawLine(0, height * sizeOfUnit - 1, width * sizeOfUnit - 1,
				height * sizeOfUnit - 1);

	}
	


	@Override
	public void addTower(TowerInterface newTower, String positionKey){
		
	}
	
	@Override
	public void registerTowerAsObserver(int positionx, int positiony, TowerInterface tower){}



	@Override
	public TowerInterface getTower(String towerPosition) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void unregisterObserver(TowerInterface tower) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public ArrayList<GridCell> getPath() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void addCritter(Critter newCritter) {
		// TODO Auto-generated method stub
	
	}



	@Override
	public ArrayList<Critter> getCritters() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public HashMap<String, TowerInterface> getTowers() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void getState(int state) {
		// TODO Auto-generated method stub
		
	}



}