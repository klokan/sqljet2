package org.tmatesoft.sqljet2.internal.impl;

import java.io.IOException;

import org.tmatesoft.sqljet2.internal.FileSystem;
import org.tmatesoft.sqljet2.internal.FileSystem.OpenPermission;
import org.tmatesoft.sqljet2.internal.Memory;
import org.tmatesoft.sqljet2.internal.Page;
import org.tmatesoft.sqljet2.internal.Pager;

public class DefaultPager implements Pager {

	private static final int MIN_SECTOR_SIZE = 512;
	private static final int MAX_SECTOR_SIZE = 0x0100000;

	private static final int MAX_PAGE_NUMBER = 2147483647;

	public void open(FileSystem fs, String fileName, OpenPermission permission)
			throws IOException {
		// TODO Auto-generated method stub

	}

	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	public int getPageSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setPageSize(int pageSize) {
		// TODO Auto-generated method stub

	}

	public int getPagesCount() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	public Memory readFileHeader(int count) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public Page readPage(int pageNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page lookupPage(int pageNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}