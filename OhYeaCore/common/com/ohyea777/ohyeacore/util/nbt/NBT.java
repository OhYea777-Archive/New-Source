package com.ohyea777.ohyeacore.util.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.bukkit.inventory.ItemStack;

@SuppressWarnings({ "unchecked", "rawtypes", "incomplete-switch" })
public enum NBT {
	BOOL(-1), END(0), BYTE(1), SHORT(2), INT(3), LONG(4), FLOAT(5), DOUBLE(6), BYTE_ARRAY(
			7), STRING(8), LIST(9), COMPOUND(10), INT_ARRAY(11);

	private final int _id;

	private NBT(int id) {
		_id = id;
	}

	public int getId() {
		return _id;
	}

	public static Map<String, Tag> itemStackToMap(ItemStack item)
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException, InvocationTargetException,
			NoSuchFieldException, NoSuchMethodException,
			NBTLibDisabledException, UnknownTagException {
		return NBTToMap(saveItem(bukkitToMc(item)));
	}

	public static ItemStack mapToItemStack(Map<String, ?> map)
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException, InvocationTargetException,
			NoSuchFieldException, NoSuchMethodException,
			NBTLibDisabledException, UnknownTagException {
		return mcToBukkit(loadItem(mapToNBT(map)));
	}

	@Deprecated
	public static Object mapToNBT(String name, Map<String, ?> map)
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException, InvocationTargetException,
			NoSuchFieldException, NoSuchMethodException,
			NBTLibDisabledException, UnknownTagException {
		return tagToNBT(name, Tag.newCompound(map));
	}

	public static Object mapToNBT(Map<String, ?> map)
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException, InvocationTargetException,
			NoSuchFieldException, NoSuchMethodException,
			NBTLibDisabledException, UnknownTagException {
		return tagToNBT(Tag.newCompound(map));
	}

	@Deprecated
	public static Object tagToNBT(String name, Tag tag)
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException, InvocationTargetException,
			NoSuchMethodException, NBTLibDisabledException {
		return tagToNBT(tag);
	}
	
	public static Object tagToNBT(Tag tag) throws ClassNotFoundException,
			IllegalAccessException, InstantiationException,
			InvocationTargetException, NoSuchMethodException,
			NBTLibDisabledException {
		switch (tag.getType()) {
		case BOOL:
			return NBTLib
					.instantiateMinecraft("NBTTagByte",
							new Object[] { byte.class },
							new Object[] { (byte) (((Boolean) tag.get())
									.booleanValue() ? 1 : 0) });
		case BYTE:
			return NBTLib.instantiateMinecraft("NBTTagByte",
					new Object[] { byte.class },
					new Object[] { ((Byte) tag.get()).byteValue() });
		case SHORT:
			return NBTLib.instantiateMinecraft("NBTTagShort",
					new Object[] { short.class },
					new Object[] { ((Short) tag.get()).shortValue() });
		case INT:
			return NBTLib.instantiateMinecraft("NBTTagInt",
					new Object[] { int.class },
					new Object[] { ((Integer) tag.get()).intValue() });
		case LONG:
			return NBTLib.instantiateMinecraft("NBTTagLong",
					new Object[] { long.class },
					new Object[] { ((Long) tag.get()).longValue() });
		case FLOAT:
			return NBTLib.instantiateMinecraft("NBTTagFloat",
					new Object[] { float.class },
					new Object[] { ((Float) tag.get()).floatValue() });
		case DOUBLE:
			return NBTLib.instantiateMinecraft("NBTTagDouble",
					new Object[] { double.class },
					new Object[] { ((Double) tag.get()).doubleValue() });
		case BYTE_ARRAY:
			return NBTLib.instantiateMinecraft("NBTTagByteArray",
					new Object[] { byte[].class },
					new Object[] { (byte[]) tag.get() });
		case STRING:
			return NBTLib.instantiateMinecraft("NBTTagString",
					new Object[] { String.class },
					new Object[] { (String) tag.get() });
		case LIST:
			Object list = NBTLib.instantiateMinecraft("NBTTagList",
					new Object[0], new Object[0]);
			for (Tag t : ((List<Tag>) tag.get()).toArray(new Tag[0])) {
				NBTLib.invokeMinecraft(
						"NBTTagList",
						list,
						"add",
						new Object[] { NBTLib.getMinecraftPackage() + "NBTBase" },
						new Object[] { tagToNBT("", t) });
			}
			return list;
		case COMPOUND:
			Object map = NBTLib.instantiateMinecraft("NBTTagCompound",
					new Object[0], new Object[0]);
			for (Map.Entry<String, Tag> entry : ((Map<String, Tag>) tag.get())
					.entrySet()) {
				String key = entry.getKey();
				NBTLib.invokeMinecraft(
						"NBTTagCompound",
						map,
						"set",
						new Object[] { String.class,
								NBTLib.getMinecraftPackage() + "NBTBase" },
						new Object[] { key, tagToNBT(key, entry.getValue()) });
			}
			return map;
		case INT_ARRAY:
			return NBTLib.instantiateMinecraft("NBTTagIntArray",
					new Object[] { int[].class },
					new Object[] { (int[]) tag.get() });
		}
		return null;
	}

	public static Map<String, Tag> NBTToMap(Object o)
			throws ClassNotFoundException, IllegalAccessException,
			InvocationTargetException, NoSuchFieldException,
			NoSuchMethodException, NBTLibDisabledException, UnknownTagException {
		if (getEnum(o) == COMPOUND) {
			return (Map<String, Tag>) (NBTToTag(o).get());
		} else {
			HashMap<String, Tag> map = new HashMap<String, Tag>();
			map.put("", NBTToTag(o));
			return map;
		}
	}

	public static Tag NBTToTag(Object o) throws ClassNotFoundException,
			IllegalAccessException, InvocationTargetException,
			NoSuchFieldException, NoSuchMethodException,
			NBTLibDisabledException, UnknownTagException {
		switch (getEnum(o)) {
		case BYTE:
			return Tag.newByte((Byte) NBTLib.fetchMinecraftField("NBTTagByte",
					o, "data"));
		case SHORT:
			return Tag.newShort((Short) NBTLib.fetchMinecraftField(
					"NBTTagShort", o, "data"));
		case INT:
			return Tag.newInt((Integer) NBTLib.fetchMinecraftField("NBTTagInt",
					o, "data"));
		case LONG:
			return Tag.newLong((Long) NBTLib.fetchMinecraftField("NBTTagLong",
					o, "data"));
		case FLOAT:
			return Tag.newFloat((Float) NBTLib.fetchMinecraftField(
					"NBTTagFloat", o, "data"));
		case DOUBLE:
			return Tag.newDouble((Double) NBTLib.fetchMinecraftField(
					"NBTTagDouble", o, "data"));
		case BYTE_ARRAY:
			return Tag.newByteArray((byte[]) NBTLib.fetchMinecraftField(
					"NBTTagByteArray", o, "data"));
		case STRING:
			return Tag.newString((String) NBTLib.fetchMinecraftField(
					"NBTTagString", o, "data"));
		case LIST:
			return Tag.newList((List) NBTLib.fetchMinecraftField("NBTTagList",
					o, "list"));
		case COMPOUND:
			return Tag.newCompound((Map<String, ?>) NBTLib.fetchMinecraftField(
					"NBTTagCompound", o, "map"));
		case INT_ARRAY:
			return Tag.newIntArray((int[]) NBTLib.fetchMinecraftField(
					"NBTTagIntArray", o, "data"));
		}
		throw new UnknownTagException();
	}

	public static Object loadNBT64(String string)
			throws ClassNotFoundException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			NBTLibDisabledException {
		return loadNBT(DatatypeConverter.parseBase64Binary(string));
	}

	public static Object loadNBT(byte[] array) throws ClassNotFoundException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, NBTLibDisabledException {
		return NBTLib.invokeMinecraftDynamic("NBTCompressedStreamTools", null,
				NBTLib.getMinecraftPackage() + "NBTTagCompound",
				new Object[] { byte[].class }, new Object[] { array });
	}

	public static Object loadNBT(InputStream stream)
			throws ClassNotFoundException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			NBTLibDisabledException {
		return NBTLib.invokeMinecraftDynamic("NBTCompressedStreamTools", null,
				NBTLib.getMinecraftPackage() + "NBTTagCompound",
				new Object[] { InputStream.class }, new Object[] { stream });
	}

	public static Object loadNBT(DataInput input)
			throws ClassNotFoundException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			NBTLibDisabledException {
		return NBTLib.invokeMinecraftDynamic("NBTCompressedStreamTools", null,
				NBTLib.getMinecraftPackage() + "NBTTagCompound",
				new Object[] { DataInput.class }, new Object[] { input });
	}

	public static String saveNBT64(Object o) throws ClassNotFoundException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, NBTLibDisabledException {
		return DatatypeConverter.printBase64Binary(saveNBT(o));
	}

	public static byte[] saveNBT(Object o) throws ClassNotFoundException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, NBTLibDisabledException {
		return (byte[]) NBTLib
				.invokeMinecraftDynamic("NBTCompressedStreamTools", null,
						byte[].class,
						new Object[] { NBTLib.getMinecraftPackage()
								+ "NBTTagCompound" }, new Object[] { o });
	}

	public static void saveNBT(OutputStream stream, Object o)
			throws ClassNotFoundException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			NBTLibDisabledException {
		NBTLib.invokeMinecraftDynamic("NBTCompressedStreamTools", null,
				void.class, new Object[] {
						NBTLib.getMinecraftPackage() + "NBTTagCompound",
						OutputStream.class }, new Object[] { o, stream });
	}

	public static void saveNBT(DataOutput output, Object o)
			throws ClassNotFoundException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			NBTLibDisabledException {
		NBTLib.invokeMinecraftDynamic("NBTCompressedStreamTools", null,
				void.class, new Object[] {
						NBTLib.getMinecraftPackage() + "NBTTagCompound",
						DataOutput.class }, new Object[] { o, output });
	}

	public static ItemStack loadItemStack64(String string)
			throws ClassNotFoundException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			NBTLibDisabledException {
		return mcToBukkit(loadItem(loadNBT64(string)));
	}

	public static ItemStack loadItemStack(byte[] array)
			throws ClassNotFoundException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			NBTLibDisabledException {
		return mcToBukkit(loadItem(loadNBT(array)));
	}

	public static ItemStack loadItemStack(InputStream stream)
			throws ClassNotFoundException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			NBTLibDisabledException {
		return mcToBukkit(loadItem(loadNBT(stream)));
	}

	public static ItemStack loadItemStack(DataInput input)
			throws ClassNotFoundException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			NBTLibDisabledException {
		return mcToBukkit(loadItem(loadNBT(input)));
	}

	private static Object loadItem(Object nbt) throws ClassNotFoundException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, NBTLibDisabledException {
		return NBTLib
				.invokeMinecraftDynamic("ItemStack", null,
						NBTLib.getMinecraftPackage() + "ItemStack",
						new Object[] { NBTLib.getMinecraftPackage()
								+ "NBTTagCompound" }, new Object[] { nbt });
	}

	public static String saveItemStack64(ItemStack item)
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException, InvocationTargetException,
			NoSuchMethodException, NBTLibDisabledException {
		return saveNBT64(saveItem(bukkitToMc(item)));
	}

	public static byte[] saveItemStack(ItemStack item)
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException, InvocationTargetException,
			NoSuchMethodException, NBTLibDisabledException {
		return saveNBT(saveItem(bukkitToMc(item)));
	}

	public static void saveItemStack(OutputStream stream, ItemStack item)
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException, InvocationTargetException,
			NoSuchMethodException, NBTLibDisabledException {
		saveNBT(stream, saveItem(bukkitToMc(item)));
	}

	public static void saveItemStack(DataOutput output, ItemStack item)
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException, InvocationTargetException,
			NoSuchMethodException, NBTLibDisabledException {
		saveNBT(output, saveItem(bukkitToMc(item)));
	}

	private static Object saveItem(Object item) throws ClassNotFoundException,
			IllegalAccessException, InstantiationException,
			InvocationTargetException, NoSuchMethodException,
			NBTLibDisabledException {
		return NBTLib
				.invokeMinecraftDynamic("ItemStack", item,
						NBTLib.getMinecraftPackage() + "NBTTagCompound",
						new Object[] { NBTLib.getMinecraftPackage()
								+ "NBTTagCompound" }, new Object[] { NBTLib
								.instantiateMinecraft("NBTTagCompound",
										new Object[0], new Object[0]) });
	}

	public static Object bukkitToMc(ItemStack item)
			throws ClassNotFoundException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			NBTLibDisabledException {
		return NBTLib.invokeCraftbukkit("inventory.CraftItemStack", null,
				"asNMSCopy", new Object[] { ItemStack.class },
				new Object[] { item });
	}

	public static ItemStack mcToBukkit(Object o) throws ClassNotFoundException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, NBTLibDisabledException {
		return (ItemStack) NBTLib.invokeCraftbukkit("inventory.CraftItemStack",
				null, "asBukkitCopy",
				new Object[] { NBTLib.getMinecraftPackage() + "ItemStack" },
				new Object[] { o });
	}

	public static NBT getEnum(Object o) throws ClassNotFoundException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, NBTLibDisabledException {
		int i = ((Byte) NBTLib.invokeMinecraft("NBTBase", o, "getTypeId",
				new Object[0], new Object[0])).intValue();
		for (NBT nbt : values()) {
			if (nbt.getId() == i) {
				return nbt;
			}
		}
		return null;
	}

}