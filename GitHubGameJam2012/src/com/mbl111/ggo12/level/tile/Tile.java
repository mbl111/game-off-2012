package com.mbl111.ggo12.level.tile;

public class Tile {

	public static Tile[] tiles = new Tile[256];
	
	public static Tile byid(int id){
		if (id > tiles.length) return null;
		return tiles[id];
	}
	
}
