package com.ohyea777.ohyeacore.util.nbt;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class NBTLib {
	
	static {
		_log = Logger.getLogger("Minecraft");
		try {
			invoke("sun.reflect.Reflection", null, "registerFieldsToFilter",
					new Class[] { Class.class, String[].class }, new Object[] {
							NBTLib.class, new String[] { "_disabled" } });
		} catch (Throwable t) {
		}
		clinit();
	}

	public static final String _version = "#VERSION#";

	public static final Logger _log;
	private static String _minecraft;
	private static String _craftbukkit;
	private static String _pversion;
	private static boolean _disabled;

	private static void clinit() {
		_disabled = true;
		ArrayList<Package> list = new ArrayList<Package>();
		for (Package p : Package.getPackages()) {
			if (p.getName().startsWith("net.minecraft.server")) {
				list.add(p);
			}
		}
		if (list.size() == 1) {
			_minecraft = list.get(0).getName();
			_pversion = _minecraft.substring(21);
			_craftbukkit = "org.bukkit.craftbukkit" + _minecraft.substring(20);
			if (Package.getPackage(_craftbukkit) == null) {
				_log.severe("[NBTLib] Can't find Craftbukkit package! ("
						+ _minecraft + "/" + _craftbukkit + ")");
			} else {
				_minecraft += ".";
				_craftbukkit += ".";
				_disabled = false;
			}
		} else {
			_log.severe("[NBTLib] Can't find Minecraft package! " + list.size()
					+ " possible packages found:");
			for (Package p : list.toArray(new Package[0])) {
				_log.severe("[NBTLib] " + p.getName());
			}
		}
	}

	public static boolean enabled() {
		return !_disabled;
	}

	public static Class getMinecraftClass(String className)
			throws ClassNotFoundException, NBTLibDisabledException {
		return Class.forName(getMinecraftPackage() + className);
	}

	public static Class getCraftbukkitClass(String className)
			throws ClassNotFoundException, NBTLibDisabledException {
		return Class.forName(getCraftbukkitPackage() + className);
	}

	public static String getPackageVersion() {
		return _pversion;
	}

	public static String getMinecraftVersion() {
		return Bukkit.getVersion();
	}

	public static String getMinecraftPackage() throws NBTLibDisabledException {
		if (_disabled) {
			throw new NBTLibDisabledException();
		}
		return _minecraft;
	}

	public static String getCraftbukkitPackage() throws NBTLibDisabledException {
		if (_disabled) {
			throw new NBTLibDisabledException();
		}
		return _craftbukkit;
	}

	public static Object fetchDynamicMinecraftField(String className,
			Object object, Object type) throws ClassNotFoundException,
			IllegalAccessException, NoSuchFieldException,
			NBTLibDisabledException {
		return fetchDynamicField(getMinecraftPackage() + className, object,
				type);
	}

	public static Object fetchMinecraftField(String className, Object object,
			String name) throws ClassNotFoundException, IllegalAccessException,
			NoSuchFieldException, NBTLibDisabledException {
		return fetchField(getMinecraftPackage() + className, object, name);
	}

	public static Object fetchDynamicCraftbukkitField(String className,
			Object object, Object type) throws ClassNotFoundException,
			IllegalAccessException, NoSuchFieldException,
			NBTLibDisabledException {
		return fetchDynamicField(getCraftbukkitPackage() + className, object,
				type);
	}

	public static Object fetchCraftbukkitField(String className, Object object,
			String name) throws ClassNotFoundException, IllegalAccessException,
			NoSuchFieldException, NBTLibDisabledException {
		return fetchField(getCraftbukkitPackage() + className, object, name);
	}

	public static Object fetchDynamicField(String className, Object object,
			Object type) throws ClassNotFoundException, IllegalAccessException,
			NoSuchFieldException {
		return getField(Class.forName(className), parseClass(type)).get(object);
	}

	public static Object fetchField(String className, Object object, String name)
			throws ClassNotFoundException, IllegalAccessException,
			NoSuchFieldException {
		return getField(Class.forName(className), name).get(object);
	}

	public static void putDynamicMinecraftField(String className,
			Object object, Object type, Object value)
			throws ClassNotFoundException, IllegalAccessException,
			NoSuchFieldException, NBTLibDisabledException {
		putDynamicField(getMinecraftPackage() + className, object, type, value);
	}

	public static void putMinecraftField(String className, Object object,
			String name, Object value) throws ClassNotFoundException,
			IllegalAccessException, NoSuchFieldException,
			NBTLibDisabledException {
		putField(getMinecraftPackage() + className, object, name, value);
	}

	public static void putDynamicCraftbukkitField(String className,
			Object object, Object type, Object value)
			throws ClassNotFoundException, IllegalAccessException,
			NoSuchFieldException, NBTLibDisabledException {
		putDynamicField(getCraftbukkitPackage() + className, object, type,
				value);
	}

	public static void putCraftbukkitField(String className, Object object,
			String name, Object value) throws ClassNotFoundException,
			IllegalAccessException, NoSuchFieldException,
			NBTLibDisabledException {
		putField(getCraftbukkitPackage() + className, object, name, value);
	}

	public static void putDynamicField(String className, Object object,
			Object type, Object value) throws ClassNotFoundException,
			IllegalAccessException, NoSuchFieldException {
		getField(Class.forName(className), parseClass(type)).set(object, value);
	}

	public static void putField(String className, Object object, String name,
			Object value) throws ClassNotFoundException,
			IllegalAccessException, NoSuchFieldException {
		getField(Class.forName(className), name).set(object, value);
	}

	public static Field getField(Class clazz, Class type)
			throws NoSuchFieldException {
		Field[][] all = new Field[][] { clazz.getDeclaredFields(),
				clazz.getFields() };
		for (Field[] array : all) {
			for (Field f : array) {
				if (f.getType() == type) {
					f.setAccessible(true);
					return f;
				}
			}
		}
		throw new NoSuchFieldException("Class \"" + clazz.getName()
				+ "\" has no field of type " + type.getName() + "!");
	}

	public static Field getField(Class clazz, String name)
			throws NoSuchFieldException {
		try {
			Field f = clazz.getDeclaredField(name);
			f.setAccessible(true);
			return f;
		} catch (NoSuchFieldException e) {
			Field f = clazz.getField(name);
			f.setAccessible(true);
			return f;
		}
	}

	public static Object instantiateMinecraft(String className,
			Object[] paramTypes, Object... params)
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException, InvocationTargetException,
			NoSuchMethodException, NBTLibDisabledException {
		return instantiate(getMinecraftPackage() + className, paramTypes,
				params);
	}

	public static Object instantiateCraftbukkit(String className,
			Object[] paramTypes, Object... params)
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException, InvocationTargetException,
			NoSuchMethodException, NBTLibDisabledException {
		return instantiate(getCraftbukkitPackage() + className, paramTypes,
				params);
	}

	public static Object instantiate(String className, Object[] paramTypes,
			Object... params) throws ClassNotFoundException,
			IllegalAccessException, InstantiationException,
			InvocationTargetException, NoSuchMethodException {
		return getConstructor(Class.forName(className), parseClass(paramTypes))
				.newInstance(params);
	}

	public static Constructor getConstructor(Class clazz, Class... paramTypes)
			throws NoSuchMethodException {
		try {
			Constructor c = clazz.getDeclaredConstructor(paramTypes);
			c.setAccessible(true);
			return c;
		} catch (NoSuchMethodException e) {
			Constructor c = clazz.getConstructor(paramTypes);
			c.setAccessible(true);
			return c;
		}
	}

	public static Object invokeMinecraftDynamic(String className,
			Object object, Object returnType, Object[] paramTypes,
			Object... params) throws ClassNotFoundException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, NBTLibDisabledException {
		return invokeDynamic(getMinecraftPackage() + className, object,
				returnType, paramTypes, params);
	}

	public static Object invokeMinecraft(String className, Object object,
			String name, Object[] paramTypes, Object... params)
			throws ClassNotFoundException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			NBTLibDisabledException {
		return invoke(getMinecraftPackage() + className, object, name,
				paramTypes, params);
	}

	public static Object invokeCraftbukkitDynamic(String className,
			Object object, Object returnType, Object[] paramTypes,
			Object... params) throws ClassNotFoundException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, NBTLibDisabledException {
		return invokeDynamic(getCraftbukkitPackage() + className, object,
				returnType, paramTypes, params);
	}

	public static Object invokeCraftbukkit(String className, Object object,
			String name, Object[] paramTypes, Object... params)
			throws ClassNotFoundException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			NBTLibDisabledException {
		return invoke(getCraftbukkitPackage() + className, object, name,
				paramTypes, params);
	}

	public static Object invokeDynamic(String className, Object object,
			Object returnType, Object[] paramTypes, Object... params)
			throws ClassNotFoundException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return getMethod(Class.forName(className), parseClass(returnType),
				parseClass(paramTypes)).invoke(object, params);
	}

	public static Object invoke(String className, Object object, String name,
			Object[] paramTypes, Object... params)
			throws ClassNotFoundException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return getMethod(Class.forName(className), name, parseClass(paramTypes))
				.invoke(object, params);
	}

	public static Method getMethod(Class clazz, Class returnType,
			Class... params) throws NoSuchMethodException {
		Method[][] all = new Method[][] { clazz.getDeclaredMethods(),
				clazz.getMethods() };
		for (Method[] array : all) {
			label1: for (Method m : array) {
				if (m.getReturnType() == returnType) {
					Class[] args = m.getParameterTypes();
					if (args.length != params.length) {
						continue;
					}
					for (int i = 0; i < args.length; i++) {
						if (args[i] != params[i]) {
							continue label1;
						}
					}
					m.setAccessible(true);
					return m;
				}
			}
		}
		String string = "Class \"" + clazz.getName() + "\" has no method (";
		boolean first = true;
		for (Class cls : params) {
			if (first) {
				first = false;
			} else {
				string += ", ";
			}
			string += cls.getName();
		}
		string += ") returning \"" + returnType.getName() + "\"!";
		throw new NoSuchMethodException(string);
	}

	public static Method getMethod(Class clazz, String name, Class... params)
			throws NoSuchMethodException {
		try {
			Method m = clazz.getDeclaredMethod(name, params);
			m.setAccessible(true);
			return m;
		} catch (NoSuchMethodException e) {
			Method m = clazz.getMethod(name, params);
			m.setAccessible(true);
			return m;
		}
	}

	public static Class[] parseClass(Object... array)
			throws ClassNotFoundException {
		ArrayList<Class> list = new ArrayList<Class>();
		for (Object o : array) {
			list.add(parseClass(o));
		}
		return list.toArray(new Class[0]);
	}

	public static Class parseClass(Object o) throws ClassNotFoundException {
		if (o instanceof Class) {
			return (Class) o;
		} else {
			return Class.forName((String) o);
		}
	}
	
}
	
