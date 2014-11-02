package ui.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ui.CanvaObject;
import ui.towerdesign.SimpleInspection;
import ui.towerdesign.TowerInfoPanel;
import core.applicationservice.mapservices.MapManager;
import core.applicationservice.mapservices.connectivity.imp.StartEndChecker;
import core.applicationservice.warriorservices.TowerFactory;
import core.contract.DefenderConstants;
import core.contract.MapConstants;
import core.domain.account.BankManager;
import core.domain.maps.Grid;
import core.domain.maps.GridCellContentType;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.towertype.TowerLevel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Observer, ActionListener,
		MouseListener {
	private JDialog towerInfo;
	private int width;
	private int height;
	private BankManager bank;

	private boolean addTowerFlag;

	private String towertype;
	private Tower[][] towers;
	private JButton modernTowerBtn;
	private JButton ancientTowerBtn;
	private JButton kingTowerBtn;

	private JLabel bankLbl;
	@SuppressWarnings("unused")
	private Color colorToDisplayTower;
	private GridCellContentType cellContent;

	private Map grid;

	private MapManager mapManager;
	private CanvaObject canvas;
	private JPanel mapContainer;

	private JPanel toolBoxContainer = new JPanel();

	private SimpleInspection inspection;
	private int x, y;
	private long availFunds;

	private TowerInfoPanel towerInfoPanel;

	@SuppressWarnings("unused")
	private GamePanel() {
	}

	/**
	 * <b>Constructs the main panel for game</b>
	 * @param width map width
	 * @param height map height
	 */
	public GamePanel(int width, int height) {

		initialize(width, height);
		setLayout(new BorderLayout());

		modernTowerBtn.setBackground(MapConstants.MODERN_TOWER_COLOR);
		ancientTowerBtn.setBackground(MapConstants.ANCIENT_TOWER_COLOR);
		kingTowerBtn.setBackground(MapConstants.KING_TOWER_COLOR);

		toolBoxContainer.setSize(10, 500);
		toolBoxContainer.setLayout(new FlowLayout());
		toolBoxContainer.add(bankLbl);
		toolBoxContainer.add(modernTowerBtn);
		toolBoxContainer.add(ancientTowerBtn);
		toolBoxContainer.add(kingTowerBtn);
		mapManager = new MapManager();

		toolBoxContainer.add(towerInfoPanel);

		modernTowerBtn.addActionListener(this);
		ancientTowerBtn.addActionListener(this);
		kingTowerBtn.addActionListener(this);
		canvas.addMouseListener(this);

		modernTowerBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				displayTowerInfo("ModernTower");

			}

			@Override
			public void mouseExited(MouseEvent e) {
				hideTowerInfo();

			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

		});

		ancientTowerBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				displayTowerInfo("AncientTower");

			}

			@Override
			public void mouseExited(MouseEvent e) {
				hideTowerInfo();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		kingTowerBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				displayTowerInfo("KingTower");

			}

			@Override
			public void mouseExited(MouseEvent e) {
				hideTowerInfo();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		int mapPixelWidth = grid.getWidth() * grid.getUnitSize();
		int mapPixelHeight = grid.getHeight() * grid.getUnitSize();
		canvas.setSize(mapPixelWidth, mapPixelHeight);
		mapContainer.setPreferredSize(new Dimension(mapPixelWidth,
				mapPixelHeight));
		mapContainer.add(canvas);

		add(new JPanel(), BorderLayout.NORTH);
		add(toolBoxContainer, BorderLayout.EAST);
		add(mapContainer, BorderLayout.CENTER);
		add(new JPanel(), BorderLayout.WEST);
		add(new JPanel(), BorderLayout.SOUTH);
		setVisible(true);

	}

	private void initialize(int width, int height) {

		towerInfoPanel = new TowerInfoPanel();

		this.bank = BankManager.getInstance();

		availFunds = this.bank.getBalance() - this.bank.getCurrentBalance();
		String str = new Long(availFunds).toString();
		this.bankLbl = new JLabel("$" + str);
		this.width = width;
		this.height = height;

		this.addTowerFlag = false;

		modernTowerBtn = new JButton(DefenderConstants.MODERN_TOWER_TYPE);
		ancientTowerBtn = new JButton(DefenderConstants.ANCIENT_TOWER_TYPE);
		kingTowerBtn = new JButton(DefenderConstants.KING_TOWER_TYPE);

		towers = new Tower[width][height];
		grid = new Map(width, height);

		canvas = new CanvaObject(grid);
		mapContainer = new JPanel();

		toolBoxContainer = new JPanel();

	}

	private void displayTowerInfo(String towertype) {
		TowerFactory factory = new TowerFactory();

		Tower tower = factory.getTower(towertype, TowerLevel.one);

		towerInfoPanel = new TowerInfoPanel(tower);
		// toolBoxContainer.add(towerInfoPanel);
		towerInfo = new JDialog(new Frame(), "Tower info");
		towerInfo.add(towerInfoPanel);
		towerInfo.setSize(200, 200);

		towerInfo.setLocation(getWidth() - 250, 100);
		towerInfo.setVisible(true);

	}

	private void hideTowerInfo() {
		towerInfo.dispose();
	}

	public class CanvasCoordinate extends Point {
		public CanvasCoordinate(int x, int y) {
			super(x, y);
		}
	}

	/**
	 * <b>Converts point to a coordinate that is actionable by the canvas.</b>
	 * @param point target location on the grid (canvas)
	 * @return canvas coordinate
	 */
	public CanvasCoordinate toCanvasCoordinates(Point point) {
		Point canvasLocation = canvas.getLocationOnScreen();
		int relativeX = point.x - canvasLocation.x;
		int relativeY = point.y - canvasLocation.y;
		int i = relativeX / grid.getUnitSize();
		int j = relativeY / grid.getUnitSize();
		return new CanvasCoordinate(i, j);
	}

	/**
	 * <b>Prepares the grid for further actions.</b>
	 * @param width map width
	 * @param height map height
	 */
	public void setGridSize(int width, int height) {
		grid.setSize(width, height);
		canvas.setGrid(grid);
	}

	
	/**
	 * <b>Prepares the map after it is loaded from file system.</b>
	 * @param width map width
	 * @param height map height
	 */
	public void design(int width, int height) {
		try {
			grid.setSize(width, height);
			canvas.setGrid(grid);

			mapContainer.setSize(width * grid.getUnitSize(),
					height * grid.getUnitSize());
			canvas.setSize(width * grid.getUnitSize(),
					height * grid.getUnitSize());

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	private int[] cellCoordinate(int pixelX, int pixelY) {
		int i = pixelX / grid.getUnitSize();
		int j = pixelY / grid.getUnitSize();
		int[] coordinate = { i, j };
		this.x = i;
		this.y = j;
		return coordinate;
	}

	private void draw(int x, int y) {
		if ((x < grid.getWidth()) && (y < grid.getHeight())
				&& (grid.getCell(x, y) != cellContent)) {
			grid.setCell(x, y, cellContent);
			canvas.repaint();
		}
	}

	/** <b>Depending on where the user clicks it would either place a tower on the map or inspects a tower characteristics.</b>
	 * @param event MouseEvent generated from user action.
	 */
	@Override
	public void mouseClicked(MouseEvent event) {

		int[] coordinate = cellCoordinate(event.getX(), event.getY());
		int x = coordinate[0];
		int y = coordinate[1];

		if (x <= width & y <= height) {
			if (addTowerFlag) {
				addTower(x, y);
			} else {
				towerUpgradePanels();
			}
		}
	}

	private void towerUpgradePanels() {
		if (towers[x][y] != null) {

			if (inspection != null) {
				inspection.close();
				inspection = null;
			}
			// customTowerFeatures = new TowerManagerPanel(towers[x][y]);
			inspection = new SimpleInspection(towers[x][y]);
			inspection.addObserver(this);
		}
	}

	private void addTower(int x, int y) {

		if (grid.getCell(x, y) == GridCellContentType.SCENERY) {
			TowerFactory factory = new TowerFactory();
			Tower tower;
			switch (towertype) {
			case DefenderConstants.MODERN_TOWER_TYPE:
				tower = factory.getTower(DefenderConstants.MODERN_TOWER_TYPE,
						TowerLevel.one);
				break;
			case DefenderConstants.ANCIENT_TOWER_TYPE:
				tower = factory.getTower(DefenderConstants.ANCIENT_TOWER_TYPE,
						TowerLevel.one);
				break;
			case DefenderConstants.KING_TOWER_TYPE:
				tower = factory.getTower(DefenderConstants.KING_TOWER_TYPE,
						TowerLevel.one);
				break;

			default:
				tower = factory.getTower(DefenderConstants.MODERN_TOWER_TYPE,
						TowerLevel.one);
			}

			if (tower.cost() < bank.getBalance() - bank.getCurrentBalance()) {
				bank.setCurrentBalance(tower.cost());
				availFunds = this.bank.getBalance()
						- this.bank.getCurrentBalance();
				String str = new Long(availFunds).toString();
				this.bankLbl.setText("$" + str);
				towers[x][y] = tower;
				grid.updateTowers(towers);
				draw(x, y);
			} else {
				JOptionPane.showMessageDialog(new JFrame(),
						"you don't have enough money :(", "Alert",
						JOptionPane.WARNING_MESSAGE);
			}
			addTowerFlag = false;
		}
	}

	
	/** 
	 * <b>Depending on the tower selection by the user, selects one and prepares to place it on the map.</b>
	 * @param event ActionEvent passed as a result of user action.
	 */
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();

		switch (command) {
		case DefenderConstants.MODERN_TOWER_TYPE:
			towertype = DefenderConstants.MODERN_TOWER_TYPE;
			tower(towertype);
			break;
		case DefenderConstants.ANCIENT_TOWER_TYPE:
			towertype = DefenderConstants.ANCIENT_TOWER_TYPE;
			tower(towertype);
			break;
		case DefenderConstants.KING_TOWER_TYPE:
			towertype = DefenderConstants.KING_TOWER_TYPE;
			tower(towertype);
			break;
		}
	}

	private void tower(String towertype) {
		try {
			addTowerFlag = true;
			switch (towertype) {
			case DefenderConstants.MODERN_TOWER_TYPE:
				colorToDisplayTower = modernTowerBtn.getBackground();
				break;
			case DefenderConstants.ANCIENT_TOWER_TYPE:
				colorToDisplayTower = ancientTowerBtn.getBackground();
				break;
			case DefenderConstants.KING_TOWER_TYPE:
				colorToDisplayTower = kingTowerBtn.getBackground();
				break;
			}
			cellContent = GridCellContentType.TOWER;
		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	protected void setMapSize(int width, int height) {
		try {
			// validation part
			StartEndChecker checker = new StartEndChecker();
			if (!checker.isCorrectSize(height, width))
				// end of validation

				mapContainer.setSize(width * grid.getUnitSize(),
						height * grid.getUnitSize());
			canvas.setSize(width * grid.getUnitSize(),
					height * grid.getUnitSize());

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	protected void loadMap() {
		try {
			JFileChooser openFile = new JFileChooser();
			if (JFileChooser.APPROVE_OPTION == openFile.showOpenDialog(null)) {
				grid = new Map((Grid) mapManager.LoadMapFromFile(openFile
						.getSelectedFile().getAbsolutePath()));
				canvas.setGrid(grid);
				width = grid.getWidth();
				height = grid.getHeight();
				mapContainer.setSize(width * grid.getUnitSize(),
						height * grid.getUnitSize());
				canvas.setSize(width * grid.getUnitSize(),
						height * grid.getUnitSize());
				resetGameState();
			}

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	private void resetGameState() {
		towers = new Tower[width][height];
		bank.resetCurrentBalance();
	}

	@Override
	public void mouseEntered(MouseEvent event) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent event) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	/**
	 * <b>This method updates the tower stats and the bank balance and removes the a tower from the map.</b>
	 * @param arg1 is object is of type tower  perform operation
	 * @param arg0 observer object
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		switch (inspection.getPerformedAction()) {
		case "Upgrade":
			upgradeTower();
			break;
		case "Sell":
			clearTower(x, y);
			break;
		default:
			break;
		}
	}

	private void upgradeTower() {
		towers[x][y] = inspection.getTower();
		availFunds = this.bank.getBalance() - this.bank.getCurrentBalance();
		String str = new Long(availFunds).toString();
		this.bankLbl.setText("$" + str);
	}

	private void clearTower(int x, int y) {
		if ((x < grid.getWidth()) && (y < grid.getHeight())
				&& (grid.getCell(x, y) == GridCellContentType.TOWER)) {
			availFunds = this.bank.getBalance() - this.bank.getCurrentBalance();
			String str = new Long(availFunds).toString();
			this.bankLbl.setText("$" + str);
			towers[x][y] = null;
			grid.updateTowers(towers);
			grid.setCell(x, y, GridCellContentType.SCENERY);
			canvas.repaint();
		}
	}
}
