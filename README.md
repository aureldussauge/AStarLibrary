AStarLibrary : AStar implementation in Java
===========================================

This library allows to estimate shortest path on a grid, by using the A\* algorithm.

Latest release
---------------

The most recent release is 1.0, released October 1, 2016

You can get the .jar here : https://github.com/Mursaat/AStarLibrary/releases/tag/1.0

To add a dependency using Gradle:
Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

Add the dependency
```
dependencies {
	compile 'com.github.Mursaat:AStarLibrary:1.0'
}
```

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
params.setNeighborsEnumerator(NeighborsEnumerator.ORTHO_NEIGHBORS).setHeuristic(DistanceCalculator.MANHATTAN_DISTANCE);

LinkedList<PathNodePosition> path = AStar.findPath(params);
```
