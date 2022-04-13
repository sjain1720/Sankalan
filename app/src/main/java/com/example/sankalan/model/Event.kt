package com.example.sankalan.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
data class Event (
    @StringRes val eventTitleId: Int,
    @DrawableRes val eventPosterId: Int,
    @StringRes val eventTypeId: Int,
    @StringRes val eventNosId: Int,
    @StringRes val eventVenueId: Int,
    @StringRes val eventTimingId: Int
)