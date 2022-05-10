package com.example.moviebeca.model

data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)

/*fun mockMovie() = listOf(
    Movie(
            adult = false,
            "aaaaaa",
            "aaaaaa",
            listOf(2,3,4),
            1,
            "qwqwqewqe",
            "batman",
            listOf("br","ue"),
            "br",
            "The batman",
            "batmin",
            "223,
            8.4,
            "qweqwe",
            3123123,
            "Bamin",
            true,
            9.5,
            3
        )
    ),
    6,
    2,
    2
)*/