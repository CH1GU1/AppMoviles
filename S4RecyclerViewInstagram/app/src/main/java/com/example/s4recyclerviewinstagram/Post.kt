package com.example.s4recyclerviewinstagram

import java.util.*

class Post {

    var posterUserImgID:String
    var posterName:String
    var location:String
    var postImgID:String
    var likesNumber:Int
    var commentUserName:String
    var relevanceComment:String
    var date:String
    var myImgID:String

    constructor(
        posterUserImgID: String,
        posterName: String,
        location: String,
        postImgID: String,
        likesNumber: Int,
        commentUserName: String,
        relevanceComment: String,
        date: String,
        myImgID: String
    ) {
        this.posterUserImgID = posterUserImgID
        this.posterName = posterName
        this.location = location
        this.postImgID = postImgID
        this.likesNumber = likesNumber
        this.commentUserName = commentUserName
        this.relevanceComment = relevanceComment
        this.date = date
        this.myImgID = myImgID
    }
}