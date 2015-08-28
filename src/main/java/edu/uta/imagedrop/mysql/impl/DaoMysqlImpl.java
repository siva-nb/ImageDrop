package edu.uta.imagedrop.mysql.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.uta.imagedrop.constants.AWSConstants;
import edu.uta.imagedrop.model.Comment;
import edu.uta.imagedrop.model.Media;

public class DaoMysqlImpl {
	private Connection connection = null;
	private DBConnectionManager dbConnectionMgr;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public int authenticate(String userName, String password) {
		dbConnectionMgr = new DBConnectionManager();

		try {
			connection = dbConnectionMgr.getConnection();
			String sql = "SELECT userid FROM users WHERE email=? and password=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.first()) {
				return resultSet.getInt(1); // userid
			}
		} catch (SQLException e) {
			return 0;
		}finally{
			try {
				dbConnectionMgr.closeConnection(connection);
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
		}
		return 0;

	}
	
	public int insertMedia(int ownerId, String fileName, String filePath){
		dbConnectionMgr = new DBConnectionManager();
		try {
			connection = dbConnectionMgr.getConnection();
			String sql = "INSERT INTO media(ownerid, filename, filepath) VALUES(?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, ownerId);
			preparedStatement.setString(2, fileName);
			preparedStatement.setString(3, filePath);

			return preparedStatement.executeUpdate();

			
		} catch (SQLException e) {
			return 0;
		}finally{
			try {
				dbConnectionMgr.closeConnection(connection);
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
		}
	}
	
	public int insertComment(int ownerId, int mediaId, String comment){
		dbConnectionMgr = new DBConnectionManager();
		try {
			connection = dbConnectionMgr.getConnection();
			String sql = "INSERT INTO comment(ownerid, mediaid, comment) VALUES(?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, ownerId);
			preparedStatement.setInt(2, mediaId);
			preparedStatement.setString(3, comment);

			return preparedStatement.executeUpdate();

			
		} catch (SQLException e) {
			return 0;
		}finally{
			try {
				dbConnectionMgr.closeConnection(connection);
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
		}
	}
	
	public List<Media> getImageList(int userId){
		Media media = null;
		List<Media> mediaList = new ArrayList<Media>();
		
		dbConnectionMgr = new DBConnectionManager();
		
		try{
			connection = dbConnectionMgr.getConnection();
			String sql = "SELECT mediaid,ownerid,firstname,filename,filepath FROM media INNER JOIN users ON media.ownerid = users.userid ORDER BY mediaid DESC";
			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				media = new Media();
				media.setMediaId(resultSet.getInt(1));
				media.setOwnerId(resultSet.getInt(2));
				media.setFirstName(resultSet.getString(3));
				media.setFileName(resultSet.getString(4));
				media.setFilePath(AWSConstants.FILE_PREFIX+resultSet.getString(5));
				if(userId == media.getOwnerId())
					media.setOwnerProp(1);
				else
					media.setOwnerProp(0);
				mediaList.add(media);
			}
			return mediaList;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			try {
				dbConnectionMgr.closeConnection(connection);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		
	}
	
	public List<Media> getMedia(int userId, int mediaId){
		Media media = null;
		List<Media> mediaList = new ArrayList<Media>();
		
		dbConnectionMgr = new DBConnectionManager();
		
		try{
			connection = dbConnectionMgr.getConnection();
			String sql = "SELECT mediaid,ownerid,firstname,filename,filepath FROM media INNER JOIN users ON media.ownerid = users.userid WHERE mediaID = ? ORDER BY mediaid DESC";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, mediaId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				media = new Media();
				media.setMediaId(resultSet.getInt(1));
				media.setOwnerId(resultSet.getInt(2));
				media.setFirstName(resultSet.getString(3));
				media.setFileName(resultSet.getString(4));
				media.setFilePath(AWSConstants.FILE_PREFIX+resultSet.getString(5));
				if(userId == media.getOwnerId())
					media.setOwnerProp(1);
				else
					media.setOwnerProp(0);
				mediaList.add(media);
			}
			return mediaList;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			try {
				dbConnectionMgr.closeConnection(connection);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		
	}
	
	public List<Comment> getComment(int userId, int mediaId){
		Comment comment = null;
		List<Comment> commentList = new ArrayList<Comment>();
		
		dbConnectionMgr = new DBConnectionManager();
		
		
		try{
			connection = dbConnectionMgr.getConnection();
			String sql = "SELECT commentid, firstname, comment FROM comment INNER JOIN users on ownerid = userid WHERE mediaid = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, mediaId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				comment = new Comment();
				comment.setCommentId(resultSet.getInt(1));
				comment.setFullName(resultSet.getString(2));
				comment.setComment(resultSet.getString(3));
				commentList.add(comment);
			}
			
			return commentList;
			
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			try {
				dbConnectionMgr.closeConnection(connection);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
}
