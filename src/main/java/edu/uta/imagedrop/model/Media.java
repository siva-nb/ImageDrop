package edu.uta.imagedrop.model;

public class Media {
	private int ownerId;
	private int mediaId;
	private String firstName;
	private String fileName;
	private String filePath;
	private int ownerProp;

	
	public int getOwnerProp() {
		return ownerProp;
	}
	public void setOwnerProp(int ownerProp) {
		this.ownerProp = ownerProp;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public int getMediaId() {
		return mediaId;
	}
	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
}
