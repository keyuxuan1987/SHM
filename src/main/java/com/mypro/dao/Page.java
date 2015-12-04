package com.mypro.dao;

/**
 * 页面对象，用于支持分页查询
 * 
 * @author Kelvin
 * @version 1.0
 * 
 */
public class Page {

	/**
	 * 默认每页记录数
	 */
	public static final int DEFAULT_PAGE_SIZE = 10;

	/**
	 * 默认起始页
	 */
	public static final int DEFAULT_START_INDEX = 1;

	private int pageIndex;// 当前页
	private int pageSize;// 每页记录数
	private int totalCount;// 总记录数
	private int pageCount;// 总页数

	/**
	 * 构造方法，根据当前页和每页记录数，创建页面对象
	 * 
	 * @param pageIndex
	 *            当前页
	 * @param pageSize
	 *            每页记录数
	 */
	public Page(int pageIndex, int pageSize) {
		if (pageIndex < 1)
			pageIndex = 1;
		if (pageSize < 1)
			pageSize = 1;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}

	/**
	 * 重载构造方法，默认每页记录数
	 * 
	 * @param pageIndex
	 *            当前页
	 */
	public Page(int pageIndex) {
		this(pageIndex, DEFAULT_PAGE_SIZE);
	}

	/**
	 * 重载构造方法，默认起始页，默认每页记录数
	 */
	public Page() {
		this(DEFAULT_START_INDEX, DEFAULT_PAGE_SIZE);
	}

	/**
	 * 返回当前页第一行记录的起始位置
	 * 
	 * @return 当前页的第一行记录
	 */
	public int getFirstResult() {
		return (pageIndex - 1) * pageSize;
	}

	/**
	 * 是否有上一页
	 * 
	 * @return 返回true表示有上一页，返回false表示已经是第一页
	 */
	public boolean hasPrevious() {
		return pageIndex > 1;
	}

	/**
	 * 是否有下一页
	 * 
	 * @return 返回true表示有下一页，返回false表示已经是最后一页
	 */
	public boolean hasNext() {
		return pageIndex < pageCount;
	}

	/**
	 * 根据总记录数初始化页面信息
	 * 
	 * @param totalCount
	 *            总记录数
	 */
	public void init(int totalCount) {

		this.totalCount = totalCount;

		/* 计算总页面数 */
		this.pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);

		if (pageIndex > pageCount) {
			/* 若总记录数不为0，且当前页超过最大页面数 */
			pageIndex = pageCount;
		}
	}

	/**
	 * 记录集是否为空
	 * 
	 * @return 返回true表示记录为空，返回false表示记录不为空
	 */
	public boolean isEmpty() {
		return totalCount == 0;
	}

	/**
	 * getter() method
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getPageCount() {
		return pageCount;
	}

}
