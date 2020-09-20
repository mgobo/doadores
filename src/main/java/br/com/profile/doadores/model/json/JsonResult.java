package br.com.profile.doadores.model.json;

import java.util.List;

public class JsonResult<T extends Object> {

	private long totalRecords;
	private T data;
	private List<T> collectionData;
	private String messageError;
	
	public static class Builder<T extends Object> {
		
		private long totalRecords;
		private T data;
		private List<T> collectionData;
		private String messageError;
		
		public JsonResult<T> build(){
			JsonResult<T> jsonResult = new JsonResult<>();
			jsonResult.setTotalRecords(this.totalRecords);
			jsonResult.setData(this.data);
			jsonResult.setCollectionData(collectionData);
			jsonResult.setMessageError(this.messageError);
			return jsonResult;
		}
		
		public Builder<T> withTotalRecords(long totalRecords) {
			this.totalRecords = totalRecords;
			return this;
		}
		public Builder<T> withData(T data) {
			this.data = data;
			return this;
		}
		public Builder<T> withCollectionData(List<T> collectionData) {
			this.collectionData = collectionData;
			return this;
		}
		public Builder<T> withMessageError(String messageError) {
			this.messageError = messageError;
			return this;
		}
	}
	
	public long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public List<T> getCollectionData() {
		return collectionData;
	}
	public void setCollectionData(List<T> collectionData) {
		this.collectionData = collectionData;
	}
	public String getMessageError() {
		return messageError;
	}
	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}
}
