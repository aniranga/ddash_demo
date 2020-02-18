package com.anita.doordashdemo.home

import com.anita.doordashdemo.data.model.Restaurant

open class DiscoverPageListItem(val type: ListItemViewType){

    enum class ListItemViewType {
        OBJ_LIST_ITEM,
        LOAD_MORE_PROGRESS_ITEM;

        companion object {
            private val values = values()

            fun fromInt(index: Int): ListItemViewType {
                if (index > -1 && index < values.size) {
                    return values[index]
                }

                throw IllegalArgumentException("Illegal index value " + index + " for enum " + ListItemViewType::class.java.simpleName)
            }
        }
    }
}

class RestaurantListItem(type: ListItemViewType, var obj: Restaurant): DiscoverPageListItem(type)

class LoadMoreProgressListItem(type: ListItemViewType): DiscoverPageListItem(type)