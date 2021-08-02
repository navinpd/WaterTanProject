package com.compose.waterfilter.ui.component.ringindicator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.compose.waterfilter.R
import com.compose.waterfilter.ui.theme.ColorRingBackground
import com.compose.waterfilter.ui.theme.ColorRingForeground
import com.compose.waterfilter.ui.utils.quantityStringResourceWithFallback
import kotlin.math.roundToInt

@Composable
fun RingIndicator(
    modifier: Modifier = Modifier,
    fill: Float,
    daysInUse: Int?
) {
    var percentage by remember { mutableStateOf(0) }
    ConstraintLayout(modifier) {
        val (percentageRef, daysInUseRef, labelRef) = createRefs()
        Ring(
            modifier = modifier.fillMaxSize(),
            bgColor = MaterialTheme.colors.ColorRingBackground,
            fgColor = MaterialTheme.colors.ColorRingForeground,
            fill = fill
        ) { fgFill ->
            percentage = (fgFill * 100).roundToInt()
        }
        Text(
            modifier = Modifier.constrainAs(percentageRef) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = stringResource(
                id = R.string.ring_indicator_remaining_percentage_sign,
                percentage
            ),
            style = MaterialTheme.typography.h2,
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier.constrainAs(daysInUseRef) {
                bottom.linkTo(percentageRef.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = quantityStringResourceWithFallback(
                R.plurals.ring_indicator_day_in_use,
                daysInUse
            ).uppercase(),
            style = MaterialTheme.typography.subtitle1,
        )

        Text(
            modifier = Modifier.constrainAs(labelRef) {
                top.linkTo(percentageRef.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = stringResource(id = R.string.ring_indicator_remaining_capacity_label).uppercase(),
            style = MaterialTheme.typography.overline,
            fontSize = 12.sp
        )
    }
}