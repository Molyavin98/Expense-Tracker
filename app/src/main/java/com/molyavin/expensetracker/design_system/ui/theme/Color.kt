package com.molyavin.expensetracker.design_system.ui.theme

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.molyavin.expensetracker.design_system.AppColors
import com.molyavin.expensetracker.design_system.HighlightColors
import com.molyavin.expensetracker.design_system.OnBackgroundColors
import com.molyavin.expensetracker.design_system.OnSurfaceColors

internal val expenseTrackerLightTextSelectionColors = TextSelectionColors(
    handleColor = ExpenseTrackerColors.Primary,
    backgroundColor = ExpenseTrackerColors.Primary.copy(alpha = 0.4f)
)

internal val expenseTrackerDarkTextSelectionColors = TextSelectionColors(
    handleColor = LalafoDarkColors.Primary,
    backgroundColor = LalafoDarkColors.Primary.copy(alpha = 0.4f)
)

internal val expenseTrackerLightColors: AppColors
    @Composable
    get() {
        return AppColors(
            primary = ExpenseTrackerColors.Primary,
            primaryHover = ExpenseTrackerColors.PrimaryHover,
            secondary = ExpenseTrackerColors.Secondary,
            secondaryHover = ExpenseTrackerColors.SecondaryHover,
            tertiary = ExpenseTrackerColors.Tertiary,
            error = ExpenseTrackerColors.Error,
            isLight = true,

            background = ExpenseTrackerColors.Background,
            onBackground = OnBackgroundColors(
                primary = ExpenseTrackerColors.OnBackgroundColors.Primary,
                secondary = ExpenseTrackerColors.OnBackgroundColors.Secondary,
                mediumGrey = ExpenseTrackerColors.OnBackgroundColors.MediumGrey,
                grey = ExpenseTrackerColors.OnBackgroundColors.Grey01,
                lightGrey = ExpenseTrackerColors.OnBackgroundColors.LightGrey01,
                extraLightGrey = ExpenseTrackerColors.OnBackgroundColors.ExtraLightGrey01,
                modal = ExpenseTrackerColors.OnBackgroundColors.Modal
            ),

            surface = ExpenseTrackerColors.Surface,
            onSurface = OnSurfaceColors(
                primary = ExpenseTrackerColors.OnSurfaceColors.Primary,
                secondary = ExpenseTrackerColors.OnSurfaceColors.Secondary,
                mediumGrey = ExpenseTrackerColors.OnSurfaceColors.MediumGrey,
                grey = ExpenseTrackerColors.OnSurfaceColors.Grey01,
                lightGrey = ExpenseTrackerColors.OnSurfaceColors.LightGrey01,
                modal = ExpenseTrackerColors.OnSurfaceColors.Modal
            ),
            highlight = HighlightColors(
                purple = ExpenseTrackerColors.Purple,
                lightPurple = ExpenseTrackerColors.LightPurple,
                extraLightPurple = ExpenseTrackerColors.ExtraLightPurple,

                darkBlue = ExpenseTrackerColors.DarkBlue,
                lightDarkBlue = ExpenseTrackerColors.LightDarkBlue,
                extraLightDarkBlue = ExpenseTrackerColors.ExtraLightDarkBlue,

                blue = ExpenseTrackerColors.Blue,
                lightBlue = ExpenseTrackerColors.LightBlue,
                extraLightBlue = ExpenseTrackerColors.ExtraLightBlue,

                mintGreen = ExpenseTrackerColors.MintGreen,
                lightMintGreene = ExpenseTrackerColors.LightMintGreen,
                extraLightMintGreene = ExpenseTrackerColors.ExtraLightGreen,

                grassGreen = ExpenseTrackerColors.GrassGreen,
                lightGrassGreen = ExpenseTrackerColors.LightGrassGreen,
                extraLightGrassGreen = ExpenseTrackerColors.ExtraLightGrassGreen,

                yellow = ExpenseTrackerColors.Yellow,
                lightYellow = ExpenseTrackerColors.LightYellow,
                extraLightYellow = ExpenseTrackerColors.ExtraLightYellow,

                orange = ExpenseTrackerColors.Orange,
                lightOrange = ExpenseTrackerColors.LightOrange,
                extraLightOrange = ExpenseTrackerColors.ExtraLightOrange,

                red = ExpenseTrackerColors.Red,
                lightRed = ExpenseTrackerColors.LightRed,
                extraLightRed = ExpenseTrackerColors.ExtraLightRed,

                barbiePink = ExpenseTrackerColors.BarbiePink,
                lightBarbiePink = ExpenseTrackerColors.LightBarbiePink,
                extraLightBarbiePink = ExpenseTrackerColors.ExtraLightBarbiePink,
                darkBlueHover = ExpenseTrackerColors.DarkBlueHover
            ),
        )
    }

internal val expenseTrackerDarkColors: AppColors
    @Composable
    get() {
        return AppColors(
            primary = LalafoDarkColors.Primary,
            primaryHover = LalafoDarkColors.PrimaryHover,
            secondary = LalafoDarkColors.Secondary,
            secondaryHover = LalafoDarkColors.SecondaryHover,
            tertiary = LalafoDarkColors.Tertiary,
            error = LalafoDarkColors.Error,
            isLight = false,

            background = LalafoDarkColors.Background,
            onBackground = OnBackgroundColors(
                primary = LalafoDarkColors.OnBackgroundColors.Primary,
                secondary = LalafoDarkColors.OnBackgroundColors.Secondary,
                mediumGrey = LalafoDarkColors.OnBackgroundColors.MediumGrey,
                grey = LalafoDarkColors.OnBackgroundColors.Grey01,
                lightGrey = LalafoDarkColors.OnBackgroundColors.LightGrey01,
                extraLightGrey = LalafoDarkColors.OnBackgroundColors.ExtraLightGrey01,
                modal = LalafoDarkColors.OnBackgroundColors.Modal
            ),

            surface = LalafoDarkColors.Surface,
            onSurface = OnSurfaceColors(
                primary = LalafoDarkColors.OnSurfaceColors.Primary,
                secondary = LalafoDarkColors.OnSurfaceColors.Secondary,
                mediumGrey = LalafoDarkColors.OnSurfaceColors.MediumGrey,
                grey = LalafoDarkColors.OnSurfaceColors.Grey01,
                lightGrey = LalafoDarkColors.OnSurfaceColors.LightGrey01,
                modal = LalafoDarkColors.OnSurfaceColors.Modal
            ),
            highlight = HighlightColors(
                purple = LalafoDarkColors.Purple,
                lightPurple = LalafoDarkColors.LightPurple,
                extraLightPurple = LalafoDarkColors.ExtraLightPurple,

                darkBlue = LalafoDarkColors.DarkBlue,
                lightDarkBlue = LalafoDarkColors.LightDarkBlue,
                extraLightDarkBlue = LalafoDarkColors.ExtraLightDarkBlue,

                blue = LalafoDarkColors.Blue,
                lightBlue = LalafoDarkColors.LightBlue,
                extraLightBlue = LalafoDarkColors.ExtraLightBlue,

                mintGreen = LalafoDarkColors.MintGreen,
                lightMintGreene = LalafoDarkColors.LightMintGreen,
                extraLightMintGreene = LalafoDarkColors.ExtraLightGreen,

                grassGreen = LalafoDarkColors.GrassGreen,
                lightGrassGreen = LalafoDarkColors.LightGrassGreen,
                extraLightGrassGreen = LalafoDarkColors.ExtraLightGrassGreen,

                yellow = LalafoDarkColors.Yellow,
                lightYellow = LalafoDarkColors.LightYellow,
                extraLightYellow = LalafoDarkColors.ExtraLightYellow,

                orange = LalafoDarkColors.Orange,
                lightOrange = LalafoDarkColors.LightOrange,
                extraLightOrange = LalafoDarkColors.ExtraLightOrange,

                red = LalafoDarkColors.Red,
                lightRed = LalafoDarkColors.LightRed,
                extraLightRed = LalafoDarkColors.ExtraLightRed,

                barbiePink = LalafoDarkColors.BarbiePink,
                lightBarbiePink = LalafoDarkColors.LightBarbiePink,
                extraLightBarbiePink = LalafoDarkColors.ExtraLightBarbiePink,

                darkBlueHover = LalafoDarkColors.DarkBlueHover
            ),
        )
    }

internal object ExpenseTrackerColors {
    // Primary
    val Primary = Color(0xff22CA46)
    val PrimaryHover = Color(0xff43D262)
    val Secondary = Color(0xffFF2366)
    val SecondaryHover = Color(0xffFF447D)
    val Tertiary = Color(0xffEE374F)
    val Error = Color(0xffEE374F)
    val Surface = Color(0xffF0F2F7)
    val Background = Color(0xFFFFFFFF)

    // Highlight
    val Purple = Color(0xff6C4AE9)
    val LightPurple = Color(0xffDCC9F8)
    val ExtraLightPurple = Color(0xffF3EDFC)
    val DarkBlue = Color(0xff0B78E3)
    val DarkBlueHover = Color(0xff2A8FF2)
    val LightDarkBlue = Color(0xffB5D6F6)
    val ExtraLightDarkBlue = Color(0xffE6F1FC)
    val Blue = Color(0xff15B2F1)
    val LightBlue = Color(0xffB8E7FA)
    val ExtraLightBlue = Color(0xffE7F7FD)
    val MintGreen = Color(0xff11BC91)
    val LightMintGreen = Color(0xffB7EADE)
    val ExtraLightGreen = Color(0xffE7F8F4)
    val GrassGreen = Color(0xff7ED321)
    val LightGrassGreen = Color(0xffD9F2BD)
    val ExtraLightGrassGreen = Color(0xffE8F9EC)
    val Yellow = Color(0xffFFC620)
    val LightYellow = Color(0xffFFEEBB)
    val ExtraLightYellow = Color(0xffFFFAE9)
    val Orange = Color(0xffFF5A00)
    val LightOrange = Color(0xffFFCDB2)
    val ExtraLightOrange = Color(0xffFFEEE5)
    val Red = Color(0xffEE374F)
    val LightRed = Color(0xffFFBDD1)
    val ExtraLightRed = Color(0xffFFE9EF)
    val BarbiePink = Color(0xffFD51D9)
    val LightBarbiePink = Color(0xffFECAF3)
    val ExtraLightBarbiePink = Color(0xffFEEDFB)

    object OnBackgroundColors {
        val Primary = Color(0xff0A1331)
        val Secondary = Color(0xff737D9B)
        val MediumGrey = Color(0xffA1A8BD)
        val Grey01 = Color(0xffD9DDE7)
        val LightGrey01 = Color(0xffF0F2F7)
        val ExtraLightGrey01 = Color(0xffF7F8FB)
        val Modal = Color(0xffFFFFFF)
    }

    object OnSurfaceColors {
        val Primary = Color(0xff0A1331)
        val Secondary = Color(0xff737D9B)
        val MediumGrey = Color(0xffA1A8BD)
        val Grey01 = Color(0xffD9DDE7)
        val LightGrey01 = Color(0xffF0F2F7)
        val Modal = Color(0xffFFFFFF)
    }
}

internal object LalafoDarkColors {
    // Primary
    val Primary = Color(0xff64DA7E)
    val PrimaryHover = Color(0xff43D262)
    val Secondary = Color(0xffFF3D77)
    val SecondaryHover = Color(0xffFF447D)
    val Tertiary = Color(0xffF04F64)
    val Error = Color(0xffF04F64)
    val Surface = Color(0xff303541)
    val Background = Color(0xff141417)

    // Highlight
    val Purple = Color(0xff7E60EC)
    val LightPurple = Color(0xff493A81)
    val ExtraLightPurple = Color(0xff250A4D)
    val DarkBlue = Color(0xff1484F4)
    val LightDarkBlue = Color(0xff144C85)
    val ExtraLightDarkBlue = Color(0xff0C3661)
    val Blue = Color(0xff7DD2F7)
    val LightBlue = Color(0xff5D99B3)
    val ExtraLightBlue = Color(0xff07465F)
    val MintGreen = Color(0xff13D3A3)
    val LightMintGreen = Color(0xff13745D)
    val ExtraLightGreen = Color(0xff1B5C4D)
    val GrassGreen = Color(0xff8ADE2F)
    val LightGrassGreen = Color(0xff4F7923)
    val ExtraLightGrassGreen = Color(0xff23562E)
    val Yellow = Color(0xffFFCE3A)
    val LightYellow = Color(0xff897128)
    val ExtraLightYellow = Color(0xff5D4600)
    val Orange = Color(0xffFF6A1A)
    val LightOrange = Color(0xff893F18)
    val ExtraLightOrange = Color(0xff662400)
    val Red = Color(0xffF04F64)
    val LightRed = Color(0xff82323D)
    val ExtraLightRed = Color(0xff5B001B)
    val BarbiePink = Color(0xffFD6ADD)
    val LightBarbiePink = Color(0xff883F7A)
    val ExtraLightBarbiePink = Color(0xff883F7A)
    val DarkBlueHover = Color(0xff2A8FF2)

    object OnBackgroundColors {
        val Primary = Color(0xffFFFFFF)
        val Secondary = Color(0xffA1A8BD)
        val MediumGrey = Color(0xff6C7183)
        val Grey01 = Color(0xff53596E)
        val LightGrey01 = Color(0xff303541)
        val ExtraLightGrey01 = Color(0xff292E3B)
        val Modal = Color(0xff292E3B)
    }

    object OnSurfaceColors {
        val Primary = Color(0xffFFFFFF)
        val Secondary = Color(0xffA1A8BD)
        val MediumGrey = Color(0xff6C7183)
        val Grey01 = Color(0xff464B5A)
        val LightGrey01 = Color(0xff303541)
        val Modal = Color(0xff292E3B)
    }
}