package edu.uta.imagedrop.action;

import java.io.File;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.opensymphony.xwork2.ActionSupport;

import edu.uta.imagedrop.constants.AWSConstants;
import edu.uta.imagedrop.interceptor.UserAware;
import edu.uta.imagedrop.model.Comment;
import edu.uta.imagedrop.model.Media;
import edu.uta.imagedrop.model.User;
import edu.uta.imagedrop.mysql.impl.DaoMysqlImpl;

public class HomeAction extends ActionSupport implements UserAware{

	private static final long serialVersionUID = 1L;
	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;
 
	private User user;
	private List<Media> mediaList;
	private List<Comment> commentList;
	
	private int mediaId;
	private String commentBox;
	
	public String listAll() throws Exception{
		DaoMysqlImpl daoMysqlImpl  = new DaoMysqlImpl();
		mediaList = daoMysqlImpl.getImageList(user.getUserId());
		fileUploadContentType="text/png";
		return SUCCESS;
	}
	
	public List<Media> getMediaList() {
		return mediaList;
	}

	public void setMediaList(List<Media> mediaList) {
		this.mediaList = mediaList;
	}

	public String createNew() throws Exception{
		return SUCCESS;
	}
	
	public String showMedia() throws Exception{
		DaoMysqlImpl daoMysqlImpl  = new DaoMysqlImpl();
		mediaList = daoMysqlImpl.getMedia(user.getUserId(),mediaId);
		setCommentList(daoMysqlImpl.getComment(user.getUserId(), mediaId)); 
		return SUCCESS;
	}
	
	public String createNewComment() throws Exception{
		DaoMysqlImpl daoMysqlImpl  = new DaoMysqlImpl();
		daoMysqlImpl.insertComment(user.getUserId(), mediaId, commentBox);
		mediaList = daoMysqlImpl.getMedia(user.getUserId(),mediaId);
		setCommentList(daoMysqlImpl.getComment(user.getUserId(), mediaId));
		return SUCCESS;
	}
	
	@Override
	public String execute() throws Exception {
		AWSCredentials credentials = new BasicAWSCredentials(AWSConstants.AWS_ACCESS_KEY,AWSConstants.AWS_SECRET_KEY);
		AmazonS3 s3Client = new AmazonS3Client(credentials);
		long epoch = System.currentTimeMillis()/1000;
		
		//String[] tokens = fileUploadFileName.split(".");
		String fname = fileUploadFileName.substring(0, fileUploadFileName.lastIndexOf('.'));
		
		String key =fname+"_"+epoch+".png";
		s3Client.putObject(new PutObjectRequest(AWSConstants.bucketName, key, fileUpload).withCannedAcl(CannedAccessControlList.PublicRead));
		DaoMysqlImpl daoMysqlImpl = new DaoMysqlImpl();
		daoMysqlImpl.insertMedia(user.getUserId(), fileUploadFileName, key);
		return SUCCESS;
	}
	
	public String getFileUploadContentType() {
		return fileUploadContentType;
	}
 
	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}
 
	public String getFileUploadFileName() {
		return fileUploadFileName;
	}
 
	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}
 
	public File getFileUpload() {
		return fileUpload;
	}
 
	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser(){
		return user;
	}

	public int getMediaId() {
		return mediaId;
	}

	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
	}

	public String getCommentBox() {
		return commentBox;
	}

	public void setCommentBox(String commentBox) {
		this.commentBox = commentBox;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
}
