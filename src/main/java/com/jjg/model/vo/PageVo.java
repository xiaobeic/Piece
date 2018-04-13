package com.jjg.model.vo;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.io.Serializable;

@ApiObject(name = "PageVo", description = "PageVo use for pagenation", group = "ADMIN PAGE")
public class PageVo<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	@ApiObjectField(description = "object total amount")
	private Long totalAmount;
	@ApiObjectField(description = "object total page: totalAmount/pagesize")
	private int totalPages;
	@ApiObjectField(description = "object current page")
	private int currentPage;
	@ApiObjectField(description = "object size")
	private int size;
	@ApiObjectField(description = "object")
	private T content;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

}
