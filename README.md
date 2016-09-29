AStarLibrary : AStar implementation in Java
===========================================

Getting started
---------------

You have to implements PathFinderMap, and the method isTraversable. Here is an example.
```java
public class MyMap implements PathFinderMap {
	private enum CellType { GRASS, DIRT, LAVA }
	private int width;
	private int height;
    
	private CellType[][] cells;
    
	public MyMap(int width, int height) {
		this.width = width;
		this.height = height;
		cells = new CellType[width][height];
		... // Initialization
	}
    
	@Override
	boolean isTraversable(int x, int y){
		return cells[x][y] != CellType.LAVA;
	}
    
	@Override
	public int getWidth(){
		return width;
	}
    
	@Override
	public int getHeight(){
		return height;
	}
}
```

After that, you have to create an instance of AStarParams, and initialize it as you wish.
Now, you can call AStar.findPath on your params object and get a LinkedList which contains your path.
```java
MyMap map = new MyMap(26,11);
PathNodePosition startPos = new PathNodePosition(1,3);
PathNodePosition endPos = new PathNodePosition(8,6);
AStarParams params = new AStarParams(map, startPos, endPos);
params.setMoveType(AStarParams.MoveType.ORTHOGONAL_ONLY).setHeuristic(AStarParams.Heuristic.MANHATTAN_DISTANCE);

LinkedList<PathNodePosition> path = AStar.findPath(params);
```
