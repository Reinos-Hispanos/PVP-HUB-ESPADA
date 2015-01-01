package me.lacacatua.espada;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;

public class ReflectionHandler
{
  public static Class<?> getClass(String name, PackageType type)
    throws Exception
  {
    return Class.forName(type + "." + name);
  }

  public static Class<?> getClass(String name, SubPackageType type)
    throws Exception
  {
    return Class.forName(type + "." + name);
  }

  public static Constructor<?> getConstructor(Class<?> clazz, Class<?>[] parameterTypes)
  {
    Class[] p = DataType.convertToPrimitive(parameterTypes);
    for (Constructor c : clazz.getConstructors()) {
      if (DataType.equalsArray(DataType.convertToPrimitive(c.getParameterTypes()), p)) {
        return c;
      }
    }
    return null;
  }

  public static Constructor<?> getConstructor(String className, PackageType type, Class<?>[] parameterTypes)
    throws Exception
  {
    return getConstructor(getClass(className, type), parameterTypes);
  }

  public static Constructor<?> getConstructor(String className, SubPackageType type, Class<?>[] parameterTypes)
    throws Exception
  {
    return getConstructor(getClass(className, type), parameterTypes);
  }

  public static Object newInstance(Class<?> clazz, Object[] args)
    throws Exception
  {
    return getConstructor(clazz, DataType.convertToPrimitive(args)).newInstance(args);
  }

  public static Object newInstance(String className, PackageType type, Object[] args)
    throws Exception
  {
    return newInstance(getClass(className, type), args);
  }

  public static Object newInstance(String className, SubPackageType type, Object[] args)
    throws Exception
  {
    return newInstance(getClass(className, type), args);
  }

  public static Method getMethod(Class<?> clazz, String name, Class<?>[] parameterTypes)
  {
    Class[] p = DataType.convertToPrimitive(parameterTypes);
    for (Method m : clazz.getMethods()) {
      if ((m.getName().equals(name)) && (DataType.equalsArray(DataType.convertToPrimitive(m.getParameterTypes()), p))) {
        return m;
      }
    }
    return null;
  }

  public static Method getMethod(String className, PackageType type, String name, Class<?>[] parameterTypes)
    throws Exception
  {
    return getMethod(getClass(className, type), name, parameterTypes);
  }

  public static Method getMethod(String className, SubPackageType type, String name, Class<?>[] parameterTypes)
    throws Exception
  {
    return getMethod(getClass(className, type), name, parameterTypes);
  }

  public static Object invokeMethod(String name, Object instance, Object[] args)
    throws Exception
  {
    return getMethod(instance.getClass(), name, DataType.convertToPrimitive(args)).invoke(instance, args);
  }

  public static Object invokeMethod(Class<?> clazz, String name, Object instance, Object[] args)
    throws Exception
  {
    return getMethod(clazz, name, DataType.convertToPrimitive(args)).invoke(instance, args);
  }

  public static Object invokeMethod(String className, PackageType type, String name, Object instance, Object[] args)
    throws Exception
  {
    return invokeMethod(getClass(className, type), name, instance, args);
  }

  public static Object invokeMethod(String className, SubPackageType type, String name, Object instance, Object[] args)
    throws Exception
  {
    return invokeMethod(getClass(className, type), name, instance, args);
  }

  public static Field getField(Class<?> clazz, String name)
    throws Exception
  {
    Field f = clazz.getField(name);
    f.setAccessible(true);
    return f;
  }

  public static Field getField(String className, PackageType type, String name)
    throws Exception
  {
    return getField(getClass(className, type), name);
  }

  public static Field getField(String className, SubPackageType type, String name)
    throws Exception
  {
    return getField(getClass(className, type), name);
  }

  public static Field getDeclaredField(Class<?> clazz, String name)
    throws Exception
  {
    Field f = clazz.getDeclaredField(name);
    f.setAccessible(true);
    return f;
  }

  public static Field getDeclaredField(String className, PackageType type, String name)
    throws Exception
  {
    return getDeclaredField(getClass(className, type), name);
  }

  public static Field getDeclaredField(String className, SubPackageType type, String name)
    throws Exception
  {
    return getDeclaredField(getClass(className, type), name);
  }

  public static Object getValue(Object instance, String fieldName)
    throws Exception
  {
    return getField(instance.getClass(), fieldName).get(instance);
  }

  public static Object getValue(Class<?> clazz, Object instance, String fieldName)
    throws Exception
  {
    return getField(clazz, fieldName).get(instance);
  }

  public static Object getValue(String className, PackageType type, Object instance, String fieldName)
    throws Exception
  {
    return getValue(getClass(className, type), instance, fieldName);
  }

  public static Object getValue(String className, SubPackageType type, Object instance, String fieldName)
    throws Exception
  {
    return getValue(getClass(className, type), instance, fieldName);
  }

  public static Object getDeclaredValue(Object instance, String fieldName)
    throws Exception
  {
    return getDeclaredField(instance.getClass(), fieldName).get(instance);
  }

  public static Object getDeclaredValue(Class<?> clazz, Object instance, String fieldName)
    throws Exception
  {
    return getDeclaredField(clazz, fieldName).get(instance);
  }

  public static Object getDeclaredValue(String className, PackageType type, Object instance, String fieldName)
    throws Exception
  {
    return getDeclaredValue(getClass(className, type), instance, fieldName);
  }

  public static Object getDeclaredValue(String className, SubPackageType type, Object instance, String fieldName)
    throws Exception
  {
    return getDeclaredValue(getClass(className, type), instance, fieldName);
  }

  public static void setValue(Object instance, String fieldName, Object fieldValue)
    throws Exception
  {
    Field f = getField(instance.getClass(), fieldName);
    f.set(instance, fieldValue);
  }

  public static void setValue(Object instance, FieldPair pair)
    throws Exception
  {
    setValue(instance, pair.getName(), pair.getValue());
  }

  public static void setValue(Class<?> clazz, Object instance, String fieldName, Object fieldValue)
    throws Exception
  {
    Field f = getField(clazz, fieldName);
    f.set(instance, fieldValue);
  }

  public static void setValue(Class<?> clazz, Object instance, FieldPair pair)
    throws Exception
  {
    setValue(clazz, instance, pair.getName(), pair.getValue());
  }

  public static void setValue(String className, PackageType type, Object instance, String fieldName, Object fieldValue)
    throws Exception
  {
    setValue(getClass(className, type), instance, fieldName, fieldValue);
  }

  public static void setValue(String className, PackageType type, Object instance, FieldPair pair)
    throws Exception
  {
    setValue(className, type, instance, pair.getName(), pair.getValue());
  }

  public static void setValue(String className, SubPackageType type, Object instance, String fieldName, Object fieldValue)
    throws Exception
  {
    setValue(getClass(className, type), instance, fieldName, fieldValue);
  }

  public static void setValue(String className, SubPackageType type, Object instance, FieldPair pair)
    throws Exception
  {
    setValue(className, type, instance, pair.getName(), pair.getValue());
  }

  public static void setValues(Object instance, FieldPair[] pairs)
    throws Exception
  {
    for (FieldPair pair : pairs)
      setValue(instance, pair);
  }

  public static void setValues(Class<?> clazz, Object instance, FieldPair[] pairs)
    throws Exception
  {
    for (FieldPair pair : pairs)
      setValue(clazz, instance, pair);
  }

  public static void setValues(String className, PackageType type, Object instance, FieldPair[] pairs)
    throws Exception
  {
    setValues(getClass(className, type), instance, pairs);
  }

  public static void setValues(String className, SubPackageType type, Object instance, FieldPair[] pairs)
    throws Exception
  {
    setValues(getClass(className, type), instance, pairs);
  }

  public static void setDeclaredValue(Object instance, String fieldName, Object fieldValue)
    throws Exception
  {
    Field f = getDeclaredField(instance.getClass(), fieldName);
    f.set(instance, fieldValue);
  }

  public static void setDeclaredValue(Object instance, FieldPair pair)
    throws Exception
  {
    setDeclaredValue(instance, pair.getName(), pair.getValue());
  }

  public static void setDeclaredValue(Class<?> clazz, Object instance, String fieldName, Object fieldValue)
    throws Exception
  {
    Field f = getDeclaredField(clazz, fieldName);
    f.set(instance, fieldValue);
  }

  public static void setDeclaredValue(Class<?> clazz, Object instance, FieldPair pair)
    throws Exception
  {
    setDeclaredValue(clazz, instance, pair.getName(), pair.getValue());
  }

  public static void setDeclaredValue(String className, PackageType type, Object instance, String fieldName, Object fieldValue)
    throws Exception
  {
    setDeclaredValue(getClass(className, type), instance, fieldName, fieldValue);
  }

  public static void setDeclaredValue(String className, PackageType type, Object instance, FieldPair pair)
    throws Exception
  {
    setDeclaredValue(className, type, instance, pair.getName(), pair.getValue());
  }

  public static void setDeclaredValue(String className, SubPackageType type, Object instance, String fieldName, Object fieldValue)
    throws Exception
  {
    setDeclaredValue(getClass(className, type), instance, fieldName, fieldValue);
  }

  public static void setDeclaredValue(String className, SubPackageType type, Object instance, FieldPair pair)
    throws Exception
  {
    setDeclaredValue(className, type, instance, pair.getName(), pair.getValue());
  }

  public static void setDeclaredValues(Object instance, FieldPair[] pairs)
    throws Exception
  {
    for (FieldPair pair : pairs)
      setDeclaredValue(instance, pair);
  }

  public static void setDeclaredValues(Class<?> clazz, Object instance, FieldPair[] pairs)
    throws Exception
  {
    for (FieldPair pair : pairs)
      setDeclaredValue(clazz, instance, pair);
  }

  public static void setDeclaredValues(String className, PackageType type, Object instance, FieldPair[] pairs)
    throws Exception
  {
    setDeclaredValues(getClass(className, type), instance, pairs);
  }

  public static void setDeclaredValues(String className, SubPackageType type, Object instance, FieldPair[] pairs)
    throws Exception
  {
    setDeclaredValues(getClass(className, type), instance, pairs);
  }

  public static enum DataType
  {
    BYTE, SHORT, INTEGER, LONG, CHARACTER, FLOAT, DOUBLE, BOOLEAN;

    private static final Map<Class<?>, DataType> CLASS_MAP;
    private final Class<?> primitive;
    private final Class<?> reference;

    static
    {
      CLASS_MAP = new HashMap();
      for (DataType t : values())
      {
        CLASS_MAP.put(t.primitive, t);
        CLASS_MAP.put(t.reference, t);
      }
    }

    public Class<?> getPrimitive()
    {
      return this.primitive;
    }

    public Class<?> getReference()
    {
      return this.reference;
    }

    public static DataType fromClass(Class<?> c)
    {
      return ((DataType)CLASS_MAP.get(c));
    }

    public static Class<?> getPrimitive(Class<?> c)
    {
      DataType t = fromClass(c);
      return ((t == null) ? c : t.getPrimitive());
    }

    public static Class<?> getReference(Class<?> c)
    {
      DataType t = fromClass(c);
      return ((t == null) ? c : t.getReference());
    }

    public static Class<?>[] convertToPrimitive(Class<?>[] classes)
    {
      int length = (classes == null) ? 0 : classes.length;
      Class[] types = new Class[length];
      for (int i = 0; i < length; ++i) {
        types[i] = getPrimitive(classes[i]);
      }
      return types;
    }

    public static Class<?>[] convertToPrimitive(Object[] objects)
    {
      int length = (objects == null) ? 0 : objects.length;
      Class[] types = new Class[length];
      for (int i = 0; i < length; ++i) {
        types[i] = getPrimitive(objects[i].getClass());
      }
      return types;
    }

    public static boolean equalsArray(Class<?>[] a1, Class<?>[] a2)
    {
      if ((a1 == null) || (a2 == null) || (a1.length != a2.length)) {
        return false;
      }
      for (int i = 0; i < a1.length; ++i) {
        if ((!(a1[i].equals(a2[i]))) && (!(a1[i].isAssignableFrom(a2[i])))) {
          return false;
        }
      }
      return true;
    }
  }

  public final class FieldPair
  {
    private final String name;
    private final Object value;

    public FieldPair(String paramString, Object paramObject)
    {
      this.name = paramString;
      this.value = value;
    }

    public String getName()
    {
      return this.name;
    }

    public Object getValue()
    {
      return this.value; }
  }

  public static enum PackageType {
    MINECRAFT_SERVER, CRAFTBUKKIT;

    private final String name;

    public String getName()
    {
      return this.name;
    }

    public String toString()
    {
      return this.name;
    }
  }

  public static enum PacketType
  {
    HANDSHAKING_IN_SET_PROTOCOL, LOGIN_IN_ENCRYPTION_BEGIN, LOGIN_IN_START, LOGIN_OUT_DISCONNECT, LOGIN_OUT_ENCRYPTION_BEGIN, LOGIN_OUT_SUCCESS, PLAY_IN_ABILITIES, PLAY_IN_ARM_ANIMATION, PLAY_IN_BLOCK_DIG, PLAY_IN_BLOCK_PLACE, PLAY_IN_CHAT, PLAY_IN_CLIENT_COMMAND, PLAY_IN_CLOSE_WINDOW, PLAY_IN_CUSTOM_PAYLOAD, PLAY_IN_ENCHANT_ITEM, PLAY_IN_ENTITY_ACTION, PLAY_IN_FLYING, PLAY_IN_HELD_ITEM_SLOT, PLAY_IN_KEEP_ALIVE, PLAY_IN_LOOK, PLAY_IN_POSITION, PLAY_IN_POSITION_LOOK, PLAY_IN_SET_CREATIVE_SLOT, PLAY_IN_SETTINGS, PLAY_IN_STEER_VEHICLE, PLAY_IN_TAB_COMPLETE, PLAY_IN_TRANSACTION, PLAY_IN_UPDATE_SIGN, PLAY_IN_USE_ENTITY, PLAY_IN_WINDOW_CLICK, PLAY_OUT_ABILITIES, PLAY_OUT_ANIMATION, PLAY_OUT_ATTACH_ENTITY, PLAY_OUT_BED, PLAY_OUT_BLOCK_ACTION, PLAY_OUT_BLOCK_BREAK_ANIMATION, PLAY_OUT_BLOCK_CHANGE, PLAY_OUT_CHAT, PLAY_OUT_CLOSE_WINDOW, PLAY_OUT_COLLECT, PLAY_OUT_CRAFT_PROGRESS_BAR, PLAY_OUT_CUSTOM_PAYLOAD, PLAY_OUT_ENTITY, PLAY_OUT_ENTITY_DESTROY, PLAY_OUT_ENTITY_EFFECT, PLAY_OUT_ENTITY_EQUIPMENT, PLAY_OUT_ENTITY_HEAD_ROTATION, PLAY_OUT_ENTITY_LOOK, PLAY_OUT_ENTITY_METADATA, PLAY_OUT_ENTITY_STATUS, PLAY_OUT_ENTITY_TELEPORT, PLAY_OUT_ENTITY_VELOCITY, PLAY_OUT_EXPERIENCE, PLAY_OUT_EXPLOSION, PLAY_OUT_GAME_STATE_CHANGE, PLAY_OUT_HELD_ITEM_SLOT, PLAY_OUT_KEEP_ALIVE, PLAY_OUT_KICK_DISCONNECT, PLAY_OUT_LOGIN, PLAY_OUT_MAP, PLAY_OUT_MAP_CHUNK, PLAY_OUT_MAP_CHUNK_BULK, PLAY_OUT_MULTI_BLOCK_CHANGE, PLAY_OUT_NAMED_ENTITY_SPAWN, PLAY_OUT_NAMED_SOUND_EFFECT, PLAY_OUT_OPEN_SIGN_EDITOR, PLAY_OUT_OPEN_WINDOW, PLAY_OUT_PLAYER_INFO, PLAY_OUT_POSITION, PLAY_OUT_REL_ENTITY_MOVE, PLAY_OUT_REL_ENTITY_MOVE_LOOK, PLAY_OUT_REMOVE_ENTITY_EFFECT, PLAY_OUT_RESPAWN, PLAY_OUT_SCOREBOARD_DISPLAY_OBJECTIVE, PLAY_OUT_SCOREBOARD_OBJECTIVE, PLAY_OUT_SCOREBOARD_SCORE, PLAY_OUT_SCOREBOARD_TEAM, PLAY_OUT_SET_SLOT, PLAY_OUT_SPAWN_ENTITY, PLAY_OUT_SPAWN_ENTITY_EXPERIENCE_ORB, PLAY_OUT_SPAWN_ENTITY_LIVING, PLAY_OUT_SPAWN_ENTITY_PAINTING, PLAY_OUT_SPAWN_ENTITY_WEATHER, PLAY_OUT_SPAWN_POSITION, PLAY_OUT_STATISTIC, PLAY_OUT_TAB_COMPLETE, PLAY_OUT_TILE_ENTITY_DATA, PLAY_OUT_TRANSACTION, PLAY_OUT_UPDATE_ATTRIBUTES, PLAY_OUT_UPDATE_HEALTH, PLAY_OUT_UPDATE_SIGN, PLAY_OUT_UPDATE_TIME, PLAY_OUT_WINDOW_ITEMS, PLAY_OUT_WORLD_EVENT, PLAY_OUT_WORLD_PARTICLES, STATUS_IN_PING, STATUS_IN_START, STATUS_OUT_PONG, STATUS_OUT_SERVER_INFO;

    private final String name;
    private Class<?> packet;

    public String getName()
    {
      return getName();
    }

    public Class<?> getPacket()
      throws Exception
    {
      return ((this.packet == null) ? (this.packet = ReflectionHandler.getClass(this.name, ReflectionHandler.PackageType.MINECRAFT_SERVER)) : this.packet);
    }
  }

  public static enum SubPackageType
  {
    BLOCK, CHUNKIO, COMMAND, CONVERSATIONS, ENCHANTMENS, ENTITY, EVENT, GENERATOR, HELP, INVENTORY, MAP, METADATA, POTION, PROJECTILES, SCHEDULER, SCOREBOARD, UPDATER, UTIL;

    private final String name;

    public String getName()
    {
      return this.name;
    }

    public String toString()
    {
      return this.name;
    }
  }
}
