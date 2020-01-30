package com.anita.doordashdemo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Restaurant(var idParam: Int, var n: String, var desc: String, var statusParam: String) {

    @SerializedName("is_time_surging")
    @Expose
     var isTimeSurging: Boolean? = null
    @SerializedName("max_order_size")
    @Expose
     var maxOrderSize: Any? = null
    @SerializedName("delivery_fee")
    @Expose
     var deliveryFee: Int? = null
    @SerializedName("max_composite_score")
    @Expose
     var maxCompositeScore: Int? = null
    @SerializedName("id")
    @Expose
     var id: Int? = idParam
    @SerializedName("average_rating")
    @Expose
     var averageRating: Double? = null
    @SerializedName("composite_score")
    @Expose
     var compositeScore: Int? = null
    @SerializedName("status_type")
    @Expose
     var statusType: String? = null
    @SerializedName("is_only_catering")
    @Expose
     var isOnlyCatering: Boolean? = null
    @SerializedName("status")
    @Expose
     var status: String? = statusParam
    @SerializedName("number_of_ratings")
    @Expose
     var numberOfRatings: Int? = null
    @SerializedName("asap_time")
    @Expose
     var asapTime: Int? = null
    @SerializedName("description")
    @Expose
     var description: String? = desc
    @SerializedName("tags")
    @Expose
     var tags: List<String>? = null
    @SerializedName("yelp_review_count")
    @Expose
     var yelpReviewCount: Int? = null
    @SerializedName("business_id")
    @Expose
     var businessId: Int? = null
    @SerializedName("extra_sos_delivery_fee")
    @Expose
     var extraSosDeliveryFee: Int? = null
    @SerializedName("yelp_rating")
    @Expose
     var yelpRating: Double? = null
    @SerializedName("cover_img_url")
    @Expose
     var coverImgUrl: String? = null
    @SerializedName("header_img_url")
    @Expose
     var headerImgUrl: String? = null
    @SerializedName("price_range")
    @Expose
     var priceRange: Int? = null
    @SerializedName("slug")
    @Expose
     var slug: String? = null
    @SerializedName("name")
    @Expose
     var name: String? = n
    @SerializedName("is_newly_added")
    @Expose
     var isNewlyAdded: Boolean? = null
    @SerializedName("url")
    @Expose
     var url: String? = null
    @SerializedName("service_rate")
    @Expose
     var serviceRate: Double? = null
    @SerializedName("promotion")
    @Expose
     var promotion: Any? = null
}