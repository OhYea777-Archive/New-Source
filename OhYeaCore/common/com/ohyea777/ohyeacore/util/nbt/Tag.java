package com.ohyea777.ohyeacore.util.nbt;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({ "unchecked", "rawtypes" })
public final class Tag<T> {

	private final NBT _type;
	private final T _data;

	public static Tag<Boolean> newBool(boolean data) {
		return new Tag<Boolean>(NBT.BOOL, data);
	}

	public static Tag<Byte> newByte(byte data) {
		return new Tag<Byte>(NBT.BYTE, data);
	}

	public static Tag<Short> newShort(short data) {
		return new Tag<Short>(NBT.SHORT, data);
	}

	public static Tag<Integer> newInt(int data) {
		return new Tag<Integer>(NBT.INT, data);
	}

	public static Tag<Long> newLong(long data) {
		return new Tag<Long>(NBT.LONG, data);
	}

	public static Tag<Float> newFloat(float data) {
		return new Tag<Float>(NBT.FLOAT, data);
	}

	public static Tag<Double> newDouble(double data) {
		return new Tag<Double>(NBT.DOUBLE, data);
	}

	public static Tag<byte[]> newByteArray(byte[] data) {
		return new Tag<byte[]>(NBT.BYTE_ARRAY, data);
	}

	public static Tag<String> newString(String data) {
		return new Tag<String>(NBT.STRING, data);
	}

	public static Tag<List<Tag>> newList(List data)
			throws ClassNotFoundException, IllegalAccessException,
			InvocationTargetException, NoSuchFieldException,
			NoSuchMethodException, NBTLibDisabledException, UnknownTagException {
		List<Tag> list;
		try {
			for (Object o : data.toArray()) {
				Tag.class.cast(o);
			}
			list = (List<Tag>) data;
		} catch (ClassCastException e) {
			list = new ArrayList<Tag>();
			for (Object o : data.toArray()) {
				list.add(parse(o));
			}
		}
		return new Tag<List<Tag>>(NBT.LIST, list);
	}

	public static Tag<Map<String, Tag>> newCompound(Map<String, ?> data)
			throws ClassNotFoundException, IllegalAccessException,
			InvocationTargetException, NoSuchFieldException,
			NoSuchMethodException, NBTLibDisabledException, UnknownTagException {
		Map<String, Tag> map;
		try {
			for (Object o : data.values().toArray()) {
				Tag.class.cast(o);
			}
			map = (Map<String, Tag>) data;
		} catch (ClassCastException e) {
			map = new HashMap<String, Tag>();
			for (Map.Entry<String, ?> entry : data.entrySet()) {
				map.put(entry.getKey(), parse(entry.getValue()));
			}
		}
		return new Tag<Map<String, Tag>>(NBT.COMPOUND, map);
	}

	public static Tag<int[]> newIntArray(int[] data) {
		return new Tag<int[]>(NBT.INT_ARRAY, data);
	}

	private static Tag parse(Object o) throws ClassNotFoundException,
			IllegalAccessException, InvocationTargetException,
			NoSuchFieldException, NoSuchMethodException,
			NBTLibDisabledException, UnknownTagException {
		if (o instanceof Tag) {
			return (Tag) o;
		} else if (o instanceof Boolean) {
			return newBool((Boolean) o);
		} else if (o instanceof Byte) {
			return newByte((Byte) o);
		} else if (o instanceof Short) {
			return newShort((Short) o);
		} else if (o instanceof Integer) {
			return newInt((Integer) o);
		} else if (o instanceof Long) {
			return newLong((Long) o);
		} else if (o instanceof Float) {
			return newFloat((Float) o);
		} else if (o instanceof Double) {
			return newDouble((Double) o);
		} else if (o instanceof byte[]) {
			return newByteArray((byte[]) o);
		} else if (o instanceof String) {
			return newString((String) o);
		} else if (o instanceof List) {
			return newList((List) o);
		} else if (o instanceof Map) {
			return newCompound((Map) o);
		} else if (o instanceof int[]) {
			return newIntArray((int[]) o);
		} else if (Class.forName(NBTLib.getMinecraftPackage() + "NBTBase")
				.isInstance(o)) {
			return NBT.NBTToTag(o);
		} else {
			throw new UnknownTagException();
		}
	}

	private Tag(NBT type, T data) {
		_type = type;
		_data = data;
	}

	public NBT getType() {
		return _type;
	}

	public T get() {
		return _data;
	}
}