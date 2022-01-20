package com.ohyea777.virtualvaults.util.nbt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

/**
 * @author PC
 *
 */
/**
 * @author PC
 *
 */
public class NBTUtils {

	static {
		_log = Logger.getLogger("Minecraft");
		clinit();
	}

	public static final Logger _log;
	private static String _craftbukkit;
	private static boolean _disabled;

	private static void clinit() {
		_disabled = false;
	}

	public static boolean enabled() {
		return !_disabled;
	}

	public static Class<?> getCraftbukkitClass(String className) throws ClassNotFoundException, NBTUtilsDisabledException {
		return Class.forName("org.bukkit.craftbukkit.v1_6_R3." + className);
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
	
	public static Object getInstance(String clz) {
		try {
			return Class.forName(clz).newInstance();
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
	
	@SuppressWarnings("unused")
	private static boolean getClass(String clz) {
		try {
			Class.forName(clz);
			return true;
		} catch(ClassNotFoundException e) {
			return false;
		}
	}
	
	public static void list(String clz) {
		try {
			Bukkit.broadcastMessage("Class: " + clz);
			Class<?> c = Class.forName(clz);
			for (Method m : c.getDeclaredMethods()) {
				StringBuilder str = new StringBuilder();
				for (Class<?> cl : m.getParameterTypes()) {
					str.append(" : ");
					str.append(cl.getSimpleName());
				}
				
				Bukkit.broadcastMessage("Method: " + m.getName() + ", Params: " + str);
			}
		} catch (Exception e) { }
	}

	public static String toBase64(ItemStack item) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		DataOutputStream dataOutput = new DataOutputStream(outputStream);
		Object tag = getInstance("net.minecraft.nbt.NBTTagCompound");
		
		if (tag != null) {
			Object craft = getCraftVersion(item);

			if (craft != null) {
				try {
					Object itemStack = invoke(getCraftbukkitMethod("inventory.CraftItemStack", "asNMSCopy", ItemStack.class), null, craft);

					if (itemStack != null) {
						Method meth = Class.forName("net.minecraft.item.ItemStack").getDeclaredMethod("func_77955_b", Class.forName("net.minecraft.nbt.NBTTagCompound"));
						if (meth != null)
							invoke(meth, itemStack, tag);
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
		try {
			Method meth = Class.forName("net.minecraft.nbt.CompressedStreamTools").getDeclaredMethod("func_74800_a", Class.forName("net.minecraft.nbt.NBTTagCompound"), DataOutput.class);
			invoke(meth, null, tag, output);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static Object readNbt(DataInput input) {
		try {
			Method meth = Class.forName("net.minecraft.nbt.CompressedStreamTools").getDeclaredMethod("func_74794_a", DataInput.class);
			return invoke(meth, null, input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ItemStack getCraftMirror(Object tag) {
		try {
			Method craftMirror = getCraftbukkitMethod("inventory.CraftItemStack", "asCraftMirror", Class.forName("net.minecraft.item.ItemStack"));
			Method createStack = Class.forName("net.minecraft.item.ItemStack").getDeclaredMethod("func_77949_a", Class.forName("net.minecraft.nbt.NBTTagCompound"));

			if (craftMirror != null && createStack != null) {
				Object itemStack = invoke(createStack, null, tag);
				if (itemStack != null) {
					Object obj = invoke(craftMirror, null, itemStack);
					if (obj instanceof ItemStack) {
						return (ItemStack) obj;
					}
				}
			}
		} catch (Exception e) {
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