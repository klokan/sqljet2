package org.tmatesoft.sqljet2.internal.system.impl;

import org.tmatesoft.sqljet2.internal.system.Memory;
import org.tmatesoft.sqljet2.internal.system.MemoryBlock;
import org.tmatesoft.sqljet2.internal.system.Pointer;

public abstract class AbstractMemory implements MemoryBlock {

	final public static int U_BYTE = MAX_UNSIGNED_BYTE;
	final public static long U_BYTE_L = MAX_UNSIGNED_BYTE;
	final public static int U_SHORT = MAX_UNSIGNED_SHORT;
	final public static long U_INT = MAX_UNSIGNED_INT;

	final public static short toUnsignedByte(final short value) {
		return (short) (U_BYTE & value);
	}

	final public static int toUnsignedShort(final int value) {
		return U_SHORT & value;
	}

	final public static long toUnsignedInt(final long value) {
		return U_INT & value;
	}

	final public static byte fromUnsignedByte(final short value) {
		return (byte) value;
	}

	final public static short fromUnsignedShort(final int value) {
		return (short) value;
	}

	final public static int fromUnsignedInt(final long value) {
		return (int) value;
	}

	final public static boolean toBoolean(final byte value) {
		return value != 0;
	}

	final public static byte fromBoolean(final boolean value) {
		return value ? (byte) 1 : (byte) 0;
	}

	final public static char getChar(final Memory m, final int a) {
		return (char) (((m.getByte(a + 1) & U_BYTE)) | ((m.getByte(a) & U_BYTE) << 8));
	}

	final public static short getShort(final Memory m, final int a) {
		return (short) (((m.getByte(a + 1) & U_BYTE)) | ((m.getByte(a) & U_BYTE) << 8));
	}

	final public static int getInt(final Memory m, final int a) {
		return ((m.getByte(a + 3) & U_BYTE)) | ((m.getByte(a + 2) & U_BYTE) << 8)
				| ((m.getByte(a + 1) & U_BYTE) << 16) | ((m.getByte(a) & U_BYTE) << 24);
	}

	final public static float getFloat(final Memory m, final int a) {
		final int i = ((m.getByte(a + 3) & U_BYTE))
				| ((m.getByte(a + 2) & U_BYTE) << 8)
				| ((m.getByte(a + 1) & U_BYTE) << 16) + ((m.getByte(a) & U_BYTE) << 24);
		return Float.intBitsToFloat(i);
	}

	final public static long getLong(final Memory m, final int a) {
		return ((m.getByte(a + 7) & U_BYTE_L)) | ((m.getByte(a + 6) & U_BYTE_L) << 8)
				| ((m.getByte(a + 5) & U_BYTE_L) << 16)
				+ ((m.getByte(a + 4) & U_BYTE_L) << 24)
				| ((m.getByte(a + 3) & U_BYTE_L) << 32)
				| ((m.getByte(a + 2) & U_BYTE_L) << 40)
				+ ((m.getByte(a + 1) & U_BYTE_L) << 48)
				| ((m.getByte(a) & U_BYTE_L) << 56);
	}

	final public static double getDouble(final Memory m, final int a) {
		final long j = ((m.getByte(a + 7) & U_BYTE_L))
				| ((m.getByte(a + 6) & U_BYTE_L) << 8)
				| ((m.getByte(a + 5) & U_BYTE_L) << 16)
				+ ((m.getByte(a + 4) & U_BYTE_L) << 24)
				| ((m.getByte(a + 3) & U_BYTE_L) << 32)
				| ((m.getByte(a + 2) & U_BYTE_L) << 40)
				+ ((m.getByte(a + 1) & U_BYTE_L) << 48)
				| ((m.getByte(a) & U_BYTE_L) << 56);
		return Double.longBitsToDouble(j);
	}

	final public static void putChar(final Memory m, final int a, final char v) {
		m.putByte(a + 1, (byte) (v));
		m.putByte(a, (byte) (v >>> 8));
	}

	final public static void putShort(final Memory m, final int a, final short v) {
		m.putByte(a + 1, (byte) (v));
		m.putByte(a, (byte) (v >>> 8));
	}

	final public static void putInt(final Memory m, final int a, final int v) {
		m.putByte(a + 3, (byte) (v));
		m.putByte(a + 2, (byte) (v >>> 8));
		m.putByte(a + 1, (byte) (v >>> 16));
		m.putByte(a, (byte) (v >>> 24));
	}

	final public static void putFloat(final Memory m, final int a, final float v) {
		final int i = Float.floatToIntBits(v);
		m.putByte(a + 3, (byte) (i));
		m.putByte(a + 2, (byte) (i >>> 8));
		m.putByte(a + 1, (byte) (i >>> 16));
		m.putByte(a, (byte) (i >>> 24));
	}

	final public static void putLong(final Memory m, final int a, final long v) {
		m.putByte(a + 7, (byte) (v));
		m.putByte(a + 6, (byte) (v >>> 8));
		m.putByte(a + 5, (byte) (v >>> 16));
		m.putByte(a + 4, (byte) (v >>> 24));
		m.putByte(a + 3, (byte) (v >>> 32));
		m.putByte(a + 2, (byte) (v >>> 40));
		m.putByte(a + 1, (byte) (v >>> 48));
		m.putByte(a, (byte) (v >>> 56));
	}

	final public static void putDouble(final Memory m, final int a,
			final double v) {
		final long j = Double.doubleToLongBits(v);
		m.putByte(a + 7, (byte) (j));
		m.putByte(a + 6, (byte) (j >>> 8));
		m.putByte(a + 5, (byte) (j >>> 16));
		m.putByte(a + 4, (byte) (j >>> 24));
		m.putByte(a + 3, (byte) (j >>> 32));
		m.putByte(a + 2, (byte) (j >>> 40));
		m.putByte(a + 1, (byte) (j >>> 48));
		m.putByte(a, (byte) (j >>> 56));
	}

	final public Pointer getPointer(final int address) {
		return new MemoryPointer(this, address);
	}

	public Pointer getBegin() {
		return getPointer(0);
	}

	public Pointer getEnd() {
		return getPointer(getSize());
	}

	final public boolean getBoolean(final int address) {
		return toBoolean(getByte(address));
	}

	final public void putBoolean(final int address, final boolean value) {
		putByte(address, fromBoolean(value));
	}

	final public short getUnsignedByte(final int address) {
		return toUnsignedByte(getByte(address));
	}

	final public void putUnsignedByte(final int address, final short value) {
		putByte(address, fromUnsignedByte(value));
	}

	final public int getUnsignedShort(final int address) {
		return toUnsignedShort(getShort(address));
	}

	final public void putUnsignedShort(final int address, final int value) {
		putShort(address, fromUnsignedShort(value));
	}

	final public long getUnsignedInt(final int address) {
		return toUnsignedInt(getInt(address));
	}

	final public void putUnsignedInt(final int address, final long value) {
		putInt(address, fromUnsignedInt(value));
	}

	final public void clear() {
		fill((byte) 0);
	}

}
