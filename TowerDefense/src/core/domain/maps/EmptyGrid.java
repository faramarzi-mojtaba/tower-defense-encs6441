package core.domain.maps;

import java.awt.Graphics;

public class EmptyGrid extends Grid {
	public int height = 15;
	public int width = 15;
	public int sizeOfUnit = 30;
	public int [][] content = new int[height][width];
	
	
	public EmptyGrid(int newheiht, int newwidth){
		super();
		height = newheiht;
		width = newwidth;
	}
	
	public EmptyGrid(){
		
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
	
	public void setSize(int newWidth, int newheight){
		
		height = newheight;
		width = newWidth;
		content = new int[height][width];
	};

	
	public int getCellType(int i, int j)
	{
		if(i >= 0 && i < height && j >= 0 && j < width)
			return content[i][j];
		
		return -1;
	}
	
	public void setCell(int cordinatX, int cordinatY, int type)
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

}
