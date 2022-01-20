package com.ohyea777.monopoly;

import org.bukkit.Location;
import org.bukkit.Material;

public enum GameBoardGeometry {

	INSTANCE;
	
	private int[][] TILE_OFFSET;
	
	private GameBoardGeometry() {
		initOffset();
	}
	
	private void initOffset() {
		TILE_OFFSET = new int[][] {
			{ 0, 32 - 1, 0, 32 - 1 },
			{ 0, 32 - 1, 32, 32 * 2 - 1 },
			{ 0, 32 - 1, 32 * 2, 32 * 3 - 1 },
			{ 0, 32 - 1, 32 * 3, 32 * 4 - 1 },
			{ 0, 32 - 1, 32 * 4, 32 * 5 - 1 },
			{ 0, 32 - 1, 32 * 5, 32 * 6 - 1 },
			{ 0, 32 - 1, 32 * 6, 32 * 7 - 1 },
			{ 0, 32 - 1, 32 * 7, 32 * 8 - 1 },
			{ 0, 32 - 1, 32 * 8, 32 * 9 - 1 },
			{ 0, 32 - 1, 32 * 9, 32 * 10 - 1 },
			
			{ 32, 32 * 2 - 1, 32 * 9, 32 * 10 - 1 },
			{ 32 * 2, 32 * 2 - 1, 32 * 9, 32 * 10 - 1 },
			{ 32 * 3, 32 * 3 - 1, 32 * 9, 32 * 10 - 1 },
			{ 32 * 4, 32 * 4 - 1, 32 * 9, 32 * 10 - 1 },
			{ 32 * 5, 32 * 5 - 1, 32 * 9, 32 * 10 - 1 },
			{ 32 * 6, 32 * 6 - 1, 32 * 9, 32 * 10 - 1 },
			{ 32 * 7, 32 * 7 - 1, 32 * 9, 32 * 10 - 1 },
			{ 32 * 8, 32 * 8 - 1, 32 * 9, 32 * 10 - 1 },
			
			{ 32 * 9, 32 * 10 - 1, 0, 32 - 1 },
			{ 32 * 9, 32 * 10 - 1, 32, 32 * 2 - 1 },
			{ 32 * 9, 32 * 10 - 1, 32 * 2, 32 * 3 - 1 },
			{ 32 * 9, 32 * 10 - 1, 32 * 3, 32 * 4 - 1 },
			{ 32 * 9, 32 * 10 - 1, 32 * 4, 32 * 5 - 1 },
			{ 32 * 9, 32 * 10 - 1, 32 * 5, 32 * 6 - 1 },
			{ 32 * 9, 32 * 10 - 1, 32 * 6, 32 * 7 - 1 },
			{ 32 * 9, 32 * 10 - 1, 32 * 7, 32 * 8 - 1 },
			{ 32 * 9, 32 * 10 - 1, 32 * 8, 32 * 9 - 1 },
			{ 32 * 9, 32 * 10 - 1, 32 * 9, 32 * 10 - 1 },
			
			{ 32, 32 * 2 - 1, 0, 32 - 1 },
			{ 32 * 2, 32 * 2 - 1, 0, 32 - 1 },
			{ 32 * 3, 32 * 3 - 1, 0, 32 - 1 },
			{ 32 * 4, 32 * 4 - 1, 0, 32 - 1 },
			{ 32 * 5, 32 * 5 - 1, 0, 32 - 1 },
			{ 32 * 6, 32 * 6 - 1, 0, 32 - 1 },
			{ 32 * 7, 32 * 7 - 1, 0, 32 - 1 },
			{ 32 * 8, 32 * 8 - 1, 0, 32 - 1 }
		};
	}
	
	public int[][] getTileOffset() {
		return TILE_OFFSET;
	}
	
	public void test(Location loc) {
		int x = loc.getBlockX(), y = loc.getBlockY(), z = loc.getBlockZ();
		for (int i = 0; i < TILE_OFFSET.length; i ++) {
			for (int xOff = TILE_OFFSET[i][0]; xOff <= TILE_OFFSET[i][1]; xOff ++) {
				for (int zOff = TILE_OFFSET[i][2]; zOff <= TILE_OFFSET[i][3]; zOff ++) {
					loc.add(x + xOff, y, z + zOff).getBlock().setType(Material.DIAMOND_BLOCK);
				}
			}
		}
	}
	
}
