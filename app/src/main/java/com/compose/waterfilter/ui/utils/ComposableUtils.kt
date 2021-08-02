package com.compose.waterfilter.ui.utils

import androidx.annotation.PluralsRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.compose.waterfilter.R


@Composable
fun quantityStringResource(@PluralsRes res: Int, quantity: Int): String {

    return LocalContext.current.resources.getQuantityString(res, quantity, quantity)
}

@Composable
fun quantityStringResourceWithFallback(@PluralsRes res: Int, quantity: Int?): String {
    return if (quantity != null)
        quantityStringResource(res = res, quantity = quantity)
    else
        stringResource(id = R.string.resource_not_available)

}