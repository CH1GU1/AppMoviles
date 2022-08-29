package com.example.s4recyclerviewinstagram

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class PostsAdapter : RecyclerView.Adapter<PostView>(), PostView.OnPostLike{

    private val posts = ArrayList<Post>()

    fun addPost(post: Post){
        posts.add(post)
        notifyItemInserted(posts.size-1)
    }

    init {
        posts.add(Post("1234","Andres Andrade","Cali, Colombia","567",127,"Falcao","Melo pa, re melo", "16 de Agosto","789"))

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostView {

        //Inflater: XML lo convierte a View
        var inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.postrow, parent, false) //Este row ya es de tipo row
        val postView = PostView(row)
        postView.listener = this
        return postView
    }

    override fun onBindViewHolder(skeleton: PostView, position: Int) {
        val post = posts[position]
        skeleton.post = post
        skeleton.posterUserName.text = post.posterName
        skeleton.location.text = post.location
        skeleton.likesQ.text = post.likesNumber.toString()
        skeleton.commntUserName.text = post.commentUserName
        skeleton.relevanceCommnt.text = post.relevanceComment
        skeleton.date.text = post.date
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onPostLike(post: Post) {
        val index = posts.indexOf(post)
        val tempLikes = posts[index].likesNumber.inc()

        posts[index].likesNumber = tempLikes
        Log.e(">>>",tempLikes.toString())
        notifyItemChanged(index)
    }


}