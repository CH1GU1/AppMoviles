package com.example.s4recyclerviewinstagram

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostView(itemView : View) : RecyclerView.ViewHolder(itemView){

    var post : Post? = null

    //Listeners
    var listener : OnPostLike? = null

    //Identificar los elementos UI components
    var posterUserName : TextView = itemView.findViewById(R.id.posterNameRow)
    var postUserImg : ImageView = itemView.findViewById(R.id.posterImageRow)
    var location : TextView = itemView.findViewById(R.id.locationRow)

    var postImg : ImageView = itemView.findViewById(R.id.postImgRow)

    var likeBtn : Button = itemView.findViewById(R.id.likeBtn)
    var commentBtn : Button = itemView.findViewById(R.id.commentBtn)
    var shareBtn : Button = itemView.findViewById(R.id.shareBtn)
    var saveBtn : Button = itemView.findViewById(R.id.saveBtn)
    var likesQ : TextView = itemView.findViewById(R.id.likesRow)
    var commntUserName : TextView = itemView.findViewById(R.id.commentUserNameRow)
    var relevanceCommnt : TextView = itemView.findViewById(R.id.relevanceCommentRow)

    var myImg : ImageView = itemView.findViewById(R.id.myImgIDRow)
    var commentField : TextView = itemView.findViewById(R.id.commentFieldRow)
    var date : TextView = itemView.findViewById(R.id.dateRow)

    init {
        likeBtn.setOnClickListener {
            post?.let {
                listener?.onPostLike(it)
            }
        }
    }

    interface OnPostLike{
        fun onPostLike(post: Post)
    }
}