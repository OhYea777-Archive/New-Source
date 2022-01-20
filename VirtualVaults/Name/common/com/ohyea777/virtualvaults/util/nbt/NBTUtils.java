package com.ohyea777.virtualvaults.util.nbt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

public class NBTUtils {

	static {
		_log = Logger.getLogger("Minecraft");
		clinit();
	}

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

	public static Class<?> getMinecraftClass(String className) throws ClassNotFoundException, NBTUtilsDisabledException {
		return Class.forName(getMinecraftPackage() + className);
	}

	public static Class<?> getCraftbukkitClass(String className) throws ClassNotFoundException, NBTUtilsDisabledException {
		return Class.forName(getCraftbukkitPackage() + className);
	}

	public static String getPackageVersion() {
		return _pversion;
	}

	public static String getMinecraftVersion() {
		return Bukkit.getVersion();
	}

	public static String getMinecraftPackage() throws NBTUtilsDisabledException {
		if (_disabled) {
			throw new NBTUtilsDisabledException();
		}
		return _minecraft;
	}

	public static String getCraftbukkitPackage() throws NBTUtilsDisabledException {
		if (_disabled) {
			throw new NBTUtilsDisabledException();
		}
		return _craftbukkit;
	}

	public static Object invoke(Method method, Object object, Object... args) {
		try {
			Object obj = method.invoke(object, args);
			if (obj != null)
				return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object invoke(Field field, Object object) {
		try {
			Object obj = field.get(object);
			if (obj != null)
				return obj;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Method getMinecraftMethod(String clz, String method, Class<?>... classes) {
		try {
			Class<?> obj = getMinecraftClass(clz);
			Method meth = obj.getDeclaredMethod(method, classes);
			meth.setAccessible(true);
			return meth;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Field getMinecraftField(String clz, String field) {
		try {
			Class<?> obj = getMinecraftClass(clz);
			Field f = obj.getDeclaredField(field);
			f.setAccessible(true);
			return f;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Method getCraftbukkitMethod(String clz, String method, Class<?>... classes) {
		try {
			Class<?> obj = getCraftbukkitClass(clz);
			Method meth = obj.getDeclaredMethod(method, classes);
			meth.setAccessible(true);
			return meth;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Field getCraftbukkitField(String clz, String field) {
		try {
			Class<?> obj = getCraftbukkitClass(clz);
			Field f = obj.getDeclaredField(field);
			f.setAccessible(true);
			return f;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object getMinecraftInstance(String clz) {
		try {
			return getMinecraftClass(clz).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ItemStack fromBase64(String data) {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
		Object tag = readNbt(new DataInputStream(inputStream));
		return getCraftMirror(tag);
	}

	public static String toBase64(ItemStack item) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		DataOutputStream dataOutput = new DataOutputStream(outputStream);
		Object tag = getMinecraftInstance("NBTTagCompound");

		if (tag != null) {
			Object craft = getCraftVersion(item);

			if (craft != null) {
				try {
					Object itemStack = invoke(getCraftbukkitMethod("inventory.CraftItemStack", "asNMSCopy", ItemStack.class), null, craft);

					if (itemStack != null) {
						invoke(getMinecraftMethod("ItemStack", "save", getMinecraftClass("NBTTagCompound")), itemStack, tag);
					} else {
						Bukkit.broadcastMessage("Item is Null!");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			writeNbt(tag, dataOutput);
		}

		return Base64Coder.encodeLines(outputStream.toByteArray());
	}

	public static void writeNbt(Object tag, DataOutput output) {
		Method meth;
		try {
			meth = getMinecraftMethod("NBTCompressedStreamTools", "a", getMinecraftClass("NBTTagCompound"), DataOutput.class);
		} catch (ClassNotFoundException | NBTUtilsDisabledException e) {
			e.printStackTrace();
			return;
		}
		invoke(meth, null, tag, output);
	}

	public static Object readNbt(DataInput input) {
		try {
			Method meth = getMinecraftMethod("NBTCompressedStreamTools", "a", DataInput.class, getMinecraftClass("NBTReadLimiter"));
			Field field = getMinecraftField("NBTReadLimiter", "a");

			if (meth != null && field != null) {
				return invoke(meth, null, input, invoke(field, null));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ItemStack getCraftMirror(Object tag) {
		try {
			Method craftMirror = getCraftbukkitMethod("inventory.CraftItemStack", "asCraftMirror", getMinecraftClass("ItemStack"));
			Method createStack = getMinecraftMethod("ItemStack", "createStack", getMinecraftClass("NBTTagCompound"));

			if (craftMirror != null && createStack != null) {
				Object itemStack = invoke(createStack, null, tag);
				if (itemStack != null) {
					Object obj = invoke(craftMirror, null, itemStack);
					if (obj instanceof ItemStack) {
						return (ItemStack) obj;
					}
				}
			}
		} catch (ClassNotFoundException | NBTUtilsDisabledException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object getCraftVersion(ItemStack item) {
		try {
			return getCraftbukkitClass("inventory.CraftItemStack").cast(item);
		} catch (ClassCastException | ClassNotFoundException ignored) { } catch (NBTUtilsDisabledException e) {
			e.printStackTrace();
		}
		Bukkit.broadcastMessage("Null? " + (item == null));
		return invoke(getCraftbukkitMethod("inventory.CraftItemStack", "asCraftCopy", ItemStack.class), null, item);
	}

}