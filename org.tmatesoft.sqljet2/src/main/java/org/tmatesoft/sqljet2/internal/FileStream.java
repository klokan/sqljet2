package org.tmatesoft.sqljet2.internal;

import java.io.IOException;

public interface FileStream {

	int SECTOR_SIZE = 512;

	/* First byte past the 1GB boundary */
	long PENDING_BYTE = 0x40000000;
	long RESERVED_BYTE = (PENDING_BYTE + 1);
	long SHARED_FIRST = (PENDING_BYTE + 2);
	long SHARED_SIZE = 510;

	void close() throws IOException;

	int read(int position, Pointer pointer, int count) throws IOException;

	void write(int position, Pointer pointer, int count) throws IOException;

	void sync(boolean full) throws IOException;

	void truncate(int size) throws IOException;

	long getSize() throws IOException;

	LockType getLockType();

	boolean lock(LockType lockType) throws IOException;

	boolean unlock(final LockType lockType) throws IOException;

	boolean checkReservedLock() throws IOException;

	int getSectorSize();

	enum LockType {

		/**
		 * Not locked
		 */
		NONE,

		/**
		 * Any number of processes may hold a SHARED lock simultaneously.
		 */
		SHARED,

		/**
		 * A single process may hold a RESERVED lock on a file at any time.
		 * Other processes may hold and obtain new SHARED locks.
		 */
		RESERVED,

		/**
		 * A single process may hold a PENDING lock on a file at any one time.
		 * Existing SHARED locks may persist, but no new SHARED locks may be
		 * obtained by other processes.
		 */
		PENDING,

		/**
		 * An EXCLUSIVE lock precludes all other locks.
		 */
		EXCLUSIVE
	}

}