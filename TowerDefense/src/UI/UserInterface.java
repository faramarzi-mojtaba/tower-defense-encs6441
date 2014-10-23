package UI;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import UI.FactoryTower;
import core.applicationService.vikiMapServacs.MoneyManager;
import core.applicationService.vikiMapServacs.StandardAlgorithms;
import core.applicationService.vikiMapServacs.MapManager;
import UI.TowerParameters;
import core.domain.maps.CompleteGrid;
import core.domain.maps.EmptyGrid;
import core.domain.maps.Grid;
import core.domain.maps.Map;
//import core.domain.warriors.defenders.towers.*;
import core.domain.warriors.defenders.towers.vikiTowers.*;

public class UserInterface extends JFrame {

	int width = 15;
	int height = 15;
	TowerWindow towerWindow = new TowerWindow(this);
	// TowerWindowOnMap towerPropertyPanel1 = new TowerWindowOnMap(this);
	JTextField xField = new JTextField("", 4);
	JTextField yField = new JTextField("", 4);
	JLabel entryPoint = new JLabel("Tag Entery Point");
	JLabel exitPoint = new JLabel("Tag Exit Point");
	JButton setMapSizeButten = new JButton("Set Size");
	JButton drowMapButton = new JButton("Draw");
	JButton sinary = new JButton("sinary");
	JButton path = new JButton("path");
	JButton ep = new JButton("entryP");
	JButton exp = new JButton("exitP");
	JButton save = new JButton("Save Map");
	JButton load = new JButton("Load Map");
	JButton designMap = new JButton("Design Map");
	JButton playGame = new JButton("Start Playing");
	JTextField moneyView = new JTextField(); // display of view accumulated
												// points during the game
	JLabel acumulatedMoney = new JLabel("POINTS :");

	JButton designTowers = new JButton("Design Towers");
	Color colorToDrawGreed = Color.green;
	int colorInInteger = 1; // 1 = gray , 2 = green, 3 = red, 4 = blue

	Grid grid = new EmptyGrid(height, width);
	MapManager mapManager;
	FactoryTower towerFactory = new FactoryTower(); // //////////????????????
	CanvaObject canva = new CanvaObject(grid);
	// StandardAlgorithms pathAlgorithm = new StandardAlgorithms();
	JPanel lower = new JPanel();
	JPanel entryP = new JPanel();

	TowerParameters towerParam = new TowerParameters();
//	DesignToweerDialog towerDialogWindow = new DesignToweerDialog(this);
	MoneyManager moneyManager = new MoneyManager();
	public enum EnumGameStatValue {DESIGN,PLAYGAME};
	public EnumGameStatValue gameStatus;


	JPanel upper = new JPanel();

	UserInterface() {

		entryP.add(entryPoint);
		entryP.add(exitPoint);
		entryP.add(drowMapButton);
		upper.add(new JLabel("Set map size"));
		upper.add(xField);
		upper.add(yField);
		upper.add(setMapSizeButten);
		sinary.setBackground(Color.green);
		path.setBackground(Color.gray);
		ep.setBackground(Color.red);
		exp.setBackground(Color.blue);
		upper.add(sinary);
		upper.add(path);
		upper.add(exp);
		upper.add(ep);
		upper.add(save);
		upper.add(load);
		upper.add(designMap);
		upper.add(playGame);

		upper.add(towerWindow);
		towerWindow.setVisible(false);

		// make part of interface invisible to a user
		drowMapButton.setVisible(false);

		sinary.setEnabled(false);
		setMapSizeButten.setVisible(false);
		path.setEnabled(false);
		exp.setEnabled(false);
		ep.setEnabled(false);
		save.setEnabled(false);
		xField.setEnabled(false);
		yField.setEnabled(false);

		sinary.setVisible(false);
		path.setVisible(false);
		exp.setVisible(false);
		ep.setVisible(false);
		save.setVisible(false);
		xField.setVisible(false);
		yField.setVisible(false);
		playGame.setVisible(false);

		mapManager = new MapManager();

		xField.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if (!setMapSizeButten.isVisible())
					setMapSizeButten.setVisible(true);
				setMapSizeButten.setVisible(true);
				pack();
			}
		});

		xField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (!setMapSizeButten.isVisible())
					setMapSizeButten.setVisible(true);
				setMapSizeButten.setVisible(true);
				pack();

			}
		});

		yField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (!setMapSizeButten.isVisible())
					setMapSizeButten.setVisible(true);
				setMapSizeButten.setVisible(true);
				pack();

			}
		});

		yField.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if (!setMapSizeButten.isVisible())
					setMapSizeButten.setVisible(true);
				setMapSizeButten.setVisible(true);
				pack();

			}
		});

		playGame.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					towerWindow.setVisible(true);
					towerWindow.levelUp.setEnabled(true);
					towerWindow.levelDown.setEnabled(true);

					if (grid != null)
						grid = new Map((EmptyGrid)(((CompleteGrid)grid).simpleGrid));
					canva.updateGrid(grid);
					upper.add(acumulatedMoney);
					upper.add(moneyView);
					moneyView.setText("200");
					gameStatus = EnumGameStatValue.PLAYGAME;

					pack();
				} catch (java.lang.Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}

			}

		});

		designTowers.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				//	towerDialogWindow.setVisible(true);

					// pack();
				} catch (java.lang.Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}

			}

		});

		designMap.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					xField.setVisible(true);
					yField.setVisible(true);
					xField.setEnabled(true);
					yField.setEnabled(true);
					// load.setEnabled(false);
					// designMap.setVisible(false);
					grid = new CompleteGrid((EmptyGrid)grid);
					canva.updateGrid(grid);
					pack();
				} catch (java.lang.Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}

			}

		});

		load.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					JFileChooser openFile = new JFileChooser();
					if (JFileChooser.APPROVE_OPTION == openFile
							.showOpenDialog(null)) {
					//	grid = new CompleteGrid(grid);
						grid = new CompleteGrid(mapManager.LoadMapFromFile(openFile.getSelectedFile().getAbsolutePath()));
			
					/*	grid.gridAssignmentOperator(mapManager // TODO
								.LoadMapFromFile(openFile.getSelectedFile()
										.getAbsolutePath())); */
						canva.updateGrid(grid); 
						width = ((EmptyGrid)(((CompleteGrid) grid).simpleGrid)).getWidth();
						height = ((EmptyGrid)(((CompleteGrid) grid).simpleGrid)).getHeight();
						lower.setSize(width * ((EmptyGrid)(((CompleteGrid) grid).simpleGrid)).getUnitSize(),
								height * ((EmptyGrid)(((CompleteGrid) grid).simpleGrid)).getUnitSize());
						canva.setSize(width * ((EmptyGrid)(((CompleteGrid) grid).simpleGrid)).getUnitSize(),
								height *((EmptyGrid)(((CompleteGrid) grid).simpleGrid)).getUnitSize());
						// designMap.setEnabled(false);

						add(entryP, BorderLayout.CENTER);

						String message = mapManager.validateMapContent(((EmptyGrid)(((CompleteGrid) grid).simpleGrid)));
						if (!message.equals(""))
							JOptionPane.showMessageDialog(null, message);
						else
							playGame.setVisible(true);
						pack();

						setLocationRelativeTo(null);
					}

				} catch (java.lang.Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}

		});

		save.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					JFileChooser saveFile = new JFileChooser();

					if (saveFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
						String fileName = saveFile.getSelectedFile()
								.getAbsolutePath();

						String errorMessage = mapManager.SaveMapIntoFle((EmptyGrid)(((CompleteGrid)grid).simpleGrid),
								fileName);

						load.setEnabled(true);

						designMap.setEnabled(true);

						if (!errorMessage.equals(""))
							JOptionPane.showMessageDialog(null, errorMessage);

						pack();

					}
				} catch (java.lang.Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}

		});

		sinary.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					colorToDrawGreed = sinary.getBackground();
					colorInInteger = 2; // green

				} catch (java.lang.Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}

		});

		path.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					colorToDrawGreed = path.getBackground();
					colorInInteger = 1; // black for path
				} catch (java.lang.Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}

		});

		ep.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					colorToDrawGreed = ep.getBackground();
					colorInInteger = 3; // red for entry point
				} catch (java.lang.Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}

		});

		exp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					colorInInteger = 4; // blue for exit point
					colorToDrawGreed = exp.getBackground();
				} catch (java.lang.Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());

				}
			}

		});

		drowMapButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (((Map) grid).getEntryPoint().isEmpty()
							|| ((Map) grid).getExitPOint().isEmpty())
						throw new Exception(
								"Entry and exit points are not defined");
					canva.updateGrid(grid);
					canva.repaint();

				} catch (java.lang.Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});

		setMapSizeButten.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					path.setVisible(true);
					sinary.setVisible(true);
					exp.setVisible(true);
					ep.setVisible(true);
					save.setVisible(true);

					path.setEnabled(true);
					sinary.setEnabled(true);
					exp.setEnabled(true);
					ep.setEnabled(true);
					save.setEnabled(true);

					width = Integer.parseInt(xField.getText());
					height = Integer.parseInt(yField.getText());
					if (width > 60 || width < 5 || height > 60 || height < 5)
						throw new java.lang.Exception(
								"Error size max size: ....., min size: ....");


							// (((CompleteGrid)grid).simpleGrid)
					((EmptyGrid)(((CompleteGrid)grid).simpleGrid)).setSize(width, height);
					canva.updateGrid(grid);

					lower.setSize(width * ((EmptyGrid)(((CompleteGrid)grid).simpleGrid)).getUnitSize(),
							height * ((EmptyGrid)(((CompleteGrid)grid).simpleGrid)).getUnitSize());
					canva.setSize(width * ((EmptyGrid)(((CompleteGrid)grid).simpleGrid)).getUnitSize(),
							height * ((EmptyGrid)(((CompleteGrid)grid).simpleGrid)).getUnitSize());

					add(entryP, BorderLayout.CENTER);
					pack();

					setLocationRelativeTo(null);
					setMapSizeButten.setEnabled(false);

				} catch (java.lang.Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});

		canva.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (designMap.isEnabled()) { 
					int i = e.getY() / ((EmptyGrid)(((CompleteGrid)grid).simpleGrid)).getUnitSize();
					
					int j = e.getX() / ((EmptyGrid)(((CompleteGrid)grid).simpleGrid)).getUnitSize();
					
					if ((i < ((EmptyGrid)(((CompleteGrid)grid).simpleGrid)).getHeight()) && (j < ((EmptyGrid)(((CompleteGrid)grid).simpleGrid)).getWidth())
							&& ((((EmptyGrid)(((CompleteGrid)grid).simpleGrid))).content[i][j] != colorInInteger)) {
						
						if ((((EmptyGrid)(((CompleteGrid)grid).simpleGrid))).content[i][j] == 5) {
							Point point = canva.getLocationOnScreen();//getLocation();
							int x = point.x + (j + 1) * (((EmptyGrid)(((CompleteGrid)grid).simpleGrid))).getUnitSize();
							int y = point.y + (i + 1) * (((EmptyGrid)(((CompleteGrid)grid).simpleGrid))).getUnitSize();
							Point pointOnScreen = new Point(x, y);//e.getLocationOnScreen();
							// add(towerPropertyPanel);

							towerWindow.frame.setLocation(pointOnScreen);
							
							//towerWindow.setVisible(true);
							towerWindow.frame.setVisible(true);
							
							String position = Integer.toString(i) + " "
									+ Integer.toString(j);

							towerParam = ((Map) grid).towers.get(position).parameters;
							if(towerParam.towerCurrentLevel == 2){
								towerWindow.levelUp.setEnabled(false);
							towerWindow.levelDown.setEnabled(true);
							}
							else {									
									towerWindow.levelDown.setEnabled(true);
									towerWindow.levelUp.setEnabled(true);
							}
							
							String view = "<html>\n"
									+ "Tower characteristics:\n" + "<ul>\n"
									+ "<li><font color=red>Distance: "
									+ towerParam.range + "</font>\n"
									+ "<li><font color=blue>Frequency:"
									+ towerParam.firingSpeed + "</font>\n"
									+ "<li><font color=green>Buy price: "
									+ towerParam.buyPrice + "</font>\n"
									+ "<li><font color=green>Sale price: "
									+ towerParam.salePrice + "</font>\n"
									+ "</ul>\n";


							Point currentPosition = new Point(j, i);
							towerWindow.updateCurrentPosition(currentPosition);
							towerWindow.updateView(view);
							canva.repaint();

						} else {
							if( !(gameStatus == EnumGameStatValue.PLAYGAME))
								((EmptyGrid)(((CompleteGrid) grid).simpleGrid)).content[i][j] = colorInInteger;
						}

						canva.repaint();

					}
				}

			}

			@Override
			public void mouseMoved(MouseEvent e) {
				if (designMap.isEnabled()) {

					//((EmptyGrid)(((Map) grid).simpleGrid))
					
					int i = e.getY() /((EmptyGrid) grid).getUnitSize();
					int j = e.getX() / ((EmptyGrid) grid).getUnitSize();
					if ((i < ((EmptyGrid) grid).getHeight()) && (j < ((EmptyGrid) grid).getWidth())
							&& (((EmptyGrid) grid).content[i][j] != colorInInteger)) {
						((EmptyGrid) grid).content[i][j] = colorInInteger;
						canva.repaint();

					}
				}

			}

		});

		canva.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if (designMap.isEnabled()) {
					int i = e.getY() / ((EmptyGrid)((CompleteGrid)grid).simpleGrid).getUnitSize();
					int j = e.getX() / ((EmptyGrid) ((CompleteGrid)grid).simpleGrid).getUnitSize();
					if ((i < ((EmptyGrid) ((CompleteGrid)grid).simpleGrid).getHeight()) && (j < ((EmptyGrid) ((CompleteGrid)grid).simpleGrid).getWidth())
							&& (((EmptyGrid) ((CompleteGrid)grid).simpleGrid).content[i][j] != colorInInteger)) {
						((EmptyGrid) ((CompleteGrid)grid).simpleGrid).content[i][j] = colorInInteger;
						canva.repaint();

					}
				}
			}
		});

		canva.setSize(((EmptyGrid) grid).getHeight() * ((EmptyGrid) grid).getUnitSize(), ((EmptyGrid) grid).getWidth()
				* ((EmptyGrid) grid).getUnitSize());
		lower.setSize(((EmptyGrid) grid).getHeight() * ((EmptyGrid) grid).getUnitSize(), ((EmptyGrid) grid).getWidth()
				* ((EmptyGrid) grid).getUnitSize());
		lower.add(canva);

		add(upper, BorderLayout.NORTH);
		add(lower, BorderLayout.SOUTH);

		// setLayout(new FlowLayout());

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			/*	if (towerDialogWindow.isVisible()) {
					towerDialogWindow.setVisible(false);
					towerDialogWindow.dispose();
				}*/
			}
		});

		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	void setMapSize(int width, int height) {
		lower.setSize(width * ((EmptyGrid)grid).getUnitSize(), height * ((EmptyGrid)grid).getUnitSize());
		canva.setSize(width * ((EmptyGrid)grid).getUnitSize(), height * ((EmptyGrid)grid).getUnitSize());
		pack();
		setLocationRelativeTo(null);
	}

	public boolean canPlaceTower(Point point) {
		CanvasCoordinate coordinate = toCanvasCoordinates(point);
		return (((EmptyGrid)grid).getCellType(coordinate.x, coordinate.y) == 2);
	}

	public class CanvasCoordinate extends Point {
		public CanvasCoordinate(int x, int y) {
			super(x, y);
		}
	}

	public CanvasCoordinate toCanvasCoordinates(Point point) {
		Point canvasLocation = canva.getLocationOnScreen();
		int relativeX = point.x - canvasLocation.x;
		int relativeY = point.y - canvasLocation.y;
		int i = relativeX / ((EmptyGrid)((Map)grid).simpleGrid).getUnitSize();
		int j = relativeY / ((EmptyGrid)((Map)grid).simpleGrid).getUnitSize();
		return new CanvasCoordinate(i, j);
	}

	// tower types
	// 10 - basic tower => base level, 11 next level
	// 20 - freezing tower =>base level
	// 30 - firing tower => 31 , 32, ...
	public void placeTowerOnMap(Point point, int towerType) {
		CanvasCoordinate localPoint = toCanvasCoordinates(point);
		
		if(localPoint.x < 0 || localPoint.y < 0 || 
				localPoint.x > ((EmptyGrid)(((Map) grid).simpleGrid)).height || 
				localPoint.y > ((EmptyGrid)(((Map) grid).simpleGrid)).width)
		{
			return;
		}
		
		if(  ((EmptyGrid)(((Map) grid).simpleGrid)).content[localPoint.y][localPoint.x] == 2)
				//if ( canPlaceTower(point))
		{
			TowerParameters newParams = new TowerParameters();
			// newParams.towerType = ;

			newParams.position = toCanvasCoordinates(point);
			newParams.towerType = towerType;
			newParams.towerCurrentLevel = 0;

			Tower tower = towerFactory.creatTower(newParams);

			
			((EmptyGrid)(((Map) grid).simpleGrid)).setCell(localPoint.x, localPoint.y, 5); // @TODO: change the
															// last parameter
															// set proper tower
															// value
			String position = Integer.toString(localPoint.y) + " "
					+ Integer.toString(localPoint.x);

			((Map) grid).addTower(tower, position);
			moneyControler(localPoint, "buy"); // updates amount of point owned by the player 

			canva.repaint();
			pack();
		}
	}

	public void towerControler(Point point, String levelUpDown) {
		String position = Integer.toString(point.y) + " "
				+ Integer.toString(point.x);
		((Map) grid).updateLevel(position, levelUpDown);

		// double price = ((Map) grid).towers.get(position).cost();

		String view = "<html>\n" + "Tower characteristics:\n" + "<ul>\n"
				+ "<li><font color=red>Distance: " + towerParam.range
				+ "</font>\n" + "<li><font color=blue>Frequency:"
				+ towerParam.firingSpeed + "</font>\n"
				+ "<li><font color=green>Buy price: " +towerParam.buyPrice + "</font>\n"
				+ "<li><font color=green>Sale price: " + towerParam.salePrice + "</font>\n" + "</ul>\n";

		// Point currentPosition = new Point(j,i);
		// towerWindow.updateCurrentPosition(currentPosition);
		canva.repaint();
		towerWindow.updateView(view);

		canva.repaint();
		pack();

	}
	
	public boolean moneyControler(Point point, String buysell){
		boolean anableSellBuy = true;
		
		double amount = 0;
		String position = Integer.toString(point.y) + " "
				+ Integer.toString(point.x);	
		if(buysell.equals("buy"))
			amount = ((Map) grid).towers.get(position).parameters.buyPrice;
		else
			amount = ((Map) grid).towers.get(position).parameters.salePrice;
		
		Double value = (this.moneyManager.update(buysell, amount));
		if(value < 0){
			towerControler(point, "down");
			JOptionPane.showMessageDialog(null, "Not enouph money to buy a tower!");
		}else 	this.moneyView.setText(Double.toString(value)); 
		
		
		int level = ((Map) grid).towers.get(position).parameters.towerCurrentLevel;
		
		if(buysell.equals("sell") && (level == 0) || (buysell.equals("buy") && level == 2))
			anableSellBuy = false;
		
			
		return anableSellBuy;
			
	}
	
}
