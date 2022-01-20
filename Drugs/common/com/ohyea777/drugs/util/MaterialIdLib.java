package com.ohyea777.drugs.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;

@SuppressWarnings("deprecation")
public class MaterialIdLib {

	private final static Map<Material, Integer> materials = new HashMap<Material, Integer>();

    public static Material getMaterialById(int id) {
        if (materials.containsValue(id)) {
            for (Material m : materials.keySet()) {
                if (id == materials.get(m)) {
                    return m;
                }
            }
        }

        throw new IllegalArgumentException();
    }
    
    public static int getIdByMaterial(Material mat) {
        if (materials.containsKey(mat)) {
            return materials.get(mat);
        }

        throw new IllegalArgumentException();
    }
    
    public static List<Material> getAvailableMaterials() {
        List<Material> mats = new ArrayList<Material>();

        for (Material m : materials.keySet()) {
            mats.add(m);
        }

        return mats;
    }
    
    public static List<Integer> getAvailableIds() {
        List<Integer> ids = new ArrayList<Integer>();

        for (Material m : materials.keySet()) {
            ids.add(materials.get(m));
        }

        return ids;
    }
    
    public static Map<Material, Integer> getMaterialIdLinkage() {
        return materials;
    }

    static {
        materials.put(Material.AIR, 0);
        materials.put(Material.STONE, 1);
        materials.put(Material.GRASS, 2);
        materials.put(Material.DIRT, 3);
        materials.put(Material.COBBLESTONE, 4);
        materials.put(Material.WOOD, 5);
        materials.put(Material.SAPLING, 6);
        materials.put(Material.BEDROCK, 7);
        materials.put(Material.WATER, 8);
        materials.put(Material.STATIONARY_WATER, 9);
        materials.put(Material.LAVA, 10);
        materials.put(Material.STATIONARY_LAVA, 11);
        materials.put(Material.SAND, 12);
        materials.put(Material.GRAVEL, 13);
        materials.put(Material.GOLD_ORE, 14);
        materials.put(Material.IRON_ORE, 15);
        materials.put(Material.COAL_ORE, 16);
        materials.put(Material.LOG, 17);
        materials.put(Material.LEAVES, 18);
        materials.put(Material.SPONGE, 19);
        materials.put(Material.GLASS, 20);
        materials.put(Material.LAPIS_ORE, 21);
        materials.put(Material.LAPIS_BLOCK, 22);
        materials.put(Material.DISPENSER, 23);
        materials.put(Material.SANDSTONE, 24);
        materials.put(Material.NOTE_BLOCK, 25);
        materials.put(Material.BED_BLOCK, 26);
        materials.put(Material.POWERED_RAIL, 27);
        materials.put(Material.DETECTOR_RAIL, 28);
        materials.put(Material.PISTON_STICKY_BASE, 29);
        materials.put(Material.WEB, 30);
        materials.put(Material.LONG_GRASS, 31);
        materials.put(Material.DEAD_BUSH, 32);
        materials.put(Material.PISTON_BASE, 33);
        materials.put(Material.PISTON_EXTENSION, 34);
        materials.put(Material.WOOL, 35);
        materials.put(Material.PISTON_MOVING_PIECE, 36);
        materials.put(Material.YELLOW_FLOWER, 37);
        materials.put(Material.RED_ROSE, 38);
        materials.put(Material.BROWN_MUSHROOM, 39);
        materials.put(Material.RED_MUSHROOM, 40);
        materials.put(Material.GOLD_BLOCK, 41);
        materials.put(Material.IRON_BLOCK, 42);
        materials.put(Material.DOUBLE_STEP, 43);
        materials.put(Material.STEP, 44);
        materials.put(Material.BRICK, 45);
        materials.put(Material.TNT, 46);
        materials.put(Material.BOOKSHELF, 47);
        materials.put(Material.MOSSY_COBBLESTONE, 48);
        materials.put(Material.OBSIDIAN, 49);
        materials.put(Material.TORCH, 50);
        materials.put(Material.FIRE, 51);
        materials.put(Material.MOB_SPAWNER, 52);
        materials.put(Material.WOOD_STAIRS, 53);
        materials.put(Material.CHEST, 54);
        materials.put(Material.REDSTONE_WIRE, 55);
        materials.put(Material.DIAMOND_ORE, 56);
        materials.put(Material.DIAMOND_BLOCK, 57);
        materials.put(Material.WORKBENCH, 58);
        materials.put(Material.CROPS, 59);
        materials.put(Material.SOIL, 60);
        materials.put(Material.FURNACE, 61);
        materials.put(Material.BURNING_FURNACE, 62);
        materials.put(Material.SIGN_POST, 63);
        materials.put(Material.WOODEN_DOOR, 64);
        materials.put(Material.LADDER, 65);
        materials.put(Material.RAILS, 66);
        materials.put(Material.COBBLESTONE_STAIRS, 67);
        materials.put(Material.WALL_SIGN, 68);
        materials.put(Material.LEVER, 69);
        materials.put(Material.STONE_PLATE, 70);
        materials.put(Material.IRON_DOOR_BLOCK, 71);
        materials.put(Material.WOOD_PLATE, 72);
        materials.put(Material.REDSTONE_ORE, 73);
        materials.put(Material.GLOWING_REDSTONE_ORE, 74);
        materials.put(Material.REDSTONE_TORCH_OFF, 75);
        materials.put(Material.REDSTONE_TORCH_ON, 76);
        materials.put(Material.STONE_BUTTON, 77);
        materials.put(Material.SNOW, 78);
        materials.put(Material.ICE, 79);
        materials.put(Material.SNOW_BLOCK, 80);
        materials.put(Material.CACTUS, 81);
        materials.put(Material.CLAY, 82);
        materials.put(Material.SUGAR_CANE_BLOCK, 83);
        materials.put(Material.JUKEBOX, 84);
        materials.put(Material.FENCE, 85);
        materials.put(Material.PUMPKIN, 86);
        materials.put(Material.NETHERRACK, 87);
        materials.put(Material.SOUL_SAND, 88);
        materials.put(Material.GLOWSTONE, 89);
        materials.put(Material.PORTAL, 90);
        materials.put(Material.JACK_O_LANTERN, 91);
        materials.put(Material.CAKE_BLOCK, 92);
        materials.put(Material.DIODE_BLOCK_OFF, 93);
        materials.put(Material.DIODE_BLOCK_ON, 94);
        materials.put(Material.LOCKED_CHEST, 95);
        materials.put(Material.STAINED_GLASS, 95);
        materials.put(Material.TRAP_DOOR, 96);
        materials.put(Material.MONSTER_EGGS, 97);
        materials.put(Material.SMOOTH_BRICK, 98);
        materials.put(Material.HUGE_MUSHROOM_1, 99);
        materials.put(Material.HUGE_MUSHROOM_2, 100);
        materials.put(Material.IRON_FENCE, 101);
        materials.put(Material.THIN_GLASS, 102);
        materials.put(Material.MELON_BLOCK, 103);
        materials.put(Material.PUMPKIN_STEM, 104);
        materials.put(Material.MELON_STEM, 105);
        materials.put(Material.VINE, 106);
        materials.put(Material.FENCE_GATE, 107);
        materials.put(Material.BRICK_STAIRS, 108);
        materials.put(Material.SMOOTH_STAIRS, 109);
        materials.put(Material.MYCEL, 110);
        materials.put(Material.WATER_LILY, 111);
        materials.put(Material.NETHER_BRICK, 112);
        materials.put(Material.NETHER_FENCE, 113);
        materials.put(Material.NETHER_BRICK_STAIRS, 114);
        materials.put(Material.NETHER_WARTS, 115);
        materials.put(Material.ENCHANTMENT_TABLE, 116);
        materials.put(Material.BREWING_STAND, 117);
        materials.put(Material.CAULDRON, 118);
        materials.put(Material.ENDER_PORTAL, 119);
        materials.put(Material.ENDER_PORTAL_FRAME, 120);
        materials.put(Material.ENDER_STONE, 121);
        materials.put(Material.DRAGON_EGG, 122);
        materials.put(Material.REDSTONE_LAMP_OFF, 123);
        materials.put(Material.REDSTONE_LAMP_ON, 124);
        materials.put(Material.WOOD_DOUBLE_STEP, 125);
        materials.put(Material.WOOD_STEP, 126);
        materials.put(Material.COCOA, 127);
        materials.put(Material.SANDSTONE_STAIRS, 128);
        materials.put(Material.EMERALD_ORE, 129);
        materials.put(Material.ENDER_CHEST, 130);
        materials.put(Material.TRIPWIRE_HOOK, 131);
        materials.put(Material.TRIPWIRE, 132);
        materials.put(Material.EMERALD_BLOCK, 133);
        materials.put(Material.SPRUCE_WOOD_STAIRS, 134);
        materials.put(Material.BIRCH_WOOD_STAIRS, 135);
        materials.put(Material.JUNGLE_WOOD_STAIRS, 136);
        materials.put(Material.COMMAND, 137);
        materials.put(Material.BEACON, 138);
        materials.put(Material.COBBLE_WALL, 139);
        materials.put(Material.FLOWER_POT, 140);
        materials.put(Material.CARROT, 141);
        materials.put(Material.POTATO, 142);
        materials.put(Material.WOOD_BUTTON, 143);
        materials.put(Material.SKULL, 144);
        materials.put(Material.ANVIL, 145);
        materials.put(Material.TRAPPED_CHEST, 146);
        materials.put(Material.GOLD_PLATE, 147);
        materials.put(Material.IRON_PLATE, 148);
        materials.put(Material.REDSTONE_COMPARATOR_OFF, 149);
        materials.put(Material.REDSTONE_COMPARATOR_ON, 150);
        materials.put(Material.DAYLIGHT_DETECTOR, 151);
        materials.put(Material.REDSTONE_BLOCK, 152);
        materials.put(Material.QUARTZ_ORE, 153);
        materials.put(Material.HOPPER, 154);
        materials.put(Material.QUARTZ_BLOCK, 155);
        materials.put(Material.QUARTZ_STAIRS, 156);
        materials.put(Material.ACTIVATOR_RAIL, 157);
        materials.put(Material.DROPPER, 158);
        materials.put(Material.STAINED_CLAY, 159);
        materials.put(Material.STAINED_GLASS_PANE, 160);
        materials.put(Material.LEAVES_2, 161);
        materials.put(Material.LOG_2, 162);
        materials.put(Material.ACACIA_STAIRS, 163);
        materials.put(Material.DARK_OAK_STAIRS, 164);
        materials.put(Material.HAY_BLOCK, 170);
        materials.put(Material.CARPET, 171);
        materials.put(Material.HARD_CLAY, 172);
        materials.put(Material.COAL_BLOCK, 173);
        materials.put(Material.PACKED_ICE, 174);
        materials.put(Material.DOUBLE_PLANT, 175);
        // ----- Item Seperator -----
        materials.put(Material.IRON_SPADE, 256);
        materials.put(Material.IRON_PICKAXE, 257);
        materials.put(Material.IRON_AXE, 258);
        materials.put(Material.FLINT_AND_STEEL, 259);
        materials.put(Material.APPLE, 260);
        materials.put(Material.BOW, 261);
        materials.put(Material.ARROW, 262);
        materials.put(Material.COAL, 263);
        materials.put(Material.DIAMOND, 264);
        materials.put(Material.IRON_INGOT, 265);
        materials.put(Material.GOLD_INGOT, 266);
        materials.put(Material.IRON_SWORD, 267);
        materials.put(Material.WOOD_SWORD, 268);
        materials.put(Material.WOOD_SPADE, 269);
        materials.put(Material.WOOD_PICKAXE, 270);
        materials.put(Material.WOOD_AXE, 271);
        materials.put(Material.STONE_SWORD, 272);
        materials.put(Material.STONE_SPADE, 273);
        materials.put(Material.STONE_PICKAXE, 274);
        materials.put(Material.STONE_AXE, 275);
        materials.put(Material.DIAMOND_SWORD, 276);
        materials.put(Material.DIAMOND_SPADE, 277);
        materials.put(Material.DIAMOND_PICKAXE, 278);
        materials.put(Material.DIAMOND_AXE, 279);
        materials.put(Material.STICK, 280);
        materials.put(Material.BOWL, 281);
        materials.put(Material.MUSHROOM_SOUP, 282);
        materials.put(Material.GOLD_SWORD, 283);
        materials.put(Material.GOLD_SPADE, 284);
        materials.put(Material.GOLD_PICKAXE, 285);
        materials.put(Material.GOLD_AXE, 286);
        materials.put(Material.STRING, 287);
        materials.put(Material.FEATHER, 288);
        materials.put(Material.SULPHUR, 289);
        materials.put(Material.WOOD_HOE, 290);
        materials.put(Material.STONE_HOE, 291);
        materials.put(Material.IRON_HOE, 292);
        materials.put(Material.DIAMOND_HOE, 293);
        materials.put(Material.GOLD_HOE, 294);
        materials.put(Material.SEEDS, 295);
        materials.put(Material.WHEAT, 296);
        materials.put(Material.BREAD, 297);
        materials.put(Material.LEATHER_HELMET, 298);
        materials.put(Material.LEATHER_CHESTPLATE, 299);
        materials.put(Material.LEATHER_LEGGINGS, 300);
        materials.put(Material.LEATHER_BOOTS, 301);
        materials.put(Material.CHAINMAIL_HELMET, 302);
        materials.put(Material.CHAINMAIL_CHESTPLATE, 303);
        materials.put(Material.CHAINMAIL_LEGGINGS, 304);
        materials.put(Material.CHAINMAIL_BOOTS, 305);
        materials.put(Material.IRON_HELMET, 306);
        materials.put(Material.IRON_CHESTPLATE, 307);
        materials.put(Material.IRON_LEGGINGS, 308);
        materials.put(Material.IRON_BOOTS, 309);
        materials.put(Material.DIAMOND_HELMET, 310);
        materials.put(Material.DIAMOND_CHESTPLATE, 311);
        materials.put(Material.DIAMOND_LEGGINGS, 312);
        materials.put(Material.DIAMOND_BOOTS, 313);
        materials.put(Material.GOLD_HELMET, 314);
        materials.put(Material.GOLD_CHESTPLATE, 315);
        materials.put(Material.GOLD_LEGGINGS, 316);
        materials.put(Material.GOLD_BOOTS, 317);
        materials.put(Material.FLINT, 318);
        materials.put(Material.PORK, 319);
        materials.put(Material.GRILLED_PORK, 320);
        materials.put(Material.PAINTING, 321);
        materials.put(Material.GOLDEN_APPLE, 322);
        materials.put(Material.SIGN, 323);
        materials.put(Material.WOOD_DOOR, 324);
        materials.put(Material.BUCKET, 325);
        materials.put(Material.WATER_BUCKET, 326);
        materials.put(Material.LAVA_BUCKET, 327);
        materials.put(Material.MINECART, 328);
        materials.put(Material.SADDLE, 329);
        materials.put(Material.IRON_DOOR, 330);
        materials.put(Material.REDSTONE, 331);
        materials.put(Material.SNOW_BALL, 332);
        materials.put(Material.BOAT, 333);
        materials.put(Material.LEATHER, 334);
        materials.put(Material.MILK_BUCKET, 335);
        materials.put(Material.CLAY_BRICK, 336);
        materials.put(Material.CLAY_BALL, 337);
        materials.put(Material.SUGAR_CANE, 338);
        materials.put(Material.PAPER, 339);
        materials.put(Material.BOOK, 340);
        materials.put(Material.SLIME_BALL, 341);
        materials.put(Material.STORAGE_MINECART, 342);
        materials.put(Material.POWERED_MINECART, 343);
        materials.put(Material.EGG, 344);
        materials.put(Material.COMPASS, 345);
        materials.put(Material.FISHING_ROD, 346);
        materials.put(Material.WATCH, 347);
        materials.put(Material.GLOWSTONE_DUST, 348);
        materials.put(Material.RAW_FISH, 349);
        materials.put(Material.COOKED_FISH, 350);
        materials.put(Material.INK_SACK, 351);
        materials.put(Material.BONE, 352);
        materials.put(Material.SUGAR, 353);
        materials.put(Material.CAKE, 354);
        materials.put(Material.BED, 355);
        materials.put(Material.DIODE, 356);
        materials.put(Material.COOKIE, 357);
        materials.put(Material.MAP, 358);
        materials.put(Material.SHEARS, 359);
        materials.put(Material.MELON, 360);
        materials.put(Material.PUMPKIN_SEEDS, 361);
        materials.put(Material.MELON_SEEDS, 362);
        materials.put(Material.RAW_BEEF, 363);
        materials.put(Material.COOKED_BEEF, 364);
        materials.put(Material.RAW_CHICKEN, 365);
        materials.put(Material.COOKED_CHICKEN, 366);
        materials.put(Material.ROTTEN_FLESH, 367);
        materials.put(Material.ENDER_PEARL, 368);
        materials.put(Material.BLAZE_ROD, 369);
        materials.put(Material.GHAST_TEAR, 370);
        materials.put(Material.GOLD_NUGGET, 371);
        materials.put(Material.NETHER_STALK, 372);
        materials.put(Material.POTION, 373);
        materials.put(Material.GLASS_BOTTLE, 374);
        materials.put(Material.SPIDER_EYE, 375);
        materials.put(Material.FERMENTED_SPIDER_EYE, 376);
        materials.put(Material.BLAZE_POWDER, 377);
        materials.put(Material.MAGMA_CREAM, 378);
        materials.put(Material.BREWING_STAND_ITEM, 379);
        materials.put(Material.CAULDRON_ITEM, 380);
        materials.put(Material.EYE_OF_ENDER, 381);
        materials.put(Material.SPECKLED_MELON, 382);
        materials.put(Material.MONSTER_EGG, 383);
        materials.put(Material.EXP_BOTTLE, 384);
        materials.put(Material.FIREBALL, 385);
        materials.put(Material.BOOK_AND_QUILL, 386);
        materials.put(Material.WRITTEN_BOOK, 387);
        materials.put(Material.EMERALD, 388);
        materials.put(Material.ITEM_FRAME, 389);
        materials.put(Material.FLOWER_POT_ITEM, 390);
        materials.put(Material.CARROT_ITEM, 391);
        materials.put(Material.POTATO_ITEM, 392);
        materials.put(Material.BAKED_POTATO, 393);
        materials.put(Material.POISONOUS_POTATO, 394);
        materials.put(Material.EMPTY_MAP, 395);
        materials.put(Material.GOLDEN_CARROT, 396);
        materials.put(Material.SKULL_ITEM, 397);
        materials.put(Material.CARROT_STICK, 398);
        materials.put(Material.NETHER_STAR, 399);
        materials.put(Material.PUMPKIN_PIE, 400);
        materials.put(Material.FIREWORK, 401);
        materials.put(Material.FIREWORK_CHARGE, 402);
        materials.put(Material.ENCHANTED_BOOK, 403);
        materials.put(Material.REDSTONE_COMPARATOR, 404);
        materials.put(Material.NETHER_BRICK_ITEM, 405);
        materials.put(Material.QUARTZ, 406);
        materials.put(Material.EXPLOSIVE_MINECART, 407);
        materials.put(Material.HOPPER_MINECART, 408);
        materials.put(Material.IRON_BARDING, 417);
        materials.put(Material.GOLD_BARDING, 418);
        materials.put(Material.DIAMOND_BARDING, 419);
        materials.put(Material.LEASH, 420);
        materials.put(Material.NAME_TAG, 421);
        materials.put(Material.COMMAND_MINECART, 422);
        // ----- Disc Seperator -----
        materials.put(Material.GOLD_RECORD, 2256);
        materials.put(Material.GREEN_RECORD, 2257);
        materials.put(Material.RECORD_3, 2258);
        materials.put(Material.RECORD_4, 2259);
        materials.put(Material.RECORD_5, 2260);
        materials.put(Material.RECORD_6, 2261);
        materials.put(Material.RECORD_7, 2262);
        materials.put(Material.RECORD_8, 2263);
        materials.put(Material.RECORD_9, 2264);
        materials.put(Material.RECORD_10, 2265);
        materials.put(Material.RECORD_11, 2266);
        materials.put(Material.RECORD_12, 2267);
    }
	
}
