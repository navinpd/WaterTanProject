package com.compose.waterfilter.ui.component.ringindicator

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.max

private val fgStrokeDp: Dp = 12.dp
private val bgStrokeDp: Dp = 8.dp

private enum class TransitionState { INIT_START, INIT_END, FILLED }

@Composable
fun Ring(
    modifier: Modifier = Modifier,
    bgColor: Color,
    fgColor: Color,
    fill: Float,
    fgFillCb: ((Float) -> Unit)? = null
) {
    var bgStroke: Stroke
    var fgStroke: Stroke
    with(LocalDensity.current) {
        bgStroke = remember {
            Stroke(width = bgStrokeDp.toPx())
        }
        fgStroke = remember {
            Stroke(
                width = fgStrokeDp.toPx(),
                cap = StrokeCap.Round
            )
        }
    }
    val transitionState = remember {
        MutableTransitionState(TransitionState.INIT_START)
    }
    val transition = updateTransition(
        transitionState = transitionState,
        label = "ring-anim-transition"
    )
    val bgRingAngleEdge by transition.animateFloat(
        transitionSpec = {
            tween(400, 400)
        },
        label = "bgRingEdge"
    ) { currentState ->
        if (currentState == TransitionState.INIT_START) 0.0f else 188.0f
    }
    val fgRingAngleEdge by transition.animateFloat(
        transitionSpec = {
            tween(400)
        },
        label = "fgRingEdge"
    ) { currentState ->
        if (currentState == TransitionState.FILLED) 180.0f * fill else 0.0f
    }
    val fgFill by transition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 400)
        },
        label = "fgFill"
    ) { currentState ->
        if (currentState == TransitionState.INIT_START) 0f else 100 * fill
    }
    LaunchedEffect(fgFillCb) {
        fgFillCb?.invoke(fgFill)
    }

    val maxStroke = remember {
        max(bgStroke.width, fgStroke.width)
    }
    LaunchedEffect(transitionState.currentState) {
        transitionState.targetState = when (transitionState.currentState) {
            TransitionState.INIT_START -> TransitionState.INIT_END
            else -> TransitionState.FILLED
        }
    }
    Canvas(modifier) {
        val innerRadius = (size.minDimension - maxStroke) / 2
        val halfSize = size / 2f

        val topLeft = Offset(
            x = halfSize.width - innerRadius,
            y = halfSize.height - innerRadius
        )
        val arcSize = Size(
            width = innerRadius * 2,
            height = innerRadius * 2
        )

        //Backgroud Ring
        drawRing(bgColor, -bgRingAngleEdge, bgRingAngleEdge, bgStroke, arcSize, topLeft)
        //Foreground Ring
        drawRing(
            fgColor,
            180.0f - fgRingAngleEdge,
            180.0f + fgRingAngleEdge,
            fgStroke,
            arcSize,
            topLeft
        )
    }
}

private fun DrawScope.drawRing(
    bgColor: Color,
    startAngle: Float,
    endAngle: Float,
    bgStroke: Stroke,
    arcSize: Size,
    topLeft: Offset
) {
    drawArc(
        color = bgColor,
        startAngle = startAngle - 90f,
        sweepAngle = endAngle - startAngle,
        useCenter = false,
        style = bgStroke,
        size = arcSize,
        topLeft = topLeft,
    )
}

@Preview(showBackground = true)
@Composable
fun ShowPreview() {
    Ring(
        modifier = Modifier.size(300.dp),
        bgColor = Color.Black,
        fgColor = Color.Magenta,
        fill = 0.8f
    )
}