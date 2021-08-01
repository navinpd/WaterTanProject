package com.compose.waterfilter.ui.component.ringindicator

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.max

private val fgStrokeDp: Dp = 12.dp
private val bgStrokeDp: Dp = 8.dp

@Composable
fun Ring(modifier: Modifier = Modifier, bgColor: Color, fgColor: Color, fill: Float) {
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
    val fgRingAngleEdge = remember(fill) {
        180.0f * fill
    }
    val maxStroke = remember {
        max(bgStroke.width, fgStroke.width)
    }
    Canvas(
        modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
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
        drawRing(bgColor, 0.0f, 360f, bgStroke, arcSize, topLeft)
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