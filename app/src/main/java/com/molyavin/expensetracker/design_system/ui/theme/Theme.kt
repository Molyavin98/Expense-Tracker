import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import com.molyavin.expensetracker.design_system.AppTheme
import com.molyavin.expensetracker.design_system.LocalAppColors
import com.molyavin.expensetracker.design_system.LocalAppShapes
import com.molyavin.expensetracker.design_system.LocalAppTypography
import com.molyavin.expensetracker.design_system.ui.theme.expenseTrackerDarkColors
import com.molyavin.expensetracker.design_system.ui.theme.expenseTrackerDarkTextSelectionColors
import com.molyavin.expensetracker.design_system.ui.theme.expenseTrackerLightColors
import com.molyavin.expensetracker.design_system.ui.theme.expenseTrackerLightTextSelectionColors
import com.molyavin.expensetracker.design_system.ui.theme.expenseTrackerShapes
import com.molyavin.expensetracker.design_system.ui.theme.expenseTrackerTypography

@Composable
internal fun ExpenseTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) expenseTrackerDarkColors else expenseTrackerLightColors
    val textSelectionColors =
        if (darkTheme) expenseTrackerDarkTextSelectionColors else expenseTrackerLightTextSelectionColors
    val rippleIndication = rememberRipple()
    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppTypography provides expenseTrackerTypography,
        LocalAppShapes provides expenseTrackerShapes,
        LocalIndication provides rippleIndication,
        LocalRippleTheme provides AppRippleTheme,
        LocalContentAlpha provides ContentAlpha.high,
        LocalTextSelectionColors provides textSelectionColors,
        content = content
    )
}

@Immutable
private object AppRippleTheme : RippleTheme {

    @Composable
    override fun defaultColor() = RippleTheme.defaultRippleColor(
        contentColor = LocalContentColor.current,
        lightTheme = AppTheme.colors.isLight
    )

    @Composable
    override fun rippleAlpha() = RippleTheme.defaultRippleAlpha(
        contentColor = LocalContentColor.current,
        lightTheme = AppTheme.colors.isLight
    )
}