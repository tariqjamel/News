package com.example.newsapi

object ColorPicker {
    val colors = arrayOf(
        "#FFCCCC", // Light Red
        "#CCFFCC", // Light Green
        "#CCCCFF", // Light Blue
        "#FFFFCC", // Light Yellow
        "#FFCCFF", // Light Magenta
        "#CCFFFF", // Light Cyan
        "#F0F0F0", // Light Grey
        "#E0E0E0", // Light Grey
        "#FFD699", // Light Orange
        "#CC99FF", // Light Purple
        "#99FF99", // Light Green
        "#FF9999", // Light Maroon
        "#99FFFF", // Light Teal
        "#FFCCCC", // Light Pink
        "#FFFF99", // Light Yellow
        "#D2B48C", // Light Brown
        "#99FFCC", // Light Spring Green
        "#D2691E", // Chocolate
        "#B0E0E6", // Light Sea Green
        "#DDA0DD", // Light Blue Violet
        "#FFCC99", // Light Tomato
        "#C6E2FF", // Light Medium Slate Blue
        "#98FB98", // Light Sea Green
        "#FF9966", // Light Orange Red
        "#AFEEEE", // Light Medium Turquoise
        "#B0E0E6", // Light Dark Turquoise
        "#ADFF2F", // Green Yellow
        "#7FFF00", // Chartreuse
        "#D8BFD8", // Light Medium Purple
        "#FFD700"  // Light Goldenrod
    )

    var colorIndex = 1
        fun getColor(): String{
            return  colors[ColorPicker.colorIndex++ % colors.size]
        }

}