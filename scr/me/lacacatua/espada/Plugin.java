package me.lacacatua.espada;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public enum ParticleEffect
{
  HUGE_EXPLODE, LARGE_EXPLODE, FIREWORK_SPARK, AIR_BUBBLE, SUSPEND, DEPTH_SUSPEND, TOWN_AURA, CRITICAL_HIT, MAGIC_CRITICAL_HIT, MOB_SPELL, MOB_SPELL_AMBIENT, SPELL, INSTANT_SPELL, BLUE_SPARKLE, NOTE_BLOCK, ENDER, ENCHANTMENT_TABLE, EXPLODE, FIRE, LAVA_SPARK, FOOTSTEP, SPLASH, LARGE_SMOKE, CLOUD, REDSTONE_DUST, SNOWBALL_HIT, DRIP_WATER, DRIP_LAVA, SNOW_DIG, SLIME, HEART, ANGRY_VILLAGER, GREEN_SPARKLE, ICONCRACK, TILECRACK;

  private String name;
  private int id;

  String getName()
  {
    return this.name;
  }

  int getId()
  {
    return this.id;
  }

  public static void sendToPlayer(ParticleEffect effect, Player player, Location location, float offsetX, float offsetY, float offsetZ, float speed, int count)
  {
    try
    {
      Object packet = createPacket(effect, location, offsetX, offsetY, 
        offsetZ, speed, count);
      sendPacket(player, packet);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public static void sendToPlayer(ParticleEffect effect, Location location, float offsetX, float offsetY, float offsetZ, float speed, int count)
  {
    try
    {
      Object packet = createPacket(effect, location, offsetX, offsetY, 
        offsetZ, speed, count);
      for (Player player : Bukkit.getOnlinePlayers()) {
        sendPacket(player, packet);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  private static Object createPacket(ParticleEffect effect, Location location, float offsetX, float offsetY, float offsetZ, float speed, int count)
    throws Exception
  {
    if (count <= 0) {
      count = 1;
    }
    Class packetClass = getCraftClass("PacketPlayOutWorldParticles");
    Object packet = packetClass.getConstructor(new Class[] { String.class, Float.TYPE, 
      Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, 
      Float.TYPE, Float.TYPE, Integer.TYPE }).newInstance(new Object[] { 
      effect.name, 
      Float.valueOf((float)location.getX()), Float.valueOf((float)location.getY()), 
      Float.valueOf((float)location.getZ()), Float.valueOf(offsetX), Float.valueOf(offsetY), Float.valueOf(offsetZ), Float.valueOf(speed), 
      Integer.valueOf(count) });
    return packet;
  }

  private static void sendPacket(Player p, Object packet)
    throws Exception
  {
    Object eplayer = getHandle(p);
    Field playerConnectionField = eplayer.getClass().getField(
      "playerConnection");
    Object playerConnection = playerConnectionField.get(eplayer);
    for (Method m : playerConnection.getClass().getMethods()) {
      if (!(m.getName().equalsIgnoreCase("sendPacket")))
        continue;
      m.invoke(playerConnection, new Object[] { packet });
      return;
    }
  }

  private static Object getHandle(Entity entity)
  {
    try
    {
      Method entity_getHandle = entity.getClass().getMethod("getHandle", new Class[0]);
      return entity_getHandle.invoke(entity, new Object[0]);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    return null;
  }

  private static Class<?> getCraftClass(String name)
  {
    String version = getVersion() + ".";
    String className = "net.minecraft.server." + version + name;
    Class clazz = null;
    try
    {
      clazz = Class.forName(className);
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
    return clazz;
  }

  private static String getVersion()
  {
    return Bukkit.getServer().getClass().getPackage().getName()
      .replace(".", ",").split(",")[3];
  }
}
