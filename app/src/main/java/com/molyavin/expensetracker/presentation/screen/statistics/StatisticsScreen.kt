package com.molyavin.expensetracker.presentation.screen.statistics

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.molyavin.expensetracker.design_system.AppTheme
import com.molyavin.expensetracker.di.scope.Injector
import com.molyavin.expensetracker.presentation.BaseScreen
import com.molyavin.expensetracker.presentation.BaseSettingsScreen
import com.molyavin.expensetracker.presentation.ObserveLifecycleEvents

private val viewModel: StatisticsViewModel = Injector.INSTANCE.provideStatisticsViewModel()

@Composable
fun StatisticsScreen(navController: NavController) {
    viewModel.ObserveLifecycleEvents(LocalLifecycleOwner.current.lifecycle)
    val isLoading by viewModel.isLoading.collectAsState()
    BaseSettingsScreen(isLoading = isLoading) {
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
