package com.xyz.networkcalls.network;

import com.xyz.networkcalls.model.PhotosResponse;
import com.xyz.networkcalls.model.Post;
import com.xyz.networkcalls.model.PostQuery;
import com.xyz.networkcalls.model.RegisterResponseModel;
import com.xyz.networkcalls.model.RegisterUserRequestModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Write all the API calls in this interface
 */
public interface ApiService {

    // Make an Api call and specify the type of HTTP request (i.e GET, POST ....) along with the end points.
    @GET("/photos")
    Call<List<PhotosResponse>> getPhotos();

    /*
    {postId} means that this value has to be passed by the user and the value passed by the user is consumed
    by @Path("postId")
     */
    @GET("/posts/{postId}")
    Call<Post> getPost(@Path("postId") int id);

    /*
    If the Api has any query ( usually represented by ?. Example : https://jsonplaceholder.typicode.com/comments?postId=1)
    then it is represented using @Query(" ")
     */
    @GET("/comments")
    Call<List<PostQuery>> getListOfPost(@Query("postId") int postId);

    @POST("/posts")
    Call<RegisterResponseModel> getRegisteredUserDetails(@Body RegisterUserRequestModel registerUserRequestModel);
}
