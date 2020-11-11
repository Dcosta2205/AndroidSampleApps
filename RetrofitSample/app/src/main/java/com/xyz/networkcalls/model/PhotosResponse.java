package com.xyz.networkcalls.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class PhotosResponse implements Serializable {

	/*
	 * @SerializedName("albumId") is useful if the name of the member variable is different i.e whatever key
	 * is written inside serializedName will match the JSON response body.
	 */
	@SerializedName("albumId")
	private int albumId;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("url")
	private String url;

	@SerializedName("thumbnailUrl")
	private String thumbnailUrl;

	public void setAlbumId(int albumId){
		this.albumId = albumId;
	}

	public int getAlbumId(){
		return albumId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@Override
	public String toString() {
		return "PhotosResponse{" +
				"albumId=" + albumId +
				", id=" + id +
				", title='" + title + '\'' +
				", url='" + url + '\'' +
				", thumbnailUrl='" + thumbnailUrl + '\'' +
				'}';
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setThumbnailUrl(String thumbnailUrl){
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getThumbnailUrl(){
		return thumbnailUrl;
	}

}