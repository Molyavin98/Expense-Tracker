package com.molyavin.expensetracker.presentation.screen.statistics

import android.graphics.Typeface
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.molyavin.expensetracker.design_system.AppTheme
import com.molyavin.expensetracker.design_system.Spacing
import com.molyavin.expensetracker.di.scope.Injector
import com.molyavin.expensetracker.presentation.screen.BaseScreen
import com.molyavin.expensetracker.presentation.viewmodel.statistics.StatisticsViewModel

class StatisticsScreen : BaseScreen() {

    override val viewModel: StatisticsViewModel by lazy {
        Injector.INSTANCE.provideStatisticsViewModel()
    }

    @Composable
    override fun Content() {
        val pieChartData = PieChartData(
            slices = listOf(
                PieChartData.Slice("Balance", 30f, AppTheme.colors.highlight.purple),
                PieChartData.Slice("Income", 60f, AppTheme.colors.primaryHover),
                PieChartData.Slice("Expense", 10f, AppTheme.colors.error),
            ),
            plotType = PlotType.Pie,
        )

        val pieChartConfig = PieChartConfig(
            isAnimationEnable = true,
            showSliceLabels = true,
            sliceLabelTextSize = 20.sp,
            isSumVisible = true,
            labelVisible = true,
            animationDuration = 2000,
            backgroundColor = AppTheme.colors.background
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            PieChart(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center),
                pieChartData,
                pieChartConfig
            )
        }

    }
}